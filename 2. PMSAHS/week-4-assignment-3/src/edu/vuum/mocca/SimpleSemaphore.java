package edu.vuum.mocca;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @class SimpleSemaphore
 * 
 * @brief This class provides a simple counting semaphore implementation using
 *        Java a ReentrantLock and a ConditionObject. It must implement both
 *        "Fair" and "NonFair" semaphore semantics, just liked Java Semaphores.
 */
public class SimpleSemaphore {

	/**
	 * Constructor initialize the data members.
	 */
	public SimpleSemaphore(int permits, boolean fair) {
		// TODO - you fill in here
		availablePermits = permits;
		lock = new ReentrantLock(fair);
		isZero = lock.newCondition();
	}

	/**
	 * Acquire one permit from the semaphore in a manner that can be
	 * interrupted.
	 */
	public void acquire() throws InterruptedException {
		// TODO - you fill in here
		lock.lockInterruptibly();
		while (availablePermits == 0) {
			isZero.await();
		}
		availablePermits--;
		lock.unlock();
	}

	/**
	 * Acquire one permit from the semaphore in a manner that cannot be
	 * interrupted.
	 */
	public void acquireUninterruptibly() {
		// TODO - you fill in here
		lock.lock();
		while (availablePermits == 0) {
			isZero.awaitUninterruptibly();
		}
		availablePermits--;
		lock.unlock();
	}

	/**
	 * Return one permit to the semaphore.
	 */
	void release() {
		// TODO - you fill in here
		lock.lock();
		try {
			availablePermits++;
			isZero.signal();
		} finally {
			lock.unlock();
		}
	}

	/**
	 * Return the number of permits available.
	 */
	public int availablePermits() {
		// TODO - you fill in here
		return availablePermits; // You will change this value.
	}

	/**
	 * Define a ReentrantLock to protect the critical section.
	 */
	// TODO - you fill in here
	private ReentrantLock lock;

	/**
	 * Define a ConditionObject to wait while the number of permits is 0.
	 */
	// TODO - you fill in here
	private Condition isZero;

	/**
	 * Define a count of the number of available permits.
	 */
	// TODO - you fill in here
	private int availablePermits;
}
