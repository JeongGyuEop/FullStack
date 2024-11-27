package com.bookshop01.board.controller;

import java.math.BigDecimal;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bookshop01.board.service.BoardService;



@Controller
public class BoardController{

	@Autowired
	BoardService boardService;

	//게시글 리스트 조회
	@RequestMapping(value = "/board/boardList.do")
	public String boardList(@RequestParam Map<String, Object> paramMap, Model model,
			                HttpServletRequest request, HttpServletResponse response) throws Exception {

		//조회 하려는 페이지
		int startPage = (paramMap.get("startPage")!=null?Integer.parseInt(paramMap.get("startPage").toString()):1);
		//한페이지에 보여줄 리스트 수
		int visiblePages = (paramMap.get("visiblePages")!=null?Integer.parseInt(paramMap.get("visiblePages").toString()):10);
		//일단 전체 건수를 가져온다.
		int totalCnt = boardService.getContentCnt(paramMap);


		//아래 1,2는 실제 개발에서는 class로 빼준다. (여기서는 이해를 위해 직접 적음)
		//1.하단 페이지 네비게이션에서 보여줄 리스트 수를 구한다.
		BigDecimal decimal1 = new BigDecimal(totalCnt);
		BigDecimal decimal2 = new BigDecimal(visiblePages);
		BigDecimal totalPage = decimal1.divide(decimal2, 0, BigDecimal.ROUND_UP);

		int startLimitPage = 0;
		//2.mysql limit 범위를 구하기 위해 계산
		if(startPage==1){
			startLimitPage = 0;
		}else{
			startLimitPage = (startPage-1)*visiblePages;
		}

		paramMap.put("start", startLimitPage);

		//MYSQL
		//paramMap.put("end", visiblePages);

		//ORACLE
		paramMap.put("end", startLimitPage+visiblePages);

		//jsp 에서 보여줄 정보 추출
		model.addAttribute("startPage", startPage+"");//현재 페이지      
		model.addAttribute("totalCnt", totalCnt);//전체 게시물수
		model.addAttribute("totalPage", totalPage);//페이지 네비게이션에 보여줄 리스트 수
		model.addAttribute("boardList", boardService.getContentList(paramMap));//검색
		
		System.out.println("조회된 개수 : "  +  boardService.getContentList(paramMap).size());
		
		//  /board/boardList 뷰 주소 얻기
		String viewName=(String)request.getAttribute("viewName");  

		return viewName;

	}

	//게시글 상세 보기
	@RequestMapping(value = "/board/view.do")
	public String boardView(@RequestParam Map<String, Object> paramMap, Model model,
							HttpServletRequest request, HttpServletResponse response) throws Exception {

		model.addAttribute("replyList", boardService.getReplyList(paramMap));
		model.addAttribute("boardView", boardService.getContentView(paramMap));

		
		//  /board/view 뷰 주소 얻기
		String viewName=(String)request.getAttribute("viewName"); 
			
		return viewName;

	}

	//게시글 등록 및 수정
	@RequestMapping(value = "/board/edit.do")
	public String boardEdit(HttpServletRequest request, 
							@RequestParam Map<String, Object> paramMap, 
							Model model) {

		//Referer 검사
		String Referer = request.getHeader("referer");

		if(Referer!=null){//URL로 직접 접근 불가
			if(paramMap.get("id") != null){ //게시글 수정
				if(Referer.indexOf("/board/view")>-1){

					//정보를 가져온다.
					model.addAttribute("boardView", boardService.getContentView(paramMap));
					return "boardEdit";
				}else{
					return "redirect:/board/list.do";
				}
			}else{ //게시글 등록
				if(Referer.indexOf("/board/list")>-1){
					return "boardEdit";
				}else{
					return "redirect:/board/list";
				}
			}
		}else{
			return "redirect:/board/list";
		}

	}

	//AJAX 호출 (게시글 등록, 수정)
	@RequestMapping(value="/board/save", method=RequestMethod.POST)
	@ResponseBody
	public Object boardSave(@RequestParam Map<String, Object> paramMap) {

		//리턴값
		Map<String, Object> retVal = new HashMap<String, Object>();

		//패스워드 암호화
		ShaPasswordEncoder encoder = new ShaPasswordEncoder(256);
		String password = encoder.encodePassword(paramMap.get("password").toString(), null);
		paramMap.put("password", password);

		//정보입력
		int result = boardService.regContent(paramMap);

		if(result>0){
			retVal.put("code", "OK");
			retVal.put("message", "등록에 성공 하였습니다.");
		}else{
			retVal.put("code", "FAIL");
			retVal.put("message", "등록에 실패 하였습니다.");
		}

		return retVal;

	}

	//AJAX 호출 (게시글 삭제)
	@RequestMapping(value="/board/del", method=RequestMethod.POST)
	@ResponseBody
	public Object boardDel(@RequestParam Map<String, Object> paramMap) {

		//리턴값
		Map<String, Object> retVal = new HashMap<String, Object>();

		//패스워드 암호화
		ShaPasswordEncoder encoder = new ShaPasswordEncoder(256);
		String password = encoder.encodePassword(paramMap.get("password").toString(), null);
		paramMap.put("password", password);

		//정보입력
		int result = boardService.delBoard(paramMap);

		if(result>0){
			retVal.put("code", "OK");
		}else{
			retVal.put("code", "FAIL");
			retVal.put("message", "패스워드를 확인해주세요.");
		}

		return retVal;

	}

	//AJAX 호출 (게시글 패스워드 확인)
	@RequestMapping(value="/board/check", method=RequestMethod.POST)
	@ResponseBody
	public Object boardCheck(@RequestParam Map<String, Object> paramMap) {

		//리턴값
		Map<String, Object> retVal = new HashMap<String, Object>();

		//패스워드 암호화
		ShaPasswordEncoder encoder = new ShaPasswordEncoder(256);
		String password = encoder.encodePassword(paramMap.get("password").toString(), null);
		paramMap.put("password", password);

		//정보입력
		int result = boardService.getBoardCheck(paramMap);

		if(result>0){
			retVal.put("code", "OK");
		}else{
			retVal.put("code", "FAIL");
			retVal.put("message", "패스워드를 확인해주세요.");
		}

		return retVal;

	}

	//AJAX 호출 (댓글 등록)
	@RequestMapping(value="/board/reply/save", method=RequestMethod.POST)
	@ResponseBody
	public Object boardReplySave(@RequestParam Map<String, Object> paramMap) {

		//리턴값
		Map<String, Object> retVal = new HashMap<String, Object>();

		//패스워드 암호화
		ShaPasswordEncoder encoder = new ShaPasswordEncoder(256);
		String password = encoder.encodePassword(paramMap.get("reply_password").toString(), null);
		paramMap.put("reply_password", password);

		//정보입력
		int result = boardService.regReply(paramMap);

		if(result>0){
			retVal.put("code", "OK");
			retVal.put("reply_id", paramMap.get("reply_id"));
			retVal.put("parent_id", paramMap.get("parent_id"));
			retVal.put("message", "등록에 성공 하였습니다.");
		}else{
			retVal.put("code", "FAIL");
			retVal.put("message", "등록에 실패 하였습니다.");
		}

		return retVal;

	}

	//AJAX 호출 (댓글 삭제)
	@RequestMapping(value="/board/reply/del", method=RequestMethod.POST)
	@ResponseBody
	public Object boardReplyDel(@RequestParam Map<String, Object> paramMap) {

		//리턴값
		Map<String, Object> retVal = new HashMap<String, Object>();

		//패스워드 암호화
		ShaPasswordEncoder encoder = new ShaPasswordEncoder(256);
		String password = encoder.encodePassword(paramMap.get("reply_password").toString(), null);
		paramMap.put("reply_password", password);

		//정보입력
		int result = boardService.delReply(paramMap);

		if(result>0){
			retVal.put("code", "OK");
		}else{
			retVal.put("code", "FAIL");
			retVal.put("message", "삭제에 실패했습니다. 패스워드를 확인해주세요.");
		}

		return retVal;

	}

	//AJAX 호출 (댓글 패스워드 확인)
	@RequestMapping(value="/board/reply/check", method=RequestMethod.POST)
	@ResponseBody
	public Object boardReplyCheck(@RequestParam Map<String, Object> paramMap) {

		//리턴값
		Map<String, Object> retVal = new HashMap<String, Object>();

		//패스워드 암호화
		ShaPasswordEncoder encoder = new ShaPasswordEncoder(256);
		String password = encoder.encodePassword(paramMap.get("reply_password").toString(), null);
		paramMap.put("reply_password", password);

		//정보입력
		boolean check = boardService.checkReply(paramMap);

		if(check){
			retVal.put("code", "OK");
			retVal.put("reply_id", paramMap.get("reply_id"));
		}else{
			retVal.put("code", "FAIL");
			retVal.put("message", "패스워드를 확인해 주세요.");
		}

		return retVal;

	}

	//AJAX 호출 (댓글 수정)
	@RequestMapping(value="/board/reply/update", method=RequestMethod.POST)
	@ResponseBody
	public Object boardReplyUpdate(@RequestParam Map<String, Object> paramMap) {

		//리턴값
		Map<String, Object> retVal = new HashMap<String, Object>();

		//패스워드 암호화
		ShaPasswordEncoder encoder = new ShaPasswordEncoder(256);
		String password = encoder.encodePassword(paramMap.get("reply_password").toString(), null);
		paramMap.put("reply_password", password);

		System.out.println(paramMap);

		//정보입력
		boolean check = boardService.updateReply(paramMap);

		if(check){
			retVal.put("code", "OK");
			retVal.put("reply_id", paramMap.get("reply_id"));
			retVal.put("message", "수정에 성공 하였습니다.");
		}else{
			retVal.put("code", "FAIL");
			retVal.put("message", "수정에 실패 하였습니다.");
		}

		return retVal;

	}

	


}
