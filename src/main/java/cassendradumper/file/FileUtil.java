package cassendradumper.file;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileUtil {

	public static void simpleWrite(String content, String filePath) {
		Path fileP = Paths.get(filePath);
		try {
			Files.write(fileP, content.getBytes("utf-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {

		}

	}
}
