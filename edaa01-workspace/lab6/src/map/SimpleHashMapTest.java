package map;

import java.util.Random;

public class SimpleHashMapTest {
	public static void main(String[] args) {
		SimpleHashMap<Integer, Integer> map = new SimpleHashMap<Integer, Integer>(10);
		Random rand = new Random();
		int test = 0;
		for (int i = 0; i < 15; i++) {
			test = rand.nextInt(30 + 1 + 30) - 10;
			map.put(test, test);
		}
		System.out.println(map.show());
	}
}
