#!/usr/bin/env perl

# if
if (condition) {
} elsif (other condition) {
} else {
}

# Negated version of if
unless (condition) {
}

if (!condition) {
}

# The traditional way
if ($zippy) {
  print "Yow!";
}

# the Perlish post-condition way
print "Yow!" if $zippy;
print "We have no bananas" unless $bananas;

# while
while (condition) {
}

until (condition) {
}

print "LA LA LA\n" while 1; #loops forever

# for
for ($i = 0; $i <= $max; $i++) {
}

# foreach
foreach (@array) {
  print "This element is $_\n";
}

print $list[$_] foreach 0 .. $max;

foreach my $key (keys %hash) {
  print "The value of $key is $hash{$key}\n";
}


