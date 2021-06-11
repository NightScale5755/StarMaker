package starmaker.utils.json.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GrassGenImpl {

	@SerializedName("grass_block")
	@Expose
	private String grassBlock;
	@SerializedName("ground_block")
	@Expose
	private String groundBlock;
	@SerializedName("grass_count")
	@Expose
	private int blockCount;
	@SerializedName("onWater")
	@Expose
	private boolean onWater;
	
	public GrassGenImpl()
	{
	}
	
	public GrassGenImpl(String grass, String ground, int blockcount, boolean onWater)
	{
		this.grassBlock = grass;
		this.groundBlock = ground;
		this.blockCount = blockcount;
		this.onWater = onWater;	
	}
	
	public void setGrassBlock(String grass)
	{
		this.grassBlock = grass;
	}
	
	public String getGrassBlock()
	{
		return this.grassBlock;
	}
	
	public GrassGenImpl withGrassBlock(String grass)
	{
		this.grassBlock = grass;
		return this;
	}
	
	public void setGroundBlock(String ground)
	{
		this.groundBlock = ground;
	}
	
	public String getGroundBlock()
	{
		return this.groundBlock;
	}
	
	public GrassGenImpl withGroundBlock(String ground)
	{
		this.groundBlock = ground;
		return this;
	}
	
	public void setGrassCount(int count)
	{
		this.blockCount = count;
	}
	
	public int getGrassCount()
	{
		return this.blockCount;
	}
	
	public GrassGenImpl withGrassCount(int count)
	{
		this.blockCount = count;
		return this;
	}
	
	public void setOnWater(boolean onWater)
	{
		this.onWater = onWater;
	}
	
	public boolean onWater()
	{
		return this.onWater;
	}
	
	
}
