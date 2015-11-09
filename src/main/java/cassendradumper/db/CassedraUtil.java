package cassendradumper.db;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import com.datastax.driver.core.Statement;
import com.datastax.driver.core.querybuilder.QueryBuilder;

import cassendradumper.commands.Command;
import cassendradumper.domains.PlatformSerilizer;
import cassendradumper.domains.Table;
import cassendradumper.serialize.Serializer;

public class CassedraUtil {

	public CassedraUtil(Session session) {
		this.session = session;
	}

	public StringBuilder queryBuilder = new StringBuilder();

	public StringBuilder getQueryBuilder() {
		return queryBuilder;
	}

	public void setQueryBuilder(StringBuilder queryBuilder) {
		this.queryBuilder = queryBuilder;
	}

	private Session session;

	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}

	public List<Row> getRows(String keyspace, String table) {
		Statement statement = QueryBuilder.select().all().from(keyspace, table);
		return getSession().execute(statement).all();
	}

	public String loadCourses() {
		// ResultSet results = session.execute("SELECT * FROM
		// PWCKeyspace.AssignmentOptions");
		// ResultSet results = session.execute("SELECT * FROM
		// system.schema_keyspaces " +
		// "WHERE keyspace_name = 'PWC_Keyspace'");

		ResultSet results2 = session.execute("SELECT * FROM \"Platform\" WHERE key='CCNG'");//
		for (Row row : results2) {
			// System.out.println(row.getc);
			// System.out.println("ee-"+row.getString("key"));
			// System.out.println("dd-"+row.getString("column1"));
			// System.out.println(loadAssignments(row.getString("column1")).toString());
			queryBuilder.append(loadAssignments(row.getString("column1")).toString());
			// break;
		}
		return queryBuilder.toString();
		// System.out.println(queryBuilder);
		// return "Finished";
	}

	public StringBuilder loadAssignments(String courrseId) {
		// System.out.println(courrseId);
		// 7fb93dbe-981d-497d-8146-c3fed4a88845
		Map<String, String> columnMap = new LinkedHashMap<String, String>();
		QueryGenerator query = new QueryGenerator();
		ResultSet results2 = session.execute("SELECT * FROM \"CourseAssignment\" WHERE key=" + "'" + courrseId + "'");
		// ResultSet results2 = session.execute("SELECT * FROM
		// \"PWC_Keyspace\".\"CourseAssignment\" WHERE key="+courrseId+"");
		for (Row row : results2) {
			// Object[]
			// valueArray={row.getString("key"),row.getString("column1"),row.getString("value")};
			// String[] columnaArray={"key","column1","value"};
			// String json=(row.getString("value")).replaceAll("\"", "/\"");
			// System.out.println("Json"+json);
			Table t = new Table(row.getString("key"), row.getString("column1"),
					row.getString("value").replace("'", "''"), "CourseAssignment");
			// Map<String,String> dataMap=new LinkedHashMap<>();
			// dataMap.put("key", row.getString("key"));
			// dataMap.put("column1", row.getString("column1"));
			// dataMap.put("value", row.getString("value"));
			Serializer ser = new Serializer();
			ser.serilize(t, String.valueOf(t.hashCode()), "CourseAssignment");
			//

			// query.insert("CourseAssignment",columnaArray ,valueArray);
			// query.insertSet("CourseAssignment", dataMap, "default");

			// break;
			// queryBuilder.append(query.insertSet("CourseAssignment", dataMap,
			// "default"));

			// break;
			// System.out.println(row.);
			// System.out.println("ee-"+row.getString("key"));
			// System.out.println("dd-"+row.getString("column1"));
			// System.out.println("dd-"+row.getString("value"));
		}

		// System.out.println("File has been finished");
		return query.getQueryBuilder();
	}

	public void insertData(Table t, String keySapace, String tableName) {
		try {
			// String k="https://ws.app.writer.pearsonhighered.com/";
			// System.out.println("hello");
			// String s = "I'm a human";
			// System.out.println("INSERT INTO \""+ tableName +"\"
			// (key,column1,value) values ('"
			// + t.getKey() + "','" + t.getColumn1() + "','" + t.getValue() +
			// "')");
			session.execute("INSERT INTO \"" + t.getTableName() + "\" (key,column1,value) values ('" + t.getKey() + "','"
					+ t.getColumn1() + "','" + t.getValue() + "')");
			// System.out.println("hh");

		} catch (Exception e) {
			e.printStackTrace();
		}

		// System.out.println(t.getColumn1());
		// System.out.println("INSERT INTO " + keySapace + "." +
		// t.getTableName() + " values ('" + t.getKey() + "','"
		// + t.getColumn1() + "','" + t.getValue() + "')");
		// session.execute("INSERT INTO " + keySapace + "." + t.getTableName() +
		// " values ('" + t.getKey() + "','"
		// + t.getColumn1() + "','" + t.getValue() + "')");
	}
	//
	// public void doCommand(Command c) {
	//
	// List<Command> nextSt = c.getNextStep();
	// List<String> keyGlo = c.getKeys();
	//
	// if (c.getNextStep().isEmpty() || c.getNextStep().equals(null) ||
	// c.getNextStep().size() == 0) {
	// ResultSet results = session.execute(
	// "SELECT * FROM " + "\"" + c.getTableName() + "\"" + " WHERE key='" +
	// c.getKeys().get(0) + "'");
	// for (Row row : results) {
	// Table t = new Table(row.getString("key"), row.getString("column1"),
	// row.getString("value").replace("'", "''"), c.getTableName());
	// Serializer ser = new Serializer();
	// ser.serilize(t, String.valueOf(t.hashCode()), c.getTableName());
	// }
	// } else {
	// for (Iterator iterator = nextSt.iterator(); iterator.hasNext();) {
	// Command command = (Command) iterator.next();
	//
	// for (Iterator iterator2 = keyGlo.iterator(); iterator2.hasNext();) {
	// String key = (String) iterator2.next();
	//
	// ResultSet results = session
	// .execute("SELECT * FROM " + "\"" + c.getTableName() + "\"" + " WHERE
	// key='" + key + "'");
	// Command c1 = new Command();
	// List<String> keys = new ArrayList<>();
	// List<String> column1 = new ArrayList<>();
	// for (Row row : results) {
	// column1.add(row.getString("column1"));
	// }
	// c1.setKeys(column1);
	// c1.setTableName(command.getTableName());
	// // c1.setNextStep(com);
	// doCommand(c1);
	// }
	// }
	// }
	// }

	public void executeCommand(Command c) {
		try {
			if (c.getKeys().size() == 1) {
				List<Command> nextSt = c.getNextStep();
				ResultSet results = session.execute(
						"SELECT * FROM " + "\"" + c.getTableName() + "\"" + " WHERE key='" + c.getKeys().get(0) + "'");
				List<String> column1 = new ArrayList<>();
				for (Row row : results) {
					column1.add(row.getString("column1"));
					Table t = new Table(row.getString("key"), row.getString("column1"),
							row.getString("value").replace("'", "''"), c.getTableName());
					Serializer ser = new Serializer();
					ser.serilize(t, String.valueOf(t.hashCode()), c.getTableName());
				}

				if (nextSt != null && !nextSt.isEmpty()) {
					for (Iterator iterator = nextSt.iterator(); iterator.hasNext();) {
						Command command = (Command) iterator.next();
						command.setKeys(column1);
						if (!c.getNextStep().isEmpty()) {
							executeCommand(command);
						}
					}
				}
			} else {
				List<Command> nextSt = c.getNextStep();
				List<String> keyGlo = c.getKeys();
				for (Iterator iterator = keyGlo.iterator(); iterator.hasNext();) {
					String key = (String) iterator.next();
					ResultSet results = session
							.execute("SELECT * FROM " + "\"" + c.getTableName() + "\"" + " WHERE key='" + key + "'");
					List<String> column1 = new ArrayList<>();
					for (Row row : results) {
						column1.add(row.getString("column1"));
						Table t = new Table(row.getString("key"), row.getString("column1"),
								row.getString("value").replace("'", "''"), c.getTableName());
						Serializer ser = new Serializer();
						ser.serilize(t, String.valueOf(t.hashCode()), c.getTableName());
					}

					if (nextSt != null && !nextSt.isEmpty()) {
						for (Iterator iterator1 = nextSt.iterator(); iterator1.hasNext();) {
							Command command = (Command) iterator1.next();
							command.setKeys(column1);
							if (command.getNextStep() != null && !command.getNextStep().isEmpty()) {
								executeCommand(command);
							}
						}
					}

				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
