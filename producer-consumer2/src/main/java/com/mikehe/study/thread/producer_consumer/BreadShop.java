package com.mikehe.study.thread.producer_consumer;

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

import org.apache.log4j.Logger;

public class BreadShop {
	
	private static Logger log = Logger.getLogger(BreadShop.class);
	
	//锁对象
	private ReentrantLock lock = new ReentrantLock(true);
	//用于监控篮子是否满了的condition对象
	private Condition fullCondition = lock.newCondition();
	//用于监控篮子是否空了的condition对象
	private Condition emptyCondition = lock.newCondition();
	
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

	public void produce(int produceNum) {
		
		lock.lock(); //获取锁
		
		try {
			while (breadBasket.size()+produceNum > MAX_NUM) {
				log.info("面包篮子最大存量："+MAX_NUM+"; "+
						 "当前存量："+breadBasket.size()+"; "+
						 Thread.currentThread().getName()+"要生产的数量:"+ produceNum+"; "+
						 "总数超过最大存量，暂时不能生产! 等待中...");
				
				//...
				fullCondition.await();
			}
			
			log.info("面包篮子最大存量："+MAX_NUM+"; "+
					 "当前存量："+breadBasket.size()+"; "+
					Thread.currentThread().getName()+" 正在生产面包"+produceNum+"个...");
			Thread.currentThread().sleep(15000);
			
			//
			for (int i=0; i<produceNum; i++) {
				this.breadBasket.add(new Bread());
			}
			
			log.info("面包篮子最大存量："+MAX_NUM+"; "+
					 Thread.currentThread().getName()+"生产了:"+ produceNum+"个; "+
					 "当前存量："+breadBasket.size()+"; ");
			
			//...
			emptyCondition.signal();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			lock.unlock(); //释放锁
		}
		
	}
	
	public void consume(int consumeNum) {
		
		lock.lock();
		
		try {
			while (consumeNum > breadBasket.size()) {
				log.info("面包篮子最大存量："+MAX_NUM+"; "+
						   "当前存量："+breadBasket.size()+"; "+
						   Thread.currentThread().getName()+"要消费的数量:"+ consumeNum+"; "+
						   "消费数超过当前存量，暂时不能消费! 等待中...");
				
				//...
				emptyCondition.await();
			}
			
			log.info("面包篮子最大存量："+MAX_NUM+"; "+
					 "当前存量："+breadBasket.size()+"; "+
					Thread.currentThread().getName()+" 正在消费面包"+consumeNum+"个...");
			Thread.currentThread().sleep(15000);
			
			for (int i=0; i<consumeNum; i++) {
				breadBasket.remove();
			}
			
			log.info("面包篮子最大存量："+MAX_NUM+"; "+
					Thread.currentThread().getName()+"消费了:"+ consumeNum+"个; "+
					   "当前存量："+breadBasket.size()+"; ");
			
			//...
			fullCondition.signal();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			lock.unlock(); //释放锁
		}
	}
	
}
