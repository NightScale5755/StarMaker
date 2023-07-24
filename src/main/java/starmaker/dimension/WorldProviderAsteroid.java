package starmaker.dimension;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.TreeMap;

import micdoodle8.mods.galacticraft.api.vector.BlockVec3;
import micdoodle8.mods.galacticraft.api.vector.Vector3;
import micdoodle8.mods.galacticraft.core.client.CloudRenderer;
import micdoodle8.mods.galacticraft.core.util.GCLog;
import micdoodle8.mods.galacticraft.planets.asteroids.entities.EntityAstroMiner;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.biome.BiomeProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraftforge.client.IRenderHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import starmaker.utils.data.AsteroidData;
import starmaker.utils.data.AsteroidWorldSaveData;
import starmaker.world.BiomeProviderBody;

public class WorldProviderAsteroid extends WorldProviderBody {
	
	private HashSet<AsteroidData> asteroids = new HashSet<>();
	private boolean dataNotLoaded = true;
	private AsteroidWorldSaveData datafile;
	
	@Override
    public void init()
    {
        super.init();
        this.nether = true;
    }
	
	@Override
    public boolean isDaytime()
    {
        return true;
    }

	@Override
    public boolean hasSunset()
    {
        return false;
    }
	
	@Override
    public int getAverageGroundLevel()
    {
        return 96;
    }
	
	@Override
	public IChunkGenerator createChunkGenerator() {		
		return new ChunkProviderAsteroid(this.world, this.world.getSeed(), getDimData());		
	}
	
	@Override 
    public Class<? extends BiomeProvider> getBiomeProviderClass() { 
    	return BiomeProviderBody.class; 
    }
	
	@Override
	public float calculateCelestialAngle(long par1, float par3) {
		return 0.22F;
	}
	
	@Override
	public double getMeteorFrequency() {
		return 0;
	}
	
	@Override
	public long getDayLength() {
		return 0L;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public Vector3 getFogColor() {
	
		return new Vector3(0, 0, 0);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Vector3 getSkyColor() {
		return new Vector3(0, 0, 0);	
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public IRenderHandler getCloudRenderer() {
		return new CloudRenderer();
	}
	 
	@Override
    @SideOnly(Side.CLIENT)
    public float getSunBrightness(float par1) {
       float f1 = this.world.getCelestialAngle(1.0F);
       float f2 = 1.25F - (MathHelper.cos(f1 * 3.1415927F * 2.0F) * 2.0F + 0.2F);
       float f3 = this.world.getWorldTime();
       if(f2 < 0.0F) {
          f2 = 0.0F;
       }

       if(f2 > 1.0F) {
          f2 = 1.0F;
       }

       f2 = 1.2F - f2;
       return f2 * 1.0F;
    }
	
	public void addAsteroid(BlockPos pos, int size, int core) {
		AsteroidData coords = new AsteroidData(pos, size, core);
		if (!this.asteroids.contains(coords)) {
			if (this.dataNotLoaded) {
				this.loadAsteroidSavedData();
			}
			if (!this.asteroids.contains(coords)) {
				this.addToNBT(this.datafile.datacompound, coords);
				this.asteroids.add(coords);
			}
		}
	}

	public void removeAsteroid(BlockPos pos) {
		AsteroidData coords = new AsteroidData(pos);
		if (this.asteroids.contains(coords)) {
			this.asteroids.remove(coords);

			if (this.dataNotLoaded) {
				this.loadAsteroidSavedData();
			}
			this.writeToNBT(this.datafile.datacompound);
		}
	}

	private void loadAsteroidSavedData() {
		this.datafile = (AsteroidWorldSaveData) this.world.loadData(AsteroidWorldSaveData.class,
				AsteroidWorldSaveData.saveDataID + "SM" + getDimData().getBody().getName() + "Data");

		if (this.datafile == null) {
			this.datafile = new AsteroidWorldSaveData(getDimData().getBody().getName());
			this.world.setData(datafile.saveDataID, this.datafile);
			this.writeToNBT(this.datafile.datacompound);
		} else {
			this.readFromNBT(this.datafile.datacompound);
		}

		this.dataNotLoaded = false;
	}

	private void readFromNBT(NBTTagCompound nbt) {
		NBTTagList coordList = nbt.getTagList("coords", 10);
		if (coordList.tagCount() > 0) {
			for (int j = 0; j < coordList.tagCount(); j++) {
				NBTTagCompound tag1 = coordList.getCompoundTagAt(j);

				if (tag1 != null) {
					this.asteroids.add(AsteroidData.readFromNBT(tag1));
				}
			}
		}
	}

	private void writeToNBT(NBTTagCompound nbt) {
		NBTTagList coordList = new NBTTagList();
		for (AsteroidData coords : this.asteroids) {
			NBTTagCompound tag = new NBTTagCompound();
			coords.writeToNBT(tag);
			coordList.appendTag(tag);
		}
		nbt.setTag("coords", coordList);
		this.datafile.markDirty();
	}

	private void addToNBT(NBTTagCompound nbt, AsteroidData coords) {
		NBTTagList coordList = nbt.getTagList("coords", 10);
		NBTTagCompound tag = new NBTTagCompound();
		coords.writeToNBT(tag);
		coordList.appendTag(tag);
		nbt.setTag("coords", coordList);
		this.datafile.markDirty();
	}

	public boolean checkHasAsteroids() {
		if (this.dataNotLoaded) {
			this.loadAsteroidSavedData();
		}

		if (this.asteroids.size() == 0) {
			return false;
		}

		return true;
	}

	public BlockVec3 getClosestAsteroidXZ(int x, int y, int z, boolean mark) {
		if (!this.checkHasAsteroids()) {
			return null;
		}

		BlockVec3 result = null;
		AsteroidData resultRoid = null;
		int lowestDistance = Integer.MAX_VALUE;

		for (AsteroidData test : this.asteroids) {
			if (mark && (test.sizeAndLandedFlag & 128) > 0) {
				continue;
			}

			int dx = x - test.centre.getX();
			int dz = z - test.centre.getZ();
			int a = dx * dx + dz * dz;
			if (a < lowestDistance) {
				lowestDistance = a;
				result = new BlockVec3(test.centre);
				resultRoid = test;
			}
		}

		if (result == null) {
			return null;
		}

		if (mark) {
			resultRoid.sizeAndLandedFlag |= 128;
			this.writeToNBT(this.datafile.datacompound);
		}
		result = result.clone();
		result.sideDoneBits = resultRoid.sizeAndLandedFlag & 127;
		return result;
	}

	public ArrayList<BlockPos> getClosestAsteroidsXZ(int x, int y, int z, int facing, int count) {
		if (!this.checkHasAsteroids()) {
			return null;
		}

		TreeMap<Integer, BlockPos> targets = new TreeMap<>();

		for (AsteroidData roid : this.asteroids) {
			BlockPos test = roid.centre;
			switch (facing) {
			case 2:
				if (z - 16 < test.getZ()) {
					continue;
				}
				break;
			case 3:
				if (z + 16 > test.getZ()) {
					continue;
				}
				break;
			case 4:
				if (x - 16 < test.getX()) {
					continue;
				}
				break;
			case 5:
				if (x + 16 > test.getX()) {
					continue;
				}
				break;
			}
			int dx = x - test.getX();
			int dz = z - test.getZ();
			int a = dx * dx + dz * dz;
			if (a < 262144) {
				targets.put(a, test);
			}
		}

		int max = Math.max(count, targets.size());
		if (max <= 0) {
			return null;
		}

		ArrayList<BlockPos> returnValues = new ArrayList<>();
		int i = 0;
		int offset = EntityAstroMiner.MINE_LENGTH_AST / 2;
		for (BlockPos target : targets.values()) {
			BlockPos coords = target;
			GCLog.debug("Found nearby asteroid at " + target.toString());
			switch (facing) {
			case 2:
				coords.add(0, 0, offset);
				break;
			case 3:
				coords.add(0, 0, -offset);
				break;
			case 4:
				coords.add(offset, 0, 0);
				break;
			case 5:
				coords.add(-offset, 0, 0);
				break;
			}
			returnValues.add(coords);
			if (++i >= count) {
				break;
			}
		}

		return returnValues;
	}
	
	@Override
	public boolean hasSkyLight() {
		return false;
	}
	
    @Override
    public float getArrowGravity()
    {
        return 0.002F;
    }

}
