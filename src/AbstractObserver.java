

import java.util.ArrayList;
import java.util.EventObject;
import java.util.Iterator;
import java.util.List;

public abstract class AbstractObserver implements Observer{
    protected List<CustomEventListener> listeners = new ArrayList<CustomEventListener>();
    
    public synchronized void addEventListener( CustomEventListener l ){
    	listeners.add(l);
    }
    public synchronized void removeEventListener( CustomEventListener l ) {
        listeners.remove( l );
    }
    
    public void eventOccurred(EventObject e){
    	Iterator<CustomEventListener> listenersIterator = listeners.iterator();
        while( listenersIterator.hasNext() ) {
            ( (CustomEventListener) listenersIterator.next() ).eventOccurred( e );
        }
    }

}
