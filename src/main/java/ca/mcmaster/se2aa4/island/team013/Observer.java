package ca.mcmaster.se2aa4.island.team013;

public abstract class Observer {
    protected Subject subject;

    public abstract void update(Drone drone);
}