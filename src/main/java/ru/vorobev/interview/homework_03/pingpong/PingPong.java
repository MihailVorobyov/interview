package ru.vorobev.interview.homework_03.pingpong;

public class PingPong {
	
	public static void main(String[] args) {
		Game monitor = new Game();

		Thread pingThread = new Thread(() -> {
			for (int i = 0; i < 20; i++) {
				synchronized (monitor) {
					while (!monitor.isPing()) {
						try {
							monitor.wait();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					try {
						Thread.sleep(300);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println("ping");
					monitor.setPing(false);
					monitor.notify();
				}
			}
			
		});
		
		Thread pongThread = new Thread(() -> {
			for (int i = 0; i < 20; i++) {
				synchronized (monitor) {
					while (monitor.isPing()) {
						try {
							monitor.wait();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					try {
						Thread.sleep(300);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println("pong");
					monitor.setPing(true);
					monitor.notify();
				}
			}
		});
		
		try {
			pingThread.join();
			pongThread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		pingThread.start();
		pongThread.start();
		
	}
}
