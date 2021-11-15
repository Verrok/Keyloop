package verrok.keyloop;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.ExtensionPoint;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.network.FMLNetworkConstants;
import org.apache.commons.lang3.tuple.Pair;
import verrok.keyloop.events.ClientHandler;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(KeyLoop.MOD_ID)
public class KeyLoop {

    public static final String MOD_ID = "keyloop";

    public KeyLoop() {
        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
        MinecraftForge.EVENT_BUS.register(new ClientHandler());

        ModLoadingContext.get().registerExtensionPoint(ExtensionPoint.DISPLAYTEST, () -> Pair.of(() -> FMLNetworkConstants.IGNORESERVERONLY, (a, b) -> true));
        DistExecutor.safeRunWhenOn(Dist.CLIENT, () -> ClientHandler::new);

    }

    public static String makeDataKey(String data) {
        return KeyLoop.MOD_ID + data;
    }


}
