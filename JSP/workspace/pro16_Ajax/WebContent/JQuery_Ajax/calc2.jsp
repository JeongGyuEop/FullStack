<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	//클라이언트 화면 1.html에서 전송된 두개의 요청한값 얻기
	int v1 = Integer.parseInt(request.getParameter("v1"));
	int v2 = Integer.parseInt(request.getParameter("v2"));
    
	//요청한 2개의 데이터를 더해서 더한 결과데이터를 응답하면
	//요청한 페이지인 1.html의 
	//$.ajax메소드 내부에 작성된 success:function의 매개변수 reponseData로 전달한다. 
%>
{
	"v1":<%=v1%>,
	"v2":<%=v2%>,
	"result":<%=v1+v2%>

}















	