# https://www.youtube.com/watch?v=fKl2JW_qrso
# related: threads.py

import multiprocessing
import time

start = time.perf_counter()

def do_something(seconds):
    print(f'Sleeping for {seconds} second(s)')
    time.sleep(seconds)
    print('Done')

processes = []
for _ in range(10):
    # unlike with threads, arguments to processes
    # must be able to be serialized with pickle
    p = multiprocessing.Process(target=do_something, args=[1.5])
    processes.append(p)
    p.start()

for p in processes:
    p.join()

print(80 * '=')
p1 = multiprocessing.Process(target=do_something, args=[1])
p2 = multiprocessing.Process(target=do_something, args=[1])
p1.start()
p2.start()
p1.join()
p2.join()

finish = time.perf_counter()

print(f'Finished in {round(finish-start, 2)} second(s)')
