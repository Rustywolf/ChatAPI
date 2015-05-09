package codes.rusty.chatapi.modifiers;

import net.minecraft.server.v1_8_R2.ChatClickable.EnumClickAction;

public enum ClickAction {
    OPEN_URL(EnumClickAction.OPEN_URL), OPEN_FILE(EnumClickAction.OPEN_FILE), RUN_COMMAND(EnumClickAction.RUN_COMMAND), TWITCH_USER_INFO(EnumClickAction.TWITCH_USER_INFO), SUGGEST_COMMAND(EnumClickAction.SUGGEST_COMMAND), CHANGE_PAGE(EnumClickAction.CHANGE_PAGE);
    
    private final EnumClickAction action;

    private ClickAction(EnumClickAction action) {
        this.action = action;
    }
    
    public EnumClickAction getNMSValue() {
        return action;
    }
}
