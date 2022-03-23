package DAO;

import java.util.List;

/**
 *
 * @author csgn
 * @param <T>
 */
public interface IDAO<T> {
	T find(int id);	
	List<T> findAll();
	int create(T t);
	void update(T t);
	void delete(int id);
}
