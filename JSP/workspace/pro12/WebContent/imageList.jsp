<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

	<style type="text/css">
		/* class='list_type' ul영역의 스타일 */
		.list_type {
			 overflow: hidden; /*내용이 넘칠 경우 가려지는 부분을 숨깁니다.*/
			 width:80%; /*리스트의 너비를 80%로 설정합니다.*/
			 padding: 0 10px 10px; /* 위, 양옆, 아래 안쪽여백 설정 */
			 margin: 0 auto; /* 가운데 정렬을 위해 좌우여백을 auto로 설정 */
		}	
		/* class='list_type' ul부모태그 내부에 만들어진 모든 li태그를 선택해서 스타일  */
		.list_type li{
			overflow: hidden; /*내용이 넘칠 경우 가려지는 부분을 숨깁니다.*/
			clear:both; /*왼쪽과 오른쪽의 부유(floating)를 해제 합니다.*/
			margin: 10px 0 0; /*위쪽 여백 10px, 아래쪽여백 0*/
			color:#2d2c2d; /*글자 색상 설정*/
			font-family: '돋움', Dotum; /*폰트 설정*/
			font-size: 12px; /*글자 크기 설정*/
			line-height: 100px; /*행 높이 설정*/
			list-style: none; /*리스트 스타일 제거*/
			border-bottom: 2px solid lightgray; /*하단에 회색 경계선을 2xp 두께로 설정 */
			position: relative; /* 상대적인 위치 설정을 위해 position속성 사용 */
		}
		
		/* class="list_type"인  ul태그 내부에 만든 자식 li태그내부에 만은 img태그를 최종선택해서 스타일 */
		.list_type li img {
			display: inline; /*이미지를 인라인 으로 배치 합니다.*/
			float: left; /*왼쪽으로 부유(floating)시킵니다.*/
			position: absolute; /*이미지의 위치를 절대적으로 설정합니다.*/
		}
		/* ul태그 내부에 만들어 놓은 모든 자식 li태그내부에 만들어 놓은  a태그들을 최종선택해서 스타일*/
		.list_type li a{
			color: #2d2c2d; /*링크의 글자 색상 설정*/
			text-decoration: none; /*링크의 밑줄 스타일을 없앱니다.*/
			margin-left: 340px; /*왼쪽 여백을 340px로설정하여 이미지와 텍스트 간의 간격을 띄워 조절하자*/
		}
		/* 모든 a태그를 선택해  hover스타일 적용 */
		.list_type li a:hover {
			/*링크에 마우스를 올렸을때 밑줄을 다시 표시 합니다.*/
			text-decoration: underline;
		}
		
		/* 모든 span을 선택해 스타일 */
		.list_type li span{
			color:blue; /*글자 색상 파란색으로 설정*/
			margin-left: 330px; /*왼쪽 여백을 330px로 설정하여 이미지와 텍스트 간의 간격을 조절하자*/
			font-size: 14px; /*글자 크기 14px로 설정*/
		}
		
	
	</style>

</head>
<body>
	<ul class="list_type">
		<%-- 리스트의 헤더 영역을 표시 --%>
		<li>
			<span style="margin-left: 50px">이미지</span>
			<span>이미지 이름</span>
			<span>선택하기</span>
		</li>
		<%-- for반복문을 이용해 <li>태그를 연속해서 출력합니다. --%>
<%
	for(int i=0; i<10; i++){
		//<li>태그를 이용해 한 행에 <a>태그의 이미지와 텍스트를 나타냅니다. 그리고 마지막<a>는  체크박스를 나타냅니다.
%>		
		<li>
			<a href="#" style="margin-left: 50px" ><img src="image/duke.png" width="90" height="90"></a>
			<a href="#"><strong>이미지 이름 : 듀크<%=i%></strong></a>		
			<a href="#"><input type="checkbox" name="chk<%=i%>"></a>
		</li>
<%		
	}
%>	
	</ul>

</body>
</html>




