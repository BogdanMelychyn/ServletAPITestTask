package ua.lviv.view;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import org.json.JSONArray;
import org.json.JSONObject;
import ua.lviv.domain.TextFile;

public class ReadFilter {

	private TextFile textFile;
	private BufferedReader bReader;
	private JSONObject jsonObj = new JSONObject();
	private JSONArray jsonArraj = new JSONArray();

	public ReadFilter(TextFile textFile) throws NullPointerException, IOException, FileNotFoundException {
		this.textFile = textFile;
		this.bReader = new BufferedReader(new FileReader(textFile.getFile()));
	}

	/**
	 * Return JsonOblect
	 * 
	 * @param str
	 *            - string which represents text to search in file
	 * @param limit
	 *            - number of chars in text
	 * @param lineLength
	 *            - max string length
	 * @return return all strings which equals to ‘str’ or containing it, with
	 *         length of string and max number of chars in text.
	 * @throws IOException
	 */
	public JSONObject findLines(String str, int limit, String lineLength, String meta) throws IOException {
		if (str == null || str.isEmpty())
			return findAllLines();
		
		int length = 0;
		String line = bReader.readLine();
		boolean useLineLength = true;
		if (Boolean.parseBoolean(meta)) {
			jsonObj.put("metaData", getMetaData());
		}
		try {
			length = Integer.parseInt(lineLength);
		} catch (NumberFormatException e) {
			useLineLength = false;
		}
		if (useLineLength) {
			while (line != null && limit > 0) {
				if (line.contains(str) && line.length() <= length && (limit -= line.length()) >= 0) {
					jsonArraj.put(line);
				}
				line = bReader.readLine();
			}
		}
		while (line != null && limit > 0) {
			if (line.contains(str) && (limit -= line.length()) >= 0) {
				jsonArraj.put(line);
			}
			line = bReader.readLine();
		}
		jsonObj.put("text", jsonArraj);
		bReader.close();
		return jsonObj;
	}

	/**
	 * @return return JsonObject which contains all lines from file
	 * @throws IOException
	 */
	public JSONObject findAllLines() throws IOException {
		String line;
		while ((line = bReader.readLine()) != null) {
			jsonArraj.put(line);
		}
		jsonObj.put("text", jsonArraj);
		bReader.close();
		return jsonObj;
	}

	/**
	 * @return return new JsonObject which contains file meta data
	 * @throws IOException
	 */

	public JSONObject getMetaData() throws IOException {
		JSONObject jsonObjMeta = new JSONObject();
		jsonObjMeta.put("fileName", textFile.getFileName());
		jsonObjMeta.put("fileSize", textFile.getSize());
		jsonObjMeta.put("fileCreationDate", textFile.getDateCreation());
		return jsonObjMeta;

	}
}
