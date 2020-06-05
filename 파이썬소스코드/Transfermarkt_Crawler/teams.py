
""" Crawler the teams from transfermarkt. """
import re
import analyzer
import crawler


def get_players(team_name, team_id, season):
    """ Get the players from a team.

        Return a dict of players names and ID.
    """
    link = analyzer.team_detailed_link_assemble(team_name, team_id, season)
    players_page = crawler.get_page(link)

    begin_token = '<a name="zugaenge" class="anchor">'
    end_token = '<div class="werbung werbung-fullsize_contentad">'
    page = analyzer.cut_page(begin_token, end_token, players_page)

    begin_token = '<td class="hauptlink">'
    pages = []
    pages = analyzer.retrieve_in_tags(begin_token, '/a>', page, False)
    #print(pages)
    # inside the pages, we must have a href
    if pages is not None:
        pages = list(filter(lambda x: 'href' in x, pages))
    
    #print(pages)
    players_info = {}

    if pages is not None:
        for page in pages:

            player_id = analyzer.retrieve_in_tags('id="', '"', page)
            player_name = analyzer.retrieve_in_tags(player_id+'">', '<', page)

            if player_name is not None:
                players_info[player_id] = player_name

    

    return players_info


def get_team_info(team_name, team_id, season):
    """ Get teams info.
''
        Returns a dict with all team info
    """
    link = analyzer.team_link_assemble(team_name, team_id, season)
    team_page = crawler.get_page(link)

    team_info = {}

    team_info["Name"] = team_name
    team_info["Squad Id"] = team_id
    team_info["Season"] = season

    #token = 'class="container-hauptinfo" itemprop="name">'
    token = 'class="container-hauptinfo" itemprop="name">'
    #print("token", token)
    #print("team_page",team_page)
    team_info["Manager"] = analyzer.retrieve_in_tags(token,
                                                   "</a>", team_page)

    team_info['Manager'] = analyzer.remove_tokens(team_info['Manager'],['\t', '\n'])
    team_info["Manager Id"] = analyzer.retrieve_in_tags("profil/trainer/",
                                                      '">', team_page)

    for key in ['Manager', 'Manager Id']:
        if isinstance(team_info[key], str):
            team_info[key] = [team_info[key]]

    team_info["Income"] = analyzer.retrieve_in_tags('class="greentext rechts">\n\t\t\t\t\t',
                                                  "\t\t\t\t</td>", team_page)
    #print("team_info = ",team_info["Income"])
    team_info['Income'] = analyzer.remove_tokens(team_info['Income'],
                                               ['\t', '\n'])
    
    print("team_info[income] =========",team_info['Income'])

    # team_info['Expend.'] = analyzer.retrieve_in_tags('class="redtext rechts">',
    #                                                "</td>", team_page)[0]

    team_info['Expend.'] = analyzer.retrieve_in_tags('class="redtext rechts">\n\t\t\t\t\t',
                                                   "\t\t\t\t</td>", team_page)[0]

    team_info['Expend.'] = analyzer.remove_tokens(team_info['Expend.'],
                                                ['\t', '\n'])
                                                
    print("team_info expend =========",team_info['Expend.'])
    parsed_season = analyzer.parse_season(season)

    titles_link = analyzer.titles_link_assemble(team_name, team_id)
    titles_page = crawler.get_page(titles_link)

    titles = analyzer.retrieve_in_tags("<h2", "<h2>", titles_page, False)

    if titles is not None:
        season_titles = []
        for title in titles:
            if parsed_season in title:
                season_titles.append(analyzer.retrieve_in_tags(">", "</h2>", title))


    
    season_titles = list(map(lambda x: re.sub(r'[\d]+x ', '', x),
                             season_titles))
    if not season_titles:
        team_info['Titles'] = None
    else:
        team_info['Titles'] = ','.join(season_titles)

    return team_info
