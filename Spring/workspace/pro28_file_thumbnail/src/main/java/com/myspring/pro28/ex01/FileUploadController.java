package com.myspring.pro28.ex01;

import java.io.File;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;


/*
순서1.
  http://localhost:8090/pro28/form 주소를 입력하여 엔터를 누르면
  ID와 이름을 입력받고 파일 추가를 클릭하면 아래의 form메소드에 의해 파일 업로드요청 하는 /WEB-INF/views/uploadForm.jsp화면 이 나옵니다. 

순서2. /WEB-INF/views/uploadForm.jsp화면에서 
	  <input type="text" name="id">에 아이디를 입력받고,
	  <input type="text" name="name">에 이름을 입력받으며,
      파일추가 버튼을 클릭하여 생성된 <input type="file" name="file1" /> 
      						   <input type="file" name="file2" />
      						   <input type="file" name="file3" />
      에 각각 업로드할 이미지 파일 duke.png, duke2.png을 선택하고 업로드 버튼을 클릭하면
      아래의 upload메소드에 의해 파일다중업로드를 처리합니다.


*/

@Controller
public class FileUploadController {

	//파일이 실제 업로드되는 폴더 경로 저장
	private static final String CURR_IMAGE_REPO_PATH = "c:\\spring\\image_repo";
	
	
	//http://localhost:8090/pro28/form 주소를 입력하여 엔터를 누르면 아래의 form메소드에 의해
	//ID와 이름을 입력받고 파일 추가를 클릭하여! 업로드요청을 누르는 다중 파일 업로드요청 하는 /WEB-INF/views/uploadForm.jsp화면 이 나옵니다.
	@RequestMapping(value="/form")
	public String form() {
		return "uploadForm";  //   /WEB-INF/views/uploadForm.jsp
	}
	
	//uploadForm.jsp화면에서 업로드할 파일을 다중 선택하고 업로드 버튼 눌렀을때 호출되는 메소드로
	//다중 파일 업로드를 처리 하는 메소드 
	@RequestMapping(value="/upload", method=RequestMethod.POST)
	public ModelAndView upload(MultipartHttpServletRequest multipartRequest,
							   HttpServletResponse response) throws Exception {
		
		//업로드할 파일명 또는 입력한 데이터가 한글일 경우 인코딩 방식 UTF-8로 설정
		multipartRequest.setCharacterEncoding("UTF-8");
		
		//입력한 텍스트(아이디,이름) 그리고 다중업로드 요청한 파일의 정보들을 저장할 Map을 생성합니다.
		Map map = new HashMap();
		
		//입력한 텍스트(아이디,이름) 요청을 한 
		//<input>태그의 name속성값들을 알지 못할때... 이name속성값들을
		//request에서 꺼내와서 Enumeration배열에 저장후  Enumeration배열 자체를 리턴 받는다.
		Enumeration enu = multipartRequest.getParameterNames();
		// [name, id]
		
		//요청한 <input>태그들의 name속성값들 name, id이 
		//Enumeration배열 [name, id]에 저장되어 있으면 반복 합니다.
		while(enu.hasMoreElements()) {
		
						  //입력한 이름 <input>의 name속성값 과
						  //입력한 아이디 <input>의 name속성값 을 반복해서 차례로 얻어옵니다.
			String name = (String)enu.nextElement(); // "name" 
													//  "id"
							
						 //입력한 이름과 아이디를 반복해서 차례로  얻어 옵니다.
			String value = multipartRequest.getParameter(name); //"홍길동"
																//"admin"
			
			//key/value 한쌍씩 해서 ~ Map저장 
			map.put(name, value);// {"name"="홍길동", "id"="admin"}
				
		}
		//파일을 업로드한 후 반환된 파일 이름이 저장된 fileList배열을 다시 map에 저장합니다.
		//[duke.png, duke2.png]
		List fileList = fileProcess(multipartRequest);
		
		//Map에 추가로 업로드한 파일이름이 저장돤 fileList(배열)을 저장 합니다.
		//{name=관리자, id=admin, fileList=[duke.png, duke2.png]}
		map.put("fileList", fileList);
		
		
		ModelAndView mav = new ModelAndView();
					 mav.addObject("map", map);
					 mav.setViewName("result");
	
		
		return mav;
		
	}//upload메소드 닫는 기호 
	
	//파일을 업로드한 후 반환된 파일 이름이 저장된 fileList배열을 반환하는 메소드 
	private List<String> fileProcess(MultipartHttpServletRequest multipartRequest) 
						 		    throws Exception{
		
		List<String> fileList = new ArrayList<String>();
		
		//첨부된 파일들의 input 태그의 name속성값=CommonsMultipartFile객체 한쌍씩 저장된  LinkedKeyIterator 배열 자체를 반환 합니다. 
		//{file1=[CommonsMultipartFile@42b715da], file2=[CommonsMultipartFile@78a15f55]}
		//							0							1
		Iterator<String>  fileNames = multipartRequest.getFileNames();
		
		//LinkedKeyIterator 배열에  CommonsMultipartFile객체들이 저장되어 있는 동안 반복
		while(fileNames.hasNext()) {
			
			//LinkedKeyIterator 배열에 저장된 첨부한 <input>태그의 name속성값을 반복해서 얻는다. 
			String fileName = fileNames.next(); // file1, file2
			
			//<input type="file">태그에 첨부한 파일의 정보가 저장된 fileItem객체를 반환 받는다.
			/*
			아래는 하나의 fileItem객체의 정보 입니다. 
			name=duke.png, 
			StoreLocation=C://Program Files//Apache Software Foundation//Tomcat 9.0//work//Catalina//localhost//pro28//upload_705ab047_18b7f334aa0__8000_00000002.tmp,
			size=4437bytes, isFormField=false, FieldName=file1
			*/
			MultipartFile mFile = multipartRequest.getFile(fileName);
			
			//fileItem객체에서 실제 업로드(첨부)한 파일이름을 반복해서 가져오기
			//duke.png, duke2.png
			String originFileName = mFile.getOriginalFilename();
			
			//실제 업로드한 파일이름을 하나씩 반복해서  ArrayList배열에 추가 하여 저장
			fileList.add(originFileName); //[duke.png, duke2.png]
			
			//c:\spring\image_repo\duke.png  업로드할 파일 경로   
			//c:\spring\image_repo\duke2.jpg    업로드할 파일 경로
			File file = new File(CURR_IMAGE_REPO_PATH + "\\" + originFileName);
			
			//첨부되어 업로드할 파일사이즈가 있는지  (업로드할 파일이 있는지) 체크 합니다.
			if(mFile.getSize() != 0) { 
			
				//업로드된 파일을 저장할위치가 존재 하지 않는지 확인합니다.
				//파일이 존재 하지 않으면  업로드한 파일을 저장할 디렉토리와 파일을 생성해야 합니다. 
				if(! file.exists()) {
					
					//c:\spring\image_repo
					File file1 =   file.getParentFile();
					
					//c:\spring\image_repo 업로드될 폴더 생성 
					file1.mkdirs();
				}
				
				//임시로 저장된 fileItem객체를 지정된 대상 파일로 전송하며, 
				//업로드한 파일을 원하는 위치에 저장하고 동일한 이름을 가진 기존파일을 덮어 씁니다.
				mFile.transferTo( new File(CURR_IMAGE_REPO_PATH + "\\" + originFileName) );
				
			}
		}
		
		return fileList;//업로드한 파일명들이 저장된 ArrayList배열 반환 
		
	}// fileProcess 메소드 닫는 기호 
	
	
	
	
}//FileUploadController클래스 닫는 기호 






