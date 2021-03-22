package textproc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MultiWordCounter implements TextProcessor {
	private HashMap<String, Integer> listan = new HashMap<>();
	
	public MultiWordCounter(String[] ord)	{
		for (String s : ord)	{
			listan.put(s, 0);
		}
	}
	
	public void process(String w) {
		if (listan.containsKey(w)) {
			listan.put(w, listan.get(w) + 1);
		}
	}

	public void report() {
		for (String s : listan.keySet())	{
			System.out.println(s + ": " + listan.get(s));
		}
	}
}
