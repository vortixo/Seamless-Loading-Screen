package com.minenash.seamless_loading_screen.neoforge;

import com.minenash.seamless_loading_screen.config.SeamlessLoadingScreenConfig;
import net.minecraft.client.gui.screen.Screen;
import net.neoforged.fml.ModContainer;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;

public class ConfigScreenFactory implements IConfigScreenFactory {

    @Override
    public Screen createScreen(ModContainer container, Screen parent) {
        return SeamlessLoadingScreenConfig.getInstance().generateScreen(parent);
    }
}
