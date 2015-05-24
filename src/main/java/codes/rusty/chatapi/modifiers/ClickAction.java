package codes.rusty.chatapi.modifiers;

import codes.rusty.chatapi.util.ReflectionUtil;

public enum ClickAction {
    OPEN_URL(getNMSAction("OPEN_URL")), OPEN_FILE(getNMSAction("OPEN_FILE")), RUN_COMMAND(getNMSAction("RUN_COMMAND")), TWITCH_USER_INFO(getNMSAction("TWITCH_USER_INFO")), SUGGEST_COMMAND(getNMSAction("SUGGEST_COMMAND")), CHANGE_PAGE(getNMSAction("CHANGE_PAGE"));
    
    private final Object action;

    private ClickAction(Object action) {
        this.action = action;
    }
    
    public Object getNMSValue() {
        return action;
    }
    
    public static Object getNMSAction(String name) {
        return ReflectionUtil.getNMSField("EnumClickAction", name).get(null);
    }
}
