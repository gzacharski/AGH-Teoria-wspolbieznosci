public class Consumer extends Thread {
	private Buffer _buf;
	private int timeDelay;
	
	public Consumer(Buffer bufor, String threadName, int timeDelay) {
		_buf=bufor;
		this.setName(threadName);
		this.timeDelay=timeDelay;
	}
	
	public void run() {
		for(int i=0; i<100; ++i) {
			try {
				Thread.sleep(timeDelay);
				_buf.get();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}

