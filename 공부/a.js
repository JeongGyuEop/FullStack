// 예제 1.
function Animal() {}

// Animal의 프로토 타입에 eat 메서드 추가
Animal.prototype.eat = function () {
  console.log("I'm eating");
};

let dog = new Animal();
dog.eat();

console.log();
console.log('-------------------------------------------------------');
console.log();

// 예제 2.
// Person 생성자 함수 정의
function Person(name) {
  this.name = name; // name 속성을 설정
}

// Person의 프로토타입에 greet 메서드 추가
Person.prototype.greet = function () {
  console.log(`greeting: ${this.name}`);
};

// Student 생성자 함수 정의
function Student(name) {
  // Person 생성자 함수를 호출하여 name 속성 설정
  Person.call(this, name);
}

// Student가 Person을 상속받도록 프로토타입 설정
Student.prototype = Object.create(Person.prototype);

// Student 생성자의 cosntructor 속성 재설정
Student.prototype.constructor = Student;

// Student의 prototype에 study 메서드 추가
Student.prototype.study = function () {
  console.log('I am studying');
};

let student = new Student('John');
student.greet();
student.study();

console.log();
console.log('-------------------------------------------------------');
console.log();

// 예제 3.
// 생성자 함수 정의
function Car() {}

// Car의 프로토타입에 dirve 함수 추가
Car.prototype.drive = function () {
  console.log('Car is driving');
};

// ElectricCar 생성자 함수 정의
function ElectricCar() {
  // Car 생성자 함수를 호출
  Car.call(this);
}

// ElectricCar가 생성자 Car를 상속받도록 프로토타입 설정
ElectricCar.prototype = Object.create(Car.prototype);

// ElectricCar 생성자의 constructor 속성 재설정
ElectricCar.prototype.constructor = ElectricCar;

// ElectricCar의 prototype에 charge 함수 추가
ElectricCar.prototype.charge = function () {
  console.log('Electric car is charging');
};

let tesla = new ElectricCar();
tesla.drive();
tesla.charge();

// 프로토타입 체인 확인
console.log(tesla instanceof ElectricCar);
console.log(tesla instanceof Car);
console.log(tesla instanceof Object);

console.log();
console.log('-------------------------------------------------------');
console.log();

// 예제 4.
// Animal 생성자 함수 정의
function Animal() {}

Animal.prototype.speak = function () {
  console.log('I am Animal');
};

function Dog() {
  Animal.call(this);
}

// Dog가 Animal을 상속받도록 Dog.prototype 설정
Dog.prototype = Object.create(Animal.prototype);

// Dog 생성자의 constructor 속성 재설정
Dog.prototype.constructor = Dog;

// Dog의 프로토타입에 speak 메서드 오버라이드
Dog.prototype.speak = function () {
  console.log('Woof!');
};

let animal = new Animal();
animal.speak();

let dog2 = new Dog();
dog2.speak();

console.log();
console.log('-------------------------------------------------------');
console.log();

// 예제 5.
function Vehicle() {}

Vehicle.prototype.drive = function () {
  console.log('Vehicle is moving');
};

function Car() {
  Vehicle.call(this);
}

Car.prototype = Object.create(Vehicle);

Car.prototype.constructor = Car;

Car.prototype.drive = function () {
  console.log('Car is driving');
};

let hyundai = new Vehicle();
hyundai.drive();

let santafe = new Car();
santafe.drive();

//-------------------------------------------------------------
const solution = (str, n) => {
  let arr = [];
  for (let i = 0; i < str.length; i += n) {
    arr.push(str.slice(i, i + n));
  }
  return arr;
};

let str = 'abc1Addfggg4556b';
let n = 6;
console.log(solution(str, n));
