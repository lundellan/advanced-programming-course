package textproc;
import java.util.*;
import java.util.Map.Entry;

public class WordCountComparator implements Comparator<Map.Entry<String,Integer>> {

	@Override
	public int compare(Entry<String, Integer> o1, Entry<String, Integer> o2) {
		// TODO Auto-generated method stub
		if (o1.getValue() == o2.getValue())	{
			return o1.getKey().compareTo(o2.getKey());
		} else if (o1.getValue() > o2.getValue())	{
			return -1;
		} else	{
			return 1;
		}
	}

}
