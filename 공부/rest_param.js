function myFun(a, b, ...manyMoreArgs) {
  console.log('a:', a);
  console.log('b:', b);
  console.log('Rest 파라미터 manyMoreArgs:', manyMoreArgs);
}

myFun(1, 2, 3, 4, 5);
