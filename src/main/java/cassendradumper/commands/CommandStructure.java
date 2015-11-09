package cassendradumper.commands;
import java.util.Arrays;

public class CommandStructure {
	
	public Command createStructure(){
		Command c1=new Command();
		c1.setTableName("Platform");
		c1.setKeys(Arrays.asList("CCNG"));
		Command c2=new Command();
		c2.setTableName("CourseAssignment");
		c1.setNextStep(Arrays.asList(c2));
//		Command c3=new Command();
//		c3.setTableName("");
//		Command c4=new Command();
//		c4.setTableName("");
//		c2.setNextStep(Arrays.asList(c3,c4));
		return c1;
	}

}
