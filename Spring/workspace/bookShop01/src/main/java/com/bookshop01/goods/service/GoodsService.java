package com.bookshop01.goods.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.bookshop01.goods.vo.GoodsVO;

public interface GoodsService {
	
	//상품 리스트를 조회하는 메서드로, Map<String,List<GoodsVO>> 형태로 반환합니다.
	public Map<String,List<GoodsVO>> listGoods() throws Exception;
	
	//상품 상세정보를 조회하는 메서드로, _goods_id를 인자로 받아 Map 형태로 반환합니다.
	public Map goodsDetail(String _goods_id) throws Exception;
	
	//키워드로 상품을 검색하는 메서드로, keyword를 인자로 받아 List<String> 형태로 반환합니다.
	public List<String> keywordSearch(String keyword) throws Exception;
	
	//검색어로 상품을 검색하는 메서드로, searchWord를 인자로 받아 List<GoodsVO> 형태로 반환합니다.
	public List<GoodsVO> searchGoods(String searchWord) throws Exception;
}
