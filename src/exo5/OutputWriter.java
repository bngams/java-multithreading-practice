package exo5;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;

public class OutputWriter {
	
	private File f;
	private PrintWriter writer;
	
	public OutputWriter(File f) throws FileNotFoundException, UnsupportedEncodingException {
		this.f = f;
		this.writer = new PrintWriter(f, "UTF-8");
	}
	
	public synchronized void writeLine(String s) {
		this.writer.println(s);
	}
	
	public void close() {
		this.writer.close();
	}

}
