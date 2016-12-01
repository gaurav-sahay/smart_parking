package org.smart.parking.raspi.driver.webService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestMain {

	public static void main(String[] args) throws IOException {
//		//InetAddress inetAddress = InetAddress.getLocalHost();
//		InetAddress inetAddress = InetAddress.getByName("192.168.1.3");
//		System.out.println("inet address value  ="+inetAddress);
//		NetworkInterface networkInterface = NetworkInterface.getByInetAddress(inetAddress);
//		byte[] macAddress = networkInterface.getHardwareAddress();
//		StringBuilder sb = new StringBuilder();
//		for (int i = 0; i < macAddress.length; i++) {
//			sb.append(String.format("%02X%s", macAddress[i], (i < macAddress.length - 1) ? "-" : ""));
//		}
//		//System.out.println("deviceId = "+deviceId.length()+" 			mac address value "+sb.length());
//		//System.out.println("is authenticate = "+(""+sb).equals(deviceId));
//		System.out.println("sb+toSting value = "+(sb.toString()));
		String command = "arp -a 10.157.107.27";
	       Process p = Runtime.getRuntime().exec(command);

	       BufferedReader inn = new BufferedReader(new InputStreamReader(p.getInputStream()));
	       //Pattern pattern = Pattern.compile(".*Physical Addres.*: (.*)");

	       while (true) {
	            String line = inn.readLine();
	            System.out.println(line);

		    if (line == null)
		        break;

//		    Matcher mm = pattern.matcher(line);
//		    if (mm.matches()) {
//		        System.out.println(mm.group(1));
//		    }
		}
	}

}
