$(function(){
/*
 주제 : Show & Hide 로그인폼 만들기
 
 - 화면상단에 [로그인]버튼을 눌렀을때, 화면 상단 바깥에 숨겨져 있던
 로그인 폼이 위에서 내려와 화면에 나타나도록 만들자.

 -그리고 로그인 폼에 [close(닫기)]버튼을 눌렀을때는 
 폼이 다시 화면 상단 바깥으로 이동되어 숨겨지도록 만들자.
 
 -또한 로그인 폼의 아이디 및 비밀번호 입력 요소에 안내가이드도 만들자
*/ 

  //class="login_wrap"인  <li>부모요소의 직전 하위 <a>요소를 선택해서 
  //click이벤트 등록
  $(".login_wrap>a").on("click",() => {
/*
  참고.  아이디와 비밀번호를 입력할수 있는 <form>요소영역은
        common.css파일에   #login_f {   top: -500px;  } 설정되어 있으므로
        원래 위치에서 위쪽으로 500px만큼 이동된 상태로 설정되어 있다

        그러므로 <form id="login_f">요소 영역을 0.5초만에 top속성의값을 20px로 설정해서
        위쪽 상단의 20px 위치로 이동되게 하여 내려오게 하는 동작(애니메이션)을
        설정할수 있다.
*/
        $("#login_f").animate({top:"20px"}, 500);
        //따라서 <form>요소영역이 상단에서 -500px만큼 이동한 위치에서
        //20px만큼 상승하여 화면에 표시됩니다.

        return false; //<a> 태그의 href속성의 주소로 이동되는것을 차단 
  });

  /*  
    <form>요소 영역 내부에 있는 
    close버튼 영역을 선택해서(class="login_close_btn인 <p></p>")그 하위
    <a>요소 최종 선택하고  그룹선택자 , 를 이용해 다중 선택해서 가져오되
    로그인 이미지영역(<input alt='로그인버튼'>)또한 선택해서 가져와
    click이벤트를 등록하여  클릭하면 <form>요소 전체 영역이 위쪽으로 이동되는
    동작을 줘서 다시 숨겨지게 만들자
  */
  $(".login_wrap .login_close_btn>a, input[alt='로그인버튼']").on("click", () => {

    //id="login_f"인 <form>요소 전체 영역을 다시 선택해서
    //0.5초만에 상단 문서 밖 화면으로 위로 이동되게 하는 동작을 처리하자.
      $("#login_f").animate({top:"-500px"}, 500);

      return false;
  });

/*
  //아이디, 비밀번호를 입력할수 있는 <input>태그 2개를 다중 선택해서  
  //포커스 이벤트 등록
  $("#user_id,#user_pw").on("focus",function(){

    //$(this) -> focus이벤트가 발생한 두개의 아이디, 비밀번호 <input>중 하나를 선택
    //$(this).prev() -> focus이벤트가 발생한 <input>의 이전에 작성한 <label>태그를 최종선택
    //css("left","-9999px"); 로 설정하여 전체 홈페이지 화면 바깥 왼쪽영역으로
    //이동 시켜 보이지 않게 설정
    $(this).prev().css("left","-9999px");

  });

  //아이디, 비밀번호를 입력할수 있는 <input>태그 2개를 다중선택해서
  //blur이벤트(포커스를 잃은 동작시점) 등록
  $("#user_id,#user_pw").on("blur",function(){

    //아이디, 비밀번호 <input> 중에서 포커스를 잃은 이벤트가 발생한
    //<input>을 선택해서 만약 입력한 값이 없다면?
    if($(this).val() === ""){
        //힌트 이미지 영역 <label>영역에 left속성값이 -9999px로 이동되었다가
        //다시 보이게 하기 위해 left속성값을 2px로 적용하여
        //안내가이드 힌트 이미지가 다시 보이도록 설정
        $(this).prev().css("left","2px");
    }

  });
*/
/*
//아이디, 비밀번호 입력할수 있는 <input> 2개를 다중 선택해서
//on메소드를 이용해 focus이벤트와 blur이벤트를 각각 등록하고
//각각 이벤트가 발생하면 각각 다른 처리를 할수 있도록 위 주석의 코드처럼 작성되게
$("#user_id,#user_pw").on(
                          {
                            focus:function(){
                              $(this).prev().css("left","-9999px");
                            },
                            blur:function(){
                              if($(this).val() === ""){
                                $(this).prev().css("left","2px");
                              }
                            }
                          }
                          );
*/

$("#user_id,#user_pw").on("focus blur",function(event){
   /*
    event.type은 발생한 이벤트의 종류를 나타내는 문자열을 얻을수 있다.
    예를 들어 사용자가 <input>요소에 포커스를 주면 event.type은 
    "focus"가 되고, <input>요소에 포커스를 잃으면 "blur"가 됩니다.
   */
   if( event.type === "focus"){ //포커스 이벤트가 발생했을때 실행
     
        //포커스된 <input>의 이전 요소(힌트이미지영역)<label>영역을 왼쪽으로 완전히 숨김
        $(this).prev().css("left","-9999px");

   }else if(event.type === "blur" && $(this).val() === ""){

       //blur이벤트가 발생한 <input>에 입력하지 않은 경우 
       //힌트 이미지 영역 <label>을 다시 표시합니다.
       $(this).prev().css("left","2px");
   }

});

//-----------------------------------------------------------------------------------------------------

/*
 주제 :  ZOOM 버튼 만들기
 [+]를 클릭하면 화면이 확대 되고,
 [-]를 클릭하면 화면이 축소 됩니다.
 그리고 [100]을 눌렀을때는 원래 100%사이즈로 되돌리도록 만들기
 
 참고 : zoom 버튼을 클릭 했을때 화면 확대/축소를 적용하기 위해서는 
 		    CSS속성중에 zoom속성과 transform의 scale속성 사용법을 잘 알고 있어야 합니다.
 		
 		이때 웹브라우저 마다 CSS속성이 모두 정상적으로 작동하지 않으므로 
    구분해서 사용 해야 하는데,
 		zoom속성의 경우에는 현재 파이어폭스를 제외한 모든 브라우저에서 지원하고 있습니다.
 		
 		그리고 transform의 scale속성은 현재 IE8 이하를 제외한 모든 브라우저에서 지원 하고 있습니다.
 
 		1.제이쿼리의 zoom속성 사용법
 			문법-> $(요소선택).css("zoom", "확대비율%");
 			
 			예-> $("body").css("zoom","200%");
 			 	//<body>영역의 모든 태그요소가 2배로 확대 됩니다.
 		
 		2.제이쿼리의 transform의 scale속성 사용법	 	
 			문법-> $(요소선택).css("transform","scale(확대비율)")
 			
 			예-> $("body").css("transform","scale(2)")
 				//화면에서 <body>영역의 모든 태그요소가 2배로 확대 됩니다.
 				
 			참고 :  	 transform의 scale속성을 이용해 보면 확대 기준점이 가운데로 지정되어 있어
 			         사방으로 퍼져 확대됨.
 			         확대 기준점을 바꾸고 싶으면 CSS에 transform-origin속성을 사용 하면됨.
 		
 		3.제이쿼리의 transform-origin속성 사용법
 			문법-> $(요소선택).css("transform-origin","x축 위칫값 y축 위칫값")
 			
 			예-> $("body").css("transform-origin","0 0");
 				//<body>영역의 확대 기준점이 "0 0" 으로 설정되어  
 				//(0,0)위치에서 x축 과 y축 방향으로 확대됨
 
 */  
  
/* zoom 버튼 */

//확대 비율 초기값 변수에 저장
var base = 100;

//<body>전체요소영역을 선택해서 변수에 저장 해 놓고 재활용하자
var mybody = $("body");

//id="zoom"인 부모<ul>요소 내부에 만들어져 있는 하위 후손<a>모두를 선택해서 가져와
//click이벤트를 등록해 놓으면
//사이트 이용자(클라이언트)가 <a>태그들   +    100    - 중에서 하나를 클릭했을때
//클릭한 <a>(클릭이벤트가 발생한 <a>)의 이벤트를 처리할수 있다.
//[ <a href="#" class="zoom_in">,      +    0 index
//  <a href="#" class="zoom_return">,  100  1 index
//  <a href="#" class="zoom_out">  ]   -    2 index
$("#zoom a").on("click",function(){

    // +   100   -  <a>중에서 click이벤트가 발생한 <a>의 index위치 번호를 얻자
    var zNum = $("#zoom a").index(this);

    if(zNum === 0){ //index위치 번호가 0일때   + <a>태그를 클릭했다면?

       base += 10; //확대 값을 10 누적해서 증가 시키자

    }else if(zNum === 1){//index위치 번호가 1일때  100 <a>태그를 클릭했다면?

       base = 100; //정사이즈 100으로 설정

    }else{//index위치 번호가 2일때   - <a>태그를 클릭했다면?

      base -= 10; //축소값 설정을 위해 -10 차감
    }

    //body태그 전체화면 영역을 선택해서(위 var mybody변수에 저장되어 있기때문에 재활용)
    mybody
    //overflow-x속성을 적용하면 IE 8버전 이하에서 정상적으로 작동되도록 설정
    .css("overflow-x","auto")

    //body태그 전체 영역의 확대 기준점을 x축과 y축의 좌표 0, 0으로 설정하여
    //x축과 y축 방향으로 확대 되도록 설정
    .css("transform-origin","0 0")

    //전체 body태그 영역화면에 포함된 자식요소들 영역들이 base/100의 값만큼 확대되도록 설정
    .css("transfrom","scale("+ base/100 +")")

    //전체 body태그영역의 모든태그가 base의 값 % 만큼 확대/축소 됩니다.
    .css("zoom",base+"%");

    return false; //클릭한 <a>태그의 href속성으로 설정된 기본이벤트 차단시켜
                  //사이트 재요청 X
});

 
//-----------------------------------------------------------------------------------------------------

  
  /*
   	주제 : 인쇄 버튼 만들기 
   	인쇄 버튼 웹사이트에서 인쇄 버튼을 방문자가 클릭했을때.. 
    인쇄창을 뛰우는 방법을 알아 봅시다.
   */
   //인쇄 버튼 이미지 영역을 감싸고 있는 <a>를선택해서  click이벤트 등록
   $(".print_btn").on("click",() => {
     /*
     window객체는 윈도우 창의 정보를 가지고 있는 객체 이기때문에
     print()메소드를 제공합니다.  
     print()메소드를 호출하면 인쇄 미리보기창을 띄워주는 기능이 실행됩니다.
     */
     window.print();
     
     return false; //클릭한 <a>태그의 href속성으로 설정된 기본이벤트 차단!

   });
  
//-----------------------------------------------------------------------------------------------------

  /*
   주제 : 검색 창 안내 가이드 만들기
   검색 입력 요소에 마우스로 클릭해 포커스가 이동되면 
   안내 가이드 변경 이미지가 사라지고,
   포커스가 다른 요소로 이동되었을때 검색 입력 요소에 아무 내용이 없으면
   다시 안내가이드 배경 이미지가 다시 나타나게 하기 
   */
  /*검색 창 안내 가이드*/

  //id="keyword"인<input>선택해서 가져와 
  //focus이벤트와 그리고 이어서 blur이벤트도 등록
  $("#keyword").on("focus",function(){

    //focus이벤트가 발생한 <input>요소를 선택해서 가져와
    //검색창의 배경 이미지 위치를 변경 시키는데
    //"background-position"속성값은 "0  -500px"로 설정되어 있으므로
    //배경 이미지가 <input>요소 밖으로 이동되게 하여 안내가이드 배경이미지를 숨기게합니다
    //이때 "0 -500px"은 이미지의 x축 위칫값으로 왼쪽 끝을 의미하며,
    //-500px은 y축의 위치값으로 위쪽 끝을 의미 합니다.
    //따라서 아래 코드는 검색창의 배경이미지를 왼쪽 끝(0)에서 
    //상단에서 500px(배경이미지의 높이)위쪽으로 이동시키는 것입니다.
    $(this).css("background-position","0 -500px");


  }).on("blur",function(){
    //focus이벤트가 <input>에 존재하다가 focus를 잃은 동작(blur이벤트)가 발생했을때
    //blur이벤트가 발생한 <input>을 선택해서 가져와 
    //<input>에 데이터를 입력하지 않았을떄!
    if( $(this).val() === ""){

      //<input>에 설정된 힌트 배경이미지가 다시 보이게 x축 0  y축 0으로 설정해서
      //보여줍니다
      $(this).css("background-position","0 0");
    }
  });
  
//-----------------------------------------------------------------------------------------------------
  
 /*
  주제 : GNB(글로벌 네비게이션 바) 메뉴 만들기
  사이트의 모든 페이지에 노출되는 메뉴를 가리키며,
  보통 사이트 상단에 위치합니다.
  GNB상위 메뉴에 마우스가 올라갔을때, 
  해당 상위 메뉴 이미지는 활성화(컬러)된 이미지로 바뀌게 됨.
  이어서 마우스를 다른 상위메뉴로 이동하면,
  앞서 활성화된 상위 메뉴 이미지는 다시 비활성화(무채도)된 이미지로 바뀌고,
  현재 마우스가 올라간 상위 메뉴의 이미지는 활성화된 이미지로 바뀌도록 만들자 
  */ 
  /*GNB 메뉴*/

 //나중에 우리가 마우스 포인터가 올라가 있던~ 상위메뉴<a>에서 마우스 포인터가
 //벗어나게 하기위해서 마우스 포인터가 올라가는 상위 메뉴 <a>를 저장시킬
 //전역변수 만들기
 var beforeEl;

 //id="gnb"인 <ul></ul>요소 내부에 만들어져 있는 
 //           모든<li></li>요소들 내부에 만들어져 있는
 //           모든 하위 <a></a>요소들 4쌍을 배열에 담아 최종선택해서 가져와
 //           "mouseover" 이벤트와 "focus"이벤트를 다중 등록
 $("#gnb>li>a").on("mouseover focus", function(){

    //만일 펼쳐져 있는 서브 메뉴영역이 있으면 선택해서 가져와
    //위로 접으면서 숨기는 효과를 주기 위해 slideUp()메소드를 사용하자
    $("#gnb ul:visible").slideUp("fast");

    //상위 메뉴 영역 <a>들 중에서
    //하나의 <a>메뉴영역에 마우스 포인터를 올리거나 포커스가 생성되면
	  //1. 상위 메뉴 <a>영역에 작성된 <img>태그의 이미지 경로를 
    //   노란색이미지가 되도록 변경 
    var imgSrc = $("img",this).attr("src");
    //만약 [홈] <a>태그위에 마우스포인터를 올리면 
	  //마우스포인터가 올라간 <a>내부에 만들어진 <img src="images/gnb_1_out.gif">태그의
	  //src속성의 주소(images/gnb_1_out.gif)를 얻어 낸다.

    //"images/gnb_1_out.gif"  -> "images/gnb_1_over.gif"
     //설명 : out.gif 특정 부분만  over.gif로 변경시킨 
     //       전체 경로 "images/gnb_1_over.gif" 반환해줌
    var newSrc = imgSrc.replace("out.gif","over.gif");

    //<img src="images/gnb_1_over.gif"> 노란색 홈 이미지
    $("img",this).attr("src",newSrc); 


    //2.마우스 포인터를 올리거나 포커스가 생성된 <a>의 서브 메뉴영역<ul></ul>영역을
    // 아래로 펼쳐지며 노출되게 효과 메소드로 나타내자
    $(this).next().stop().slideDown("normal");


    //상위 <a>메뉴들 중에서 현재 마우스포인터가 올라가있거나
    //포커스가 생성된 <a>요소를 선택해서 beforeEl변수에 저장
    beforeEl = $(this);

 });

 //id="gnb"인 <ul>태그의 하위<li>요소들을 모두 선택해서
 //경계 범위 내에서 마우스 포인터가 완전히 벗어 났을때의 이벤트 등록
 $("#gnb>li").on("mouseleave",function(){

    //만일 펼쳐져 있는 서브 메뉴영역 <ul>이 있으면 slideUp()효과 메소드로
    //위로 접으면서 숨김
    $("#gnb ul:visible").slideUp("fast");

    if(beforeEl){

      //상위메뉴 <a>들 중에서 마우스 포인터를 올린 이벤트가 발생한 <a>의 
      //자식 <img>요소를 선택해서 가져와 src속성의 전체 주소를 얻어
      //"over.gif"를  "out.gif" 부분을 변경한 전체 주소 "images/gnb_1_out.gif"를 
      //반환받는다
      var newSrc = beforeEl.children("img")
                           .attr("src")
                           .replace("over.gif","out.gif");

		 //하얀색 메뉴이미지로 변경된 전체주소 "images/gnb_1_out.gif"를
		 //상위 메뉴<a>들 중에서 마우스 포인터를 올린 이벤트가 발생한 <a>의 
     //자식 <img>요소의 src속성에 설정
      beforeEl.children("img").attr("src",newSrc);

    }


 });

//-----------------------------------------------------------------------------------------------------
  /*
   주제: 슬라이드 전체 메뉴 만들기
   - 전체 메뉴를 클릭 했을 때 전체메뉴가 slide효과로 펼쳐지고
     전체 메뉴 버튼 이미지도 바뀌도록 만들어 보자
   - [전체메뉴]버튼을 클릭 했을때 전체 메뉴가 아래로 펼쳐지며
     [CLOSE]버튼을 클릭했을때는 다시 전체메뉴가 위로 접히면서 사라지게 해보자.  
   */
 
    //<p id="total_btn"><a><img></a></p>상위 요소를 먼저 선택한 후
    //하위 <a>를 최종선택해 click이벤트 등록
    $("#total_btn>a").click(function(){

        //id="total_menu"인 <div id="total_menu">...</div>서브 메뉴 영역 요소 전체를
        //선택해 숨겨져 있으면 아래로 펼쳐지며 노출되게 하고
        //노출되어 있으면 위로 접으면서 숨겨지게 하기 위해 slideTooge메소드 사용하자
        $("#total_menu").slideToggle("normal");

        //조건식
        //만약~~` 클릭이벤트가 발생한 <a>의 하위 자식<img>선택해
        //<img>의 src속성값이 "images/allmenu_btn_out.gif"이면?
        //                   (전체메뉴 ▼ 이미지 주소라면?)
        if(  $("img",this).attr("src")  == "images/allmenu_btn_out.gif" ){
          
          //<img>의 src속성값을 "images/allmenu_btn_over.gif"(전체메뉴 ▲) 주소로 변경
          $("img",this).attr(
                              "src", 
                              $("img",this).attr("src").replace("out.gif","over.gif") 
                            );
        }else {
          //만약 클릭이벤트가 발생한 <a>의 하위 자식<img>속성값이
          //"images/allmenu_btn_over.gif"이면?(전체메뉴 ▲ 이미지이면?)

            // "images/allmenu_btn_out.gif"로 전체메뉴 ▼ 변경 
            $(this).children("img").attr("src","images/allmenu_btn_out.gif");
        }

        //클릭한 <a>의 href속성 기본이벤트를 차단! 
        return false;
    });


    //전체 메뉴 닫기 CLOSE 버튼 처리
    
      //CLOSE <a>를 선택해 click이벤트 등록
      $("#total_close>a").click(function(event){

        //a태그의 href속성의 주소로 재요청해서 이동되는 기본이벤트 차단
        event.preventDefault();

        //id="total_menu"인 <div id="total_menu">...</div>서브메뉴영역 전체를
        //노출되어 있으면 위로 접으면서 숨김
        $("#total_menu").slideUp(500);

        //화살표 방향이 전체메뉴 ▼  이미지 경로로 변경
        $("#total_btn>a>img").attr("src","images/allmenu_btn_out.gif"); 

      });
  

  

//-----------------------------------------------------------------------------------------------------

  /*
    주제 :  현재 날짜 표기 하기
    사이트 헤더 영역에  현재 연도, 월, 일을 표기 합니다.
  Date객체를 사용하여 오늘의 날짜 정보를 구해 올것입니다.
  */
  
  /*날짜 표기*/
  var date = new Date();//Javascript의 Date객체는 현재 날짜와 시간을 기준으로하는
                        //정보를 제공하는 객체 입니다.
   
  //초 단위로 Date객체의 시간값을 밀리초 값으로 구해서 변수에 저장
  //Date객체의 getTime()메소드를 호출하면
  //1970년 1월 1일  00:00:00 초부터 현재 오늘날짜까지 경과된 시간정보를
  //밀리초 단위로 반환합니다.
  //요약 : 현재 시간을 기준으로 한 타임스탬프를 저장합니다.
  var initalTime = date.getTime();

  //경과된 시간을 밀리초 단위로 저장할 변수
  //-> 초기값은 0으로 설정되어 있고, 이후에 매 초마다 1000밀리초씩 증가하게 됩니다
  var elapsedTime = 0;

  window.setInterval(function(){

    //매 1초 마다 elapsedTime변수에 1000을 더하여 경과 시간을 누적합니다
    //요약 : 1초 결과 시간을 변수에 누적
    elapsedTime += 1000;

    //setTime()메소드를 사용하여 date객체의 시간을 업데이트 합니다
    //초기시간(initalTime)에 경과 시간을 더하여 새로운 시간을 설정합니다
    //이렇게 하면 항상 현재 시간에 맞춰 업데이트됩니다
    date.setTime( initalTime + elapsedTime );
    //기존 위 Date객체의 시간을 초기시간과 경과시간의 합계로 설정

/*  
	  	<p id="date_wrap">
				<span class="year">0000</span> 년 
				<span class="month">00</span> 월 
				<span class="date">00</span>일
				<span class="hour">00</span>시
				<span class="minute">00</span>분
				<span class="second">00</span>초
			</p>
	*/	
     $("#date_wrap .year").text(  date.getFullYear() );//현재 년도 정보
     $("#date_wrap .month").text( date.getMonth() + 1 ); //현재 월 정보 7+1  -> 8 		
	   $("#date_wrap .date").text(  date.getDate()  ); //현재 일 정보 20
     $("#date_wrap .hour").text(  date.getHours()  ); //현재 시간 정보 12
     $("#date_wrap .minute").text(  date.getMinutes()  ); //현재 분 정보  21 
   	 $("#date_wrap .second").text(  date.getSeconds()  ); //현재 초 정보 ~~~~ 
	  
  },1000);

//-----------------------------------------------------------------------------------------------------

/*
   주제 : 관련 사이트 이동 선택 상자 만들기
   -푸터 영역에는 관련 사이트 이동 선택 상자가 있습니다.
  	사이트에 방문객체 관련 사이트를 선택한 후 
    [이동]버튼을 클릭하였을 때 새창으로 선택한 사이트가 열리도록 할것입니다.
  	여기서는 [이동] 버튼을 클릭했을때 
    <form>태그에서 전송이 일어나므로 submit 이벤트를 적용 하겠습니다.
  	이벤트가 발생 했을 때 이벤트 핸들러에 
    window.open(사이트 경로)메서드를 이용해 새창 으로 
    지정한 사이트가 열리도록 만들것입니다.  
  */
  //<form action="a.jsp" method="#" name="rel_f">......</form>요소를 선택해서
  //submit(전송이벤트) 등록
  //(<input type="image" src="images/rel_site_btn.gif" alt="관련사이트 이동" />)
  // 이동 이미지를 클릭해서 전송이벤트가 발생하면?
  $("form[name=rel_f]").submit(function(){

      //전송이벤트가 발생당한 <form>요소의 하위 <select>요소를 선택해서
      //선택한 <option>태그의 value속성의 사이트주소값 얻기
      var url = $("select",this).val();

      //새로운 웹브라우저 창을 띄워 변수 url에 저장된 
      //선택한 option의 주소값으로 요청하여 보여주게 하자
      window.open(url);

      return false; //<form>태그의 action속성에 적힌 전송할 서버페이지 주소로
                    //요청전송하는 submit이벤트 차단!

  });



  
  

 //-----------------------------------------------------------------------------------------------------

  /*옆쪽 퀵 메뉴*/
  /*
  	클라이언트가 index.html사이트를 웹브라우저로 처음 요청했을떄
  	퀵 메뉴 영역인 <div id="quick_menu"></div>요소영역의 
    CSS설정 top속성의 위치 이동값 100을 구하기 위해
  	퀵 메뉴 영역을  선택해서 가져와  css("top")메소드를 호출하면
  	미리 설정되어 있는 top 속성값 "100px" 문자열을 얻는다.
  	"100px" top속성의 값을 나중에 스크롤막대바가 세로로 이동한 거릿값과 + 계산하기 위해 
  	100정수만 추출해서 얻어낸다.
    
    요약 : div에 css문법으로 설정된 기본전체 문서 상단에서 퀵 메뉴영역(div)이 위치한 top속성값 !
    	  퀵 메뉴가 아래로 위치한 top속성값 얻기 
  */
   //console.log( $("#quick_menu").css("top") );  // <- "100px"
   //parsetInt("100px"); -> 100
   
   //div퀵메뉴영역에 css문법으로 설정된 기본전체 문서 상단에서
   //div퀵메뉴영역이 위치한 top속성값! 즉! 퀵 메뉴가 아래로 위치한 top속성값 얻기
   var defaultTop = parseInt(  $("#quick_menu").css("top")  ); //100

   //웹브라우저 윈도우창 객체(window객체)에
   //scroll이벤트(스크롤막대바를 누른상태에서 이동시키는 동작)를 등록
   $(window).on("scroll",function(){

      //스크롤이벤트가 발생한 윈도우창을 다시선택해서
      //스크롤 막대바가 세로 아랫방향으로 이동된 높잇값(거리값)을 반환하여
      //scv변수에저장
      var scv = $(this).scrollTop();

      /*
     스크롤 막대바를 세로 아랫방향으로 이동시키는 scroll이벤트가 발생할떄마다 
     animate()메소드가 계속 호출됩니다.
     animate메소드가 호출된다고 하는 것은 
     큐 메모리 공간에 계속 쌓이면서 대기 상태가 되어 자기 차례가 된 animate메소드 
     하나씩 실행된다는 의미 입니다.
     이떄 사이트 방문자가  스크롤막대바를 위 아래로 계속 무한반복해서 움직이면 
     animate메소드가 여러번동작하려 하기 떄문에
     화면의 에니메이션 처리가 느려 질수 있습니다. 
     이를 방지하기 위해 stop()메소드를 호출해 큐저장소에 대기 중인 
     animate메소드를 제거 하고
     가장 먼저 호출된 animate메소드만 실행되게 하여 
     애니메이션 동작 처리를 시켜야 합니다.

      1초만에  스크롤막대바의 이동 거릿값 만큼  
      퀵메뉴영역이 따라오는 동작 효과를 주기 위해
      scv변수에 저장된 값과 defaultTop변수에 저장된 값을 합하여  
      퀵메뉴영역의 top속성 위치값으로 1초만에 이동시키는 동작을 처리 해야함
      */
      $("#quick_menu").stop().animate({ top: scv+defaultTop+"px"});


   });




});
















