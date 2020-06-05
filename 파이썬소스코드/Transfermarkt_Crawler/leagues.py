""" Crawler the leagues from Transfermarkt. """
import re
import analyzer
import crawler


def get_teams(league_link):
    """ Return all the teams of a given league. """
    league_page = crawler.get_page(league_link)

    league_page = analyzer.cut_page('id="verein_select_breadcrumb"',
                                  "</select>", league_page)

    clubs_id = analyzer.retrieve_in_tags('value="', '">', league_page)
    clubs_name = analyzer.retrieve_in_tags('>', '<', league_page)

    clubs_id = analyzer.remove_token(clubs_id, ['', ' '])
    # letting only digts on the list
    clubs_id = list(filter(lambda x: re.match(r'\d', x), clubs_id))

    clubs_name = analyzer.remove_token(clubs_name, ['\n', 'Club'])

    return {int(clubs_id[index]): name for index,
            name in enumerate(clubs_name)}


def get_results(league_link, season):
    """ Get all results the classification table of a league."""
    league_link = analyzer.league_result_assemble(league_link, season)
    league_page = crawler.get_page(league_link)

    league_page = analyzer.cut_page('<div class="responsive-table">',
                                  '</table>', league_page)

    chuncks = analyzer.retrieve_in_tags("<tr>", "</tr>", league_page,
                                      parse=False)[1:]

    info = list(map(get_team_result, chuncks))

    return info


def get_team_result(chunck):
    """
        Given a chunck of unparsed text, retrieve all
        needed information.
    """
    info = {}
    info["Club"] = analyzer.retrieve_in_tags('alt="', '"', chunck, False)[0]
    info['Club Id'] = analyzer.retrieve_in_tags('id="', '"', chunck,
                                              False)[0]
    results = analyzer.retrieve_in_tags(">", "<", chunck, False)

    results = list(filter(lambda x: re.match(r'[\d\-:]+', x)
                          and x != '' and ' ' not in x, results))
    info['Position'] = results[0]
    info['Matches'] = results[1]
    info['Win'] = results[2]
    info['Draw'] = results[3]
    info['Lose'] = results[4]
    goals = results[5].split(':')
    info['Scored Goals'] = goals[0]
    info['Taken Goals'] = goals[1]
    info['Balance'] = results[6]
    info['Points'] = results[7]

    return info
