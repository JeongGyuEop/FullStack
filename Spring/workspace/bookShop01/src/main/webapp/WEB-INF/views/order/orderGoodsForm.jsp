<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"
	 isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<c:set var="contextPath"  value="${pageContext.request.contextPath}"  />
<!-- 주문자 휴대폰 번호 -->
<c:set  var="orderer_hp" value=""/>
<!-- 최종 결제 금액 -->
<c:set var="final_total_order_price" value="0" />

<!-- 총주문 금액 -->
<c:set var="total_order_price" value="0" />
<!-- 총 상품수 -->
<c:set var="total_order_goods_qty" value="0" />
<!-- 총할인금액 -->
<c:set var="total_discount_price" value="0" />
<!-- 총 배송비 -->
<c:set var="total_delivery_price" value="0" />

<head>
<style>
/* 
#layer라는 ID를 가진 div요소(팝업창을 감싸고 있는 요소)에 대한 스타일을 정의하고 있습니다. 
z-index 속성은 요소의 쌓이는 순서를 지정합니다. 
position 속성은 요소의 위치 지정 방법을 나타내며, 
absolute는 부모 요소를 기준으로 상대적인 위치가 아니라 문서 전체를 기준으로 위치가 지정된다는 것을 의미합니다. 
top, left, width 속성은 요소의 위치와 크기를 조절하는 속성입니다. 
마지막으로 주석 처리된 background-color 속성은 요소의 배경색을 지정하는 속성입니다.
 */
#layer {
	z-index: 2;
	position: absolute;
	top: 0px;
	left: 0px;
	width: 100%;
/* 	background-color:rgba(0,0,0,0.8);  */
}

/* 
#popup_order_detail라는 ID를 가진 div요소(팝업창영역)에 대한 스타일을 정의하고 있습니다. 
position 속성은 fixed로 설정되어 있어 스크롤에 따라 요소가 움직이지 않습니다. 
text-align 속성은 요소 내부의 텍스트를 정렬하는 속성입니다. 
left, top, width, height 속성은 요소의 위치와 크기를 조절하는 속성입니다. 
background-color 속성은 요소의 배경색을 지정합니다. 
border 속성은 요소의 테두리 스타일을 지정하는 속성입니다.

 */
#popup_order_detail {
	z-index: 3;
	position: fixed;
 	text-align: center; 
	left: 10%;
	top: 0%;
	width: 60%;
	height: 100%;
	background-color:#ccff99; /* 팝업창의 배경색 설정*/
	border: 2px solid  #0000ff;
}



/* 
#close라는 ID를 가진 <img>요소에 대한 스타일을 정의하고 있습니다. 

<!-- 팝업창 닫기  X 버튼 -->
<a href="javascript:" onClick="javascript:imagePopup('close', '.layer01');">
 <img  src="${contextPath}/resources/image/close.png" id="close" />
</a> 
			
이 요소는 팝업 창 내부에 위치한 닫기  X버튼을 나타냅니다. 
float 속성은 요소를 왼쪽이나 오른쪽으로 띄워서 텍스트와 함께 표시할 때 사용하는 속성입니다. 
이 경우 right로 설정되어 있어 닫기 버튼이 팝업 창 오른쪽에 띄워지게 됩니다.
 */
 
 #close {
	
/* 	z-index 속성은 요소의 쌓이는 순서를 지정하는 속성으로, 값이 클수록 다른 요소보다 위에 위치하게 됩니다. 
	코드에서 #close라는 ID를 가진 요소에 대해 z-index 속성이 4로 설정되어 있으므로, 이 요소는 다른 모든 요소보다 위에 위치하게 됩니다.
	이는 팝업 창 내부에 위치한 닫기 버튼이 사용자와 상호작용하는데 있어서 중요한 역할을 하기 때문입니다. 
	사용자는 팝업 창을 닫기 위해 이 버튼을 클릭하게 되는데, 만약 다른 요소들이 닫기 버튼을 가리게 된다면 사용자는 버튼을 찾기 어렵게 됩니다. 
	따라서 이를 방지하기 위해 닫기 버튼을 다른 요소들보다 상위에 위치시켜 사용자의 편의성을 높이고 있습니다.
 */	z-index: 4;
	
	
	float: right;
}
</style>

<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<script>
    function execDaumPostcode() {
        new daum.Postcode({
            oncomplete: function(data) {
                // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

                // 도로명 주소의 노출 규칙에 따라 주소를 조합한다.
                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
                var fullRoadAddr = data.roadAddress; // 도로명 주소 변수
                var extraRoadAddr = ''; // 도로명 조합형 주소 변수

                // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                    extraRoadAddr += data.bname;
                }
                // 건물명이 있고, 공동주택일 경우 추가한다.
                if(data.buildingName !== '' && data.apartment === 'Y'){
                   extraRoadAddr += (extraRoadAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                }
                // 도로명, 지번 조합형 주소가 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                if(extraRoadAddr !== ''){
                    extraRoadAddr = ' (' + extraRoadAddr + ')';
                }
                // 도로명, 지번 주소의 유무에 따라 해당 조합형 주소를 추가한다.
                if(fullRoadAddr !== ''){
                    fullRoadAddr += extraRoadAddr;
                }

                // 우편번호와 주소 정보를 해당 필드에 넣는다.
                document.getElementById('zipcode').value = data.zonecode; //5자리 새우편번호 사용
                document.getElementById('roadAddress').value = fullRoadAddr;
                document.getElementById('jibunAddress').value = data.jibunAddress;

                // 사용자가 '선택 안함'을 클릭한 경우, 예상 주소라는 표시를 해준다.
                if(data.autoRoadAddress) {
                    //예상되는 도로명 주소에 조합형 주소를 추가한다.
                    var expRoadAddr = data.autoRoadAddress + extraRoadAddr;
                    document.getElementById('guide').innerHTML = '(예상 도로명 주소 : ' + expRoadAddr + ')';

                } else if(data.autoJibunAddress) {
                    var expJibunAddr = data.autoJibunAddress;
                    document.getElementById('guide').innerHTML = '(예상 지번 주소 : ' + expJibunAddr + ')';

                } else {
                    document.getElementById('guide').innerHTML = '';
                }
            }
        }).open();
    }
    
    
// 상품을 주문할수 있는  orderGoodsForm.jsp페이지 입니다.




//상품 주문 페이지가 표시되면  주문자의 휴대폰번호와 유선전화 번호를 셀렉트 박스에 표시합니다. 
//2. 배송지 정보 화면을 보자.
  window.onload=function()
  {
    init();
  }
  function init(){
	//<form  name="form_order"> ...... </form>
  	var form_order=document.form_order;

	//로그인 한 주문자의 유선전화번호 중 지역번호를 option태그가 선택되도록 설정 하는 부분  	
	  	//<input type="hidden" id="h_tel1" name="h_tel1" value="${sessionScope.orderer.tel1 }" />
	  	var h_tel1=form_order.h_tel1;
	 	// 로그인한 회원(상품 주문하는 사람) 유선 전화번호 051 , 02  등등 중에 하나 얻기 
	 	var tel1=h_tel1.value; 
	 	// <select id="tel1" name="tel1">....</select>  유선전화번호     
	 	var select_tel1=form_order.tel1;
	 	// <select id="tel1" name="tel1">....</select>  유선전화번호  선택하는 디자인의
	 	// <option value="02">02</option>
		// 	<option value="031">031</option>
		// 	<option value="032">032</option>
		// 	<option value="033">033</option>
		// 	<option value="041">041</option>
		// 	<option value="042">042</option>
		// 	<option value="043">043</option>
		// 	<option value="044">044</option>
		// 	<option value="051">051</option>
		//태그들 중에 하나가  로그인한 회원(상품 주문하는 사람)의 주문자 정보로 선택되도록 값 설정
	  	select_tel1.value=tel1; //유선전화 번호 중  지역번호  051 , 02, 031 등등  ... 중에 하나 로 설정됨  (DB의 테이블에 저장되어 있는 값과 동일)
	  
	//로그인 한 주문자의 휴대폰번호 중  앞 010, 011, 017, 018 중  option태그가 선택되도록 설정 하는 부분     (DB의 테이블에 저장되어 있는 값과 동일)
	  	var h_hp1=form_order.h_hp1;
		var hp1=h_hp1.value; 
	  	var select_hp1=form_order.hp1;
	  	select_hp1.value=hp1; 
  	
  }    
//-----------------------------------------------------------------------    
  
  
  
  //2. 배송지 정보 화면을 보자.
  /* 아래의 디자인에서  
      <input type="radio" name="delivery_place" value="새로입력" onClick="reset_all()">새로입력  &nbsp;&nbsp;&nbsp;
           라디오 버튼을 클릭하면 호출되는 함수로  기본배송지로 선택된 정보들을 모두 없애 주는 기능을 합니다.
  
  <tr class="dot_line">
	<td class="fixed_join">배송지 선택</td>
	<td><input type="radio" name="delivery_place"
		onClick="restore_all()" value="기본배송지" checked>기본배송지 &nbsp;&nbsp;&nbsp; 
		<input type="radio" name="delivery_place" value="새로입력" onClick="reset_all()">새로입력 &nbsp;&nbsp;&nbsp;
		<input type="radio" name="delivery_place" value="최근배송지">최근배송지 &nbsp;&nbsp;&nbsp;
    </td>
  </tr>
  */			
	function reset_all() {
		var e_receiver_name = document.getElementById("receiver_name");
		var e_hp1 = document.getElementById("hp1");
		var e_hp2 = document.getElementById("hp2");
		var e_hp3 = document.getElementById("hp3");

		var e_tel1 = document.getElementById("tel1");
		var e_tel2 = document.getElementById("tel2");
		var e_tel3 = document.getElementById("tel3");

		var e_zipcode = document.getElementById("zipcode");
		var e_roadAddress = document.getElementById("roadAddress");
		var e_jibunAddress = document.getElementById("jibunAddress");
		var e_namujiAddress = document.getElementById("namujiAddress");

		e_receiver_name.value = "";
		e_hp1.value = 0;
		e_hp2.value = "";
		e_hp3.value = "";
		e_tel1.value = "";
		e_tel2.value = "";
		e_tel3.value = "";
		e_zipcode.value = "";
		e_roadAddress.value = "";
		e_jibunAddress.value = "";
		e_namujiAddress.value = "";
	}

  
//2. 배송지 정보 화면을 보자.  
/* 아래의 디자인에서  
  <input type="radio" name="delivery_place" onClick="restore_all()" value="기본배송지" checked>기본배송지 &nbsp;&nbsp;&nbsp;
     라디오 버튼을 클릭하면 호출되는 함수로   기본배송지로 선택된 정보들을  로그인한 구매자의 정보들로 설정함

<tr class="dot_line">
<td class="fixed_join">배송지 선택</td>
<td><input type="radio" name="delivery_place" onClick="restore_all()" value="기본배송지" checked>기본배송지 &nbsp;&nbsp;&nbsp; 
	<input type="radio" name="delivery_place" value="새로입력" onClick="reset_all()">새로입력 &nbsp;&nbsp;&nbsp;
	<input type="radio" name="delivery_place" value="최근배송지">최근배송지 &nbsp;&nbsp;&nbsp;
</td>
</tr>
*/			
	function restore_all() {
		var e_receiver_name = document.getElementById("receiver_name");
		var e_hp1 = document.getElementById("hp1");
		var e_hp2 = document.getElementById("hp2");
		var e_hp3 = document.getElementById("hp3");

		var e_tel1 = document.getElementById("tel1");
		var e_tel2 = document.getElementById("tel2");
		var e_tel3 = document.getElementById("tel3");

		var e_zipcode = document.getElementById("zipcode");
		var e_roadAddress = document.getElementById("roadAddress");
		var e_jibunAddress = document.getElementById("jibunAddress");
		var e_namujiAddress = document.getElementById("namujiAddress");

		var h_receiver_name = document.getElementById("h_receiver_name");
		var h_hp1 = document.getElementById("h_hp1");
		var h_hp2 = document.getElementById("h_hp2");
		var h_hp3 = document.getElementById("h_hp3");

		var h_tel1 = document.getElementById("h_tel1");
		var h_tel2 = document.getElementById("h_tel2");
		var h_tel3 = document.getElementById("h_tel3");

		var h_zipcode = document.getElementById("h_zipcode");
		var h_roadAddress = document.getElementById("h_roadAddress");
		var h_jibunAddress = document.getElementById("h_jibunAddress");
		var h_namujiAddress = document.getElementById("h_namujiAddress");
		//alert(e_receiver_name.value);
		e_receiver_name.value = h_receiver_name.value;
		e_hp1.value = h_hp1.value;
		e_hp2.value = h_hp2.value;
		e_hp3.value = h_hp3.value;

		e_tel1.value = h_tel1.value;
		e_tel2.value = h_tel2.value;
		e_tel3.value = h_tel3.value;
		e_zipcode.value = h_zipcode.value;
		e_roadAddress.value = h_roadAddress.value;
		e_jibunAddress.value = h_jibunAddress.value;
		e_namujiAddress.value = h_namujiAddress.value;

	}
	
	
// <h1>4.결제정보</h1> 화면을 보자.
/*
 아래의 디자인 화면 중에서  [휴대폰 결제] 라디오 버튼을 클릭했을때 호출되는 함수로  
휴대폰 결제 라디오 버튼을 클릭해서 선택했을때의  휴대폰번호 입력 하는 디자인<tr></tr>영역은 보이게 하고 
신용카드  라디오 버튼을 클릭해서 선택했을때의  결제 카드를 선택하고 할부 기간은 선택하는  디자인은  <tr></tr>영역 숨기게 함.

 참고.처음에는 강제로 checked속성이 설정 되어 있어 신용카드 결제가 라디오 버튼이 선택되어 있다.
 
 <input type="radio" id="pay_method" name="pay_method" value="휴대폰결제" onClick="fn_pay_phone()">휴대폰 결제 &nbsp;&nbsp;&nbsp;
 <input type="radio" id="pay_method" name="pay_method" value="카카오페이(간편결제)">카카오페이(간편결제) &nbsp;&nbsp;&nbsp; 
 <input type="radio" id="pay_method" name="pay_method" value="페이나우(간편결제)">페이나우(간편결제) &nbsp;&nbsp;&nbsp; 
 <input type="radio" id="pay_method" name="pay_method" value="페이코(간편결제)">페이코(간편결제) &nbsp;&nbsp;&nbsp;
*/					   

function fn_pay_phone(){
/*	
	<tr id="tr_pay_card">
		<td>
		  <strong>카드 선택<strong>:&nbsp;&nbsp;&nbsp;
		  <select id="card_com_name" name="card_com_name">
				<option value="삼성" selected>삼성</option>
				<option value="하나SK">하나SK</option>
				<option value="현대">현대</option>
				<option value="KB">KB</option>
				<option value="신한">신한</option>
				<option value="롯데">롯데</option>
				<option value="BC">BC</option>
				<option value="시티">시티</option>
				<option value="NH농협">NH농협</option>
		</select>
		<br><Br>
		<strong>할부 기간:<strong>  &nbsp;&nbsp;&nbsp;
		<select id="card_pay_month" name="card_pay_month">
				<option value="일시불" selected>일시불</option>
				<option value="2개월">2개월</option>
				<option value="3개월">3개월</option>
				<option value="4개월">4개월</option>
				<option value="5개월">5개월</option>
				<option value="6개월">6개월</option>
		</select>
		
		</td>
	</tr>
	
	 위 디자인은  신용카드  라디오 버튼을 클릭해서 선택했을때 결제 카드를 선택하고 할부 기간은 선택하는  디자인 입니다.
*/	
		var e_card=document.getElementById("tr_pay_card");
	
/* 	
	<tr id="tr_pay_phone" style="visibility:hidden">
		<td>
		<strong>휴대폰 번호 입력: <strong>
			       <input  type="text" size="5" value=""  id="pay_order_tel1" name="pay_order_tel1" />-
		         <input  type="text" size="5" value="" id="pay_order_tel2" name="pay_order_tel2" />-
		         <input  type="text" size="5" value="" id="pay_order_tel3" name="pay_order_tel3" />
		</td>
	</tr>
    위 디자인은  휴대폰 결제 라디오 버튼을 클릭해서 선택했을때 휴대폰번호 입력 하는 디자인 입니다.
 */	
	 	var e_phone=document.getElementById("tr_pay_phone");
    
		// 	신용카드  라디오 버튼을 클릭해서 선택했을때 결제 카드를 선택하고 할부 기간은 선택하는  디자인 <tr></tr>영역 숨김
		e_card.style.visibility="hidden";
		
		//  휴대폰 결제 라디오 버튼을 클릭해서 선택했을때 휴대폰번호 입력 하는 <tr></tr> 디자인 보이게
		e_phone.style.visibility="visible";
}


//<h1>4.결제정보</h1> 화면을 보자.
/*
 아래의 디자인 화면 중에서  신용카드 라디오 버튼을 클릭했을때 호출되는 함수로  
휴대폰 결제 라디오 버튼을 클릭해서 선택했을때의  휴대폰번호 입력 하는 디자인<tr></tr>영역은 숨김고
신용카드  라디오 버튼을 클릭해서 선택했을때 결제 카드를 선택하고 할부 기간은 선택하는  디자인은  <tr></tr>영역 보이게 함.

 참고.처음에는 강제로 checked속성이 설정 되어 있어 신용카드 결제가 라디오 버튼이 선택되어 있다.
 
 <input type="radio" id="pay_method" name="pay_method" value="신용카드"   onClick="fn_pay_card()" checked>신용카드 &nbsp;&nbsp;&nbsp; 
 <input type="radio" id="pay_method" name="pay_method" value="제휴 신용카드"  >제휴 신용카드 &nbsp;&nbsp;&nbsp; 
 <input type="radio" id="pay_method" name="pay_method" value="실시간 계좌이체">실시간 계좌이체 &nbsp;&nbsp;&nbsp;
 <input type="radio" id="pay_method" name="pay_method" value="무통장 입금">무통장 입금 &nbsp;&nbsp;&nbsp;
*/	
function fn_pay_card(){
	var e_card=document.getElementById("tr_pay_card");
	var e_phone=document.getElementById("tr_pay_phone");
	e_card.style.visibility="visible";
	e_phone.style.visibility="hidden";
}




function imagePopup(type) {
	if (type == 'open') {
		// 팝업창을 연다.
		jQuery('#layer').attr('style', 'visibility:visible');

		// 페이지를 가리기위한 레이어 영역의 높이를 페이지 전체의 높이와 같게 한다.
		jQuery('#layer').height(jQuery(document).height());
	}

	else if (type == 'close') {

		// 팝업창을 닫는다.
		jQuery('#layer').attr('style', 'visibility:hidden');
	}
}

var goods_id=""; //구매 상품 번호들이 저장되는 변수  (장바구니에 추가 후 구매하는 상품이 아니라면 구매 상품 번호는 하나만 저장될 수 있음)
var goods_title="";//구매 상품 제목들이 저장되는 변수 (장바구니에 추가 후 구매하는 상품이 아니라면 구매 상품 제목은 하나만 저장될 수 있음)
var goods_fileName="";//구매 도서상품 이미지명 들이 저장되는 변수 (장바구니에 추가 후 구매하는 상품이 아니라면 구매 상품 이미지명은 하나만 저장될 수 있음)

var order_goods_qty //구매 상품 수량
var each_goods_price;
var total_order_goods_price;
var total_order_goods_qty;

var orderer_name  //로그인된 구매자 이름(받으실분 이름)
var receiver_name //로그인된 구매자 이름(받으실분 이름)

//로그인된 구매자의 휴대폰 번호
var hp1;  // 010
var hp2;  // 1234
var hp3;  // 5678

//로그인된 구매자의 유선전화 번호
var tel1;  // 051
var tel2;  // 1234
var tel3;  // 5678

//로그인된 구매자의 휴대폰 번호를 완성 해서  receiver_hp_num변수에 저장
var receiver_hp_num; // 예)  010-1234-5678

//로그인된 구매자의 유선전화 번호를 완성 해서  receiver_tel_num변수에 저장
var receiver_tel_num; // 예)  051-1234-5678

//주소 디자인 영역에 보여줄  우편번호 +  도로명 주소 + 지번주소를 합친 문자열을   delivery_address변수에 저장
var delivery_address; 
// 예)  "우편번호:13547
// 	        도로명 주소:경기 성남시 분당구 동원동 79-1
// 		[지번 주소:경기 성남시 분당구 고기로 25 (동원동)]
// 		럭키빌딩 101호"
 		
//주소 디자인 영역에 보여지는 배송 메세지를 얻어 delivery_message변수에 저장
var delivery_message; //예)  "택배 기사님 문앞에 두세요."


//for문을 이용하여, 배송 방법(일반택배,편의점택배,해외배송) 중 선택한 하나의 <input type="radio"> 항목을 찾아 
//해당 값을 delivery_method 변수에 저장합니다.
var delivery_method;

//for문을 이용하여, 선물 포장 여부(yes 예 또는 no 아니오) 중 
//선택한 하나의 <input type="radio"> 항목을 찾아 
//해당 값 (선물 포장 여부 yes 또는 no)을 gift_wrapping 변수에 저장합니다.
var gift_wrapping;


/*
결제정보에서  
신용카드, 제휴 신용카드, 실시간 계좌이체, 무통장입금, 
휴대폰결제, 카카오페이,페이나우,
직접입금  라디오 버튼 중에서 클릭해서 선택한 결제 정보가 저장되는 변수 
*/
var pay_method;

/*
 결제정보에서 신용카드를 선택했을 경우 
 카드사 이름을 저장할 변수 
 */
var card_com_name;

/* 결제정보에서 신용카드를 선택했을 경우 할부 기간 저장할 변수   */
var card_pay_month;

/* 결제 정보에서 휴대폰 결제를 선택했을 경우 
 	구매자가 입력한 휴대폰 번호를 받아 저장할 변수 
 */
var pay_orderer_hp_num;



/* 
<a href="javascript:fn_show_order_detail();"> 
   <img width="125" alt="" src="${contextPath}/resources/image/btn_gulje.jpg"> 
</a> 

위  결제하기 이미지를 클릭하면 호출되는 함수 
 */
function fn_show_order_detail(){
	
	goods_id=""; //구매 상품 번호들이 저장되는 변수  (장바구니에 추가 후 구매하는 상품이 아니라면 구매 상품 번호는 하나만 저장될 수 있음)
	goods_title=""; //구매 상품 제목들이 저장되는 변수 (장바구니에 추가 후 구매하는 상품이 아니라면 구매 상품 제목은 하나만 저장될 수 있음)
	
	//<form  name="form_order"> ...... </form>
	var frm=document.form_order;

	var h_goods_id=frm.h_goods_id;	//주문 한 상품 번호가 설정 된 <input   type="hidden">
	
	var h_goods_title=frm.h_goods_title; //주문 한 상품 명이 설정 된 <input   type="hidden">
	
	var h_goods_fileName=frm.h_goods_fileName; //주문 한 상품 이미지명이 설정 된 <input   type="hidden">
/*
<form name="form_order">
....
	<tr class="dot_line">
		<td class="fixed_join">배송방법</td>
		<td>
		    <input type="radio" id="delivery_method" name="delivery_method" value="일반택배" checked>일반택배 &nbsp;&nbsp;&nbsp; 
			<input type="radio" id="delivery_method" name="delivery_method" value="편의점택배">편의점택배 &nbsp;&nbsp;&nbsp; 
			<input type="radio" id="delivery_method" name="delivery_method" value="해외배송">해외배송 &nbsp;&nbsp;&nbsp;
	    </td>
	</tr>
	...
</form>

	위 배송 방법(일반택배,편의점택배,해외배송) 중 선택한 하나의 <input type="radio">만 저장 됩니다. 
*/	
	var r_delivery_method  =  frm.delivery_method;
	
	//주문 한 상품 수량이 설정된  <input   type="hidden">
	var h_order_goods_qty=document.getElementById("h_order_goods_qty");
	
	//구매할 총 상품 수량이 설정된  <input id="h_total_order_goods_qty" type="hidden" value="${total_order_goods_qty}" />
	var h_total_order_goods_qty=document.getElementById("h_total_order_goods_qty");
	
	//총할인금액이 설정된  <input id="h_total_sales_price" type="hidden" value="${total_discount_price}" />
	var h_total_sales_price=document.getElementById("h_total_sales_price");
	
	//최종 결제 금액이 설정된  <input id="h_final_total_Price" type="hidden"value="${final_total_order_price}" />
	var h_final_total_Price=document.getElementById("h_final_total_Price");
	
	//로그인된 구매자(받으실분) 이름이 설정된 <input type="hidden" id="h_orderer_name" name="h_orderer_name"  value="${orderer.member_name }" /> 
	var h_orderer_name=document.getElementById("h_orderer_name");
	
	//로그인된 구매자(받으실분) 이름이 설정된 
	//<input id="receiver_name" name="receiver_name" type="text" size="40" value="${orderer.member_name }" />
	var i_receiver_name=document.getElementById("receiver_name");
	
	// h_goods_id 변수의 길이가 2보다 작거나 null인 경우, 
	//즉 주문할 상품의 개수가 1개인 경우, 주문 할 상품 번호를 goods_id 변수에 추가합니다.
	if(h_goods_id.length <2 ||h_goods_id.length==null){
				
		goods_id+=h_goods_id.value;
	
	//h_goods_id 변수의 길이가 2 이상인 경우, 
	//즉 주문할 상품의 개수가 2개 이상인 경우, 	
	}else{
		
		//주문할 상품들의 번호를 goods_id 변수에 추가합니다. 
		//이때 for 문을 이용해 각각의 주문상품의 번호 값을 차례로 goods_id 변수에 추가합니다.	
		for(var i=0; i<h_goods_id.length;i++){
			goods_id+=h_goods_id[i].value+"<br>";
		}	
	}
	
/* 	
	상품의 제목을 h_goods_title 변수에서 가져오는 부분으로, h_goods_id와 유사한 방식으로 가져옵니다.

	먼저 h_goods_title의 값이 null이거나 길이가 2보다 작을 경우에는 단일 상품이므로 
	h_goods_title.value에서 값을 가져와 goods_title 변수에 저장합니다.

	하지만 h_goods_title의 값이 배열일 경우에는 다수의 상품이므로
	for 반복문을 사용하여 h_goods_title[i].value에서 값을 가져와 goods_title 변수에 추가합니다.

	최종적으로 goods_title 변수에는 선택한 상품의 제목이 저장됩니다.	
 */
 	if(h_goods_title.length <2 ||h_goods_title.length==null){
		goods_title+=h_goods_title.value;
	}else{
		for(var i=0; i<h_goods_title.length;i++){
			goods_title+=h_goods_title[i].value+"<br>";
			
		}	
	}
	
/* 	
 	 주문한 상품 이미지명이 설정된 input 태그인 h_goods_fileName에서 값을 가져와서, 
 	 그 값이 하나인 경우는 goods_fileName 변수에 그 값을 할당하고,
 	 
 	 값이 여러개인 경우는 for문을 통해 해당 값을 모두 합쳐서 줄바꿈 태그(br)와 함께
 	 goods_fileName 변수에 할당하는 코드입니다.	
*/
	if(h_goods_fileName.length <2 ||h_goods_fileName.length==null){
		//만약 h_goods_fileName의 값이 하나라면, 조건문에서 h_goods_fileName.value가 존재하는지 확인한 후, 
		//그 값을 goods_fileName 변수에 할당합니다. 
		//이때 값이 존재하지 않으면, 할당되는 값도 null일 것입니다.
		goods_fileName+=h_goods_fileName.value;
	}else{
		
// 		만약 h_goods_fileName의 값이 여러개라면, 
// 		for문에서 h_goods_fileName.length의 길이를 확인하여 for문을 실행하게 됩니다. 
// 		for문 안에서는 h_goods_fileName[i].value의 값을 goods_fileName 변수에 할당하고, 
// 		그 값과 함께 줄바꿈 태그(br)를 합쳐서 다시 goods_fileName 변수에 할당합니다.
		for(var i=0; i<h_goods_fileName.length;i++){
			goods_fileName+=h_goods_fileName[i].value+"<br>";
			
		}	
	}
	
	//최종 결제 금액을 total_order_goods_price 변수에 저장합니다.
	total_order_goods_price=h_final_total_Price.value;
	
	//구매할 총 상품 수량을 total_order_goods_qty 변수에 저장합니다. 
	total_order_goods_qty=h_total_order_goods_qty.value;
	
	
	//for문을 이용하여, 배송 방법(일반택배,편의점택배,해외배송) 중 선택한 하나의 <input type="radio"> 항목을 찾아 
	//해당 값을 delivery_method 변수에 저장합니다.
	for(var i=0; i<r_delivery_method.length;i++){
		
	  if(r_delivery_method[i].checked==true){
		 delivery_method=r_delivery_method[i].value
		 break;
	  }
	} 
	
	//r_gift_wrapping 변수에는 선물 포장 여부를 입력하는 라디오 버튼(input type="radio") 요소가 저장됩니다.	
	var r_gift_wrapping  =  frm.gift_wrapping;
	
	//for문을 이용하여, 선물 포장 여부(yes 예 또는 no 아니오) 중 
	//선택한 하나의 <input type="radio"> 항목을 찾아 
	//해당 값 (선물 포장 여부 yes 또는 no)을 gift_wrapping 변수에 저장합니다.
	for(var i=0; i<r_gift_wrapping.length;i++){
	  if(r_gift_wrapping[i].checked==true){
		  gift_wrapping=r_gift_wrapping[i].value
		 break;
	  }
	}
	
	
/* 	아래 코드는 결제 방법(pay_method)에 따라 변수들의 값을 설정하는 부분입니다.*/	
 	var r_pay_method  =  frm.pay_method;
	
	for(var i=0; i<r_pay_method.length;i++){
		
// 		우선 r_pay_method라는 변수에는 결제 방법을 선택하는 라디오 버튼 그룹을 가져옵니다. 
// 		그리고 이 그룹에서 체크된 버튼의 value 값을 가져와 pay_method 변수에 할당합니다.		
		if(r_pay_method[i].checked==true){
		  
			pay_method=r_pay_method[i].value
			
// 			이후, pay_method 변수가 "신용카드"인 경우,  
// 			추가적인 입력 값을 받아와서 해당 변수들에 할당하고, 
// 			pay_method 변수의 값을 "신용카드"와 함께 문자열로 수정합니다.
		  if(pay_method=="신용카드"){
			var i_card_com_name=document.getElementById("card_com_name");
			var i_card_pay_month=document.getElementById("card_pay_month");
			card_com_name=i_card_com_name.value;
			card_pay_month=i_card_pay_month.value;
			pay_method+="<Br>"+
				 		"카드사:"+card_com_name+"<br>"+
				 		"할부개월수:"+card_pay_month;
			
// 			끝으로, 결제 방법에 따라 할당된 변수들을 이용하여 결제 정보를 출력하고자 하는 경우를 위해, 
// 			card_com_name, card_pay_month, pay_orderer_hp_num 변수들도 각각의 값으로 초기화합니다.			
			pay_orderer_hp_num="해당없음";
 			
// 			만약, pay_method 변수가 "휴대폰결제"인 경우, 또 다른 입력 값을 받아와서 해당 변수에 할당합니다. 
// 			그리고 pay_method 변수의 값을 "휴대폰결제"와 함께 문자열로 수정합니다.			
		  }else if(pay_method=="휴대폰결제"){
			var i_pay_order_tel1=document.getElementById("pay_order_tel1");
			var i_pay_order_tel2=document.getElementById("pay_order_tel2");
			var i_pay_order_tel3=document.getElementById("pay_order_tel3");
			pay_orderer_hp_num=i_pay_order_tel1.value+"-"+
						   	    i_pay_order_tel2.value+"-"+
							    i_pay_order_tel3.value;
			pay_method+="<Br>"+"결제휴대폰번호:"+pay_orderer_hp_num;
			
// 			끝으로, 결제 방법에 따라 할당된 변수들을 이용하여 결제 정보를 출력하고자 하는 경우를 위해, 
// 			card_com_name, card_pay_month, pay_orderer_hp_num 변수들도 각각의 값으로 초기화합니다.
			card_com_name="해당없음";
			card_pay_month="해당없음";
		  } //end if
		 break; 
	  }// end for
	}
	
/*	입력된  2.배송지 정보 중에서... 휴대폰번호 선택 요소들 얻기 */	
	var i_hp1=document.getElementById("hp1");
	var i_hp2=document.getElementById("hp2");
	var i_hp3=document.getElementById("hp3");
 
		
/*	입력된  2.배송지 정보 중에서... 유선전화(선택)요소들 얻기 */	
	var i_tel1=document.getElementById("tel1");
	var i_tel2=document.getElementById("tel2");
	var i_tel3=document.getElementById("tel3");

	
	
//입력된  2.배송지 정보 중에서...  주소 영역에서...
//우편번호(zipcode),
//지번주소(roadAddress),
//도로명 주소(jibunAddress),
//나머지 주소(namujiAddress)
	var i_zipcode=document.getElementById("zipcode");
	var i_roadAddress=document.getElementById("roadAddress");
	var i_jibunAddress=document.getElementById("jibunAddress");
	var i_namujiAddress=document.getElementById("namujiAddress");
	
//입력된 	 2.배송지 정보 중에서...  배송 메세지 영역에서..
//배송 메세지(delivery_message)
	var i_delivery_message=document.getElementById("delivery_message");
	
//입력된  4. 결제정보 영역에서 
//   <input type="radio" id="pay_method" name="pay_method" value="신용카드"   onClick="fn_pay_card()" checked>신용카드 &nbsp;&nbsp;&nbsp; 
//   <input type="radio" id="pay_method" name="pay_method" value="제휴 신용카드"  >제휴 신용카드 &nbsp;&nbsp;&nbsp; 
//   <input type="radio" id="pay_method" name="pay_method" value="실시간 계좌이체">실시간 계좌이체 &nbsp;&nbsp;&nbsp;
//   <input type="radio" id="pay_method" name="pay_method" value="무통장 입금">무통장 입금 &nbsp;&nbsp;&nbsp;
//   라디오 요소 배열에 담아  얻기 	
	var i_pay_method=document.getElementById("pay_method");

    //주문한 상품 수량(구매 상품 수량)을 위 선언된 order_goods_qty변수에 저장
	order_goods_qty=h_order_goods_qty.value;

	//로그인된 구매자 이름(받으실분 이름)을 위 선언된 orderer_name변수에 저장
	orderer_name=h_orderer_name.value;
	
	//로그인된 구매자 이름(받으실분 이름)을 위 선언된 receiver_name변수에 저장
	receiver_name=i_receiver_name.value;
	
	//로그인된 구매자의 휴대폰 번호를 hp1 ~ hp3 변수에 저장
	hp1=i_hp1.value;
	hp2=i_hp2.value;
	hp3=i_hp3.value;
	
	//로그인된 구매자의 유선 전화 번호를 tel1 ~ tel3 변수에 저장
	tel1=i_tel1.value;
	tel2=i_tel2.value;
	tel3=i_tel3.value;
	
	//로그인된 구매자의 휴대폰 번호를 완성 해서  receiver_hp_num변수에 저장
	receiver_hp_num=hp1+"-"+hp2+"-"+hp3;
	//로그인된 구매자의 유선 전화 번호를 완성 해서  receiver_tel_num변수에 저장
	receiver_tel_num=tel1+"-"+tel2+"-"+tel3;
	
	//주소 디자인 영역에 보여지는  우편번호 +  도로명 주소 + 지번주소를 합친 문자열을   delivery_address변수에 저장
	delivery_address="우편번호:"+i_zipcode.value+"<br>"+
						"도로명 주소:"+i_roadAddress.value+"<br>"+
						"[지번 주소:"+i_jibunAddress.value+"]<br>"+
								  i_namujiAddress.value;
	
	//주소 디자인 영역에 보여지는 배송 메세지를 얻어 delivery_message변수에 저장
	delivery_message=i_delivery_message.value;
	
	var p_order_goods_id=document.getElementById("p_order_goods_id");
	var p_order_goods_title=document.getElementById("p_order_goods_title");
	var p_order_goods_qty=document.getElementById("p_order_goods_qty");
	var p_total_order_goods_qty=document.getElementById("p_total_order_goods_qty");
	var p_total_order_goods_price=document.getElementById("p_total_order_goods_price");
	var p_orderer_name=document.getElementById("p_orderer_name");
	var p_receiver_name=document.getElementById("p_receiver_name");
	var p_delivery_method=document.getElementById("p_delivery_method");
	var p_receiver_hp_num=document.getElementById("p_receiver_hp_num");
	var p_receiver_tel_num=document.getElementById("p_receiver_tel_num");
	var p_delivery_address=document.getElementById("p_delivery_address");
	var p_delivery_message=document.getElementById("p_delivery_message");
	var p_gift_wrapping=document.getElementById("p_gift_wrapping");	
	var p_pay_method=document.getElementById("p_pay_method");
	
	p_order_goods_id.innerHTML=goods_id;
	p_order_goods_title.innerHTML=goods_title;
	p_total_order_goods_qty.innerHTML=total_order_goods_qty+"개";
	p_total_order_goods_price.innerHTML=total_order_goods_price+"원";
	p_orderer_name.innerHTML=orderer_name;
	p_receiver_name.innerHTML=receiver_name;
	p_delivery_method.innerHTML=delivery_method;
	p_receiver_hp_num.innerHTML=receiver_hp_num;
	p_receiver_tel_num.innerHTML=receiver_tel_num;
	p_delivery_address.innerHTML=delivery_address;
	p_delivery_message.innerHTML=delivery_message;
	p_gift_wrapping.innerHTML=gift_wrapping;
	p_pay_method.innerHTML=pay_method;
	imagePopup('open');
}

//!
//최종 결제하기를 클릭하면 <input>태그를 동적으로 생성한 후 주문창에서 입력한 수량자 정보를 설정하여 컨트롤러로 전송합니다.
function fn_process_pay_order(){
	
	alert("최종 결제하기");
	
	
	
	var formObj=document.createElement("form");
    var i_receiver_name=document.createElement("input");
    
    var i_receiver_hp1=document.createElement("input");
    var i_receiver_hp2=document.createElement("input");
    var i_receiver_hp3=document.createElement("input");
   
    var i_receiver_tel1=document.createElement("input");
    var i_receiver_tel2=document.createElement("input");
    var i_receiver_tel3=document.createElement("input");
    
    var i_delivery_address=document.createElement("input");
    var i_delivery_message=document.createElement("input");
    var i_delivery_method=document.createElement("input");
    var i_gift_wrapping=document.createElement("input");
    var i_pay_method=document.createElement("input");
    var i_card_com_name=document.createElement("input");
    var i_card_pay_month=document.createElement("input");
    var i_pay_orderer_hp_num=document.createElement("input");
   
    i_receiver_name.name="receiver_name";
    i_receiver_hp1.name="receiver_hp1";
    i_receiver_hp2.name="receiver_hp2";
    i_receiver_hp3.name="receiver_hp3";
   
    i_receiver_tel1.name="receiver_tel1";
    i_receiver_tel2.name="receiver_tel2";
    i_receiver_tel3.name="receiver_tel3";
   
    i_delivery_address.name="delivery_address";
    i_delivery_message.name="delivery_message";
    i_delivery_method.name="delivery_method";
    i_gift_wrapping.name="gift_wrapping";
    i_pay_method.name="pay_method";
    i_card_com_name.name="card_com_name";
    i_card_pay_month.name="card_pay_month";
    i_pay_orderer_hp_num.name="pay_orderer_hp_num";
 /*  
<form>
	<input name="receiver_name">
	<input name="receiver_hp1">
	<input name="receiver_hp2">
	<input name="receiver_hp3">
	<input name="receiver_tel1">
	<input name="receiver_tel2">
	<input name="receiver_tel3">
	<input name="delivery_address">
	<input name="delivery_message">
	<input name="delivery_method">
	<input name="gift_wrapping">
	<input name="pay_method">
	<input name="card_com_name">
	<input name="card_pay_month">
	<input name="pay_orderer_hp_num">
</form>
     */
    
    i_receiver_name.value=receiver_name;
    i_receiver_hp1.value=hp1;
    i_receiver_hp2.value=hp2;
    i_receiver_hp3.value=hp3;
    
    i_receiver_tel1.value=tel1;
    i_receiver_tel2.value=tel2;
    i_receiver_tel3.value=tel3;
   
    i_delivery_address.value=delivery_address;
    i_delivery_message.value=delivery_message;
    i_delivery_method.value=delivery_method;
    i_gift_wrapping.value=gift_wrapping;
    i_pay_method.value=pay_method;
    i_card_com_name.value=card_com_name;
    i_card_pay_month.value=card_pay_month;
    i_pay_orderer_hp_num.value=pay_orderer_hp_num;
    
    formObj.appendChild(i_receiver_name);
    formObj.appendChild(i_receiver_hp1);
    formObj.appendChild(i_receiver_hp2);
    formObj.appendChild(i_receiver_hp3);
    formObj.appendChild(i_receiver_tel1);
    formObj.appendChild(i_receiver_tel2);
    formObj.appendChild(i_receiver_tel3);

    formObj.appendChild(i_delivery_address);
    formObj.appendChild(i_delivery_message);
    formObj.appendChild(i_delivery_method);
    formObj.appendChild(i_gift_wrapping);
    
    formObj.appendChild(i_pay_method);
    formObj.appendChild(i_card_com_name);
    formObj.appendChild(i_card_pay_month);
    formObj.appendChild(i_pay_orderer_hp_num);
    

    document.body.appendChild(formObj); 
    
    var body = document.body;
    console.log(body);
    
    formObj.method="post";
    formObj.action="${contextPath}/order/payToOrderGoods.do"; //최종 결제 요청!
    formObj.submit();
	imagePopup('close');
}
</script>
</head>
<body>
	<H1>1.주문확인</H1>
<form  name="form_order">	
	<table class="list_view">
		<tbody align=center>
			<tr style="background: #33ff00">
				<td colspan=2 class="fixed">주문상품</td>
				<td>수량</td>
				<td>주문금액</td>
				<td>배송비</td>
				<td>예상적립금</td>
				<td>주문금액합계</td>
			</tr>
						
<%-- 			${ fn:length(sessionScope.myOrderList) } <br><br><br> --%>
			
	   <%-- session영역에   (OrderVO객체(주문 정보)가 저장된  ArrayList배열) 크기 만큼 반복 !!
	   	   참고. 장바구니에 상품을 담지 않고 로그인 후 바로 구매하기를 누르면 ArrayList배열에 저장된  OrderVO객체(주문상품정보)는 1개 이다.
	    --%>
		<c:forEach var="item" items="${sessionScope.myOrderList}">
			<tr>	
				<td class="goods_image" colspan="2">
					
				  <%-- 주문 상품 이미지를 클릭하면 주문하는 상품 번호를 전달하여  상세화면을 요청 합니다.  --%>	
				  <a href="${contextPath}/goods/goodsDetail.do?goods_id=${item.goods_id }">
				   
				    <%--주문 상품 이미지 표시--%>
				    <img width="75" alt=""  src="${contextPath}/thumbnails.do?goods_id=${item.goods_id}&fileName=${item.goods_fileName}">
				    
				    <%-- 나중에  결제하기를 눌렀을때 요청하는 값들 (주문 한 상품 번호 , 주문 한 상품 이미지명, 주문 상품명, 주문 수량, 주문금액 합계)  --%>
				    <input   type="hidden" id="h_goods_id" name="h_goods_id" value="${item.goods_id }" />
				    <input   type="hidden" id="h_goods_fileName" name="h_goods_fileName" value="${item.goods_fileName }" />
				     <input   type="hidden" id="h_goods_title" name="h_goods_title" value="${item.goods_title }" />
				      <input   type="hidden" id="h_order_goods_qty" name="h_order_goods_qty" value="${item.order_goods_qty}" />
				       <input  type="hidden" id="h_each_goods_price"  name="h_each_goods_price" value="${item.goods_sales_price * item.order_goods_qty}" />
				  </a>
				  
				</td>
<%-- 				
				<td>
				  <h2>
				     <a href="${pageContext.request.contextPath}/goods/goods.do?command=goods_detail&goods_id=${item.goods_id }">${item.goods_title }</a>
				      <input   type="hidden" id="h_goods_title" name="h_goods_title" value="${item.goods_title }" />
				  </h2>
				</td> 
--%>

				<%-- 수량  --%>  
				<td>		  
				  <h2>${item.order_goods_qty }개<h2>	
<%-- 			 <input   type="hidden" id="h_order_goods_qty" name="h_order_goods_qty" value="${item.order_goods_qty}" /> --%>
				</td>
				
				<%-- 주문금액 --%>
				<td><h2>${item.goods_sales_price}원 (10% 할인)</h2></td>
				
				<%-- 배송비 --%>
				<td><h2>0원</h2></td>
				
				<%-- 예상적립금 --%>
				<td><h2>${1500 *item.order_goods_qty}원</h2></td>
				
				<%-- 주문금액 합계 --%>
				<td>
				  <h2>${item.goods_sales_price * item.order_goods_qty}원</h2>
<%-- 			  <input  type="hidden" id="h_each_goods_price"  name="h_each_goods_price" value="${item.goods_sales_price * item.order_goods_qty}" /> --%>
				</td>
			</tr>
				  <%-- 최종 결제 금액 ( 주문 금액 합계 금액을 누적) --%>
					<c:set var="final_total_order_price"
						value="${final_total_order_price+ item.goods_sales_price* item.order_goods_qty}" />
						
				  <%-- 총주문 금액 ( 주문 수량 누적 ) --%>		
					<c:set var="total_order_price"
						value="${total_order_price+ item.goods_sales_price* item.order_goods_qty}" />
					
				  <%--  총 상품 수 ( 주문 수 누적 )--%>	
					<c:set var="total_order_goods_qty"
						value="${total_order_goods_qty+item.order_goods_qty }" />
			</c:forEach>
		</tbody>
	</table>
	<div class="clear"></div>

	<br>
	<br>
	<H1>2.배송지 정보</H1>
	<DIV class="detail_table">
	
		<table>
			<tbody>
				<tr class="dot_line">
					<td class="fixed_join">배송방법</td>
					<td>
					    <input type="radio" id="delivery_method" name="delivery_method" value="일반택배" checked>일반택배 &nbsp;&nbsp;&nbsp; 
						<input type="radio" id="delivery_method" name="delivery_method" value="편의점택배">편의점택배 &nbsp;&nbsp;&nbsp; 
						<input type="radio" id="delivery_method" name="delivery_method" value="해외배송">해외배송 &nbsp;&nbsp;&nbsp;
				    </td>
				</tr>
				<tr class="dot_line">
					<td class="fixed_join">배송지 선택</td>
					<td><input type="radio" name="delivery_place"
						onClick="restore_all()" value="기본배송지" checked>기본배송지 &nbsp;&nbsp;&nbsp; 
						<input type="radio" name="delivery_place" value="새로입력" onClick="reset_all()">새로입력 &nbsp;&nbsp;&nbsp;
						<input type="radio" name="delivery_place" value="최근배송지">최근배송지 &nbsp;&nbsp;&nbsp;
				    </td>
				</tr>
				<tr class="dot_line">
					<td class="fixed_join">받으실 분</td>
					<td><input id="receiver_name" name="receiver_name" type="text" size="40" value="${orderer.member_name }" />
					   <input type="hidden" id="h_orderer_name" name="h_orderer_name"  value="${orderer.member_name }" /> 
					   <input type="hidden" id="h_receiver_name" name="h_receiver_name"  value="${orderer.member_name }" />
					</td>
				</tr>
				<tr class="dot_line">
					<td class="fixed_join">휴대폰번호</td>
					<td><select id="hp1" name="hp1">
							<option>없음</option>
							<option value="010" selected>010</option>
							<option value="011">011</option>
							<option value="016">016</option>
							<option value="017">017</option>
							<option value="018">018</option>
							<option value="019">019</option>
					</select> 
					 - <input size="10px" type="text" id="hp2" name="hp2" value="${orderer.hp2 }"> 
					 - <input size="10px" type="text" id="hp3" name="hp3" value="${orderer.hp3 }"><br><br> 
					  <input type="hidden" id="h_hp1" name="h_hp1" value="${orderer.hp1 }" /> 
					  <input type="hidden" id="h_hp2" name="h_hp2" value="${orderer.hp2 }" /> 
					  <input type="hidden" id="h_hp3" name="h_hp3"  value="${orderer.hp3 }" />
					  <c:set  var="orderer_hp" value="${orderer.hp1}-${orderer.hp2}-${orderer.hp3 }"/>
					   									
					         
				  </tr>
				<tr class="dot_line">
					<td class="fixed_join">유선전화(선택)</td>
					<%--  051-1111-2222 --%>
					<td>
							<select id="tel1" name="tel1">
								<option value="02">02</option>
								<option value="031">031</option>
								<option value="032">032</option>
								<option value="033">033</option>
								<option value="041">041</option>
								<option value="042">042</option>
								<option value="043">043</option>
								<option value="044">044</option>
								<option value="051">051</option>
								<option value="052">052</option>
								<option value="053">053</option>
								<option value="054">054</option>
								<option value="055">055</option>
								<option value="061">061</option>
								<option value="062">062</option>
								<option value="063">063</option>
								<option value="064">064</option>
								<option value="0502">0502</option>
								<option value="0503">0503</option>
								<option value="0505">0505</option>
								<option value="0506">0506</option>
								<option value="0507">0507</option>
								<option value="0508">0508</option>
								<option value="070">070</option>
						</select> - 
						<input size="10px" type="text" id="tel2" name="tel2" value="${sessionScope.orderer.tel2 }"> -
					    <input size="10px" type="text" id="tel3" name="tel3" value="${orderer.tel3 }">
					</td>
					
<%-- OrderControllerImpl 컨트롤러 클래스의  orderEachGoods 메소드 안에서 
     session에 저장 했던 로그인한 회원(상품 주문하는 사람)의 전화번호를 각각 얻어 
         바로위 <select><option>, <input>, <input> 태그에  각각 자바스크립트 코드 function init(){}로 설정하기 위해 
        아래에 값들을 <input type="hidden"> 의 value속성을 작성 하여 설정 해 놓음 
--%>
					<input type="hidden" id="h_tel1" name="h_tel1" value="${sessionScope.orderer.tel1 }" />
					<input type="hidden" id="h_tel2" name="h_tel2"	value="${sessionScope.orderer.tel2 }" />
					<input type="hidden" id="h_tel3" name="h_tel3"value="${sessionScope.orderer.tel3 }" />
				</tr>
				<tr class="dot_line">
					<td class="fixed_join">주소</td>
					<td><input type="text" id="zipcode" name="zipcode" size="5"
						value="${orderer.zipcode }"> 
						<a href="javascript:execDaumPostcode()">우편번호검색</a> <br>
						<p>
							지번 주소:<br>
							<input type="text" id="roadAddress" name="roadAddress" size="50" value="${orderer.roadAddress }" /><br>
							<br> 도로명 주소: 
							   <input type="text" id="jibunAddress" name="jibunAddress" size="50"
								              value="${orderer.jibunAddress }" /><br>
							<br> 나머지 주소: 
							   <input type="text" id="namujiAddress"  name="namujiAddress" size="50"
								     value="${orderer.namujiAddress }" /> 
						</p> 
						 <input type="hidden" id="h_zipcode" name="h_zipcode" value="${orderer.zipcode }" /> 
						 <input type="hidden"  id="h_roadAddress" name="h_roadAddress"  value="${orderer.roadAddress }" /> 
						 <input type="hidden"  id="h_jibunAddress" name="h_jibunAddress" value="${orderer.jibunAddress }" /> 
						 <input type="hidden"  id="h_namujiAddress" name="h_namujiAddress" value="${orderer.namujiAddress }" />
					</td>
				</tr>
				<tr class="dot_line">
					<td class="fixed_join">배송 메시지</td>
					<td>
					   <input id="delivery_message" name="delivery_message" type="text" size="50"
						                   placeholder="택배 기사님께 전달할 메시지를 남겨주세요." />
				     </td>
				</tr>
				<tr class="dot_line">
					<td class="fixed_join">선물 포장</td>
					<td><input type="radio" id="gift_wrapping" name="gift_wrapping" value="yes">예
						&nbsp;&nbsp;&nbsp; <input type="radio"  id="gift_wrapping" name="gift_wrapping" checked value="no">아니요</td>
				</td>
			</tboby>
		</table>
	</div>
	<div >
	  <br><br>
	   <h2>주문고객</h2>
		 <table>
		   <tbody>
			 <tr class="dot_line">
				<td ><h2>이름</h2></td>
				<td>
				 <input  type="text" value="${orderer.member_name}" size="15" />
				</td>
			  </tr>
			  <tr class="dot_line">
				<td ><h2>핸드폰</h2></td>
				<td>
				 <input  type="text" value="${orderer.hp1}-${orderer.hp2}-${orderer.hp3}" size="15" />
				</td>
			  </tr>
			  <tr class="dot_line">
				<td ><h2>이메일</h2></td>
				<td>
				 <input  type="text" value="${orderer.email1}@${orderer.email2}" size="15" />
				</td>
			  </tr>
		   </tbody>
		</table>
	</div>
	<div class="clear"></div>
	<br>
	<br>
	<br>


	<H1>3.할인 정보</H1>
	<div class="detail_table">
		<table>
			<tbody>
				<tr class="dot_line">
					<td width=100>적립금</td>
					<td><input name="discount_juklip" type="text" size="10" />원/1000원
						&nbsp;&nbsp;&nbsp; <input type="checkbox" /> 모두 사용하기</td>
				</tr>
				<tr class="dot_line">
					<td>예치금</td>
					<td><input name="discount_yechi" type="text" size="10" />원/1000원
						&nbsp;&nbsp;&nbsp; <input type="checkbox" /> 모두 사용하기</td>
				</tr>
				<tr class="dot_line">
					<td>상품권 전환금</td>
					<td cellpadding="5"><input name="discount_sangpum" type="text"
						size="10" />원/0원 &nbsp;&nbsp;&nbsp; <input type="checkbox" /> 모두
						사용하기</td>
				</tr>
				<tr class="dot_line">
					<td>OK 캐쉬백 포인트</td>
					<td cellpadding="5"><input name="discount_okcashbag" type="text"
						size="10" />원/0원 &nbsp;&nbsp;&nbsp; <input type="checkbox" /> 모두
						사용하기</td>
				</tr>
				<tr class="dot_line">
					<td>쿠폰할인</td>
					<td cellpadding="5"><input name="discount_coupon" type="text"
						size="10" />원/0원 &nbsp;&nbsp;&nbsp; <input type="checkbox" /> 모두
						사용하기</td>
				</tr>
			</tbody>
		</table>
	</div>
	<div class="clear"></div>

	<br>
	<table width=80% class="list_view" style="background: #ccffff">
		<tbody>
			<tr align=center class="fixed">
				<td class="fixed">총 상품수</td>
				<td>총 상품금액</td>
				<td></td>
				<td>총 배송비</td>
				<td></td>
				<td>총 할인 금액</td>
				<td></td>
				<td>최종 결제금액</td>
			</tr>
			<tr cellpadding=40 align=center>
				<td id="">
					<p id="p_totalNum">${total_order_goods_qty}개</p> 
					<input id="h_total_order_goods_qty" type="hidden" value="${total_order_goods_qty}" />
				</td>
				<td>
					<p id="p_totalPrice">${total_order_price}원</p> <input
					id="h_totalPrice" type="hidden" value="${total_order_price}" />
				</td>
				<td><IMG width="25" alt=""
					src="${pageContext.request.contextPath}/resources/image/plus.jpg"></td>
				<td>
					<p id="p_totalDelivery">${total_delivery_price }원</p> <input
					id="h_totalDelivery" type="hidden" value="${total_delivery_price}" />
				</td>
				<td>
				<img width="25" alt="" 	src="${pageContext.request.contextPath}/resources/image/minus.jpg"></td>
				<td>
					<p id="p_totalSalesPrice">${total_discount_price }원</p> 
					<input id="h_total_sales_price" type="hidden" value="${total_discount_price}" />
				</td>
				<td><img width="25" alt="" src="${pageContext.request.contextPath}/resources/image/equal.jpg"></td>
				<td>
					<p id="p_final_totalPrice">
						<font size="15">${final_total_order_price }원 </font>
					</p> <input id="h_final_total_Price" type="hidden" value="${final_total_order_price}" />
				</td>
			</tr>
		</tbody>
	</table>
   <div class="clear"></div>
	<br>
	<br>
	<br>
	<h1>4.결제정보</h1>
	<div class="detail_table">
		<table>
			<tbody>
				<tr >
					<td>
					   <input type="radio" id="pay_method" name="pay_method" value="신용카드"   onClick="fn_pay_card()" checked>신용카드 &nbsp;&nbsp;&nbsp; 
					   <input type="radio" id="pay_method" name="pay_method" value="제휴 신용카드"  >제휴 신용카드 &nbsp;&nbsp;&nbsp; 
					   <input type="radio" id="pay_method" name="pay_method" value="실시간 계좌이체">실시간 계좌이체 &nbsp;&nbsp;&nbsp;
					   <input type="radio" id="pay_method" name="pay_method" value="무통장 입금">무통장 입금 &nbsp;&nbsp;&nbsp;
					</td>
				</tr>
				<tr >
					<td>
					   <input type="radio" id="pay_method" name="pay_method" value="휴대폰결제" onClick="fn_pay_phone()">휴대폰 결제 &nbsp;&nbsp;&nbsp;
					   <input type="radio" id="pay_method" name="pay_method" value="카카오페이(간편결제)">카카오페이(간편결제) &nbsp;&nbsp;&nbsp; 
					   <input type="radio" id="pay_method" name="pay_method" value="페이나우(간편결제)">페이나우(간편결제) &nbsp;&nbsp;&nbsp; 
					   <input type="radio" id="pay_method" name="pay_method" value="페이코(간편결제)">페이코(간편결제) &nbsp;&nbsp;&nbsp;
					</td>
				</tr>
				<tr >
					<td>
					   <input type="radio"  id="pay_method" name="pay_method" value="직접입금">직접입금&nbsp;&nbsp;&nbsp;
					</td>
				</tr>
				<tr id="tr_pay_card">
					<td>
					  <strong>카드 선택<strong>:&nbsp;&nbsp;&nbsp;
					  <select id="card_com_name" name="card_com_name">
							<option value="삼성" selected>삼성</option>
							<option value="하나SK">하나SK</option>
							<option value="현대">현대</option>
							<option value="KB">KB</option>
							<option value="신한">신한</option>
							<option value="롯데">롯데</option>
							<option value="BC">BC</option>
							<option value="시티">시티</option>
							<option value="NH농협">NH농협</option>
					</select>
					<br><Br>
					<strong>할부 기간:<strong>  &nbsp;&nbsp;&nbsp;
					<select id="card_pay_month" name="card_pay_month">
							<option value="일시불" selected>일시불</option>
							<option value="2개월">2개월</option>
							<option value="3개월">3개월</option>
							<option value="4개월">4개월</option>
							<option value="5개월">5개월</option>
							<option value="6개월">6개월</option>
					</select>
					
					</td>
				</tr>
				<tr id="tr_pay_phone" style="visibility:hidden">
				  <td>
				  <strong>휴대폰 번호 입력: <strong>
				  	       <input  type="text" size="5" value=""  id="pay_order_tel1" name="pay_order_tel1" />-
				           <input  type="text" size="5" value="" id="pay_order_tel2" name="pay_order_tel2" />-
				           <input  type="text" size="5" value="" id="pay_order_tel3" name="pay_order_tel3" />
				  </td>
				</tr>
			</tbody>
		</table>
	</div>
</form>
    <div class="clear"></div>
	<br>
	<br>
	<br>
	<center>
		<br>
		<br> 
		 <%-- 결제하기  --%>
		<a href="javascript:fn_show_order_detail();"> 
		   <img width="125" alt="" src="${contextPath}/resources/image/btn_gulje.jpg"> 
		</a> 
		 <%-- 쇼핑 계속 하기 --%>
		<a href="${contextPath}/main/main.do"> 
		   <img width="75" alt="" src="${contextPath}/resources/image/btn_shoping_continue.jpg">  
		</a>
	
<div class="clear"></div>		

<%--  --------------------------   팝업 창   시작 ------------------------------------  --%>
	<div id="layer" style="visibility:hidden">
		<!-- visibility:hidden 으로 설정하여 해당 div안의 모든것들을 가려둔다. -->
		<div id="popup_order_detail">
			<!-- 팝업창 닫기  X 버튼 -->
			<a href="javascript:" onClick="javascript:imagePopup('close', '.layer01');">
			 <img  src="${contextPath}/resources/image/close.png" id="close" />
			</a> 
			<br/> 
			  <div class="detail_table">
			  <h1>최종 주문 사항</h1>
			<table>
				<tbody align=left>
				 	<tr>
					  <td width=200px>
					      주문상품번호:
					 </td>
					 <td>
						 <p id="p_order_goods_id"> 주문번호 </p>    
					 </td>
				   </tr>
				   <tr>
					  <td width=200px>
					      주문상품명:
					 </td>
					 <td>
						  <p id="p_order_goods_title"> 주문 상품명 </p>    
					 </td>
				   </tr>
				   <tr>
					  <td width=200px>
					      주문상품개수:
					 </td>
					 <td>
						  <p id="p_total_order_goods_qty"> 주문 상품개수 </p>    
					 </td>
				   </tr>
				   <tr>
					  <td width=200px>
					     주문금액합계:
					 </td>
					 <td >
					      <p id="p_total_order_goods_price">주문금액합계</p>
					 </td>
				   </tr>
					<tr>
					  <td width=200px>
					     주문자:
					 </td>
					 <td>
					      <p id="p_orderer_name"> 주문자 이름</p>
					 </td>
				   </tr>
				   <tr>
					  <td width=200px>
					     받는사람:
					 </td>
					 <td>
					      <p id="p_receiver_name">받는사람이름</p>
					 </td>
				   </tr>
				   <tr>
					  <td width=200px>
					     배송방법:
					 </td>
					 <td>
					      <p id="p_delivery_method">배송방법</p>
					 </td>
				   </tr>
				   <tr>
					  <td width=200px>
					     받는사람 휴대폰번호:
					 </td>
					 <td>
					      <p id="p_receiver_hp_num"></p>
					 </td>
				   </tr>
				   <tr>
					  <td width=200px>
					     받는사람 유선번화번호:
					 </td>
					 <td>
					      <p id="p_receiver_tel_num">배송방법</p>
					 </td>
				   </tr>
				   <tr>
					  <td width=200px>
					     배송주소:
					 </td>
					 <td align=left>
					      <p id="p_delivery_address">배송주소</p>
					 </td>
				   </tr>
				    <tr>
					  <td width=200px>
					     배송메시지:
					 </td>
					 <td align=left>
					      <p id="p_delivery_message">배송메시지</p>
					 </td>
				   </tr>
				   <tr>
					  <td width=200px>
					     선물포장 여부:
					 </td>
					 <td align=left>
					      <p id="p_gift_wrapping">선물포장</p>
					 </td>
				   </tr>
				   <tr>
					  <td width=200px>
					     결제방법:
					 </td>
					 <td align=left>
					      <p id="p_pay_method">결제방법</p>
					 </td>
				   </tr>
				   <tr>
				    <td colspan=2 align=center>
				    
				    <%-- 팝업창에서 최종결제하기 클릭시 함수호출 !  함수 내부에서는  팝업창에서 수량정보를 확인후   컨트롤러로 결제 요청을함 --%>
				    
				    <input  name="btn_process_pay_order" type="button" 
				           onClick="fn_process_pay_order()" value="최종결제하기">
				    </td>
				   </tr>
				</tbody>
				</table>
			</div>
<%--  --------------------------   팝업 창  끝  ------------------------------------  --%>			
			
			<div class="clear"></div>	
			<br> 
			
			
			
			