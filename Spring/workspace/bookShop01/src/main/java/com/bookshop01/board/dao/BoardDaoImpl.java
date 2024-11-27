package com.bookshop01.board.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bookshop01.board.vo.Board;
import com.bookshop01.board.vo.BoardReply;


@Repository("boardDao")
public class BoardDaoImpl implements BoardDao{
	
	@Autowired
    private SqlSession sqlSession;
 
    public void setSqlSession(SqlSession sqlSession){
        this.sqlSession = sqlSession;
    }
    
	@Override
	public int regContent(Map<String, Object> paramMap) {
		return sqlSession.insert("mapper.board.insertContent", paramMap);
	}
	
	@Override
	public int modifyContent(Map<String, Object> paramMap) {
		return sqlSession.update("mapper.board.updateContent", paramMap);
	}

	@Override
	public int getContentCnt(Map<String, Object> paramMap) {
		return sqlSession.selectOne("mapper.board.selectContentCnt", paramMap);
	}
	
	@Override
	public List<Board> getContentList(Map<String, Object> paramMap) {
		return sqlSession.selectList("mapper.board.selectContent", paramMap);
	}

	@Override
	public Board getContentView(Map<String, Object> paramMap) {
		return sqlSession.selectOne("mapper.board.selectContentView", paramMap);
	}

	@Override
	public int regReply(Map<String, Object> paramMap) {
		return sqlSession.insert("mapper.board.insertBoardReply", paramMap);
	}

	@Override
	public List<BoardReply> getReplyList(Map<String, Object> paramMap) {
		return sqlSession.selectList("mapper.board.selectBoardReplyList", paramMap);
	}

	@Override
	public int delReply(Map<String, Object> paramMap) {
		if(paramMap.get("r_type").equals("main")) {
			//부모부터 하위 다 지움
			return sqlSession.delete("mapper.board.deleteBoardReplyAll", paramMap);
		}else {
			//자기 자신만 지움
			return sqlSession.delete("mapper.board.deleteBoardReply", paramMap);
		}
		
		
	}

	@Override
	public int getBoardCheck(Map<String, Object> paramMap) {
		return sqlSession.selectOne("mapper.board.selectBoardCnt", paramMap);
	}

	@Override
	public int delBoard(Map<String, Object> paramMap) {
		return sqlSession.delete("mapper.board.deleteBoard", paramMap);
	}

	@Override
	public boolean checkReply(Map<String, Object> paramMap) {
		int result = sqlSession.selectOne("mapper.board.selectReplyPassword", paramMap);
				
		if(result>0) {
			return true;
		}else {
			return false;
		}
		
	}

	@Override
	public boolean updateReply(Map<String, Object> paramMap) {
		int result = sqlSession.update("mapper.board.updateReply", paramMap);
		
		if(result>0) {
			return true;
		}else {
			return false;
		}
	}
	
}
