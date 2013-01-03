# system - you want to execute a command and don't want to capture its output
system(); # accepts as argument either a scalar or an array.
#-- calling 'command' with arguments
system("command arg1 arg2 arg3");
#-- better way of calling the same command
system("command", "arg1", "arg2", "arg3");
# The return value is set in $?; this is the exit status as returned by the 'wait' call;
# to get the real exit status, $? >> 8
# if the value of $? is -1, then the command failed to execute,
# $! will contain the reason of reason of the failure
system("command", "arg1");
if ( $? == -1) {
  print "command failed: $!\n";
} else {
  printf "command exited with value %d", $? >> 8;
}

# exec - you don't want to return to the calling perl script
# never returns to the calling program, except in the case of
# failure because the specified command does not exist AND the
# exec argument is an array

# backticks - you want to capture the output of that command
# In scalar context it returns a single (possible multiline) string,
# in list context it returns a list of lines or an empty list if the
# command failed
# The exit status of the executed command is stored in $?
#-- scalar context
$result = `command arg1 arg2`;
#-- list context
@result = `command arg1 arg2`;
# This collects STDOUT. To collect STDERR as well, you need to redirect
#-- capture STDERR as well as STDOUT
$result = `command 2>&1`;

# open - you want to pipe the command (as input or output) to your script
#- capture the data of a command (syntax: open("command |"))
#- feed an external command with data generated from the Perl script (syntax: open("| command"))
#-- list the processes running on your system
open(PS, "ps -e -o pid,stime,args |") || die "Failed: $!\n";
while (<PS>) {
  #-- do something
}
#-- send an email to user@localhost
open(MAIL, "| /bin/mailx -s test user\@localhost ") || die "mailx failed: $!\n";
print MAIL "This is a test message";
