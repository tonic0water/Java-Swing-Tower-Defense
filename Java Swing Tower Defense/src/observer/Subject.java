package observer;

import java.awt.List;
import java.util.ArrayList;

import critterMain.Critter;

public abstract class Subject {

	boolean changed = false;
	private ArrayList<IObserver> obList = new ArrayList<IObserver>();

	public void addObserver(IObserver ob){
		if(!obList.contains(ob)){
			this.obList.add(ob);
		}
	}

	public boolean hasChanged(){			//Tests if this object has changed
		return this.changed;
	}

	protected void clearChanged(){
		this.changed = false;
		System.out.println("the has changedChanged method is set back to false");
		
	}

	protected void setChanged(){
		this.changed = true;
		System.out.println("this Observable object has changed; the hasChanged method will now return true.");
	}

	public int countObservers(){
		return this.obList.size();
	}

	public void deleteObserver(IObserver ob){
		this.obList.remove(ob);
		System.out.println("An observer has been deleted");
	}

	public void notifyObservers(Critter cr){
		if(changed){
			for(IObserver o : obList){
				o.update(cr);
			}
		}
		clearChanged();
	}

	public void notifyAliveObserver(Critter cr){
		if(changed){
			for(IObserver o : obList){
				if(o instanceof AliveObserver){
					o.update(cr);
				}
			}
		}
		clearChanged();
	}

	public void notifyHitPointsObserver(Critter cr){
		if(changed){
			for(IObserver o : obList){
				if(o instanceof HitPointsObserver){
					o.update(cr);
				}
			}
		}
		clearChanged();

	}

	public void notifyPositionObserver(Critter cr){
		if(changed){
			for(IObserver o : obList){
				if(o instanceof PositionObserver){
					o.update(cr);
				}
			}
		}
		clearChanged();
	}

	public void notifySpeedObserver(Critter cr){
		if(changed){
			for(IObserver o : obList){
				if(o instanceof SpeedObserver){
					o.update(cr);
				}
			}
		}
		clearChanged();
	}
}
