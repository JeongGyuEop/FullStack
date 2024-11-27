<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8" 	isELIgnored="false"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>


<%--
	 goodsDetail.jsp에서는 상품 상세페이지를 보여주는 페이지로  
	 상품정보 테이블(t_shopping_goods)과 상품이미지정보 테이블(t_goods_detail_image) 에서 조회한 상품 하나의 정보를 표시합니다.	 
 --%>


<c:set var="contextPath"  value="${pageContext.request.contextPath}"  />

<%-- GoodsControllerImpl클래스의 goodsDetail메소드 내부에서 ModelAndView에 저장했던  도서상품 정보와 이미지 정보 얻기 --%>
<c:set var="goods"  value="${goodsMap.goodsVO}"  />
<c:set var="imageList"  value="${goodsMap.imageList }"  />
 <%
     //치환 변수 선언합니다.
      pageContext.setAttribute("crcn", "\r\n"); //개행문자
    //pageContext.setAttribute("crcn" , "\n"); //Ajax로 변경 시 개행 문자 
      pageContext.setAttribute("br", "<br/>"); //br 태그
  /*    
  	 참고. 도서 상품 목차 등에 개행문자(\r\n) 가 포함되어 있으면 웹페이지에서는 이를 <br>태그를 사용해 바꿔줘야 합니다.
  	      즉, 테이블의 상품목차를 웹페이지에 표시하면서 개행 기능을 유지하려면 
  	      개행문자(\r\n)를 <br>태그로 대체해서 표시해야 상품 목차를 등록할때의 형태로 웹페이지에 표시됩니다.
  	      
  	      <textArea>태그 내의 입력한 value값으로 데이터를 불러오기 떄문에 개행문자(엔터키처리한 \r\n문자)가 그대로 표기 되는 반면
  	      홈쇼핑 사이트화면(웹브라우저에 보여지는 화면)에서는 textArea가 아닌 html태그 자체를 불러오기 때문에 <br>태그로 치환해주어야 합니다.
  	      
  	      글을 작성할떄(글쓰기할때) textArea태그에선 개행문자가 \n 입니다.
  	      웹브라우저에는 html태그로 표현되는데 html태그에서 개행문자역할은 <br>태그 입니다. 안바꿔주면 그냥 한줄에 출력됩니다.
  	      
  	      
  	//  "crcn"은 개행문자를 나타내는 이스케이프 시퀀스(escape sequence) 중 하나입니다. 
  	//  "crcn"은 Carriage Return(CR)과 Line Feed(LF)의 조합을 나타내며, 
  	//   일반적으로 텍스트 파일에서 한 줄의 끝을 나타내는 데 사용됩니다.

  	//  Carriage Return은 문자열 커서를 문자열의 맨 앞으로 이동시키는 제어 문자이고, 
  	//  Line Feed는 문자열 커서를 다음 줄로 이동시키는 제어 문자입니다. 
  	//  두 문자를 조합한 "crcn"은 텍스트 파일에서 한 줄을 끝내고 다음 줄로 넘어가는 역할을 합니다.

  	//  참고로, 다른 이스케이프 시퀀스로는 "\n" (Line Feed)과 "\r" (Carriage Return)이 있습니다. 
  	//  이들은 각각 개행문자를 나타내는데 사용됩니다. 
  	//  예를 들어, Unix 및 Linux 운영 체제에서는 일반적으로 "\n"을 사용하고, Windows 운영 체제에서는 "\r\n"을 사용합니다. 
  	      
  	      
  */
%>  
<html>
<head>
<style>
/*
  #layer라는 ID선택자를 사용하여 div요소영역을 선택해 스타일을 지정하고 있습니다.
  이 div요소 영역은 다른 요소들 위에 떠있는 배경 레이아웃을 나타내며,
  z-index속성을 사용하여 다른 요소들과 겹치는 순서를 조정 하고 있습니다.
  position: absolute 송석을 사용하여 요소를 절대적인 위치에 배치 하고 있으며
  top과 left속성을 사용하여 왼쪽 상단 모서리를 기준으로 요소를 위치시키고 있습니다.
  마지막으로 width속성을 사용하여 div요소 영역의 가로 길이를 100%로 설정하고 있습니다. 
*/
#layer {
	z-index: 2;
	position: absolute;
	top: 0px;
	left: 0px;
	width: 100%;
}

/*
	#popup라는 ID선택자를 사용하여 <div id="popup">팝업창 요소 영역을 스타일링하고 있습니다.
    이 <div>요소는 position: fixed 속성을 사용하여 화면의 고정된 위치에 배치되며,
    text-align : center 속성을 사용하여 텍스트를 <div>의 가운데로 정렬하고 있습니다.
    left와 top속성을 사용하여  요소를 중앙에 배치하고 있으며,
    width와 height속성을 사용하여 <div>요소영역의 크기를 지정하고 있습니다.
    이 <div>요소의 배경색과 테두리 색상은 각각 background-color와 border속성을 사용하여 지정하고 있습니다.
*/
#popup {
	z-index: 3;
	position: fixed;
	text-align: center;
	left: 50%;
	top: 45%;
	width: 300px;
	height: 200px;
	background-color: #ccffff;
	border: 3px solid #87cb42;
}

/*
	#close라는 ID선택자를 사용하여 닫기 X 이미지 버튼을 보이는 <img>태그를 선택해 스타일링 하고 있습니다.
	이 <img>요소는 float:right 속성을 사용하여 x이미지가 우측 상단으로 배치되며 
	z-index속성을 사용하여 다른요소들보다 위에 위치하도록 조정하고 있습니다.
*/
#close {
	z-index: 4;
	float: right;
}
</style>
<script type="text/javascript">
    //장바구니 버튼을 클릭하면 호출되는 함수로~~
    //도서 상품 번호를 매개변수로 전달 받아!
    //
	function add_cart(goods_id) {
		$.ajax({
			type : "post",
			async : false, //false인 경우 동기식으로 처리한다.
			
			//Ajax를 이용해 장바구니에 추가할 상품 번호를 전송 하여 장바구니에 상품 추가 요청을 합니다. 
			url : "${contextPath}/cart/addGoodsInCart.do",
			data : { goods_id:goods_id },
			success : function(data, textStatus) {
// 						장바구니테이블에 추가할 상품이 이미 존재하면? "already_existed" 메세지를 받고
// 						존재하지 않으면? 장바구니 테이블에 새 상품을 추가INSERT하고 나서?
// 						'add_success'메세지를  위 data매개변수로 받는다.
				//alert(data);
			//	$('#message').append(data);
				if(data.trim()=='add_success'){ //장바구니 테이블에 새상품을 추가하고 메세지를 받으면?
					
						imagePopup('open', '.layer01');	
				
				}else if(data.trim()=='already_existed'){//장바구니 테이블에 
														 //사이트 이용자가 추가할 상품이 이미 저장되어 있으면?
					
					alert("이미 카트에 등록된 상품입니다.");	
				}
				
			},
			error : function(data, textStatus) {
				alert("에러가 발생했습니다."+data);
			},
			complete : function(data, textStatus) {
				//alert("작업을완료 했습니다");
			}
		}); //end ajax	
	}

	// 팝업창의 x 이미지를 감싸고 있는 <a>태그를 클랙했을때 호출되는 함수 
	function imagePopup(type) {
		
		if (type == 'open') {
			// 팝업창을 연다.
			$('#layer').attr('style', 'visibility:visible');

			// 페이지를 가리기위한 레이어 영역의 높이를 페이지 전체의 높이와 같게 한다.
			$('#layer').height(jQuery(document).height());
		}

		else if (type == 'close') {

			// 팝업창을 닫는다.
			$('#layer').attr('style', 'visibility:hidden');
		}
	}

//구매하기 버튼을 누르면 호출되는 함수로 현재보고 있는 도서상품의 번호, 제목, 정가가격, 도서이미지명을 매개변수로 받아서 처리   
function fn_order_each_goods(goods_id,goods_title,goods_sales_price,fileName){
	
	//id속성값이 isLogOn인 태그를 선택해서  _isLogOn변수에 저장
	//<input type="hidden" name="isLogOn" id="isLogOn" value="${isLogOn}"/>
	var _isLogOn=document.getElementById("isLogOn");
	
	//<input type="hidden" name="isLogOn" id="isLogOn" value="${isLogOn}"/> 태그의 value속성에 적힌 값 얻어
	//var isLogOn변수에 저장 
	var isLogOn=_isLogOn.value;  //"false" 또는 "true"
	
	 if(isLogOn == "false" || isLogOn == '' ){
		alert("로그인 후 주문이 가능합니다!!!");
	} 
	
	var total_price,final_total_price;
	//id속성값이 order_goods_qty인 <select>태그 내부의 선택한 <option> 구매수량의 정보를 가져오기 위해 <select>태그 선택해오기 
	/*
	도서 상품 구매 수량 선택하는 태그를 선택함 
	     <select style="width: 60px;" id="order_goods_qty">
    		<option value="1">1</option>
			<option value="2">2</option>
			<option>3</option>
			<option>4</option>
			<option>5</option>
  		</select>
	 */	
	var order_goods_qty=document.getElementById("order_goods_qty");
	
   	//<form></form>
	var formObj=document.createElement("form");
	
	//<input>
	var i_goods_id = document.createElement("input"); 
	 //<input name="goods_id">
    i_goods_id.name="goods_id";
    //<input name="goods_id" value="매개변수 goods_id로 전달 받는 상품번호">
    i_goods_id.value=goods_id;
    
    
	//<input>
    var i_goods_title = document.createElement("input");
	//<input name="goods_title">
    i_goods_title.name="goods_title";
    //<input name="goods_title" vlaue="매개변수 goods_title로 전달받는 도서상품제목">
    i_goods_title.value=goods_title;
    
   
    //<input>
    var i_goods_sales_price=document.createElement("input");
	//<input name="goods_sales_price">
    i_goods_sales_price.name="goods_sales_price";
    //<input name="goods_sales_price" value="매개변수 goods_sales_price로 전달받는 도서상품판매가격">
    i_goods_sales_price.value=goods_sales_price;
    
    
    
   	//<input>
    var i_fileName=document.createElement("input");
	//<input name="goods_fileName">
    i_fileName.name="goods_fileName";
	//<input name="goods_fileName" value="매개변수 fileName으로 전달받는 도서상품 이미지파일명">
    i_fileName.value=fileName;
    
    
    //<input>
    var i_order_goods_qty=document.createElement("input");
  	//<input name="order_goods_qty">
    i_order_goods_qty.name="order_goods_qty";
  	//<input name="order_goods_qty" value="select option태그에서 선택한 구매수량을 설정"> 
    i_order_goods_qty.value = order_goods_qty.value;
  
    
    formObj.appendChild(i_goods_id);
    formObj.appendChild(i_goods_title);
    formObj.appendChild(i_goods_sales_price);
    formObj.appendChild(i_fileName);
    formObj.appendChild(i_order_goods_qty);


    document.body.appendChild(formObj); 
    
    formObj.method="post";
    formObj.action="${contextPath}/order/orderEachGoods.do";
    /*
    <body>
	  	<form action="${contextPath}/order/orderEachGoods.do" method="post">
	  		<input name="goods_id" value="매개변수 goods_id로 전달 받는 상품번호">
	  		<input name="goods_title" vlaue="매개변수 goods_title로 전달받는 도서상품제목">
	  		<input name="goods_sales_price" value="매개변수 goods_sales_price로 전달받는 도서상품판매가격">
	  		<input name="goods_fileName" value="매개변수 fileName으로 전달받는 도서상품 이미지파일명">
	  		<input name="order_goods_qty" value="select option태그에서 선택한 구매수량을 설정">
	  	</form>  
	 </body>  
*/      
    formObj.submit();//구매 요청!
	}	
</script>
</head>
<body>
	<hgroup>
		<h1>컴퓨터와 인터넷</h1>
		<h2>국내외 도서 &gt; 컴퓨터와 인터넷 &gt; 웹 개발</h2>
		<h3>${goods.goods_title }</h3>
		<h4>${goods.goods_writer} &nbsp; 저| ${goods.goods_publisher}</h4>
	</hgroup>
	<div id="goods_image">
		<figure>
			<img alt="HTML5 &amp; CSS3"
				src="${contextPath}/thumbnails.do?goods_id=${goods.goods_id}&fileName=${goods.goods_fileName}">
		</figure>
	</div>
	<div id="detail_table">
		<table>
			<tbody>
				<tr>
					<td class="fixed">정가</td>
					<td class="active"><span >
					   <fmt:formatNumber  value="${goods.goods_price}" type="number" var="goods_price" />
				         ${goods_price}원
					</span></td>
				</tr>
				<tr class="dot_line">
					<td class="fixed">판매가</td>
					<td class="active"><span >
					   <fmt:formatNumber  value="${goods.goods_price*0.9}" type="number" var="discounted_price" />
				         ${discounted_price}원(10%할인)</span></td>
				</tr>
				<tr>
					<td class="fixed">포인트적립</td>
					<td class="active">${goods.goods_point}P(10%적립)</td>
				</tr>
				<tr class="dot_line">
					<td class="fixed">포인트 추가적립</td>
					<td class="fixed">만원이상 구매시 1,000P, 5만원이상 구매시 2,000P추가적립 편의점 배송 이용시 300P 추가적립</td>
				</tr>
				<tr>
					<td class="fixed">발행일</td>
					<td class="fixed">
					   <c:set var="pub_date" value="${goods.goods_published_date}" />
					 <%-- 
					   		바로 아래줄의 코드는  JSTL의 함수인 fn:split함수를 사용하여 
					   		문자열 "2018-10-02"를 공백 문자를 기준으로 분리(split)한 결과를 반환하는 코드입니다.
					   		실행결과는 [2018-10-02]배열과 같습니다.
					   					 0      index
					   		결과는 문자열 "2018-10-02"가 하나의 요소로 구성된 배열로 반환됩니다. 이는 공백문자가 없기때문입니다.
					   		만약 문자열에 공백문자가 있다면, 해당 문자열은 공백 문자를 기준으로 분리되어 새로운 배열에 담아 배열자체를 반환 해 줍니다.
					   		예를 들어 "2023 - 10 - 11 "  -> ["2023", "-", "10", "-", "11"] 분리된 문자열들이 새로운 배열에 저장되 배열을 반환해 줍니다.		   
					   --%>
					   <c:set var="arr" value="${fn:split(pub_date,' ')}" />
					   <c:out value="${arr[0]}" />
					</td>
				</tr>
				<tr>
					<td class="fixed">페이지 수</td>
					<td class="fixed">${goods.goods_total_page}쪽</td>
				</tr>
				<tr class="dot_line">
					<td class="fixed">ISBN</td>
					<td class="fixed">${goods.goods_isbn}</td>
				</tr>
				<tr>
					<td class="fixed">배송료</td>
					<td class="fixed"><strong>무료</strong></td>
				</tr>
				<tr>
					<td class="fixed">배송안내</td>
					<td class="fixed">
						<strong>[당일배송]</strong> 당일배송 서비스 시작!<br> 
						<strong>[휴일배송]</strong>
						휴일에도 배송받는 Bookshop
					</td>
				</tr>
				<tr>
					<td class="fixed">도착예정일</td>
					<td class="fixed">지금 주문 시 내일 도착 예정</td>
				</tr>
				<tr>
					<td class="fixed">수량</td>
					<td class="fixed">
					      <select style="width: 60px;" id="order_goods_qty">
				      		<option>1</option>
							<option>2</option>
							<option>3</option>
							<option>4</option>
							<option>5</option>
					     </select>
					 </td>
				</tr>
			</tbody>
		</table>
		<ul>
			<li><a class="buy" href="javascript:fn_order_each_goods('${goods.goods_id }','${goods.goods_title }','${goods.goods_sales_price}','${goods.goods_fileName}');">구매하기 </a></li>
			<li><a class="cart" href="javascript:add_cart('${goods.goods_id }')">장바구니</a></li>
			
			<li><a class="wish" href="#">위시리스트</a></li>
		</ul>
	</div>
	<div class="clear"></div>
	<!-- 내용 들어 가는 곳 -->
	<div id="container">
		<ul class="tabs">
			<li><a href="#tab1">책소개</a></li>
			<li><a href="#tab2">저자소개</a></li>
			<li><a href="#tab3">책목차</a></li>
			<li><a href="#tab4">출판사서평</a></li>
			<li><a href="#tab5">추천사</a></li>
			<li><a href="#tab6">리뷰</a></li>
		</ul>
		<div class="tab_container">
			<div class="tab_content" id="tab1">
				<h4>책소개</h4>
				<p>${fn:replace(goods.goods_intro,crcn,br)}</p>
				<c:forEach var="image" items="${imageList }">
					<img src="${contextPath}/download.do?goods_id=${goods.goods_id}&fileName=${image.fileName}">
				</c:forEach>
			</div>
			<div class="tab_content" id="tab2">
				<h4>저자소개</h4>
				<p>
				<div class="writer">저자 : ${goods.goods_writer}</div>
				
				 <%-- fn:relpace함수를 이용해 입력했던 저자 소개에 포함된 개행문자 "\r\n"을 <br>대체 후 반환 받아 웹브라우저 화면에 출력합니다. --%>
				 <p>${fn:replace(goods.goods_writer_intro,crcn,br) }</p> 
				
			</div>
			<div class="tab_content" id="tab3">
				<h4>책목차</h4>
				<%-- replace함수를 이용해 상품 목차에 포함된 crcn( "\r\n")을 br(<br>태그)로 대체합니다. --%>
				<p>${fn:replace(goods.goods_contents_order,crcn,br)}</p> 
			</div>
			<div class="tab_content" id="tab4">
				<h4>출판사서평</h4>
				<%-- replace함수를 이용해 출판사 서평 내용에 포함된 crcn( "\r\n")을 br(<br>태그)로 대체합니다. --%>
				 <p>${fn:replace(goods.goods_publisher_comment ,crcn,br)}</p> 
			</div>
			<div class="tab_content" id="tab5">
				<h4>추천사</h4>
				<%-- replace함수를 이용해 추천사 내용에 포함된 crcn( "\r\n")을 br(<br>태그)로 대체합니다. --%>
				<p>${fn:replace(goods.goods_recommendation,crcn,br) }</p>
			</div>
			<div class="tab_content" id="tab6">
				<h4>리뷰</h4>
			</div>
		</div>
	</div>
	<div class="clear"></div>
	<div id="layer" style="visibility: hidden">
		<!-- visibility:hidden 으로 설정하여 해당 div안의 모든것들을 가려둔다. -->
		<div id="popup">
			<!-- 팝업창 닫기  X 이미지 버튼 -->
			<a href="javascript:" onClick="javascript:imagePopup('close', '.layer01');"> 
				<img src="${contextPath}/resources/image/close.png" id="close" />
			</a> 
			<br /> 
			<font size="12" id="contents">장바구니에 담았습니다.</font>
			<br>
			<%-- 장바구니 테이블에 저장된 상품목록 조회 요청! --%>
			<form   action='${contextPath}/cart/myCartList.do'  >				
					<input  type="submit" value="장바구니 보기">
			</form>			
		</div>
	</div>		
</body>
</html>
<input type="hidden" name="isLogOn" id="isLogOn" value="${isLogOn}"/>


