package com.mikehe.study.thread.producer_consumer;

import org.apache.log4j.Logger;

public class Client {
	
	private static Logger log = Logger.getLogger(Client.class);

	public static void main(String[] args) {
		
		BreadShop breadShop = new BreadShop("Mike Bread Shop");
		
//		
//		//Producer
		Producer p1 = new Producer("p1", breadShop);
		Producer p2 = new Producer("p2", breadShop);
		Producer p3 = new Producer("p3", breadShop);
		Producer p4 = new Producer("p4", breadShop);
		Producer p5 = new Producer("p5", breadShop);
		
		//Consumer
		Consumer c1 = new Consumer("c1", breadShop);
		Consumer c2 = new Consumer("c2", breadShop);
		Consumer c3 = new Consumer("c3", breadShop);
		Consumer c4 = new Consumer("c3", breadShop);
		Consumer c5 = new Consumer("c3", breadShop);
		
//		//produce
//		p1.setProduceNum(11);
//		p2.setProduceNum(12);
//		p3.setProduceNum(18);
//		
//		//consume
//		c1.setConsumeNum(10);
//		c2.setConsumeNum(20);
//		c3.setConsumeNum(30);
		
		//start
		p1.start();
		c1.start();
		p2.start();
		c2.start();
		c3.start();
		p3.start();
		
		c4.start();
		p4.start();
		
		c5.start();
		
	}
	
}
