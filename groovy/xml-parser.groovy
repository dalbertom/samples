def text = '''<?xml version="1.0" encoding="UTF-8"?>
<top-element>
  <sub-element1 attr="attribute1"/>
  <sub-element2>value1</sub-element2>
</top-element>
'''

def xml = new XmlParser().parseText(text)
println xml

def se1 = xml.'sub-element1'
se1?.each { xml.remove(it) }
se1 = xml.appendNode('sub-element1')
se1.appendNode('sub-sub-element11', ['att2' : 'attribute2'])
println xml

new XmlNodePrinter(new PrintWriter(System.out)).print(xml)
// <top-element>
//   <sub-element2>
//     value1
//   </sub-element2>
//   <sub-element1>
//     <sub-sub-element11 att2="attribute2"/>
//   </sub-element1>
// </top-element>


// MarkupBuilder
def xml = new XmlParser().parse(file(xmlReport))
def xmlFile = xml.file
def xmlOutput = new MarkupBuilder(new PrintWriter(System.out))

xmlOutput.suppressions {
  xmlFile.each { e ->
    def source = e.error.@source
    def file = e.@name
    p.sourceSets*.allSource.srcDirs.flatten().each { srcDir ->
      def srcDirStr = srcDir as String
      if (file.startsWith(srcDirStr)) {
        file = file.substring(srcDirStr.length() + 1).replaceAll(/[\/\\]/, '.')
      }
    }
    source.collect { it.substring(it.lastIndexOf('.') + 1) }.unique().each {
      suppress(files: file, checks: it)
    }
  }

