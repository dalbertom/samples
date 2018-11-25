class Sample(object):
    pass

x = Sample()
print(type(x))

class Dog(object):
    # Class Object Attribute
    species = 'mammal'

    # self can be something else, like this
    def __init__(self, breed, name):
        # Instance Attributes
        self.breed = breed
        self.name = name

sam = Dog(breed='Lab', name='Sammy')
#sam = Dog('Lab', 'Sammy')
print(sam)
print(sam.breed)
print(sam.species)
print(Dog.species)
print(sam.name)

print('\nCircle')
class Circle(object):
    pi = 3.14

    def __init__(self, radius=1):
        self.radius = radius

    def area(self):
        return Circle.pi * self.radius ** 2 

    def set_radius(self, radius):
        'This method takes in a radius, and resets the current radius of the circle'
        self.radius = radius

c = Circle(10)
print(c.area())
c.set_radius(100)
print(c.area())

print('\nBook')
class Book(object):
    def __init__(self, title, author, pages):
        print('A book has been created')
        self.title = title
        self.author = author
        self.pages = pages

    def __del__(self):
        print('A book has been deleted')

    def __len__(self):
        return self.pages

    def __str__(self):
        return 'Title: %s, Author: %s'.format(self.title, self.author)

b = Book('Biography', 'David', 2)
print(b)
print(len(b))
del b
