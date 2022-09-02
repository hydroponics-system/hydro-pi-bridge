import requests


# Does a simple get reqest on the given url and returns the data.
# This is a synchronus request.
#
# params:
#   url - The url to perform a get request on.
def get(url):
    response = requests.get(url)
    return response.json()