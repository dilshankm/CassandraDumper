package cassendradumper.domains;

import java.io.Serializable;

public class CourseAssignmentSerilizer extends Table implements Serializable {

	private static final long serialVersionUID = 1L;

	public CourseAssignmentSerilizer(String key, String column1, String value,String tableName) {
		super(key, column1, value,tableName);
	}

}
