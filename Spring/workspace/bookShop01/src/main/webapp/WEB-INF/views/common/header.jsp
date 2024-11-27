<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"
    isELIgnored="false"
    %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath"  value="${pageContext.request.contextPath}" />

<script type="text/javascript">
	var loopSearch=true;
	
	//사용자가 검색창에 검색키워드를 입력하면 Ajax기능을 이용해 해당 키워드가 포함된 목록을 조회해서 가져옵니다.
	//그런 후 ! id속성값이  suggest인 <div>태그를 선택해 차례대로 표시합니다. 
	function keywordSearch(){
		
		//제시된 키워드를 클릭하면 keywordSearch()함수의 실행을 중지 시키고 빠져나감
		if(loopSearch==false){
			return;
		}
		
		//사용자가 검색창에 입력한 검색키워드를 가져옵니다.
	 	var value=document.frmSearch.searchWord.value;
		
		
		$.ajax({
			type : "get",
			async : false, //false인 경우 동기식으로 처리한다.
			url : "${contextPath}/goods/keywordSearch.do",
			data : {keyword:value},
			success : function(data, textStatus) {
				console.log(data);
				
				
//			    {"keyword":["가장 빨리 만나는 자바9",
//    			"자바로 배우는 리팩토링",
//    			"자바 EE 디자인 패턴",
//    			"유지 보수가 가능한 코딩의 기술-자바편",
//    			"초보자를 위한 자바 프로그래밍",
//    			"자바스크립트 배우기",
//    			"Try! helloworld 자바스크립트"]}
			    var jsonInfo = JSON.parse(data);
				displayResult(jsonInfo);
			},
			error : function(data, textStatus) {
				alert("에러가 발생했습니다."+data);
			},
			complete : function(data, textStatus) {
				//alert("작업을완료 했습니다");
				
			}
		}); //end ajax	
	}
	
	function displayResult(jsonInfo){
		
//	    {"keyword":["가장 빨리 만나는 자바9",
//		"자바로 배우는 리팩토링",
//		"자바 EE 디자인 패턴",
//		"유지 보수가 가능한 코딩의 기술-자바편",
//		"초보자를 위한 자바 프로그래밍",
//		"자바스크립트 배우기",
//		"Try! helloworld 자바스크립트"]}
		
		var count = jsonInfo.keyword.length;
		if(count > 0) {
		    var html = '';
		    for(var i in jsonInfo.keyword){
			   html += "<a href=\"javascript:select('"+jsonInfo.keyword[i]+"')\">"+jsonInfo.keyword[i]+"</a><br/>";
		    }
		    /*
		    
		    	<a href="javascript:select('가장 빨리 만나는 자바9')">가장 빨리 만나는 자바9</a><br/>
		    	<a href="javascript:select('자바로 배우는 리팩토링')">자바로 배우는 리팩토링</a><br/>
		    	<a href="javascript:select('가장 빨리 만나는 자바9')">가장 빨리 만나는 자바9</a><br/>
		    	<a href="javascript:select('자바 EE 디자인 패턴')">자바 EE 디자인 패턴</a><br/>
		    	<a href="javascript:select('유지 보수가 가능한 코딩의 기술-자바편')">유지 보수가 가능한 코딩의 기술-자바편</a><br/>
		    	<a href="javascript:select('초보자를 위한 자바 프로그래밍')">초보자를 위한 자바 프로그래밍</a><br/>
		    	<a href="javascript:select('자바스크립트 배우기')">자바스크립트 배우기</a><br/>
		    	<a href="javascript:select('Try! helloworld 자바스크립트')">Try! helloworld 자바스크립트</a><br/>
		    
		    */
		    
		    //<div id="suggestList"></div> 태그를 선택해  
		    var listView = document.getElementById("suggestList");
		    //<div>사이 내부에 데이터 집어 넣기</div>
		    listView.innerHTML = html;
		    /*
		    	  <div id="suggestList">
				    	<a href="javascript:select('가장 빨리 만나는 자바9')">가장 빨리 만나는 자바9</a><br/>
				    	<a href="javascript:select('자바로 배우는 리팩토링')">자바로 배우는 리팩토링</a><br/>
				    	<a href="javascript:select('가장 빨리 만나는 자바9')">가장 빨리 만나는 자바9</a><br/>
				    	<a href="javascript:select('자바 EE 디자인 패턴')">자바 EE 디자인 패턴</a><br/>
				    	<a href="javascript:select('유지 보수가 가능한 코딩의 기술-자바편')">유지 보수가 가능한 코딩의 기술-자바편</a><br/>
				    	<a href="javascript:select('초보자를 위한 자바 프로그래밍')">초보자를 위한 자바 프로그래밍</a><br/>
				    	<a href="javascript:select('자바스크립트 배우기')">자바스크립트 배우기</a><br/>
				    	<a href="javascript:select('Try! helloworld 자바스크립트')">Try! helloworld 자바스크립트</a><br/>
		    	  </div>
		    
		    */
		    show('suggest');
		}else{
		    hide('suggest');
		} 
	}
	
	function select(selectedKeyword) {
		 document.frmSearch.searchWord.value=selectedKeyword;
		 loopSearch = false;
		 hide('suggest');
	}
		
	function show(elementId) {
		 var element = document.getElementById(elementId);
		 if(element) {
		  element.style.display = 'block';
		 }
		}
	
	function hide(elementId){
	   var element = document.getElementById(elementId);
	   if(element){
		  element.style.display = 'none';
	   }
	}

</script>
<body>
	<div id="logo">
	<a href="${contextPath}/main/main.do">
		<img width="176" height="80" alt="booktopia" src="${contextPath}/resources/image/Booktopia_Logo.jpg">
		</a>
	</div>
	<div id="head_link">
		<ul>
		   <c:choose>
		   	<%-- 세션에  isLogOn변수 값이 true이고  세션에 조회된 memberVO객체가 저장되어 있으므로 로그인 된 화면을 보여주자. --%>
		     <c:when test="${sessionScope.isLogOn==true and not empty sessionScope.memberInfo }">
			   <li><a href="${contextPath}/member/logout.do">로그아웃</a></li>
			   <li><a href="${contextPath}/mypage/myPageMain.do">마이페이지</a></li>
			   <li><a href="${contextPath}/cart/myCartList.do">장바구니</a></li>
			   <li><a href="#">주문배송</a></li>
			 </c:when>
			 <%--로그아웃된 화면을 보여주자. --%>
			 <c:otherwise>
			   <li><a href="${contextPath}/member/loginForm.do">로그인</a></li>
			   <li><a href="${contextPath}/member/memberForm.do">회원가입</a></li> 
			 </c:otherwise>
			</c:choose>
			   <li><a href="${contextPath}/board/boardList.do">고객센터</a></li>
			  <%--세션에 isLogOn변수 값이 true이고 세션에 입력한 아이디(admin)로 조회된 MemberVO객체의 member_id변수값이 admin이라면?
			  	   관리자 화면 추가 
			   --%>
		      <c:if test="${sessionScope.isLogOn==true and sessionScope.memberInfo.member_id =='admin' }">  
		   	   <li class="no_line"><a href="${contextPath}/admin/goods/adminGoodsMain.do">관리자</a></li>
			  </c:if>
			  
		</ul>
	</div>
	<br>
	
	<div id="search" >
		<form name="frmSearch" action="${contextPath}/goods/searchGoods.do" >
			<input name="searchWord" class="main_input" type="text" value="" onKeyUp="keywordSearch()"> 
			<input type="submit" name="search" class="btn1"  value="검 색" >
		</form>
	</div>
   <div id="suggest">
        <div id="suggestList"></div>
   </div>
</body>
</html>






