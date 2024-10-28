package API;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/NaverSearchAPI.do")
public class SearchAPI extends HttpServlet {
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {

		// 1. 인증 정보 설정
        String clientId = "aOjwAZYlib3IQVrzvNgZ"; //애플리케이션 클라이언트 아이디
        String clientSecret = "cuMccau7F_"; //애플리케이션 클라이언트 시크릿
		
        // 2. 검색 조건 설정
        int startNum = 0; // 검색 시작 위치
        String text = null; // 입력한 검색어가 저장되는 변수
        
        try {
        	// 검색 시작 위치 받아오기
        	startNum = Integer.parseInt(request.getParameter("startNum"));
        	
        	// 디자인 검색 요청 화면에서 입력한 검색어 얻기
        	String searchText = request.getParameter("keyword");
        	
        	text = URLEncoder.encode(searchText, "UTF-8");

            
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("검색어 인코딩 실패",e);
        }

        // 3. API 요청 주소 조합
        // 검색결과 데이터를 JSON 으로 받기 위한 API이다.
        // 검색어를 쿼리 스트링으로 보내는데 여기에는 display와 start 매개변수도 추가했다.
        // display 속성은 한번에 가져올 검색 결과의 갯수이며,
        // start 속성은 검색 시작 위치이다.									검색어가 들어갈 자리
        String apiURL = "https://openapi.naver.com/v1/search/blog?query=" + text
        		+ "&display=10&start=" + startNum;    // JSON 데이터응답받기 위한 네이버 서버에 요청할 주소
        //String apiURL = "https://openapi.naver.com/v1/search/blog.xml?query="+ text; // XML 결과

        // 4. API 호출
        
        Map<String, String> requestHeaders = new HashMap<>();
        
        // 클라이언트의 클라이언트 ID와 시크릿을 Http프로토콜 요청할 때
        // Http 요청 메세지의 HEADER 영역에 설정
        requestHeaders.put("X-Naver-Client-Id", clientId);
        requestHeaders.put("X-Naver-Client-Secret", clientSecret);
        
        // API 를 호출해서 JSON 데이터 문자열 형태로 응답 받는다.
        String responseBody = get(apiURL,requestHeaders);

        // 웹 브라우저로 응답할 데이터 유형 서정
        response.setContentType("application/json; charset=utf-8");

        
        // PrinterWriter 객체 얻어 검색 결과 보냄
        response.getWriter().write(responseBody);
        
        System.out.println(responseBody);
        
        
	} // service 메소드 닫기
	
	private static String get(String apiUrl, Map<String, String> requestHeaders){
		HttpURLConnection con = connect(apiUrl);
	    try {
	    	
	        con.setRequestMethod("GET");
	        
	        for(Map.Entry<String, String> header :requestHeaders.entrySet()) {
	            con.setRequestProperty(header.getKey(), header.getValue());
	        }

	        int responseCode = con.getResponseCode();
	        
	        if (responseCode == HttpURLConnection.HTTP_OK) { // 정상 호출
	            return readBody(con.getInputStream());
	        } else { // 오류 발생
	            return readBody(con.getErrorStream());
	        }
	        
	    } catch (IOException e) {
	        throw new RuntimeException("API 요청과 응답 실패", e);
	    } finally {
	        con.disconnect();
	    }
	}


	private static HttpURLConnection connect(String apiUrl){
	    try {
	    	
	        URL url = new URL(apiUrl);
	        
	        return (HttpURLConnection)url.openConnection();
	        
	    } catch (MalformedURLException e) {
	        throw new RuntimeException("API URL이 잘못되었습니다. : " + apiUrl, e);
	    } catch (IOException e) {
	        throw new RuntimeException("연결이 실패했습니다. : " + apiUrl, e);
	    }
	}


	private static String readBody(InputStream body){
		
	    InputStreamReader streamReader = new InputStreamReader(body);

	    try (BufferedReader lineReader = new BufferedReader(streamReader)) {
	    	
	        StringBuilder responseBody = new StringBuilder();

	        String line;
	        
	        while ((line = lineReader.readLine()) != null) {
	            responseBody.append(line);
	        }

	        return responseBody.toString();
	         
	    } catch (IOException e) {
	        throw new RuntimeException("API 응답을 읽는 데 실패했습니다.", e);
	    }
	}
	
	
}
