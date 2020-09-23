package lab4;

import java.io.PrintWriter;

public abstract class SharedResource {
	private String resourceText="sample";
	
	public void setResource(String character) throws Exception {
		beforeSettingResource();
	
		Main.printWriter.println("Writer " + Thread.currentThread().getName()+" is writing. It adds :"+String.valueOf(character));
		this.resourceText=this.resourceText.concat(character);
		
		afterSettingResource();
	}
	
	protected abstract void afterSettingResource() throws Exception;
	protected abstract void beforeSettingResource() throws Exception;
	
	public void getResource() throws Exception{
		beforeGettingResource();
		
		Main.printWriter.println("Reader " +Thread.currentThread().getName()+" is reading: " + resourceText);

		afterGettingResource();
	}
	
	protected abstract void afterGettingResource() throws Exception;
	protected abstract void beforeGettingResource() throws Exception;
}
