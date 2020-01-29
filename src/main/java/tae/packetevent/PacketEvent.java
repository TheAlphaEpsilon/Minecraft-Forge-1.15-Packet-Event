package tae.packetevent;

import net.minecraft.network.IPacket;
import net.minecraftforge.eventbus.api.Cancelable;
import net.minecraftforge.eventbus.api.Event;

/*
 * By TheAlphaEpsilon
 * 28JAN2020
 * 
 */


@Cancelable
public class PacketEvent extends Event{

	  private IPacket<?> packet;
	
	  public PacketEvent(IPacket<?> packet) {
		    this.packet = packet;
	  }
	  
	  public IPacket<?> getPacket() {
		    return packet;
	  }
	  
	  public void setPacket(IPacket<?> packet) {
		  this.packet = packet;
	  }
	  
	  public static class Outgoing extends PacketEvent {
		    
		    public Outgoing(IPacket<?> packetIn) {
		    	super(packetIn);
		    }
		    
	  }
	
	  public static class Incoming extends PacketEvent {
		  
		  public Incoming(IPacket<?> packetIn) {
			  super(packetIn);
		  }
		    
	  }
}
