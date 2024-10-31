<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<% 
	request.setCharacterEncoding("UTF-8"); 
	String contextPath = request.getContextPath();
%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

	
	
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
				<!-- 한 페이지에 10개씩 출력됨 <br> -->
				
				<%-- 블로그 검색 시작 위치를 페이지 단위로 선택하는 디자인 --%>
				<select id="start">
					<option value="1">1페이지</option>
					<option value="6">2페이지</option>
					<option value="11">3페이지</option>
					<option value="16">4페이지</option>
					<option value="21">5페이지</option>
				</select>
				
				<%-- 검색어를 입력할 수 있는 디자인
				<input type="text" id="keyword" placeholder="검색어를 입력하세요">

				검색 요청 버튼
				<button type="button" id="searchBtn">검색 요청</button>
				--%>
			</form>
		</div>
		
		<%-- 검색 결과 데이터가 출력되는 영역입니다. --%>
		<div class="row" id="searchResult">

			
		</div>
	</div>

	<script src="http://code.jquery.com/jquery-latest.min.js"></script>
	
	<script>
	
	$(document).ready(function() {
		let searchData = '${searchData}';
		console.log(typeof searchData);
		if (searchData !== '') {
		    let data = JSON.parse(searchData);
		    suncFuncJson(data);
		}
	});
	
	
	// 검색 요청 통신에 성공하면 호출되는 콜백 함수 만들기
	function suncFuncJson(data) {
        
		console.log()
        console.log("Data Items:", data.items);  // 데이터가 제대로 들어왔는지 확인
	
        // 테이블의 헤더와 첫 행을 구성하는 HTML 문자열 초기화
        let str = "<tr style='background-color:aqua;'>"
                + "<td align='center'>제목</td>"
                + "<td align='center'>요약내용</td>"
                + "<td align='center'>블로거명</td>"
                + "<td align='center'>작성날짜</td>"
                + "<td align='center'>링크</td>"
                + "</tr>";

        // 각 item을 테이블의 행으로 추가
        $.each(data.items, function(index, item) {   
        	    str += "<tr>";
        	    str += "<td>" + item.title + "</td>";                // 제목
        	    str += "<td>" + item.description + "</td>";          // 요약 내용
        	    str += "<td>" + item.bloggername + "</td>";          // 블로거 이름
        	    str += "<td>" + item.postdate + "</td>";             // 작성 날짜
        	    str += "<td><a href='" + item.link + "' target='_blank'>바로가기</a></td>";  // 링크
        	    str += "</tr>";
        });


        // 전체 <table> 요소로 출력
        $("#searchResult").html("<table border='1' width='100%'>" + str + "</table>");
	}
	
	// 요청 실패 시 호출되는 콜백 함수
	function errFunc(xhr, status, error) {
		console.error("요청 실패 - 상태 코드:", xhr.status);
		console.error("에러 메시지:", error);
		alert("요청 실패! 상태 코드: " + xhr.status + ", 에러 메시지: " + error);
	}
	
		
	
		$(document).on("change", "#start", function() {
			    const selectedPage = $(this).val();  // 페이지 번호 값 가져오기
			    const keyword = decodeURIComponent('${keyword}'); // 입력된 검색어 값 가져오기
			    
			    console.log(selectedPage);
			    console.log(keyword);
			    
			    $.ajax({
			        url: "<%=contextPath%>/Car/NaverSearchAPIajax.do",
			        type: "get",
			        async: true,
			        data: {
			            keyword: keyword,
			            startNum: selectedPage
			        },
			        success: function(data) {
			        	suncFuncJson(data);
			        },
			        error: errFunc
			    });
			});

	</script>
	
</body>
</html>