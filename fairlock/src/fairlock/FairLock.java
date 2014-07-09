package fairlock;

import java.util.ArrayList;
import java.util.List;

public class FairLock implements Lock {
	private boolean isLocked = false;
	private Object monitor;
	private List<Object> lockQueue = new ArrayList<Object>();

	@Override
	public void lock() {
		synchronized (this) {
			monitor = new Object();
			System.out.println("new monitor: " + monitor.toString() + " "
					+ Thread.currentThread().getName());
			lockQueue.add(monitor);
		}
		synchronized (monitor) {
			try {
				while (isLocked) {
					monitor.wait();
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

	
			isLocked = true;
			lockQueue.remove(0);
		}

	}

	@Override
	public void unlock() {
		synchronized (this) {
			if (lockQueue.size() > 0) {
				monitor = lockQueue.get(0);
				isLocked = false;
				synchronized (monitor) {
					monitor.notify();
				}
			} else {
				isLocked = false;
			}
		}

	}

}
