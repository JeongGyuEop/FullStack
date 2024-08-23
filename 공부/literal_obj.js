// 객체 리터럴 표기법 {}이 더 많이 사용됩니다.
//      간결함: 객체 리터럴 표기법은 코드가 짧고 읽기 쉽습니다.
//      가독성: 객체의 구조가 한눈에 들어오기 때문에 유지보수가 용이합니다.
//      성능: 일반적으로 객체 리터럴 표기법이 더 빠르게 실행됩니다.

//--------------------------------------------------------------------------------------
// 예제 1. 객체 리터럴을 사용한 프로토타입 설정
// 기본 객체 생성
let animal = {
  type: 'animal',
  sound: function () {
    console.log('Some generic sound');
  }
};

// animal 객체를 프로토타입으로 사용하여 dog 객체 생성
let dog = Object.create(animal);
dog.type = 'dog';
dog.sound = function () {
  console.log('Woof!');
};

// animal 객체를 프로토타입으로 사용하여 cat 객체 생성
let cat = Object.create(animal);
cat.type = 'cat';
cat.sound = function () {
  console.log('Meow!');
};

// 호출 예시
dog.sound();
cat.sound();
