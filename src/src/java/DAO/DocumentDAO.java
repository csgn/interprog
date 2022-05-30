package DAO;

import static DAO.IDAO.conn;
import Model.Document;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author csgn
 */
public class DocumentDAO implements IDAO<Document> {

	@Override
	public Document find(int id) {
		Document doc = new Document();
		PreparedStatement ps;
		ResultSet rs;

		try {
			ps = conn.prepareStatement("SELECT * FROM document WHERE id=?");
			ps.setInt(1, id);

			rs = ps.executeQuery();

			while (rs.next()) {
				doc.setId(rs.getInt("id"));
				doc.setFilePath(rs.getString("filepath"));
				doc.setFileName(rs.getString("filename"));
				doc.setFileType(rs.getString("filetype"));
			}
		} catch (SQLException e) {
			Logger.getLogger(DocumentDAO.class.getName()).log(Level.SEVERE, null, e);
		}

		return doc;
	}

	@Override
	public List<Document> findAll() {
		List<Document> docs = new ArrayList<>();
		Document doc = new Document();
		PreparedStatement ps;
		ResultSet rs;

		try {
			ps = conn.prepareStatement("SELECT * FROM document;");
			rs = ps.executeQuery();

			while (rs.next()) {
				doc.setId(rs.getInt("id"));
				doc.setFilePath(rs.getString("filepath"));
				doc.setFileName(rs.getString("filename"));
				doc.setFileType(rs.getString("filetype"));
			}
		} catch (SQLException e) {
			Logger.getLogger(DocumentDAO.class.getName()).log(Level.SEVERE, null, e);
		}

		return docs;
	}

	@Override
	public List<Document> findAll(int page, int pageSize) {
		List<Document> docs = new ArrayList<>();
		Document doc = new Document();
		PreparedStatement ps;
		ResultSet rs;

		int start = (page-1)*pageSize;

		try {
			ps = conn.prepareStatement("SELECT * FROM document limit ? offset ?");
			ps.setInt(1, page);
			ps.setInt(2, start);

			rs = ps.executeQuery();

			while (rs.next()) {
				doc.setId(rs.getInt("id"));
				doc.setFilePath(rs.getString("filepath"));
				doc.setFileName(rs.getString("filename"));
				doc.setFileType(rs.getString("filetype"));
			}
		} catch (SQLException e) {
			Logger.getLogger(DocumentDAO.class.getName()).log(Level.SEVERE, null, e);
		}

		return docs;
	}

	@Override
	public int create(Document t) {
		int id = -1;
		PreparedStatement ps;
		ResultSet rs;

		try {
			ps = conn.prepareStatement("INSERT INTO document (filePath, fileName, fileType) VALUES (?, ?, ?) RETURNING id");
			ps.setString(1, t.getFilePath());
			ps.setString(2, t.getFileName());
			ps.setString(3, t.getFileType());

			rs = ps.executeQuery();

			while (rs.next()) {
				id = rs.getInt("id");
			}
		} catch (SQLException e) {
			Logger.getLogger(DocumentDAO.class.getName()).log(Level.SEVERE, null, e);
		}

		return id;
	}

	@Override
	public void update(Document t) {
		PreparedStatement ps;

		try {
			ps = conn.prepareStatement("UPDATE role SET filepath=?,filename=?,filetype=? where id=?");
			ps.setString(1, t.getFilePath());
			ps.setString(2, t.getFileName());
			ps.setString(3, t.getFileType());
			ps.setInt(4, t.getId());
			ps.executeUpdate();
		} catch (SQLException e) {
			Logger.getLogger(DocumentDAO.class.getName()).log(Level.SEVERE, null, e);
		}
	}

	@Override
	public void delete(int id) {
		PreparedStatement ps;

		try {
			ps = conn.prepareStatement("DELETE FROM role WHERE id=?");
			ps.setInt(1, id);
			ps.executeUpdate();
		} catch (SQLException e) {
			Logger.getLogger(RoleDAO.class.getName()).log(Level.SEVERE, null, e);
		}
	}
	
}
