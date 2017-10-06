# loops.py

myString = ""
print(type(myString))

myString = 'single quotes'

# String literals
print("""\
      Usage: stuff
        -h help
""")
print('''hello
      world''')
long_string = ('long '
             'string')
print(long_string)

# Raw Strings
print('hello\nworld')
print(r'hello\nworld')

# Common String Methods in Python
print("axbxcx".count('x'))
print("axbxcx".find("x"))
print("HELLO".lower())
print("hello".upper())
print("hola".replace('h', 'b'))
print(" hello ".strip())

# String Indexes
a = "string"
print(a[1:3]) # indexing is 0-based, 3 is non-inclusive
print(a[:-1])

word = 'Python'
print(word[0])
print(word[5])
print(word[-1])
print(word[-6])
print(word[0:2]) # slicing
print(word[2:5])
print(word[:2])
print(word[2:])
print(word[4:42])
print(word[42:])

# lists.py
