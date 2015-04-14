package com.mikehe.study.thread.producer_consumer;

public class TestObj {

	public synchronized void test() {
		
		System.out.println("11111111");
		
		try {
			Thread.sleep(50000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
