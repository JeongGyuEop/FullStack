import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

public class Server {
	/*
	 	public interface Closeable extends AutoCloseable {
    		public void close() throws IOException;
		}
	 */
	
	// public class ServerSocket implements java.io.Closeable { ... }
	ServerSocket server; // 서버 전용 소켁 통로 객체 생성 후 저장할 변수 선언 

	final static int port = 6000; // 포트 번호를 설정
	
	// public class Socket implements java.io.Closeable { ... }
	Socket child; // 클라이언트가 요청이 들어오면 통신할 소켓 통로 객체 생성 수를 저장할 변수
	
	// public class HashMap<K,V> extends AbstractMap<K,V> implements Map<K,V>, Cloneable, Serializable { ... }
	// 채팅 스레드들 간의 정보를 공유할 HashMap 배열을 생성할 변수
	HashMap<String, ObjectOutputStream> map; // key : 아이디, value : 출력 스트림
	
	// 서버 전용 소켓 객체 생성, 클라이언트 요청이 들어오면 통신할 소켓통로로 객체를 생성
	// 초기화할 생성자
	public Server() {
		// 서버 전용 소켓 객체 생성 시 포트 번호를 설정
		try {
			server = new ServerSocket(port);
		} catch(IOException e) { // 서버 소켓 생성에 실패하면
			e.printStackTrace(); // 예외 메시지를 출력하고
			System.exit(0); // 서버 채팅 프로그램 강제 종료
		}
		
		System.out.println("********* 채팅 서버 *********");
		System.out.println("서버 채팅 프로그램은 클라이언트 소켓 통로의 접속 요청을 기다리고 있음");
		
		// 스레드 간 정보를 공유할 HashMap 객체 생성
		map = new HashMap<String, ObjectOutputStream>();
		
		// 클라이언트 채팅 프로그램이 서버 채팅 프로그램으로 항상 접속을 할 수 있도록
		// 무한 루프를 돌린다.
		while(true) {
			try {
				// 클라이언트 채팅 프로그램의 요청이 들어오면 요청을 받아들인다.
				// accept 메소드는 클라이언트와 데이터를 주고 받는다.
				// 요청한 클라이언트 전용 소켓 통로(new Socket()) 객체를 반환한다.
				child = server.accept();
				
				// 접속을 유지하면서 데이터를 송수신하기 위해서 스레드 객체를 생성한다.
				// Runnable 인터페이스를 구현한 일반 클래스의 객체를 생성
				ChatServerThread childThread  = new ChatServerThread(child, map);
				
				// public class Thread implements Runnable { ... }
				Thread t = new Thread(childThread); // 스레드로 만든다.
				
				t.start();
				
			} catch (IOException e) {
				e.printStackTrace(); // 예외 메세지 출력
				System.exit(0); // 서버 채팅 프로그램 강제 종료
			}
		}
	} // Server 클래스 생성자
	

	// 메인 스레드
	public static void main(String[] args) {

		new Server(); // 서버 채팅 프로그램 객체를 생성

	}

}

/*
  	서버가 다른 클라이언트에게 메세지를 송수신하기 위해서  컬렉션(해쉬 맵)을 가지고 있으면서
   	새로운 클라이언트가 접속해 오면 해쉬맵에서 사용자 아이디와 출력 스트림을 저장해 두고
   	클라이언트가 접속을 해제하면 해쉬맵에서 해당클라이언트를 삭제 하는 역할을 하는
   	
   	ChatServerThread 클래스 구현
 */   
/*
 	@FunctionalInterface
	public interface Runnable {
    	void run();
	}
 */
class ChatServerThread implements Runnable {
	
	Socket child; // 클라이언트와 통신하기 위한 소켓
	
	ObjectInputStream ois; // 클라이언트로부터 데이터를 수신기 위한 스트림
	ObjectOutputStream oos; // 클라이언트에게 데이터를 송신하기 위한 스트림
	
	String user_id; // 서버 채팅 프로그램에 접속한 클라이언트의 아이디를 저장할 변수
	
	// 스레드 간의 정보를 공유할 HashMap
	HashMap<String, ObjectOutputStream> map;
	
	// 생성자 : 입출력 스트림을 얻어서 아이디와 출력스트림을 해시맵에 등록한다.
	public ChatServerThread(Socket s, HashMap map) { // 소켓 객체와 해시맵이 매개변수로 전달된다.
		
		child = s; // 클라이언트와 통신할 수 있는 소켓 객체를 저장한다.
		this.map = map;
		
		try {
			
			// 접속한 클라이언트의 아이피 주소를 바로 위의 요청한 클라이언트 채팅 프로그램과
			// 연결되는 클라이언트 전용 소켓으로부터 얻음.
			System.out.println( child.getInetAddress() + "로 부터 연결을 요청받았다.");
			
			// 클라이언트로부터 데이터를 수신받기 위한
			// 클라이언트 PC의 채팅 프로그램과 연결된 입력스트림 통로를 얻는다.
			ois = new ObjectInputStream(child.getInputStream());
			
			// 클라이언트 PC의 채팅프로그램에게 데이터를 송신하기 위한 출력 스트림 통로를 얻는다.
			oos = new ObjectOutputStream(child.getOutputStream());
			
			// 접속한 클라이언트의 아이디를 얻어와서 아이디와 출력스트림 통로를 해시맵에 등록한다.
			user_id = (String)ois.readObject();
			
			// 이미 서버 채팅프로그램에 접속되어 있는 모든 클라이언트 채팅프로그램들에게
			// 접속되었다는 메시지 전송
			broadcast(user_id + "님이 접속하셨습니다.");
			
			// 여러 스레드가 공유하는 해시맵을 동기화한다
			synchronized(map) {
				// 사용자 아이디 : key, 출력스트림객체 : value
				// 모든 클라이언트에 의해 공유되어 메시지를 브로드 캐스팅하기 위해 출력스트림을 
				// 해시맵에 저장
				map.put(user_id, oos);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	} // ChatServerThread 클래스의 생성자
	
	// Runnable 인터페이스의 run 메소드 오버라이딩
	// 클라이언트 채팅프로그램으로 부터 수신받은 데이터를 다시 모든 클라이언트에게 송신
	@Override
	public void run() {
		
		// 클라이언트 채팅 프로그램으로 부터 수신받은 데이터를 저장할 문자열 변수
		String receiveData;
		
		try {
			
			while(true) {
				// 클라이언트가 보낸 메시지를 수신받는다.
				// 입력 스트림을 통해서 클라이언트가 보낸 메시지를 읽어온다.
				receiveData = (String)ois.readObject();
				
				// 클라이언트로 부터 종료 메시지가 들어오면 반복문을 벗어나
				// finally 구문의 코드 실행
				if(receiveData.equals("/quit")) {
					break;
				} else if(receiveData.indexOf("/to") > -1) { // 귓말을 위해 메시지를 보내면 sendMsg 호
					sendMsg(receiveData);
				} else { // "/quit" 또는 "/to" 이외의 다른 메시지를 입력하면 모든 클라이언트 채팅프로그램에게
					// 브로드캐스팅 한다.
					broadcast(user_id + " : " + receiveData);
				}
			}
			
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("클라이언트 강제 종");
		} finally { // 클라이언트로부터 .quit 메시지 받으면
			
			// 여러 스레드들이 공유하는 해시맵을 동기화한다.
			// 동기화 블럭 이용
			synchronized(map) {
				// 종료 메시지를 보낸 클라이언트 정보 해시맵에서 삭제
				map.remove(user_id);
			}
			// 나머지 클라이언트 채팅 프로그램들에게 종료 메시지를 보낸 클라이언트가 종료를 알린다.
			broadcast(user_id + "님이 나가셨습니다.");
			
			// 서버 채팅 프로그램에도 접속 종료를 알라기 위해 출력
			System.out.println(user_id +"님이 나가셨습니다.");
			
			try {
				
				// 서버 채팅 프로그램에 접속하여 종료메시지를 보낸 클라이언트 채팅 프로그램
				// 전용 소켓 통로 자원 해제
				if(child != null ) {
					child.close();
				}
				
			} catch(Exception e) {
				e.printStackTrace();
			}
			
		} // finally
		
	} // Runnable 인터페이스의 run메소드 오버라이딩
	
	
	// 서버에 연결된 모든 클라이언트에게 메시지를 전송하는 역할을 하는 함수 (서버가 사용)
	public void broadcast(String message) {
		// 매개변수 message : 클라이언트가 보낸 메시지 또는 서버가 전달하고자 하는 메시지
		//					 서버에 연결된 모든 클라이언트에게 전송된다.
		
		// HashMap을 통해 동기화
		// synchronized 블록 : 동기화를 위해 사용. 멀티스레딩 환경에서 안전하게 공유자원에 접근하기 위해서 사용한다.
		//					  한 번에 하나의 스레드만 map 객체를 수정하거나 읽을 수 있다.
		synchronized(map) { // map : HashMap 객체, 각 클라이언트 id와 출력스트림을 저장하고 있다.
			for(ObjectOutputStream oos : map.values()) { // map에 저장된 모든 클라이언트의 ObjectOutputStream을 순회
				// map.values()는 클라이언트에게 데이터를 전송할 수 있는 출력 스트림이다.
				
				try {
					oos.writeObject(message); // 클라이언트에게 메시지 전송(ObjectOutputStream을 통한 직렬화된 형태의 데이터)
					oos.flush(); // 버퍼에 남아 있는 데이터를 강제로 전송.
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}// synchronized 블록
		
	} // broadcast 함수 
	
	// 귓속말을 하기 위해서 특정 클라이언트에게 메시지를 보냈다면 다음과 같은 형식으로 보낸다.
	public void sendMsg(String message) {
		
		// "/to user_id message"형식으로 가정
		String[] tokens = message.split(" ", 3); // 공백을 기준으로 3개로 나눈다.
		
		if( tokens.length >= 3 && tokens[0].equals("/to")) {
			String id = tokens[1]; // 대상 사용자 ID
			String msg = tokens[2]; // 전송할 메시지
			
			ObjectOutputStream oos = map.get(id); // 해시맵에서 대상 사용자 ID의 출력스트림을 얻어온다.
			
			if(oos != null) {
				try {
					oos.writeObject(user_id + "님이 귓속말을 보냈습니다 : " + msg);
					oos.flush();
				} catch(IOException e) {
					e.printStackTrace();
				}
			}
		}
		
	} // sendMsg 함수
	
}



































