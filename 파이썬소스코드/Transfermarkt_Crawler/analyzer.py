# import sys
# import io
# sys.stdout = io.TextIOWrapper(sys.stdout.detach(), encoding = 'utf-8')
# sys.stderr = io.TextIOWrapper(sys.stderr.detach(), encoding = 'utf-8')
""" Parser Module for Transfermarkt Crawler."""
import re
from headers import TRANSFERS, PLAYERS, TEAMS, MANAGERS, MANAGER_HISTORY
import collections


def file_read(file_name):
    """ Read files function. """
    try:
        with open(file_name) as file_data:
            return file_data.read()
    except UnicodeDecodeError:
        with open(file_name, encoding='utf-8') as file_data:
            return file_data.read()


def cut_page(start_token, end_token, page):
    """ Cut the page.

        Cut the page in the start_token, and then
        the first token that matchs with the position
        bigger than the position of the start token.

        return cut of the page
    """
    start_pos = [(a.end()) for a in list(re.finditer(start_token, page))]

    if start_pos:
        start_pos = start_pos[0]
        end_pos = [(a.start()) for a in list(re.finditer(end_token, page))]
        end_pos = list(filter(lambda x: x > start_pos, end_pos))[0]

        return page[start_pos:end_pos]

    return page


def _match_positions(start_list, end_list):
    """ Match start and end positions. """

    if len(start_list) == 1:
        value = start_list[0]
        return {value: list(filter(lambda x: value < x, end_list))[0]}

    result = {}
    for start in start_list:
        for end in end_list:
            if start < end:
                result[start] = end
                break

    return result





###############################에러 발생########################
def remove_tokens(page, tokens):
    """ Remove tokens from the page. """

    # if tokens is not None:
    #     for token in tokens:
    #         age = list(filter((token).__ne__, page))
    
    # if '  ' in ''.join(page):
    #     text_aux = ''
    #     for pag in ''.join(page).split(' '):
    #         if pag:
    #             text_aux += pag + ' '

    #     return ''.join(text_aux[:-1])

    # return ''.join(page)
    try:
        for token in tokens:
            age = list(filter((token).__ne__, page))
    
        if '  ' in ''.join(page):
            text_aux = ''
            for pag in ''.join(page).split(' '):
                if pag:
                    text_aux += pag + ' '

            return ''.join(text_aux[:-1])

        return ''.join(page)

    except:
        
        page1 = re.sub('\t\t\t\t\t\t\t\t','',str(page))
        print("예외처리1 = ",page1)
        print("예외처리2 = ",page)
        if page1 is not None:
            return page1
        else:
            return page
    


def parse_in_tags(page, join=True):
    """ Parse between > and < tags. """

    if '>' in page:
        pages = []
        start_pos = [(a.end()) for a in list(re.finditer('>', page))]
        for pos in start_pos:
            aux = pos
            while aux <= len(page)-1 and page[aux] != '<':
                aux += 1
            pages.append(page[pos:aux])

        for index, pag in enumerate(pages):
            pages[index] = remove_tokens(pag, ['\t', '\n', '<', '>', '',
                                               '</th>', '<td>', '<br>'])

        if join:
            return ''.join(pages)

        return list(filter(lambda x: x not in ['', '&nbsp;'], pages))

    return page

# def get_img(start_token,end_token, page, parse=True):

#     start_pos = [(a.end()) for a in list(re.finditer(start_token, page))]


#     global img_src
#     for i in elements:
#             image = i.find_elements_by_tag_name("img")
#             for j in image:
#                 img_src = j.get_attribute("src")
    
#     return img_src


def retrieve_in_tags(start_token, end_token, page, parse=True):
    """ Retrieve between tags.

        Given a start_token and a end_token, will retrieve
        all values between those two tags.

        return parsed values
    """
    #print("start_token = ",start_token)
    #print("end_token = ",end_token)
    

    start_pos = [(a.end()) for a in list(re.finditer(start_token, page))]
    #print("strat_pos = ", start_pos)

    if not start_pos:
        return None

    end_pos = [(a.start()) for a in list(re.finditer(end_token, page))]
    #print("end_pos = ",end_pos)

    positions = _match_positions(start_pos, end_pos)

    pages = list(map(lambda x: page[x:positions[x]], positions))
    #print("pages = ",pages)
    if parse:
        for index, pag in enumerate(pages):
            #print("pag = ",pag)

            pages[index] = parse_in_tags(pag)
            
            print("pages[index] = ", pages[index])

        if len(set(pages)) > 1:
            #print("pages = ",pages)
            return pages

        if not pages:
            return None
        return pages[0]
    #print("pages = ",pages)
    return pages

def remove_token(values, tokens):
    """ Remove a list of tokens from list. """
    return list(filter(lambda x: x not in tokens, values))


def team_detailed_link_assemble(team_name, team_id, season):
    """ Mount a link of a team getting the with it transfers. """

    link = "transfermarkt.com/" + team_name.replace(' ', '-').lower()
    #link = "transfermarkt.co.uk/" + team_name.replace(' ', '-').lower()

    club = "/transfers/verein/" + str(team_id)

    season = "/saison_id/" + str(season)

    detailed = "/pos//detailpos/0/w_s//plus/1#zugaenge"

    return link + club + season + detailed


def team_link_assemble(team_name, team_id, season):
    """ Mount a not detailed link of a team. """

    link = "transfermarkt.com/" + team_name.replace(' ', '-').lower()
    #link = "transfermarkt.co.uk/" + team_name.replace(' ', '-').lower()
    club = "/transfers/verein/" + str(team_id)

    season = "?saison_id=" + str(season)

    return link + club + season


def league_result_assemble(link, season):
    """ Mount a link of a league results. """
    return link + season


def player_link_assemble(player_name, player_id):
    """ Mount a link of a player getting his history."""

    link = "transfermarkt.com/" + player_name.replace(' ', '-')
    #link = "transfermarkt.co.uk/" + player_name.replace(' ', '-')
    return link + "/profil/spieler/" + str(player_id)


def titles_link_assemble(team_name, team_id):
    """ Mount a link to get all titles of a club."""

    link = "transfermarkt.com/" + team_name.replace(' ', '-')
    #link = "transfermarkt.co.uk/" + manager_name.replace(' ', '-')
    return link + '/erfolge/verein/' + str(team_id)


def manager_link_assemble(manager_name, manager_id):
    """Mount a link with manager infos."""

    link = "www.transfermarkt.com/" + manager_name.replace(' ', '-')
    #link = "www.transfermarkt.co.uk/" + manager_name.replace(' ', '-')
    return link + '/profil/trainer/' + str(manager_id)


def manager_detailed_link(manager_name, manager_id):
    """ Mount detailed history link. """

    link = "www.transfermarkt.com/" + manager_name.replace(' ', '-')
    #link = "www.transfermarkt.co.uk/" + manager_name.replace(' ', '-')
    return link + '/profil/trainer/' + str(manager_id) + 'plus/1'


def file_write(team_info, players_info, managers_info, header):
    """ Write a file with team info.

        players_info = list = each element is a
        dict with players info
        season = int = collect season

        Responsible for create/alterate a file.

        Two files will be change along time:
            transfer.txt - Will store all gathered transfers.
            player.txt - Will store all gathered players
            teams.txt - Will store all gathered teams.
            managers.txt
    """

    with open('C:/Users/김태희\Desktop/2020졸업프로젝트/Transfermarkt-Crawler-master-20200524T062900Z-001/Transfermarkt-Crawler-master/Output/teams.txt', 'a+',encoding='utf-8') as file:
        save_file(file, TEAMS, team_info, header)

    with open('C:/Users/김태희\Desktop/2020졸업프로젝트/Transfermarkt-Crawler-master-20200524T062900Z-001/Transfermarkt-Crawler-master/Output/players_id.txt', 'a+',encoding='utf-8') as file:
        players_info = verify_id(file, players_info)

    header_aux = header
    with open('C:/Users/김태희\Desktop/2020졸업프로젝트/Transfermarkt-Crawler-master-20200524T062900Z-001/Transfermarkt-Crawler-master/Output/players.txt', 'a+',encoding='utf-8') as file:
        for player in players_info:
            save_file(file, PLAYERS, player, header)
            header = False
        
        
        header = header_aux

    header_aux = header
    

    with open('C:/Users/김태희\Desktop/2020졸업프로젝트/Transfermarkt-Crawler-master-20200524T062900Z-001/Transfermarkt-Crawler-master/Output/transfers.txt', 'a+',encoding='utf-8') as file:
        #transfers = []
        transfers = list(map(lambda x: x['Transfers'],players_info))
        # for i in players_info:

        #     print("players_info = ",i)
        #print("transfers len = ",len(transfers))
        # for i in transfers:
        #     print("transfers = ",i)
        
        #print("transfers type : ",isinstance(transfers, collections.Iterable))
        if transfers is not None:
            for player_transfers in transfers:

            
            #isinstance(player_transfers, collections.Iterable)
            #print("player_transfers type : ",isinstance(player_transfers, collections.Iterable))
            #print("player_transfers len = ",len(player_transfers))
            #print("player_transfers  = ", player_transfers)
            #isinstance(player_transfers, collections.Iterable)
                if player_transfers is not None:
                    for transfer in player_transfers:
                
                #isinstance(transfer, collections.Iterable)
                #print("transfer type : ",isinstance(transfer, collections.Iterable))
                #print("transfer  = ", transfer)    
                #isinstance(player_transfers, collections.Iterable)
                        save_file(file, TRANSFERS, transfer, header)
                        header = False

                
          
        header = header_aux

    with open('Output/managers_id.txt', 'a+',encoding='utf-8') as file:
        managers_info = verify_id(file, managers_info)

    with open('Output/managers.txt', 'a+',encoding='utf-8') as file:
        for manager in managers_info:
            save_file(file, MANAGERS, manager, header)
            header = False
        header = header_aux

    #manager_history = []
    with open('Output/managers_history.txt', 'a+',encoding='utf-8') as file:
        manager_history = list(map(lambda x: x['History'],managers_info))
        for history in manager_history:
            for works in history:
                save_file(file, MANAGER_HISTORY, works, header)
                header = False


def verify_id(file, data):
    """ Verify repeated ids and remove them from the list."""
    ids = file.readlines()


    if isinstance(data[0], list):
        new_data = []
        for value in data:
            new_data += data
        data = new_data

    data = list(filter(lambda x: str(x['Id']) not in ids,data))

    info = [str(player['Id']) for player in data]

    file.write('\n'.join(info))

    return data


def save_file(file, header, data, header_flag=False):
    """ Generic function to save in a database."""
    if header_flag:
        file.write('\t'.join(header) + '\n')

    if data is not None and data:
        data = [str(data[column]) for column in header]
        file.write('\t'.join(data) + '\n')


def write_header(file, header):
    """ Generic function to save the header in a dataset."""
    file.write('\t'.join(header) + '\n')


def parse_season(season):
    """ Get the season in a soccer format: 2000 --> 00/01."""

    season = ''.join(list(str(season))[2:])  # get the two last values
    if season == '99':
        return '99/00'

    if int(season) == 9:
        return "09/10"

    return season + '/' + str(int(season) + 1)
