package util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class BlockingList<T> extends ArrayList<T>{
	
	
	public void addAllB(Collection<? extends T> c){
		synchronized(this){
			this.addAll(c);
		}
	}
	
	public void addAllB(List<T> c){
		synchronized(this){
			this.addAll(c);
		}
	}
	
	
	public void removeB(T t){
		synchronized(this){
			this.remove(t);
		}
	}
	
	
	public void addB(T t){
		synchronized(this){
			this.add(t);
		}
	}
}
