package dataMapper;

import models.Department;

public interface Mapper {

	public Object search(String id);
	public void insert(Object obj);
	public void update(Object obj);
	public void delete(Object obj);
}
