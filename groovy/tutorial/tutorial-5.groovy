// Capture groups
locationData = "Liverpool, England: 53d 25m 0s N 3d 0m 0s"
regex = /(\w+), (\w+): (\d+). (\d+). (\d+). ([A-Z]) (\d+). (\d+). (\d+)./

matcher = (locationData =~ regex)

println matcher[0]
if (matcher.matches()) {
  println matcher.getCount() + " occurrence of the regex was found"
  println matcher[0][1] + " is in the " + matcher[0][6] + " hemisphere. (According to: " + matcher[0][0] + ")"
}

// Non-matching Groups
names = [
  "Graham James Edward Miller",
  "Andrew Gregory Macintyre",
  "No MiddleName"
]
printClosure = {
  matcher = (it =~ /(.*?)(?: .*)* (.*)/); // non-matching group in the middle
  if (matcher.matches())
    println(matcher[0][2] + ", " + matcher[0][1]);
}
names.each(printClosure)

// Replacement
excerpt = """At school, Harry had no one. Everybody knew that Dudley's
gang hated that odd Harry Potter in his baggy old clothes and broken
glasses, and nobody liked to disagree with Dudley's gang."""
matcher = (excerpt =~ /Harry Potter/)
excerpt = matcher.replaceAll("Tanya Grotter")

matcher = (excerpt =~ /Harry/);
excerpt = matcher.replaceAll("Tanya")
println "Publish it! " + excerpt
