package lab6;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MyListNodeLock implements IMyList{
	
	private class Node{
		Object object;
		Lock nodeLock;
		Node nextNode;

		public Node(Object object) {
			this.object=object;
			this.nodeLock=new ReentrantLock();
			this.nextNode=null;
		}
	}
	
	private int size;
	private Node head;

	public MyListNodeLock() {
		this.size=0;
		this.head=new Node("HEAD");
	}

	@Override
	public boolean add(Object objectToAdd) {
		boolean hasBeenAdded=false;
		
		if(objectToAdd!=null) {

			Node tmpNode=head;
			
			tmpNode.nodeLock.lock();
			
			while(tmpNode.nextNode!=null) {
				tmpNode.nextNode.nodeLock.lock();
				tmpNode.nodeLock.unlock();
				tmpNode=tmpNode.nextNode;
			}
			
			Node newNode=new Node(objectToAdd);
			tmpNode.nextNode=newNode;
			
			tmpNode.nodeLock.unlock();
			
			size++;
			hasBeenAdded=true;
			
		}else {
			throw new IllegalArgumentException();
		}
		return hasBeenAdded;
	}
	
	
	@Override
	public boolean contains(Object objectToFind) {
		boolean isInList=false;
		
		if(objectToFind!=null && !isEmpty()) {
		
			Node tmpNode=head;
			
			tmpNode.nodeLock.lock();

			while(tmpNode.nextNode!=null && !isInList) {
				tmpNode.nextNode.nodeLock.lock();
				
				if(tmpNode.object.equals(objectToFind)) isInList=true;

				tmpNode.nodeLock.unlock();
				tmpNode=tmpNode.nextNode;
			}
			
			tmpNode.nodeLock.unlock();
			
		}else if(objectToFind==null) {
			throw new NullPointerException();
		}
		return isInList;
	}

	@Override
	public boolean remove(Object objecToRemove) {
		boolean hasBeenRemoved=false;
		
		if(objecToRemove!=null && !isEmpty()) {
			Node beforeToDeleteNode=head;
			
			beforeToDeleteNode.nodeLock.lock();
			
			Node toDeleteNode=beforeToDeleteNode.nextNode;
			
			while(toDeleteNode!=null) {
				toDeleteNode.nodeLock.lock();
				
				if(toDeleteNode.object.equals(objecToRemove)) {
					
					if(toDeleteNode.nextNode!=null) toDeleteNode.nextNode.nodeLock.lock();
					
					beforeToDeleteNode.nextNode=toDeleteNode.nextNode;
					
					//remove node
					toDeleteNode.nextNode=null;
					toDeleteNode.object=null;
					toDeleteNode.nodeLock.unlock();
					
					hasBeenRemoved=true;
					break;
				}
				
				beforeToDeleteNode.nodeLock.unlock();
				beforeToDeleteNode=toDeleteNode;
				
				toDeleteNode=toDeleteNode.nextNode;
			}
			
			if(hasBeenRemoved && beforeToDeleteNode.nextNode!=null) beforeToDeleteNode.nextNode.nodeLock.unlock();
			
			beforeToDeleteNode.nodeLock.unlock();
			
		}else if(objecToRemove==null) {
			throw new IllegalArgumentException();
		}

		return hasBeenRemoved;
	}

	@Override
	public void print() {
		String str="[";
		boolean firstTime=true;
		
		Node tmpNode=head;
		tmpNode.nodeLock.lock();
		
		while(tmpNode.nextNode!=null) {
			tmpNode.nextNode.nodeLock.lock();
			
			Object object=tmpNode.nextNode.object;
			
			if(firstTime) {
				str=str.concat(object.toString());
				firstTime=false;
			}else {
				str=str.concat(", ");
				str=str.concat(object.toString());
			}
			
			tmpNode.nodeLock.unlock();
			tmpNode=tmpNode.nextNode;
		}
		
		tmpNode.nodeLock.unlock();
		str=str.concat("]");
		
		System.out.println(str);
	}

	@Override
	public boolean isEmpty() {
		return (head.nextNode==null);
	}

	@Override
	public int size() {
		return size;
	}
}
