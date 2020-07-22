/*
 * Copyright (c) 2018, SomeoneWithAnInternetConnection
 * Copyright (c) 2018, oplosthee <https://github.com/oplosthee>
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice, this
 *    list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR
 * ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package net.runelite.client.plugins.powerskiller;

import java.time.Instant;
import java.util.function.Consumer;
import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;
import net.runelite.client.config.ConfigSection;
import net.runelite.client.config.ConfigTitleSection;
import net.runelite.client.config.Range;
import net.runelite.client.config.Title;

@ConfigGroup("PowerSkiller")
public interface PowerSkillerConfiguration extends Config
{

	@ConfigSection(
		keyName = "delayConfig",
		name = "Sleep Delay Configuration",
		description = "Configure how the bot handles sleep delays",
		position = 2
	)
	default boolean delayConfig()
	{
		return false;
	}

	@Range(
		min = 0,
		max = 550
	)
	@ConfigItem(
		keyName = "sleepMin",
		name = "Sleep Min",
		description = "",
		position = 3,
		section = "delayConfig"
	)
	default int sleepMin()
	{
		return 60;
	}

	@Range(
		min = 0,
		max = 550
	)
	@ConfigItem(
		keyName = "sleepMax",
		name = "Sleep Max",
		description = "",
		position = 4,
		section = "delayConfig"
	)
	default int sleepMax()
	{
		return 350;
	}

	@Range(
		min = 0,
		max = 550
	)
	@ConfigItem(
		keyName = "sleepTarget",
		name = "Sleep Target",
		description = "",
		position = 5,
		section = "delayConfig"
	)
	default int sleepTarget()
	{
		return 100;
	}

	@Range(
		min = 0,
		max = 550
	)
	@ConfigItem(
		keyName = "sleepDeviation",
		name = "Sleep Deviation",
		description = "",
		position = 6,
		section = "delayConfig"
	)
	default int sleepDeviation()
	{
		return 10;
	}

	@ConfigItem(
		keyName = "sleepWeightedDistribution",
		name = "Sleep Weighted Distribution",
		description = "Shifts the random distribution towards the lower end at the target, otherwise it will be an even distribution",
		position = 7,
		section = "delayConfig"
	)
	default boolean sleepWeightedDistribution()
	{
		return false;
	}

	@ConfigSection(
		keyName = "delayTickConfig",
		name = "Game Tick Configuration",
		description = "Configure how the bot handles game tick delays, 1 game tick equates to roughly 600ms",
		position = 8
	)
	default boolean delayTickConfig()
	{
		return false;
	}

	@Range(
		min = 0,
		max = 10
	)
	@ConfigItem(
		keyName = "tickDelayMin",
		name = "Game Tick Min",
		description = "",
		position = 9,
		section = "delayTickConfig"
	)
	default int tickDelayMin()
	{
		return 1;
	}

	@Range(
		min = 0,
		max = 10
	)
	@ConfigItem(
		keyName = "tickDelayMax",
		name = "Game Tick Max",
		description = "",
		position = 10,
		section = "delayTickConfig"
	)
	default int tickDelayMax()
	{
		return 3;
	}

	@Range(
		min = 0,
		max = 10
	)
	@ConfigItem(
		keyName = "tickDelayTarget",
		name = "Game Tick Target",
		description = "",
		position = 11,
		section = "delayTickConfig"
	)
	default int tickDelayTarget()
	{
		return 2;
	}

	@Range(
		min = 0,
		max = 10
	)
	@ConfigItem(
		keyName = "tickDelayDeviation",
		name = "Game Tick Deviation",
		description = "",
		position = 12,
		section = "delayTickConfig"
	)
	default int tickDelayDeviation()
	{
		return 1;
	}

	@ConfigItem(
		keyName = "tickDelayWeightedDistribution",
		name = "Game Tick Weighted Distribution",
		description = "Shifts the random distribution towards the lower end at the target, otherwise it will be an even distribution",
		position = 13,
		section = "delayTickConfig"
	)
	default boolean tickDelayWeightedDistribution()
	{
		return false;
	}

	@ConfigTitleSection(
		keyName = "instructionsTitle",
		name = "Instructions",
		description = "",
		position = 16
	)
	default Title instructionsTitle()
	{
		return new Title();
	}

	@ConfigItem(
		keyName = "instructions",
		name = "",
		description = "Instructions. Don't enter anything into this field",
		position = 20,
		titleSection = "instructionsTitle"
	)
	default String instructions()
	{
		return "Use Developer Tools to determine the Type and ID of the object you want to power-skill on." +
			"Typically in-game objects that have blue hover text are Game Objects (trees, rocks etc.) and objects that have yellow text are NPCs (fishing spots etc.)";
	}

	@ConfigTitleSection(
		keyName = "skillerTitle",
		name = "Power Skiller Configuration",
		description = "",
		position = 60
	)
	default Title skillerTitle()
	{
		return new Title();
	}

	@ConfigItem(
		keyName = "type",
		name = "Object Type",
		description = "Type of Object. Typically in-game objects that have blue hover text are Game Objects (trees, rocks etc.) " +
			"and objects that have yellow text are NPCs (e.g. fishing spots). Use Developer Tools to determine Object Type and ID.",
		position = 70
	)
	default PowerSkillerType type()
	{
		return PowerSkillerType.GAME_OBJECT;
	}

	@ConfigItem(
		keyName = "objectIds",
		name = "IDs to power-skill",
		description = "Separate with comma",
		position = 80
	)
	default String objectIds()
	{
		return "";
	}

	@ConfigItem(
		keyName = "dropInventory",
		name = "Drop entire inventory",
		description = "Enable to drop your entire inventory",
		position = 90
	)
	default boolean dropInventory()
	{
		return false;
	}

	@ConfigItem(
		keyName = "requiredItems",
		name = "Required inventory item IDs",
		description = "Separate with comma. Bot will stop if required items are not in inventory, e.g. fishing bait. Leave at 0 if there are none.",
		position = 100,
		hide = "dropInventory"
	)
	default String requiredItems()
	{
		return "";
	}

	@ConfigItem(
		keyName = "items",
		name = "Item IDs to drop/not drop",
		description = "Separate with comma, enable below option to not drop these IDs.",
		position = 110,
		hide = "dropInventory"
	)
	default String items()
	{
		return "";
	}

	@ConfigItem(
		keyName = "dropExcept",
		name = "Drop all except above IDs",
		description = "Enable to drop all items except the given IDs",
		position = 120,
		hide = "dropInventory"
	)
	default boolean dropExcept()
	{
		return true;
	}

	@Range(
		min = 1,
		max = 60
	)
	@ConfigItem(
		keyName = "locationRadius",
		name = "Location Radius",
		description = "Radius to search for GameObjects.",
		position = 130
	)
	default int locationRadius()
	{
		return 10;
	}

	@ConfigItem(
		keyName = "enableUI",
		name = "Enable UI",
		description = "Enable to turn on in game UI",
		position = 140
	)
	default boolean enableUI()
	{
		return true;
	}

	@ConfigItem(
		keyName = "startButton",
		name = "Start/Stop",
		description = "Test button that changes variable value",
		position = 150
	)
	default Consumer<PowerSkillerPlugin> startButton()
	{
		return (plugin) ->
		{
			if (plugin.pluginManager.isPluginEnabled(plugin))
			{
				plugin.startPowerSkiller = !plugin.startPowerSkiller;
				plugin.botTimer = (plugin.startPowerSkiller) ? Instant.now() : null;
				plugin.setLocation();
			}
			else
			{
				plugin.startPowerSkiller = false;
			}
		};
	}
}
