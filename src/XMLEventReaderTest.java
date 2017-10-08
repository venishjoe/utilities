
/*******************************************************************************
 Copyright (c) 2010 Venish Joe Clarence (http://venishjoe.net)

 Permission is hereby granted, free of charge, to any person obtaining a copy
 of this software and associated documentation files (the "Software"), to deal
 in the Software without restriction, including without limitation the rights
 to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 copies of the Software, and to permit persons to whom the Software is
 furnished to do so, subject to the following conditions:

 The above copyright notice and this permission notice shall be included in all
 copies or substantial portions of the Software.

 THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 SOFTWARE.

 ******************************************************************************/

import java.io.File;
import java.io.FileInputStream;

import javax.xml.stream.EventFilter;
import javax.xml.stream.StreamFilter;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.events.XMLEvent;

public class XMLEventReaderTest {
  public static void main(String[] args) throws Exception {
    XMLInputFactory inputFactory = XMLInputFactory.newInstance();
    XMLEventReader reader = inputFactory.createXMLEventReader(new FileInputStream(new File("a.xml")));
    reader = inputFactory.createFilteredReader(reader, new EventFilter() {
      public boolean accept(XMLEvent event) {
        return false;
      }
    });
    reader = inputFactory.createFilteredReader(reader, new ElementOnlyFilter());

    System.out.println(reader.hasNext());
    System.out.println(reader.next());
  }
}

class ElementOnlyFilter implements EventFilter, StreamFilter {
  public boolean accept(XMLEvent event) {
    return accept(event.getEventType());
  }

  public boolean accept(XMLStreamReader reader) {
    return accept(reader.getEventType());
  }

  private boolean accept(int eventType) {
    return eventType == XMLStreamConstants.START_ELEMENT
        || eventType == XMLStreamConstants.END_ELEMENT;
  }
}
