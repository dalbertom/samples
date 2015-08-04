import groovy.json.JsonBuilder
import groovy.json.JsonSlurper

def save(content, filePath) {
  new File(filePath).write(new JsonBuilder(content).toPrettyString())
}

def load(filePath) {
  new JsonSlurper().parseText(new File(filePath).text)
}

save([hello: 'world'], '/tmp/file.json')
println load('/tmp/file.json')
