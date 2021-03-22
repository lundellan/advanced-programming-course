package textproc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

public class GeneralWordCounter implements TextProcessor {
	private Map<String, Integer> listan = new TreeMap<>();
	Set<String> stopwords = new HashSet<String>();
	
	public GeneralWordCounter(Set s)	{
		/* Iterator<String> itr = s.iterator();
		while (itr.hasNext())	{
			stopwords.add(itr.next());
		} */
		this.stopwords = s;
	}
	
	public List<Map.Entry<String, Integer>> getWordList() {
		return new ArrayList<Map.Entry<String, Integer>>(listan.entrySet());
	}
	
	public void process(String w)	{
		if (!stopwords.contains(w) && !listan.containsKey(w))	{
			listan.put(w, 1);
		} else if (!stopwords.contains(w) && listan.containsKey(w))	{
			listan.put(w, listan.get(w) + 1);
		}
	}
	
	public void report()	{
		/* for (String s : listan.keySet())	{
			if (listan.get(s) >= 200)	{
				System.out.println(s + ": " + listan.get(s));
			}
		} */
		
		Set<Map.Entry<String, Integer>> wordSet = listan.entrySet();
		List<Map.Entry<String, Integer>> wordList = new ArrayList<>(wordSet);
		wordList.sort(new WordCountComparator());
		
		for (int n = 0; n < 5; n++)	{
			System.out.println(wordList.get(n));
		}
	}
	
	
}