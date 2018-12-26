package ua.lviv.domain;


import java.io.IOException;

import ua.lviv.view.ReadFilter;

public class Main {

	public static void main(String[] args) throws IOException {
		// ReadFilter rf = new ReadFilter("test.txt");
		// System.out.println(rf.findLimitedLines("java",40));
		// System.out.println(rf.getTextFile());
	ReadFilter fs = new ReadFilter();
			//TextFile tf = new TextFile();
			//System.out.println(tf.getFile()+tf.getFile().getAbsolutePath());
		System.out.println(fs.findLines("", "d100", null, "true"));
	
//		RandomAccessFile raf = new RandomAccessFile(tf.getFile(), "rw");
//		char[] c = new char[30];
//		for (int y = 0; y < 10; y++) {
//			for (int i = 0; i < c.length; i++) {
//				c[i] = (char) ((Math.random() * 25) + 97);
//				}
//			String s = new String(c);
//			s="\n"+s;
//			raf.seek(raf.length());
//			raf.write(s.getBytes("UTF-8"));
//			System.out.println();
//		}
//		raf.close();

}

}
