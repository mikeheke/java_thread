package com.mikehe.study.thread.producer_consumer;

import java.util.Random;

public class Consumer extends Thread {

	private String userName;
	
	private int consumeNum;
	
	private BreadShop breadShop;
	
	
	public Consumer(String userName, BreadShop breadShop) {
		super();
		this.userName = userName;
		this.breadShop = breadShop;
	}
	//=================================================================================================
	
	
//	public void consume(int consumeNum) {
//		breadShop.consume(consumeNum);
//	}
	
	@Override
	public void run() {
		//while (true) {//不停在消费
		for (int i=0; i<5; i++) {	
			//breadShop.consume(consumeNum);
			breadShop.consume(new Random().nextInt(50));
			try {
				Thread.currentThread().sleep(5000);//休息5s
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	
	//=================================================================================================
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getConsumeNum() {
		return consumeNum;
	}

	public void setConsumeNum(int consumeNum) {
		this.consumeNum = consumeNum;
	}

	public BreadShop getBreadShop() {
		return breadShop;
	}

	public void setBreadShop(BreadShop breadShop) {
		this.breadShop = breadShop;
	}
	
	
	
}
