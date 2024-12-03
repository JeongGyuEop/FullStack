<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"
	isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath"  value="${pageContext.request.contextPath}"  />


<!DOCTYPE html >
<html>
<head>
<meta charset="utf-8">
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

//아이디 중복 확인
function fn_overlapped(){
	
    var _id=$("#_member_id").val();
    
    if(_id==''){
   	 alert("ID를 입력하세요");
   	 return;
    }
    
    $.ajax({
       type:"post",
       async:false,  
       url:"${contextPath}/member/overlapped.do", //회원가입 시  입력한 아이디가 DB에 저장되어 있는지 중복확인 요청!
       dataType:"text",
       data: {id:_id},
       success:function (data,textStatus){
          if(data=='false'){
       	    alert("사용할 수 있는 ID입니다.");
       	    $('#btnOverlapped').prop("disabled", true);
       	    $('#_member_id').prop("disabled", true);
       	    $('#member_id').val(_id);
          }else{
        	  alert("사용할 수 없는 ID입니다.");
          }
       },
       error:function(data,textStatus){
          alert("에러가 발생했습니다.");ㅣ
       },
       complete:function(data,textStatus){
          //alert("작업을완료 했습니다");
       }
    });  //end ajax	 
 }	
</script>
</head>
<body>
	<h3>필수입력사항</h3>
	<form action="${contextPath}/member/addMember.do" method="post">	
	<div id="detail_table">
		<table>
			<tbody>
				<tr class="dot_line">
					<td class="fixed_join">아이디</td>
					<td>
					  <input type="text" name="_member_id"  id="_member_id"  size="20" />
					  <input type="hidden" name="member_id"  id="member_id" value=""/>
					  
					  <input type="button"  id="btnOverlapped" value="중복체크" onClick="fn_overlapped()" />
					</td>
				</tr>
				<tr class="dot_line">
					<td class="fixed_join">비밀번호</td>
					<td><input name="member_pw" type="password" size="20" /></td>
				</tr>
				<tr class="dot_line">
					<td class="fixed_join">이름</td>
					<td><input name="member_name" type="text" size="20" /></td>
				</tr>
				<tr class="dot_line">
					<td class="fixed_join">성별</td>
					 <td>
				        <c:choose>
				            <c:when test="${not empty param.gender}">
				                <input type="radio" name="member_gender" value="female" 
				                       <c:if test="${param.gender == 'female'}">checked</c:if> disabled />여성
				                <span style="padding-left:120px"></span>
				                <input type="radio" name="member_gender" value="male" 
				                       <c:if test="${param.gender == 'male'}">checked</c:if> disabled />남성
				                <input type="hidden" name="member_gender" value="${param.gender}" />
				            </c:when>
				
				            <c:otherwise>
				                <input type="radio" name="member_gender" value="female" />여성
				                <span style="padding-left:120px"></span>
				                <input type="radio" name="member_gender" value="male" checked />남성
				            </c:otherwise>
				        </c:choose>
				    </td>
				</tr>
				<tr class="dot_line">
				    <td class="fixed_join">법정생년월일</td>
				    <td>
				        <!-- 연도 -->
				        <c:choose>
				            <c:when test="${not empty param.birthyear}">
				                <input type="text" name="member_birth_y" 
				                       value="${param.birthyear}" 
				                       readonly 
				                       style="font-size:12px; color:gray; width:50px;" />년
				            </c:when>
				            <c:otherwise>
				                <select name="member_birth_y" style="font-size:12px; color:gray; width:60px;">
				                    <c:forEach var="year" begin="1920" end="2020">
				                        <option value="${year}" 
				                                <c:if test="${year == 1980}">selected</c:if>>${year}</option>
				                    </c:forEach>
				                </select>년
				            </c:otherwise>
				        </c:choose>
				
				        <!-- 월 -->
				        <c:choose>
				            <c:when test="${not empty param.birthday}">
				                <input type="text" name="member_birth_m" 
				                       value="${param.birthday.substring(0, 2)}" 
				                       readonly 
				                       style="font-size:12px; color:gray; width:30px;" />월
				            </c:when>
				            <c:otherwise>
				                <select name="member_birth_m" style="font-size:12px; color:gray; width:40px;">
				                    <c:forEach var="month" begin="1" end="12">
				                        <option value="${month}" 
				                                <c:if test="${month == 5}">selected</c:if>>${month}</option>
				                    </c:forEach>
				                </select>월
				            </c:otherwise>
				        </c:choose>
				
				        <!-- 일 -->
				        <c:choose>
				            <c:when test="${not empty param.birthday}">
				                <input type="text" name="member_birth_d" 
				                       value="${param.birthday.substring(2, 4)}" 
				                       readonly 
				                       style="font-size:12px; color:gray; width:30px;" />일
				            </c:when>
				            <c:otherwise>
				                <select name="member_birth_d" style="font-size:12px; color:gray; width:40px;">
				                    <c:forEach var="day" begin="1" end="31">
				                        <option value="${day}" 
				                                <c:if test="${day == 10}">selected</c:if>>${day}</option>
				                    </c:forEach>
				                </select>일
				            </c:otherwise>
				        </c:choose>
				
				        <span style="padding-left:20px;"></span>
				
				        <!-- 양력/음력 -->
				        <c:choose>
				            <c:when test="${not empty param.birth_gn}">
				                <input type="radio" name="member_birth_gn" 
				                       value="2" 
				                       <c:if test="${param.birth_gn == '2'}">checked</c:if> 
				                       disabled 
				                       style="font-size:12px; color:gray;" />양력
				                <span style="padding-left:20px;"></span>
				                <input type="radio" name="member_birth_gn" 
				                       value="1" 
				                       <c:if test="${param.birth_gn == '1'}">checked</c:if> 
				                       disabled 
				                       style="font-size:12px; color:gray;" />음력
				            </c:when>
				            <c:otherwise>
				                <input type="radio" name="member_birth_gn" value="2" checked 
				                       style="font-size:12px; color:gray;" />양력
				                <span style="padding-left:20px;"></span>
				                <input type="radio" name="member_birth_gn" value="1" 
				                       style="font-size:12px; color:gray;" />음력
				            </c:otherwise>
				        </c:choose>
				    </td>
				</tr>

				<tr class="dot_line">
					<td class="fixed_join">전화번호</td>
					<td><select  name="tel1">
							<option>없음</option>
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
					   </select> - <input  size="10px" type="text" name="tel2"> - <input size="10px"  type="text" name="tel3">
					</td>
				</tr>
				<tr class="dot_line">
				    <td class="fixed_join">휴대폰번호</td>
				    <td>
				        <c:choose>
				            <c:when test="${not empty param.phoneNumber}">
				                <input type="text" name="phoneNumber" size="15" 
				                       value="${param.phoneNumber}" readonly 
				                       style="color:gray;" />
				            </c:when>
				            
				            <c:otherwise>
				                <select name="hp1">
				                    <option>없음</option>
				                    <option value="010" selected>010</option>
				                    <option value="011">011</option>
				                    <option value="016">016</option>
				                    <option value="017">017</option>
				                    <option value="018">018</option>
				                    <option value="019">019</option>
				                </select> - 
				                <input size="10px" type="text" name="hp2"> - 
				                <input size="10px" type="text" name="hp3"><br><br>
				                <input type="checkbox" name="smssts_yn" value="Y" checked /> 쇼핑몰에서 발송하는 SMS 소식을 수신합니다.
				            </c:otherwise>
				        </c:choose>
				    </td>
				</tr>
				
				<tr class="dot_line">
				    <td class="fixed_join">이메일<br>(e-mail)</td>
				    <td>
				        <c:choose>
				            <c:when test="${not empty param.email1 and not empty param.email2}">
				            	<input size="10px" type="text" name="email1" value="${param.email1}" style="color:gray;" readonly /> @
								<input size="10px" type="text" name="email2" value="${param.email2}" style="color:gray;" readonly />
				            </c:when>
				
				            <c:otherwise>
				                <input size="10px" type="text" name="email1" /> @ 
				                <input size="10px" type="text" name="email2" /> 
				                <select name="email2" onChange="" title="직접입력">
				                    <option value="non">직접입력</option>
				                    <option value="hanmail.net">hanmail.net</option>
				                    <option value="naver.com">naver.com</option>
				                    <option value="yahoo.co.kr">yahoo.co.kr</option>
				                    <option value="hotmail.com">hotmail.com</option>
				                    <option value="paran.com">paran.com</option>
				                    <option value="nate.com">nate.com</option>
				                    <option value="google.com">google.com</option>
				                    <option value="gmail.com">gmail.com</option>
				                    <option value="empal.com">empal.com</option>
				                    <option value="korea.com">korea.com</option>
				                    <option value="freechal.com">freechal.com</option>
				                </select>
				            </c:otherwise>
				        </c:choose>
				        <br><br>
				        <input type="checkbox" name="emailsts_yn" value="Y" checked /> 쇼핑몰에서 발송하는 e-mail을 수신합니다.
				    </td>
				</tr>


				<tr class="dot_line">
					<td class="fixed_join">주소</td>
					<td>
					   <input type="text" id="zipcode" name="zipcode" size="10" > <a href="javascript:execDaumPostcode()">우편번호검색</a>
					  <br>
					  <p> 
					   지번 주소:<br><input type="text" id="roadAddress"  name="roadAddress" size="50"><br><br>
					  도로명 주소: <input type="text" id="jibunAddress" name="jibunAddress" size="50"><br><br>
					  나머지 주소: <input type="text"  name="namujiAddress" size="50" />
					 <!--   <span id="guide" style="color:#999"></span> -->
					   </p>
					</td>
				</tr>
			</tbody>
		</table>
		</div>
		<div class="clear">
		<br><br>
		<table align=center>
		<tr >
			<td >
				<input type="submit"  value="회원 가입">
				<input  type="reset"  value="다시입력">
			</td>
		</tr>
	</table>
	</div>
</form>	
</body>
</html>