<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<% request.setCharacterEncoding("UTF-8"); %>

<c:set var="contextPath"  value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

	<script src="http://code.jquery.com/jquery-latest.min.js"></script>
	
	
	<style type="text/css">
		ul{
			border: 2px #cccccc solid;
		}
	</style>
	
		<script type="text/javascript">

		$(function() {/* 
	        // 서버 측 데이터를 JavaScript로 전달
	        let keyword = decodeURIComponent("${keyword}"); */
	        console.log("서버로부터 전달된 keyword: ", keyword);

	        // 첫 검색 요청
	        suncFuncJson();  // 페이지 로드 시 초기 요청

	    });
		
		// 페이지 번호 선택 시마다 비동기적으로 결과 갱신
        $("#startNum").on("change", function() {
            console.log("페이지 번호 변경됨:", $(this).val());  // 선택된 페이지 번호 로그 출력
            fetchResults();  // 선택 값이 변경될 때마다 호출
        });		
		
		// 검색 결과를 불러오는 함수
        function fetchResults() {
        	
        	// JSP에서 JavaScript로 contextPath 전달
            const contextPath = "${contextPath}";
            console.log(contextPath);
            
            let selectedPage = $("#startNum").val();  // 선택한 페이지의 값
            console.log("선택된 페이지 번호:", selectedPage);
            
	        // 서버 측 데이터를 JavaScript로 전달
	        let keyword = decodeURIComponent("${keyword}");
            $.ajax({
                url: contextPath + "/Car/NaverSearchAPI.do",
                type: "GET",
                async: true,
                data: {
                    keyword: keyword,
                    startNum: selectedPage
                },
                success: suncFuncJson,
                error: errFunc
            });
        }
		
		// 검색 요청 통신에 성공하면 호출되는 콜백 함수 만들기
		function suncFuncJson() {
			// searchData를 JSON 문자열로 변환하여 안전하게 JavaScript 변수로 전달
            let data = JSON.parse('${searchData}');
            
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
		

	</script>
	
</head>
<body>
	<div>
		<div>
			<%-- 네이버 블로그 OPEN API 검색요청을 위한 <form> 정의 --%>
			<form id="searchFrm">
				<!-- 한 페이지에 10개씩 출력됨 <br> -->
				
				<%-- 블로그 검색 시작 위치를 페이지 단위로 선택하는 디자인 --%>
				<select id="startNum">
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

	
</body>
</html>