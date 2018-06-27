#pip3 install -U requests

import requests
#from requests.auth import HTTPBasicAuth
#r = requests.get('https://ci00.eng.appianci.net', auth=HTTPBasicAuth('david.alberto', '0d7c57e4ad8f26abfdc2dd8bbef74807'))
#r = requests.get('https://ci00.eng.appianci.net')
r = requests.get('https://api.spotify.com/v1/search?type=artist&q=snoop')
print(r)
