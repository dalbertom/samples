# conditionals.py

def someFunction():
    print("boo")

someFunction()

def someFunction(a, b):
    print(a + b)

someFunction(12, 543)

# Default Argument Values
# https://docs.python.org/3/tutorial/controlflow.html
def f(a, L=[]):
  L.append(a)
  return L

print(f(1))
print(f(2))
print(f(3))

# Argument Lists and Dictionaries
def g(a, *b, **c):
  print(a)
  for bb in b:
    print(bb)
  for cc in c:
    print(cc,':',c[cc])

g('hello world', 'hola', 'mundo', en='hello', es='hola')
# loops.py
