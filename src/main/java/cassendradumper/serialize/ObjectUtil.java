package cassendradumper.serialize;

import cassendradumper.domains.CourseAssignmentSerilizer;
import cassendradumper.domains.PlatformSerilizer;
import cassendradumper.domains.Table;

public class ObjectUtil {
	
	public Table objectRetuner(String tableName, Table o) {

		if (tableName.equalsIgnoreCase("Platform")) {
			return new PlatformSerilizer(o.getKey(), o.getColumn1(), o.getValue(),tableName);
		} else if (tableName.equalsIgnoreCase("CourseAssignment")) {
			return new CourseAssignmentSerilizer(o.getKey(), o.getColumn1(), o.getValue(),tableName);
		}
		return null;
	}

}
