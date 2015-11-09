package cassendradumper;

import java.util.Iterator;
import java.util.List;

import com.datastax.driver.core.Session;

import cassendradumper.commands.CommandStructure;
import cassendradumper.db.CassandraConnector;
import cassendradumper.db.CassedraUtil;
import cassendradumper.domains.Table;
import cassendradumper.serialize.DeSerilizer;

public class Executer {

	public static void main(String[] args) {

		final CassandraConnector client = new CassandraConnector();

		final String ipAddress = args.length > 0 ? args[0] : "localhost";
		//final int port = args.length > 1 ? Integer.parseInt(args[1]) : 9242;
		System.out.println("Connecting to IP Address " + ipAddress + ":" + 9242 + "...");
		Session session = client.connect("127.0.0.1", 9242,"PWC_Keyspace");
		CassedraUtil util = new CassedraUtil(session);
		util.executeCommand(new CommandStructure().createStructure());
		client.close();
		System.out.println("Closed");
		final CassandraConnector local = new CassandraConnector();
		
		DeSerilizer de=new DeSerilizer();
		System.out.println("Desrilization has strted");
		List<Table> tables=de.deSerialize("/home/dilshankm/dumps/objects","CourseAssignment");
		//System.out.println("HH"+tables.size());
		Session sessionLocal = client.connect("127.0.0.1", 9042,"PWC_Keyspace");
		
		CassedraUtil utilLocal = new CassedraUtil(sessionLocal);
		
		for (Iterator iterator = tables.iterator(); iterator.hasNext();) {
			//System.out.println("hello");
			Table table = (Table) iterator.next();
			utilLocal.insertData(table,"PWC_Keyspace","CourseAssignment");	
		}
		System.out.println("End");
		
	
		
		
		//util.loadCourses();
		//FileUtil.simpleWrite(util.loadCourses(),"/home/dilshankm/dumps/newversion.txt");
		//client.close();
//		String[] columns={"id","age","name"};
//		Object[] objArr={2,12,"saman"};
//		QueryGenerator query=new QueryGenerator();
//		for(int i=0;i<4;i++){
//			query.insert("Hello"+i, columns,objArr);
//		}
//		FileUtil.simpleWrite((query.queryBuilder).toString(),"/home/dilshankm/dumps/text.txt");
		

	}

}
