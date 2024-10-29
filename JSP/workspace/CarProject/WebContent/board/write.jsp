
<%@page import="Vo.MemberVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%
	request.setCharacterEncoding("UTF-8");

	String contextPath = request.getContextPath();
	
	// 조회한 글작성자의 정보
	MemberVo membervo = (MemberVo)request.getAttribute("membervo");
	String email = membervo.getEmail();
	String name = membervo.getName();
	String id = membervo.getId();
	
	
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<table width="90%" border="0" cellspacing="0" cellpadding="0">
  <tr height="40"> 
    <td width="41%" style="text-align: left"> &nbsp;&nbsp;&nbsp; 
    	<img src="<%=contextPath%>/board/images/board02.gif" width="150" height="30">
    </td>
    <td width="57%">&nbsp;</td>
    <td width="2%">&nbsp;</td>
  </tr>
  <tr> 
    <td colspan="3"><div align="center"><img src="<%=contextPath%>/board/images/line_870.gif" width="870" height="4"></div></td>
  </tr>
  <tr> 
    <td colspan="3"><div align="center"> 
        <table width="95%" border="0" cellspacing="0" cellpadding="0">
          <tr> 
            <td height="20" colspan="3"></td>
          </tr>
          <tr> 
            <td colspan="3" valign="top">
            <div align="center"> 
                <table width="100%" height="373" border="0" cellpadding="0" cellspacing="1" class="border1">
                  <tr> 
                    <td width="13%" height="29" bgcolor="#e4e4e4" class="text2">
                    	<div align="center">작 성 자</div>
                    </td>
                    <td width="34%" bgcolor="#f5f5f5" style="text-align: left">
                    	<input type="text" name="writer" size="20" class="text2" value="<%=name %>" readonly />
                    </td>
                    
                    <td width="13%" height="29" bgcolor="#e4e4e4" class="text2">
                    	<div align="center">아 이 디</div>
                    </td>
                    <td width="34%" bgcolor="#f5f5f5" style="text-align: left">
                    	<input type="text" name="writer_id" 
                    	size="20" class="text2" value="<%=id %>" readonly/>
                    </td>
                   </tr>
                   <tr>
                    <td width="13%" bgcolor="#e4e4e4">
                    	<div align="center"> 
                        	<p class="text2">메일주소</p>
                      	</div>
                    </td>
                    <td colspan="3" bgcolor="#f5f5f5" style="text-align: left">
                        <input type="text" name="email" size="40" class="text2" value="<%=email %>" readonly/>
                    </td>
                  </tr>             
                  <tr> 
                    <td height="31" bgcolor="#e4e4e4" class="text2">
                    	<div align="center">제&nbsp;&nbsp;&nbsp;목</div>
                    </td>
                    <td colspan="3" bgcolor="#f5f5f5" style="text-align: left">
                    	<input type="text" name="title" size="70"/>
                    </td>
                  </tr>
                  <tr> 
                    <td bgcolor="#e4e4e4" class="text2">
                    	<div align="center">내 &nbsp;&nbsp; 용</div>
                    </td>
                    <td colspan="3" bgcolor="#f5f5f5" style="text-align: left">
                    	<textarea name="content" rows="15" cols="100"></textarea>
                    </td>
                  </tr>
                  <tr> 
                    <td bgcolor="#e4e4e4" class="text2">
                    	<div align="center">패스워드</div>
                    </td>
                    <td colspan="3" bgcolor="#f5f5f5" style="text-align: left">
                    	<input type="password" name="pass"/>
                    </td>
                  </tr>
                </table>
              </div>
              </td>
          </tr>
          <tr> 
            <td colspan="3">&nbsp;</td>
          </tr>
          <tr> 
            <td width="48%">
            <!-- 등록 버튼 -->
            <div align="right">
            	<a href="" id="registration1">
            		<img src="<%=contextPath%>/board/images/confirm.gif" border="0"/>
           		</a>
            </div>
            </td>
            <td width="10%">
            <!-- 목록보기 -->
            <div align="center">
            	<a href="#" id="list">
            		<img src="<%=contextPath%>/board/images/list.gif" border="0"/>
            	</a>
            </div>
            </td>
            <td width="42%" id="resultInsert"></td>
          </tr>
        </table>
      </div></td>
  </tr>
</table>
</form>
	<script  src="http://code.jquery.com/jquery-latest.min.js"></script>

	<script type="text/javascript">

		// 목록 링크 <a>를 선택해서 클릭이벤트 등록
		$("#list").click(function(event){
			
			// <a href="#" id="list">목록</a>
			event.preventDefault(); // a태그 href 속성의 주소 요청 기본 이벤트 차단

			// board 테이블에 저장된 모든 글 조회 요청!
			location.href="<%=contextPath %>/Board/list.bo";
		});
		
		
		 //새글 정보를 입력하고 등록 이미지를 감싸고 있는 <a>를 선택해서 클릭이벤트 등록
	    $("#registration1").click(function(event){
	    	
	    	event.preventDefault();
	    	
	    	//요청 할 값 얻기
	    	let writer = $("input[name=writer]").val();//글 작성자 명 
	    	let id = $("input[name=writer_id]").val();//글 작성자 아이디 
	    	let email = $("input[name=email]").val();//글 작성자 이메일
	    	let title = $("input[name=title]").val();//작성한 글 제목
	    	let content = $("textarea[name=content]").val();//작성한 글 내용
	    	let pass = $("input[name=pass]").val(); //작성한 새글의 비밀번호 
	    	
	    	//BoardController에 입력한 새글정보를 board테이블에 INSERT요청
	    	$.ajax({
	    			url:"<%=contextPath%>/Board/writePro.bo",
	    			type:"post",
	    			async:true,
	    			data:{ w:writer, 
	    				   i:id,	 
	    				   e:email,
	    				   t:title,
	    				   c:content,
	    				   p:pass
	    			     },
	    			dataType:"text",
	    			success:function(responseData){
	    							 //"1" 또는 "0" 가 매개변수로 넘어 온다
	    				if(responseData === "1"){
	    					
	    					$("#resultInsert").text("글 작성 완료!").css("color","green");
	    					
	    				}else{//"0"
	    					
	    					$("#resultInsert").text("글 작성 실패!").css("color","red");
	    				}
	    			}
	    	});
	    	
	    });

	
	
			
		
		
	</script>
</body>
</html>