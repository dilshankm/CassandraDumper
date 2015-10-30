package cassendradumper;

import java.util.List;

import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;

public class Executer {

	public static void main(String[] args) {

		final CassandraConnector client = new CassandraConnector();

		final String ipAddress = args.length > 0 ? args[0] : "localhost";
		final int port = args.length > 1 ? Integer.parseInt(args[1]) : 9242;
		System.out.println("Connecting to IP Address " + ipAddress + ":" + port + "...");
		Session session = client.connect("127.0.0.1", 9242);
		//session.
		CassedraUtil util = new CassedraUtil(session);
		util.loadCourses();
//		List<Row> dataRows = util.getRows("PWC_Keyspace", "Platform");
//		for (Row row : dataRows) {
//			System.out.println("value" + row.getString("value"));
//		}
		client.close();

	}

}
