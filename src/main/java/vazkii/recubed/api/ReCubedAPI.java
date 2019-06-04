/**
 * This class was created by <Vazkii>. It's distributed as
 * part of the ReCubed Mod.
 *
 * ReCubed is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 *
 * File Created @ [Dec 13, 2013, 2:36:06 PM (GMT)]
 */
package vazkii.recubed.api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.common.util.FakePlayer;
import vazkii.recubed.api.internal.Category;
import vazkii.recubed.api.internal.PlayerCategoryData;
import vazkii.recubed.api.internal.ServerData;

public final class ReCubedAPI {

	public static final List<String> categories = new ArrayList<String>();
	public static final Map<String, String> shortTerms = new HashMap<String, String>();

	public static void registerCategory(String category, String shortTerm) {
		if(!categories.contains(category)) {
			categories.add(category);
			shortTerms.put(shortTerm, category);
		}
	}

	public static int getValueFromCategory(String category, String player, String tag) {
		Category category_ = ServerData.categories.get(category);
		PlayerCategoryData data = category_.playerData.get(player);
		return data.stats.containsKey(tag) ? data.stats.get(tag) : 0;
	}

	public static void addValueToCategory(String category, String player, String tag, int value) {
		int val = getValueFromCategory(category, player, tag);
		setValueToCategory(category, player, tag, val + value);
	}

	public static void sbtValueFromCategory(String category, String player, String tag, int value) {
		int val = getValueFromCategory(category, player, tag);
		setValueToCategory(category, player, tag, Math.max(0, val - value));
	}

	public static void setValueToCategory(String category, String player, String tag, int value) {
		Category category_ = ServerData.categories.get(category);

		if(category != null && !category_.isFrozen) {
			PlayerCategoryData data = category_.playerData.get(player);
			data.stats.put(tag, value);
		}
	}

	public static boolean validatePlayer(EntityPlayer player) {
		return !player.world.isRemote && !(player instanceof FakePlayer);
	}

}
