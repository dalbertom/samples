# https://www.youtube.com/watch?v=IEEhzQoKtQU
# related: threads.py

import concurrent.futures
import time

start = time.perf_counter()

def do_something(seconds):
    print(f'Sleeping for {seconds} second(s)')
    time.sleep(seconds)
    return f'Done sleeping for {seconds} seconds(s)'

with concurrent.futures.ThreadPoolExecutor() as executor:
    f1 = executor.submit(do_something, 1)
    f2 = executor.submit(do_something, 1)
    print(f1.result())
    print(f2.result())

print(80 * '=')
with concurrent.futures.ThreadPoolExecutor() as executor:
    secs = [5, 4, 3, 2, 1]
    results = [executor.submit(do_something, i) for i in secs]
    for f in concurrent.futures.as_completed(results):
        print(f.result())

print(80 * '=')
with concurrent.futures.ThreadPoolExecutor() as executor:
    secs = [5, 4, 3, 2, 1]
    results = executor.map(do_something, secs)
    for result in results:
        print(result)

#threads = []
#for _ in range(10):
#    t = threading.Thread(target=do_something, args=[1.5])
#    threads.append(t)
#    t.start()
#
#for t in threads:
#    t.join()

finish = time.perf_counter()

print(f'Finished in {round(finish-start, 2)} second(s)')
