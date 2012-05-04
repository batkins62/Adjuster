package com.creepercountry.util;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.material.MaterialData;

import com.creepercountry.main.ADJInfo;
import com.creepercountry.main.ADJPlugin;

public class BukkitUtils
{
	private static final Logger log = Logger.getLogger("Adjuster");

	public static String materialName(int type)
	{
		final Material mat = Material.getMaterial(type);
		return mat != null ? mat.toString().replace('_', ' ').toLowerCase() : String.valueOf(type);
	}

	public static String materialName(int type, byte rawData)
	{
		final Material mat = Material.getMaterial(type);
		if (mat != null)
		{
			if ((type == 6 || type == 17 || type == 18) && rawData > 0 || type == 35 || type == 43 || type == 44)
			{
				final MaterialData data = mat.getNewData(rawData);
				if (data != null)
				{
					return data.toString().toLowerCase().replace('_', ' ').replaceAll("[^a-z ]", "");
				}
			}
			return mat.toString().replace('_', ' ').toLowerCase();
		}
		return String.valueOf(type);
	}

	/**
	 * Send a message to a CommandSender (can be a player or console). Has
	 * parsing built in for &a colours, as well as `n for new line
	 *
	 * @param player sender to send to
	 * @param msg message to send
	 */
	public static void sendMessage(CommandSender player, String msg)
	{
		if (player != null)
		{
			player.sendMessage(msg.replace('&', '§'));
		}
		// TODO: add in line-length checking, color wrapping etc
	}

	/**
	 * Send an info level log message to console
	 * includes: version 
	 *
	 * @param msg
	 */
	public static void info(String msg)
	{
		final StringBuilder out = new StringBuilder();
		out.append("[Adjuster v" + ADJInfo.FULL_VERSION.toString() + "] ");
		out.append(msg);
		log.log(Level.INFO, out.toString());
	}

	/**
	 * Send a warn level log message to console
	 * includes: version
	 *
	 * @param msg
	 */
	public static void warning(String msg)
	{
		final StringBuilder out = new StringBuilder();
		out.append("[Adjuster v" + ADJInfo.FULL_VERSION.toString() + "] ");
		out.append(msg);
		log.log(Level.WARNING, out.toString());
	}

	/**
	 * Send a warn level stacktrace to console
	 * includes: version
	 *
	 * @param msg
	 * @param ex
	 */
	public static void warning(String msg, Exception ex)
	{
		log.log(Level.WARNING, "[Adjuster v" + ADJInfo.FULL_VERSION.toString() + "] " + msg + ":", ex);
	}

	/**
	 * Send a severe level log message to console
	 * includes: version
	 *
	 * @param msg
	 */
	public static void severe(String msg)
	{
		final StringBuilder out = new StringBuilder();
		out.append("[Adjuster v" + ADJInfo.FULL_VERSION.toString() + "] ");
		out.append(msg);
		log.log(Level.SEVERE, out.toString());
	}

	/**
	 * Send a severe level stacktrace to console
	 * includes: version
	 *
	 * @param msg
	 * @param ex
	 */
	public static void severe(String msg, Exception ex)
	{
		log.log(Level.SEVERE, "[Adjuster v" + ADJInfo.FULL_VERSION.toString() + "] " + msg + ":", ex);
	}

	/**
	 * Send an debug message to console if debug is enabled
	 *
	 * @param msg
	 */
	public static void debug(String msg)
	{
		if (ADJPlugin.getInstance().getConfig().getBoolean("system.isDebug"))
		{
			info("DEBUG: " + msg);
		}
	}

	/**
	 * Returns the friendly bridgeName of an entity
	 *
	 * @param entity
	 * @return
	 */
	public static String getEntityName(Entity entity)
	{
		if (entity instanceof Player)
		{
			return ((Player) entity).getName();
		}
		if (entity instanceof TNTPrimed)
		{
			return "TNT";
		}
		if (entity.getClass().getSimpleName().startsWith("Craft"))
		{
			return entity.getClass().getSimpleName().substring(5);
		}
		return "Unknown";
	}

	/**
	 * Returns the distance between two Locations
	 *
	 * @param from
	 * @param to
	 * @return
	 */
	public static double distance(Location from, Location to)
	{
		return Math.sqrt(Math.pow(from.getX() - to.getX(), 2) + Math.pow(from.getY() - to.getY(), 2) + Math.pow(from.getZ() - to.getZ(), 2));
	}
}
