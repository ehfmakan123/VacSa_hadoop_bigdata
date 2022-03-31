import csv
import time
import re
from urllib.request import urlopen
from urllib.parse import quote_plus
from bs4 import BeautifulSoup

# https://www.ppomppu.co.kr/zboard/

# 검색어
search = '부작용'

# 시작페이지
pageNum = 2

# 마지막 페이지
lastPage = 10

# 게시글 링크를 저장할 리스트
linkList = []

# 자유게시판 baseUrl
baseUrl = 'https://www.ppomppu.co.kr/zboard/'

# 결과물을 담을 리스트
searchList = [['SECTION', 'DATE', 'TITLE', 'CONTENT']]


while pageNum < lastPage:
    listUrl = f'https://www.ppomppu.co.kr/zboard/zboard.php?id=freeboard&page={pageNum}&search_type=sub_memo&keyword={quote_plus(search)}'
    html = urlopen(listUrl).read()
    soup = BeautifulSoup(html, 'html.parser')
    total = soup.select('.list_vspace a')

# 페이지를 돌며 게시글URL을 리스트로 저장
    for i in total:
        linkList.append(i.attrs['href'])
    pageNum += 1

# 링크에 섞여들어온 '#' 제거
rm_set = {'#'}
newList = [i for i in linkList if i not in rm_set]

# print('linkList 완료')
# print(newList)

# 게시글에서 title, date, context 찾기.
for link in newList:
    html = urlopen(baseUrl+link).read()
    time.sleep(1)
    soup = BeautifulSoup(html, 'html.parser')
    title = soup.select('.view_title2')
    context = soup.select('.pic_bg .han')
    date = soup.select('.sub-top-text-box')
    temp = []
    temp.append('커뮤니티')

    for i in date:
        tmp_date = i.text+''
        tmp_date2 = tmp_date.split("\n")
        number_date = re.sub(r'[^0-9]', '', tmp_date2[5])
        temp.append(number_date[0:8])
    for i in title:
        temp.append(i.text)
    for i in context:
        temp.append(i.text)

    searchList.append(temp)

# for i in total:
#     temp = []
#     temp.append(i.text)
#     temp.append(i.attrs['href'])
#     searchList.append(temp)

# 만든 리스트를 csv 파일로 저장
f = open(f'{search}.csv', 'w', encoding='utf-8', newline='')
csvWriter = csv.writer(f)
for i in searchList:
    csvWriter.writerow(i)

f.close()

print('완료')
