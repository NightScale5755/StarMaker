package starmaker.utils.json.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OrbitDataImpl {

	@SerializedName("phase")
	@Expose
	private double phase;
	@SerializedName("size")
	@Expose
	private double size;
	@SerializedName("distance_from_center")
	@Expose
	private double distanceFromCenter;
	@SerializedName("relative_time")
	@Expose
	private double relativeTime;
	@SerializedName("eccentricityX")
	@Expose
	private double eccentricityX;
	@SerializedName("eccentricityY")
	@Expose
	private double eccentricityY;
	
	public OrbitDataImpl(double phase, double size, double distanceFromCenter, double relativeTime, double eccentricityX, double eccentricityY) {
		this.phase = phase;
		this.size = size;
		this.distanceFromCenter = distanceFromCenter;
		this.relativeTime = relativeTime;
		this.eccentricityX = eccentricityX;
		this.eccentricityY = eccentricityY;
	}
	
	public Float getPhase()
	{
		return (float) phase;
	}

	public void setPhase(double phase)
	{
		this.phase = phase;
	}

	public OrbitDataImpl withPhase(double phase)
	{
		this.phase = phase;
		return this;
	}

	public Float getSize()
	{
		return (float) size;
	}

	public void setSize(double size)
	{
		this.size = size;
	}

	public OrbitDataImpl withSize(double size)
	{
		this.size = size;
		return this;
	}

	public Float getDistanceFromCenter()
	{
		return (float) distanceFromCenter;
	}

	public void setDistanceFromCenter(double distanceFromCenter)
	{
		this.distanceFromCenter = distanceFromCenter;
	}

	public OrbitDataImpl withDistanceFromCenter(double distanceFromCenter)
	{
		this.distanceFromCenter = distanceFromCenter;
		return this;
	}

	public Float getRelativeTime()
	{
		return (float) relativeTime;
	}

	public void setRelativeTime(double relativeTime)
	{
		this.relativeTime = relativeTime;
	}

	public OrbitDataImpl withRelativeTime(double relativeTime)
	{
		this.relativeTime = relativeTime;
		return this;
	}
	
	public Float getEccentricityX()
	{
		return (float) eccentricityX;
	}

	public void setEccentricityX(double eccentricityX)
	{
		this.eccentricityX = eccentricityX;
	}

	public OrbitDataImpl withEccentricityX(double eccentricityX)
	{
		this.eccentricityX = eccentricityX;
		return this;
	}
	
	public Float getEccentricityY()
	{
		return (float) eccentricityY;
	}

	public void setEccentricityY(double eccentricityY)
	{
		this.eccentricityY = eccentricityY;
	}

	public OrbitDataImpl withEccentricityY(double eccentricityY)
	{
		this.eccentricityY = eccentricityY;
		return this;
	}
	

}
