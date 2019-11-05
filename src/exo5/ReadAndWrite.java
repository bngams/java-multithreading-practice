package exo5;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class ReadAndWrite implements Runnable {
	
	OutputWriter writer;
	File file;
	
	public ReadAndWrite(File file, OutputWriter writer) {
		this.file = file;
		this.writer = writer;
	}

	@Override
	public void run() {
		System.out.println("init scanner for file " + this.file.getName());
		try{
			Scanner sc = new Scanner(this.file);
			int i = 1;
	        while (sc.hasNextLine()) {
	            String s = sc.nextLine();
	            if (i % 2 == 0) {
	            	System.out.println("writing : " + s);
	            	writer.writeLine(s);
	            }
	            i++;
	        }
	        sc.close();	
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

}
