package codes.rusty.chatapi.components;

import codes.rusty.chatapi.util.ConstructorWrapper;
import codes.rusty.chatapi.util.MethodWrapper;
import codes.rusty.chatapi.util.ReflectionUtil;

/**
 * ChatAPI utility {@link ChatComponent} for parsing legacy text.
 * 
 * @author Rustywolf
 */
public class RawTextChatComponent extends ChatComponent {

    private static final Class clazzIChatBaseComponent = ReflectionUtil.getNMSClass("IChatBaseComponent");
    private static final ConstructorWrapper constructor = ReflectionUtil.getNMSConstructor("ChatComponentText").withArgs(String.class);
    private static final MethodWrapper addSibling = ReflectionUtil.getNMSMethod("ChatComponentText", "addSibling", clazzIChatBaseComponent);
    private static final MethodWrapper fromString = ReflectionUtil.getOBCMethod("CraftChatMessage", "fromString", String.class, Boolean.class);
    
    private final String text;
    
    /**
     * Creates a {@link RawTextChatComponent} to handle the legacy text provided.
     * Takes the lines passed, parse them as legacy text and then assigns the child components under this component.
     * Each line passed is considered a new line.
     * 
     * @param lines the lines to be converted to {@link ChatComponent}s
     */
    public RawTextChatComponent(String... lines) {
        this.text = buildString(lines);
    }

    private String buildString(String... lines) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < lines.length; i++) {
            builder.append(lines[i]);
            if (i < lines.length - 1) {
                builder.append("\n");
            }
        }
        
        return builder.toString();
    }
    
    @Override
    protected Object compile() {
        Object base = constructor.construct("");
        for (Object component : (Object[]) fromString.invoke(null, text, true)) {
            addSibling.invoke(base, component);
        }
        
        return base;
    }
    
}
