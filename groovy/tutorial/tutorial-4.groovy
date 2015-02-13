println "potatoe" ==~ /potatoe/
println "potatoe" ==~ /potatoe?/
println "motato" ==~ /potatoe?/

def checkSpelling = { spellingAttempt, spellingRegularExpression ->
  if (spellingAttempt ==~ spellingRegularExpression) {
    println "Congratulations, you spelled it correctly."
  } else {
    println "Sorry, try again"
  }
}

theRegularExpression = /Wisniewski/
checkSpelling "Wisniewski", theRegularExpression
checkSpelling "Wisnewski", theRegularExpression

println "How tall is Angelina Jolie?" ==~ /[^\?]+\?/
