package org.testing_declarative_services_consumer;

import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Reference;
import org.testing_declarative_services.FirstService;

@Component
public class ConsumerService {
	
	@Reference(bind="setFirstService",unbind="unsetFirstService",referenceInterface=FirstService.class)
	private FirstService firstService;
	
	protected void setFirstService(FirstService firstService){
		System.out.println("Setting the service ");
		firstService.sayHello();
	}
	
	protected void unsetFirstService(FirstService firstService){
		firstService.sayByeBye();
	}
	
	

}
