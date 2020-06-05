# import sys
# import io
# sys.stdout = io.TextIOWrapper(sys.stdout.detach(), encoding = 'utf-8')
# sys.stderr = io.TextIOWrapper(sys.stderr.detach(), encoding = 'utf-8')
""" Main module of Transfermarket Crawler

    Given a league link, this crawler will get:
        - The teams of each season
        - The players of each team at each season
        - The historic of each player player
"""
import analyzer
import leagues
import players
import managers
import teams

if __name__ == '__main__':

    SEASON_START = 2019
    SEASON_END = 2020
    HEADER = True

    for league in analyzer.file_read("C:/Users/김태희\Desktop/2020_finalProject/Transfermarkt_Crawler/Input/leagues.txt").split('\n'):

        league_teams = leagues.get_teams(league)

        for team in league_teams:

            seasons = []  # new list of seasons of a team

            for season in range(SEASON_START, SEASON_END):

                print("Temporada: ", season)

                team_players = teams.get_players(league_teams[team],team, season)
                team_info = teams.get_team_info(league_teams[team],team, season)
                #print("teams.get_team_info = " + teams.get_team_info(league_teams[team],team, season))
                #print("team_info",team_info['Income'])
                managers_info = []
                if team_info['Manager'] is not None:

                    print("team 정보 : ",team_info['Manager'])

                    for index, manager in enumerate(team_info['Manager']):

                        print("index 값 : ", index)
                        print("manager : ",manager)
                        manager_id = team_info['Manager Id'][index]
                        print("manager_id = ",manager_id)
                        info = managers.get_manager_info(manager, manager_id)
                        managers_info.append(info)

                players_info = []
                for player in team_players:

                    players_info.append(players.get_player_info(team_players[player], player))

                analyzer.file_write(team_info, players_info,managers_info, HEADER)
                HEADER = False
