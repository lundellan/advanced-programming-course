package testqueue;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Queue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import queue_singlelinkedlist.FifoQueue;

class TestAppendFifoQueue {
	private FifoQueue<Integer> myIntQueue1;
	private FifoQueue<Integer> myIntQueue2;

	@BeforeEach
	void setUp() throws Exception {
		myIntQueue1 = new FifoQueue<Integer>();
		myIntQueue2 = new FifoQueue<Integer>();
	}

	@AfterEach
	void tearDown() throws Exception {
		myIntQueue1 = null;
		myIntQueue2 = null;
	}

	@Test
	public final void testAppendTwoEmptyQueues() {
		myIntQueue1.append(myIntQueue2);
		assertTrue(myIntQueue1.isEmpty());
		assertTrue(myIntQueue2.isEmpty());
	}
	
	@Test
	public final void testAppendEmptyToNonEmptyQueue() {
		myIntQueue1.offer(1);
		myIntQueue1.offer(2);
		myIntQueue1.offer(3);
		myIntQueue1.append(myIntQueue2);
		
		assertTrue(myIntQueue1.size() == 3, "The first queue should have the size of three items");
		assertTrue(myIntQueue2.isEmpty(), "The second queue should be empty");
		
		for (int i = 1; i <= 3; i++) {
			assertTrue(myIntQueue1.poll() == i, "Incorrect order in queue");
		}
	}
	
	@Test
	public final void testAppendNonEmptyToEmptyQueue() {
		myIntQueue2.offer(1);
		myIntQueue2.offer(2);
		myIntQueue2.offer(3);
		myIntQueue1.append(myIntQueue2);
		
		assertTrue(myIntQueue1.size() == 3, "The first queue should have the size of three items");
		assertTrue(myIntQueue2.isEmpty(), "The second queue should be empty");
		
		for (int i = 1; i <= 3; i++) {
			assertTrue(myIntQueue1.poll() == i, "Incorrect order in queue");
		}
	}
	
	@Test
	public final void testAppendTwoNonEmptyQueues() {
		myIntQueue1.offer(1);
		myIntQueue1.offer(2);
		myIntQueue2.offer(3);
		myIntQueue1.append(myIntQueue2);
		
		assertTrue(myIntQueue1.size() == 3, "The first queue should have the size of three items");
		assertTrue(myIntQueue2.isEmpty(), "The second queue should be empty");
		
		for (int i = 1; i <= 3; i++) { 
			assertTrue(myIntQueue1.poll() == i, "Incorrect order in queue");
		}
	}
	
	@Test
	public final void testAppendQueueWithItself() {
		assertThrows( IllegalArgumentException.class, () -> myIntQueue1.append(myIntQueue1));
	}

}
