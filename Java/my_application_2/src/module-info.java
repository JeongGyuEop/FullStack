module my_application_2 {
	// 직접적 의존 관계를 주석 처리
//	requires my_module_a;
	
	// 직접적 의존 관계를 주석 처리
//	requires my_module_b;
	
	// my_module 모듈에만 의
	requires my_module;
}