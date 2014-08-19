package edu.usf.cutr.obascs.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class FileWriter {

    public static void writeToFile(String text, String path) throws FileNotFoundException{
	File file = new File(path);
	PrintWriter printWriter = new PrintWriter(file);
	printWriter.println(text);
	printWriter.close();
    }
}
