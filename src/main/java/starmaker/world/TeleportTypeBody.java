package starmaker.world;

import java.util.Random;

import micdoodle8.mods.galacticraft.api.galaxies.Satellite;
import micdoodle8.mods.galacticraft.api.vector.Vector3;
import micdoodle8.mods.galacticraft.api.world.AtmosphereInfo;
import micdoodle8.mods.galacticraft.api.world.IGalacticraftWorldProvider;
import micdoodle8.mods.galacticraft.api.world.ITeleportType;
import micdoodle8.mods.galacticraft.core.entities.EntityLander;
import micdoodle8.mods.galacticraft.core.entities.EntityLanderBase;
import micdoodle8.mods.galacticraft.core.entities.player.GCPlayerStats;
import micdoodle8.mods.galacticraft.core.util.CompatibilityManager;
import micdoodle8.mods.galacticraft.core.util.ConfigManagerCore;
import micdoodle8.mods.galacticraft.core.util.EnumColor;
import micdoodle8.mods.galacticraft.core.util.GCCoreUtil;
import micdoodle8.mods.galacticraft.planets.mars.entities.EntityLandingBalloons;
import micdoodle8.mods.galacticraft.planets.venus.entities.EntityEntryPodVenus;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import starmaker.api.ILanderTypeProvider;
import starmaker.utils.data.DimData;

public class TeleportTypeBody implements ITeleportType{
	
	private DimData data;
	
	public TeleportTypeBody() {}
	
	public TeleportTypeBody(DimData data) {
		this.data = data;
	}

    @Override
    public boolean useParachute()
    {
        return false;
    }

    @Override
    public Vector3 getPlayerSpawnLocation(WorldServer world, EntityPlayerMP player)
    {
    	if(data != null && data.getBody() instanceof Satellite) {
    		 return new Vector3(0.5, 65.0, 0.5);
    	}
    	
        if (player != null)
        {
            GCPlayerStats stats = GCPlayerStats.get(player);
            double x = stats.getCoordsTeleportedFromX();
            double z = stats.getCoordsTeleportedFromZ();
            int limit = ConfigManagerCore.otherPlanetWorldBorders - 2;
            if (limit > 20)
            {
                if (x > limit)
                {
                    z *= limit / x;
                    x = limit;
                }
                else if (x < -limit)
                {
                    z *= -limit / x;
                    x = -limit;
                }
                if (z > limit)
                {
                    x *= limit / z;
                    z = limit;
                }
                else if (z < -limit)
                {
                    x *= - limit / z;
                    z = -limit;
                }
            }
            return new Vector3(x, ConfigManagerCore.disableLander ? 250.0 : 900.0, z);
        }

        return null;
    }

    @Override
    public Vector3 getEntitySpawnLocation(WorldServer world, Entity entity)
    {
    	if(data != null && data.getBody() instanceof Satellite) {
    		return new Vector3(0.5, 65.0, 0.5);
    	}
   	
        return new Vector3(entity.posX, ConfigManagerCore.disableLander ? 250.0 : 900.0, entity.posZ);
    }

    @Override
    public Vector3 getParaChestSpawnLocation(WorldServer world, EntityPlayerMP player, Random rand)
    {
    	if(data != null && data.getBody() instanceof Satellite) {
    		return new Vector3(-8.5D, 90.0, -1.5D);
    	}
   	
        return null;
    }

    @Override
    public void onSpaceDimensionChanged(World world, EntityPlayerMP player, boolean ridingAutoRocket)
    {
    	if(data != null && data.getBody() instanceof Satellite) {
	    	if (ConfigManagerCore.spaceStationsRequirePermission && !world.isRemote)
	        {
	            player.sendMessage(new TextComponentString(EnumColor.YELLOW + GCCoreUtil.translate("gui.spacestation.type_command") + " " + EnumColor.AQUA + "/ssinvite " + GCCoreUtil.translate("gui.spacestation.playername") + " " + EnumColor.YELLOW + GCCoreUtil.translate("gui.spacestation.to_allow_entry")));
	        }
    	} else {
	    	
	        if (!ridingAutoRocket && player != null && GCPlayerStats.get(player).getTeleportCooldown() <= 0)
	        {
	            if (player.capabilities.isFlying)
	            {
	                player.capabilities.isFlying = false;
	            }
	
	            //EntityLandingBalloons lander = new EntityLandingBalloons(player);
	            EntityLanderBase lander = new EntityLander(player);
	            if (!world.isRemote)
	            {
	            	boolean previous = CompatibilityManager.forceLoadChunks((WorldServer) world);
	            	
	            	if(world.provider instanceof ILanderTypeProvider) {
	            		ILanderTypeProvider provider = (ILanderTypeProvider) world.provider;
	            		
	            		switch(provider.getLanderType()) {
	            			case 1:
	            				lander = new EntityLandingBalloons(player);
	            				break;
	            			case 2:
	            				lander = new EntityEntryPodVenus(player);
	            				break;
	            		}
	           			
	            	}
	            	
	            	
	            	lander.forceSpawn = true;
	                world.spawnEntity(lander);
	                CompatibilityManager.forceLoadChunksEnd((WorldServer) world, previous);
	            }
	
	            GCPlayerStats.get(player).setTeleportCooldown(10);
	        }
    	}
    }

	@Override
	public void setupAdventureSpawn(EntityPlayerMP player) {	
	}

}
