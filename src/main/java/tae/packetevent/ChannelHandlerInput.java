package tae.packetevent;

/*
 * By TheAlphaEpsilon
 * 28JAN2020
 * 
 */

import io.netty.channel.ChannelPipeline;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.ClientPlayerNetworkEvent.LoggedInEvent;
import net.minecraftforge.client.event.ClientPlayerNetworkEvent.LoggedOutEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class ChannelHandlerInput {
	
	
	public static Minecraft mc = Minecraft.getInstance();

	public static boolean firstConnection = true;
	
	@SubscribeEvent
	public void init(LoggedInEvent event)  {
		
		if(firstConnection) {
							
			firstConnection = false;
						
			ChannelPipeline pipeline = event.getNetworkManager().channel().pipeline();
						
			pipeline.addBefore("packet_handler","listener", new PacketListener());
						
		}
	}
	
	@SubscribeEvent (priority = EventPriority.HIGHEST)
	public void onDisconnect(LoggedOutEvent event) {
		firstConnection = true;
	}
	
}
