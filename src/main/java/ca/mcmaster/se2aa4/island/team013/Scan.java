package ca.mcmaster.se2aa4.island.team013;

import org.json.JSONObject;

public class Scan implements Command
{
	
	public Scan(){}

	@Override
	public JSONObject execute(Drone drone){
		return new JSONObject().put("action","scan");
	}
		
	
}