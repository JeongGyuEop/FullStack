package com.bookshop01.board.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookshop01.board.dao.BoardDao;
import com.bookshop01.board.vo.Board;
import com.bookshop01.board.vo.BoardReply;
import com.bookshop01.board.service.BoardService;

@Service("boardService")
public class BoardServiceImpl implements BoardService {

	@Autowired
	private BoardDao boardDao;

	@Override
	public int regContent(Map<String, Object> paramMap) {
		//아이디가 없으면 입력
		if(paramMap.get("id")==null) {
			return boardDao.regContent(paramMap);
		}else {//아이디가 있으면 수정
			return boardDao.modifyContent(paramMap);
		}
	}

	@Override
	public int getContentCnt(Map<String, Object> paramMap) {
		return boardDao.getContentCnt(paramMap);
	}

	@Override
	public List<Board> getContentList(Map<String, Object> paramMap) {
		return boardDao.getContentList(paramMap);
	}

	@Override
	public Board getContentView(Map<String, Object> paramMap) {
		return boardDao.getContentView(paramMap);
	}

	@Override
	public int regReply(Map<String, Object> paramMap) {
		return boardDao.regReply(paramMap);
	}

	@Override
	public List<BoardReply> getReplyList(Map<String, Object> paramMap) {

		List<BoardReply> boardReplyList = boardDao.getReplyList(paramMap);

		//msyql 에서 계층적 쿼리가 어려우니 여기서 그냥 해결하자

		//부모
		List<BoardReply> boardReplyListParent = new ArrayList<BoardReply>();
		//자식
		List<BoardReply> boardReplyListChild = new ArrayList<BoardReply>();
		//통합
		List<BoardReply> newBoardReplyList = new ArrayList<BoardReply>();

		//1.부모와 자식 분리
		for(BoardReply boardReply: boardReplyList){
			if(boardReply.getDepth().equals("0")){
				boardReplyListParent.add(boardReply);
			}else{
				boardReplyListChild.add(boardReply);
			}
		}

		//2.부모를 돌린다.
		for(BoardReply boardReplyParent: boardReplyListParent){
			//2-1. 부모는 무조건 넣는다.
			newBoardReplyList.add(boardReplyParent);
			//3.자식을 돌린다.
			for(BoardReply boardReplyChild: boardReplyListChild){
				//3-1. 부모의 자식인 것들만 넣는다.
				if(boardReplyParent.getReply_id().equals(boardReplyChild.getParent_id())){
					newBoardReplyList.add(boardReplyChild);
				}

			}

		}

		//정리한 list return
		return newBoardReplyList;
	}

	@Override
	public int delReply(Map<String, Object> paramMap) {
		return boardDao.delReply(paramMap);
	}

	@Override
	public int getBoardCheck(Map<String, Object> paramMap) {
		return boardDao.getBoardCheck(paramMap);
	}

	@Override
	public int delBoard(Map<String, Object> paramMap) {
		return boardDao.delBoard(paramMap);
	}

	@Override
	public boolean checkReply(Map<String, Object> paramMap) {
		return boardDao.checkReply(paramMap);
	}

	@Override
	public boolean updateReply(Map<String, Object> paramMap) {
		return boardDao.updateReply(paramMap);
	}

}
