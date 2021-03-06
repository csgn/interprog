package Model;

import java.util.Objects;

/**
 *
 * @author csgn
 */
public class Document {
	private int id;
	private String filePath;
	private String fileName;
	private String fileType;

	public Document() {}

	public Document(int id, String filePath, String fileName, String fileType) {
		this.id = id;
		this.filePath = filePath;
		this.fileName = fileName;
		this.fileType = fileType;
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	@Override
	public int hashCode() {
		int hash = 5;
		hash = 97 * hash + this.id;
		hash = 97 * hash + Objects.hashCode(this.filePath);
		hash = 97 * hash + Objects.hashCode(this.fileName);
		hash = 97 * hash + Objects.hashCode(this.fileType);
		return hash;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final Document other = (Document) obj;
		return true;
	}


}
