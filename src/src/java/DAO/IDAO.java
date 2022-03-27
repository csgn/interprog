package DAO;

import Utils.PGConn;
import java.sql.Connection;
import java.util.List;

/**
 *
 * @author csgn
 * @param <T>
 */
public interface IDAO<T> {
	Connection conn = PGConn.connect().getConnection();

	T find(int id);	
	List<T> findAll();
	int create(T t);
	void update(T t);
	void delete(int id);
}
