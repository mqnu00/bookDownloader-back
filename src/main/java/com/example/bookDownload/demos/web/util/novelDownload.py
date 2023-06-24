# -*- coding: utf-8 -*-

import os
import requests
from bs4 import BeautifulSoup
import json

header = {'User-Agent': 'Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:109.0) Gecko/20100101 Firefox/112.0'}
cookies = ''


def get_cookies(username, password):
    login_url = "https://www.wenku8.net/login.php"
    header = {'User-Agent': 'Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:109.0) Gecko/20100101 Firefox/112.0'}
    cookies = {}

    ssion = requests.session()
    cnt = 0
    while cnt < 3:

        try:
            result = ssion.post(
                login_url,
                data={
                    'username': username,
                    "password": password,
                    'usecookie': '0',
                    'action': 'login',
                    'submit': '%26%23160%3B%B5%C7%26%23160%3B%26%23160%3B%C2%BC%26%23160%3B'
                },
                headers=header,
                timeout=10
            )
            print(result.status_code)
            # result.encoding = 'gbk'
            # print(result.text)
            for i, j in result.cookies.items():
                cookies[i] = j
            # print(cookies)
            break
        except requests.exceptions.RequestException:
            print('超时' + '第' + str(cnt + 1) + '次重连')
            cnt += 1
    #     print(cookies)
    return cookies


def create_folder(path):

    if not os.path.exists(path):
        # print("???123")
        os.mkdir(path)


def novel_info(url, path):
    res = requests.get(
        url,
        headers=header,
        cookies=cookies,
        timeout=10
    )

    print(res.status_code)

    soup = BeautifulSoup(res.content, 'html.parser')
    list = soup.find('title').text.split('-')

    noveldesc = soup.find_all('span')
    for i in range(0, len(noveldesc)):
        if str(noveldesc[i]).find('内容简介') != -1:
#             print(i)
            noveldesc = noveldesc[i + 1].text
            break
    # print(noveldesc)
    noveldict = {"novelName": list[0].strip(), "author": list[1].strip(), "description": noveldesc}
    novelInfo = json.dumps(noveldict, ensure_ascii=False)

    path = path + noveldict["novelName"]

    print(path)

    create_folder(path)

    with open(path + "\\info.json", 'w', encoding='utf-8') as f:
        f.write(novelInfo)
        print("小说信息收集完成")

    picurl = str(soup.find_all('img')[1])
    picurl = picurl[picurl.find("src") + 5:picurl.find("jpg") + 3]

    with open(path + "\\" + noveldict["novelName"] + ".jpg", 'wb') as f:
        f.write(
            requests.get(
                picurl,
                headers=header,
                cookies=cookies,
                timeout=10
            ).content
        )
        print("小说封面下载完成")

    return noveldict


def download_result(username, password, url):
    res = requests.get(
        url,
        headers=header,
        cookies=cookies,
        timeout=10
    )
    print(res.status_code)
    with open('./result.html', 'wb') as f:
        f.write(res.content)


def download(username, password, url, downloadurl):
    download_result(username, password, downloadurl)

    header = {'User-Agent': 'Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:109.0) Gecko/20100101 Firefox/112.0'}

    content = open('./result.html', 'r', encoding='gbk').read()
    soup = BeautifulSoup(content, 'html.parser')

    url_target = soup.select('.even')
    download_url = []
    for i in url_target:
        i = i.find_all('a')
        i = str(i).split('a href')
        for j in i:
            if j.find('utf-8') != -1 and j.find('aname') == -1:
                download_url.append(j[j.find('https'):j.find('utf-8') + 5].replace('amp;', ''))

    name_target = soup.select('.odd')
    download_name = []
    for i in name_target:
        download_name.append(i.text)

    folder_name = os.path.abspath(os.path.join(os.path.dirname(__file__), "..\..\..")) + '\\novel\\'
    #     print(folder_name)

    noveldict = {}

    try:
        noveldict = novel_info(url, folder_name)
    except Exception:
        print("小说信息收集失败")
        return

    #print(noveldict)
    novelName = noveldict["novelName"]

    # 判断文件夹是否存在，不存在则创建

    for i, j in zip(download_name, download_url):

        check = 0
        for root, dirs, files in os.walk(folder_name + novelName):
            for name in files:
                if str(name).find(i) != -1:
                    check = 1
                    break
            if check == 1:
                break
        if check == 1:
            print(i + ' ' + "已存在")
            continue

        cnt = 0
        while cnt < 3:

            try:

                res = requests.get(
                    j,
                    data={
                        'username': username,
                        "password": password,
                        'usecookie': '0',
                        'action': 'login',
                        'submit': '%26%23160%3B%B5%C7%26%23160%3B%26%23160%3B%C2%BC%26%23160%3B'
                    },
                    headers=header,
                    timeout=10
                )
                with open(folder_name + novelName + '\\' + i + '.txt', 'wb') as f:
                    f.write(res.content)
                    print(i + ' ' + "成功")
                    f.close()
                res.close()
                break
            except requests.exceptions.RequestException:
                print(i + ' ' + '失败，' + '尝试第' + str(cnt + 1) + '次重连')
                cnt += 1
        if cnt >= 3:
            print(i + ' ' + '失败')
            return


if __name__ == '__main__':
    import sys

    dict = {"username": sys.argv[1], "password": sys.argv[2], "novelUrl": sys.argv[3], "novelDownloadUrl": sys.argv[4]}
    cookies = get_cookies(dict["username"], dict["password"])
    # novel_info(dict["novelUrl"])
    # pic_test(dict["novelUrl"])
    download(dict["username"], dict["password"], dict["novelUrl"], dict["novelDownloadUrl"])
     # get_cookies(sys.argv[1], sys.argv[2])
