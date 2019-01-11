package ua.lviv.domain;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.text.SimpleDateFormat;

public class TextFile {
	private File file;

	public TextFile() {
		
	}

	public TextFile(String path) {
		this.file = new File(path);
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public String getDateCreation() throws IOException {
		BasicFileAttributes attr = Files.readAttributes(Paths.get(file.toURI()), BasicFileAttributes.class);
		SimpleDateFormat df = new SimpleDateFormat("yyyy.MM.dd HH:mm a");
		return df.format(attr.creationTime().toMillis());

	}

	public String getFileName() {
		return file.getName();
	}

	public String getSize() {
		return String.valueOf(Math.round((double) file.length() / 1024)) + " KB";
	}

}
