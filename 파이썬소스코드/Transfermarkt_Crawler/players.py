""" Crawler the players from Transfermarkt. """
import analyzer
from collections import OrderedDict
import crawler


def get_player_info(player_name, player_id):
    """ Get the information about a player. """

    link = analyzer.player_link_assemble(player_name, player_id)
    player_page = crawler.get_page(link)
    #player_img = crawler.get_img(link)

    if not player_page:
        player_page = crawler.get_page(link, error=True)
    # if not player_img:
    #     player_img = crawler.get_img(link, error=True)

    #print("타입이 뭐임? ",type(player_page))
    player_info = {}

    player_info['Transfers'] = get_player_transfer(player_page, player_id)

    player_info['Name'] = player_name.replace('-', ' ').capitalize()

    player_info['Id'] = player_id

    # player_info['Full Name'] = analyzer.retrieve_in_tags("Full Name:</th>",
    #                                                    "</td>", player_page)
    # player_info['Full Name'] = analyzer.remove_tokens(player_info['Full Name'],['\t', '\n'])

    player_info['Birth Date'] = analyzer.retrieve_in_tags("Date of Birth:",
                                                        "</a>", player_page)
    player_info['Birth Date'] = analyzer.remove_tokens(player_info['Birth Date'],['\t', '\n'])
    span = '</span>'
    player_info['Birth Place'] = analyzer.retrieve_in_tags('"birthPlace">',
                                                         span, player_page)
    player_info['Birth Place'] = analyzer.remove_tokens(player_info['Birth Place'],['\t', '\n'])
    
    token = 'itemprop="nationality">'
    player_info['Nationality'] = analyzer.retrieve_in_tags(token,
                                                         span, player_page)
    player_info['Nationality'] = analyzer.remove_tokens(player_info['Nationality'],['\t', '\n'])

    player_info['Age'] = analyzer.retrieve_in_tags("Age:</th>", "</td>",
                                                 player_page)
    player_info['Age'] = analyzer.remove_tokens(player_info['Age'],['\t', '\n'])

    player_info['Height'] = analyzer.retrieve_in_tags('itemprop="height"',
                                                    span, player_page)
    player_info['Height'] = analyzer.remove_tokens(player_info['Height'],['\t', '\n'])

    #player_info['Position'] = analyzer.retrieve_in_tags("Position:</span>",
    #                                                  "</span>", player_page)
    
    player_info['Position'] = analyzer.retrieve_in_tags("<span>Main position                                                        :",
                                                      "</div>", player_page)
    #player_info['Position'] = analyzer.remove_tokens(player_info['Position'],['\t', '\n'])
    
    print("선수 포지션 = ",player_info['Position'])

    player_info['Foot'] = analyzer.retrieve_in_tags("Foot:\n",
                                                  "</td>", player_page)
    player_info['Foot'] = analyzer.remove_tokens(player_info['Foot'],['\t', '\n'])

    # player_info['Agent'] = analyzer.retrieve_in_tags("Player Agents:",
    #                                                "</a>", player_page)
    # player_info['Agent'] = analyzer.remove_tokens(player_info['Agent'],['\t', '\n'])

    player_info['Joined'] = analyzer.retrieve_in_tags("Joined:</span>",
                                                    span, player_page)

    player_info['Joined'] = analyzer.remove_tokens(player_info['Joined'],['\t', '\n'])

    token = "Contract until:</span>"
    player_info['Contract Length'] = analyzer.retrieve_in_tags(token,
                                                             span, player_page)
    player_info['Contract Length'] = analyzer.remove_tokens(player_info['Contract Length'],['\t', '\n'])

    # player_info['Outfiter'] = analyzer.retrieve_in_tags("Outfitter:",
    #                                                   "</td>", player_page)
    # player_info['Outfiter'] = analyzer.remove_tokens(player_info['Outfiter'],['\t', '\n'])
    #img src 가져오기
    players_img = crawler.get_img(link)
    player_info['img'] = players_img                                               
    # token = '"dataBild">'
    # player_info['img'] = analyzer.get_img(token,player_page)
    # print(player_info['img'])

    #token = '<div class="dataBild">'                                                   
    # player_info['playerImg_url'] = analyzer.retrieve_in_tags(token,
    #                                                   "/>", player_page)
    
    return player_info


def get_player_transfer(player_page, player_id):
    """ Get the transfers made along a player career. """
    player_page = analyzer.cut_page('<div class="box transferhistorie">',
                                  "</tfoot>", player_page)

    pages = analyzer.retrieve_in_tags('<tr class="zeile-transfer">', '</tr>',
                                    player_page, False)

    transfers = []

    if pages is None:
        return pages

    for page in pages:
        info = {}
        info['Player Id'] = player_id
        info['Season'] = analyzer.retrieve_in_tags(
            'class="zentriert hide-for-small"', '</td>', page)[0]
        info['Fee'] = analyzer.retrieve_in_tags('zelle-abloese', '<', page)
        info['Market Value'] = analyzer.retrieve_in_tags('zelle-mw', '<', page)


        clubs_name = []
        clubs_name = analyzer.retrieve_in_tags('vereinsname">', '</a>', page)
        print(clubs_name)
        #print("len(clubs_name) = ",len(clubs_name))
        # for i in range(0,len(clubs_name)):#
        #     print(i)
        #     clubs_name_1[i] = analyzer.retrieve_in_tags('vereinsname">\n\t\t\t\t\t\t', '</a>', page)
        #     clubs_name_1[i] = analyzer.remove_tokens(clubs_name[i],['\t', '\n'])
            #print("클럽 이름 : ",clubs_name[i])
        
        # make a set without sorting the list
        clubs_id = list(OrderedDict.fromkeys(analyzer.retrieve_in_tags(
            'id="', '"', page)))

        # The even values are the teams nickname
        info['Team A'], info['Team B'] = clubs_name[1], clubs_name[3]

        print("a = ",info['Team A'])
        print("b = ",info['Team B'])
        info['ID Team A'], info['ID Team B'] = clubs_id[0], clubs_id[1]
        transfers.append(info)

    return transfers
