package org.testing_declarative_services;

import org.apache.felix.scr.annotations.Activate;
import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Deactivate;
import org.apache.felix.scr.annotations.Service;
import org.osgi.service.component.ComponentContext;

@Component(immediate=true)
@Service(value=FirstService.class)
public class FirstService {


	@Activate
	public void activate(ComponentContext componentContext){
		System.out.println("Hello I am active now !");
	}
	
	@Deactivate
	public void deactivate(ComponentContext componentContext){
		System.out.println("Bye Bye !");
	}
	
	public void sayHello(){
		System.out.println("Hello world ! :)");
	}
	public void sayByeBye(){
		System.out.println("Bye Bye World ! :(");
	}

}
