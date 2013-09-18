

import java.util.ArrayList;
import java.util.EventObject;
import java.util.List;

public abstract class AbstractObservable implements Observable{
	protected List<Observer> observers;
	
	public AbstractObservable(){
		observers = new ArrayList<Observer>();
	}
	public void addObserver(Observer o) {
		observers.add(o);
	}

	public void removeObserver(Observer o) {
		observers.remove(o);
	}

	public void notifyObservers(EventObject e) {
		for(Observer o: observers){
			o.eventOccurred(e);
		}
	}
}
