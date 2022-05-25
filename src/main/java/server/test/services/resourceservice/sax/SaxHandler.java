package server.test.services.resourceservice.sax;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import server.test.services.resourceservice.reflection.ReflectionHelper;

public class SaxHandler extends DefaultHandler {
    private static final String CLASSNAME = "class";
    private String element = null;
    private Object classObject = null;

    @Override
    public void startDocument() throws SAXException {
        System.out.println("Start document");
    }

    @Override
    public void endDocument() throws SAXException {
        System.out.println("End document");
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (!qName.equals(CLASSNAME)) {
            element = qName;
        } else {
            String classname = attributes.getValue(0);
            System.out.println("Class name: " + classname);
            classObject = ReflectionHelper.createInstance(classname);
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        element = null;
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        if (element != null) {
            String value = new String(ch, start, length);
            System.out.println(element + ": " + value);
            ReflectionHelper.setFieldValue(classObject, element, value);
        }
    }

    public Object getClassObject() {
        return classObject;
    }

}
