package lab5.zad1;

public class Consumer extends Thread {
	private Buffer buffer;
	private int iterations;
	
	public Consumer(Buffer buffer, int iterations) {
		this.buffer=buffer;
		this.iterations=iterations;
	}
	
	public void run() {
		for(int i=0;i<iterations;i++) {
			
			int value=buffer.get();
			System.out.println("Thread " + Thread.currentThread().getName() + " consumes " +value);
			
		}
	}
}
