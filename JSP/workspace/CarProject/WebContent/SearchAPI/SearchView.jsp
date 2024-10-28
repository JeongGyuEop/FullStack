<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<% request.setCharacterEncoding("UTF-8"); %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

	<script src="http://code.jquery.com/jquery-latest.min.js"></script>
	
	<script type="text/javascript">
	
		$(function() {
		
			// 검색요청 버튼(<button id="searchBtn">)을 클릭했을 때
			// $.ajax메소드로 SearchAPI.java 서블릿 서버페이지를 요청해서 JSON 응답받게 하자
			$("#searchBtn").click(function() {
				
				$.ajax({
					url: "../NaverSearchAPI.do",
					type: "GET",
					
					// 요청할 값
					// 1. 입력한 검색어
					// 2. 선택한 블로그의 검색위치
					data: {
						keyword: $("#keyword").val(),
						startNum: $("#startNum option:selected").val()
					},
					
					// 응답받을 데이터 형식 설정
					dataType: "json",
					
					success: suncFuncJson, // 요청 성공 시 호출할 콜백 함수명 설정
					
					error: errFunc, // 요청 실패 시 호출할 콜백함수명 호출구문으로 설정
				});
			});
		});
		
		// 검색 요청 통신에 성공하면 호출되는 콜백 함수 만들기
		function suncFuncJson(data) {
			// json 데이터 응답 받아 표시
			console.log(data);
			
			var str = "";
			
			$.each(data.items, function(index, item){
				
				str += "<ul>";
				str += 		"<li>" + (index+1) + "</li>"; // 검색한 순번
				str += 		"<li>" + item.title + "</li>"; // 검색결과 문서의 제목
				str += 		"<li>" + item.description + "</li>"; // 검색결과 문서의 내용
				str += 		"<li>" + item.blogname + "</li>"; // 검색결과 블로그 이름
				str += 		"<li>" + item.bloggerlink + "</li>"; // 검색결과 블로그 포스트를 작성한 링크
				str += 		"<li>" + item.postdate+ "</li>"; // 검색결과 블로그 포스트를 작성한 날짜
				str += 		"<li><a href='" + item.link + "'>바로가기</a></li>"; // 검색결과 블로그 포스트를 작성한 날짜
				str += "</ul>";
				
			});
			
			// id="searchResult"인 <div> 영역을 선택해서 HTML 태그형식으로 보내서 보여주자
			$("#searchResult").html(str);
			
/*
			{
				   "lastBuildDate":"Mon, 28 Oct 2024 17:53:09 +0900",
				   "total":2228180,
				   "start":1,
				   "display":10,
				   "items":[
				      {
				         "title":"신차장기<b>렌트카<\/b> 견적비교 및 장단점 정리!",
				         "link":"https:\/\/blog.naver.com\/ige1026\/223620944462",
				         "description":"신차장기<b>렌트카<\/b> 견적비교 및 장단점 정리! 10월의 두 번째 주말 편하게 보내셨나요? 저는 1년만에 KTX... 신차 장기<b>렌트카<\/b>란? 사촌이 장기렌탈 관련 회사에 있어서 알려줬는데, 예전에는 단기 렌트 이용자수가... ",
				         "bloggername":"우리의 블로그",
				         "bloggerlink":"blog.naver.com\/ige1026",
				         "postdate":"20241016"
				      },
				      {
				         "title":"베트남 다낭 자유여행 호이안 올드타운 등 다낭 <b>렌트카<\/b> 추천",
				         "link":"https:\/\/blog.naver.com\/kkulee\/223626410031",
				         "description":"하루는 다낭 <b>렌트카<\/b> 타고 호이안 올드타운까지 다녀왔는데 진짜 완전 편하고 좋았습니다. 항상... 다낭 <b>렌트카<\/b> 예약 https:\/\/www.vetnamplay.com\/S0334393#none 베트남 <b>렌트카<\/b>는 국내처럼 직접 운전하는 게 아니라 베테랑... ",
				         "bloggername":"Paradise is where I am.",
				         "bloggerlink":"blog.naver.com\/kkulee",
				         "postdate":"20241020"
				      }
				   ]
				}
*/
		}
		
		// 요청 실패 시 호출되는 콜백 함수
		function errFunc() {
			alert("요청 실패!");
		}
	
	</script>
	
	<style type="text/css">
		ul{
			border: 2px #cccccc solid;
		}
	</style>
	
</head>
<body>
	<div>
		<div>
			<%-- 네이버 블로그 OPEN API 검색요청을 위한 <form> 정의 --%>
			<form id="searchFrm">
				한 페이지에 10개씩 출력됨 <br>
				
				<%-- 블로그 검색 시작 위치를 페이지 단위로 선택하는 디자인 --%>
				<select id="startNum">
					<option value="1">1페이지</option>
					<option value="11">2페이지</option>
					<option value="21">3페이지</option>
					<option value="31">4페이지</option>
					<option value="41">5페이지</option>
				</select>
				
				<%-- 검색어를 입력할 수 있는 디자인 --%>
				<input type="text" id="keyword" placeholder="검색어를 입력하세요">
				
				<%-- 검색 요청 버튼 --%>
				<button type="button" id="searchBtn">검색 요청</button>
				
			</form>
		</div>
		
		<%-- 검색 결과 데이터가 출력되는 영역입니다. --%>
		<div class="row" id="searchResult">
			여기에 검색 결과가 출력됩니다.
			
		</div>
	</div>
</body>
</html>