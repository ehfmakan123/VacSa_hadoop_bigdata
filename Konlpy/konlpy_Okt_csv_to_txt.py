import matplotlib.pyplot as plt
from konlpy.tag import Okt
from wordcloud import WordCloud
import pandas as pd
import csv
import re

# twitter = Twitter()
okt = Okt()

# csv 파일 열기
f = open('Symptom_news.csv', 'r')

# txt 파일 열기
ft = open("Symptom_news.txt", 'a')


rdr = csv.reader(f)
text = ''''''


# context만 저장
for line in rdr:
    text += line[3]

# 의미없는 문자 제거
han = re.compile(r'[ㄱ-ㅎㅏ-ㅣ!?~,".\n\r#\ufeff\u200d]')
text = re.sub(han, "", text)

# print(text)

# konlpy 에러 방지 3000자씩 자르기.
length = 3000

split_list = [text[i:i+length] for i in range(0, len(text), length)]
words = []

# print(split_list)
for split in split_list:
    # print(okt.nouns(split))
    # txt 파일에 덮어쓰기.
    ft.write(' '.join(okt.nouns(split)))
    # words.extend(list(hannanum.morphs(split)))


# 워드 클라우드
# words_ = words
# words_save = pd.DataFrame(words_, index=[0])
# words_save.to_csv("test_words.csv", mode='w', encoding='utf-8-sig')

# wordcloud = WordCloud(font_path=font_path_input,
#                       background_color='white', width=1600, height=1200)
# cloud = wordcloud.generate_from_frequencies(words)
# plt.imshow(cloud)
# plt.axis('off')
# plt.show()
