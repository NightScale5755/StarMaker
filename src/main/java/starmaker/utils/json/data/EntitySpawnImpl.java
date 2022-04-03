package starmaker.utils.json.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EntitySpawnImpl {

	@SerializedName("entity") @Expose
	private String entity;
	
	@SerializedName("weight") @Expose
	private int weight;
	
	@SerializedName("min_count") @Expose
	private int min_count;
	
	@SerializedName("max_count") @Expose
	private int max_count;

	public EntitySpawnImpl(String entity, int weight, int min_count, int max_count) {
		this.entity = entity;
		this.weight = weight;
		this.min_count = min_count;
		this.max_count = max_count;
	}
	
	public String getEntity()
	{
		return this.entity;
	}
	
	public int getWeight() {
		return this.weight;
	}
	
	public int getMinCount() {
		return this.min_count;
	}
	
	public int getMaxCount() {
		return this.max_count;
	}
}
