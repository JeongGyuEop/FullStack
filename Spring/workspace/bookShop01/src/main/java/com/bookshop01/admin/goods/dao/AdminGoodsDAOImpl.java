package com.bookshop01.admin.goods.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.bookshop01.goods.vo.GoodsVO;
import com.bookshop01.goods.vo.ImageFileVO;
import com.bookshop01.order.vo.OrderVO;

@Repository("adminGoodsDAO")
public class AdminGoodsDAOImpl  implements AdminGoodsDAO{
	@Autowired
	private SqlSession sqlSession;
	
	
	//1. 새 도서상품을 테이블에 INSERT
	@Override
	public int insertNewGoods(Map newGoodsMap) throws DataAccessException {
		sqlSession.insert("mapper.admin.goods.insertNewGoods",newGoodsMap);
		return Integer.parseInt((String)newGoodsMap.get("goods_id")); // 추가한 새 도서 상품 번호를 리턴 
	}
	
	//2. 새 도서상품 이미지 정보를 테이블에 INSERT
	@Override
	public void insertGoodsImageFile(List fileList)  throws DataAccessException {
		
		for(int i=0; i<fileList.size();i++){
			ImageFileVO imageFileVO=(ImageFileVO)fileList.get(i);
			//상품 이미지 정보를 테이블에 추가 
			sqlSession.insert("mapper.admin.goods.insertGoodsImageFile",imageFileVO);
		}
		
		
	}
		
	@Override
	public List<GoodsVO>selectNewGoodsList(Map condMap) throws DataAccessException {
		ArrayList<GoodsVO>  goodsList=(ArrayList)sqlSession.selectList("mapper.admin.goods.selectNewGoodsList",condMap);
		return goodsList;
	}
	
	//3.
	@Override
	public GoodsVO selectGoodsDetail(int goods_id) throws DataAccessException{
		GoodsVO goodsBean=(GoodsVO)sqlSession.selectOne("mapper.admin.goods.selectGoodsDetail",goods_id);
		return goodsBean;
	}
	
	//4.
	@Override
	public List selectGoodsImageFileList(int goods_id) throws DataAccessException {
		List imageList=(List)sqlSession.selectList("mapper.admin.goods.selectGoodsImageFileList",goods_id);
		return imageList;
	}
	
	//5.
	@Override
	public void updateGoodsInfo(Map goodsMap) throws DataAccessException{
		sqlSession.update("mapper.admin.goods.updateGoodsInfo",goodsMap);
	}
	
	@Override
	public void deleteGoodsImage(int image_id) throws DataAccessException{
		sqlSession.delete("mapper.admin.goods.deleteGoodsImage",image_id);
	}
	
	@Override
	public void deleteGoodsImage(List fileList) throws DataAccessException{
		int image_id;
		for(int i=0; i<fileList.size();i++){
			ImageFileVO bean=(ImageFileVO) fileList.get(i);
			image_id=bean.getImage_id();
			sqlSession.delete("mapper.admin.goods.deleteGoodsImage",image_id);	
		}
	}

	@Override
	public List<OrderVO> selectOrderGoodsList(Map condMap) throws DataAccessException{
		List<OrderVO>  orderGoodsList=(ArrayList)sqlSession.selectList("mapper.admin.selectOrderGoodsList",condMap);
		return orderGoodsList;
	}	
	
	@Override
	public void updateOrderGoods(Map orderMap) throws DataAccessException{
		sqlSession.update("mapper.admin.goods.updateOrderGoods",orderMap);
		
	}

	@Override
	public void updateGoodsImage(List<ImageFileVO> imageFileList) throws DataAccessException {
		
		for(int i=0; i<imageFileList.size();i++){
			ImageFileVO imageFileVO = imageFileList.get(i);
			sqlSession.update("mapper.admin.goods.updateGoodsImage",imageFileVO);	
		}
		
	}





	

}
