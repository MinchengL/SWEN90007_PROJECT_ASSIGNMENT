package dataMapper;

import models.*;

public class DepartmentLockingMapper implements IDepartmentMapper {

	private IDepartmentMapper mapper;
	private LockManager lockmanager;
	
	public DepartmentLockingMapper() {
		this.mapper = new DepartmentDataMapper();
		this.lockmanager = LockManager.getInstance();
	}
	
	public static Department search(String id, String name) {
		return IDepartmentMapper.search(id, name);
	}
	
	public static boolean insert(Department department) {
		try {
			LockManager.getInstance().acquireWriteLock(department);
			DepartmentDataMapper.insert(department);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return true;
	}

	public static boolean update(Department department) {
		try {
			LockManager.getInstance().acquireWriteLock(department);
			DepartmentDataMapper.update(department);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return true;
	}

	public static boolean delete(Department department) {
		try {
			LockManager.getInstance().acquireWriteLock(department);
			DepartmentDataMapper.delete(department);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return true;
	}
	
}
