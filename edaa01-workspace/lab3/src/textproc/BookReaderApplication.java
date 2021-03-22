package textproc;

import java.awt.Container;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import javax.swing.JList;
import javax.swing.JScrollPane;

public class BookReaderApplication {

	public static void main(String[] args) throws FileNotFoundException {
		List<TextProcessor> listan = new ArrayList<TextProcessor>();
		
		Scanner scan = new Scanner(new File("undantagsord.txt"));
		Set<String> stopwords = new HashSet<String>();
		while (scan.hasNext())	{
			stopwords.add(scan.next());
		}
		scan.close();
		
		GeneralWordCounter count = new GeneralWordCounter(stopwords);
		listan.add(count);
		
		Scanner scan2 = new Scanner(new File("nilsholg.txt"));
		scan2.findWithinHorizon("\uFEFF", 1);
		scan2.useDelimiter("(\\s|,|\\.|:|;|!|\\?|'|\\\")+"); // se handledning
		while (scan2.hasNext()) {
			String word = scan2.next().toLowerCase();
			count.process(word);
		}
		scan2.close();
		
		new BookReaderController(count);
		
	}
}
