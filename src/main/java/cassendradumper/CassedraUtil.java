package cassendradumper;

import java.util.List;

import org.apache.cassandra.cql3.restrictions.SingleColumnRestriction.EQ;

import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import com.datastax.driver.core.Statement;
import com.datastax.driver.core.querybuilder.QueryBuilder;

public class CassedraUtil {

	public CassedraUtil(Session session) {
		this.session = session;
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

	public void loadCourses() {
		//ResultSet results = session.execute("SELECT * FROM PWCKeyspace.AssignmentOptions");
//		ResultSet results = session.execute("SELECT * FROM system.schema_keyspaces " +
//			    "WHERE keyspace_name = 'PWC_Keyspace'");

		ResultSet results2 = session.execute("SELECT * FROM \"PWC_Keyspace\".\"Platform\" WHERE key='CCNG'");
		for(Row row:results2){
			//System.out.println(row.getColumnDefinitions());
//			System.out.println("ee-"+row.getString("key"));
//			System.out.println("dd-"+row.getString("column1"));
			loadAssignments(row.getString("column1"));
		}
	}
	
	public void loadAssignments(String courrseId){
		//System.out.println(courrseId);
		//7fb93dbe-981d-497d-8146-c3fed4a88845
		ResultSet results2 = session.execute("SELECT * FROM \"PWC_Keyspace\".\"CourseAssignment\" WHERE key="+"'"+courrseId+"'");
//		ResultSet results2 = session.execute("SELECT * FROM \"PWC_Keyspace\".\"CourseAssignment\" WHERE key="+courrseId+"");
		for(Row row:results2){
			//System.out.println(row.getColumnDefinitions());
			System.out.println("ee-"+row.getString("key"));
			System.out.println("dd-"+row.getString("column1"));
			System.out.println("dd-"+row.getString("value"));
		}
	}

}
