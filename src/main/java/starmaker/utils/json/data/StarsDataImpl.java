package starmaker.utils.json.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class StarsDataImpl {

	@SerializedName("name")
	@Expose
	private String name;
	@SerializedName("star_phase")
	@Expose
	private float starPhase;
	@SerializedName("star_size")
	@Expose
	private float starSize;
	@SerializedName("star_class")
	@Expose
	private Integer starClass;
	@SerializedName("star_color")
	@Expose
	private Integer starColor;
	@SerializedName("distance_from_center") @Expose
	private Float distanceFromCenter = -1F;
	
	public StarsDataImpl(String name, float star_phase, float star_size, int star_class, int star_color) {
		this.name = name;
		this.starPhase = star_phase;
		this.starSize = star_size;
		this.starClass = star_class;
		this.starColor = star_color;		
	}
	
	public StarsDataImpl setDistanceFromCenter(float distance) {
		this.distanceFromCenter = distance;
		return this;
	}
	
	public String getName() {
		return this.name;
	}
	
	public float getStarPhase() {
		return this.starPhase;
	}
	
	public float getStarSize() {
		return this.starSize;
	}
	
	public int getStarClass() {
		return this.starClass;
	}
	
	public int getStarColor() {
		return this.starColor;
	}
	
	public Float getDistanceFromCenter() {
		return this.distanceFromCenter;
	}
}
