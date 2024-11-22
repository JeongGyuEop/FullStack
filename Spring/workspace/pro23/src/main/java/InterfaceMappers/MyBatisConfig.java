package InterfaceMappers;

import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

// MyBatis 설정을 관리하는 클래스 
public class MyBatisConfig {
	
	//SqlMapper객체를 저장할 부모 SqlSessionFactory인터페이스 타입의 정적변수 선언
	private static SqlSessionFactory sqlSessionFactory;
	
	//MyBatisConfig클래스가 톰캣메모리에 처음로드될때 한번만 실행되는 정적 초기화 블럭
	static {
		try {
			//MyBatis 설정 파일 경로 저장
			String resource = "mybatis/SqlMapConfig.xml";
			
			//설정 파일 읽을 입력스트림 통로 생성
			Reader reader = Resources.getResourceAsReader(resource);
			
			//SqlSessionFactoryBuilder객체의 build메소드 호출시 인자로 Reader입력스트림 통로 전달해서
			//SqlMapper객체를 반환받아 얻기
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
				
		}catch(Exception e) {
			//설정 파일 읽기 실패 또는 초기화 오류 처리 
			e.printStackTrace();
			throw new RuntimeException("MyBatisConfig 초기화 중 오류 발생:" + e.getMessage());
		}
			
	}//static 초기화 블럭 끝
	
	
	//SqlSessionFactory변수에 저장된 SqlMapper객체 반환 하는 메소드
	public static SqlSessionFactory getSqlSessionFactory() {
	
		return sqlSessionFactory;
	}
	
	

}

















