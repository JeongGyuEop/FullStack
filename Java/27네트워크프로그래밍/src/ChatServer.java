/*
	채팅 서버 구현하기
	
	- 채팅 서버를 구성하는 클래스는 ChatServer와 ChatServerThread 두개 입니다
	-    ChatServer 클래스에서는
		 반복문을 돌면서 클라이언트의 접속이 있을 때이를 수락(accept)하는 
		 동작은 메인스레드가 담당하고
		 접속을 유지하면서 데이터 송수신을 하기 위해서는 
		 따로 스레드 객체(ChatServerThread)를 생성해야
		 하므로 채팅 서버를 구현하기 위해서 두개의 클래스가 필요합니다.
		 
	클래스 						역할
	ChatServer			    서버 소캣 생성. 접속을 계속 유지하면서 데이터 송수신 하기 위해서
		 					스레드(ChatServerThread)객체 생성.
		 					스레드 객체 생성시 전달인자로 컬렉션 객체인 HashMap을 전달해 줌으로써
		 					멀티 캐스팅 기능하도록 합니다.
		 					
		 					
	ChatServerThread		접속을 계속 유지하면서 데이터 송수신 하기 위한 클래스
		 					ChatServer에 있는 컬렉션 객체인 HashMap을 이용하여 
		 					멀티캐스팅할 수 있다.

 */

//서버 채팅 프로그램
//   서버소켓 + 클라이언트통신을 위한 소켓통로 

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

public class ChatServer {

	 ServerSocket server; //서버 전용 소켓 통로 객체 생성후 저장할 변수
	 
	 final static int port = 6000; //포트번호 설정
	 
	 //클라이언트가 요청이 들어오면 통신할 소켓통로 객체 생성수 저장할 변수
	 Socket child;
	 
	 //채팅 스레드들 간의 정보를 공유할 HashMap배열을 생성할 변수
	 HashMap<String, ObjectOutputStream> hm; //key 아이디고, value는 출력스트림
	 
	 //서버 전용 소켓객체 생성, 클라이언트요청이 들어오면 통신할 소켓통로 객체 생성
	 //초기화할 생성자 
	 public ChatServer() {
		 //서버 전용 소켓 객체 생성시 포트번호 설정
		 try {
			server = new ServerSocket(port);
			
		} catch (IOException e) {//서버 소켓 생성에 실패하면 
			e.printStackTrace();//예외 메세지를 출력하고
			System.exit(0);//서버 채팅 프로그램ChatServer.class 강제 종료 
		}
		 
		 System.out.println("***** 채팅 서버 *****");
		 System.out.println("서버채팅프로그램은 "
		 		             + "클라이언트 소켓통로의 접속요청을 기다리고 있음");
	 
		 
		//+++스레드 간의 정보를 공유할 HashMap 객체 생성 +++++++
		hm = new HashMap<String, ObjectOutputStream>();
		 
		//클라이언트채팅 프로그램이 서버채팅프로그램으로 항상 접속을 할수 있도록
		//무한루프 돌림
		while(true) {
			try {
				 //클라이언트 채팅프로그램의 접속을 기다리다가 
				//만약 요청이 들어오면 요청을 받아들임
				//그리고 accept메소드는 클라이언트와 데이터를 주고받을 
				//요청한!!클라이언트 전용 소켓통로(new Socket()) 객체를 반환해줌
				 child = server.accept(); 
				 
				 //+++ 접속을 유지 하면서 데이터를 송수신하기위해서 스레드 객체 생성 +++
				 //Runnable인터페이스를 구현한 일반 클래스의 객체 생성
				 ChatServerThread childThread = new ChatServerThread(child,hm);
				 
				 Thread t = new Thread(childThread); //스레드로 만듬 
				 
				 t.start();
				 
				
			}catch(IOException e) {
				e.printStackTrace();//예외 메세지 출력하고
				System.exit(0);//서버채팅프로그램 강제 종료
			}						
		}
				 
	 }//생성자
	 
	 
	//메인스레드 
	public static void main(String[] args) {
			new ChatServer();//서버채팅프로그램 객체 생성
	}
}//-----------------------------------------------------------------

//---------------------------------------------------------------------------------

/*
	ChatServerThread 클래스 구현하기
	
이 클래스의 메소드 						역할
	ChatserverThread 생성자 			1. 클라이언트로부터 데이터를 송수신을 위해서 입출력 스트림을 얻음
									2. 아이디를 얻어와 아이디와 출력 스트림을 해쉬맵에 등록
									
	run 메소드 
	- 클라이언트로부터 수신받은 데이터를	1. 클라이언트로부터 보내진 메세지를 수신받아
	  클라이언트에게 송신하는 메소드 		2. 모든 클라이언트에게 브로드 캐스팅 한다.(broadcast)
	  								3. /to 가 입력되면 귓속말로 처리한다(sendMsg)
	  								4. /quit 가 입력되면  종료 메세지로 처리한다.
	  								
	broadcast 메소드					클라이언트로 부터 받은 메세지를 서버에 연결된 모든 클라이이언트에게 보냄
	
	sendMsg	메소드 					귓속말을 위해서 특정 클라이언트에게 메세지를 보냄 
	  								
	  								

참고. 서버가 다른 클라이언트에게 메세지를 송수신하기 위해서  컬렉션(해쉬 맵)을 가지고 있으면서
   새로운 클라이언트가 접속해 오면 해쉬맵에서 사용자 아이디와 출력 스트림을 저장해 두고
   클라이언트가 접속을 해제하면 해쉬맵에서 해당클라이언트를 삭제 합니다.
*/
class ChatServerThread implements Runnable{
	
	Socket child;   //클라이언트와 통신하기 위한 소켓

	ObjectInputStream ois;//클라이언트으로부터 데이터를 수신받기 위한 스트림
	ObjectOutputStream oos;//클라이언트에게 데이터를 송신하기 위한 스트림 
	
	String user_id;//서버채팅프로그램에 접속한 클라이언트의 아이디를 저장할 변수 
	
	//스레드간의 정보를 공유할 HashMap
	HashMap<String, ObjectOutputStream> hm;
	
	//1.생성자 : 입출력 스트림을 얻음, 아이디와 출력스트림을 해쉬맵에 등록-------
	//-> ChatServerThread생성자로 
	//   접속 요청한 클라이언트의 소켓객체와 해쉬맵이 매개변수로 전달된다.
	public ChatServerThread(Socket s, HashMap hm) {
		
		child = s;//클라이언트와 통신할수 있는 소켓객체를 저장
		this.hm = hm;
	
		try {
			//접속한 클라이언트의 아이피 주소를 바로위 
			//요청한 클라이언트 채팅프로그램과 연결되는 클라이언트 전용소켓으로 부터
			//얻는다
			System.out.println(  child.getInetAddress() 
					             + "로 부터 연결 요청받음" );
			
			//클라이언트로부터 데이터를 수신받기 위한 
			//클라이언트 PC의 채팅프로그램과 연결된 입력스트림 통로 얻기
			ois = new ObjectInputStream(child.getInputStream());
			
			//클라이언트PC의 채팅프로그램에게 데이터를 송신하기 위한 
			//출력스트림 통로 얻기
			oos = new ObjectOutputStream(child.getOutputStream());
			
			//++++++ 접속한 클라이언트의 아이디를 얻어와
			//       아이디와 출력스트림통로를 해쉬맵에 등록 +++++++

			user_id = (String)ois.readObject();
			
			//이미 서버채팅프로그램에 접속되어 있는 모든 클라이언트 채팅프로그램들에게
			//XXX아이디가 접속되었다는 메세지 전송
			boradcast(user_id + "님이 접속하셨습니다.");
			
			//여러 스레드가 공유하는 해쉬맵을 동기화
			synchronized (hm) {
				//해쉬맵에 사용자의 아이디를 key로 출력스트림객체를 value로 저장
				//모든 클라이언트에 의해 공유되어 메세지를 브로드 캐스팅을 위해
				//출력스트림을 해쉬맵에 저장 
				hm.put(user_id, oos);		
			}	
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}//생성자
	

//2. run메소드 오버라이딩
//역할 : 클라이언트 채팅프로그램으로 부터 수신받은 데이터를 다시 모든 클라이언트에게 송신함	
	@Override
	public void run() {
		
		//클라이언트 채팅프로그램으로 부터 수신받은 데이터를 저장할 문자열 변수
		String reciveData;
		
		try {
			while(true) {
				//+++ 클라이언트가 보낸 메세지를 수신 받음 +++
				
				//입력스트림을 통해 클라이언트가 보낸 메세지를 읽어옴 
				reciveData = (String)ois.readObject();
				
				//클라이언트로부터 종료메시가 들어오면 반복문을 벗어나서
				//finally구문으로 가서 코드 실행함
				if(reciveData.equals("/quit")) {
					break;
					
				//귓속말을 위해서  특정 클라리언트에게 메세지를 보냈다면
			    //sendMsg메소드를 호출한다.
				}else if(reciveData.indexOf("/to") > -1 ) {
					sendMsg(reciveData);
				
				//특정 클라이언트가 /quit 그리고 /to 이외 다른 채팅 메세지를
				//입력하면 모든 클라이언트 채팅프로그램들에게 브로드 캐스팅한다
				}else {
					
					boradcast(user_id + " : " + reciveData);
				}
				
			}
			
			
		}catch(Exception e) {
			System.out.println("클라이언트 강제 종료 ");
		}finally { // 클라이언트로부터 .quit종료 메시지를 받으면 
			
			//여러 스레드들이 공유하는 해시맵을 동기화한다.
			// 동기화 블럭 이용
			synchronized(hm) {
				// 종료 메시지를 보낸 클라이언트의 정보를 해시맵에서 삭제한 후
				hm.remove(user_id);
			}
			// 나머지 클라이언트 채팅프로그램들에게 종료메시지를 보낸 클라이언트가 접속 종료를 알림
			broadcast(user_id + "님이 나가셨습니다.");
			
			// 서버 채팅 프로그램에도 접속종료를 알리기위해 출력
			System.out.println(user_id + "님이 나가셨습니다.");
			
			try {
				// 서버채팅 프로그램에 접속하여 종료메세지를 보낸 클라이언트 채팅프로그램
				// 전용 소켓 통로 자원해제(서버채팅 프로그램과 클라이언트 채팅프로그램 접속 해제)
				if(child != null) { child.colse(); }
			} catch (Exception e) {
				e.printStackTrace();
			}
		}		
	}
	
	
	
}















