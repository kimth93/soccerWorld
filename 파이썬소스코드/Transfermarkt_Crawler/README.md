# Transfermarkt Crawler

## Overview

	Crawler for the website transfermarkt.com. The site is a huge database with soccer info as
	leagues, teams and players.

## Quick Start
	https://www.globo.com/
	This WebCrawler can work in two different way:
		- You can only retrieve using a singular module:
			* Teams
			* Players
			* Leagues

		- You can also retrieve by inputing a league, which will gather Teams and Players. 

		- You can define the seasons that will be crawled.

	- To execute the code you can run python main.py or import the modules for your code.

	- In case you does not have a webdriver in your computer, here some tutorials of how install:
		Chorme: http://chromedriver.chromium.org/getting-started
		Firefox: https://github.com/mozilla/geckodriver

## Modeled Dataset

	Three diffenrent dataset can be modeled from the Transfermarkt Web Crawler.
	Next, we present each one these datasets.

### Teams


	In this dataset we have teams information, the fields are:

		Team Name, ID, Season, Manager, Manager Id, Transfer Income,
		Transfer Departures, Title, Squad ID

### Players

	
	In this dataset we have players information, the fields are:

		Player Name, Full Name, ID, Birth Date, Birth Place, Age, Height, Nationality, 
		Position, Foot, Agent, Joined, Contract Lenght, Outfiter

### Transfers

	In this dataset we have transfers information, the fields are:

		Player ID, Season, Fee, Market Value, Team A, Team B, ID Team A, ID Team B

### Managers
	
	In this dataset we have managers information, the fields are:

		Manager Name, Manager ID, Birth Date, Birth Place, Nationality, Avg. term,
		Coaching License, Preferred Formation, Titles, History

### Managers History
	
	In this dataset we have managers history, the fields are:

		Team, ID, Appointed, Contract, Position, Number of Matches, Points Per Match

## License

	You're free to use this package, but if it makes it to your production environment we highly
	appreciate you sending us a postcard from your hometown, mentioning which of our package(s)
	you are using. To get our address send a e-mail to lucasgsfelix@gmail.com

## Used this Crawler in your paper and want to refer ?
```
@misc{lucasgsfelix,
  author = {Félix, Lucas},
  title = {Transfermarkt Crawler},
  year = {2019},
  publisher = {GitHub},
  journal = {GitHub repository},
  howpublished = {\url{https://github.com/lucasgsfelix/Transfermarkt-Crawler}}
}
```