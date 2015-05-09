package codes.rusty.chatapi.modifiers;

import net.minecraft.server.v1_8_R2.ChatHoverable.EnumHoverAction;

public enum HoverAction {
    SHOW_TEXT(EnumHoverAction.SHOW_TEXT), SHOW_ACHIEVEMENT(EnumHoverAction.SHOW_ACHIEVEMENT), SHOW_ITEM(EnumHoverAction.SHOW_ITEM), SHOW_ENTITY(EnumHoverAction.SHOW_ENTITY);
    
    private final EnumHoverAction action;

    private HoverAction(EnumHoverAction action) {
        this.action = action;
    }
    
    public EnumHoverAction getNMSValue() {
        return action;
    }
}
