package cassendradumper.db;

import com.datastax.driver.core.Session;

public class Connection {

	private Session session;
	private CassandraConnector client;

	public Session connect(String host, int port, String keySpace) {
		try {
			client = new CassandraConnector();
			System.out.println("Connecting to IP Address " + host + ":" + port + "...");
			Session session = client.connect(host, port, keySpace);
			this.session = session;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return session;
	}

	public void closeConnection() {
		try {
			client.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
