#!/usr/bin/perl

open(my $in, "<", "input.txt") or die "Can't open input.txt: $!";
open(my $out, ">", "output.txt") or die "Can't open output.txt: $!";
open(my $log, ">>", "my.log") or die "Can't open my.log: $!";

# Read from an open filehandle using <> operator
# In scalar context it reads a single file
# In list context it reads the whole file (aka slurping)
my $line = <$in>;
my @lines = <$in>;

# The <> operator is most often seen in a while loop
while (<$in>) { # assigns each line in turn to $_
  print "Just read in this file: $_";
}

# Print to a different filehandle
print STDERR "This is your final warning.\n";
print $out $record;
print $log $logmessage;

# When you're done with filehandles, you should close them
close $in or die "$in: $!";
