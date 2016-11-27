package org.smart.parking.raspi.driver.webService;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.smart.parking.raspi.driver.JSONConverter;
import org.smart.parking.raspi.driver.to.DeviceDetailsTO;

@Path("/deviceManagement")
public class DeviceManagement {
	
	
	@Path("/claimDevice")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String claimDevice(String deviceDetails) throws UnknownHostException, SocketException{
		System.out.println("device details string value ="+deviceDetails);
		DeviceDetailsTO deviceDetailsTO = (DeviceDetailsTO) JSONConverter.convertToObject(deviceDetails, DeviceDetailsTO.class);
		System.out.println("device details dto device id value ="+deviceDetailsTO.getDeviceId());
		if(authenticateDevice(deviceDetailsTO.getDeviceId())){
			System.out.println("inside if connected ");
			deviceDetailsTO.setDeviceStatus("Connected");
			return JSONConverter.convertToJson(deviceDetailsTO);
		} 
			throw new RuntimeException("The device id doesn't match with the device !");
	}

	private boolean authenticateDevice(String deviceId) throws UnknownHostException, SocketException {
		InetAddress inetAddress = InetAddress.getLocalHost();
		System.out.println("inet address value  ="+inetAddress);
		NetworkInterface networkInterface = NetworkInterface.getByInetAddress(inetAddress);
		byte[] macAddress = networkInterface.getHardwareAddress();
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < macAddress.length; i++) {
			sb.append(String.format("%02X%s", macAddress[i], (i < macAddress.length - 1) ? "-" : ""));
		}
		System.out.println("deviceId = "+deviceId.length()+" 			mac address value "+sb.length());
		System.out.println("is authenticate = "+(""+sb).equals(deviceId));
		System.out.println("sb+toSting value = "+(sb.toString()).equals(deviceId));
		
		return ((""+sb).equals(deviceId));
	}


}
