package verrok.keyloop.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.NewChatGui;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.command.arguments.IRangeArgument;
import net.minecraft.command.arguments.MessageArgument;
import net.minecraft.command.arguments.TimeArgument;
import net.minecraft.util.text.Color;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.event.ClickEvent;
import net.minecraft.util.text.event.HoverEvent;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
import org.apache.logging.log4j.LogManager;
import verrok.keyloop.ClientHandler;
import verrok.keyloop.KeyLoop;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class KeyLoopCommand {

    private static final Map<String, KeyBinding> KEYBIND_ARRAY = ObfuscationReflectionHelper.getPrivateValue(KeyBinding.class, null, "field_74516_a");

    public KeyLoopCommand(CommandDispatcher<CommandSource> dispatcher) {
        Minecraft mc = Minecraft.getInstance();
        NewChatGui gui = mc.ingameGUI.getChatGUI();

        List<String> keys = KEYBIND_ARRAY.keySet().stream().map(k -> k.replaceFirst("^key\\.", "")).sorted().collect(Collectors.toList());

        dispatcher.register(Commands.literal("keyloop").then(Commands.literal("list").executes((command) -> {
            Style hoverStyle = Style.EMPTY;
            hoverStyle = hoverStyle.setColor(Color.fromTextFormatting(TextFormatting.AQUA));

            Style hoverKeyStyle = Style.EMPTY;
            hoverKeyStyle = hoverKeyStyle.setColor(Color.fromTextFormatting(TextFormatting.GOLD));

            for (String key : keys) {
                StringTextComponent hoverComponent = new StringTextComponent("Click to suggest command\n\n");
                StringTextComponent hoverKeyComponent = new StringTextComponent("/keyloop " + key);
                hoverComponent.setStyle(hoverStyle);
                hoverKeyComponent.setStyle(hoverKeyStyle);
                hoverComponent.appendSibling(hoverKeyComponent);

                StringTextComponent comp = new StringTextComponent(key);
                Style style = comp.getStyle();
                style = style.setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, "/keyloop " + key));
                style = style.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, hoverComponent));

                comp.setStyle(style);
                gui.printChatMessage(comp);
            }

            return 1;
        })));

        for (String key : keys) {
            dispatcher.register(
                    Commands.literal("keyloop")
                            .then(Commands.literal(key)
                                    .then(Commands.argument("delay", TimeArgument.func_218091_a()).executes((commands) -> {
                int delay = IntegerArgumentType.getInteger(commands, "delay");
                mc.player.getPersistentData().putInt(KeyLoop.MOD_ID + "delay", delay);
                mc.player.getPersistentData().putString(KeyLoop.MOD_ID + "keyBind", key);

                return 1;
            }))));

        }


//        for (String key : keys) {
//            dispatcher.register(Commands
//                            .literal("keyloop")
//                            .then(Commands.literal(key))
//                            .then(Commands.argument("delay", IntegerArgumentType.integer(0)))
//                            .executes((command) -> {
//
//                Style hoverStyle = Style.EMPTY;
//                hoverStyle = hoverStyle.setColor(Color.fromTextFormatting(TextFormatting.AQUA));
//
//                Style hoverKeyStyle = Style.EMPTY;
//                hoverKeyStyle = hoverKeyStyle.setColor(Color.fromTextFormatting(TextFormatting.GOLD));
//
//                for (String key : keys) {
//                    StringTextComponent hoverComponent = new StringTextComponent("Click to suggest command\n\n");
//                    StringTextComponent hoverKeyComponent = new StringTextComponent("/keyloop " + key);
//                    hoverComponent.setStyle(hoverStyle);
//                    hoverKeyComponent.setStyle(hoverKeyStyle);
//                    hoverComponent.appendSibling(hoverKeyComponent);
//
//                    StringTextComponent comp = new StringTextComponent(key);
//                    Style style = comp.getStyle();
//                    style = style.setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, "/keyloop " + key));
//                    style = style.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, hoverComponent));
//
//                    comp.setStyle(style);
//                    gui.printChatMessage(comp);
//                }
//
//            }));
//        }



    }

}
