package generate;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

public class Cenas {

	public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
		PrintWriter writer = new PrintWriter("cenas.pml", "UTF-8");
		writer.printf("Ola mundo! %d %n", 2);
		writer.printf("   Ho ho!! %s %n", "Dude");
		writer.printf("   Ho ho!! %s  %n", "Dude");
		writer.printf("   Ho ho!! %s %n", "Dude");
		writer.println("  pblabla  ");
		writer.println("wuhuuuuu ! !");
		writer.printf("   Ho ho!! %s", "Dude");
		writer.close();

	}

}
