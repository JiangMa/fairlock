package fairlock;

import java.util.concurrent.TimeUnit;

public class Worker implements Runnable {

	private Lock lock;

	public Worker(Lock lock) {
		this.lock = lock;
	}

	@Override
	public void run() {
		try {
			System.out.println("sleep 3 seconds "
					+ Thread.currentThread().getName());
			TimeUnit.SECONDS.sleep(3);
			output();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	private void output() throws InterruptedException {
		lock.lock();
		System.out.println("aquired: " + Thread.currentThread().getName());
		TimeUnit.SECONDS.sleep(3);
		lock.unlock();

	}
}
