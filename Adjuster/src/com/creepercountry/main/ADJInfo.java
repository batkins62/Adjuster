package com.creepercountry.main;

import com.creepercountry.util.Version;


public class ADJInfo
{
	/**
	 * Full Adjuster version
	 */
	public static Version FULL_VERSION;

    /**
     * Sets Adjuster's version
     *
     * @param version
     */
    public static void setVersion(String version)
    {
    	String implementationVersion = ADJPlugin.class.getPackage().getImplementationVersion();
    	FULL_VERSION = new Version(version + " " + implementationVersion);
    }
}