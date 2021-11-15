package verrok.keyloop.events;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.server.command.ConfigCommand;
import verrok.keyloop.KeyLoop;
import verrok.keyloop.commands.KeyLoopCommand;

@Mod.EventBusSubscriber(modid = KeyLoop.MOD_ID, value = Dist.CLIENT)
public class ModEvents {

    @SubscribeEvent
    public static void onCommandRegister(RegisterCommandsEvent event) {
        new KeyLoopCommand(event.getDispatcher());
        ConfigCommand.register(event.getDispatcher());
    }

}
