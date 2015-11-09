package cassendradumper.db;

import java.util.LinkedHashMap;
import java.util.Map;

/*
 * This class used to generate query programatically
 */
public class QueryGenerator {

	public StringBuilder queryBuilder = new StringBuilder();
	StringBuilder query = new StringBuilder();

	public StringBuilder getQuery() {
		return getQueryBuilder();
	}

	public void setQuery(StringBuilder query) {
		this.setQueryBuilder(query);
	}

	public void insert(String table, String[] columns, Object[] values) {
		try {
			StringBuilder query = new StringBuilder("INSERT INTO " + table + " (");
			for (int i = 0; i < columns.length; i++) {
				if (i == (columns.length - 1)) {
					query.append(columns[i] + ")");
				} else {
					query.append(columns[i] + ",");
				}
			}
			query.append(" values (");

			for (int i = 0; i < values.length; i++) {
				if (i == (values.length - 1)) {
					query.append("'" + values[i] + "'" + ");");
				} else {
					query.append("'" + values[i] + "'" + ",");
				}
			}

			getQueryBuilder().append(query.toString() + "\n");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public String insertSet(String table, Map<String, String> data, String user) {
		try {
			
			for (Map.Entry<String, String> entry : data.entrySet()) {
	            query.append("SET " + table + " ['" + user + "']");
				// System.out.println("key"+entry.getKey());
				query.append("['" + entry.getKey() + "']=" + "'" + entry.getValue() + "';");
				query.append("\n");
				//break;
			}
			
			//System.out.println(queryBuilder);

			getQueryBuilder().append(query.toString());
			//break;
			//System.out.println("Linkedin");

		}

		catch (Exception e) {
			e.printStackTrace();
		}
		return getQueryBuilder().toString();

	}

	public StringBuilder getQueryBuilder() {
		return queryBuilder;
	}

	public void setQueryBuilder(StringBuilder queryBuilder) {
		this.queryBuilder = queryBuilder;
	}

}
