@Grab('net.sourceforge.nekohtml:nekohtml:1.9.15')
@Grab(group='org.codehaus.groovy.modules.http-builder', module='http-builder', version='0.7')

import groovyx.net.http.HTTPBuilder
import static groovyx.net.http.ContentType.JSON
import static groovyx.net.http.Method.GET
import static groovyx.net.http.Method.POST

def http = new HTTPBuilder('http://api.openweathermap.org/data/2.5/')

http.get(path: 'weather', query: [q: 'London']) { resp, json ->
  println resp.status
  println "It is currently ${json.weather.main[0]} in London."
  println "The temperature is ${json.main.temp} degrees Kelvin"
}

http = new HTTPBuilder('http://ajax.googleapis.com')
// perform a GET requset, expecging JSON response data
http.request(GET, JSON) {

  uri.path = '/ajax/services/search/web'
  uri.query = [v: '1.0', q: 'Calvin and Hobbes']

  headers.'User-Agent' = 'Mozilla/5.0 Ubuntu/8.10 Firefox/3.0.4'

  // response handler for a success response code:
  response.success = { resp, json ->
    println resp.status

    // parse the JSON response object:
    json.responseData.results.each {
      println "  ${it.titleNoFormatting} : ${it.visibleUrl}"
    }
  }

  // handler for any failure status code:
  response.failure = { resp ->
    println "Unexpected error: ${resp.status} : ${resp.statusLine.reasonPhrase}"
  }
}

