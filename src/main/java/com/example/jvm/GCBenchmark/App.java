package com.example.jvm.GCBenchmark;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class App {
	
	public static class MemoryEater {
		private int[] buffer;
		public MemoryEater(int size) { buffer = new int[size]; }
		public static MemoryEater alloc(int sizeInMb) {
			return new MemoryEater(sizeInMb * (1024*1024/4));
		}
	}
	
	public static class Test {
		private int obj_size;
		private int num_threads;
		private int n_alloc;
		private Random generator = new Random();
		Test(int obj_size, int num_threads, int n_alloc) {
			this.obj_size = obj_size;
			this.num_threads = num_threads;
			this.n_alloc = n_alloc;
		}
		
		class Task extends Thread implements Runnable {
			public void run() { MemoryEater.alloc(obj_size); }
		}
		
		public void execTask() {
			boolean flag = false;
			if (obj_size <= 0) flag = true;
			final ExecutorService executorService = Executors.newFixedThreadPool(num_threads);
			Task task = new Task();
			long time = System.currentTimeMillis();
	    	for (int x = 0; x < n_alloc; x++) {
	    		if (flag) obj_size = generator.nextInt(11) + 5;
	    		executorService.execute(task);
	    	}
	    	System.out.println(System.currentTimeMillis() - time);
	    	executorService.shutdownNow();
		}
	}
		
    public static void main( String[] args ) {
    	// warmup
    	System.out.print("Warmup: ");
    	Test test = new Test(15, 1, 1000000);
    	test.execTask();
    	
    	// test 1
    	System.out.print("\nTest 1: ");
    	test = new Test(15, 1, 1000000);
    	test.execTask();
    	
    	// test 2
    	System.out.print("\nTest 2: ");
    	test = new Test(15, 10, 1000000);
    	test.execTask();
    	
    	// test 3
    	System.out.print("\nTest 3: ");
    	test = new Test(0, 1, 1000000);
    	test.execTask();
    	
    	// test 4
    	System.out.print("\nTest 4: ");
    	test = new Test(0, 10, 1000000);
    	test.execTask();
    	
    	//Runtime.getRuntime().totalMemory()
    }
}
