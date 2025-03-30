package ca.mcmaster.se2aa4.island.team013;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

public abstract class Subject {
    private  List<Observer> observers = new ArrayList<>();

    public void attach(Observer observer){
        this.observers.add(observer);
    }

    public void detach(Observer observer){
        this.observers.remove(observer);
    }

    public void notifyAllObservers(JSONObject data){
        for (Observer observer : this.observers) {
            observer.update(data);
        }
    }
}