package dataMapper;

import java.util.HashMap;
import java.util.Map;

public class ReadWriteLock {

	private Map<Thread, Integer> readingThreads = new HashMap<>();
	
	private int writeAccesses = 0;
	private int writeRequests = 0;
	private Thread writingThread = null;
	
	public synchronized void lockRead() throws InterruptedException {
		Thread callingThread = Thread.currentThread();
		while (!canGrantReadAccess(callingThread)) {
			wait();
		}
		readingThreads.put(callingThread, getReadAccessCount(callingThread) + 1);
	}
	
	public synchronized void unlockRead() {
		Thread callingThread = Thread.currentThread();
		if (!isReader(callingThread)) {
			throw new IllegalMonitorStateException();
		}
		int accessCount = getReadAccessCount(callingThread);
		if (accessCount == 1) {
			readingThreads.remove(callingThread);
		}
		else {
			readingThreads.put(callingThread, accessCount - 1);
		}
		notifyAll();
	}
	
	public synchronized void lockWrite() throws InterruptedException {
		writeRequests++;
		Thread callingThread = Thread.currentThread();
		while (!canGrantWriteAccess(callingThread)) {
			wait();
		}
		writeRequests--;
		writeAccesses++;
		writingThread = callingThread;
	}
	
	public synchronized void unlockWrite() {
		if (!isWriter(Thread.currentThread())) {
			throw new IllegalMonitorStateException();
		}
		writeAccesses--;
		if (writeAccesses == 0) {
			writingThread = null;
		}
		notifyAll();
	}
	
	public synchronized void unlock() {
		Thread callingThread = Thread.currentThread();
		if (!(isReader(callingThread) || isWriter(callingThread))) {
			throw new IllegalMonitorStateException("thread does not hold any lock");
		}
		readingThreads.remove(callingThread);
		writingThread = null;
		notifyAll();
	}

	private boolean canGrantWriteAccess(Thread callingThread) {
		// TODO Auto-generated method stub
		if (isOnlyReader(callingThread)) return true;
		if (hasReaders()) return false;
		if (writingThread == null) return true;
		return isWriter(callingThread);
	}

	private boolean hasReaders() {
		// TODO Auto-generated method stub
		return readingThreads.size() > 0;
	}

	private boolean isOnlyReader(Thread callingThread) {
		// TODO Auto-generated method stub
		return readingThreads.size() == 1 && readingThreads.get(callingThread) != null;
	}

	private int getReadAccessCount(Thread callingThread) {
		// TODO Auto-generated method stub
		Integer accessCount = readingThreads.get(callingThread);
		if (accessCount == null) return 0;
		return accessCount;
	}

	private boolean canGrantReadAccess(Thread callingThread) {
		// TODO Auto-generated method stub
		if (isWriter(callingThread)) return true;
		if (hasWriter()) return false;
		if (isReader(callingThread)) return true;
		return !hasWriterRequests();
	}

	private boolean hasWriterRequests() {
		// TODO Auto-generated method stub
		return this.writeRequests > 0;
	}

	private boolean isReader(Thread callingThread) {
		// TODO Auto-generated method stub
		return readingThreads.get(callingThread) != null;
	}

	private boolean isWriter(Thread callingThread) {
		// TODO Auto-generated method stub
		return writingThread == callingThread;
	}

	private boolean hasWriter() {
		// TODO Auto-generated method stub
		return writingThread != null;
	}
	
}
