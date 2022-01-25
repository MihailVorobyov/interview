package ru.vorobev.interview.homework_03.pingpong;

public class Game {
	private boolean ping;
	
	public Game() {
		this.ping = true;
	}
	
	public boolean isPing() {
		return ping;
	}
	
	public void setPing(boolean ping) {
		this.ping = ping;
	}
	
	public synchronized void ping() {
		while (ping) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			System.out.println("ping");
			ping = false;
			notifyAll();
		}
	}
	
	public synchronized void pong() {
		while (!ping) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			System.out.println("pong");
			ping = true;
			notifyAll();
		}
	}
}
