package net.skhu;
// 이 파일이 속한 패키지를 정의합니다.
// 'package'는 파일의 논리적 위치를 나타냅니다.
// 여기서는 'net.skhu'라는 패키지에 속해 있습니다.
// 'net.skhu'는 프로젝트의 고유 이름 공간으로 볼 수 있습니다.

import org.springframework.boot.SpringApplication;
// Spring Boot 애플리케이션을 실행하기 위해 필요한 클래스입니다.
// SpringApplication은 Spring Boot 애플리케이션을 시작하는 도우미 역할을 합니다.
import org.springframework.boot.autoconfigure.SpringBootApplication;
// 이 애노테이션은 여러 설정을 한 번에 처리하는 역할을 합니다.
// Spring Boot 애플리케이션임을 선언하고, 필요한 기본 설정을 자동으로 추가합니다.
// 예: Spring Boot 애플리케이션의 시작점 설정, 필요한 설정을 자동 구성.

@SpringBootApplication
// 위에서 설명한 'SpringBootApplication'을 이 클래스에 적용합니다.
// 이 애노테이션을 사용하면 해당 클래스가 Spring Boot 애플리케이션의 진입점이 됩니다.

public class Hello2Application {
// 'Hello2Application'은 프로젝트의 메인 클래스입니다.
// 모든 Java 애플리케이션에는 'main' 메서드가 있어야 시작할 수 있습니다.
// 여기서 'Hello2Application' 클래스는 프로젝트 이름과 동일하게 만들어졌습니다.

	public static void main(String[] args) {
		// Java 애플리케이션은 'main' 메서드에서 실행을 시작합니다.
		// 'main' 메서드는 프로그램이 시작될 때 가장 먼저 실행되는 부분입니다.

		SpringApplication.run(Hello2Application.class, args);
		// SpringApplication.run() 메서드를 호출하여 애플리케이션을 실행합니다.
		// - 'Hello2Application.class': 실행할 메인 클래스입니다.
		//   여기서는 이 파일의 클래스인 'Hello2Application'을 지정합니다.
		// - 'args': 프로그램 실행 시 전달받는 추가 명령줄 인수입니다.
		//   필요하지 않다면 그대로 비워두어도 됩니다.
		// 이 코드를 실행하면 Spring Boot 애플리케이션이 실행됩니다.
		// 애플리케이션 실행 시 내장 서버(Tomcat)가 시작되고, 애플리케이션이 준비됩니다.
	}

}
/*
## **코드 요약**
1. **패키지**:
   - `package net.skhu;`는 이 클래스가 `net.skhu`라는 패키지에 속해 있음을 나타냅니다. 패키지는 파일을 체계적으로 정리하는 폴더 같은 역할을 합니다.
2. **필요한 클래스 가져오기**:
   - `import org.springframework.boot.SpringApplication`은 Spring Boot 애플리케이션을 실행하기 위한 기능을 제공합니다.
   - `import org.springframework.boot.autoconfigure.SpringBootApplication`은 Spring Boot의 자동 설정을 활성화합니다.
3. **애노테이션**:
   - `@SpringBootApplication`은 이 클래스가 Spring Boot 애플리케이션의 시작점임을 나타냅니다.
   - Spring Boot의 자동 구성, 컴포넌트 스캔 등을 처리합니다.
4. **메인 클래스**:
   - `Hello2Application` 클래스는 애플리케이션의 중심 역할을 합니다.
5. **메인 메서드**:
   - `public static void main(String[] args)`는 애플리케이션이 실행될 때 처음 실행되는 코드입니다.
   - `SpringApplication.run()`을 호출하여 Spring Boot 애플리케이션을 시작합니다.



### **Spring Boot 애플리케이션의 실행 과정**
1. `main` 메서드를 실행하면 `SpringApplication.run()` 메서드가 호출됩니다.
2. Spring Boot가 필요한 설정을 자동으로 수행합니다.
   - 내장 웹 서버(Tomcat) 시작.
   - 지정된 포트에서 애플리케이션 대기.
3. 애플리케이션이 실행되어 사용자 요청을 처리할 준비가 됩니다.
*/