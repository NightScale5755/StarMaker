
package starmaker.utils.json.planet;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import micdoodle8.mods.galacticraft.api.vector.Vector3;

public class BiomeImpl
{

	@SerializedName("persistance")
	@Expose
	private double persistance;
	@SerializedName("octaves")
	@Expose
	private Integer octaves;
	@SerializedName("height")
	@Expose
	private Integer height;
	@SerializedName("intquility")
	@Expose
	private Integer intquility;
	@SerializedName("biomeSize")
	@Expose
	private double biomeSize;
	@SerializedName("water_color")
	@Expose
	private List<Integer> waterColor = null;
	@SerializedName("foliage_color")
	@Expose
	private List<Integer> foliageColor = null;
	@SerializedName("grass_color")
	@Expose
	private List<Integer> grassColor = null;
	@SerializedName("surface_block")
	@Expose
	private String surfaceBlock;
	@SerializedName("subsurface_block")
	@Expose
	private String subsurfaceBlock;
	
	@SerializedName("oregen")
	@Expose
	private List<BiomeDecoratorImpl> oregen = null;
	/**
	 * No args constructor for use in serialization
	 * 
	 */
	public BiomeImpl()
	{
	}

	/**
	 * 
	 * @param octaves
	 * @param waterColor
	 * @param surfaceBlock
	 * @param persistance
	 * @param intquility
	 * @param biomeSize
	 * @param foliageColor
	 * @param grassColor
	 * @param subsurfaceBlock
	 * @param height
	 */
	public BiomeImpl(Double persistance, Integer octaves, Integer height, Integer intquility, Double biomeSize,
			List<Integer> waterColor, List<Integer> foliageColor, List<Integer> grassColor, String surfaceBlock,
			String subsurfaceBlock, List<BiomeDecoratorImpl> oregen)
	{
		super();
		this.persistance = persistance;
		this.octaves = octaves;
		this.height = height;
		this.intquility = intquility;
		this.biomeSize = biomeSize;
		this.waterColor = waterColor;
		this.foliageColor = foliageColor;
		this.grassColor = grassColor;
		this.surfaceBlock = surfaceBlock;
		this.subsurfaceBlock = subsurfaceBlock;
		this.oregen = oregen;
	}

	public Float getPersistance()
	{
		return (float) persistance;
	}

	public void setPersistance(Double persistance)
	{
		this.persistance = persistance;
	}

	public BiomeImpl withPersistance(Double persistance)
	{
		this.persistance = persistance;
		return this;
	}

	public Integer getOctaves()
	{
		return octaves;
	}

	public void setOctaves(Integer octaves)
	{
		this.octaves = octaves;
	}

	public BiomeImpl withOctaves(Integer octaves)
	{
		this.octaves = octaves;
		return this;
	}

	public Integer getHeight()
	{
		return height;
	}

	public void setHeight(Integer height)
	{
		this.height = height;
	}

	public BiomeImpl withHeight(Integer height)
	{
		this.height = height;
		return this;
	}

	public Integer getIntquility()
	{
		return intquility;
	}

	public void setIntquility(Integer intquility)
	{
		this.intquility = intquility;
	}

	public BiomeImpl withIntquility(Integer intquility)
	{
		this.intquility = intquility;
		return this;
	}

	public Float getBiomeSize()
	{
		return (float) biomeSize;
	}

	public void setBiomeSize(Double biomeSize)
	{
		this.biomeSize = biomeSize;
	}

	public BiomeImpl withBiomeSize(Double biomeSize)
	{
		this.biomeSize = biomeSize;
		return this;
	}

	public Vector3 getWaterColor()
	{
		return new Vector3(waterColor.get(0), waterColor.get(1), waterColor.get(2));
	}

	public void setWaterColor(List<Integer> waterColor)
	{
		this.waterColor = waterColor;
	}

	public BiomeImpl withWaterColor(List<Integer> waterColor)
	{
		this.waterColor = waterColor;
		return this;
	}

	public Vector3 getFoliageColor()
	{
		return new Vector3(foliageColor.get(0), foliageColor.get(1), foliageColor.get(2));
	}

	public void setFoliageColor(List<Integer> foliageColor)
	{
		this.foliageColor = foliageColor;
	}

	public BiomeImpl withFoliageColor(List<Integer> foliageColor)
	{
		this.foliageColor = foliageColor;
		return this;
	}

	public Vector3 getGrassColor()
	{
		return new Vector3(grassColor.get(0), grassColor.get(1), grassColor.get(2));
	}

	public void setGrassColor(List<Integer> grassColor)
	{
		this.grassColor = grassColor;
	}

	public BiomeImpl withGrassColor(List<Integer> grassColor)
	{
		this.grassColor = grassColor;
		return this;
	}

	public String getSurfaceBlock()
	{
		return surfaceBlock;
	}

	public void setSurfaceBlock(String surfaceBlock)
	{
		this.surfaceBlock = surfaceBlock;
	}

	public BiomeImpl withSurfaceBlock(String surfaceBlock)
	{
		this.surfaceBlock = surfaceBlock;
		return this;
	}

	public String getSubsurfaceBlock()
	{
		return subsurfaceBlock;
	}

	public void setSubsurfaceBlock(String subsurfaceBlock)
	{
		this.subsurfaceBlock = subsurfaceBlock;
	}

	public BiomeImpl withSubsurfaceBlock(String subsurfaceBlock)
	{
		this.subsurfaceBlock = subsurfaceBlock;
		return this;
	}
	
	public List<BiomeDecoratorImpl> getOreGenList()
	{
		return this.oregen;
	}
	
	public void setOreGenList(List<BiomeDecoratorImpl> oregen)
	{
		this.oregen = oregen;
	}
	
	public BiomeImpl withOreGenList(List<BiomeDecoratorImpl> oregen)
	{
		this.oregen = oregen;
		return this;
	}

}
