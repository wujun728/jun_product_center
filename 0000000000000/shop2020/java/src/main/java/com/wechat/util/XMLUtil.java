package com.wechat.util;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XMLUtil {
	public static Map<String, String> getResult(String document2) {
		Map<String, String> map = new HashMap<String, String>();
		try {
			Document document = DocumentHelper.parseText(document2);
			Element root = document.getRootElement();
			Iterator<Element> it = root.elementIterator();
			while (it.hasNext()) {
				Element element = it.next();
				map.put(element.getName(), element.getTextTrim());
			}
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		return map;
	}

	public static Map<String, String> getMap(DocumentBuilder safebuilder,String document2) throws Exception {
		Map<String, String> map = new HashMap<String, String>(); 
		InputStream stream = new ByteArrayInputStream(document2.getBytes("UTF-8"));
        org.w3c.dom.Document doc = safebuilder.parse(stream);
        doc.getDocumentElement().normalize();
        NodeList nodeList = doc.getDocumentElement().getChildNodes();
        for (int idx=0; idx<nodeList.getLength(); ++idx) {
            Node node = nodeList.item(idx);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                org.w3c.dom.Element element = (org.w3c.dom.Element) node;
                map.put(element.getNodeName(), element.getTextContent());
            }
        } 
        stream.close(); 
        return map;
	}
	 
}
