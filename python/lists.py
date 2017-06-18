# strings.py
squares = [1, 4, 9, 16, 25]
print(squares)
print(squares[0])
print(squares[-3:]) # 9, 16, 25
print(squares + [36, 49, 64, 81, 100])
array = [1, 2, 3]
array[1] = 20
print(array) # 1, 20, 3
array.append(50) # 1, 20, 3, 50

letters = ['a', 'b', 'c', 'd', 'e', 'f', 'g']
letters[2:5] = ['C', 'D', 'E']
letters[2:5] = [] #remove items 2, 3, 4
letters[:] = [] # clear list

letters = ['a', 'b', 'c', 'd']
length = len(letters) # length = 4
print(length)

# fibonacci.py
