package starmaker.utils.data;

public class GrassGenData
{
	private String grass, ground;
	private int blockcount;
	private boolean onWater;
	
	public GrassGenData(String grass, int blockcount, boolean onWater, String ground) {
		this.grass = grass;
		this.blockcount = blockcount;
		this.onWater = onWater;
		this.ground = ground;
	}
	
	public String getGrass()	{ return this.grass; }
	public int getBlockCount() { return this.blockcount; }
	public boolean onWater() { return this.onWater; }
	public String getGround() { return this.ground; }
	
}