import java.util.LinkedList;

public abstract class Buffer {
	private int capacity;
	private LinkedList<Integer> linkedList=new LinkedList<>();
	
	public int getCapacity() {
		return capacity;
	}
	
	public void setCapacity(int capacity) {
		this.capacity=capacity;
	}
	
	public LinkedList<Integer> getLinkedList(){
		return linkedList;
	}
	
	public abstract void put(int i) throws InterruptedException;
	
	public abstract int get() throws InterruptedException;
	
	public String getBufor() {
		return linkedList.toString();
	}

	public void printInfo(String str, Boolean printAdditionalLine) {
		System.out.println(Thread.currentThread().getName()+", " +str+this.getBufor());
		if(printAdditionalLine) System.out.println("");
		
		PKmon.printWriter.println(Thread.currentThread().getName()+", " +str +this.getBufor());
		if(printAdditionalLine) PKmon.printWriter.println("");
	}
}
