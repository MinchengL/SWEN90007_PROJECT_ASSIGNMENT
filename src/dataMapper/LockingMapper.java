package dataMapper;

import models.*;

class LockingMapper implements Mapper {

	private Mapper impl;
	private LockManager lm;
	
	public LockingMapper() {
		this.impl = impl;
		this.lm = LockManager.getInstance();
	}
	
	public Object search(String id) {
		return impl.search(id);
	}
	
	public void insert(Object obj) {
		impl.insert(obj);
	}

	public void update(Object obj) {
		impl.update(obj);
	}

	public void delete(Object obj) {
		impl.delete(obj);
	}
	
}
