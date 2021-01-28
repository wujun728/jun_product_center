package org.myframework.commons.util;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.Reader;
import java.io.StringReader;
import java.util.List;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentFactory;
import org.dom4j.DocumentType;
import org.dom4j.Element;
import org.dom4j.QName;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

/**
 * XML字符串，XML文件解析工具
 * <ol>
 * <li>{@link  }
 *
 * </ol>
 *
 * @author Wujun
 * @since 1.0
 */
public class XmlUtils {
    public static final String DEFAULT_ENCODING = "UTF-8";

    private XmlUtils() {
    }

    /**
     * Return the child element with the given name.  The element must be in
     *   the same name space as the parent element.
     *  @param element The parent element
     *  @param name The child element name
     *  @return The child element
     */
    public static Element child(Element element, String name) {
        return element.element(new QName(name, element.getNamespace()));
    }

    /**
     * @param element
     * @param name
     * @param optional
     * @return
     * @throws XMLDocException
     */
    public static Element child(Element element,
                                String name,
                                boolean optional)
        throws XMLDocException {
        Element child = element.element(new QName(name, element.getNamespace()));
        if (child == null && !optional) {
            throw new XMLDocException(name + " element expected as child of " +
                                      element.getName() + ".");
        }
        return child;
    }

    /** Return the child elements with the given name.  The elements must be in
        the same name space as the parent element.
        @param element The parent element
        @param name The child element name
        @return The child elements
     */
    public static List children(Element element, String name) {
        return element.elements(new QName(name, element.getNamespace()));
    }



    /**
     * @param element
     * @param name
     * @param optional
     * @return
     * @throws XMLDocException
     */
    public static String getAttribute(Element element,
                                      String name,
                                      boolean optional)
        throws XMLDocException {
    	Attribute attr = null;
    	if(element!=null)
    		attr = element.attribute(name);
        if (attr == null && !optional) {
        	if(element!=null)
        		throw new XMLDocException("Attribute " + name + " of " +
                                      element.getName() + " expected.");
        	else
        		return null;
        } else if (attr != null) {
            return attr.getValue();
        }
        else {
            return null;
        }
    }



    /**
     * @param element
     * @param name
     * @param optional
     * @return
     * @throws XMLDocException
     */
    public static java.util.Date getAttributeAsDate(Element element,
        String name,
        boolean optional)
        throws XMLDocException {
        String value = getAttribute(element, name, optional);
        if ( (optional) && ( (value == null) || (value.equals("")))) {
            return null;
        }
        else {
            try {
                return DateUtils.tryParse(value );
            }
            catch ( Exception exception) {
                throw new XMLDocException(element.getName() + "/@" + name +
                                          exception);
            }
        }
    }

    /**
     * @param element
     * @param name
     * @param optional
     * @return
     * @throws XMLDocException
     */
    public static String getAttributeAsString(Element element,
                                              String name,
                                              boolean optional)
        throws XMLDocException {
        return getAttribute(element, name, optional);
    }


    /**
     * @param element
     * @param name
     * @param optional
     * @return
     * @throws XMLDocException
     */
    public static int getAttributeAsInt(Element element,
                                        String name,
                                        boolean optional)
        throws XMLDocException {
        try {
            return Integer.parseInt(getAttribute(element, name, optional));
        }
        catch (NumberFormatException exception) {
            throw new XMLDocException(element.getName() + "/@" + name +
                                      " attribute: value format error.",
                                      exception);
        }
    }


    /**
     * @param element
     * @param name
     * @param defaultValue
     * @param optional
     * @return
     * @throws XMLDocException
     */
    public static int getAttributeAsInt(Element element,
                                        String name,
                                        int defaultValue,
                                        boolean optional)
        throws XMLDocException {
        String value = getAttribute(element, name, optional);
        if ( (optional) && ( (value == null) || (value.equals("")))) {
            return defaultValue;
        }
        else {
            try {
                return Integer.parseInt(value);
            }
            catch (NumberFormatException exception) {
                throw new XMLDocException(element.getName() + "/@" + name +
                                          " attribute: value format error.",
                                          exception);
            }
        }
    }

    public static float getAttributeAsFloat(Element element,
                                            String name,
                                            boolean optional)
        throws XMLDocException {
        try {
            return Float.parseFloat(getAttribute(element, name, optional));
        }
        catch (NumberFormatException exception) {
            throw new XMLDocException(element.getName() + "/@" + name +
                                      " attribute: value format error.",
                                      exception);
        }
    }


    public static float getAttributeAsFloat(Element element,
                                            String name,
                                            float defaultValue,
                                            boolean optional)
        throws XMLDocException {
        String value = getAttribute(element, name, optional);
        if ( (optional) && ( (value == null) || (value.equals("")))) {
            return defaultValue;
        }
        else {
            try {
                return Float.parseFloat(value);
            }
            catch (NumberFormatException exception) {
                throw new XMLDocException(element.getName() + "/@" + name +
                                          " attribute: value format error.",
                                          exception);
            }
        }
    }


    public static long getAttributeAsLong(Element element,
                                          String name,
                                          boolean optional)
        throws XMLDocException {
        try {
            return Long.parseLong(getAttribute(element, name, optional));
        }
        catch (NumberFormatException exception) {
            throw new XMLDocException(element.getName() + "/@" + name +
                                      " attribute: value format error.",
                                      exception);
        }
    }


    public static long getAttributeAsLong(Element element,
                                          String name,
                                          long defaultValue,
                                          boolean optional)
        throws XMLDocException {
        String value = getAttribute(element, name, optional);
        if ( (optional) && ( (value == null) || (value.equals("")))) {
            return defaultValue;
        }
        else {
            try {
                return Long.parseLong(value);
            }
            catch (NumberFormatException exception) {
                throw new XMLDocException(element.getName() + "/@" + name +
                                          " attribute: value format error.",
                                          exception);
            }
        }
    }


    public static Element getFirstChild(Element element,
                                        String name,
                                        boolean optional)
        throws XMLDocException {
        java.util.List list = element.elements(new QName(name,
            element.getNamespace()));

        if (list.size() > 0) {
            return (Element) list.get(0);
        }
        else {
            if (!optional) {
                throw new XMLDocException(name +
                    " element expected as first child of " +
                    element.getName() + ".");
            }
            else {
                return null;
            }
        }
    }


    public static Element getSibling(Element element, boolean optional)
        throws XMLDocException {
        return getSibling(element, element.getName(), optional);
    }


    public static Element getSibling(Element element,
                                     String name,
                                     boolean optional)
        throws XMLDocException {
        java.util.List list = element.getParent().elements(name);
        if (list.size() > 0) {
            return (Element) list.get(0);
        }
        else {
            if (!optional) {
                throw new XMLDocException(name + " element expected after " +
                                          element.getName() + ".");
            }
            else {
                return null;
            }
        }
    }


    public static String getContent(Element element, boolean optional)
        throws XMLDocException {
        String content = null;
		if(element!=null)
			 content =element.getText();
        if (content == null && !optional) {
        	if(element!=null)
        		throw new XMLDocException(element.getName() +
                                      " element: content expected.");
            else
            	return null;
        } else {
            return content;
        }
    }


    public static String getContentAsString(Element element, boolean optional)
        throws XMLDocException {
        return getContent(element, optional);
    }


    public static int getContentAsInt(Element element, boolean optional)
        throws XMLDocException {
        try {
            return Integer.parseInt(getContent(element, optional));
        }
        catch (NumberFormatException exception) {
            throw new XMLDocException(element.getName() +
                                      " element: content format error.",
                                      exception);
        }
    }


    public static int getContentAsInt(Element element,
                                      int defaultValue,
                                      boolean optional)
        throws XMLDocException {
        String value = getContent(element, optional);
        if ( (optional) && (value == null || value.equals(""))) {
            return defaultValue;
        }
        else {
            try {
                return Integer.parseInt(value);
            }
            catch (NumberFormatException exception) {
                throw new XMLDocException(element.getName() +
                                          " element: content format error.",
                                          exception);
            }
        }
    }


    public static long getContentAsLong(Element element, boolean optional)
        throws XMLDocException {
        try {
            return Long.parseLong(getContent(element, optional));
        }
        catch (NumberFormatException exception) {
            throw new XMLDocException(element.getName() +
                                      " element: content format error.",
                                      exception);
        }
    }


    public static long getContentAsLong(Element element,
                                      long defaultValue,
                                      boolean optional)
        throws XMLDocException {
        String value = getContent(element, optional);
        if ( (optional) && (value == null || value.equals(""))) {
            return defaultValue;
        }
        else {
            try {
                return Long.parseLong(value);
            }
            catch (NumberFormatException exception) {
                throw new XMLDocException(element.getName() +
                                          " element: content format error.",
                                          exception);
            }
        }
    }


    public static float getContentAsFloat(Element element, boolean optional)
        throws XMLDocException {
        try {
            return Float.parseFloat(getContent(element, optional));
        }
        catch (NumberFormatException exception) {
            throw new XMLDocException(element.getName() +
                                      " element: content format error.",
                                      exception);
        }
    }


    public static float getContentAsFloat(Element element,
                                          float defaultValue,
                                          boolean optional)
        throws XMLDocException {
        String value = getContent(element, optional);
        if ( (optional) && (value == null || value.equals(""))) {
            return defaultValue;
        }
        else {
            try {
                return Float.parseFloat(value);
            }
            catch (NumberFormatException exception) {
                throw new XMLDocException(element.getName() +
                                          " element: content format error.",
                                          exception);
            }
        }
    }


    public static java.util.Date getContentAsDate(Element element,
                                                  boolean optional)
        throws XMLDocException {
        String value = getContent(element, optional);
        if ( (optional) && (value == null || value.equals(""))) {
            return null;
        }
        else {
            try {
                return DateUtils.tryParse(value );
            }
            catch ( Exception exception) {
                throw new XMLDocException(element.getName() +
                                          " element: content format error.",
                                          exception);
            }
        }
    }

    public static String getSubTagValue(Element root, String subTagName) {
        String returnString = root.elementText(subTagName);
        return returnString;
    }


    public static String getSubTagValue(Element root,
                                        String tagName,
                                        String subTagName) {
        Element child = root.element(tagName);
        String returnString = child.elementText(subTagName);
        return returnString;
    }


    public static Element appendChild(Element parent,
                                      String name,
                                      String value) {
        Element element = parent.addElement(new QName(name, parent.getNamespace()));
        if (value != null) {
            element.addText(value);
        }
        return element;
    }


    public static Element appendChild(Element parent, String name) {
        return parent.addElement(new QName(name, parent.getNamespace()));
    }


    public static Element appendChild(Element parent,
                                      String name,
                                      int value) {
        return appendChild(parent, name, String.valueOf(value));
    }


    public static Element appendChild(Element parent,
                                      String name,
                                      long value) {
        return appendChild(parent, name, String.valueOf(value));
    }

    public static Element appendChild(Element parent,
                                      String name,
                                      float value) {
        return appendChild(parent, name, String.valueOf(value));
    }

    public static Element appendChild(Element parent,
                                      String name,
                                      java.util.Date value) {
		return appendChild(parent, name,DateUtils.format( value));
    }


    public static boolean checkDocumentType(Document document,
                                            String dtdPublicId) {
        DocumentType documentType = document.getDocType();
        if (documentType != null) {
            String publicId = documentType.getPublicID();
            return publicId != null && publicId.equals(dtdPublicId);
        }
        return true;
    }


    public static Document createDocument()
        throws XMLDocException {
        DocumentFactory factory = new DocumentFactory();
        Document document = factory.createDocument();
        return document;
    }


    public static Document fromXML(Reader in, String encoding)
        throws XMLDocException {
        try {
            if (encoding == null || encoding.equals("")) {
                encoding = DEFAULT_ENCODING;
            }
            SAXReader reader = new SAXReader();
            Document document = reader.read(in, encoding);
            return document;
        }
        catch (Exception ex) {
            throw new XMLDocException(ex);
        }
    }

    public static Document fromXML(InputStream inputSource, String encoding)
        throws XMLDocException {
        try {
            if (encoding == null || encoding.equals("")) {
                encoding = DEFAULT_ENCODING;
            }
            SAXReader reader = new SAXReader();
            Document document = reader.read(inputSource, encoding);
            return document;
        }
        catch (Exception ex) {
            throw new XMLDocException(ex);
        }
    }


    public static Document fromXML(String source, String encoding)
        throws XMLDocException {
        return fromXML(new StringReader(source), encoding);
    }


    public static void toXML(Document document, java.io.Writer outWriter,
                             String encoding)
        throws XMLDocException {
        //
        OutputFormat outformat = OutputFormat.createPrettyPrint();
        if (encoding == null || encoding.trim().equals("")) {
            encoding = DEFAULT_ENCODING;
        }

        outformat.setEncoding(encoding);
        XMLWriter xmlWriter = null;
        try {
            xmlWriter = new XMLWriter(outWriter, outformat);
            xmlWriter.write(document);
            xmlWriter.flush();
        }
        catch (java.io.IOException ex) {
            throw new XMLDocException(ex);
        }
        finally {
            if (xmlWriter != null) {
                try {
                    xmlWriter.close();
                }
                catch (java.io.IOException ex) {
                }
            }
        }
    }


    public static void toXML(Document document, java.io.OutputStream outStream,
                             String encoding)
        throws XMLDocException {
        //
        OutputFormat outformat = OutputFormat.createPrettyPrint();
        if (encoding == null || encoding.trim().equals("")) {
            encoding = DEFAULT_ENCODING;
        }

        outformat.setEncoding(encoding);
        XMLWriter xmlWriter = null;
        try {
            xmlWriter = new XMLWriter(outStream, outformat);
            xmlWriter.write(document);
            xmlWriter.flush();
        }
        catch (java.io.IOException ex) {
            throw new XMLDocException(ex);
        }
        finally {
            if (xmlWriter != null) {
                try {
                    xmlWriter.close();
                }
                catch (java.io.IOException ex) {
                }
            }
        }
    }


    public static String toXML(Document document, String encoding)
        throws XMLDocException {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        toXML(document, stream, encoding);
        if (stream != null) {
            try {
                stream.close();
            }
            catch (java.io.IOException ex) {
            }
        }
        return stream.toString();
    }



}
