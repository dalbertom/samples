// Closures
square = { it * it }
println square(9)
println ([ 1, 2, 3, 4 ].collect(square))

printMapClosure = { key, value -> println key + "=" + value }
println([ "Yue" : "Wu", "Mark" : "Williams", "sudha" : "Kumari" ].each(printMapClosure))

// Dealing with Files
myFile = new File('myfile.txt')
printFileLine = { println "File line: " + it }
myFile.eachLine(printFileLine)

// Dealing with strings
stringDate = "2005-07-04"
dateArray = stringDate.split("-")
year = dateArray[0].toInteger()
year = year + 1
newDate = year + "-" + dateArray[1] + "-" + dateArray[2]
println newDate
