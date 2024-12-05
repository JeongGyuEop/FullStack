<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="euc-kr"
	isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="contextPath"  value="${pageContext.request.contextPath}"  />


<%-- 새로 등록된  도서 상품을 수정하기 위해 조회해 온  map안에 들어 있는 GoodsVO객체와  ImageFileList배열을   ModelandView객체 메모리에서 얻기   --%>
<c:set var="goods"  value="${goodsMap.goods}"  />
<c:set var="imageFileList"  value="${goodsMap.imageFileList}"  />

<c:choose>
<c:when test='${not empty goods.goods_status}'>
<script>
window.onload=function()
{
	init();
}

function init(){
	var frm_mod_goods=document.frm_mod_goods;
	var h_goods_status=frm_mod_goods.h_goods_status;
	var goods_status=h_goods_status.value;
	var select_goods_status=frm_mod_goods.goods_status;
	 select_goods_status.value=goods_status;
}
</script>
</c:when>
</c:choose>
<script type="text/javascript">

						//수정할상품번호, 수정할 HTML태그의 name속성값 
function fn_modify_goods(goods_id, attribute){ 
							
	// <form  name="frm_mod_goods"  method=post > </form>  수정 요청  폼						
	var frm_mod_goods=document.frm_mod_goods;
	
	var value="";
	
	//상품 분류의  option을 선택할 <select>태그 이면?
	if(attribute=='goods_sort'){
		
		//선택한 option태그의 value속성값을 얻어 저장 
		value=frm_mod_goods.goods_sort.value;
		
	//수정할 상품이름을 입력할 <input>태그 이면?	
	}else if(attribute=='goods_title'){
		
		//수정을 위해 입력한 상품 이름 얻어 저장 
		value=frm_mod_goods.goods_title.value;
		
		
	}else if(attribute=='goods_writer'){
		value=frm_mod_goods.goods_writer.value;   
	}else if(attribute=='goods_publisher'){
		value=frm_mod_goods.goods_publisher.value;
	}else if(attribute=='goods_price'){
		value=frm_mod_goods.goods_price.value;
	}else if(attribute=='goods_sales_price'){
		value=frm_mod_goods.goods_sales_price.value;
	}else if(attribute=='goods_point'){
		value=frm_mod_goods.goods_point.value;
	}else if(attribute=='goods_published_date'){
		value=frm_mod_goods.goods_published_date.value;
	}else if(attribute=='goods_page_total'){
		value=frm_mod_goods.goods_page_total.value;
	}else if(attribute=='goods_isbn'){
		value=frm_mod_goods.goods_isbn.value;
	}else if(attribute=='goods_delivery_price'){
		value=frm_mod_goods.goods_delivery_price.value;
	}else if(attribute=='goods_delivery_date'){
		value=frm_mod_goods.goods_delivery_date.value;
	}else if(attribute=='goods_status'){
		value=frm_mod_goods.goods_status.value;
	}else if(attribute=='goods_contents_order'){
		value=frm_mod_goods.goods_contents_order.value;
	}else if(attribute=='goods_writer_intro'){
		value=frm_mod_goods.goods_writer_intro.value;
	}else if(attribute=='goods_intro'){
		value=frm_mod_goods.goods_intro.value;
	}else if(attribute=='publisher_comment'){
		value=frm_mod_goods.publisher_comment.value;
	}else if(attribute=='recommendation'){
		value=frm_mod_goods.recommendation.value;
	}

	$.ajax({
		type : "post",
		async : false, //false인 경우 동기식으로 처리한다.
		url : "${contextPath}/admin/goods/modifyGoodsInfo.do",
		data : {
			goods_id:goods_id,
			attribute:attribute,
			value:value
		},               
		success : function(data, textStatus) {
				
			var  inner = document.getElementById(attribute);
			
			if(data.trim()=='mod_success'){
				
				inner.innerHTML = "<h6>성공</h61>";
				inner.style.color="red";
								
			}else if(data.trim()=='failed'){
				
				inner.innerHTML = "<h6>실패</h6>";
				inner.style.color="blue";
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

	// 이미지 미리보기 기능을 구현하는 함수
  function readURL(input,preview) {
		
		// input 매개변수로 넘어오는 DOM <- onchange 이벤트가 발생한 <input type="file"> DOM을
		//									this의 키워드에 의해서 매개변수로 전달 받음
		
		console.debug(input); //크롬 웹브라우저 F12 버튼 눌려 개발자 코드 창의 console 탭에서 출력되는지 확인
							  // <input type="file">
		
		console.log(input.files);
		/*
		참고 :
			<input 
		*/
		
	//  alert(preview);
    if (input.files && input.files[0]) {
        var reader = new FileReader();
        reader.onload = function (e) {
            $('#'+preview).attr('src', e.target.result);
        }
        reader.readAsDataURL(input.files[0]);
    }
  }  

  var cnt =1;
  function fn_addFile(){
	  $("#d_file").append("<br>"+"<input  type='file' name='detail_image"+cnt+"' id='detail_image"+cnt+"'  onchange=readURL(this,'previewImage"+cnt+"') />");
	  $("#d_file").append("<img  id='previewImage"+cnt+"'   width=200 height=200  />");
	  $("#d_file").append("<input  type='button' value='추가'  onClick=addNewImageFile('detail_image"+cnt+"','${imageFileList[0].goods_id}','detail_image')  />");
	  cnt++;
  }
  
  function modifyImageFile(fileId,goods_id, image_id,fileType){
    // alert(fileId);
	  var form = $('#FILE_FORM')[0];
      var formData = new FormData(form);
      formData.append("fileName", $('#'+fileId)[0].files[0]);
      formData.append("goods_id", goods_id);
      formData.append("image_id", image_id);
      formData.append("fileType", fileType);
      
      $.ajax({
        url: '${contextPath}/admin/goods/modifyGoodsImageInfo.do',
        processData: false,
        contentType: false,
        data: formData,
        type: 'POST',
	      success: function(result){
	         alert("이미지를 수정했습니다!");
	       }
      });
  }
  
  function addNewImageFile(fileId,goods_id, fileType){
	   //  alert(fileId);
		  var form = $('#FILE_FORM')[0];
	      var formData = new FormData(form);
	      formData.append("uploadFile", $('#'+fileId)[0].files[0]);
	      formData.append("goods_id", goods_id);
	      formData.append("fileType", fileType);
	      
	      $.ajax({
	          url: '${contextPath}/admin/goods/addNewGoodsImage.do',
	                  processData: false,
	                  contentType: false,
	                  data: formData,
	                  type: 'post',
	                  success: function(result){
	                      alert("이미지를 수정했습니다!");
	                  }
	          });
	  }
  
  function deleteImageFile(goods_id,image_id,imageFileName,trId){
	var tr = document.getElementById(trId);

      	$.ajax({
    		type : "post",
    		async : true, //false인 경우 동기식으로 처리한다.
    		url : "${contextPath}/admin/goods/removeGoodsImage.do",
    		data: {goods_id:goods_id,
     	         image_id:image_id,
     	         imageFileName:imageFileName},
    		success : function(data, textStatus) {
    			alert("이미지를 삭제했습니다!!");
                tr.style.display = 'none';
    		},
    		error : function(data, textStatus) {
    			alert("에러가 발생했습니다."+textStatus);
    		},
    		complete : function(data, textStatus) {
    			//alert("작업을완료 했습니다");
    			
    		}
    	}); //end ajax	
  }
</script>

</HEAD>
<BODY>
<form  name="frm_mod_goods"  method=post >
<DIV class="clear"></DIV>
	<!-- 내용 들어 가는 곳 -->
	<DIV id="container">
		<UL class="tabs">
			<li><A href="#tab1">상품정보</A></li>
			<li><A href="#tab2">상품목차</A></li>
			<li><A href="#tab3">상품저자소개</A></li>
			<li><A href="#tab4">상품소개</A></li>
			<li><A href="#tab5">출판사 상품 평가</A></li>
			<li><A href="#tab6">추천사</A></li>
			<li><A href="#tab7">상품이미지</A></li>
		</UL>
		<DIV class="tab_container">
			<DIV class="tab_content" id="tab1">
				<table >
			<tr >
				<td width=200 >상품분류</td>
				<td width=500>
				  <select name="goods_sort">
					<c:choose>
				      <c:when test="${goods.goods_sort=='컴퓨터와 인터넷' }">
						<option value="컴퓨터와 인터넷" selected>컴퓨터와 인터넷 </option>
				  	    <option value="디지털 기기">디지털 기기  </option>
				  	  </c:when>
				  	  <c:when test="${goods.goods_sort=='디지털 기기' }">
						<option value="컴퓨터와 인터넷" >컴퓨터와 인터넷 </option>
				  	    <option value="디지털 기기" selected>디지털 기기  </option>
				  	  </c:when>
				  	</c:choose>
					</select>
				</td>
				<td><div id="goods_sort"></div></td>
				<td>
				 <input  type="button" value="수정반영"  onClick="fn_modify_goods('${goods.goods_id }','goods_sort')"/>
				</td>
			</tr>
			<tr >
				<td >상품이름</td>
				<td><input name="goods_title" type="text" size="40"  value="${goods.goods_title }"/></td>
				<td><div id="goods_title"></div></td>
				<td>
				 <input  type="button" value="수정반영"  onClick="fn_modify_goods('${goods.goods_id }','goods_title')"/>
				</td>
			</tr>
			
			<tr>
				<td >저자</td>
				<td><input name="goods_writer" type="text" size="40" value="${goods.goods_writer }" /></td>
				<td><div id="goods_writer"></div></td>
				<td>
				 <input  type="button" value="수정반영"  onClick="fn_modify_goods('${goods.goods_id }','goods_writer')"/>
				</td>
				
			</tr>
			<tr>
				<td >출판사</td>
				<td><input name="goods_publisher" type="text" size="40" value="${goods.goods_publisher }" /></td>
				<td><div id="goods_publisher"></div></td>
			     <td>
				  <input  type="button" value="수정반영"  onClick="fn_modify_goods('${goods.goods_id }','goods_publisher')"/>
				</td>
				
			</tr>
			<tr>
				<td >상품정가</td>
				<td><input name="goods_price" type="text" size="40" value="${goods.goods_price }" /></td>
				<td><div id="goods_price"></div></td>
				<td>
				 <input  type="button" value="수정반영"  onClick="fn_modify_goods('${goods.goods_id }','goods_price')"/>
				</td>
				
			</tr>
			
			<tr>
				<td >상품판매가격</td>
				<td><input name="goods_sales_price" type="text" size="40" value="${goods.goods_sales_price }" /></td>
				<td><div id="goods_sales_price"></div></td>
				<td>
				 <input  type="button" value="수정반영"  onClick="fn_modify_goods('${goods.goods_id }','goods_sales_price')"/>
				</td>
				
			</tr>
			
			
			<tr>
				<td >상품 구매 포인트</td>
				<td><input name="goods_point" type="text" size="40" value="${goods.goods_point }" /></td>
				<td><div id="goods_point"></div></td>
				<td>
				 <input  type="button" value="수정반영"  onClick="fn_modify_goods('${goods.goods_id }','goods_point')"/>
				</td>

			</tr>

			<tr>
				<td >상품출판일</td>
				<td>
				  <input  name="goods_published_date"  type="date"  value="${goods.goods_published_date }" />
				</td>
				<td><div id="goods_published_date"></div></td>				
				<td>
				 <input  type="button" value="수정반영"  onClick="fn_modify_goods('${goods.goods_id }','goods_published_date')"/>
				</td>

			</tr>
			
			<tr>
				<td >상품 총 페이지수</td>
				<td><input name="goods_total_page" type="text" size="40"  value="${goods.goods_total_page }"/></td>
				<td><div id="goods_total_page"></div></td>				
				<td>
				 <input  type="button" value="수정반영"  onClick="fn_modify_goods('${goods.goods_id }','goods_total_page"/>
				</td>

			</tr>
			
			<tr>
				<td >ISBN</td>
				<td><input name="goods_isbn" type="text" size="40" value="${goods.goods_isbn }" /></td>
				<td><div id="goods_isbn"></div></td>				
				<td>
				 <input  type="button" value="수정반영"  onClick="fn_modify_goods('${goods.goods_id }','goods_isbn')"/>
				</td>

			</tr>
			<tr>
				<td >상품 배송비</td>
				<td><input name="goods_delivery_price" type="text" size="40"  value="${goods.goods_delivery_price }"/></td>
				<td><div id="goods_delivery_price"></div></td>								
				<td>
				 <input  type="button" value="수정반영"  onClick="fn_modify_goods('${goods.goods_id }','goods_delivery_price')"/>
				</td>

			</tr>
			<tr>
				<td >상품 도착 예정일</td>
				<td>
				  <input name="goods_delivery_date" type="date"  value="${goods.goods_delivery_date }" />
				  </td>
				<td><div id="goods_delivery_date"></div></td>												  
				<td>
				 <input  type="button" value="수정반영"  onClick="fn_modify_goods('${goods.goods_id }','goods_delivery_date')"/>
				</td>

			</tr>
			
			<tr>
				<td >상품종류</td>
				<td>
				<select name="goods_status">
				  <option value="bestseller"  >베스트셀러</option>
				  <option value="steadyseller" >스테디셀러</option>
				  <option value="newbook" >신간</option>
				  <option value="on_sale" >판매중</option>
				  <option value="buy_out"  selected>품절</option>
				  <option value="out_of_print" >절판</option>
				</select>
				<input  type="hidden" name="h_goods_status" value="${goods.goods_status }"/>
				</td>
				<td><div id="goods_status"></div></td>												  				
				<td>
				 <input  type="button" value="수정반영"  onClick="fn_modify_goods('${goods.goods_id }','goods_status')"/>
				</td>
			</tr>
			<tr>
			 <td colspan=3>
			   <br>
			 </td>
			</tr>
				</table>	
			</DIV>
			<DIV class="tab_content" id="tab2">
				<h4>책목차</h4>
				<table>	
				<tr>
					<td >상품목차</td>
					<td><textarea  rows="100" cols="80" name="goods_contents_order">
					  ${goods.goods_contents_order }
					</textarea>
					</td>
					<td><div id="goods_contents_order"></div></td>												  						
					<td>		
					&nbsp;&nbsp;&nbsp;&nbsp;
					 <input  type="button" value="수정반영"  onClick="fn_modify_goods('${goods.goods_id }','goods_contents_order')"/>
					</td>
				</tr>
				</table>	
			</DIV>
			<DIV class="tab_content" id="tab3">
				<H4>상품 저자 소개</H4>
				<P>
				 <table>
	  				 <tr>
						<td >상품 저자 소개</td>
						<td><textarea  rows="100" cols="80" name="goods_writer_intro">
						  ${goods.goods_writer_intro }
						</textarea>
						</td>
						<td><div id="goods_writer_intro"></div></td>												  								
						<td>
						&nbsp;&nbsp;&nbsp;&nbsp;
						 <input  type="button" value="수정반영"  onClick="fn_modify_goods('${goods.goods_id }','goods_writer_intro')"/>
						</td>
				   </tr>
			   </table>
				</P>
			</DIV>
			<DIV class="tab_content" id="tab4">
				<H4>상품소개</H4>
				<P>
				<table>
					<tr>
						<td>상품소개</td>
						<td><textarea  rows="100" cols="80" name="goods_intro">
						${goods.goods_intro }
						</textarea>
						</td>
						<td><div id="goods_intro"></div></td>												  										
						<td>	 
						 <input  type="button" value="수정반영"  onClick="fn_modify_goods('${goods.goods_id }','goods_intro')"/>
						</td>
					</tr>
			    </table>
				</P>
			</DIV>
			<DIV class="tab_content" id="tab5">
				<H4>출판사 상품 평가</H4>
				<P>
				<table>
					<tr>
						<td><textarea  rows="100" cols="80" name="goods_publisher_comment">
						  ${goods.goods_publisher_comment }
						</textarea>
						</td>
						<td><div id="goods_publisher_comment"></div></td>												  													
						<td>
						&nbsp;&nbsp;&nbsp;&nbsp;
						 <input  type="button" value="수정반영"  onClick="fn_modify_goods('${goods.goods_id }','goods_publisher_comment')"/>
						</td>
					</tr>
			</table>
				</P>
			</DIV>
			<DIV class="tab_content" id="tab6">
				<H4>추천사</H4>
				 <table>
					 <tr>
						<td>추천사</td>
						<td><textarea  rows="100" cols="80" name="goods_recommendation">
						  ${goods.goods_recommendation }
						</textarea>
						</td>
						<td><div id="goods_recommendation"></div></td>												  													
						<td>
						 <input  type="button" value="수정반영"  onClick="fn_modify_goods('${goods.goods_id }','goods_recommendation')"/>
						</td>
					</tr>
			    </table>
			</DIV>
			<DIV class="tab_content" id="tab7">
			   <form id="FILE_FORM" method="post" enctype="multipart/form-data"  >
				<h4>상품이미지</h4>
				 <table>
					 <tr>
					<c:forEach var="item" items="${imageFileList }"  varStatus="itemNum">
			        <c:choose>
			            <c:when test="${item.fileType=='main_image' }">
			              <tr>
						    <td>메인 이미지</td>
						    <td>
							  <input type="file"  id="main_image"  name="main_image"  onchange="readURL(this,'preview${itemNum.count}');" />
						      <%-- <input type="text" id="image_id${itemNum.count }"  value="${item.fileName }" disabled  /> --%>
							  <input type="hidden"  name="image_id" value="${item.image_id}"  />
							<br>
						</td>
						<td>
						  <img  id="preview${itemNum.count }"   width=200 height=200 src="${contextPath}/download.do?goods_id=${item.goods_id}&fileName=${item.fileName}" />
						</td>
						<td>
						  &nbsp;&nbsp;&nbsp;&nbsp;
						</td>
						 <td>
						 <input  type="button" value="수정"  onClick="modifyImageFile('main_image','${item.goods_id}','${item.image_id}','${item.fileType}')"/>
						</td> 
					</tr>
					<tr>
					 <td>
					   <br>
					 </td>
					</tr>
			         </c:when>
			         <c:otherwise>
			           <tr  id="${itemNum.count-1}">
						<td>상세 이미지${itemNum.count-1 }</td>
						<td>
							<input type="file" name="detail_image"  id="detail_image"   onchange="readURL(this,'preview${itemNum.count}');" />
							<%-- <input type="text" id="image_id${itemNum.count }"  value="${item.fileName }" disabled  /> --%>
							<input type="hidden"  name="image_id" value="${item.image_id }"  />
							<br>
						</td>
						<td>
						  <img  id="preview${itemNum.count }"   width=200 height=200 src="${contextPath}/download.do?goods_id=${item.goods_id}&fileName=${item.fileName}">
						</td>
						<td>
						  &nbsp;&nbsp;&nbsp;&nbsp;
						</td>
						 <td>
						 <input  type="button" value="수정"  onClick="modifyImageFile('detail_image','${item.goods_id}','${item.image_id}','${item.fileType}')"/>
						  <input  type="button" value="삭제"  onClick="deleteImageFile('${item.goods_id}','${item.image_id}','${item.fileName}','${itemNum.count-1}')"/>
						</td> 
					</tr>
					<tr>
					 <td>
					   <br>
					 </td>
					</tr> 
			         </c:otherwise>
			       </c:choose>		
			    </c:forEach>
			    <tr align="center">
			      <td colspan="3">
				      <div id="d_file">
						  <%-- <img  id="preview${itemNum.count }"   width=200 height=200 src="${contextPath}/download.do?goods_id=${item.goods_id}&fileName=${item.fileName}" /> --%>
				      </div>
			       </td>
			    </tr>
		   <tr>
		     <td align=center colspan=2>
		     
		     <input   type="button" value="이미지파일추가하기"  onClick="fn_addFile()"  />
		   </td>
		</tr> 
	</table>
	</form>
	</DIV>
	<DIV class="clear"></DIV>
					
</form>	