#!/usr/bin/env perl

# Perl has three main variable types: scalars, arrays, and hashes

# Scalars
my $animal = "camel";
my $answer = 42;

# my is one of the requirements of use strict

print $animal;
print "The animal is $animal\n";
print "The square of $answer is ", $answer*$answer, "\n";

# Magic scalars
# $_ is the "default variable"


# Arrays
my @animals = ("camel", "llama", "owl");
my @numbers = (23, 42, 69);
my @mixed = ("camel", 42, 1.23);

print $animals[0]; # arrays are zero-indexed
print $animals[1]; # prints "llama"

# The special variable $#array tells the index of the last element
print $mixed[$#mixed]; # last element, prints 1.23

# @array gives the number of elements in array (same as $#array + 1)
print "\nNumber of elements in array: ";
print $#animals + 1;
print "\n";
print @animals == $#animals+1;

# Array slices
# To get multiple values from an array
@animals[0,1];	# gives ("camel", "llama");
@animals[0..2];	# gives ("camel", "llama", "owl");
@animals[1..$#animals]; # gives all except the first element

# Useful things to lists
my @sorted = sort @animals;
my @backwards = reverse @numbers;

# Special arrays
# @ARGV is the command line arguments
# @_ arguments passed to a subroutine


# Hashes
my %fruit_color = ("apple", "red", "banana", "yellow");
my %fruit_color = (
  apple => "red",
  banana => "yellow",
);

# To get hash elements:
$fruit_color{"apple"};	# Gives "red"
# You can get at lists of keys and values
my @fruits = keys %fruit_colors;
my @colors = values %fruit_colors;

# Special hashes
# %ENV contains environment variables

# A reference is a scalar value that can refer to other data types
my $variables = {
  scalar => {
    description => "single item",
    sigil => '$',
  },

  array => {
    description => "ordered list of items",
    sigil => '@',
  },

  hash => {
    description => "key/value pairs",
    sigil => '%',
  },
};

print "Scalars begin with a $variables->{'scalar'}->{'sigil'}\n";
#!/usr/bin/env perl
use strict;

# Variable scoping
$var = "value"; # global variables (bad practice!)
my $var2 = "value"; # lexically scoped variables (use strict to catch errors at compile time)


