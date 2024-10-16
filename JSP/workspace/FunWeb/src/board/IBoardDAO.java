package board;

import java.util.Vector;

public interface IBoardDAO { // 각 기능별로 추상메소드들의 이름을 정해 놓고 작성
	
	// DB에 저장된 모든 글들을 조회해서 Vector 배열에 담아 반환하는 추상메소드
							//   검색 기준값  ,     검색어         를 매개변수로 받음
	public Vector getBoardList(String keyField, String keyword);

	
	// DB에 작성한 새 글정보를 추가(글쓰기) 하는 기능의 추상메소드
						// 작성한 하나의 새 글정보를 BoardBean 객체에 담아 매개변수로 받는다.
	public void insertBoard(BoardBean boardBean);
	
	// DB에 작성되어 있는 하나의 글정보를 수정하는 기능의 추상메소드
	public void updateBoard(BoardBean boardBean);
	// 수정한 하나의 새 글정보를 BoardBean 객체에 담에 매개변수로 받음
	// 참고. 수정할 글의 글번호는 BoardBean 객체에 담아 매개변수로 받는다.
	
	public void deleteBoard(int num, String passwd, String id);
	
	public void replyBoard(BoardBean boardBean);
	
	
}
