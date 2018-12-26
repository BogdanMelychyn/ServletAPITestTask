package ua.lviv.domain;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;

public class TextFile {
	private File file;

	public TextFile() {
		this.file = new File(System.getProperty("user.dir")+"\\webapps\\ServletAPITestTask\\test.txt");
	}

	public TextFile(String str)throws NullPointerException {
		this.file = new File(str);
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public String getDateCreation() throws IOException {

		BasicFileAttributes attr = Files.readAttributes(Paths.get(file.toURI()), BasicFileAttributes.class);
		return attr.creationTime().toString();

	}

	public String getFileName() {
		return file.getName();
	}

	public String getSize() {
		return String.valueOf(Math.round((double) file.length() / 1024)) + "KB";
	}

}
