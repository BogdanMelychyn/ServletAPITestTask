package ua.lviv.model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.attribute.BasicFileAttributes;
import java.text.SimpleDateFormat;
import org.json.JSONArray;
import org.json.JSONObject;

public class ReadFilter {

	private File textFile;
	private JSONObject jsonObj;
	private JSONArray jsonArraj;

	private ReadFilter() {
		
	}
		
	private static class ReadFilterHolder{
		private static final ReadFilter instance = new ReadFilter();
	}
	
	public static ReadFilter getReadFilter() {
		return ReadFilterHolder.instance;
	}
	
	public void setFile(File file) {
		this.textFile = file;
	}
	/**
	 * Return file creation date
	 * @throws IOException
	 */
	public String getDateCreation() throws IOException {
		BasicFileAttributes attr = Files.readAttributes(textFile.toPath(), BasicFileAttributes.class);
		SimpleDateFormat df = new SimpleDateFormat("yyyy.MM.dd HH:mm a");
		return df.format(attr.creationTime().toMillis());

	}
	/**
	 * Return the file size in KB or 0 if the file doesn't exist.
	 */
	public String getSize() {
		return String.valueOf(Math.round((double) textFile.length() / 1024)) + " KB";
	}
	/**
	 * Return JsonObject
	 * 
	 * @param str - string which represents text to search in file. If the <strong>str</strong> 
	 * is blank or missing, method return all lines from file
	 * @param limit - max number of chars in text
	 * @param lineLength - max string length. If the parameter is blank or null, it ignore
	 * @param meta - if true, add metadata of file to JsonObject
	 * @return return all strings which equals to <strong>str</strong> or containing it, with
	 *         length of string and max number of chars in text.
	 * @throws IOException
	 */
	public JSONObject findLines(String str, int limit, String lineLength, String meta) throws IOException {
		jsonObj = new JSONObject();
		jsonArraj = new JSONArray();
		if (str == null || str.isEmpty()) return findAllLines();
		BufferedReader bReader = new BufferedReader(new FileReader(textFile));
		int length = 0;
		String line = bReader.readLine();
		boolean useLineLength = true;
		try {
			length = Integer.parseInt(lineLength);
		} catch (NumberFormatException e) {
			useLineLength = false;
		}
		if (useLineLength) {
			while (line != null && limit >= 0) {
				if (line.contains(str) && line.length() <= length && (limit -= line.length()) >= 0) {
					jsonArraj.put(line);
				}
				line = bReader.readLine();
			}
		}
		while (line != null && limit >= 0) {
			if (line.contains(str) && (limit -= line.length()) >= 0) {
				jsonArraj.put(line);
			}
			line = bReader.readLine();
		}
		jsonObj.put("text", jsonArraj);
		if (Boolean.parseBoolean(meta)) {

			jsonObj.put("metaData", getMetaData());
		}
		bReader.close();
		return jsonObj;
	}

	/**
	 * @return return JsonObject which contains all lines from file
	 * @throws IOException
	 */
	public JSONObject findAllLines() throws IOException {
		jsonObj = new JSONObject();
		jsonArraj = new JSONArray();
		String line;
		BufferedReader bReader = new BufferedReader(new FileReader(textFile));
		while ((line = bReader.readLine()) != null) {
			jsonArraj.put(line);
		}
		jsonObj.put("text", jsonArraj);
		bReader.close();
		return jsonObj;
	}

	/**
	 * @return return new JsonObject which contains file metadata
	 * @throws IOException
	 */
	public JSONObject getMetaData() throws IOException {
		JSONObject jsonObjMeta = new JSONObject();
		jsonObjMeta.put("fileName", textFile.getName());
		jsonObjMeta.put("fileSize", getSize());
		jsonObjMeta.put("fileCreationDate", getDateCreation());
		return jsonObjMeta;

	}
}
