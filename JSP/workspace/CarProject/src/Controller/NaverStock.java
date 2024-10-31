package Controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

@WebServlet("/stock.do")
public class NaverStock extends HttpServlet{
	
	protected void doHandle(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		// 웹 크롤링(페이지 전체 정보를 가져온다.)할 요청할 전체 주소 저장
		String URL = "https://finance.naver.com/item/main.naver?code=005930";
		
		Document doc; // 웹크롤링해서 저장할 변수
		
		
		try {
			
			// 웹 크롤링할 전체 URL 주소의 전체 코드를 얻어 온다
			doc = Jsoup.connect(URL).get();
			
			// 실시간 주식 정보가 보이는~ 날짜 시간 추출
			// Document 객체의 select 메소드를 통해서 특정 요소들을 담은 Elements 배열 객체를 반환
			Elements elem = doc.select(".date");
			/*
				<em class="date">2024.10.31 14:36 <span>기준(장중)</span></em>
				<span class="date">(2024.06)</span>
				<span class="date">(2024.06)</span>
			*/
			
			// System.out.println(elem.text());
			// 2024.10.31 14:41 기준(장중) (2024.06) (2024.06)

			String[] str = elem.text().split(" ");
			// ["2024.10.31", "14:41", "기준(장중)", "(2024.06)", "(2024.06)"]
			//        0          1          2             3            4          index
			
			for(int i=0; i<str.length; i++) {
				System.out.println(str[i]);
			/*
			 	2024.10.31
			  	14:46
				기준(장중)
				(2024.06)
				(2024.06)

			 */
			}

			System.out.println("---------------------------------");
			//--------------------------------------------------------------------------------------
			
			Elements todaylist = doc.select(".new_totalinfo dl>dd");
			System.out.println(todaylist);
		/*
			index		
			  0		<dd>2024년 10월 31일 14시 57분 기준 장중</dd>
			  1		<dd>종목명 삼성전자</dd>
			  2		<dd>종목코드 005930 코스피</dd>
			  3		<dd>현재가 59,600 전일대비 상승 500 플러스 0.85 퍼센트</dd>
			  4		<dd>전일가 59,100</dd>
			  5		<dd>시가 58,500</dd>
			  6		<dd>고가 61,200</dd>
			  7		<dd>상한가 76,800</dd>
			  8		<dd>저가 58,300</dd>
			  9		<dd>하한가 41,400</dd>
			 10		<dd>거래량 29,072,599</dd>
			 11		<dd>거래대금 1,742,658백만</dd>
			  .		.
			  .		.
			  .		.
					<dd>매출액-매출원가-판관비</dd>
					<dd>지배회상의 지분에 귀속되는 당기순이익</dd>
					<dd>종속회사의 지분에 귀속되는 당기순이익</dd>
					<dd>조정영업이익/매출액</dd>
					<dd>지배주주지분 당기순이익 / (자본총계 - 비지배주주지분)</dd>
					<dd>지배주주지분 당기순이익/지배주주 평균발행주식수(우선주+보통주)</dd>
		*/

			System.out.println("---------------------------------");
			
			
			System.out.println(todaylist.get(3).text());
			// 현재가 59,500 전일대비 상승 400 플러스 0.68 퍼센트
			System.out.println("---------------------------------");
			
			String juga = todaylist.get(3).text().split(" ")[1];
			System.out.println(juga);
			// 59,500  -->(실시간)
			System.out.println("---------------------------------");
			
			String DungRakrate = todaylist.get(3).text().split(" ")[6];
			System.out.println(DungRakrate);
			// 0.68  -->(실시간)
			System.out.println("---------------------------------");
			
			String siga = todaylist.get(5).text().split(" ")[1];
			System.out.println(siga);
			// 58,500  -->(실시간)
			System.out.println("---------------------------------");
			
			String goga = todaylist.get(6).text().split(" ")[1];
			System.out.println(goga);
			// 61,200  -->(실시간)
			System.out.println("---------------------------------");
			
			String zeoga = todaylist.get(8).text().split(" ")[1];
			System.out.println(zeoga);
			// 58,300  -->(실시간)
			System.out.println("---------------------------------");
			
			String georaeryang = todaylist.get(10).text().split(" ")[1];
			System.out.println(georaeryang);
			// 30,324,981  -->(실시간)
			System.out.println("---------------------------------");
			
			String stype = todaylist.get(3).text().split(" ")[3];
			System.out.println(stype);
			// 상승  -->(실시간)
			System.out.println("---------------------------------");
			
			String vsyesterday = todaylist.get(3).text().split(" ")[4];
			System.out.println(vsyesterday);
			// 700  -->(실시간)
			System.out.println("---------------------------------");
			
			System.out.println("삼성전자 주가-----------");
			System.out.println("주가 :" + juga);
			System.out.println("등락률 :" + DungRakrate);
			System.out.println("시가 : " + siga);
			System.out.println("고가 : " + goga);
			System.out.println("저가 : " + zeoga);
			System.out.println("거래량 : " + georaeryang);
			System.out.println("타입 : " + stype);
			System.out.println("전일대비 : " + vsyesterday);
			// System.out.println("가져오는시간 : " + vsyesterday);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		doHandle(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		doHandle(request, response);
	}
	
}
