package starmaker.utils.json.celestialimpl;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GalaxyImpl {

	@SerializedName("name") @Expose
	private String name;
	
	public GalaxyImpl() {
	}
	
	public GalaxyImpl(String name) {
		this.name = name;
	}
	
	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}
}
