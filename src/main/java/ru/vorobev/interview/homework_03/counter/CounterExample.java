package ru.vorobev.interview.homework_03.counter;

public class CounterExample {
	
	public static void main(String[] args) {
		MegaCounter counter = new MegaCounter();
		
		Thread t1 = new Thread(() -> {
			for (int i = 0; i < 1000; i++) {
				counter.increase();
			}
			System.out.println("t1 end. Value = " + counter.getValue());
		});
		
		Thread t2 = new Thread(() -> {
			for (int i = 0; i < 1000; i++) {
				counter.decrease();
			}
			System.out.println("t2 end. Value = " + counter.getValue());
		});

		Thread t3 = new Thread(() -> {
			for (int i = 0; i < 500; i++) {
				counter.increase();

			}
			System.out.println("t3 end. Value = " + counter.getValue());
		});

		Thread t4 = new Thread(() -> {
			for (int i = 0; i < 500; i++) {
				counter.decrease();
			}
			System.out.println("t4 end. Value = " + counter.getValue());
		});
		
		try {
			t1.join();
			t2.join();
			t3.join();
			t4.join();
			
			t1.start();
			t2.start();
			t3.start();
			t4.start();
			
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println("End of program. Counter's value is " + counter.getValue());
		
	}
}
