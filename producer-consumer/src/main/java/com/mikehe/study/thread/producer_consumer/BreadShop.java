package com.mikehe.study.thread.producer_consumer;

import java.util.LinkedList;

import org.apache.log4j.Logger;

public class BreadShop {
	
	private static Logger log = Logger.getLogger(BreadShop.class);
	
	private String name;
	
	/**
	 * contain bread
	 */
	private LinkedList<Bread> breadBasket = new LinkedList<Bread>();
	
	public static final int MAX_NUM = 50;

	public BreadShop() {
		super();
	}

	public BreadShop(String name) {
		super();
		this.name = name;
	}

	public synchronized void produce(int produceNum) {
		
		//log.info("enter produce..........");
		
		while (breadBasket.size()+produceNum > MAX_NUM) {
			try {
				log.info("面包篮子最大存量："+MAX_NUM+"; "+
						 "当前存量："+breadBasket.size()+"; "+
						 Thread.currentThread().getName()+"要生产的数量:"+ produceNum+"; "+
						 "总数超过最大存量，暂时不能生产! 等待中...");
				//线程在此等待中，持有此对象的其他线程通知(notify())后，继续执行
				//此线程释放持有的对象锁
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		

		try {
			log.info("面包篮子最大存量："+MAX_NUM+"; "+
					 "当前存量："+breadBasket.size()+"; "+
					Thread.currentThread().getName()+" 正在生产面包"+produceNum+"个...");
			Thread.currentThread().sleep(10000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		
		//
		for (int i=0; i<produceNum; i++) {
			this.breadBasket.add(new Bread());
		}
		
		log.info("面包篮子最大存量："+MAX_NUM+"; "+
				 Thread.currentThread().getName()+"生产了:"+ produceNum+"个; "+
				 "当前存量："+breadBasket.size()+"; ");
		
		//
		this.notifyAll();
	}
	
	public synchronized void consume(int consumeNum) {
		
		//log.info("enter consume..........");
		
		while (consumeNum > breadBasket.size()) {
			try {
				log.info("面包篮子最大存量："+MAX_NUM+"; "+
						   "当前存量："+breadBasket.size()+"; "+
						   Thread.currentThread().getName()+"要消费的数量:"+ consumeNum+"; "+
						   "消费数超过当前存量，暂时不能消费! 等待中...");
				//线程在此等待中，持有此对象的其他线程通知(notify())后，继续执行
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		try {
			log.info("面包篮子最大存量："+MAX_NUM+"; "+
					 "当前存量："+breadBasket.size()+"; "+
					Thread.currentThread().getName()+" 正在消费面包"+consumeNum+"个...");
			Thread.currentThread().sleep(10000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		
		for (int i=0; i<consumeNum; i++) {
			breadBasket.remove();
		}
		
		log.info("面包篮子最大存量："+MAX_NUM+"; "+
				Thread.currentThread().getName()+"消费了:"+ consumeNum+"个; "+
				   "当前存量："+breadBasket.size()+"; ");
		
		//通知其他此对象上的线程继续运行，释放对象锁
		this.notifyAll();
	}
	
}
