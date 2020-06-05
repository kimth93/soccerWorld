""" Responsible to collect the managers info from Transfermarkt"""
import analyzer
import crawler


def get_manager_info(manager_name, manager_id):
    """ Get managers info. """
    link = analyzer.manager_link_assemble(manager_name, manager_id)
    manager_page = crawler.get_page(link)

    #print("매니저페이지가 머임 ",manager_page)

    manager_info = {}
    manager_info['Name'] = manager_name.replace('-', ' ')
    manager_info['Id'] = manager_id

    token = "Date of Birth:"
    manager_info['Birth Date'] = analyzer.retrieve_in_tags(token+"\n\t\t\t\t\t\t\t\t", "</td>",
                                                         manager_page)
    manager_info['Birth Date'] = analyzer.remove_tokens(manager_info['Birth Date'],['\t', '\n'])
    token = 'itemprop="birthPlace">'
    manager_info['Birth Place'] = analyzer.retrieve_in_tags(token, "</span>",
                                                          manager_page)
    manager_info['Birth Place'] = analyzer.remove_tokens(manager_info['Birth Place'],['\t', '\n'])
    token = 'itemprop="nationality">'
    manager_info['Nationality'] = analyzer.retrieve_in_tags(token, "</span>",
                                                          manager_page)
    manager_info['Nationality'] = analyzer.remove_tokens(manager_info['Nationality'],['\t', '\n'])
    #token = "Avg. term as manager:"
    # manager_info['Avg. term'] = analyzer.retrieve_in_tags(token, "</td>",
    #                                                     manager_page)
    # manager_info['Avg. term'] = analyzer.remove_tokens(manager_info['Avg. term'],['\t', '\n'])
    token = "Coaching Licence:"
    manager_info['Coaching License'] = analyzer.retrieve_in_tags(token +"\n\n\t\t\t\t\t\t\t\t\t", "</td>\t\t\t\t\t\t\t\t",
                                                               manager_page)
    manager_info['Coaching License'] = analyzer.remove_tokens(manager_info['Coaching License'],['\t', '\n'])
    token = "Preferred Formation"
    manager_info[token] = analyzer.retrieve_in_tags(token + ':', "</td>",
                                                  manager_page)
    #img 링크 가져오기
    manager_img = crawler.get_img(link)
    # #print("매니저 사진 링크"+manager_img)
    # #token = 'dataBild">'
    manager_info['img'] = manager_img
    # manager_info['img'] = analyzer.get_img(token,manager_page)
    # print(manager_info['img'])


    manager_info['History'] = get_manager_history(manager_name, manager_id)



    return manager_info


def get_manager_history(manager_name, manager_id):
    ''' Get all team that a manager worked. '''
    link = analyzer.manager_detailed_link(manager_name, manager_id)
    manager_page = crawler.get_page(link)

    begin_token = '<td class="zentriert no-border-rechts">'
    end_token = '</tr>'
    stories = analyzer.retrieve_in_tags(begin_token, end_token,
                                      manager_page, False)
    if stories is None:
        return None

    history = []
    for story in stories:
        info = {}
        info['Manager Id'] = manager_id
        info['Team'] = analyzer.retrieve_in_tags('alt="', '"', story, False)[0]
        info['Id'] = set(analyzer.retrieve_in_tags('id="', '"', story, False))
        tokens_tag = analyzer.parse_in_tags(story, False)
        info['Appointed'] = tokens_tag[1].replace("&nbsp;", '')
        info['Contract'] = tokens_tag[2].replace("&nbsp;", '')
        info['Position'] = tokens_tag[3]
        info['\\# Matches'] = tokens_tag[4]
        info['Points Per Match'] = tokens_tag[5]
        history.append(info)

    return history
