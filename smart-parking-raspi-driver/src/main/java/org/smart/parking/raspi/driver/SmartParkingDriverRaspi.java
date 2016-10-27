package org.smart.parking.raspi.driver;

import java.util.HashMap;
import java.util.Map;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalInput;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.Pin;
import com.pi4j.io.gpio.PinPullResistance;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;
import com.pi4j.io.gpio.event.GpioPinDigitalStateChangeEvent;
import com.pi4j.io.gpio.event.GpioPinListenerDigital;

public class SmartParkingDriverRaspi implements BundleActivator {
	private static volatile int total_num_available_parking = 4;
	private static Map<Pin, GpioPinDigitalOutput> ledMap = new HashMap<Pin, GpioPinDigitalOutput>();

	@Override
	public void start(BundleContext bundleContext) throws Exception {
		System.out.println("Started smart parking service! ");

		// create GPIO controller
		final GpioController gpio = GpioFactory.getInstance();

		// create GPIO listener
		GpioPinListenerDigital listener  = new GpioPinListenerDigital() {

			@Override
			public void handleGpioPinDigitalStateChangeEvent(
					GpioPinDigitalStateChangeEvent event) {
				System.out.println(" --> GPIO PIN STATE CHANGE: " + event.getPin().getPin() + " = " + event.getState());
				if(event.getState().isHigh()){
					total_num_available_parking--;
					ledMap.get(event.getPin().getPin()).setState(PinState.LOW);

				} else if(event.getState().isLow()){
					total_num_available_parking++;
					ledMap.get(event.getPin().getPin()).setState(PinState.HIGH);
				}
				System.out.println("Total number of available parking = "+total_num_available_parking);
			}
		};

		// provision gpio input pins with its internal pull down resistor enabled
		GpioPinDigitalInput[] pins = {
				gpio.provisionDigitalInputPin(RaspiPin.GPIO_00, PinPullResistance.PULL_DOWN),
				gpio.provisionDigitalInputPin(RaspiPin.GPIO_01, PinPullResistance.PULL_DOWN),
				gpio.provisionDigitalInputPin(RaspiPin.GPIO_02, PinPullResistance.PULL_DOWN),
				gpio.provisionDigitalInputPin(RaspiPin.GPIO_03, PinPullResistance.PULL_DOWN),
		};

		ledMap.put(RaspiPin.GPIO_00, gpio.provisionDigitalOutputPin(RaspiPin.GPIO_04, "Parking_1", PinState.HIGH));
		ledMap.put(RaspiPin.GPIO_01, gpio.provisionDigitalOutputPin(RaspiPin.GPIO_05, "Parking_2", PinState.HIGH));
		ledMap.put(RaspiPin.GPIO_02, gpio.provisionDigitalOutputPin(RaspiPin.GPIO_06, "Parking_3", PinState.HIGH));
		ledMap.put(RaspiPin.GPIO_03, gpio.provisionDigitalOutputPin(RaspiPin.GPIO_07, "Parking_4", PinState.HIGH));
		// create and register gpio pin listener
		gpio.addListener(listener, pins);
		System.out.println(" ... complete the GPIO circuit and see the listener feedback here in the console.");
	}

	@Override
	public void stop(BundleContext bundleContext) throws Exception {
		System.out.println("Shutting down smart parking service! ");

	}

}
