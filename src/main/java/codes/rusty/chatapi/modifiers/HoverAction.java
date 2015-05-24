package codes.rusty.chatapi.modifiers;

import codes.rusty.chatapi.util.ReflectionUtil;

public enum HoverAction {
    SHOW_TEXT(getNMSAction("SHOW_TEXT")), SHOW_ACHIEVEMENT(getNMSAction("SHOW_ACHIEVEMENT")), SHOW_ITEM(getNMSAction("SHOW_ITEM")), SHOW_ENTITY(getNMSAction("SHOW_ENTITY"));
    
    private final Object action;

    private HoverAction(Object action) {
        this.action = action;
    }
    
    public Object getNMSValue() {
        return action;
    }
    
    public static Object getNMSAction(String name) {
        return ReflectionUtil.getNMSField("EnumHoverAction", name).get(null);
    }
}
