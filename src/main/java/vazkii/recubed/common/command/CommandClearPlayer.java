/**
 * This class was created by <Vazkii>. It's distributed as
 * part of the ReCubed Mod.
 *
 * ReCubed is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 *
 * File Created @ [Dec 14, 2013, 8:17:41 PM (GMT)]
 */
package vazkii.recubed.common.command;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.TextComponentTranslation;
import vazkii.recubed.api.ReCubedAPI;
import vazkii.recubed.api.internal.Category;
import vazkii.recubed.api.internal.PlayerCategoryData;
import vazkii.recubed.api.internal.ServerData;
import vazkii.recubed.common.core.helper.MiscHelper;

public class CommandClearPlayer extends CommandBase {

	@Override
	public String getName() {
		return "recubed-clrplayer";
	}

	@Override
	public String getUsage(ICommandSender icommandsender) {
		return "recubed-clrplayer <category> <username>";
	}

	@Override
	public void execute(MinecraftServer server, ICommandSender icommandsender, String[] astring) throws CommandException  {
		if(astring.length != 2)
			throw new WrongUsageException(getUsage(icommandsender), (Object[]) astring);

		if(icommandsender instanceof EntityPlayer && !MiscHelper.isPlayerAllowedToUseCommands(icommandsender.getName()))
			throw new CommandException("recubed.commands.no_perms");

		Category category = ServerData.categories.get(ReCubedAPI.shortTerms.get(astring[0]));
		if(category == null)
			throw new CommandException("recubed.commands.no_category");

		category.playerData.put(astring[1], new PlayerCategoryData(astring[1]));
		icommandsender.sendMessage(new TextComponentTranslation("recubed.commands.command_sucessful"));
	}

	@Override
	public int getRequiredPermissionLevel() {
		return 3;
	}

}
