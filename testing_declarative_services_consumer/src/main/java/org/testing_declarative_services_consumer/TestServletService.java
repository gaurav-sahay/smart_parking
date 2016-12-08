package org.testing_declarative_services_consumer;

import java.util.Hashtable;

import javax.servlet.ServletException;

import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Property;
import org.apache.felix.scr.annotations.Reference;
import org.apache.log4j.Logger;
import org.glassfish.jersey.servlet.ServletContainer;
import org.osgi.service.http.HttpService;
import org.osgi.service.http.NamespaceException;

@Component(name="Test Servlet Consumer Service")
public class TestServletService {
	private static final Logger  LOG = Logger.getLogger(TestServletService.class);
	
	@Property(name="test servlet consumer service")
	@Reference(bind="setHttpService",unbind="unsetHttpService",referenceInterface=HttpService.class)
	private HttpService httpService;
	
	protected void setHttpService(HttpService httpService) throws ServletException, NamespaceException{
		this.httpService = httpService;
		LOG.info("Setting the httpservice");
		Hashtable<String, String> props = new Hashtable<String, String>();
		props.put("jersey.config.server.provider.packages", "org.smart.parking.raspi.driver.webService;org.smart.parking.raspi.test");
		httpService.registerServlet("/hello/*", new ServletContainer(), props, null);
	}
	
	protected void unsetHttpService(HttpService httpService){
		LOG.info("Unsetting the httpservice");
		httpService.unregister("/hello/*");
		this.httpService =  null;
	}

}
