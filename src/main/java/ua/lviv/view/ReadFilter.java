package ua.lviv.view;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import javax.json.*;

import ua.lviv.domain.TextFile;

public class ReadFilter {

	private TextFile textFile;
	private BufferedReader bReader;

	public ReadFilter() throws FileNotFoundException, IOException {
		this.textFile = new TextFile();
		bReader = new BufferedReader(new FileReader(textFile.getFile()));
	}

	public ReadFilter(String path) throws NullPointerException, IOException, FileNotFoundException {
		this.textFile = new TextFile(path);
		bReader = new BufferedReader(new FileReader(textFile.getFile()));
	}

	public TextFile getTextFile() {
		return textFile;
	}

	public void setTextFile(TextFile tFile) {
		this.textFile = tFile;
	}

	/**
	 * @param str
	 *            - string which represents text to search in file
	 * @param lineLength
	 *            - max string length
	 * @param limit
	 *            - number of chars in text
	 * @param metaInf
	 *            - boolean which if set to true will expose file meta data
	 * @return return all strings which equals to ‘str’ or containing it, with
	 *         length of string and max number of chars in text.
	 */
	public JsonObject findLines(String str, String lineLength, String limit, String metaInf)
			throws IOException, JsonException {
		if (str == "")
			return findAllLines();
		int len = 0;
		int lim = 0;
		String line;
		int size = 0;
		JsonObjectBuilder jsonObj = Json.createObjectBuilder();
		JsonArrayBuilder jsonArraj = Json.createArrayBuilder();
		try {
			lim = Integer.parseInt(limit);
		} catch (NumberFormatException e) {
			lim = 10000;
		}
		try {
			len = Integer.parseInt(lineLength);
		} catch (NumberFormatException e) {
			while ((line = bReader.readLine()) != null && size < lim) {
				if (line.contains(str) && (size += line.length()) <= lim) {
					jsonArraj.add(line);
				}
			}
		}
		while ((line = bReader.readLine()) != null && size < lim) {
			if (line.contains(str) && line.length() <= len && (size += line.length()) <= lim) {
				jsonArraj.add(line);
			}
		}
		jsonObj.add("text", jsonArraj.build());
		if (Boolean.parseBoolean(metaInf)) {
			JsonObjectBuilder jsonObjMeta = Json.createObjectBuilder();
			jsonObjMeta.add("fileName", textFile.getFileName());
			jsonObjMeta.add("fileSize", textFile.getSize());
			jsonObjMeta.add("fileCreationDate", textFile.getDateCreation());
			jsonObj.add("metaData", jsonObjMeta.build());
		}
		bReader.close();
		return jsonObj.build();
	}

	/**
	 * @return return JsonObject which contains all lines from file
	 */
	public JsonObject findAllLines() throws IOException, JsonException {
		JsonObjectBuilder jsonObj = Json.createObjectBuilder();
		JsonArrayBuilder jsonArraj = Json.createArrayBuilder();
		String line;
		while ((line = bReader.readLine()) != null) {
			jsonArraj.add(line);
		}
		jsonObj.add("text", jsonArraj.build());
		bReader.close();
		return jsonObj.build();
	}
}
