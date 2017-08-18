import gspread
import sys
from oauth2client.service_account import ServiceAccountCredentials

scope = ['https://spreadsheets.google.com/feeds', 'https://www.googleapis.com/auth/drive']

credentials = ServiceAccountCredentials.from_json_keyfile_name('secret.json', scope)

gc = gspread.authorize(credentials)

wks = gc.open("tut1").sheet1

wks.update_acell('A1', "Hello Wojtek")

sh = gc.create('Hello-'+str(sys.argv[1]))
sh.share('wojciech.zarski@gmail.com', perm_type='user', role='writer')