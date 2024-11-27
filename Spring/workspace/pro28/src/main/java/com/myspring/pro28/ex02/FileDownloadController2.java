package com.myspring.pro28.ex02;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import net.coobird.thumbnailator.Thumbnails;



@Controller
public class FileDownloadController2 {


	//다운로드할 파일 위치를 지정 합니다.
	private static final String CURR_IMAGE_REPO_PATH = "c:\\spring\\image_repo";

	
	@RequestMapping("/download")           //다운로드할 이미지 파일 명을 매개변수로 받는다.
	public void download(@RequestParam("imageFileName") String imageFileName,
						 HttpServletResponse response) throws Exception{
		
		//웹브라우저와 연결된 출력 스트림 통로 생성
		OutputStream out = response.getOutputStream();
		
		//매개변수로 전달 받은 다운로드할 파일명과 위 상수에 저장된 경로를 조합해서 경로를 문자열로 만들어 준다
		//다운로드할 파일위치의 파일 경로 만들기
		String filePath = CURR_IMAGE_REPO_PATH + "\\" + imageFileName;
		
		//다운로드할 파일에 접근해서 파일을 조작, 정보보기 등을 할수 있는 File객체 생성
		File image = new File(filePath);
		
		
		//확장자를 제외한 원본 이미지 파일의 이름을 가져옵니다
		//a.png -> a
		int lastIndex = imageFileName.lastIndexOf("."); 
		String fileName = imageFileName.substring(0, lastIndex);
		
		//원본 이미지 파일 이름과  같은 이름의 썸네일 파일에 접근하기 위한 File객체를 생성합니다.
		File thumbnail = 
		new File(CURR_IMAGE_REPO_PATH + "\\" + "thumbnail"+ "\\" + fileName + ".png");
		
		//원본 이미지 파일을 가로 세로가 50픽셀인 png형식의 썸네일 이미지 파일로 생성합니다
		if(image.exists()) {
			
			thumbnail.getParentFile().mkdirs();
			
			Thumbnails.of(image).size(50, 50).outputFormat("png").toFile(thumbnail);
/*
Thumbnails클래스의 메소드
 
 of 메소드를 사용하여 썸네일을 만들 원본이미지 파일을 지정합니다.
 size 메소드를 사용하여 썸네일 크기를 지정합니다.
 outputFormat 메소드를 사용하여 썸네일 이미지 파일 형식을 지정합니다.
 toFile 메소드를 사용하여 썸네일을 지정한 경로에 파일로 저장 합니다.
 
 그밖의 메소드들
 rotate 메소드 : 썸네일 이미지를 만들때 이미지를 회전 해서 만들기 위해 각도 설정
   예)  Thumbnails.of(image).size(50, 50).rotate(90)   //이미지를 90도 회전
 
 scale 메소드 : 썸네일 이미지를 백분율 단위로 크기 설정
  예)  Thumbnails.of(image).size(50, 50).rotate(90).scale(0.5) //이미지 크기를 50%로 조절
  
 watermark 메소드 : 워터마크 이미지와 투명도를 설정할수 있습니다
 예)   
 	BufferedImage bi = ImageIo.read(new File("a.png"));
 
 	   Thumbnails.of(image).size(50, 50)
 	   .watermark( Positions.BOTTOM_RIGHT,  bi , 0.5f );
	
public Thumbnails watermark(Positions position,BufferedImage watermark,float opacity);
			 
참고.
	 position매개변수는 워터마크 위치를 지정하는 Positions클래스의 상수를 전달 합니다
	 - TOP_LEFT
	 - TOP_CNETER
	 - TOP_RIGHT
	 - CENTER_LEFT
	 - CENTER
	 - CENTER_RIGHT
	 - BOTTOM_LEFT
	 - BOTTOM_CENTER
	 - BOTTOM_RIGHT
			 
	 watermark매개변수는 워터마크 이미지를 나타내는 	BufferedImage객체를 매개변수로 전달합니다.
	 
	 opacity매개변수는 워터마크 투명도를 나타내는 값을 전달합니다. 값의 범위는 0.0 ~ 1.0 사이입니다. 		
			 
			 
			 */
			
			
			
		}
			
		
		/*
		 response.setHeader("Cache-Control", "no-cache")는 
		 HTTP 응답 헤더 중 하나인 Cache-Control 헤더를 설정하는 코드입니다.
		 Cache-Control 헤더는 HTTP 응답에 대한 캐시 동작을 지정하는데 사용됩니다. 
		 no-cache 값은 캐시를 사용하지 않도록 지시하는 지시자입니다. 
		 이렇게 설정하면 브라우저는 캐시된 데이터를 사용하지 않고 서버에서 데이터를 항상 새로 요청하게 됩니다.
	         이 헤더는 주로 동적으로 생성되는 컨텐츠에 대해서 캐싱을 방지하기 위해 사용됩니다. 
	         
	         예를 들어, 사용자의 로그인 상태나, 데이터의 변경 가능성이 높은 페이지의 경우 
	         캐싱을 허용하지 않는 것이 안정적인 서비스를 제공할 수 있습니다.
		 따라서 response.setHeader("Cache-Control", "no-cache") 코드는 
		 HTTP 응답에 대한 캐시를 방지하기 위해 사용하는 것입니다. 
		 이 코드를 사용하여 웹 애플리케이션에서 캐시를 방지할 수 있으며, 항상 최신 데이터를 보여줄 수 있습니다.
		 */
		response.setHeader("Cache-Control", "no-cache");
		
		//다운로드할 파일명을 response객체에 설정
		//Content-disposition 속성은  HTTP응답에 대한 컨텐츠를 
		//어떻게 처리할지를 지정하는데 사용됩니다.
		//attachment 를 설정하면 브라우저는 해당 컨텐츠를 다운로드 할수 있도록 하며,
		//fileName 속성에 대한 값을 추가하여 다운로드 받을 파일의 이름을 지정할수 있습니다. 
		//이코드를 사용하여  이미지 파일을 웹브라우저로 다운로드 시켜 바로 보여 줄수 있습니다.!!!!!!!!
		response.addHeader("Content-disposition", "attachment; fileName=" + imageFileName);
		
		
		//다운로드할  썸네일 이미지 파일을 바이트 데이터 단위로 읽어들일 입력 스트림 통로 생성
		FileInputStream in = new FileInputStream(thumbnail);
		
		
		/*
		데이터 단위는 컴퓨터에서 데이터의 크기나 양을 표현하는 단위를 말합니다. 다음은 대표적인 데이터 단위입니다.

		비트(bit): 0과 1로 이루어진 이진수의 가장 작은 단위. 8비트가 모이면 1바이트가 됩니다.
		바이트(byte): 8비트를 모아 만든 데이터 단위. 대부분의 컴퓨터에서는 1바이트가 최소 단위입니다.
		킬로바이트(KB): 1024바이트를 나타내는 데이터 단위.
		메가바이트(MB): 1024킬로바이트(1,048,576바이트)를 나타내는 데이터 단위.
		기가바이트(GB): 1024메가바이트(1,073,741,824바이트)를 나타내는 데이터 단위.
		테라바이트(TB): 1024기가바이트(1,099,511,627,776바이트)를 나타내는 데이터 단위.
		이외에도 데이터 전송 속도를 나타내는 비트레이트(bps)나 전송량을 나타내는 패킷(packet) 등 
		다양한 데이터 단위가 있습니다.
		 */
						                    //1킬로바이트(1 KB): 1024바이트를 나타내는 데이터 단위.
		byte[] buffer = new byte[1024 * 8]; //1024byte *  8 =   8KB
		
		
		while(true) {//무한 참!
			
			//FileInputStream통로를 통해 다운로드할 파일에 접근하여 위 8kb바이트씩 읽어서
			//count변수로 받는다.  못읽어 들이면 read메소드는 -1 을 반환 한다.
			
//			read(배열) 메소드는 매개변수로 지정된 바이트 배열에 데이터를 읽어들입니다. 
//			읽어들인 데이터의 크기를 반환하며, 
//			읽어들일 데이터가 없으면 -1을 반환합니다.			
			int count = in.read(buffer);
			
			if(count == -1) {//읽어 들일 파일의 내용이 없으면 break를 써서 while을 빠져나감
				break;
			}
			
			//출력 스트림 통로를 통해 한번에 8kb 씩 브라우저로 전송하여 다운로드 시킵니다.
			out.write(buffer, 0, count);
			/*
			 참고.
			 	OutPutStream클래스의   void write(bytye[] b, int off, int len) throw IOException
			 	
			 		매개변수 b  : 출력할 데이터가 저장된 바이트 배열 전체를 전달
			 		매개변수 off : 출력을 시작할 	바이트 배열의 시작 인덱스 입니다.
			 		매개변수 len : 출력할 데이터의 길이 입니다. 
			 
			 */	
		}//while 문 끝
		
		//자원해제
		in.close();
		out.close();
												  
		
	}	
	
}





