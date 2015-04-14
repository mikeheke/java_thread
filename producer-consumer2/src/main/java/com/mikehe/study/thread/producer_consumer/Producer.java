package com.mikehe.study.thread.producer_consumer;

import java.util.Random;

public class Producer extends Thread {

	private String userName;
	
	private int produceNum;
	
	private BreadShop breadShop;

	public Producer(String userName, BreadShop breadShop) {
		super();
		this.userName = userName;
		this.breadShop = breadShop;
	}
	//=================================================================================================
	
	
	
//	public void produce(int produceNum) {
//		breadShop.produce(produceNum);
//	}
	
	
	@Override
	public void run() {
		while (true) {//不停在生产
		//for (int i=0; i<5; i++) {	
			breadShop.produce(new Random().nextInt(50));
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

	public int getProduceNum() {
		return produceNum;
	}

	public void setProduceNum(int produceNum) {
		this.produceNum = produceNum;
	}

	public BreadShop getBreadShop() {
		return breadShop;
	}

	public void setBreadShop(BreadShop breadShop) {
		this.breadShop = breadShop;
	}

	
}
