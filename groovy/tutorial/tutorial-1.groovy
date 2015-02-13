println "Hello, World!"

// Variables
x = 1
println x
x = new java.util.Date()
println x
x = -3.1499392
println x
x = false
println x
x = "Hi"
println x

// Lists and Maps
list = [1776, -1, 33, 99, 0, 987654321]
println list[0]
println list.size()
scores = [ "Brett":100, "Pete":"Did not finish", "Andrew":86.87934 ]
println scores["Pete"]
println scores.Pete
scores["Pete"] = 3
println scores["Pete"]

emptyMap = [:]
emptyList = []
println emptyMap.size()
println emptyList.size()

// Conditional Execution
amPM = Calendar.getInstance().get(Calendar.AM_PM)
if (amPM == Calendar.AM) {
  println("Good morning")
} else {
  println("Good evening")
}
