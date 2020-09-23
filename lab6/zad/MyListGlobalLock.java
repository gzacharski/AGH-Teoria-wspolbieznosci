package lab6;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MyListGlobalLock implements IMyList{
	
	private class Node{
		Object object;
		Node nextNode;

		public Node(Object object) {
			this.object=object;
			this.nextNode=null;
		}
	}
	
	private int size;
	private Node head;
	private Lock listLock;

	public MyListGlobalLock() {
		this.size=0;
		this.head=new Node("HEAD");
		this.listLock=new ReentrantLock();
	}

	@Override
	public boolean add(Object object) {
		listLock.lock();
		
		boolean hasBeenAdded=false;
		
		try {
			
			if(object!=null) {
			
				Node tmpNode=head;
				
				while(tmpNode.nextNode!=null) {
					tmpNode=tmpNode.nextNode;
				}
				Node newNode=new Node(object);
				
				tmpNode.nextNode=newNode;
				
				size++;
				hasBeenAdded=true;
				
			}else {
				throw new IllegalArgumentException();
			}
			
		}finally {
			listLock.unlock();
		}
		
		return hasBeenAdded;
	}
	
	@Override
	public boolean contains(Object object) {
		listLock.lock();
		
		boolean isInList=false;
		
		try {
			
			if(object!=null && !isEmpty()) {
			
				Node tmpNode=head.nextNode;
				
				while(tmpNode!=null && !isInList) {
					
					if(tmpNode.object.equals(object)) isInList=true;

					tmpNode=tmpNode.nextNode;
				}
				
			}else if(object==null) {
				throw new NullPointerException();
			}
			
		}finally {
			listLock.unlock();
		}
		return isInList;
	}

	@Override
	public boolean remove(Object objecToRemove) {
		listLock.lock();
		
		boolean hasBeenRemoved=false;

		try {
			if(objecToRemove!=null && !isEmpty()) {
				Node beforeToDeleteNode=head;

				Node toDeleteNode=beforeToDeleteNode.nextNode;
				
				while(toDeleteNode!=null) {

					if(toDeleteNode.object.equals(objecToRemove)) {
						
						beforeToDeleteNode.nextNode=toDeleteNode.nextNode;
						
						//remove node
						toDeleteNode.nextNode=null;
						toDeleteNode.object=null;
						
						hasBeenRemoved=true;
						break;
					}
					
					beforeToDeleteNode=toDeleteNode;
					
					toDeleteNode=toDeleteNode.nextNode;
				}

			}else if(objecToRemove==null) {
				throw new IllegalArgumentException();
			}
	
		}finally {
			listLock.unlock();
		}
		return hasBeenRemoved;
	}

	@Override
	public void print() {
		listLock.lock();
		
		try {
			String str="[";
			boolean firstTime=true;
			
			Node tmpNode=head;
			
			while(tmpNode.nextNode!=null) {
				
				Object object=tmpNode.nextNode.object;
				
				if(firstTime) {
					str=str.concat(object.toString());
					firstTime=false;
				}else {
					str=str.concat(", ");
					str=str.concat(object.toString());
				}
				
				tmpNode=tmpNode.nextNode;
			}
			
			str=str.concat("]");
			
			System.out.println(str);
			
		}finally {
			listLock.unlock();
		}
	}

	@Override
	public boolean isEmpty() {
		return (head==null);
	}

	@Override
	public int size() {
		return size;
	}
}
