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
	protected void service(HttpServletRequest req, 
						   HttpServletResponse resp) throws ServletException, IOException {
		
	   //1.인증정보 설정
	   String clientId = "9Mzk_J1lHN9Ix7MuBAJW"; //애플리케이션 클라이언트 아이디
	   String clientSecret = "dPVv6ThLhR"; //애플리케이션 클라이언트 시크릿
		
		
	   //2.검색 조건 설정
	   int startNum = 0;  //검색 시작 위치
	   String text = null;//입력한 검색어 
	   
       try {
    	   //검색시작 위치 받아오기
    	   startNum = Integer.parseInt(req.getParameter("startNum"));
    	   
    	   //디자인 검색요청화면에서 입력한 검색어 얻기
    	   String searchText = req.getParameter("keyword");

           text = URLEncoder.encode(searchText, "UTF-8");
                    
       } catch (UnsupportedEncodingException e) {
           throw new RuntimeException("검색어 인코딩 실패",e);
       }
	   
	   //3. API 요청 주소 조합
       //검색결과 데이터를 JSON으로 받기 위한 API입니다.
       //검색어를 쿼리스트링으로 보내는데 여기에는 display와 start매개변수도 추가했습니다.
       //display속성은 한번에 가져올 검색 결과의 갯수이며,
       //start속성은 검색 시작 위치 입니다.
       String apiURL = "https://openapi.naver.com/v1/search/blog?query=" + text
    		         + "&display=10&start="+startNum; //JSON 데이터응답받기 위한
       												  //네이버 서버에 요청할 주소 
       //4. API 호출
       
       Map<String, String> requestHeaders = new HashMap<>();
       
       //클라이언트의 클라이언트 ID와  시크릿을 Http프로토콜로 요청할때
       //Http요청메세지의 HEADER영역에 설정
       requestHeaders.put("X-Naver-Client-Id", clientId);
       requestHeaders.put("X-Naver-Client-Secret", clientSecret);
       
       //API를 호출해서 JSON데이터를 문자열 형태로 응답 받는다.
       String responseBody = get(apiURL,requestHeaders);

       System.out.println(responseBody);
       
        
       //웹브라우저로 응답할 데이터 유형 설정
       resp.setContentType("text/html; charset=utf-8");
       
       //PrintWriter객체 얻어 검색결과 보냄
       resp.getWriter().write(responseBody);
       
       
       
      // {	"lastBuildDate":"Mon, 28 Oct 2024 16:56:41 +0900",	"total":85753583,	"start":1,	"display":10,	"items":[		{			"title":"마곡 <b>맛집<\/b> 수율이 일품인 명량간장게장",			"link":"https:\/\/blog.naver.com\/jung4553\/223616253840",			"description":"며칠 전 가족들과 함께 마곡에서 볼 일을 보고 꼭 방문해야한다는 마곡 <b>맛집<\/b>을 들려보았어요. 오랫동안... 마곡 <b>맛집<\/b> 2층으로 올라가니 명량이라는 빨간 상호 글씨가 한 눈에 보였어요. 외부만 보아도 맛있을 것... ",			"bloggername":"SO FINE",			"bloggerlink":"blog.naver.com\/jung4553",			"postdate":"20241012"		},		{			"title":"1등 가성비 제주 소고기 <b>맛집<\/b>",			"link":"https:\/\/blog.naver.com\/haheehooheho_\/223619859438",			"description":"친구 찬스로 소개 받았던 제주 소고기 <b>맛집<\/b>에서의 고기 맛이 끝내줬던 곳이기에 소개해 드리려 합니다. 수 많은 제주의 <b>맛집<\/b> 중에서도 단연코 1등이라 자랑할 만한 곳이었으니 따라오시죠. #제주도부명 1.... ",			"bloggername":"하히후헤호",			"bloggerlink":"blog.naver.com\/haheehooheho_",			"postdate":"20241015"		},		{			"title":"중문 갈치조림 <b>맛집<\/b> 가성비 좋은 식당",			"link":"https:\/\/blog.naver.com\/cbn9792\/223594613295",			"description":"여행에서 어른들을 많이 모시고 갔는데 다들 흡족해하셔서 뿌듯했던 기억에 중문 갈치조림 <b>맛집<\/b>... 중문 갈치조림 <b>맛집<\/b>의 메뉴는 미리 공부하고 갔던 그 대로 나와있었어요. 저희는 조림도 구이도 포기할 수... ",			"bloggername":"꼬맹이 점빵~*",			"bloggerlink":"blog.naver.com\/cbn9792",			"postdate":"20240924"		},		{			"title":"서울 마곡 <b>맛집<\/b> 곱창의 신세계야",			"link":"https:\/\/blog.naver.com\/hohobaby\/223628593277",			"description":"서울마곡<b>맛집<\/b> 곱창은 세광양대창이지~ 제가 강서로 이사온지도 벌써 15년이 훌쩍 넘었어요 저랑은 어떤... 덕분에 <b>맛집<\/b>도 많이 생기고, 전 나날이 푸짐해지고 ㅋ 서울 마곡 <b>맛집<\/b> 세광양대창 , 크~ 여기도 제... ",			"bloggername":"하루하루",			"bloggerlink":"blog.naver.com\/hohobaby",			"postdate":"20241022"		},		{			"title":"대구 제일 판초밥 <b>맛집<\/b> 수성구 후꾸스시",			"link":"https:\/\/blog.naver.com\/gns4221\/223635871811",			"description":"스시 코우지의 코우지 쉐프가 다녀간 대구 제일 판초밥 <b>맛집<\/b> 수성구 후꾸스시에 드디어 방문했습니다.... 캐치테이블로 줄서기할 수 있으니까 대구 제일 판초밥 <b>맛집<\/b> 수성구 후꾸스시 방문하실 분들은 11... ",			"bloggername":"Dean's blog",			"bloggerlink":"blog.naver.com\/gns4221",			"postdate":"20241027"		},		{			"title":"충남 예산 <b>맛집<\/b> 사과당 디져트 후기~!",			"link":"https:\/\/blog.naver.com\/trip2planet\/223627353650",			"description":"충남 예산 <b>맛집<\/b> 사과당 디져트 후기~! 충남 예산 <b>맛집<\/b> 사과당 디져트 후기~! 충남 예산 <b>맛집<\/b> 사과당 디져트 후기~! 예산 상설시장이 올해 10월 4일에 리모델링을 거쳐 새롭게 재개장했다고해서 방문하고 왔는데요.... ",			"bloggername":"지구별 백패커의 세상 여행 ✈",			"bloggerlink":"blog.naver.com\/trip2planet",			"postdate":"20241021"		},		{			"title":"남양주 별내 파스타 <b>맛집<\/b> 스테이564",			"link":"https:\/\/blog.naver.com\/goldjin__\/223620466399",			"description":"파스타 <b>맛집<\/b> 스테이564. 별내 주민분께서 추천해 주신 덕분에 아주아주 맛있는 점심 식사를 하고 돌아왔다. 집에 와서도 또 생각나는 이곳! 남양주 별내 파스타 <b>맛집<\/b> 스테이564 위치  경기도 남양주시 불암로 59... ",			"bloggername":"Simple Life♩",			"bloggerlink":"blog.naver.com\/goldjin__",			"postdate":"20241015"		},		{			"title":"매력넘치는 제주 애월 <b>맛집<\/b> 해오반",			"link":"https:\/\/blog.naver.com\/withth_1\/223597822197",			"description":"매력넘치는 제주 애월 <b>맛집<\/b> 해오반 가족들과 마지막 휴가를 즐기기 위해 제주도 여행을 다녀왔어요.... 식사시간이 되어 모두들 배가 고파서 근처 제주 애월 <b>맛집<\/b>으로 소문난 해오반을 방문하게 되었습니다.... ",			"bloggername":"위드태훈의 지구별트레킹",			"bloggerlink":"blog.naver.com\/withth_1",			"postdate":"20240927"		},		{			"title":"제주 전복솥밥 <b>맛집<\/b> 동쪽 함덕 연옥",			"link":"https:\/\/blog.naver.com\/redares1\/223632880909",			"description":"여행 중 폭풍검색끝에 고르고골랐던 제주 전복솥밥 <b>맛집<\/b>이 최고였는데요. 따끈한 돌솥밥 전문점이라... 제주 전복솥밥 <b>맛집<\/b>의 테이블 옆 서랍도 청결하게 관리되고 있었답니다. 메뉴판을 보니 세트와 단품으로... ",			"bloggername":"Life",			"bloggerlink":"blog.naver.com\/redares1",			"postdate":"20241025"		},		{			"title":"김포 브런치 <b>맛집<\/b> 마산동 야무진",			"link":"https:\/\/blog.naver.com\/xogusth1004\/223635669811",			"description":"김포 브런치 <b>맛집<\/b> 마산동 야무진 다녀왔어요 안녕하세요. 이번 포스팅은 짝꿍이 아주 좋아하는 메뉴들로 구성된 김포 브런치 <b>맛집<\/b>인데요~ 비슷한 이름의 장기동 카페가 있는데 지도에 체크해두었거든요? 같은... ",			"bloggername":"큐유`s world",			"bloggerlink":"blog.naver.com\/xogusth1004",			"postdate":"20241027"		}	]}

       
        
	}//service메소드 닫기 
	
	
    public static String get(String apiUrl, Map<String, String> requestHeaders){
        
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







