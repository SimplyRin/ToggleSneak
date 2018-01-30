package deez.togglesneak.proxy;

import api.player.client.ClientPlayerAPI;
import deez.togglesneak.PlayerBase;
import deez.togglesneak.RenderTextToHUD;
import deez.togglesneak.ToggleSneakEvents;
import deez.togglesneak.ToggleSneakMod;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ClientProxy extends CommonProxy
{
	@Override
	public void registerEvents(FMLPreInitializationEvent event)
	{
		MinecraftForge.EVENT_BUS.register(RenderTextToHUD.instance);
		MinecraftForge.EVENT_BUS.register(ToggleSneakEvents.instance);
	}

	@Override
	public void initMod()
	{
		String text = ToggleSneakMod.ModID + " for Forge - version " + ToggleSneakMod.ModVersion + " Beta!";
		RenderTextToHUD.SetHUDText(ToggleSneakMod.getTSModInstance().isEnabled() ? text + " (Enabled)" : text);
		ClientPlayerAPI.register("ToggleSneak", PlayerBase.class);
	}
}