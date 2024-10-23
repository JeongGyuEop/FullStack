package Vo;

// VO 역할을하는 조회한 차량 한대의 정보를 저장할 용도 또는 
// 차량 정보 한 대의 정보를 DB의 CarList 테이블에 추가할 용도의 클래스
public class CarListVo {

	private int carno; // 차량 번호
	private String carname; // 차량 명
	private String carcompany; // 차량 제조사 명(현대, 기아 등)
	private int carprice; // 차량 한 대당 렌트 가격
	private int carusepeople; // 차량 인승 정보(4인승, 6인승)
	private String carinfo; // 차량 상세 정보
	private String carimg; // 차량 이미지 명
	private String carcategory; // 차량의 유형(소형:Small, 중형:Mid, 대형:Big)
	
	public CarListVo() { } // 기본생성자
	
	// CarListVo 클래스의 객체 생성시 모든 인스턴스 변수값 초기화할 생성자
	public CarListVo(int carno, String carname, String carcompany, int carprice, int carusepeople, String carinfo,
			String carimg, String carcategory) {
		super();
		this.carno = carno;
		this.carname = carname;
		this.carcompany = carcompany;
		this.carprice = carprice;
		this.carusepeople = carusepeople;
		this.carinfo = carinfo;
		this.carimg = carimg;
		this.carcategory = carcategory;
	}

	public int getCarno() {
		return carno;
	}

	public void setCarno(int carno) {
		this.carno = carno;
	}

	public String getCarname() {
		return carname;
	}

	public void setCarname(String carname) {
		this.carname = carname;
	}

	public String getCarcompany() {
		return carcompany;
	}

	public void setCarcompany(String carcompany) {
		this.carcompany = carcompany;
	}

	public int getCarprice() {
		return carprice;
	}

	public void setCarprice(int carprice) {
		this.carprice = carprice;
	}

	public int getCarusepeople() {
		return carusepeople;
	}

	public void setCarusepeople(int carusepeople) {
		this.carusepeople = carusepeople;
	}

	public String getCarinfo() {
		return carinfo;
	}

	public void setCarinfo(String carinfo) {
		this.carinfo = carinfo;
	}

	public String getCarimg() {
		return carimg;
	}

	public void setCarimg(String carimg) {
		this.carimg = carimg;
	}

	public String getCarcategory() {
		return carcategory;
	}

	public void setCarcategory(String carcategory) {
		this.carcategory = carcategory;
	}
	
	
	
}
