/**
 * This class was created by <Vazkii>. It's distributed as
 * part of the ReCubed Mod.
 *
 * ReCubed is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 *
 * File Created @ [Dec 13, 2013, 5:21:53 PM (GMT)]
 */
package vazkii.recubed.client.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.ResourceLocation;
import vazkii.recubed.client.core.helper.SafeCallable;
import vazkii.recubed.client.lib.LibResources;
import vazkii.recubed.common.lib.LibMisc;

public class GuiCheckboxButton extends GuiButton {

	static ResourceLocation check = new ResourceLocation(LibMisc.MOD_ID, LibResources.RESOURCE_CHECK);
	SafeCallable<Boolean> isChecked;
	String text;

	public GuiCheckboxButton(int par1, int par2, int par3, String text, SafeCallable<Boolean> isChecked) {
		super(par1, par2, par3, 20, 20, "");
		this.isChecked = isChecked;
		this.text = text;
	}

	@Override
	public void drawButton(Minecraft mc, int mouseX, int  mouseY, float partialTicks) {
		super.drawButton(mc, mouseX, mouseY, partialTicks);
		
		if(isChecked.call()) {
			mc.renderEngine.bindTexture(check);
			
			int xPos = x + 2;
			int yPos = y + 2;
			
			zLevel += 1;
			BufferBuilder wr = Tessellator.getInstance().getBuffer();
	        wr.begin(7, DefaultVertexFormats.POSITION_TEX);
			wr.pos(xPos, yPos + 16, zLevel).tex(0, 1).endVertex();
			wr.pos(xPos + 16, yPos + 16, zLevel).tex(1, 1).endVertex();
			wr.pos(xPos + 16, yPos, zLevel).tex(1, 0).endVertex();
			wr.pos(xPos, yPos, zLevel).tex(0, 0).endVertex();
			Tessellator.getInstance().draw();
			zLevel -= 1;
		}

		mc.fontRenderer.drawStringWithShadow(I18n.format(text), x + 25, y + 7, 0xFFFFFF);
	}
}
