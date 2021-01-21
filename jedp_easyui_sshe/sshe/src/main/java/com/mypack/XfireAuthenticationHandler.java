package com.mypack;

import org.codehaus.xfire.MessageContext;
import org.codehaus.xfire.XFireRuntimeException;
import org.codehaus.xfire.handler.AbstractHandler;
import org.jdom.Element;
import org.jdom.Namespace;



public class XfireAuthenticationHandler extends AbstractHandler {
	private static final String USERNAME = "sshe";
	private static final String PASSWORD = "12345678";
	private static final String NS = "http://webservice";
	
	@Override
	public void invoke(MessageContext ctx) throws Exception {
		// Check if header exists
		
		Element header = ctx.getInMessage().getHeader();
		if (header == null) {
			throw new XFireRuntimeException("Missing SOAP Header");
		}
		// Does it have version tag
		Element name = header.getChild("USERNAME", Namespace.getNamespace(NS));
		Element pass = header.getChild("PASSWORD", Namespace.getNamespace(NS));
		if (name.getValue().equals(USERNAME)
				&& pass.getValue().equals(PASSWORD)) {
			System.out.println("验证"+name+"通过");
		} else {
			System.out.println("验证未通过");
			throw new XFireRuntimeException("Authentication Failure");
		}
		ctx.setProperty("USERNAME", USERNAME);
		ctx.setProperty("PASSWORD", PASSWORD);
	}

}
