package net.dark_roleplay.travellers_map2.objects.huds.hud;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.dark_roleplay.travellers_map2.configs.client.HudConfig;
import net.minecraft.client.gui.AbstractGui;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public abstract class Hud extends AbstractGui {

    public final Map<String, HudStyle> STYLES = new HashMap<>();

    private HudStyle selectedStyle = null;
    private final HudStyle fallbackStyle;

    protected int wWidth;
    protected int wHeight;

    protected HudConfig config;
    private String unlocalizedName;

    protected Hud(HudConfig config, String unlocalizedName, HudStyle fallbackStyle){
        this.config = config;
        this.unlocalizedName = unlocalizedName;
        this.fallbackStyle = fallbackStyle;
    }

    public void refreshStyles(Collection<HudStyle> styles){
        selectedStyle = null;
        STYLES.clear();
        STYLES.put("Default", fallbackStyle);

        for(HudStyle style : styles){
            STYLES.put(style.getStyleName(), style);
        }

        HudStyle configStyle = STYLES.get(this.config.STYLE.get());
        if(configStyle != null)
            this.selectedStyle = configStyle;
    }

    public HudStyle getStyle(){
        return selectedStyle == null ? fallbackStyle : selectedStyle;
    }

    public void setStyle(HudStyle style){
        selectedStyle = style;
        this.config.STYLE.set(style.getStyleName());
    }

    public String getUnlocalizedName() {
        return unlocalizedName;
    }

    public void setWindowSize(int width, int height) {
        this.wWidth = width;
        this.wHeight = height;
    }

    public abstract void render(MatrixStack matrix, int mouseX, int mouseY, float delta);
}

