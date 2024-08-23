let slides = document.querySelectorAll('#slides > img');
let prev = document.getElementById('prev');
let next = document.getElementById('next');
let current = 0;

showSlides(current); // 현재 이미지 표시
prev.onclick = prevSlide; // 이전 이미지 표시
next.onclick = nextSlide; // 다음 이미지 표시

// n 번째 슬라이드 이미지를 화면에 표시하는 함수
function showSlides(n) {
  for (let i = 0; i < n.length; i++) {
    // 배열의 처음부터 끝까지 반복
    slides[i].style.display = 'none'; // 모든 이미지를 화면에서 감춤
  }
  slides[n].style.display = 'block'; // n번째 이미지만 화면에 표시
}

// [이전] 버튼
function prevSlide() {
  if (current > 0) current -= 1; // current 값이 0보다 크면 이전 위치로
  else current = slides.length - 1; // 그렇지 않으면(첫번째 이미지 이므로) 마지막 위치로
  showSlides(current); // 이동한 위치의 이미지 표시
}

// [다음] 버튼
function nextSlide() {
  if (current < slides.length - 1) current += 1; // current 값이 2보다 작으면 다음 위치로
  else current = 0; // 그렇지 않다면(마지막 이미지이므로) 첫 번째 위치로 이동
  showSlides(current); // 이동한 위치의 이미지 표시
}
