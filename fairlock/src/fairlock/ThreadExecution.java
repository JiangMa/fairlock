package fairlock;

import java.util.concurrent.TimeUnit;

public class ThreadExecution {
	
	private static Lock lock = new FairLock();

	public static void main(String[] args) throws InterruptedException {
		Runnable r = new Worker(lock);
		Thread[] workers = new Thread[5];
		for (int i = 0; i < 5; i++) {
			workers[i] = new Thread(r);
			workers[i].start();
		}
		
		System.out.println("aquiring resource for 10 seconds: " + Thread.currentThread().getName());
		lock.lock();
		System.out.println("holding resource for 10 seconds: " + Thread.currentThread().getName());
		TimeUnit.SECONDS.sleep(10);
		System.out.println("waked up:  " + Thread.currentThread().getName());
		lock.unlock();
		

	}
}
