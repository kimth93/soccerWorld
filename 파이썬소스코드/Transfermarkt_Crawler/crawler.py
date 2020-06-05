""" Crawler for Transfermarkt Website"""
from selenium import webdriver


class TimeOutException(Exception):
    """ Time out Exception Class."""



def get_page(link, error=False):
    """ Load and return a web page. """
    if "https://" not in link:
        link = "https://" + link

    # headers = {'Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.61 Safari/537.36'}
    # url ='크롤링할주소'
    # html = requests.get(url, headers = headers).text
    html_code = "return document.getElementsByTagName('html')[0].innerHTML"

    if error:
        driver = webdriver.Chrome(chrome_options=_change_proxy_1())
    else:
        #driver = webdriver.Chrome(chrome_options=_change_proxy_2())
        driver = webdriver.Chrome()

    try:
        #url = link
        #html = requests.get(url, headers = headers).text
        driver.get(link)
        html = driver.execute_script(html_code)
    except:
        driver.refresh()
        driver.get(link)
        html = driver.execute_script(html_code)
        

    driver.close()


    return html

def get_img(link, error=False):
    if "https://" not in link:
        link = "https://" + link

#     #html_code = "return document.getElementsByTagName('html')[0].innerHTML"
    if error:
        driver = webdriver.Chrome(chrome_options=_change_proxy_1())
    else:
        #driver = webdriver.Chrome(chrome_options=_change_proxy_2())
        driver = webdriver.Chrome()
    try:
        driver.get(link)
        elements = driver.find_elements_by_class_name('dataBild')
        # data1_list=soup.findAll()
        # #html = driver.execute_script(html_code)
        # for i in elements:
        #     image = i.find_element_by_tag_name("img")
        #     img_src = image.get_attribute("src")
    except:
        driver.refresh()
        driver.get(link)
        elements = driver.find_elements_by_class_name('dataBild')
        #img = driver.find_element_by_tag_name('img')

    global img_src
    for i in elements:
            image = i.find_elements_by_tag_name("img")
            for j in image:
                img_src = j.get_attribute("src")


    driver.close()

    return img_src


def _change_proxy_1():
    """ Change proxy and port of the browser. """

    chrome_options = webdriver.ChromeOptions()
    chrome_options.accept_untrusted_certs = True
    chrome_options.assume_untrusted_cert_issuer = True
    chrome_options.add_argument('--proxy-server=46.102.106.37:13228')
    #chrome_options.add_argument('Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.61 Safari/537.36')
    return chrome_options
def _change_proxy_2():
    """ Change proxy and port of the browser. """

    chrome_options = webdriver.ChromeOptions()
    chrome_options.accept_untrusted_certs = True
    chrome_options.assume_untrusted_cert_issuer = True
    #chrome_options.add_argument('--proxy-server=46.102.106.37:13228')
    chrome_options.add_argument('Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.61 Safari/537.36')
    return chrome_options
