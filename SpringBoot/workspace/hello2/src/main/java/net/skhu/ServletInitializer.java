package net.skhu;
// 'package net.skhu;'는 이 클래스가 'net.skhu'라는 패키지에 속해 있다는 것을 나타냅니다.
// 패키지는 클래스를 그룹으로 묶는 역할을 하며, 파일의 위치를 논리적으로 구분해줍니다.
// 예를 들어, 'net.skhu'는 'net'이라는 최상위 폴더 안에 'skhu'라는 서브폴더를 두고 있다는 의미입니다.
// 패키지를 사용하는 이유는 클래스 충돌을 피하고, 클래스들을 체계적으로 관리하기 위해서입니다.

import org.springframework.boot.builder.SpringApplicationBuilder;
// 'SpringApplicationBuilder'는 Spring Boot 애플리케이션을 설정하고 구성을 도와주는 클래스입니다.
// 이 클래스는 애플리케이션이 실행될 때 필요한 설정을 처리하고, 웹 서버를 설정하는 역할을 합니다.
// Spring Boot 애플리케이션을 외부 웹 서버에서 실행할 때 이 클래스를 사용하여 애플리케이션의 초기화를 설정합니다.
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
// 'SpringBootServletInitializer'는 Spring Boot 애플리케이션을 외부 Servlet 컨테이너(예: Tomcat, Jetty)에서 실행할 수 있도록 지원하는 클래스입니다.
// Spring Boot 애플리케이션은 기본적으로 내장된 Tomcat 서버를 사용하지만, WAR 파일로 패키징 후 외부 서버에 배포할 경우 이 클래스를 상속받아야 합니다.
// 이 클래스를 통해 외부 서버에서 Spring Boot 애플리케이션을 실행할 수 있도록 설정합니다.

public class ServletInitializer extends SpringBootServletInitializer {
    // 'ServletInitializer' 클래스는 Spring Boot 애플리케이션을 외부 서버에서 실행할 수 있게 도와주는 클래스입니다.
    // Spring Boot 애플리케이션은 기본적으로 내장 서버(Tomcat 등)를 사용하지만, 외부 서버에서 실행하려면 이 클래스를 사용하여 설정해야 합니다.
    // 'SpringBootServletInitializer' 클래스를 상속받아서 WAR 파일로 애플리케이션을 배포하고, 외부 서버에서 이를 실행할 수 있도록 합니다.

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        // 'configure' 메서드는 Spring Boot 애플리케이션을 외부 웹 서버에서 실행할 때의 초기화 작업을 설정하는 메서드입니다.
        // 이 메서드는 'SpringBootServletInitializer'에서 상속받은 메서드로, 외부 서버에서 실행될 때 애플리케이션을 어떻게 구성할지 지정합니다.
        // SpringApplicationBuilder는 애플리케이션을 설정하고 실행하는 데 사용되는 빌더입니다.

        // application.sources(Hello2Application.class)는 애플리케이션을 시작할 때 'Hello2Application' 클래스를 메인 애플리케이션 클래스로 지정합니다.
        // 'Hello2Application.class'는 Spring Boot 애플리케이션의 시작점을 나타내는 클래스입니다.
        // 이 설정으로 외부 서버(Tomcat 등)에서 Spring Boot 애플리케이션이 실행될 때 'Hello2Application' 클래스가 시작됩니다.

        return application.sources(Hello2Application.class);
        // 'application.sources(Hello2Application.class)'는 Spring Boot 애플리케이션의 설정을 수행한 후,
        // 애플리케이션을 실행할 클래스를 지정합니다. 여기서 'Hello2Application.class'는 실제로 애플리케이션을 실행하는 메인 클래스입니다.
        // 이 메서드는 Spring Boot 애플리케이션을 외부 서버에서 실행하는 데 필요한 설정을 완료한 후, 애플리케이션을 실행할 준비를 합니다.
    }
    // 'configure' 메서드를 오버라이드(재정의)하여 Spring Boot 애플리케이션의 설정을 외부 서버에 맞게 구성합니다.
}

/*

## **코드 요약 및 설명**

### 1. **패키지 선언 (`package net.skhu`)**
   - **패키지**는 클래스들을 논리적으로 그룹화합니다. `net.skhu`는 이 파일이 `net`이라는 최상위 폴더 아래에 위치하는 `skhu`라는 서브폴더에 있다는 의미입니다.
   - 패키지는 클래스의 충돌을 방지하고, 프로젝트 내에서 클래스들을 체계적으로 관리하는 역할을 합니다.

### 2. **클래스 선언 및 상속 (`public class ServletInitializer extends SpringBootServletInitializer`)**
   - `ServletInitializer` 클래스는 **Spring Boot 애플리케이션을 외부 웹 서버에서 실행**하도록 돕는 역할을 합니다.
   - **`SpringBootServletInitializer`**를 상속받음으로써, 이 클래스는 외부 Servlet 컨테이너(예: Tomcat, Jetty)에서 Spring Boot 애플리케이션을 실행할 수 있도록 설정을 합니다.
   - 외부 서버에서 Spring Boot 애플리케이션을 실행할 때 반드시 이 클래스를 작성해야 합니다.

### 3. **`configure` 메서드 오버라이드**
   - `@Override` 애노테이션은 부모 클래스의 메서드를 재정의했다는 것을 나타냅니다.
   - `configure` 메서드는 Spring Boot 애플리케이션을 **외부 웹 서버에서 실행하기 위한 설정을** 담당합니다.
   - `SpringApplicationBuilder`는 애플리케이션을 실행하기 위한 설정을 돕는 빌더 클래스로, 애플리케이션의 설정을 구성합니다.
   - `application.sources(Hello2Application.class)`는 Spring Boot 애플리케이션을 시작할 클래스(메인 클래스)를 지정하는 코드입니다.
     - 이 코드로 외부 서버(Tomcat, Jetty 등)에서 실행될 때 `Hello2Application.class`를 애플리케이션의 시작점으로 지정합니다.

### 4. **`return application.sources(Hello2Application.class);`**
   - `application.sources(Hello2Application.class)`는 애플리케이션이 실행될 때 사용할 메인 클래스를 지정하는 부분입니다.
   - `Hello2Application.class`는 **실제 애플리케이션의 메인 클래스**로, 이 클래스에서 애플리케이션이 실행됩니다.
   - 이 설정을 통해 **WAR 파일을 배포**한 외부 서버에서 Spring Boot 애플리케이션을 실행할 수 있게 됩니다.



## **WAR 파일 배포와 SpringBootServletInitializer**
1. **WAR (Web Application Archive)** 파일은 웹 애플리케이션을 외부 서버에 배포할 때 사용하는 파일 형식입니다.
2. **내장 서버 vs 외부 서버**:
   - Spring Boot는 기본적으로 내장 서버(Tomcat)를 사용하여 애플리케이션을 실행합니다.
   - 그러나 외부 서버(Tomcat, Jetty 등)에 애플리케이션을 배포하려면 `SpringBootServletInitializer` 클래스를 사용하여 설정을 추가해야 합니다.
3. **외부 서버에서 실행하기 위한 설정**:
   - `ServletInitializer` 클래스에서 `SpringBootServletInitializer`를 상속받고 `configure` 메서드를 오버라이드하여 애플리케이션을 외부 서버에서 실행할 수 있도록 설정합니다.



### **간단한 예시**
1. 애플리케이션을 WAR 파일로 빌드합니다.
2. 이 WAR 파일을 Tomcat과 같은 외부 서버에 배포합니다.
3. `ServletInitializer` 클래스가 설정을 완료하고 외부 서버에서 Spring Boot 애플리케이션이 실행됩니다.

이 과정에서 `configure` 메서드는 애플리케이션의 시작 클래스를 지정하며, Spring Boot 애플리케이션을 외부 서버에서 실행할 수 있게 도와줍니다.
*/