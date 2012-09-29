#!/usr/bin/perl
use strict;
use warnings;

# This is a comment
print "Hello, world";

# Whitespace is irrelevant
print
  "Hello, world"
  ;

# this would print with a linebreak in the middle
print "Hello
 world";

# Single quotes may also be used around literal strings
print 'Hello, world';

# Only double quotes "interpolate" variables and special characters
# such as newlines \n
my $name="David";
print "Hello, $name\n"; # works fine
print 'Hello, $name\n'; # prints $name\n

# Numbers don't need quotes around them
print 42;

# Parenthesis can be used
print("Hello, world\n");
