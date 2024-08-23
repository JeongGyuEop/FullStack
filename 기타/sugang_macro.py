import time
import datetime
from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC
from selenium.webdriver.common.alert import Alert

# ChromeDriver의 경로를 지정합니다.
driver_path = "C:\Users\1\Downloads\chromedriver-win64\chromedriver.exe"

# Initialize the Chrome driver
driver = webdriver.Chrome()

# Step 1: Log in to the website
driver.get("http://sugang.deu.ac.kr:8080/DEUSugang_LogIn.aspx")

# 사용자 이름과 비밀번호 입력 필드 찾기
username_input = WebDriverWait(driver, 10).until(
    EC.presence_of_element_located((By.ID, "txtID"))
)
password_input = WebDriverWait(driver, 10).until(
    EC.presence_of_element_located((By.ID, "txtPW"))
)

# 사용자 이름과 비밀번호 입력
username_input.send_keys("20171042")  # 실제 사용자 이름으로 변경
password_input.send_keys("wjd/75369")  # 실제 비밀번호로 변경

# 로그인 버튼 클릭
login_button = driver.find_element(By.ID, "ibtnLogin")
login_button.click()

# 특정 시간까지 대기 (예: 10:00:00)
target_time = datetime.datetime.combine(datetime.date.today(), datetime.time(10, 0, 0))

# 현재 시간이 목표 시간보다 이전인 경우 대기
while datetime.datetime.now() < target_time:
    print(f"현재 시간: {datetime.datetime.now().time()}, 목표 시간까지 대기 중...")
    time.sleep(1)  # 1초 대기 (필요에 따라 조정 가능)

print("목표 시간이 되어 다음 단계로 진행합니다.")

# 로그인 후 작업 페이지로 이동
driver.get('http://sugang.deu.ac.kr:8080/DEUSugang.aspx')

# 장바구니 선택
WebDriverWait(driver, 10).until(
    EC.element_to_be_clickable((By.ID, "CP1_rbtnlGangjwaType_0"))
).click()

repeat_count = 10000  # 원하는 시도 횟수 설정
for _ in range(repeat_count):
    try:
        # 첫 번째 수강신청 버튼 클릭
        WebDriverWait(driver, 10).until(
            EC.element_to_be_clickable((By.ID, 'CP1_dt_result_BtnSugangApply_0'))
        ).click()

        # 첫 번째 확인 대화 상자 처리
        WebDriverWait(driver, 10).until(EC.alert_is_present())
        Alert(driver).accept()

        # 결과 확인 대화 상자가 나타날 때까지 기다림
        WebDriverWait(driver, 10).until(EC.alert_is_present())
        Alert(driver).accept()

        # 다음 시도를 하기 전에 잠시 대기
        time.sleep(2)

    except Exception as e:
        print(f"예외 발생: {e}")
        time.sleep(2)  # 잠시 대기 후 다시 시도

driver.quit()
