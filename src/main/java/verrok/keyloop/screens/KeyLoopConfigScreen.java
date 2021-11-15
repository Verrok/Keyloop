package verrok.keyloop.screens;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.client.gui.widget.list.OptionsRowList;
import net.minecraft.client.settings.BooleanOption;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import verrok.keyloop.config.KeyLoopConfig;

/**
 * @see <a href="https://leo3418.github.io/2020/09/09/forge-mod-config-screen.html#correctly-exit-to-the-parent-screen">Config gui</a>
 */

public final class KeyLoopConfigScreen extends Screen {

    /** Distance from top of the screen to the options row list's top */
    private static final int OPTIONS_LIST_TOP_HEIGHT = 24;
    /** Distance from bottom of the screen to the options row list's bottom */
    private static final int OPTIONS_LIST_BOTTOM_OFFSET = 32;
    /** Height of each item in the options row list */
    private static final int OPTIONS_LIST_ITEM_HEIGHT = 25;

    /** Width of a button */
    private static final int BUTTON_WIDTH = 200;
    /** Height of a button */
    private static final int BUTTON_HEIGHT = 20;
    /** Distance from bottom of the screen to the "Done" button's top */
    private static final int DONE_BUTTON_TOP_OFFSET = 26;

    /** Distance from top of the screen to this GUI's title */
    private static final int TITLE_HEIGHT = 8;

    private final Screen parentScreen;

    private OptionsRowList optionsRowList;

    public KeyLoopConfigScreen(Screen parentScreen) {
        // Use the super class' constructor to set the screen's title
        super(new StringTextComponent("KeyLoop settings"));

        this.parentScreen = parentScreen;
    }

    @Override
    protected void init() {
        // Create the options row list
        // It must be created in this method instead of in the constructor,
        // or it will not be displayed properly
        this.optionsRowList = new OptionsRowList(
                this.minecraft, this.width, this.height,
                OPTIONS_LIST_TOP_HEIGHT,
                this.height - OPTIONS_LIST_BOTTOM_OFFSET,
                OPTIONS_LIST_ITEM_HEIGHT
        );


        this.optionsRowList.addOption(new BooleanOption(
                "Show overlay info",
                // GameSettings argument unused for both getter and setter
                unused -> KeyLoopConfig.textRenderEnabled.get(),
                (unused, newValue) -> KeyLoopConfig.textRenderEnabled.set(newValue)
        ));

        // Add the options row list as this screen's child
        // If this is not done, users cannot click on items in the list
        this.children.add(this.optionsRowList);



        // Add the "Done" button
        this.addButton(new Button(
                (this.width - BUTTON_WIDTH) / 2,
                this.height - DONE_BUTTON_TOP_OFFSET,
                BUTTON_WIDTH, BUTTON_HEIGHT,
                // Text shown on the button
                new TranslationTextComponent("gui.done"),
                // Action performed when the button is pressed
                button -> this.onClose()
        ));
    }

    @Override
    public void render(MatrixStack matrixStack,
                       int mouseX, int mouseY, float partialTicks) {
        // First draw the background of the screen
        this.renderBackground(matrixStack);
        // Draw the title
        drawCenteredString(matrixStack, this.font, this.title.getString(),
                this.width / 2, TITLE_HEIGHT, 0xFFFFFF);

        this.optionsRowList.render(matrixStack, mouseX, mouseY, partialTicks);
        drawCenteredString(matrixStack, this.font, this.title.getString(),
                this.width / 2, TITLE_HEIGHT, 0xFFFFFF);

        // Call the super class' method to complete rendering
        super.render(matrixStack, mouseX, mouseY, partialTicks);
    }

    @Override
    public void onClose() {
        this.minecraft.currentScreen = parentScreen;
    }
}