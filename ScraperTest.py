import json
import requests
import urllib.request
from bs4 import BeautifulSoup

# 1) grab stuff
# 2) from all of the links with <a> tag
    # id="hlsv" + str(i)   i = 0
# 3) under the <li> title="view serveces for Housing"

# try printing the links for each id="hlsv(i)" to make sure you can get there
# Test accessing one with the requests.get(url) where url is the link

housing = {}

url = "http://www.navigateresources.net/path/Subcategory.aspx?;;0;;N;0;118501;Basic%20Needs;Housing;3"
response = requests.get(url)
soup = BeautifulSoup(response.text, "html.parser")

mainList = soup.findAll('li')

i = 0

# mainSearch = input("What is the main heading? ")
mainSearch = "Housing"

while(True):
    # currList = all[i]
    # print("\n", currList.a.get_text(), i, "-->", str(currList))
    # user = input("continue? (n)")
    if(mainList[i].a.get_text() == mainSearch):
        subList = mainList[i].contents
        break
    else:
        i +=1

realList = subList[1].contents

for step in range(len(realList) -1):

    # for step in realList --> a link!!
    catName = realList[step].a['title']
    print(catName)
    housing[catName] = []


    subLink = "http://www.navigateresources.net/path/" + realList[step].a['href']
    print(subLink)
    subResponce = requests.get(subLink)
    soupCup = BeautifulSoup(subResponce.text, "html.parser")
    resourceList = soupCup.findAll("div", "matchlist_panel1")
    theStuff = soupCup.div

    for pos in range(len(resourceList) -1):

        placeName = resourceList[pos].h3.get_text()
        print(placeName)

        address = resourceList[pos].address.get_text()
        print(address)

        countyArr = resourceList[pos].find("div", "ml_county").get_text().split(' ')
        county = ""
        for word in countyArr:
            if(word == "County"):
                county += word
                break
            else:
                county += word + " "
        print(county)

        contactList = resourceList[pos].ul.get_text().split(' ')

        phone = contactList[0]
        website = ""
        for i in range(1, len(contactList)):
            if(contactList[i][0:4] == "FAXh"):
                website = contactList[i][3:]
            elif(contactList[i][0:4] == "Faxh"):
                website = contactList[i][3:]
            elif(contactList[i][0:6] == "Voiceh"):
                website = contactList[i][6:]
            elif(contactList[i][0:6] == "VOICEh"):
                website = contactList[i][6:]

        print(phone)
        print(website)

        housing[catName].append({
            'Title': placeName,
            'Address': address,
            'County': county,
            'Phone': phone,
            'Website': website
        })
    # end of pos loop
# end of step loop

with open('housing.json', 'w') as outfile:
    json.dump(housing, outfile)
