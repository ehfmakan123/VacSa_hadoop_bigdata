from selenium import webdriver
from selenium.webdriver.common.by import By
from bs4 import BeautifulSoup
import time
import csv

# 시작할 주소
url = 'https://www.inven.co.kr/search/webzine/article/%EC%BD%94%EB%A1%9C%EB%82%98%20%EB%B0%B1%EC%8B%A0/1?dt=s&sDate=2019.01.04&eDate=2022.03.14'
# 웹 드라이버
driver = webdriver.Chrome()
# 주소 입력
driver.get(url)
# 파일 생성
file = open('inven.csv', 'w', encoding='utf-8', newline='')

# csv writer 키고 항목 저장
csvWriter = csv.writer(file)
temp = [["SECTION", "DATE", "TITLE", "CONTENT"]]
csvWriter.writerows(temp)

for page in range(1, 51):
    print("###################" + str(page) + "페이지####################################################")
    html = driver.page_source
    soup = BeautifulSoup(html, "html.parser")
    # 이동할 글 리스트 받아오기
    title_list = driver.find_elements(By.CSS_SELECTOR, ".section_body .name")
    for i in title_list:
        # 한 줄을 저장할 리스트
        temp = []
        # temp를 받아서 csv에 저장할 리스트
        input_text = []

        time.sleep(1)
        i.click()
        time.sleep(1)
        driver.switch_to.window(driver.window_handles[1])
        detail_html = driver.page_source
        detail_soup = BeautifulSoup(html, "html.parser")
        # 날짜 정보 - 단위로 나누고 재조립
        input_date = driver.find_elements(By.CSS_SELECTOR, ".articleDate")
        if len(input_date) == 0:
            # 현재 창 닫기
            driver.close()
            # 다시 검색 창으로 포커스 맞추기
            driver.switch_to.window(driver.window_handles[0])
            time.sleep(1)
            continue
        date = input_date[0].text.split('-')
        output_date = date[0] + date[1] + date[2][0:2]
        # 글 제목 받아오기
        title = driver.find_elements(By.CSS_SELECTOR, ".articleTitle h1")
        # 글 내용 받아오기
        content = driver.find_elements(By.CSS_SELECTOR, "#powerbbsContent")
        print("######################날짜#######################")
        print(output_date)
        print("######################제목#######################")
        print(title[0].text)
        print("######################내용#######################")
        print(content[0].text)
        # temp에 정보 저장
        temp.append("기사")
        temp.append(output_date)
        temp.append(title[0].text)
        temp.append(content[0].text)
        # 저장한 내용을 input에 저장
        input_text.append(temp)
        # csv에 한 줄 저장
        csvWriter.writerows(input_text)
        # 현재 창 닫기
        driver.close()
        # 다시 검색 창으로 포커스 맞추기
        driver.switch_to.window(driver.window_handles[0])
    next = driver.find_elements(By.CSS_SELECTOR, ".nexttext")[0].click()
    time.sleep(1)
time.sleep(1)
# csv 파일 종료
file.close()
driver.close()
