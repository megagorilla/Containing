package com.github.megagorilla.containing;

import com.jme3.app.SimpleApplication;
import com.jme3.system.JmeContext;

public class ContainingServer extends SimpleApplication 
{
	private long startTime = System.currentTimeMillis();
	float time = 0;
	public static void main(String[] args)
	{
		ContainingServer app = new ContainingServer();
		app.start(JmeContext.Type.Headless);
	}
	
	@Override
	public void simpleInitApp() 
	{
	}
	
	@Override
	public void simpleUpdate(float tpf)
	{
		time++;
		if(time == 60)
			System.out.println((System.currentTimeMillis() - startTime));
	}
	

	public class Date
	{
		
	}
}


