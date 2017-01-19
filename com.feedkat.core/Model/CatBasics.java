package Model;

import org.json.JSONException;
import org.json.JSONObject;

public class CatBasics {
	private String name;
	private Integer weight;
	private String birthdate;
	
	public CatBasics(JSONObject json) throws JSONException {
		this.name = json.getString("name");
		this.weight = json.getInt("weight");
		this.birthdate = json.getString("birth");
	}
	
	public final String getName() {
		return name;
	}
	
	public final String getWeight() {
		Double w = weight*0.001;
		return String.format("%.1f kg", w);
	}
	
	public final String getBirthDate() {
		return birthdate;
	}
	
	@Override
	public String toString() {
		return "Name : " + getName() + " Weight : " + getWeight() + " Birthdate : " +getBirthDate();
	}
	
}
