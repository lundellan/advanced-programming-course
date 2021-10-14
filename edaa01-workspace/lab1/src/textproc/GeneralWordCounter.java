package textproc;

import java.util.*;

public class GeneralWordCounter implements TextProcessor {
	public Set<String> words;
	public Map<String, Integer> counter;
	
	public GeneralWordCounter(Set<String> words)	{
		this.words = new HashSet<>(words);
		counter = new TreeMap<>();
	}
	
	public void process(String w) {
		if (!words.contains(w))	{
			if (counter.containsKey(w))	{
				counter.put(w, counter.get(w) + 1);
			} else	{
				counter.put(w, 1);
			}
		}
	}
	
	public void report()	{
		Set<Map.Entry<String, Integer>> wordSet = counter.entrySet();
		List<Map.Entry<String, Integer>> wordList = new ArrayList<>(wordSet);
		wordList.sort(new WordCountComparator());
	
		for (int i = 0; i < 5; i++)	{
			System.out.println(wordList.get(i));
		}
		
//		for (String key : counter.keySet())	{
//			if (counter.get(key) >= 200)	{
//				System.out.println(key + ": " + counter.get(key));
//			}
//		}
	}
	
}
