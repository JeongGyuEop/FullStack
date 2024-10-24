package Vo;

// 역할 : 예약한 정보를 DB에서 조회해 와서 저장 후 제공할 클래스

// 참고. 2개의 테이블(carlist 테이블, non_carorder 테이블)을 자연 조인하여 
//       조회된 열들의 결과데이터를 함께 얻자.

public class CarConfirmVo {

	// carlist 테이블에서 조회된 열의 정보들을 저장할 변수
	private String carname, carimg;
	private int carprice;
	
	// non_carorder 테이블에서 조회된 열의 정보들을 저장할 변수
	private int orderid, carreserveday, carqty,
				carins, carwifi,carnave, carbabyseat;
	private String carbegindate;
	

	// getter, setter
	public String getCarname() {
		return carname;
	}
	public void setCarname(String carname) {
		this.carname = carname;
	}
	public String getCarimg() {
		return carimg;
	}
	public void setCarimg(String carimg) {
		this.carimg = carimg;
	}
	public int getCarprice() {
		return carprice;
	}
	public void setCarprice(int carprice) {
		this.carprice = carprice;
	}
	public int getOrderid() {
		return orderid;
	}
	public void setOrderid(int orderid) {
		this.orderid = orderid;
	}
	public int getCarreserveday() {
		return carreserveday;
	}
	public void setCarreserveday(int carreserveday) {
		this.carreserveday = carreserveday;
	}
	public int getCarqty() {
		return carqty;
	}
	public void setCarqty(int carqty) {
		this.carqty = carqty;
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
	public String getCarbegindate() {
		return carbegindate;
	}
	public void setCarbegindate(String carbegindate) {
		this.carbegindate = carbegindate;
	}
	
	
}
