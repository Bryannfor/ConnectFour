package observer_observable;

import java.util.ArrayList;
import java.util.List;

public class ObservableBord {

	private static List<BordObserver> observers = new ArrayList<>();

	public List<BordObserver> getObservers() {
		return observers;
	}

	/**
	 * add an observers
	 * 
	 * @param obs
	 */
	public void addObserver(BordObserver obs) {
		observers.add(obs);
	}

	/**
	 * removes an observer
	 * 
	 * @param obs
	 */
	public void removeObserver(BordObserver obs) {
		observers.remove(obs);
	}

	/**
	 * updates all observers
	 */
	public void notifyObserver() {

		for (int i = 0; i < observers.size(); i++) {
			observers.get(i).update(this);
		}
	}

}
