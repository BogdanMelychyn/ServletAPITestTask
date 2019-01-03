package ua.lviv.domain;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

public class Main {

	public static void main(String[] args) throws IOException {
		
		
	}

	static void fillFile(File file) throws IOException {
		RandomAccessFile raf = new RandomAccessFile(file, "rw");
		char[] c = new char[30];
		for (int y = 0; y < 10; y++) {
			for (int i = 0; i < c.length; i++) {
				c[i] = (char) ((Math.random() * 25) + 97);
			}
			String s = new String(c);
			s = "\n" + s;
			raf.seek(raf.length());
			raf.write(s.getBytes("UTF-8"));
			System.out.println();
		}
		raf.close();
	}
}
