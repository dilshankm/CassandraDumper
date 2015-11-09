package cassendradumper.domains;

import java.io.Serializable;

public class PlatformSerilizer extends Table implements Serializable {
	
	public PlatformSerilizer(String key, String column1, String value,String tableName) {
		super(key, column1, value, tableName);
	}

	private static final long serialVersionUID = 1L;
			
}
