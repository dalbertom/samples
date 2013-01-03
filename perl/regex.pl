#!/usr/bin/perl

# Simple matching
if (/foo/) {}        # true if $_ contains "foo"
if ($a =~ /foo/) {}  # true if $a contains "foo"

# Simple substitution
s/foo/bar/;         # replaces foo with bar in $_
$a =~ s/foo/bar/;   # replaces foo with bar in $a
$a =~ s/foo/bar/g;  # replaces ALL INSTANCES of foo with bar in $a

# More complex regular expressions
# .     a single character
# \s    a whitespace character
# \S    a non-whitespace character
# \d    a digit (0-9)
# \D    a non-digit
# \w    a word character (a-z, A-Z, 0-9, _)
# \W    a non-word character
# [aeiou]   matches a single character in the given set
# [^aeiou]  matches a single character outside the given set
#
# ^     start of string
# $     end of string
#
# *     zero or more of the previous thing
# +     one or more of the previous thing
# ?     zero or one of the previous thing
# {3}   exactly 3 of the previous thing
# {3,6} between 3 and 6 of the previous thing
# {3,}  3 or more of the previous thing

/^\d+/  # string starts with one or more digits
/^$/    # nothing in the string
/(\d\s){3}/ # three single digits, followed by a whitespace (eg "3 4 5 ")
/(a.)+/ # matches a string in which every odd-numbered letter is a (eg "abacadaf")

# This loop reads from STDIN, and prints non-blank lines:
while (<>) {
  next if /^$/;
  print;
}

# Parenthesis for capturing
if ($email =~ /([^@]+)@(.+)/) {
  print "Username is $1\n";
  print "Hostname is $2\n";
}
