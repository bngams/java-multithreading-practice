package exo5;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import exo1.Counter;

public class Exo5 {

	public static void main(String[] args) {
		
		OutputWriter outWriter ;
		try {
			// Writer object
			outWriter = new OutputWriter(new File("file-output.txt"));
			
			String[] files = {
		      "file1.txt",
		      "file2.txt",
		      "file3.txt",
		    };
			
			ExecutorService executorService = Executors.newCachedThreadPool();
			// launch a thread for each file 
			for (int i = 0; i < files.length; i++) {
				System.out.println("Launching new thread for file " + files[i] );
				executorService.execute(new ReadAndWrite(new File(files[i]), outWriter));
			}
			
			// no new tasks accepted
			executorService.shutdown();
			// await current threads ending (awaitTermination is like a while loop);
			executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.MINUTES);
			
			System.out.println("End");
			outWriter.close();
			
					
		} catch (FileNotFoundException | UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
