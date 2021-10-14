package textproc;

import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Holgersson {

	public static final String[] REGIONS = { "blekinge", "bohuslän", "dalarna", "dalsland", "gotland", "gästrikland",
			"halland", "hälsingland", "härjedalen", "jämtland", "lappland", "medelpad", "närke", "skåne", "småland",
			"södermanland", "uppland", "värmland", "västerbotten", "västergötland", "västmanland", "ångermanland",
			"öland", "östergötland" };

	public static void main(String[] args) throws FileNotFoundException {
		long t0 = System.nanoTime();
		
//		List<TextProcessor> list = new ArrayList<>();
//		list.add(new SingleWordCounter("nils"));
//		list.add(new SingleWordCounter("norge"));
		
//		MultiWordCounter counter = new MultiWordCounter(REGIONS);
		
		Scanner scan = new Scanner(new File("undantagsord.txt"));
		Set<String> stopwords = new HashSet<>();
		
		while (scan.hasNext())	{
			stopwords.add(scan.next());
		}
		
		GeneralWordCounter counter = new GeneralWordCounter(stopwords);

		Scanner s = new Scanner(new File("nilsholg.txt"));
		s.findWithinHorizon("\uFEFF", 1);
		s.useDelimiter("(\\s|,|\\.|:|;|!|\\?|'|\\\")+"); // se handledning

		while (s.hasNext()) {
			String word = s.next().toLowerCase();

			counter.process(word);
			
//			for (TextProcessor p : list)	{
//				p.process(word);
//			}
		}

		s.close();
		
		counter.report();
		
		long t1 = System.nanoTime();

		System.out.println("tid: " + (t1 - t0) / 1000000.0 + " ms");

//		for (TextProcessor p : list)	{
//			p.report();
//		}
	}
}