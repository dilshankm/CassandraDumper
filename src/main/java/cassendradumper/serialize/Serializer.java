package cassendradumper.serialize;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import cassendradumper.domains.Table;

public class Serializer {

	public void serilize(Table o, String fileName, String TableName) {
		try {
			FileOutputStream fileOut = new FileOutputStream("/home/dilshankm/dumps/objects/" + fileName);
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(new ObjectUtil().objectRetuner(TableName, o));
			out.close();
			fileOut.close();
		} catch (IOException i) {
			i.printStackTrace();
		}
	}

//	public Table objectRetuner(String tableName, Table o) {
//
//		if (tableName.equalsIgnoreCase("Platform")) {
//			return new PlatformSerilizer(o.getKey(), o.getColumn1(), o.getValue(),tableName);
//		} else if (tableName.equalsIgnoreCase("CourseAssignment")) {
//			return new CourseAssignmentSerilizer(o.getKey(), o.getColumn1(), o.getValue(),tableName);
//		}
//		return null;
//	}

}
