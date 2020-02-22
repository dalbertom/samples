# https://www.youtube.com/watch?v=fKl2JW_qrso
# related: process.py
# related: thread_pool.py

import concurrent.futures
import time

start = time.perf_counter()

def do_something(seconds):
    print(f'Sleeping for {seconds} second(s)')
    time.sleep(seconds)
    return f'Done sleeping for {seconds} seconds(s)'

with concurrent.futures.ProcessPoolExecutor() as executor:
    secs = [10, 9, 8, 7, 6, 5, 4, 3, 2, 1]
    results = executor.map(do_something, secs)
    for result in results:
        print(result)

print(80 * '=')
with concurrent.futures.ProcessPoolExecutor() as executor:
    secs = [10, 9, 8, 7, 6, 5, 4, 3, 2, 1]
    results = [executor.submit(do_something, i) for i in secs]
    for f in concurrent.futures.as_completed(results):
        print(f.result())

print(80 * '=')
with concurrent.futures.ProcessPoolExecutor() as executor:
    f1 = executor.submit(do_something, 1)
    f2 = executor.submit(do_something, 1)
    print(f1.result())
    print(f2.result())
    
#processes = []
#for _ in range(10):
#    # unlike with threads, arguments to processes
#    # must be able to be serialized with pickle
#    p = multiprocessing.Process(target=do_something, args=[1.5])
#    processes.append(p)
#    p.start()
#
#for p in processes:
#    p.join()
#
#print(80 * '=')
#p1 = multiprocessing.Process(target=do_something, args=[1])
#p2 = multiprocessing.Process(target=do_something, args=[1])
#p1.start()
#p2.start()
#p1.join()
#p2.join()

finish = time.perf_counter()

print(f'Finished in {round(finish-start, 2)} second(s)')
