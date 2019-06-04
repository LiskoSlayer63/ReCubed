/**
 * This class was created by <Vazkii>. It's distributed as
 * part of the ReCubed Mod.
 *
 * ReCubed is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 *
 * File Created @ [Dec 13, 2013, 5:37:44 PM (GMT)]
 */
package vazkii.recubed.client.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.resources.I18n;
import net.minecraftforge.fml.client.GuiScrollingList;
import vazkii.recubed.api.ReCubedAPI;
import vazkii.recubed.api.internal.Category;
import vazkii.recubed.api.internal.ClientData;

public class GuiCategorySlot extends GuiScrollingList {

	GuiCategoryList parent;

	public GuiCategorySlot(GuiCategoryList parent) {
		super(Minecraft.getMinecraft(), 125, parent.guiHeight, parent.y + 16, parent.y + parent.guiHeight - 16, parent.x, 16, Minecraft.getMinecraft().currentScreen.width, Minecraft.getMinecraft().currentScreen.height);
		this.parent = parent;
	}

	@Override
	protected int getSize() {
		return parent.indexes.size();
	}

	@Override
	protected int getContentHeight() {
		return getSize() * 16;
	}

	@Override
	protected void elementClicked(int i, boolean flag) {
		parent.selectCategory(i);
	}

	@Override
	protected boolean isSelected(int i) {
		return parent.isCategorySelected(i);
	}

	@Override
	protected void drawBackground() {
		// NO-OP
	}

	@Override
	protected void drawSlot(int i, int j, int k, int l, Tessellator tessellator) {
		Minecraft mc = Minecraft.getMinecraft();
		String categoryName = ReCubedAPI.categories.get(parent.indexes.get(i));
		Category category = ClientData.categories.get(categoryName);

		int color = 0xFFFFFF;
		if(category.playerData.get(mc.player.getGameProfile().getName()).getTotalValue() == 0)
			color = 0x777777;

		mc.fontRenderer.drawStringWithShadow(I18n.format(categoryName), j - 110, k + 2, color);
	}
}
