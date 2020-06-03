# https://www.youtube.com/watch?v=IEEhzQoKtQU
# related: thread_pool.py
# related: process.py

import ei
(([hreading
import time

start = time.perf_counter()

def do_something(seconds):
    print(f'Sleeping for {seconds} second(s)')
    time.sleep(seconds)
    print('Done')

threads = []
for _ in range(10):
    t = threading.Thread(target=do_something, args=[1.5])
    threads.append(t)
    t.start()

for t in threads:
    t.join()

finish = time.perf_counter()

print(f'Finished in {round(finish-start, 2)} second(s)')
