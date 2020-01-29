package tae.packetevent;

/*
 * By TheAlphaEpsilon
 * 28JAN2020
 * 
 */

import io.netty.channel.ChannelDuplexHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;
import net.minecraft.network.IPacket;
import net.minecraftforge.common.MinecraftForge;

public class PacketListener extends ChannelDuplexHandler {
	
	@Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		
		boolean get = true;
				
		if(msg instanceof IPacket) {
			//if msg is packet, post new packet event
			PacketEvent.Incoming inPacket = new PacketEvent.Incoming((IPacket<?>)msg);
			MinecraftForge.EVENT_BUS.post(inPacket);
			//if the packet is cancelled, dont process it
			if(inPacket.isCanceled()) {
				get = false;
			}
			//if event changes packet, update
			msg = inPacket.getPacket();
		}
		//whether or not to process the packet
        if(get) super.channelRead(ctx, msg);
    }
	
	@Override
	public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
		
		boolean send = true;
	 		
		if(msg instanceof IPacket) {
			//if msg is packet, post new packet event
			PacketEvent.Outgoing outPacket = new PacketEvent.Outgoing((IPacket<?>)msg);
			MinecraftForge.EVENT_BUS.post(outPacket);
			//if the packet is cancelled, dont process it
			if(outPacket.isCanceled()) {
				send = false;
			}
			//if event changes packet, update
			msg = outPacket.getPacket();
		}
		//whether or not to send the packet
		if(send) super.write(ctx, msg, promise);
	}
}
