package textproc;

import java.util.*;

public class MultiWordCounter implements TextProcessor {
	private Map<String, Integer> words;
	
	public MultiWordCounter(String[] words)	{
		this.words = new TreeMap<>();
		
		for (String s : words)	{
			this.words.put(s, 0);
		}
	}
	
	public void process(String w)	{
		for (String key : words.keySet())	{
			if (key.equals(w))	{
				words.put(key, words.get(key) + 1);
			}
		}
	}
	
	public void report()	{
		for (String key : words.keySet())	{
			System.out.println(key + ": " + words.get(key));
		}
	}
	
}
