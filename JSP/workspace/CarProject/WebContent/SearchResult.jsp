<%@page import="org.json.simple.JSONArray"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="org.json.simple.parser.JSONParser"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>     
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>   
 
<%
	request.setCharacterEncoding("UTF-8");
	
	//request내장객체에 바인딩된  조회된 정보(JSON형태의 문자열)꺼내오기
	String data = (String)request.getAttribute("searchData");

	//out.print(data);
	
	
//사용라이브러리 ->  json-simple-1.1.1.jar
	//JSON형태의 문자열을 -> JSONObject객체로 변환
	//"{            }" ->  {             } 로 변환
	
	//순서1. JSONParser클래스의 객체 생성
	JSONParser jsonParser = new JSONParser();
	
	//순서2. JSONParser객체의 parse메소드 호출시 매개변수로 문자열형태의 JSON을 전달하면
	//      실제 JSONObject객체로 변환해서 반환해줍니다.
	JSONObject  jsonObject = (JSONObject)jsonParser.parse(data);
	
	JSONArray	jsonArray  = (JSONArray)jsonObject.get("items");
%>
	<hr width="100%" color="red">

	<table width="100%" border="1">
		<tr style="background-color:aqua;">
			<td align="center">제목</td>
			<td align="center">요약내용</td>
			<td align="center">블로거명</td>
			<td align="center">작성날짜</td>
			<td align="center">링크</td>		
		</tr>
<% 
	for(int i=0;  i<jsonArray.size();  i++){
		
	     JSONObject object  = (JSONObject)jsonArray.get(i);
/*	     
	      {
		         "title":"신차장기<b>렌트카<\/b> 견적비교 및 장단점 정리!",
		         "link":"https:\/\/blog.naver.com\/ige1026\/223620944462",
		         "description":"신차장기<b>렌트카<\/b> 견적비교 및 장단점 정리! 10월의 두 번째 주말 편하게 보내셨나요? 저는 1년만에 KTX... 신차 장기<b>렌트카<\/b>란? 사촌이 장기렌탈 관련 회사에 있어서 알려줬는데, 예전에는 단기 렌트 이용자수가... ",
		         "bloggername":"우리의 블로그",
		         "bloggerlink":"blog.naver.com\/ige1026",
		         "postdate":"20241016"
	      }
*/
%>
		<tr>
			<td align="center"><%=object.get("title")%></td>
			<td align="center"><%=object.get("description")%></td>
			<td align="center"><%=object.get("bloggername")%></td>
			<td align="center"><%=object.get("postdate")%></td>
			<td align="center">
				<a href="<%=object.get("link")%>">바로가기</a>
			</td>		
		</tr>		
		
<%		
	}//for반복문 
%>			
	</table>
<%	
	
	/*			
	{
		   "lastBuildDate":"Mon, 28 Oct 2024 17:46:13 +0900",
		   "total":2228167,
		   "start":1,
		   "display":10,
		   "items":[
		      {
		         "title":"신차장기<b>렌트카<\/b> 견적비교 및 장단점 정리!",
		         "link":"https:\/\/blog.naver.com\/ige1026\/223620944462",
		         "description":"신차장기<b>렌트카<\/b> 견적비교 및 장단점 정리! 10월의 두 번째 주말 편하게 보내셨나요? 저는 1년만에 KTX... 신차 장기<b>렌트카<\/b>란? 사촌이 장기렌탈 관련 회사에 있어서 알려줬는데, 예전에는 단기 렌트 이용자수가... ",
		         "bloggername":"우리의 블로그",
		         "bloggerlink":"blog.naver.com\/ige1026",
		         "postdate":"20241016"
		      },
		      {
		         "title":"법인 장기<b>렌트카<\/b> 혜택 및 비용까지",
		         "link":"https:\/\/blog.naver.com\/hfg574\/223609787428",
		         "description":"법인 장기<b>렌트카<\/b> 혜택 및 비용까지 최근에는 차량을 구매할 때 할부 대신 렌트를 선택하는 사람들이... 초기 비용을 알아보는 과정에서는, <b>렌터카<\/b>의 선수금과 보증금이 주요 요소로 작용했었어요. 선수금은 계약... ",
		         "bloggername":"취미가 공부",
		         "bloggerlink":"blog.naver.com\/hfg574",
		         "postdate":"20241007"
		      },
		      {
		         "title":"장기<b>렌트카<\/b> 장단점 한번 살펴보시고 렌트 결정하세요",
		         "link":"https:\/\/blog.naver.com\/eotjd350\/223622197988",
		         "description":"장기<b>렌트카<\/b> 장단점 한번 살펴보시고 렌트 결정하세요 지난 주말, 신규 준비한 자동차의 연비도... ▶▶ 장기<b>렌트카<\/b> 장단점 관련된 내용 자세하게 알아보세요! ◀◀ 클릭 1. 장기<b>렌트카<\/b> 구매 조건 장기렌트를... ",
		         "bloggername":"레티.",
		         "bloggerlink":"blog.naver.com\/eotjd350",
		         "postdate":"20241017"
		      },
		      {
		         "title":"아이랑 여수 <b>렌트카<\/b> 여행 여수공항 <b>렌터카<\/b> 카모아",
		         "link":"https:\/\/blog.naver.com\/tjwlsska\/223633416773",
		         "description":"아이랑 여수 <b>렌트카<\/b> 여행 여수공항 <b>렌터카<\/b> 카모아 안녕하세요. 제주린아입니다. 지난주 제주도에서 출발해 여수 여행을 다녀왔어요. 제주에서 여행을 갈 때는 항상 항공권, 숙소 그리고 <b>렌트카<\/b>를 1순위로... ",
		         "bloggername":"제주린아의 제주스러운 이야기",
		         "bloggerlink":"blog.naver.com\/tjwlsska",
		         "postdate":"20241025"
		      },
		      {
		         "title":"카니발 장기<b>렌트카<\/b> 장점 및 견적 정보 총정리!",
		         "link":"https:\/\/blog.naver.com\/yunmarux\/223624899619",
		         "description":"카니발 장기<b>렌트카<\/b> 장점 및 견적 정보 총정리! 안녕하세요, 얼마 전 저는 가족들과 함께 제부도로 2박... 카니발 장기<b>렌트카<\/b> 조건 알아보기 저는 올해 3월에 차량을 출고했는데요, 차를 선택할 때 몇 가지 중요한... ",
		         "bloggername":"윤마루의 블로그",
		         "bloggerlink":"blog.naver.com\/yunmarux",
		         "postdate":"20241019"
		      },
		      {
		         "title":"태국 치앙마이 <b>렌트카<\/b> 프라이빗 가족여행 투어",
		         "link":"https:\/\/blog.naver.com\/juji0303\/223628735088",
		         "description":"치앙마이 <b>렌트카<\/b> 여행을 했는데요 프라이빗 투어로 기사님이 운전까지 해주는 곳이 있어서 6시간으로... 만약 내가 시간이 좀 애매하게 남아서 치앙마이 <b>렌트카<\/b>를 조금 더 쓰고 싶다고 하면, 시간별로 10불... ",
		         "bloggername":"사랑빔",
		         "bloggerlink":"blog.naver.com\/juji0303",
		         "postdate":"20241022"
		      },
		      {
		         "title":"장기<b>렌트카<\/b> 가격비교 신차출고 꿀팁정리!",
		         "link":"https:\/\/blog.naver.com\/secretauto\/223594818593",
		         "description":"장기<b>렌트카<\/b> 가격비교 신차출고 꿀팁정리! 9월이 끝자락에 다다랐어요. 추석 연휴가 지나면서 갑작스레... 대표적인 방법이 장기<b>렌터카<\/b>였어요. 장기<b>렌터카<\/b>는 원래 사업자들이 절세를 하며 신차를 사용하는... ",
		         "bloggername":"신차드림",
		         "bloggerlink":"blog.naver.com\/secretauto",
		         "postdate":"20240924"
		      },
		      {
		         "title":"베트남 다낭 자유여행 호이안 올드타운 등 다낭 <b>렌트카<\/b> 추천",
		         "link":"https:\/\/blog.naver.com\/kkulee\/223626410031",
		         "description":"하루는 다낭 <b>렌트카<\/b> 타고 호이안 올드타운까지 다녀왔는데 진짜 완전 편하고 좋았습니다. 항상... 다낭 <b>렌트카<\/b> 예약 https:\/\/www.vetnamplay.com\/S0334393#none 베트남 <b>렌트카<\/b>는 국내처럼 직접 운전하는 게 아니라 베테랑... ",
		         "bloggername":"Paradise is where I am.",
		         "bloggerlink":"blog.naver.com\/kkulee",
		         "postdate":"20241020"
		      },
		      {
		         "title":"다낭 <b>렌트카<\/b> 16인승 대형버스 원가로 빌리자",
		         "link":"https:\/\/blog.naver.com\/narsyus\/223627134465",
		         "description":"다낭 <b>렌트카<\/b> 16인승 대형버스 원가로 빌리자 가족들끼리 다낭 여행을 가려고 하니 이동 수단이 걱정이... 다낭 <b>렌트카<\/b> 16인승을 원가 가격으로 저렴하게 대여해 주던 곳으로 맘에 쏙 들어 소개할까 합니다.... ",
		         "bloggername":"리아의 세상사는 이야기",
		         "bloggerlink":"blog.naver.com\/narsyus",
		         "postdate":"20241021"
		      },
		      {
		         "title":"장기<b>렌트카<\/b>가격비교 한눈에 알아보고 렌트신청하세요",
		         "link":"https:\/\/blog.naver.com\/sweetmamang\/223628560865",
		         "description":"장기<b>렌트카<\/b>가격비교 한눈에 알아보고 렌트신청하세요 장기임대차량 가격대조 새차출고 꿀팁요약! ▶▶ 장기<b>렌트카<\/b>가격비교 관련정보 자세히 확인하기 ◀◀ 클릭 9월이 막바지에 이르렀습니다. 추석 연휴가... ",
		         "bloggername":"달콤스윗맘SweetMom’ SweetDay",
		         "bloggerlink":"blog.naver.com\/sweetmamang",
		         "postdate":"20241022"
		      }
		   ]
		}
*/				
	
	
	
%>
 
 
 
 
 
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

</body>
</html>