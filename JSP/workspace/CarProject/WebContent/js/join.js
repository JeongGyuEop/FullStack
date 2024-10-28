
// 		$("input").keyup(function(){
// 	    	//모든<p>요소들의 text값을 빈공백으로 설정해 없애 준다.
// 	    	$("p").text("");
// 	    });
	    
	  	//약관동의 체크박스를 클릭했을때..
	    $("#agree").click(function(){
	    	
    		if( !($("#agree").is(":checked")) ){ 
   	    		$("#agreeInput").text("약관에 동의해 주세요!").css("color","red");
   	    		
   	    	}else{
   	    		$("#agreeInput").text("약관동의 완료!").css("color","blue");
   	    	}
	   	});
	   
	    $("#id").focusout(function() {
				
	    	// 제대로 입력 했을 경우
	    	if($("#id").val().length >= 3 && $("#id").val().length < 20 ){
	    		
	    		// 회원가입을 위해 입력한 아이디가 DB의 member 테이블에 저장되어 있는지 확인 요청
	    		// Ajax 기술을 이용하여 비동기 통신으로 MemberController에 요청한다.
	    		$.ajax({
	    			
	    			url: "http://localhost:8090/CarProject/member/joinIdCheck.me",
	    			
	    			type: "POST", // 요청 방식 POST 설정
	    			
	    			async: true, // true는 비동기, false는 동기 요청
	    			
	    			data: {id: $("#id").val() }, // MemberController 서버페이지에 요청할 값 설정
	    				// 키 값(id)은 MemberService 클래스에서 String id = request.getParameter("id"); 하여 값을 가져온다.
	    			
	    			dataType: "text", // MemberController 서버 페이지로 부터 예상 응답받을 데이터 종류 설정
	    			
	    			// 요청 통신에 성공했을 때 success 속성에 설정된 콜백함수가 자동으로 호출되어 
	    			// data 매개변수로 MemberController 서버 페이지가 응답한 데이터가 넘어온다.
	    			success: function (data, textStatus) {
	    							// "not_usable" 또는 "usable" 둘 중 하나를 
	    							// data 매개변수로 받는다.
	    				// Membercontroller 서버 페이지에서 전송된 아이디 중복인지 아닌지 판단하여
	    				// 현재 join.jsp 중앙화면에 보여주는 구문 처리
	    				if(data == 'usable') {
	    					$("#idInput").text("사용할 수 있는 ID 입니다.").css("color", "blue");
	    				} else { // 아이디가 DB에 있으면? (입력한 아이디 중복)
	    					$("#idInput").text("이미 사용중인 ID 입니다.").css("color", "red");
	    				}
	    			}
	    		});
	    		
	    	// 제대로 입력하지 않았을 경우
    		}else{
    			$("#idInput").text("한글,특수문자 없이 3~20글자사이로 작성해 주세요!").css("color","red");
    		}
		});
	    
		$("#pass").focusout(function(){
			if($("#pass").val().length < 4 ){
    			$("#passInput").text("한글,특수문자 없이 4글자 이상으로 작성해 주세요!").css("color","red");
    		}else{
    			$("#passInput").text("올바르게 입력되었습니다.").css("color","blue");
    		}
		});
	    
		$("#name").focusout(function(){
	    	if($("#name").val().length < 2 || $("#name").val().length > 6 ){
    			$("#nameInput").text("이름을 제대로 작성하여주세요.").css("color","red");
    		}else{
    			$("#nameInput").text("이름입력완료!").css("color","blue");
    		}
	    });
	    
		$("#age").focusout(function() {
	    	if($("#age").val() == ""){
    			$("#ageInput").text("나이를 입력해주세요.").css("color","red");
    			
    		}else{
    			$("#ageInput").text("나이입력완료!").css("color","blue");
    		}
	    });
	   
		$(".gender").click(function() {
	    	$("#genderInput").text("성별체크완료!").css("color","blue");
	    });
	   
		$("#email").focusout(function() {
			var mail = $("#email");
    		var mailValue = mail.val();
    		var mailReg = /^\w{5,12}@[a-z]{2,10}[\.][a-z]{2,3}[\.]?[a-z]{0,2}$/;
    		var rsEmail = mailReg.test(mailValue);
    		
	    	if(!rsEmail){
    			$("#emailInput").text("이메일 형식이 올바르지 않습니다.").css("color","red");
    			
    		}else{
    			$("#emailInput").text("올바르게 입력되었습니다.").css("color","blue");
    		}
	    });
	    
		$("#tel").focusout(function() {
			var t = $("#tel");
    		var telVal = t.val();
    		var tReg = RegExp(/^0[0-9]{8,10}$/);
    		var rsTel = tReg.test(telVal);
    		if(!rsTel){
    			$("#telInput").text("전화번호 형식이 올바르지 않습니다.").css("color","red");
    		}else{
    			$("#telInput").text("올바르게 입력되었습니다.").css("color","blue");
    		}	
	    });
	    
		$("#hp").focusout(function() {
			var p = $("#hp");
    		var pValue = p.val();
    		var pReg = RegExp(/^01[0179][0-9]{7,8}$/);
    		var resultP = pReg.test(pValue);
    		if(!resultP){
    			$("#hpInput").text("휴대폰번호 형식이 올바르지 않습니다.").css("color","red");
    		}else{
    			$("#hpInput").text("올바르게 입력되었습니다.").css("color","blue");
    		}	
	    });
// 	   $("#sample4_extraAddress",
// 			   "#sample4_postcode",
// 			   "#sample4_roadAddress",
// 			   "#sample4_jibunAddress",
// 			   "#sample4_detailAddress").focusout(function() {
//     		var adVal1 = $("#sample4_postcode").val();
//     		var adVal2 = $("#sample4_roadAddress").val();
//     		var adVal3 = $("#sample4_jibunAddress").val();
//     		var adVal4 = $("#sample4_detailAddress").val();
//     		var adVal5 = $("#sample4_extraAddress").val();
//     		if(adVal1 == "" || adVal2 == "" || adVal3 == "" || adVal4 == "" || adVal5 == ""){
//     			$("#addressInput").text("주소를 모두 작성하여주세요.").css("color","red");
//     		}else{
//     			$("#addressInput").text("올바르게 입력되었습니다.").css("color","blue");
//     		}
// 	    });
	    
		$("input[name='address1'],input[name='address2'],input[name='address3'],input[name='address4'],input[name='address5']").focusout(function() {
		    		if(	$("input[name='address1']").val()== "" || 
	    				$("input[name='address2']").val()== "" ||
	    				$("input[name='address3']").val()== "" ||
	    				$("input[name='address4']").val()== "" ||
	    				$("input[name='address5']").val()== "" ){
	    			$("#addressInput").text("주소를 모두 작성하여주세요.").css("color","red");
	    		}else{
	    			$("#addressInput").text("올바르게 입력되었습니다.").css("color","blue");
	    		}
		    });
	
	
		function check() {
			
			//약관동의 <input>요소를 선택해서 가져와 
   	    	var checkbox = $("#agree");
   	    	//약관동의 체크했는지 검사
   	    	//선택한 <input type="checkbox">체크박스에 체크가 되어 있지 않으면? 
   	    	//true를 리턴 해서 조건에 만족 합니다. 
   	    	if( !(checkbox.is(":checked")) ){ //== 같은 true값을 반환 한다. if(!$("#agree").prop("checked"))
   	    		$("#agreeInput").text("약관에 동의해 주세요!").css("color","red");
   	    		
   	    		return false;
   	    	}
   	    	//====================================================================================================
   	    		
   	    	var id = $("#id");
   	    	var idValue = id.val();
   	    	
   	    	var idReg = RegExp(/^[A-Za-z0-9_\-]{3,20}$/);
   	    	var resultId = idReg.test(idValue);
   	    	
    		if(!resultId){
    			$("#idInput").text("한글,특수문자 없이 3~20글자사이로 작성해 주세요!").css("color","red");
    			id.focus();
    			
    			return false;
    		}
   	    	
    		//====================================================================================================
	    		
   	    	var pass = $("#pass");
   	    	var passValue = pass.val();
   	    	
   	    	var passReg = RegExp(/^[A-Za-z0-9_\-]{4,20}$/);
   	    	var resultPass = passReg.test(passValue);

    		if(!resultPass){
    			$("#passInput").text("한글,특수문자 없이 4글자 이상으로 작성해 주세요!").css("color","red");
    			pass.focus();
    			
    			return false;
    		}else{
    			$("#passInput").text("올바르게 입력되었습니다.").css("color","blue");
    		}
    		
    		//====================================================================================================

    		var name = $("#name");
   	    	var nameValue = name.val();
   	    	
   	    	var nameReg = RegExp(/^[가-힣]{2,6}$/);
   	    	var resultName = nameReg.test(nameValue);

    		if(!resultName){
    			$("#nameInput").text("이름을 한글로 작성하여주세요.").css("color","red");
    			name.focus();
    			
    			return false;
    		}else{
    			$("#nameInput").text("올바르게 입력되었습니다.").css("color","blue");
    		}
    		
    		//====================================================================================================
   	    	
    		var age = $("#age");
   	    	var ageValue = age.val();

    		if(ageValue == ""){
    			$("#ageInput").text("나이를 입력해주세요.").css("color","red");
    			age.focus();
    			
    			return false;
    		}else{
    			$("#ageInput").text("올바르게 입력되었습니다.").css("color","blue");
    		}
   	    	
    		//====================================================================================================

    		var address1 = $("#sample4_postcode");
    		var address2 = $("#sample4_roadAddress"); 
    		var address3 = $("#sample4_jibunAddress")
    		var address4 = $("#sample4_detailAddress");
    		var address5 = $("#sample4_extraAddress");
    		var addVal1 = address1.val();
    		var addVal2 = address2.val();
    		var addVal3 = address3.val();
    		var addVal4 = address4.val();
    		var addVal5 = address5.val();
    		if(addVal1 == "" || addVal2 == "" || addVal3 == "" || addVal4 == "" || addVal5 == ""){
    			$("#addressInput").text("주소를 모두 작성하여주세요.").css("color","red");
    			address5.focus();
    			
    			return false;
    		}else{
    			$("#addressInput").text("올바르게 입력되었습니다.").css("color","blue");
    		}
    			
    		//====================================================================================================
	
    		var gender = $(".gender:checked");
    		var genderValue = gender.val();
    		genderValue = $.trim(genderValue);
    		if (genderValue == ""){
    			
    			$("#genderInput").text("성별을 체크 해주세요.").css("color","red");
    			
    			return false;
    			
    		}
    		//====================================================================================================
			
			var email = $("#email");
    		
    		var emailValue = email.val();
    		
    		var emailReg = /^\w{5,12}@[a-z]{2,10}[\.][a-z]{2,3}[\.]?[a-z]{0,2}$/;
    		
    		var resultEmail = emailReg.test(emailValue);
    		
    		if(!resultEmail){
    			$("#emailInput").text("이메일 형식이 올바르지 않습니다.").css("color","red");
    			
    			email.focus();
    			
    			return false;
    		}else{
    			$("#emailInput").text("올바르게 입력되었습니다.").css("color","blue");
    		}	
    			
			//====================================================================================================
			
			var tel = $("#tel");
    		
    		var telValue = tel.val();
    		
    		var telReg = RegExp(/^0[0-9]{8,10}$/);
    		
    		var resultTel = telReg.test(telValue);
    		
    		if(!resultTel){
    			$("#telInput").text("전화번호 형식이 올바르지 않습니다.").css("color","red");
    			
    			tel.focus();
    			
    			return false;
    		}else{
    			$("#telInput").text("올바르게 입력되었습니다.").css("color","blue");
    		}	
    		
			//====================================================================================================
			
			var hp = $("#hp");
    		
    		var hpValue = hp.val();
    		
    		var hpReg = RegExp(/^01[0179][0-9]{7,8}$/);
    		
    		var resultHp = hpReg.test(hpValue);
    		
    		if(!resultHp){
    			
    			$("#hpInput").text("휴대폰번호 형식이 올바르지 않습니다.").css("color","red");
    			
    			hp.focus();
    			
    			return false;
    		}else{
    			$("#hpInput").text("올바르게 입력되었습니다.").css("color","blue");
    		}	
    		
   	    	
    		// <form action="<%=contextPath %>/member/joinPro.me">으로 회원가입 요청!
   	    	$("form").submit();
   	    	
			
		} // check 함수 종료
	
