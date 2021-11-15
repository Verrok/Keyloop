package verrok.keyloop;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import joptsimple.internal.Strings;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.NewChatGui;
import net.minecraft.client.gui.screen.ChatScreen;
import net.minecraft.client.gui.screen.MainMenuScreen;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.client.event.ClientChatEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
import org.apache.logging.log4j.LogManager;

import static net.minecraftforge.eventbus.api.EventPriority.HIGHEST;
import static net.minecraftforge.eventbus.api.EventPriority.LOWEST;


public class ClientHandler
{
    private static final Map<String, KeyBinding> KEYBIND_ARRAY = ObfuscationReflectionHelper.getPrivateValue(KeyBinding.class, null, "field_74516_a");
    private final Minecraft mc;

    private int i;

    public ClientHandler()
    {
        this.mc = Minecraft.getInstance();
        if (KEYBIND_ARRAY == null)
        {
            RuntimeException e = new NullPointerException("KEYBIND_ARRAY was null.");
            throw e;
        }
    }


    @SubscribeEvent()
    public void textRenderEvent(RenderGameOverlayEvent.Text event)
    {
        if (mc.world == null) return;
        if (mc.player == null) return;

        CompoundNBT data = mc.player.getPersistentData();

        int delay = data.getInt(KeyLoop.MOD_ID + "delay");
        String stringKeyBind = data.getString(KeyLoop.MOD_ID + "keyBind");

        if (Strings.isNullOrEmpty(stringKeyBind)) return;

        if (mc.currentScreen instanceof MainMenuScreen || mc.currentScreen instanceof ChatScreen)
        {
            event.getLeft().add("KeyLoop paused.");
            event.getLeft().add("If you want to AFK, use ALT+TAB.");
            event.getLeft().add("If your game pausing after ALT+TAB, use F3+P");

            return;
        }
        event.getLeft().add("KeyLoop active: " + stringKeyBind);

        if (delay == 0) {
            event.getLeft().add("Holding");
        } else {
            event.getLeft().add("Delay: " + i + " / " + delay);
        }

    }

    @SubscribeEvent(priority = HIGHEST)
    public void tickEvent(TickEvent.WorldTickEvent event)
    {
        if (event.phase != TickEvent.Phase.START) return;
        if (mc.currentScreen instanceof MainMenuScreen || mc.currentScreen instanceof ChatScreen) return;

        if (mc.world == null) return;
        if (mc.player == null) return;

        CompoundNBT data = mc.player.getPersistentData();

        int delay = data.getInt(KeyLoop.MOD_ID + "delay");
        String stringKeyBind = data.getString(KeyLoop.MOD_ID + "keyBind");

        if (Strings.isNullOrEmpty(stringKeyBind)) return;
        KeyBinding keyBinding = KEYBIND_ARRAY.get("key." + stringKeyBind);

        if (delay == 0) {
            KeyBinding.setKeyBindState(keyBinding.getKey(), true);
            KeyBinding.onTick(keyBinding.getKey());
            return;
        }

        if (i++ < delay) return;
        i = 0;

        KeyBinding.onTick(keyBinding.getKey());

    }

}
