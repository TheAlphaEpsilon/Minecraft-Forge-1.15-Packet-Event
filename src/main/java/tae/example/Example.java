package tae.example;


import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import tae.packetevent.ChannelHandlerInput;

@Mod("packeteventexamplemod")
public class Example
{
    public Example() {   
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientStuff);
    }

    private void doClientStuff(FMLCommonSetupEvent  event) {
    	MinecraftForge.EVENT_BUS.register(new EventSubscribers());
    	MinecraftForge.EVENT_BUS.register(new ChannelHandlerInput());

    }

}
