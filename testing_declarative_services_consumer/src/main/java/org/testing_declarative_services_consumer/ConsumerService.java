package org.testing_declarative_services_consumer;

import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Property;
import org.apache.felix.scr.annotations.Reference;
import org.apache.log4j.Logger;
import org.testing_declarative_service_api.FirstServiceApi;

@Component(name="Declarative Consumer Service")
public class ConsumerService {
	private static final Logger  LOG = Logger.getLogger(ConsumerService.class);
	@Property(name="consumer service")
	@Reference(bind="setFirstService",unbind="unsetFirstService",referenceInterface=FirstServiceApi.class)
	private FirstServiceApi firstServiceApi;
	
	protected void setFirstService(FirstServiceApi firstServiceApi){
		this.firstServiceApi = firstServiceApi;
		LOG.info("Setting the first service");
		this.firstServiceApi.sayHello();
	}
	
	protected void unsetFirstService(FirstServiceApi firstServiceApi){
		LOG.info("Unsetting the first service");
		this.firstServiceApi.sayByeBye();
		this.firstServiceApi =  null;
	}
	

}
