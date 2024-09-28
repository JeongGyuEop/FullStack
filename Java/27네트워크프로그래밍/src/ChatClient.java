import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/********** 클라이언트 채팅 프로그램 *************/
public class ChatClient {
	
	// 접속을 요청할 서버채팅프로그램의 IP 주소 저장할 변수
	String ipAddress;
	
	// 접속을 요청할 서버채팅 프로그램의 포트번호(문번호) 설정
	static final int port = 6000;
	
	// 클라이언트 채팅 프로그램(ChatClient) 와 연결된 클라이언트 소켓 통로
	Socket client = null;
	
	// 클라이언트 채팅 프로그램으로 입력받은 데이터를 읽어들일 입력스트림 통로 
	BufferedReader read = null;
	
	// 서버 채팅 프로그램에게 입력한 데이터를 전송(송신, Send) 하기 위한 출력스트림 통로
	ObjectOutputStream oos ;
	
	// 서버 채팅 프로그램으로 부터 데이터를 수신하기 위한 입력스트림 통로
	ObjectInputStream ois;
	
	// 서버 채팅 프로그램에 보낼 데이터를 저장하기 위한 변수
	String sendData;
	
	// 서버 채팅 프로그램으로 부터 수신받은 데이터를 저장하기 위한 변수
	String recevieData;
	
	// 서버 채팅 프로그램에 접속하는 클라이언트(접속자)의 아이디를 저장할 변수
	String user_id;
	
	// 서버 채팅 프로그램에서 보낸 데이터를 받기 위한 작업을 하는 스레드 객체를 저장할 변수 
	RecevieDataThread rt;
	
	boolean endflag = false;
	
	// 생성자 : 사용자의 아이디와 접속할 서버채팅프로그램의 IP를 매개변수로 전달받아 처리하는 생성자
	public ChatClient(String id, String ip) {
		this.user_id = id; // 서버로 요청할 클라이언트 id
		this.ipAddress = ip; // 접속 요청할 서버의 IP
	
		try {
			System.out.println("*** 클라이언트 ***");
			// 접속할 서버채팅프로그램의 IP주소와 포트번호를 생성자로 전달해서 클라이언트 Socket 객체 생성 
			client = new Socket( ipAddress , port );
			// 중요! 클라이언트 Socket 객체를 생성하는 코드가 실행되는 순간! 서버 채팅프로그램의 accept() 메소드가 자동으로 호출되어 실행된다. 
			
			// 클라이언트 채팅 프로그램에서 키보드로 입력받은 메세지를 읽어 들일 스트림 생성 
			read = new BufferedReader (new InputStreamReader(System.in)) ;
			
			// 서버채팅프로그램에게 메세지를 보내기(송신)위해서 출력스트림 통로를 얻어 
			// ObjectOutputStream  으로 변환 
			oos = new ObjectOutputStream(client.getOutputStream());
			
			// 서버채팅프로그램으로부터 데이터를 수신받기위해서 클라이언트 소켓 통로 객체로 부터 
			// 입력스트림 통로를 얻어 ObjectInputStream 으로 변환 
			ois = new ObjectInputStream(client.getInputStream());
			
			
			//  ++++++++ 서버 채팅 프로그램에게 접속하는 클라이언트의 아이디 전송 ++++++++
			oos.writeObject(user_id);
			oos.flush();
			
			// ++++++++ 서버 채팅 프로그램이 보낸 데이터를 수신받기 위한 스레드 객체 생성 ++++++++
			rt = new RecevieDataThread(client, ois);
			Thread t = new Thread(rt);
			t.start();  // 나중에 run 메소드가 실행 될 것임
			
			while(true) {
				// 클라이언트 채팅 프로그램에서 키보드로 입력한 데이터를 서버 채팅프로그램으로 보내기 위해 
				// 입력한 데이터를 BufferedReader 에서 한줄 단위로 읽어서 저장 
				sendData = read.readLine();
				
				// 서버 채팅 프로그램으로 전송해서 보냄 (출력스트림 ObjectOutputStream 통로를 통해 보냄 )
				oos.writeObject(sendData);
				oos.flush();
				
				// 클라이언트 채팅프로그램과 서버채팅 프로그램의 소켓 통로의 연결을 끊으려면 클라이언트 채팅프로그램에서 "/quit"를 입력했을 경우 
				// while 반복문을 빠져나가되 endflag 변수값을 true 로 저장 
				if( sendData.equals("/quit")) {
					endflag = true; // 클라이언트 채팅프로그램 접속 끊음 
					break; // while 반목문을 빠져나가자 
				}
				
			} // while
			
			System.out.println("클라이언트의 접속을 종료합니다.");
			
			
		} catch (Exception e) {
			// 클라이언트 채팅 프로그램이 접속중인데 예외가 발생하면?
			if(!endflag) {
				e.printStackTrace(); // 예외가 발생하면 메세지 출력 
			}

		}finally {
			try {
				// 모든 입출력 스트림을 모두 사용하고 난 뒤 JVM 메모리에서 제거
				ois.close();
				oos.close();
				client.close();
				
				System.exit(0); // 클라이언트 채팅 프로그램 강제 종료
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		} // finally
		
	} // 생성자 
	
	// 메인 스레드   
	// 명령프롬포터에 입력할 것
	// java ChatClient user_id 접속할서버의_IP
	//									0					1						index
	// 입력받으면 [ "user_id","접속할서버의_IP"  ]  <-String 배열에 저장되어 아래의 main 메소드의 매개변수 String[] args 로 전달 받는다.
	public static void main(String[] args) {
		if(args.length < 2) {
			System.out.println("사용법 : java ChatClient 사용자ID 서버의IP");
			System.exit(0); // 클라이언트 채팅프로그램 강제 종료 
		}
		// 제대로 입력해서 클라이언트 채팅 프로그램을 실행했다면 클라이언트 id, 접속할 서버채팅프로그램 IP 전달 
		new ChatClient(args[0], args[1]);
	}

}
//------------------------------------------------------------------------------------------



/***** 키보드로 메세지를 송신하면서 서버로 부터 메세지를 수신받기 위한  스레드  *****/
class RecevieDataThread implements Runnable{
	
	// 서버 채팅 프로그램에 만든 소켓 통로와 통신하기 위한 클라이언트 소켓 객체 
	Socket client;
	
	// 서버 채팅 프로그램으로 부터 데이터를 수신 받기 위한 입력 스트림 객체
	ObjectInputStream ois;
	
	// 서버 채팅 프로그램으로 부터 수신 받은 데이터를 저장할 변수 
	String receiveData;
	
	// 생성자
	// 서버 채팅 프로그램의 소켓 통로에 접속요청한 클라이언트의 소켓객체와 입력스트림 이 매개변수로 전달됨
	public RecevieDataThread(Socket client, ObjectInputStream ois){
		this.client = client;
		this.ois = ois;
		
	}
	// 메소드 오버라이딩 
	@Override
	public void run() {
		// 서버 채팅 프로그램이 보낸 메세지를 입력스트림(ObjectInputStream)을 통해 읽어와 클라이언트 채팅 프로그램을 사용하는 모니터 화면에 출력(내보내자)
		try {
			// 입력 스트림을 통해 데이터를 읽어와 모니터에 출력
			while( ( receiveData = (String)ois.readObject()) != null ) {
				// 모니터에 반복해서 출력
				System.out.println(receiveData);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 모두 사용이 끝난 스트림 통로 메모리와 소켓통로 메모리를 JVM에서 제거  - 자원해제
			try {
				ois.close();
				client.close();
			} catch (IOException e) {
				e.printStackTrace();
			}


		} // finally
		
	} // run 
} // ReceiveDataThread

