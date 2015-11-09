package cassendradumper.domains;

import java.io.Serializable;

public class Table implements Serializable {

	private static final long serialVersionUID = 1L;
	private String key;
	private String column1;
	private String value;
	private String tableName;

	public Table(String key, String column1, String value, String tableName) {
		this.key = key;
		this.column1 = column1;
		this.value = value;
		this.tableName = tableName;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getColumn1() {
		return column1;
	}

	public void setColumn1(String column1) {
		this.column1 = column1;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
