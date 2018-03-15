import xml.etree.ElementTree as XML

string = '<root><element>value</element></root>'
xml = XML.fromstring(string)
print(xml.find('element').text)
