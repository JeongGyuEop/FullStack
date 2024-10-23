package Vo;

// 렌트한 정보를 저장하는 VO클래스
public class CarOrderVo {

	private int orderid; // 렌트 예약한 ID
	private String id;
	
	// 차 번호를 이용해 2개의 테이블(carlist테이블, non_carorder테이블)의 열정보를
	// JOIN 하여 조회하기 위한 값
	private int carno; // 렌트한 차 번호
	
	private int carqty; // 대여 수량
	private int carreserveday; // 렌트 대여 기간
	private String carbegindate; // 렌트 대여 시작날짜
	
	private int carins; // 보험 적용여부 1(적용) 또는 0(미적용)이 저장
	private int carwifi; // 무선 wifi 적용 여부
	private int carnave; // 네비게이션 적용 여부
	private int carbabyseat; // 베이비시트 적용 여부
	
	private String memberphone; // 비회원으로 예약시 입력한 폰 번호 저장
	private String memberpass; // 비회원으로 예약시 입력한 비밀번호 저장
	
	// 기본 생성자
	public CarOrderVo() { }
	
	// 모든 인스턴스 변수 값 초기화할 생성자
	public CarOrderVo(int orderid, String id, int carno, int carqty, int carreserveday, String carbegindate, int carins,
			int carwifi, int carnave, int carbabyseat, String memberphone, String memberpass) {
		super();
		this.orderid = orderid;
		this.id = id;
		this.carno = carno;
		this.carqty = carqty;
		this.carreserveday = carreserveday;
		this.carbegindate = carbegindate;
		this.carins = carins;
		this.carwifi = carwifi;
		this.carnave = carnave;
		this.carbabyseat = carbabyseat;
		this.memberphone = memberphone;
		this.memberpass = memberpass;
	}


	// getter, setter
	public int getOrderid() {
		return orderid;
	}

	public void setOrderid(int orderid) {
		this.orderid = orderid;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getCarno() {
		return carno;
	}

	public void setCarno(int carno) {
		this.carno = carno;
	}

	public int getCarqty() {
		return carqty;
	}

	public void setCarqty(int carqty) {
		this.carqty = carqty;
	}

	public int getCarreserveday() {
		return carreserveday;
	}

	public void setCarreserveday(int carreserveday) {
		this.carreserveday = carreserveday;
	}

	public String getCarbegindate() {
		return carbegindate;
	}

	public void setCarbegindate(String carbegindate) {
		this.carbegindate = carbegindate;
	}

	public int getCarins() {
		return carins;
	}

	public void setCarins(int carins) {
		this.carins = carins;
	}

	public int getCarwifi() {
		return carwifi;
	}

	public void setCarwifi(int carwifi) {
		this.carwifi = carwifi;
	}

	public int getCarnave() {
		return carnave;
	}

	public void setCarnave(int carnave) {
		this.carnave = carnave;
	}

	public int getCarbabyseat() {
		return carbabyseat;
	}

	public void setCarbabyseat(int carbabyseat) {
		this.carbabyseat = carbabyseat;
	}

	public String getMemberphone() {
		return memberphone;
	}

	public void setMemberphone(String memberphone) {
		this.memberphone = memberphone;
	}

	public String getMemberpass() {
		return memberpass;
	}

	public void setMemberpass(String memberpass) {
		this.memberpass = memberpass;
	}
		
}
