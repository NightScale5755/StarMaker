package starmaker.utils.json;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SystemImpl
{

	@SerializedName("name")
	@Expose
	private String name;
	@SerializedName("galaxy")
	@Expose
	private String galaxy;
	@SerializedName("star_name")
	@Expose
	private String starName;
	@SerializedName("posX")
	@Expose
	private double posX;
	@SerializedName("posY")
	@Expose
	private double posY;
	@SerializedName("star_size")
	@Expose
	private double starSize;
	@SerializedName("star_class")
	@Expose
	private Integer starClass;
	@SerializedName("star_color")
	@Expose
	private Integer starColor;

	public SystemImpl()
	{
	}

	/**
	 *
	 * @param posX
	 * @param posY
	 * @param starName
	 * @param name
	 * @param starClass
	 * @param galaxy
	 * @param starSize
	 * @param starColor
	 */
	public SystemImpl(String name, String galaxy, String starName, double posX, double posY, double starSize,
			Integer starClass, Integer starColor)
	{
		super();
		this.name = name;
		this.galaxy = galaxy;
		this.starName = starName;
		this.posX = posX;
		this.posY = posY;
		this.starSize = starSize;
		this.starClass = starClass;
		this.starColor = starColor;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public SystemImpl withName(String name)
	{
		this.name = name;
		return this;
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
		return starName;
	}

	public void setStarName(String starName)
	{
		this.starName = starName;
	}

	public SystemImpl withStarName(String starName)
	{
		this.starName = starName;
		return this;
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

	public Float getStarSize()
	{
		return (float) starSize;
	}

	public void setStarSize(double starSize)
	{
		this.starSize = starSize;
	}

	public SystemImpl withStarSize(double starSize)
	{
		this.starSize = starSize;
		return this;
	}

	public Integer getStarClass()
	{
		return starClass;
	}

	public void setStarClass(Integer starClass)
	{
		this.starClass = starClass;
	}

	public SystemImpl withStarClass(Integer starClass)
	{
		this.starClass = starClass;
		return this;
	}

	public Integer getStarColor()
	{
		return starColor;
	}

	public void setStarColor(Integer starColor)
	{
		this.starColor = starColor;
	}

	public SystemImpl withStarColor(Integer starColor)
	{
		this.starColor = starColor;
		return this;
	}
}
