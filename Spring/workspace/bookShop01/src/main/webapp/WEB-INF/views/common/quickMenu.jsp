<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<script>
	//아래의 자바스크립트  전체 코드는  "quickMenu.jsp"페이지에서 최근 본 도서상품을 표시하는 부분에 대한 자바스크립트 코드입니다.
	//사용자가 이전과 다음 버튼을 클릭하거나 상품 이미지를 클릭했을때 해당 상품 정보를 표시하고 관리하는 역할을 합니다. 

	var array_index = 0; // 이변수는 현재 표시 중인 상품의 배열 인덱스위치가 저장됩니다. 초기값으로 0으로 설정 했습니다.
	
	
	var SERVER_URL = "${contextPath}/thumbnails.do";//이변수는 FileDownLoadController에서 썸네일이미지를 만들고 가져오기 위한 URL을 설정합니다.

	//다음 을 클릭하면 호출되는 함수로
	//빠른 퀵 메뉴에서 <hidden>태그에 저장된 상품들의 정보를 가져와 이미지를 표시합니다. 
	function fn_show_next_goods() {
		
	//현재 퀵 메뉴에 보이고 있는 하나의 도서상품 이미지를 나타내는 img요소를 선택해서 가져옵니다.
	//선택한 이유 : 다음을 클릭하면 var array_index변수의 값을 1증가 시켜 2번째로 본 최근 도서상품 썸네일 이미지를 보여주기위해 
	//           src속성 경로의 goods_id=${item.goods_id}&fileName=${item.goods_fileName} 도서상품번호와 도서상품이미지명을 변경시킵니다. 
		/*
		<img width="75"
			 height="95" id="img_sticky"
			 src="${contextPath}/thumbnails.do?goods_id=${item.goods_id}&fileName=${item.goods_fileName}">
		*/	 
		var img_sticky = document.getElementById("img_sticky");
		
	//현재 퀵 메뉴 하단 보이고 있는 하나의 도서상품 위치번호 번호를 나타내는 span요소를 선택해서 가져옵니다.
	//퀵 메뉴 하단의 페이지번호 영역인 <span id="cur_goods_num">1</span>태그 선택 
	//선택한 이유 : 다음을 클릭하면 2번째로 본 최근 도서상품 썸네일 이미지와 함께 
	//            <span id="cur_goods_num">1</span>태그 영역에 적힌 1을 2로 바꿔서 다시 넣기 위함
		var cur_goods_num = document.getElementById("cur_goods_num");
		
	//최근본 상품 중에서 1번째 2번째 3번째 4번째 도서상품 ID를 저장한 숨겨진 <input type="hidden">요소 4개를  h_goods_id배열에 담아 선택해서 가져옵니다.
	//선택한 이유 : 
		//<input type="hidden" name="h_goods_id" value="${item.goods_id}" />  index 0  최근본 1번째 도서상품 번호
		//<input type="hidden" name="h_goods_id" value="${item.goods_id}" />  index 1  최근본 2번째 도서상품 번호
		//<input type="hidden" name="h_goods_id" value="${item.goods_id}" />  index 2  최근본 3번째 도서상품 번호
		//<input type="hidden" name="h_goods_id" value="${item.goods_id}" />  index 3  최근본 4번째 도서상품 번호
		var _h_goods_id = document.frm_sticky.h_goods_id;
		console.log(_h_goods_id);

	//최근본 상품 중에서 1번째 2번째 3번째 4번째 도서상품 이미지명을 저장한 숨겨진 <input type="hidden">요소 4개를  h_goods_fileName배열에 담아 선택해서 가져옵니다.
	//선택한 이유 :  
		//<input type="hidden" name="h_goods_fileName" value="${item.goods_fileName}" />  index 0  최근본 1번째 도서상품 이미지명
		//<input type="hidden" name="h_goods_fileName" value="${item.goods_fileName}" />  index 1  최근본 2번째 도서상품 이미지명
		//<input type="hidden" name="h_goods_fileName" value="${item.goods_fileName}" />  index 2  최근본 3번째 도서상품 이미지명
		//<input type="hidden" name="h_goods_fileName" value="${item.goods_fileName}" />  index 3  최근본 4번째 도서상품 이미지명
		var _h_goods_fileName = document.frm_sticky.h_goods_fileName;
		console.log(_h_goods_fileName);
		
		
//아래 코드의 큰 흐름 : 다음 클릭시 함수 내에서 현재 배열 인덱스를 1씩 증가 시킨후 해당 인덱스에 해당하는 상품정보를 가져와 이미지와 상품번호를 업데이트 합니다.
		
		//다음 클릭시  array_index변수에 저장된 인덱스 위치값이   3(_h_goods_id배열크기-1)보다 작을 때만 array_index변수 값 인덱스 값을 1증가 시킵니다. 
		//        0     <        4  -  1  = 3
		//        1     <        4  -  1  = 3
		//        2     <        4  -  1  = 3
		//        3     <        4  -  1  = 3
		
		console.log("array_index : " + array_index);
		console.log(_h_goods_id.length -1);

		if (array_index < _h_goods_id.length - 1){
			
			array_index++; //다음 클릭시   0 -> 1  
						   //다음 클릭시   1 -> 2
						   //다음 클릭시   2 -> 3
		
		}
	
	 console.log("array_index : " + array_index );
	 console.log( _h_goods_id.length -1 );
	 

	//1증가된 인덱스 위치에 대한 그다음 상품번호와 이미지명을 가져와 표시합니다.
		var goods_id = _h_goods_id[array_index].value;
		var fileName = _h_goods_fileName[array_index].value;
		
	/*	
	 img_sticky.src

		<img width="75"
			 height="95" id="img_sticky"
			 src="${contextPath}/thumbnails.do?goods_id=${item.goods_id}&fileName=${item.goods_fileName}">
					   
	*/	
	    img_sticky.src = SERVER_URL + "?goods_id=" + goods_id + "&fileName="+ fileName;
	//				   = "${contextPath}/thumbnails.do?goods_id=" + goods_id + "&fileName="+ fileName"
		
	    cur_goods_num.innerHTML = array_index + 1;
	
		console.log(cur_goods_num);
	}
	
	
	
	//빠른 퀵 메뉴 하단의 이전을 클릭했을때
	//최근 본 상품 중에서 이전 이미지를 화면에 보여주고, 하단의 현재보이고 있는 도서상품 번호에 대한 위치를 -1 을 해서 <span>에 보여 준다. 
	function fn_show_previous_goods() {
		
		var img_sticky = document.getElementById("img_sticky");
		var cur_goods_num = document.getElementById("cur_goods_num");
		var _h_goods_id = document.frm_sticky.h_goods_id;
		var _h_goods_fileName = document.frm_sticky.h_goods_fileName;

		if (array_index > 0)
			array_index--;

		var goods_id = _h_goods_id[array_index].value;
		var fileName = _h_goods_fileName[array_index].value;
		
		img_sticky.src = SERVER_URL + "?goods_id=" + goods_id + "&fileName=" + fileName;
		
		cur_goods_num.innerHTML = array_index + 1;
		
	}

	
	//빠른 퀵메뉴 영역에 현재 보이고 있는 상품 이미지 하나를 감싸고 있는 <a>를  클릭했을때
	//-> 최근본 상품 중 2번째 상품 썸네일 이미지를 클릭한 상황을 예로 들겠습니다. 
	function goodsDetail() {
		
		//<span id="cur_goods_num">2</span>
		var cur_goods_num = document.getElementById("cur_goods_num");
		console.log(cur_goods_num); // <span id="cur_goods_num">
		
		//innerHTML속성을 적어 
		//<span id="cur_goods_num">2</span>태그 영역에 있는2을 얻어  -1 한값 1을 arrIdx변수에 저장 
		arrIdx = cur_goods_num.innerHTML - 1;

		//최근본 2번째 상품 이미지를 나타내는 img요소를 선택해서 가져옵니다.
		/*
		 <img width="75" height="95" id="img_sticky" src="/bookShop01/thumbnails.do?goods_id=356&amp;fileName=마인_메인.jpg">
		*/	
		var img_sticky = document.getElementById("img_sticky");
		console.log(img_sticky);

		//최근본 상품 중에서 1번째 2번째 3번째 4번째 도서상품 ID를 저장한 숨겨진 <input type="hidden">요소 4개를  h_goods_id배열에 담아 선택해서 가져옵니다.
		//선택한 이유 : 
			//<input type="hidden" name="h_goods_id" value="${item.goods_id}" />  index 0  최근본 1번째 도서상품 번호
			//<input type="hidden" name="h_goods_id" value="${item.goods_id}" />  index 1  최근본 2번째 도서상품 번호
			//<input type="hidden" name="h_goods_id" value="${item.goods_id}" />  index 2  최근본 3번째 도서상품 번호
			//<input type="hidden" name="h_goods_id" value="${item.goods_id}" />  index 3  최근본 4번째 도서상품 번호
		var h_goods_id = document.frm_sticky.h_goods_id;

			
		//상품 ID를 저장한 h_goods_id배열에 담긴  value속성값! 즉 최근본 상품 번호가 설정된 <input type="hidden">의 갯수 4를 얻어 변수에 저장
		var len = h_goods_id.length; //4
		
		//최근본 상품 번호가 설정된 <input type="hidden">의 갯수 4가 1보다 크면
		if (len > 1) {
			//상품번호 얻기 
			goods_id = h_goods_id[arrIdx].value;
			console.log("len > 1 : " + goods_id); //len > 1 :   356 <-도서상품번호 356
		} else {
			//상품번호 얻기 
			goods_id = h_goods_id.value;
			console.log("len < 1 : " + goods_id);

		}

		var formObj = document.createElement("form");
		var i_goods_id = document.createElement("input");

		i_goods_id.name = "goods_id";
		i_goods_id.value = goods_id;
		

		formObj.appendChild(i_goods_id);
		document.body.appendChild(formObj);
		formObj.method = "get";
		formObj.action = "${contextPath}/goods/goodsDetail.do";
		
		//최근 본 상품 중에서 두번째 상품의 썸네일을 클릭하면 최근본 2번째 상품에 대한 상품 상세 화면을 다시 요청 합니다.
		//<form method="get" action="/mytest04/goods/goodsDetail.do">
		//	<input name="goods_id" 	value = "goods_id" >
		//</form>
		
		formObj.submit();
		
	}
</script>

<body>
	<%--
			최근 상세페이지를 열어 본 도서상품 이미지를 표시하는 퀵메뉴 디자인을 나타내는 quickMenu.jsp파일입니다.
			최근 본 상품은 상품목록(new ArrayList<GoodsVo>();)에서 상품목록을 가져온 다음 첫번째 상품 이미지만 표시하고
			다른 상품 이미지는 <hidden>태그에 저장합니다.(동일한 <hidden>태그에 여러개의 데이터 저장시 자동으로 배열로 저장됩니다.)
			사용자가 다음을 클릭하면 <hidden>태그의 상품정보를 자바스크립트 함수로 전달하여 이미지를 표시합니다.
	 --%>


	<div id="sticky">
		<ul>
			<li>
				<a href="#"> 
					<img width="24" height="24" src="${contextPath}/resources/image/facebook_icon.png"> 페이스북
				</a>
			</li>
			<li>
				<a href="#"> 
					<img width="24" height="24" src="${contextPath}/resources/image/twitter_icon.png"> 트위터
				</a>
			</li>
			<li>
				<a href="#"> 
					<img width="24" height="24" src="${contextPath}/resources/image/rss_icon.png"> RSS 피드
				</a>
			</li>
		</ul>
		<div class="recent">
			<h3>최근 본 상품</h3>
			<ul>
				<c:choose>
					<%-- 최근 본 도서상품 은 상품목록(new ArrayList<GoodsVO>();)에서 상품정보(GoodsVO객체들이)들이 없을 경우  --%>
					<c:when test="${ empty sessionScope.quickGoodsList }">
						<strong>최근본 상품이 없습니다.</strong>
					</c:when>
					
					<%-- 최근 본 도서상품 은 상품목록(new ArrayList<GoodsVO>();)에서 상품정보(GoodsVO객체들이)들이 하나라도 있을 경우 --%>
					<c:otherwise>
						<form name="frm_sticky">
							
							<%-- 세션영역에 저장된 빠른 퀵 메뉴 디자인에 보여줄 이미지정보를  <hidden>태그에 차례대로 저장합니다. --%>
							<c:forEach var="item" items="${sessionScope.quickGoodsList }" varStatus="itemNum">
								<c:choose>
									<%-- 한번 반복 할때의 첫번쨰 이미지만 퀵메뉴영역에 표시하고  --%>
									<c:when test="${itemNum.count==1 }">
										<a href="javascript:goodsDetail();"> 
											<img width="75"
												 height="95" id="img_sticky"
											     src="${contextPath}/thumbnails.do?goods_id=${item.goods_id}&fileName=${item.goods_fileName}">
										</a>
										<input type="hidden" name="h_goods_id" value="${item.goods_id}" />
										<input type="hidden" name="h_goods_fileName" value="${item.goods_fileName}" />
										<br>
									</c:when>
									<c:otherwise>
										<input type="hidden" name="h_goods_id" value="${item.goods_id}" />
										<input type="hidden" name="h_goods_fileName" value="${item.goods_fileName}" />
									</c:otherwise>
								</c:choose>
							</c:forEach>
					</c:otherwise>
				</c:choose>
			</ul>
			</form>
		</div>
		<div>
			<c:choose>
				<%--  최근본 도서상품이 하나라도 없다면? --%>
				<c:when test="${ empty sessionScope.quickGoodsList }">
					<h5>&nbsp; &nbsp; &nbsp; &nbsp; 0/0 &nbsp;</h5>
				</c:when>
				<%-- 최근본 도서상품(상세페이지에 보여주는 클릭하여 조회된 GoodsVO객체)이 ArrayList배열에 하나라도 있으면? --%>
				<c:otherwise>
					<h5>
						<a href='javascript:fn_show_previous_goods();'> 이전 </a> &nbsp; 
						
						<span id="cur_goods_num">1</span>/${sessionScope.quickGoodsListNum} &nbsp; 
						
						<a href='javascript:fn_show_next_goods();'> 다음 </a>
					</h5>
				</c:otherwise>
			</c:choose>
		</div>
	</div>
</body>
</html>

