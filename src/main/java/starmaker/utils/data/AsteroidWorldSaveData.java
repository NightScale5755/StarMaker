package starmaker.utils.data;

import micdoodle8.mods.galacticraft.core.Constants;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.storage.WorldSavedData;

public class AsteroidWorldSaveData extends WorldSavedData
{
    public static final String saveDataID = Constants.GCDATAFOLDER;
    public NBTTagCompound datacompound;
    public String name;

    public AsteroidWorldSaveData(String s)
    {
        super(saveDataID + s);
        this.name = s;
        this.datacompound = new NBTTagCompound();
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt)
    {
        this.datacompound = nbt.getCompoundTag(name);
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound nbt)
    {
        nbt.setTag(name, this.datacompound);
        return nbt;
    }
}