package cassendradumper.serialize;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

import cassendradumper.domains.Table;

public class DeSerilizer {

	public List<Table> deSerialize(String folderPath, String table) {
		List<Table> tables = new ArrayList<Table>();
		Table t = null;
		try {
			File dir = new File(folderPath);
			File[] directoryListing = dir.listFiles();
			if (directoryListing != null) {
				for (File child : directoryListing) {
					FileInputStream fileOut = new FileInputStream(child);
					ObjectInputStream in = new ObjectInputStream(fileOut);
					t = (Table) in.readObject();
					tables.add(t);
					in.close();
					fileOut.close();
					// System.out.println("keys"+t.getKey());
				}
			} else {

			}
			//System.out.println("size-"+tables.size());
			//tables.clear();
		} catch (IOException i) {
			i.printStackTrace();

		} catch (ClassNotFoundException c) {
			c.printStackTrace();
		}
		return tables;

	}

}
