''' Header for each one of the modeled dateset '''

# players header
# PLAYERS = ["Name", "Full Name", "Id", "Birth Date", "Birth Place",
#            "Age", "Height", "Nationality", "Position", "Foot", "Agent",
#            "Joined", "Contract Length", "Outfiter"]
# PLAYERS = ["Name", "Full Name", "Id", "Birth Date", "Birth Place",
#            "Age", "Height", "Nationality", "Position", "Foot",
#            "Joined", "Contract Length", "img"]
PLAYERS = ["Name", "Id", "Birth Date", "Birth Place",
           "Age", "Height", "Nationality", "Position", "Foot",
           "Joined", "Contract Length", "img"]

# transfers header
TRANSFERS = ["Player Id", "Season", "Fee", "Market Value", "Team A", "Team B",
             "ID Team A", "ID Team B"]

# teams header
TEAMS = ["Name", "Squad Id", "Season", "Manager", "Manager Id", "Income",
         "Expend.", "Titles"]

# managers header
MANAGERS = ["Name", "Id", "Birth Date", "Birth Place", "Nationality", "Coaching License", "Preferred Formation", "img"]
# MANAGERS = ["Name", "Id", "Birth Date", "Birth Place", "Nationality",
#             "Avg. term", "Coaching License", "Preferred Formation"]


# mangers history
MANAGER_HISTORY = ["Manager Id", "Team", "Id", "Appointed", "Contract",
                   "Position", "\\# Matches", "Points Per Match"]

# League classification
LEAGUE_CLASS = ["Position", "Club", "Club Id", "Matches", "Win", "Draw",
                "Lose", "Scored Goals", "Taken Goals", "Balance",
                "Points"]
