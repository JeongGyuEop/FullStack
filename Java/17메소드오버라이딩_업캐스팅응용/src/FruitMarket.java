// 과일 판매자 부모 클래스
class FruitSeller {
    String name;

    public FruitSeller(String name) {
        this.name = name;
    }

    public void sell() {
        System.out.println(name + " is selling fruits.");
    }
}

// 사과 판매자 자식 클래스
class AppleSeller extends FruitSeller {
    public AppleSeller(String name) {
        super(name);  // 부모 클래스 생성자 호출
    }

    @Override
    public void sell() {
        System.out.println(name + " is selling apples.");
    }
}

// 복숭아 판매자 자식 클래스
class PeachSeller extends FruitSeller {
    public PeachSeller(String name) {
        super(name);
    }

    @Override
    public void sell() {
        System.out.println(name + " is selling peaches.");
    }
}

// 포도 판매자 자식 클래스
class GrapeSeller extends FruitSeller {
    public GrapeSeller(String name) {
        super(name);
    }

    @Override
    public void sell() {
        System.out.println(name + " is selling grapes.");
    }
}

// 과일 구매자 부모 클래스
class FruitBuyer {
    String name;

    public FruitBuyer(String name) {
        this.name = name;
    }

    public void buy(FruitSeller seller) {
        System.out.println(name + " is buying fruits from " + seller.name);
        seller.sell();
    }
}

// 영희(구매자) 자식 클래스
class Younghee extends FruitBuyer {
    public Younghee(String name) {
        super(name);  // 부모 클래스 생성자 호출
    }

    @Override
    public void buy(FruitSeller seller) {
        System.out.println(name + " (Younghee) is buying from " + seller.name);
        seller.sell();
    }
}

// 철수(구매자) 자식 클래스
class Chulsu extends FruitBuyer {
    public Chulsu(String name) {
        super(name);  // 부모 클래스 생성자 호출
    }

    @Override
    public void buy(FruitSeller seller) {
        System.out.println(name + " (Chulsu) is buying from " + seller.name);
        seller.sell();
    }
}

public class FruitMarket {
    public static void main(String[] args) {
        // 부모 타입(과일 판매자)으로 자식 객체(사과 판매자) 생성 - 업캐스팅
        FruitSeller appleSeller = new AppleSeller("Apple Seller");
        FruitSeller peachSeller = new PeachSeller("Peach Seller");
        FruitSeller grapeSeller = new GrapeSeller("Grape Seller");

        // 부모 타입(과일 구매자)으로 자식 객체(영희) 생성 - 업캐스팅
        FruitBuyer younghee = new Younghee("Younghee");
        FruitBuyer chulsu = new Chulsu("Chulsu");

        // 부모 클래스 타입으로 메서드 호출
        younghee.buy(appleSeller);  // 업캐스팅된 appleSeller로 호출
        chulsu.buy(grapeSeller);    // 업캐스팅된 grapeSeller로 호출

        // 다운캐스팅 예제
        // appleSeller는 실제로 AppleSeller 타입이므로 다운캐스팅 가능
        if (appleSeller instanceof AppleSeller) {
            AppleSeller specificAppleSeller = (AppleSeller) appleSeller;  // 다운캐스팅
            specificAppleSeller.sell();  // "is selling apples." 출력
        }

        // peachSeller는 실제로 PeachSeller 타입이므로 다운캐스팅 가능
        if (peachSeller instanceof PeachSeller) {
            PeachSeller specificPeachSeller = (PeachSeller) peachSeller;  // 다운캐스팅
            specificPeachSeller.sell();  // "is selling peaches." 출력
        }
    }
}
