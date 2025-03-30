package ca.mcmaster.se2aa4.island.team013;
import org.json.JSONObject;
public abstract class Observer {
    protected  Subject subject;

    public abstract void update(JSONObject data);
}