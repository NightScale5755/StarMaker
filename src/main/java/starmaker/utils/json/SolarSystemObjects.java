package starmaker.utils.json;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import starmaker.StarMaker;
import starmaker.utils.json.body.SystemImpl;

public class SolarSystemObjects
{

	@SerializedName("systems")
	@Expose
	private List<SystemImpl> systems = new ArrayList<SystemImpl>();

	public SolarSystemObjects()
	{
	}

	public SolarSystemObjects(List<SystemImpl> systems)
	{
		super();
		this.systems = systems;
	}

	public List<SystemImpl> getSystems()
	{
		return systems;
	}

	public void setSystems(List<SystemImpl> systems)
	{
		this.systems = systems;
	}

	public SolarSystemObjects withSystems(List<SystemImpl> systems)
	{
		this.systems = systems;
		return this;
	}

	public SolarSystemObjects addSystemToList(SystemImpl systemImpl)
	{
		if (this.systems.contains(systemImpl))
		{
			StarMaker.LOG.error(
					"Duplicate SolarSystem Found! Tried to add existing solar system %s to the list when it already existed.",
					systemImpl.getName());
		} else
		{
			this.systems.add(systemImpl);
		}
		return this;
	}
}
