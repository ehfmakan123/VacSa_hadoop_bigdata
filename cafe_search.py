from bs4 import BeautifulSoup
from selenium import webdriver
import pandas as pd
import time
import re
from selenium.webdriver.common.keys import Keys # 키보드 키를 제어
import csv
import urllib.request
import os
import sys
import json


keys = Keys()

query = urllib.parse.quote(input("검색 질의 : "))

driver = webdriver.Chrome("C:/Users/Admin/Desktop/python/chromedriver/chromedriver.exe")
driver.get("https://section.cafe.naver.com/")

time.sleep(1)
Q = driver.find_element_by_css_selector('#header > div.snb_area > div > div.SearchArea > form > fieldset > div > div > div.FormInputText > input')
Q.click()


keyword = query
Q.send_keys(keyword)
Q.send_keys(keys.ENTER)

time.sleep(1)
driver.find_element_by_css_selector('#mainContainer > div > div.SectionSearchContent > div.section_search_content > div > div.list_area.article_list_area > div > a > span').click()

html = driver.page_source
soup = BeautifulSoup(html, 'html.parser')

len(soup.find_all('div', class_ = 'detail_area'))

link_len = len(soup.find_all('div', class_ = 'detail_area'))+1

df = pd.DataFrame(columns = ( "Title" , "Content"))

#title_list = []
#con_list = []
search = []
count = 1
idx = 0

while(count < 2): # 몇페이지 까지??

    for i in range(1,link_len): # link_len
        driver.find_element_by_css_selector('#mainContainer > div > div.SectionSearchContent > div.section_search_content > div > div.article_list_area > ul > li:nth-child('+str(i)+') > div > div > div > a').click()
        time.sleep(2)
        driver.switch_to.window(driver.window_handles[-1]) # 현재 탭으로 전환

        driver.switch_to.frame('cafe_main') #iframe 제거

        html = driver.page_source
        soup = BeautifulSoup(html,'html.parser')

        tit = soup.find('h3', class_ = 'title_text').text
        tit = re.sub('[^0-9a-zA-Zㄱ-ㅣ-가-힣 ]',"",tit)
     #   title_list.append(tit)
        search.append(tit)
        #제목 크롤링

        try:
            con = soup.find('div', class_="se-main-container").text
        except:
            try:
                con2 = soup.find('div',class_='se-text').text
                con = con + con2
            except:
                pass

        con = re.sub('[^0-9a-zA-Zㄱ-ㅣ-가-힣 ]',"",con)
       # con_list.append(con)
        search.append(con)
        #내용 크롤링

        driver.close()
        driver.switch_to.window(driver.window_handles[0])
        df.loc[idx] = [tit, con]
        idx += 1
        df.to_csv('df.csv')
        print(idx) # 몇번째 게시글을 스크랩 중인지 프린트


    time.sleep(1)
    try:
      driver.find_element_by_css_selector('#mainContainer > div > div.SectionSearchContent > div.section_search_content > div > div.article_list_area > div > div > div > a:nth-child('+str(count+2)+')').click()
    except:
      driver.find_element_by_css_selector('#mainContainer > div > div.SectionSearchContent > div.section_search_content > div > div.article_list_area > div > div > div > a:nth-child(8)).click()
    count+=1
    

df


#########################
#네이버 카페 탭 크롤링
                                          
#네이버 api를 사용해서 하면 한계가  있어서 직접 코딩 네이버 카페글(공개) 중 원하는 키워드를 가지고 있는 게시물을 크롤링 한다

#Title(제목)과 , Content (내용) 을 수집. 
                                          
#df.csv 파일으로 저장한다
                                          
                                         
                                         
