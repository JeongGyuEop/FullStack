

// 주제 : FilterInputStream 부모클래스의 하위 DataInputStream 클래스를 사용한 예

// DataInputStream 클래스
// -> 기본자료형(int, byte, char, double, float등)읠 바이트 크기 단위로 
//    데이터를 읽을 수 있는 입력스트림 통로 역할의 객체를 생성할 때 사용하는 클래스로
//    기본 자료형 byte단위로 읽거나 쓸 수 있는 메소드들을 제공한다.

import java.io.DataInputStream;
import java.io.FileInputStream;

public class DataStreamTest2 {

	public static void main(String[] args) throws Exception {
		
		// data.txt 파일에 기록된 데이터들을 읽어와 저장할 변수들
		char c;
		byte b;
		short s;
		int i;
		float f;
		double d;
		String str;
		
		// data.txt파일에 기록된 데이터들을 읽어들일 스트림 통로 생성
		DataInputStream dis = new DataInputStream(new FileInputStream("data.txt"));
		
		c = dis.readChar();
		b = dis.readByte();
		s = dis.readShort();
		i = dis.readInt();
		f = dis.readFloat();
		d = dis.readDouble();
		str = dis.readUTF();
		
		System.out.println(c);
		System.out.println(b);		
		System.out.println(s);
		System.out.println(i);
		System.out.println(f);
		System.out.println(d);
		System.out.println(str);
			
	}

}
