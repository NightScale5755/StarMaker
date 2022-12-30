package starmaker.utils.json;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import starmaker.StarMaker;
import starmaker.utils.json.celestialimpl.GalaxyImpl;

public class GalaxyObjects {

	@SerializedName("galaxies")
	@Expose
	private List<GalaxyImpl> galaxies = new ArrayList<GalaxyImpl>();
	
	public GalaxyObjects()
	{
	}

	public GalaxyObjects(List<GalaxyImpl> galaxies)
	{
		this.galaxies = galaxies;
	}
	
	public List<GalaxyImpl> getGalaxies()
	{
		return galaxies;
	}

	public void setGalaxies(List<GalaxyImpl> galaxies)
	{
		this.galaxies = galaxies;
	}

	public GalaxyObjects withGalaxies(List<GalaxyImpl> galaxies)
	{
		this.galaxies = galaxies;
		return this;
	}

	public GalaxyObjects addSystemToList(GalaxyImpl galaxiesImpl)
	{
		if (this.galaxies.contains(galaxiesImpl))
		{
			StarMaker.LOG.error(
					"Duplicate Galaxy Found! Tried to add existing galaxy %s to the list when it already existed.",
					galaxiesImpl.getName());
		} else
		{
			this.galaxies.add(galaxiesImpl);
		}
		return this;
	}
}
