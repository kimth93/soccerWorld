from selenium import webdriver
import requests
from bs4 import BeautifulSoup
 
options = webdriver.ChromeOptions()
options.add_argument('headless')
options.add_argument('window-size=1920x1080')
options.add_argument("disable-gpu")
 
path = "C:\chromedriver.exe" 
driver = webdriver.Chrome(path,chrome_options=options)
driver.implicitly_wait(3) # seconds
 
naver_wfootball = "https://sports.news.naver.com/wfootball/record/index.nhn?category=bundesliga&year=2019"
driver.get(naver_wfootball)
 
page = driver.page_source
premi_team_rank_list =  BeautifulSoup(page,"html.parser")
team_rank_list = premi_team_rank_list.select('#wfootballTeamRecordBody>table>tbody>tr')
print(team_rank_list)

for team in team_rank_list:
    size = len(team.select('div.inner > span'))
    num = team.select('.num > div.inner > strong')[0].text
    name = team.select('.name')[0].text
    #goto = team.select('div.inner > span')[0].text
    point = team.select('td.selected > div.inner > span')[0].text
    if size == 10:
        games = team.select('div.inner > span')[2].text
        win = team.select('div.inner > span')[4].text
        draw = team.select('div.inner > span')[5].text
        lose = team.select('div.inner > span')[6].text
        goal = team.select('div.inner > span')[7].text
        lost_point = team.select('div.inner > span')[8].text
        goal_difference = team.select('div.inner > span')[9].text
    else :
        games = team.select('div.inner > span')[2].text
        win = team.select('div.inner > span')[3].text
        draw = team.select('div.inner > span')[4].text
        lose = team.select('div.inner > span')[5].text
        goal = team.select('div.inner > span')[6].text
        lost_point = team.select('div.inner > span')[7].text
        goal_difference = team.select('div.inner > span')[8].text

    

    #print(num +"위 " + name + " 승점 " + point + " 경기수 "  +games)
    #print(win + "승 " + draw + " 무 " + lose + " 패 " + " 득 " + goal +  " 실 " + lost_point + " 득실차 "+ goal_difference)