package org.testing_declarative_services;

import org.apache.felix.scr.annotations.Activate;
import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Deactivate;
import org.apache.felix.scr.annotations.Service;
import org.osgi.service.component.ComponentContext;
import org.testing_declarative_service_api.FirstServiceApi;

@Component
@Service(value=FirstServiceApi.class)
public class FirstService implements FirstServiceApi{


	@Activate
	public void activate(ComponentContext componentContext){
		System.out.println("Hello I am active now !");
	}
	
	@Deactivate
	public void deactivate(ComponentContext componentContext){
		System.out.println("Bye Bye !");
	}
	
	@Override
	public void sayHello(){
		System.out.println("Hello world ! :)");
	}
	@Override
	public void sayByeBye(){
		System.out.println("Bye Bye World ! :(");
	}

}
