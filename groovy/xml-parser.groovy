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
