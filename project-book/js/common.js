$(function () {
  //class="login_wrap"인  <li>부모요소의 직전 하위 <a>요소를 선택해서
  //click이벤트 등록
  $('.login_wrap>a').on('click', () => {
    $('#login_f').animate({ top: '20px' }, 500);
    // <form>요소영역이 상단에서 -500px만큼 이동한 위치에서
    //20px만큼 상승하여 화면에 표시됩니다.

    return false; //<a> 태그의 href속성의 주소로 이동되는것을 차단
  });

  $(".login_wrap .login_close_btn>a, input[alt='로그인버튼']").on('click', () => {
    //id="login_f"인 <form>요소 전체 영역을 다시 선택해서
    //0.5초만에 상단 문서 밖 화면으로 위로 이동되게 하는 동작을 처리하자.
    $('#login_f').animate({ top: '-500px' }, 500);

    return false;
  });

  $('#user_id, #user_pw').on('focus blur', function (event) {
    if (event.type === 'focus') {
      //포커스 이벤트가 발생했을때 실행
      //포커스된 <input>의 이전 요소(힌트이미지영역)<label>영역을 왼쪽으로 완전히 숨김
      $(this).prev().css('left', '-9999px');
    } else if (event.type === 'blur' && $(this).val() === '') {
      //blur이벤트가 발생한 <input>에 입력하지 않은 경우
      //힌트 이미지 영역 <label>을 다시 표시합니다.
      $(this).prev().css('left', '2px');
    }
  });

  // zoom 버튼
  // 확대 비율 초깃값 변수에 저장
  var base = 100;
  // <body> 전체요소 영역을 선택해서 변수에 저장
  var mybody = $('body');

  $('#zoom a').on('click', function () {
    var zNum = $('#zoom a').index(this);

    if (zNum === 0) {
      // index 위치번호가 0일떼 + <a>태그를 선택했다면?
      base += 10; // 확대 값을 10 증가
    } else if (zNum === 1) {
      //index위치 번호가 1일때  100 <a>태그를 클릭했다면?
      base = 100; //정사이즈 100으로 설정
    } else {
      //index위치 번호가 2일때   - <a>태그를 클릭했다면?
      base -= 10; //축소값 설정을 위해 -10 차감
    }

    //body태그 전체화면 영역을 선택 overflow-x속성을 적용
    mybody
      .css('overflow-x', 'auto')
      //body태그 전체 영역의 확대 기준점을 x축과 y축의 좌표 0, 0으로 설정하여
      //x축과 y축 방향으로 확대 되도록 설정
      .css('transform-origin', '0 0')
      //전체 body태그 영역화면에 포함된 자식요소들 영역들이 base/100의 값만큼 확대되도록 설정
      .css('transform', 'scale(' + base / 100 + ')')
      //전체 body태그영역의 모든 태그가 base의 값 % 만큼 확대/축소 ;
      .css('zoom', base + '%');

    return false; //클릭한 <a>태그의 href속성으로 설정된 기본이벤트 차단
  });

  // 프린트 버튼
  $('.print_btn').on('click', function () {
    window.print(); // 창의 정보를 가지고 있는 window 객체에서 제공하는 print() 메소드를 사용
    // 미리보기 창이 띄워짐
    return false;
  });

  // 검색 창 안내 가이드
  $('#keyword')
    .on('focus', function () {
      // focus이벤트가 발생한 <input>요소를 선택해서 가져와
      //검색창의 배경 이미지 위치를 변경
      $(this).css('background-position', '0 -500px');
    })
    //focus이벤트가 <input>에 존재하다가 focus를 잃은 동작(blur이벤트)가 발생했을때
    .on('blur', function () {
      //blur이벤트가 발생한 <input>을 선택해서 가져와 데이터를 입력하지 않았을떄!
      if ($(this).val() === '') {
        //<input>에 설정된 힌트 배경이미지가 다시 보이게 x축 0  y축 0으로 설정해서
        //보여줍니다
        $(this).css('background-position', '0 0');
      }
    });

  // 글로벌 네비게이션 바 메뉴 만들기
  // 마우스 포인터가 올라가 있던 상위메뉴<a>에서 마우스 포인터가 벗어나게
  // 하기 위해서 마우스 포인터가 올라가는 상위 메뉴 <a>를 저장시킬 전역변수
  var beforeEl;

  //id="gnb"인 <ul></ul>요소 내부에 만들어져 있는
  //           모든<li></li>요소들 내부에 만들어져 있는
  //           모든 하위 <a></a>요소들 4쌍을 배열에 담아 최종선택해서 가져와
  //           "mouseover" 이벤트와 "focus"이벤트를 다중 등록
  $('#gnb>li>a').on('mouseover focus', function () {
    //만일 펼쳐져 있는 서브 메뉴영역이 있으면 선택해서 가져와
    //위로 접으면서 숨기는 효과를 주기 위해 slideUp()메소드를 사용하자
    $('#gnb ul:visible').slideUp('fast');

    //만약 [홈] <a>태그위에 마우스포인터를 올리면
    //마우스포인터가 올라간 <a>내부에 만들어진 <img src="images/gnb_1_out.gif">태그의
    //src속성의 주소(images/gnb_1_out.gif)를 얻어 낸다.
    var imgSrc = $('img', this).attr('src');

    //"images/gnb_1_out.gif"  -> "images/gnb_1_over.gif"
    //설명 : out.gif 특정 부분만  over.gif로 변경시킨
    //       전체 경로 "images/gnb_1_over.gif" 반환해줌
    var newSrc = imgSrc.replace('out.gif', 'over.gif');

    //<img src="images/gnb_1_over.gif"> 노란색 홈 이미지
    $('img', this).attr('src', newSrc);

    //2.마우스 포인터를 올리거나 포커스가 생성된 <a>의 서브 메뉴영역<ul></ul>영역을
    // 아래로 펼쳐지며 노출되게 효과 메소드로 나타내자
    $(this).next().stop().slideDown('normal');

    //상위 <a>메뉴들 중에서 현재 마우스포인터가 올라가있거나
    //포커스가 생성된 <a>요소를 선택해서 beforeEl변수에 저장
    beforeEl = $(this);
  });

  //id="gnb"인 <ul>태그의 하위<li>요소들을 모두 선택해서
  //경계 범위 내에서 마우스 포인터가 완전히 벗어 났을때의 이벤트 등록
  $('#gnb>li').on('mouseleave', function () {
    $('#gnb ul:visible').slideUp('fast');

    if (beforeEl) {
      // <a>들 중에서 마우스 포인터를 올린 이벤트가 발생한 <a>의
      // 자식 <img>요소를 선택해서 가져와 over.gif"를  "out.gif" 부분을 변경한
      // 전체 주소 "images/gnb_1_out.gif"를 얻어 newSrc에 저장
      var newSrc = beforeEl.children('img').attr('src').replace('over.gif', 'out.gif');
      //하얀색 메뉴이미지로 변경된 전체주소 "images/gnb_1_out.gif"를
      //상위 메뉴<a>들 중에서 마우스 포인터를 올린 이벤트가 발생한 <a>의
      //자식 <img>요소의 src속성에 설정
      beforeEl.children('img').attr('src', newSrc);
    }
  });

  // 슬라이드 전체 메뉴
  //<p id="total_btn"><a><img></a></p>상위 요소를 먼저 선택한 후
  //하위 <a>를 최종선택해 click이벤트 등록
  $('#total_btn>a').on('click', function () {
    //id="total_menu"인 <div id="total_menu">...</div>서브 메뉴 영역 요소 전체를
    //선택해 숨겨져 있으면 아래로 펼쳐지며 노출되게 하고
    //노출되어 있으면 위로 접으면서 숨겨지게 하기 위해 slideToggle메소드 사용하자
    $('#total_menu').slideToggle('normal');

    //만약~~` 클릭이벤트가 발생한 <a>의 하위 자식<img>선택해
    //<img>의 src속성값이 "images/allmenu_btn_out.gif"이면?
    //                   (전체메뉴 ▼ 이미지 주소라면?)
    if ($('img', this).attr('src') == 'images/allmenu_btn_out.gif') {
      //<img>의 src속성값을 "images/allmenu_btn_over.gif"(전체메뉴 ▲) 주소로 변경
      $('img', this).attr('src', $('img', this).attr('src').replace('out.gif', 'over.gif'));
    } else {
      //만약 클릭이벤트가 발생한 <a>의 하위 자식<img>속성값이
      //"images/allmenu_btn_over.gif"이면?(전체메뉴 ▲ 이미지이면?)

      // "images/allmenu_btn_out.gif"로 전체메뉴 ▼ 변경
      $(this).children('img').attr('src', 'images/allmenu_btn_out.gif');
    }

    //클릭한 <a>의 href속성 기본이벤트를 차단!
    return false;
  });

  // 전체 메뉴 닫기 버튼
  $('#total_close').on('click', function (event) {
    //a태그의 href속성의 주소로 재요청해서 이동되는 기본이벤트 차단
    event.preventDefault();

    // <div id="total_menu">...</div>서브메뉴영역 전체를
    // 노출되어 있으면 위로 접으면서 숨김
    $('#total_menu').slideUp(500);

    //화살표 방향이 전체메뉴 ▼  이미지 경로로 변경
    $('#total_btn>a>img').attr('src', 'images/allmenu_btn_out.gif');
  });

  // 현재 날짜 표기하기
  //Javascript의 Date객체는 현재 날짜와 시간을 기준으로하는 정보를 제공하는 객체
  var date = new Date();

  //현재 시간을 기준으로 한 타임스탬프를 저장합니다.
  var initialTime = date.getTime();

  //경과된 시간을 밀리초 단위로 저장할 변수
  //-> 초기값은 0으로 설정되어 있고, 이후에 매 초마다 1000밀리초씩 증가하게 됩니다
  var elapsedTime = 0;

  window.setInterval(function () {
    //매 1초 마다 elapsedTime변수에 1000을 더하여 경과 시간을 누적합니다
    //요약 : 1초 결과 시간을 변수에 누적
    elapsedTime += 1000;

    //setTime()메소드를 사용하여 date객체의 시간을 업데이트
    date.setTime(initialTime + elapsedTime);

    $('#date_wrap .year').text(date.getFullYear()); // 현재 년도
    $('#date_wrap .month').text(date.getMonth() + 1); // 현재 월
    $('#date_wrap .date').text(date.getDate()); // 현재 일
    $('#date_wrap .hour').text(date.getHours()); // 현재 요일
    $('#date_wrap .minute').text(date.getMinutes()); // 현재 분
    $('#date_wrap .second').text(date.getSeconds()); // 현재 초
  }, 1000);

  // 퀵 메뉴
  //div퀵메뉴영역에 css문법으로 설정된 기본전체 문서 상단에서
  //div퀵메뉴영역이 위치한 top속성값! 즉! 퀵 메뉴가 아래로 위치한 top속성값 얻기
  var defaultTop = parseInt($('#quick_menu').css('top')); //100

  //웹브라우저 윈도우창 객체(window객체)에
  //scroll이벤트(스크롤막대바를 누른상태에서 이동시키는 동작)를 등록
  $(window).on('scroll', function () {
    var scv = $(this).scrollTop();

    $('#quick_menu')
      .stop()
      .animate({ top: scv + defaultTop + 'px' });
  });
});
