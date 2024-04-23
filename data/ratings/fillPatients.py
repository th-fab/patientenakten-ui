#26-06-2023;0;0;0;0;empty

import os
import random
from datetime import datetime, timedelta

files = []

for i in os.listdir():
    splt = i.split('.')
    if splt[1] == 'pat':
        files.append(i)

current_date = datetime.now()
year_ago = current_date - timedelta(days=180)

dates = []
delta = timedelta(days=1)
while current_date >= year_ago:
    dates.append(current_date.strftime("%d-%m-%Y"))
    current_date -= delta

dates.reverse();


for file in files:
   with open(file, 'a') as f:
        for dt in dates:
            random_numbers = random.choices(range(5), weights=[0.4, 0.3, 0.1, 0.1, 0.1], k=4)
            numbers_string = ";".join(str(num) for num in random_numbers)

            result_string = "{};{};empty\n".format(dt, numbers_string)
            f.write(result_string)
        f.close()