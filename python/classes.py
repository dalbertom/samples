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
