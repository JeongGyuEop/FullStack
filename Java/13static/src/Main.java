
//회원 한사람의 정보를 관리하는 클래스 Member 만들기
class Member{
	//회원의 이름을 문자열로 저장하는  인스턴스변수 name선언
	private String name;

	//회원의 이메일을 문자열로 저장하는 인스턴스변수 email선언
	private String email;
	
	//회원의 고유 ID를 정수로 저장하는 인스턴스변수 id선언
	private int id;
	
	//회원 ID를 자동 생성하기 위한 클래스변수 idCounter선언 후 1로 초기화해서 선언
	//참고. 모든 회원에게 고유한 ID를 부여하기 위한 변수 입니다
	private static int idCounter = 1;


	//생성자 : 이름과 이메일을 매개변수로 받아서 새로운 회원의 인스턴스변수값 초기화
	public Member(String name, String email) {
		//구현할 코드 내용
		//1. 매개변수로 받은 이름을 인스턴스변수 name에저장
		this.name = name;
		
        //2. 매개변수로 받은 이메일을 인스턴스변수 email에 저장
		this.email = email;
		
		//3. 고유  ID를  id인스턴스변수에 저장하고,
        //   다음회원을 위해 idCounter클래스변수값을 1증가
		this.id = Member.idCounter++;
		
	}
		
 	//회원 ID를 반환하는 인스턴스getId메소드 만들기
	public int getId() {
		return this.id;
	}
		
	//회원의 ID와 이름, 이메일 정보를 출력하는 기능의 displayInfo인스턴스메소드 만들기
	public void displayInfo() {
		//출력형식
		//"ID: admin"
		//"Name: 홍길동"
		//"Email: xxx@naver.com"
		System.out.println("ID: " + this.id);
		System.out.println("Name: " + this.name);
		System.out.println("Email: " + this.email);
		
	}
}
	

//-----------------------------------------------------------------------

//회원 관리를 담당하는 MemberManager클래스 만들기
class MemberManager {
	//Member객체들을 10개 생성해서 저장시킬 members배열 생성
    //단! static을 붙여 참조변수 members를 만들고 
	//    최대 10명의 Member객체 저장가능하게
	private static Member[] members = new Member[10];

	//현재 members배열에 저장된 Member객체들의 수를 카운팅하는 
	//클래스변수 memberCount만들고  0으로 초기화
	private static int memberCount = 0;

	//새로운 Member객체(회원) 한명을 매개변수로 받아
	//members배열 공간에 있을때만 현재 Member객체(회원)수 index위치에 새로운 회원추가
	//memberCount클래스변수값을 1증가 시켜 회원수 증가 시키는 기능의 
    //addMember 클래스메소드 만들기
	public static void addMember(Member member) {
		//members배열 공간에 있을때만 
		//현재 회원수(memberCount)가 members배열의 길이보다 작을 경우에만
		//members배열에 Member객체 추가 
		if(memberCount < members.length) {
			//현재 Member객체(회원)수 index위치에 새로운 회원추가
			members[memberCount] = member;
			//memberCount클래스변수값을 1증가 시켜 회원수 증가
			memberCount++;
		} else {
			//members배열에 Memember객체 10개가 모두 존자해서 가득차면
			//새로운 Member객체를 생성해서 members배열에 추가할수없다
			System.out.println("Cantnot add more members. "
					+ "Array is full");
		}
		
	}//addMember메소드 닫는 중괄호 }

    //회원 ID하나를 매개변수로 전달 받아 전달받은 회원 ID로 특정 회원을 검색하여
	//반환하는 기능의 getMember클래스메소드 만들기
		//구현내용
		//memberCount클래스변수값(총회원수)만큼 반복하여 끝까지 순회하는데..
		//만약 매개변수로 전달받은 ID와 
		//members배열의 각 인덱스 위치의 회원아이디가 같으면(검색되는것임)
		//해당 Member객체(회원)을 반환 
	public static Member getMember(int id) {
		
		for(int i=0;  i<memberCount; i++) {
			
			if(id == members[i].getId()) {
				//해당 Member객체(회원)을 반환
				return members[i];
			}
		}
		//일치하는 회원Member객체의 id가 없으면 null을 반환
		return null;		
		
	}//getMember메소드 중괄호 } 닫기 
	
	
	//회원 ID하나를 매개변수로 전달 받아 전달받은 회원 ID로 특정 회원을 삭제하는
	//기능의 removeMember 클래스 메소드 만들기
	public static void removeMember(int id) {
//구현내용
	//바깥쪽 for반복문 사용
	for(int i=0;  i<memberCount; i++) {	
		//memberCount클래스변수값(총회원수)만큼 반복하여 끝까지 순회하는데..
		//만약 매개변수로 전달받은 ID와 
		//members배열의 각 인덱스 위치의 회원아이디가 같으면(검색되는것임)
		if(id == members[i].getId()) {
		//안쪽 for반복문 사용
			for(int j=i;  j<memberCount-1; j++) {
			//members배열에서 해당 Member객체(회원)를 삭제하고, 
			//나머지 Member객체들을 members배열의 각 index위치 칸을 한칸씩 이동하여 저장
				members[j] = members[j+1];
			}
		//안쪽 for반복문 밖에 작성
			//members배열의 마지막 index위치를 null값으로 설정
			members[memberCount - 1] = null;
			//회원수 감소를 위해 memberCount클래스변수값 1차감
			memberCount--;
			//회원 삭제 완료 메세지 "Member with ID " + id + " removed."
			System.out.println("Member with ID " + id + " removed.");  		
			return;//메소드 종료
		}
	}		
	//바깥쪽 for반복문 밖에 작성
		 // ID와 일치하는 회원이 없을 경우 오류 메시지 출력
		 System.out.println("No member found with ID " + id); 
		
	}//------------------------------------------------------------


	// 모든 회원의 정보를 출력하는 기능의 클래스 메서드
    public static void displayAllMembers() {
    	
    	// 배열에 회원이 없을 경우
        if (memberCount == 0) { 
        	// 회원이 없다는 메시지 출력
            System.out.println("No members found."); 
            
        } else {
        	//배열에 저장된 모든 회원(Member객체들)을 순회하면서 반복해서 출력
            for (int i = 0; i < memberCount; i++) {  // 현재 저장된 회원 수만큼 순회
            	// 각 회원의 정보를 출력
            	members[i].displayInfo();          
            	// 회원 정보 사이에 빈 줄 추가
            	System.out.println();                
            }
        }
    }//displayAllMembers() 클래스 메소드 닫는 중괄호 }
    
}//MemberManager클래스 닫는 중괄호 }	


//프로그램 실행의 진입점이 되는 클래스 
public class Main {

	//클래스 메소드 이자
	//프로그램의 시작하는 기능의 main메소드 
	public static void main(String[] args) {
		
        // 새로운 회원들을 생성
        Member member1 = new Member("John Doe", "john@example.com");  // 첫 번째 회원 생성
        Member member2 = new Member("Jane Doe", "jane@example.com");  // 두 번째 회원 생성
        Member member3 = new Member("Alice Smith", "alice@example.com"); // 세 번째 회원 생성

        // 생성한 회원들을 회원 관리 배열에 추가
        MemberManager.addMember(member1);
        MemberManager.addMember(member2);
        MemberManager.addMember(member3);

        // 모든 회원의 정보를 출력
        System.out.println("All Members:");
        MemberManager.displayAllMembers();

        // 특정 ID로 회원을 검색
        int searchId = 2;  // 검색할 회원 ID 설정
        Member searchedMember = MemberManager.getMember(searchId);
        if (searchedMember != null) {  // 검색된 회원이 있을 경우
            System.out.println("Member Found with ID " + searchId + ":");
            searchedMember.displayInfo();  // 회원 정보 출력
        } else {
            System.out.println("No member found with ID " + searchId); // 해당 ID의 회원이 없을 경우
        }

        // 특정 ID의 회원을 삭제
        int deleteId = 2;  // 삭제할 회원 ID 설정
        MemberManager.removeMember(deleteId);

        // 삭제 후 남은 모든 회원의 정보를 다시 출력
        System.out.println("\n All Members After Deletion:");
        MemberManager.displayAllMembers();
	}
}



