package Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;

import Dao.FileBoardDAO;
import Dao.MemberDAO;
import Vo.FileBoardVo;
import Vo.MemberVo;

//단위 기능의 메소드들을 가지고 있는 클래스 
//부장
public class FileBoardService {

	FileBoardDAO fileboarddao;
	MemberDAO memberdao;
	
	public FileBoardService() {
		fileboarddao = new FileBoardDAO();
		memberdao = new MemberDAO();
	}

	//----------
	//회원 아이디를 매개변수로 받아서 회원 한명을 조회 후 반환하는 기능의 메소드
	public MemberVo serviceMemberOne(String memberid) {
		return memberdao.memberOne(memberid);
	}

	
	//----------
	//DB의 Fileboard테이블에 저장되어 있는 모든 글목록을 조회하는 기능의 메소드
	public ArrayList serviceBoardList() {
		
		return fileboarddao.boardListAll();
	}

	
	//----------
	//검색기준값과 입력한 검색어를 포함하고 있는 글목록을 조회하는 기능의 메소드
	public ArrayList serviceBoardKeyWord(String key, String word) {
		
		return fileboarddao.boardList(key,word);//BoardController사장에게 리턴(보고)
	}

	
	//----------
	//글번호(b_idx)를 이용해 조회 명령하는 기능의 메소드
	 public FileBoardVo serviceBoardRead(String b_idx) {
		 
		 return fileboarddao.boardRead(b_idx);
		 
	 }

	 
	//----------
	//글을 수정,삭제하기위한 버튼들을 활성화해서 보여주기 위해
	//입력한 글의 비밀번호가 DB에 있는지 체크하는 기능의 메소드 
	public boolean servicePassCheck(String b_idx, String password) {
		
		return fileboarddao.passCheck(b_idx, password);
	}


	//----------
	// 글 수정 요청 UPDATE 하기 위한 메소드 호출!
	public int serviceUpdateBoard(String idx_, String email_, 
								  String title_, String content_) {
		       //수정에 성공하면 1을 반환 실패하면 0을 반환
		return fileboarddao.updateBoard(idx_, email_, title_, content_);
	}

	
	//----------
	// 글 삭제 요청 DELETE 하기 위한 메소드 호출!
	public String serviceDeleteBoard(String delete_idx) {
		
			   //삭제에 성공하면 "삭제성공" 반환 실패하면 "삭제실패" 반환 
		return fileboarddao.deleteBoard(delete_idx);
	}

	
	//----------
	//DB의 Board테이블에 입력한 답변글 추가 하기 위해 호출!
	public void serviceReplyInsertBoard(String super_b_idx, String reply_id, 
										String reply_name, String reply_email,
										String reply_title, String reply_content, 
										String reply_pass) {
		
		fileboarddao.replyInsertBoard(super_b_idx,
									  reply_id,
									  reply_name,
									  reply_email,
									  reply_title,
									  reply_content,
									  reply_pass);
		
	}

	
	//----------(Pro15/src/FileUpload.java 참고)
	// "/writePro.bo" -> 부장의 메소드가 호출됨
	//멀티스레드 환경에서 안정성: 
	//서블릿은 여러 클라이언트 요청을 동시에 처리하기 때문에 동기화가 필요할 때가 있습니다. 
	//예를 들어, serviceInsertBoard 메소드가 특정 자원에 접근하고 수정하는 경우, 
	//synchronized를 통해 여러 요청 스레드(클라이언트)들이 동시에 실행되어 발생할 수 있는 충돌을 방지합니다.	
	public synchronized int serviceInsertBoard(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		// 파일 업로드 후~ 업로드한 파일명을 담고 있는 해쉬맵을 반환받아 저장
		Map<String, String> articleMap = upload(request, response);
		
		// 작성한 글 정보(업로드할 파일정보 포함)를 HashMap에서 꺼내오기
		String writer = articleMap.get("writer"); // 작성자
		String email = articleMap.get("email");// 이메일
		String title = articleMap.get("title");// 제목
		String content = articleMap.get("content");// 내용
		String pass = articleMap.get("pass");// 글 비밀번호
		String id = articleMap.get("writer_id");// 글 작성자 아이디
		String sfile = articleMap.get("fileName");// 글을 작성할 때 없로드 하기 위해 첨부한 파일명
		
		
		// 요청한 값들 FileBoardVo 객체 하나에 저장
		FileBoardVo vo = new FileBoardVo();
					vo.setB_name(writer);
					vo.setB_email(email);
					vo.setB_title(title);
					vo.setB_content(content);
					vo.setB_pw(pass);
					vo.setB_id(id);
					vo.setSfile(sfile);
		
		// 작성한 새 글 내용을 DB에 INSERT하고 INSERT 추가시킨 글의 글번호를 조회후 반환받게 명령
		int articleNO = fileboarddao.insertBoard(vo);
		
		// temp 폴더에 파일 업로드 후 해당 글번호 폴더로 업로드된 파일 이동에 대한 동기화 처리
		/*
		 참고.
			클라이언트가 첨부파일을 업로드후 파일 이동 요청할 때, 
			클라이언트의 요청은 서버에서 별도의 스레드로 처리됩니다. 
			일반적으로 웹 애플리케이션에서는 각 클라이언트의 요청이 독립적인 한명의 클라이언트 요청에 관한 스레드 에서 처리되어, 
			동시에 여러 클라이언트의 요청을 병렬로 처리할 수 있습니다.

			스레드 처리 방식
				클라이언트 요청: 클라이언트가 웹 서버에 파일 업로드 요청을 보내면, 
				                        웹 서버는 해당 요청을 처리하기 위해 새로운 스레드를 생성하거나 기존 스레드를 사용하여 요청을 처리합니다.

				서버의 스레드: 이 스레드는 클라이언트의 요청을 받아서 해당 메서드(예: serviceInsertBoard)를 실행하고, 
				                     이 메서드 내에서 synchronized(this) 블록이 포함된 경우, 
				                     현재 FileBoardService 인스턴스에 대한 접근이 동기화됩니다.

				동기화의 역할: 이렇게 동기화된 블록 안에서는 동시에 여러 스레드가 접근하지 못하도록 제한하므로, 
				                    파일 업로드와 같은 중요한 작업이 동시에 여러 스레드에서 실행될 경우 발생할 수 있는 데이터 손상이나 상태 불일치 문제를 예방할 수 있습니다.

				예시
				만약 두 명의 클라이언트가 동시에 temp폴더에 파일을 업로드후 파일 이동 하는 경우, 
				첫 번째 클라이언트의 요청이 synchronized(this) 블록을 실행 중일 때, 
				두 번째 클라이언트의 요청은 첫 번째 요청이 끝날 때까지 대기하게 됩니다.
				결론적으로, 클라이언트의 요청은 스레드로 처리되며, 
				synchronized(this)는 이러한 스레드가 공유 자원에 동시에 접근하는 것을 방지하는 역할을 합니다.
		*/	
		synchronized (this) {
			
			if(sfile != null && sfile.length() != 0) {
				// 업로드된 파일이 저장된 temp  폴더에 접근할 File 객체 생성
				File srcFile = new File("C:\\file_repo\\temp\\" + sfile);
				
				// temp 폴더에 저장된 업로드한 파일을 이동시킬 글번호 폴더에 접근할 File 객체생성
				File destDir = new File("C:\\file_repo\\" + articleNO);
				
				// DB에 추가한 글에 대한 글번호르 조회해서 가져왔기 때문에
				// 조회해서 가져온 글번호로 글번호 폴더를 생성한다.
				if(!destDir.exists()) {
					destDir.mkdirs(); // 글번호 폴더가 존재하지 않으면 생성
				}
				
				
				
				
				// temp 폴더에 업로드된 파일을 글번호 폴더로 이동
				FileUtils.moveFileToDirectory(srcFile, destDir, true);
				// 참고. Apache Commons IO 라이브러리에서 제공하는
				//       FileUtils 클래스의 moveFileToDirectory 메소드를 이용하면
				//		 파일을 지정된 디렉터리로 이동시키는 기능을 수행할 수 있다.
				/*
					문법
						public static void moveFileToDirectory(File srcFile, 
															   File destDir, 
															   boolean createDestDir)
					 매개변수 설명
						File srcFile:		
						이동할 원본 파일을 나타내는 File 객체입니다.
						이 파일은 존재해야 하며, 이동할 파일의 경로를 지정해야 합니다.
						
						File destDir:	
						파일을 이동할 대상 디렉토리를 나타내는 File 객체입니다.
						이 디렉토리는 존재해야 하며, 존재하지 않을 경우 createDestDir 매개변수가 true로 설정되어 있으면 새로 생성됩니다.
						
						boolean createDestDir:	
						이 매개변수는 대상 디렉토리가 존재하지 않을 때 생성할지를 결정하는 부울 값입니다.
						true일 경우, 대상 디렉토리가 없으면 새로 생성합니다.
						false일 경우, 대상 디렉토리가 존재하지 않으면 IOException이 발생합니다.
				 */	  		
			
			} // 바깥 if 닫는 부분
			
		} // synchronized 닫음
		
		return articleNO;
	} // 아래의 upload 메소드 호출
	
	
	//----------(Pro15/src/FileUpload.java 참고)
	// 파일을 웹 애플리케이션 서버의 하드디스크 공간에 업로드 하는 기능의 메소드
	private synchronized Map<String, String> upload(HttpServletRequest request, HttpServletResponse response) 

								throws ServletException, IOException {
		
		String encoding = "utf-8";
		
		// 업로드한 파일명을 저장시킬 해시맵 생성
		Map<String, String> articleMap = new HashMap<String, String>();
		
		//1. 요청한 파라미터 한글처리
		request.setCharacterEncoding("UTF-8"); // 한글처리
		
		//2. 업로드할 파일 경로와 연결된 File객체 메모리 생성
		File currentDirPath = new File("C:\\file_repo");//업로드할 폴더 경로 
		
		//2-1. 업로드할 파일 데이터를 임시로 저장할 객체 메모리 생성
		DiskFileItemFactory factory = new DiskFileItemFactory();
				
		//파일 업로드시 사용할 임시메모리 최대 크기를 1메가 바이트로 설정
		factory.setSizeThreshold(1024*1024*1);
				
		//임시 메모리에 파일업로드시 ~~ 지정한 1메가 바이트 크기를 넘을 경우!!
		//업로드될 file_repo폴더경로를 설정
		factory.setRepository(currentDirPath);
		
		//참고.
		//DiskFileItemFactory클래스는 업로드할 파일의 크기가 지정한 임시메모리의 크기를 넘기전까지는
		//업로드한 파일 데이터를 임시메모리에 저장하고  지정한 크기를 넘길 경우  
		//업로드할 file_repo폴더로 업로드해서 저장시키는 역할을 함.	
				
		//파일을 업로드할 임시 메모리 객체의 주소를 생성자쪽으로 전달해 저장한
		//파일업로드를 실제 처리할 객체 생성
		ServletFileUpload upload = new ServletFileUpload(factory);
		
		try {
			/*
				uploadForm.jsp 파일업로드 요청하는 디자인 페이지에서  
				첨부한 파일 1개와  입력한 파라미터 여러개의 정보들
				request객체에서 꺼내와서  각각의 DiskFileItem객체들에 저장한 후 
				각각의 DiskFileItem객체들을  ArrayList배열에 추가 하게 됩니다. 
				그후 ~ ArrayList배열 자체를 반환 해 줍니다.
			*/	
			     List items	 = upload.parseRequest(request);
				
			     //ArrayList배열에 저장된 DiskFileItem객체(요청한 아이템)의 갯수만큼 반복
			     for(int i=0;  i<items.size();  i++) {
			    	 
			    	 //ArrayList배열에서 DiskFileItem객체를 하나씩 반복해서 얻는다
			    	 FileItem fileitem = (FileItem)items.get(i);
			    	 
			    	 
			    	 //얻은 DiskFileItem객체의 정보가 첨부한 파일 요청이 아니고
			    	 //입력한 텍스트 요청 정보가 저장된 DiskFileItem객체일 경우?
			    	 if(fileitem.isFormField()) {
			    		 
			    		 System.out.println( fileitem.getFieldName() + "="  
			    				 			 					+ fileitem.getString(encoding) );
			    		 											// input 태그의 value값
			    		 
			    		 articleMap.put(fileitem.getFieldName(), fileitem.getString(encoding));
			    		 			 // input의 name 속성값들  , // 새 글 추가를 위해 입력한 정보들
			    		 			 //         Key							 Value
			    	 
			    	 }else {//얻은 DiskFileItem객체의 정보가  첨부한 파일일 경우
			    	
			    		 System.out.println("<input type='file'>의 name속성값:" 
			    		                     + fileitem.getFieldName());
			    		 
			    		 System.out.println("업로드 요청시 첨부한 파일명 : " +
			    		                     fileitem.getName());
			    		 
			    		 System.out.println("업로드 요청시 첨부한 파일크기 : " +
			    		                     fileitem.getSize() + "bytes");
			    		 
			    		 //업로드시 첨부한 파일의 크기가 0보다 크다면?
			    		 if(fileitem.getSize() > 0) {
			    			 
			    			 //업로드할 파일명을 얻어  파일명의 뒤에서부터 \\문자열이 들어 있는지 index위치를 알려주는데.. 
			    			 //없으면 -1을 반환
			    			 int idx = fileitem.getName().lastIndexOf("\\");//파일명의 뒤에서 부터 \\문자열이 들어 있는지 검색해서
			    			 												//만약 있으면 \의 index위치를 int로 반환,없으면 -1을 반환
			    			 System.out.println(idx);
			    			 
			    			 if(idx == -1) {//업로드할 파일명에 \\ 가 포함되어 있지 않다면?
			    				 idx = fileitem.getName().lastIndexOf("/"); //포함되어 있지않으면 -1을 반환
			    				 System.out.println("업로드할 파일명에 /기호는 없다");
			    			 }
			    			 
			    			 //업로드할 파일명 얻기
			    			 String fileName = fileitem.getName().substring(idx+1);
			    			 
			    			 //업로로드할 파일 경로 + 파일명 전체 주소를 문자열로 만들어 접근할 File객체 생성
			    			 File uploadFile = new File(currentDirPath + "\\temp\\" + fileName );
			    			 						  //"C:\\file_repo +  "\\temp\\"+ "업로드할파일명"
			    			 
			    			 // 스레드들을 동기화하여 파일 업로드 처리
			    			 synchronized (this) { 
			    				 
			    				// 업로드되는 파일명을 HashMap에 반복해서 저장
					    		articleMap.put(fileitem.getFieldName(), fileName);
				    			 				// 업로드 첨부시 사용된         // 업로드할 파일명 VALUE      
				    			 				// input의 name="fileName"을
				    			 				// 얻어서 fileName을 Key로
					    			 
					    		//실제 파일 업로드
					    		fileitem.write(uploadFile);
			    				 
			    			 }
			    			 
			    		 }//가장 안쪽 if
			    		 		    		 
			    	 }//안쪽 if else중에서 else
			    	
			     }//for
			     
			}catch(Exception e) {
				e.printStackTrace();
			}
		
		return articleMap; // upload 메소드를 호출한 곳(serviceInsertBoard() 메소드)으로 해시맵 반환
		
	} // upload 메소드
	

	//---------- (Pro15/src/FileDownload.java 참고)
	// "/Download.do"
	// 파일다운로드
	// 다운로드한 파일의 다운로드 횟수를 DB의 FileBoard 테이블에 1증가하여 UPDATE시킨다.

	public void serviceDownload(HttpServletRequest request, HttpServletResponse response)
																			throws IOException{
		
		// 파일 다운로드 로직 구현 ---------------------------------------
		// 다운로드할 글번호 폴더 경로와 다운로드할 업로드된 파일명 얻기
		String idx = request.getParameter("path");
		String name = request.getParameter("fileName");
		
		//파일 다운로드를 요청한 클라이언트의 웹브라우저와 연결된
		//파일의 정보를 읽어들여 내보낼 출력스트림 통로 역할을 하는
		//org.apache.catalina.connertor.CoyoeOutputStream@32d8fcdd 객체를 반환해 줍니다.
		OutputStream out = response.getOutputStream();
		
		// 다운로드할 파일 전체 글번호 폴더 경로 만들어 저장
		String filePath = "C:\\file_repo\\" + idx;
		
		// 실제 다운로드할 파일에 접근하기 위해  
		// C:\\file_repo\\1\\다운로드할파일명 경로를 File객체 에 저장
		File f = new File(filePath + "\\" + name);
		
		//HTTP 1.1버전에서 지원하는 헤더로 no-cache로 설정하면 브라우저는 응답받은 결과를
		//캐시 스토리지에 저장하지 않습니다.
		//또한 뒤로가기 등을 통해서 페이지 이동하는 경우 페이지를 캐싱할수 있으므로 no-stroe 값또한
		//추가해 주어야 합니다. 
		response.setHeader("Cache-Control", "no-cache");
		response.addHeader("Cache-Control", "no-store");	
		
		//웹브라우저에서 다운로드할 파일명 클릭시
		//1.Content-Disposition속성에 attachment; 값을 지정하여 
		//  다운로드시 무조건 "파일 다운로드 다른이름으로 저장?" 대화상자가 뜨도록 하는 헤더 속성의 설정 
		//2. 다운로드할 파일명 깨져 내려 받아 지지 않도록 하기 위해 Content-Disposition속성에 다운로할 파일명 인코딩 후 설정 
		response.setHeader("Content-Disposition", "attachment; fileName=\"" + URLEncoder.encode(name, "UTF-8") + "\";");
		
		//실제 다운로드 구현
		//다운로드할 파일에 작성된 데이터들을 바이트 단위로 읽어들일 입력스트림(버퍼)통로 생성
		FileInputStream in = new FileInputStream(f);
				
		//다운로드할 파일에서 데이터를 8KB씩 읽어와 저장할 배열생성
	    byte[] buffer = new byte[1024*8]; //byte데이터들이 저장될 배열
		        
		while(true) {
		        	
			//다운로드할 파일의 내용을 약8kb 단위로 읽어 와 위 bufer배열에 저장후
			//저장한 바이트 수를 리턴
			//만약 읽어들이 바이트가 없으면 -1을 리턴
	    	int count = in.read(buffer);
		        	
		  	if(count == -1) {//더이상 다운로드할 파일에서 읽어들일 데이터가 없으면?
		  		break;//while반복문 빠져나가 더이상 in.read메소드 호출 못하게 함 
		   	}
		    //출력스트림 OutputStream통로를 통해 위 한번읽어들인 파일의 정보가 저장된
		  	//buffer배열 전체 byte데이터들의  배열의 0 index위치부터 count변수위치 끝까지 
			//웹브라우저로 출력(응답,내보냄)
		    out.write(buffer, 0, count);
		        	
		} // while 문 닫음
		
		// 입출력 스트림 통로 메모리 자원해제
		in.close();
		out.close();
		// 파일 다운로드 로직 구현 끝 부분----------------------------------------
		
		// DB 테이블에 저장된 downcount 열의 값 1증가하기 위해 메소드 호출
		fileboarddao.downUpdateCount(idx);
		
	} // serviceDownload 메소드 닫음
		
} // FileBoardService 클래스









