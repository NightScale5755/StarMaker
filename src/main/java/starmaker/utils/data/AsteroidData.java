package starmaker.utils.data;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.BlockPos;

public class AsteroidData
{
    public BlockPos centre;
    public int sizeAndLandedFlag = 15;
    public int coreAndSpawnedFlag = -2;

    public AsteroidData(BlockPos pos)
    {
        this.centre = pos;
    }

    public AsteroidData(BlockPos pos, int size, int core)
    {
        this.centre = pos;
        this.sizeAndLandedFlag = size;
        this.coreAndSpawnedFlag = core;
    }

    @Override
    public int hashCode()
    {
        if (this.centre != null)
        {
            return this.centre.hashCode();
        }
        else
        {
            return 0;
        }
    }

    @Override
    public boolean equals(Object o)
    {
        if (o instanceof AsteroidData)
        {
        	BlockPos pos = ((AsteroidData) o).centre;
            return this.centre.getX() == pos.getX() && this.centre.getY() == pos.getY() && this.centre.getZ() == pos.getZ();
        }

        if (o instanceof BlockPos)
        {
        	BlockPos pos = (BlockPos) o;
            return this.centre.getX() == pos.getX() && this.centre.getY() == pos.getY() && this.centre.getZ() == pos.getZ();
        }

        return false;
    }

    public NBTTagCompound writeToNBT(NBTTagCompound tag)
    {
        tag.setInteger("x", this.centre.getX());
        tag.setInteger("y", this.centre.getY());
        tag.setInteger("z", this.centre.getZ());
        tag.setInteger("coreAndFlag", this.coreAndSpawnedFlag);
        tag.setInteger("sizeAndFlag", this.sizeAndLandedFlag);
        return tag;
    }

    public static AsteroidData readFromNBT(NBTTagCompound tag)
    {
        BlockPos tempPos = new BlockPos(tag.getInteger("x"), tag.getInteger("y"), tag.getInteger("z"));

        AsteroidData roid = new AsteroidData(tempPos);
        if (tag.hasKey("coreAndFlag"))
        {
            roid.coreAndSpawnedFlag = tag.getInteger("coreAndFlag");
        }
        if (tag.hasKey("sizeAndFlag"))
        {
            roid.sizeAndLandedFlag = tag.getInteger("sizeAndFlag");
        }

        return roid;
    }
}
