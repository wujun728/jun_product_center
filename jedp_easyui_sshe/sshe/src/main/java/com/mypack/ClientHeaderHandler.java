package com.mypack;

import org.codehaus.xfire.MessageContext;
import org.codehaus.xfire.handler.AbstractHandler;
import org.jdom.Element;

public class ClientHeaderHandler extends AbstractHandler {
	private static final String USERNAME = "sshe_chat";
	private static final String PASSWORD = "12345678";
	private static final String NS = "http://webservice";
	
	public void invoke(MessageContext ctx) throws Exception {
		Element header = ctx.getOutMessage().getOrCreateHeader();
		header.addContent(new Element("USERNAME", NS).addContent(USERNAME));
		header.addContent(new Element("PASSWORD", NS).addContent(PASSWORD));
	}
}
