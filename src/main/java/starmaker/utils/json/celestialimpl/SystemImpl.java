package starmaker.utils.json.celestialimpl;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import starmaker.utils.json.data.StarsDataImpl;

public class SystemImpl
{

	@SerializedName("galaxy") @Expose
	private String galaxy;
	@SerializedName("posX")	@Expose
	private double posX;
	@SerializedName("posY")	@Expose
	private double posY;
	@SerializedName("stars") @Expose
	private List<StarsDataImpl> starsData;
	@SerializedName("habitable_zone") @Expose
	private List<Float> habitable_zone = null;

	public SystemImpl()
	{
	}

	/**
	 *
	 * @param galaxy
	 * @param posX
	 * @param posY
	 * @param stars
	 */
	public SystemImpl(String galaxy, double posX, double posY, List<StarsDataImpl> stars, List<Float> habitable_zone)
	{
		super();
		this.galaxy = galaxy;
		this.posX = posX;
		this.posY = posY;
		this.starsData = stars;
		this.habitable_zone = habitable_zone;
	}

	public String getGalaxy()
	{
		return galaxy;
	}

	public void setGalaxy(String galaxy)
	{
		this.galaxy = galaxy;
	}

	public SystemImpl withGalaxy(String galaxy)
	{
		this.galaxy = galaxy;
		return this;
	}

	public String getStarName()
	{
		String firstStarName = this.starsData.get(0).getName();
		return firstStarName;
	}

	public Float getPosX()
	{
		return (float) posX;
	}

	public void setPosX(double posX)
	{
		this.posX = posX;
	}

	public SystemImpl withPosX(double posX)
	{
		this.posX = posX;
		return this;
	}

	public Float getPosY()
	{
		return (float) posY;
	}

	public void setPosY(double posY)
	{
		this.posY = posY;
	}

	public SystemImpl withPosY(double posY)
	{
		this.posY = posY;
		return this;
	}
	
	public List<StarsDataImpl> getStars() {
		return this.starsData;
	}
	
	public void setStars(List<StarsDataImpl> stars)
	{
		this.starsData = stars;
	}
	
	public List<Float> getHabitableZone() {
		return this.habitable_zone;
	}
	
	public void setHabitableZone(List<Float> habitable_zone) {
		this.habitable_zone = habitable_zone;
	}
}
