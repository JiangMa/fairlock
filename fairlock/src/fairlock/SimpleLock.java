package fairlock;

public class SimpleLock implements Lock {
	private boolean isLocked = false;
	private final Object monitor = new Object();

	public SimpleLock() {
		super();
	}

	@Override
	public void lock() {
		synchronized (monitor) {
			try {
				while (isLocked) {
					monitor.wait();
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			isLocked = true;
		}

	}

	@Override
	public void unlock() {
		synchronized (monitor) {
			isLocked = false;
			monitor.notify();

		}
	}

}
