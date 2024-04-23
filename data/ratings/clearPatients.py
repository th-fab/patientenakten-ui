import os

files = []

for i in os.listdir():
    splt = i.split('.')
    if splt[1] == 'pat':
        files.append(i)

for file in files:
    with open(file, 'w') as f:
        f.close()