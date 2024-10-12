module my_module_a {
	exports pack1;
//	exports pack2;
	
	// my_module_b 모듈 의존 설정
	requires transitive my_module_b;
}