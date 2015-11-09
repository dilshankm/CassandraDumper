package cassendradumper.commands;

import com.datastax.driver.core.Session;

import cassendradumper.db.Connection;

public class CommandRunner {
	
	public void run(){
		try{
			Connection con=new Connection();
			Session session= con.connect("localhost", 9242, "PWC_Keyspace");
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		
		
	}

}
