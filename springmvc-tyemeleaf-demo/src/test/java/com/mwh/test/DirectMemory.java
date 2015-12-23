package com.mwh.test;

import org.infinispan.commons.util.Util;
import org.infinispan.container.DataContainer;

public class DirectMemory {
	public DirectMemory(int a){
	}
	public static void main(String[] args) {

		// try {
		// ChronicleMap<String, String> statelessClient =
		// ChronicleMapStatelessClientBuilder
		// .createClientOf(new InetSocketAddress("localhost", 8076));
		// String s=StringUtils.repeat("w", 2014);
		// long st=System.currentTimeMillis();
		// for(int i =0 ;i<100000;i++){
		// statelessClient.put("key_"+i,s );
		// // statelessClient.remove("key_"+i);
		// // System.out.println(statelessClient.get("key_"+i));
		// // statelessClient.get("key_"+i);
		// }
		// System.out.println(System.currentTimeMillis()-st);
		// statelessClient.close();
		// } catch (IOException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		try {
//			 Util.getInstance("com.embracesource.test.DirectMemory",
//			 DirectMemory.class.getClassLoader());
			DataContainer dc = Util
					.<DataContainer> getInstance("org.infinispan.offheap.container.OffHeapDefaultDataContainer",
							DirectMemory.class.getClassLoader());
//			DataContainer dc = Util
//					.<DataContainer> getInstance("org.infinispan.container.DefaultDataContainer",
//							DirectMemory.class.getClassLoader());
//			System.out.println(dc);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
