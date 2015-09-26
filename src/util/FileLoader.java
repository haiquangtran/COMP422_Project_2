package util;

import java.io.File;

public class FileLoader {
	private static String filePath = "src/assets/";

	public static File getFile(String fileName) {
		File file = new File(filePath + fileName);
		return file;
	}

	public static String getFilePath(String fileName) {
		return filePath + fileName;
	}

}
