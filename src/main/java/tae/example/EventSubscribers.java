package tae.example;

import net.minecraft.client.Minecraft;
import net.minecraft.network.IPacket;
import net.minecraft.network.handshake.client.CHandshakePacket;
import net.minecraft.network.login.client.CCustomPayloadLoginPacket;
import net.minecraft.network.login.client.CEncryptionResponsePacket;
import net.minecraft.network.login.client.CLoginStartPacket;
import net.minecraft.network.play.client.CChatMessagePacket;
import net.minecraft.network.play.client.CKeepAlivePacket;
import net.minecraft.network.play.server.SChatPacket;
import net.minecraft.network.status.client.CPingPacket;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import tae.packetevent.PacketEvent;

public class EventSubscribers {
	
	@SubscribeEvent
	public void packetDisplay(PacketEvent event) {
		//Displays every packet class in chat as the are received/sent
		Minecraft.getInstance().player.sendMessage(new StringTextComponent(event.getPacket().getClass().toString()));
	}
	
	@SubscribeEvent
	public void incoming(PacketEvent.Incoming event) {
		//When the server sends a chat packet, display "received chat packet" in chat
		if(event.getPacket() instanceof SChatPacket) {
			Minecraft.getInstance().player.sendMessage(new StringTextComponent("Recieved Chat Packet"));
		}
	}
	
	@SubscribeEvent
	public void outgoing(PacketEvent.Outgoing event) {
		IPacket<?> packet = event.getPacket();
		
		//Only send packets that are necessary to join a server. This makes it so you can join, but cannot interact with anything.
		
		if(!(packet instanceof CHandshakePacket 
				|| packet instanceof CKeepAlivePacket 
				|| packet instanceof CLoginStartPacket
				|| packet instanceof CPingPacket
				|| packet instanceof CCustomPayloadLoginPacket
				|| packet instanceof CEncryptionResponsePacket
				|| packet instanceof CChatMessagePacket)) {
			
			
			event.setCanceled(true);
			
		}
		
		//If the client sends a chat message, it is overriden with a new chat packet saying "overriden"
		
		if(packet instanceof CChatMessagePacket) {
			//String oldMessage = ((CChatMessagePacket) packet).getMessage();
			CChatMessagePacket newPacket = new CChatMessagePacket("Overriden packet");
			event.setPacket(newPacket);
		}
		
	}
	
}
