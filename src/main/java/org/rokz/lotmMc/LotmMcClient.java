package org.rokz.lotmMc;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.resource.language.I18n;
import net.minecraft.client.util.InputUtil;
import net.minecraft.util.Identifier;
import org.lwjgl.glfw.GLFW;
import org.rokz.lotmMc.Client.ClientListener;
import org.rokz.lotmMc.Server.AirBulletPacket;
import org.rokz.lotmMc.Server.AirBulletPayload;

public class LotmMcClient implements ClientModInitializer {

    public static KeyBinding.Category category =  KeyBinding.Category.create(Identifier.of(LotmMc.MOD_ID, LotmMc.KEYBIND_CATEGORY));

    public static KeyBinding ability1 = registerKeybind("ability1", GLFW.GLFW_KEY_Z);;
    public static KeyBinding ability2 = registerKeybind("ability2", GLFW.GLFW_KEY_X);;
    public static KeyBinding ability3 = registerKeybind("ability3", GLFW.GLFW_KEY_C);;
    public static KeyBinding ability4 = registerKeybind("ability4", GLFW.GLFW_KEY_V);;
    @Override
    public void onInitializeClient() {
//        PayloadTypeRegistry.playC2S().register(AirBulletPayload.ID, AirBulletPayload.CODEC);
//        AirBulletPacket.register();

        LotmMc.log.info("c i registered");
        ClientListener.initialize();
    }


    public static KeyBinding registerKeybind(String name, int key){
        return KeyBindingHelper.registerKeyBinding(new KeyBinding(
                I18n.translate("key.lotmmc."+name), // The translation key
                InputUtil.Type.KEYSYM, // Type of input (Key or Mouse)
                key, // The default key
                category // The category
        ));
    }
}
