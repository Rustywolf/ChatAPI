package codes.rusty.chatapi.components;

import codes.rusty.chatapi.util.ConstructorWrapper;
import codes.rusty.chatapi.util.MethodWrapper;
import codes.rusty.chatapi.util.ReflectionUtil;

/**
 * ChatAPI utility {@link ChatComponent} for accepting an NMS IChatBaseComponent.
 * 
 * @author Rusty
 */
public class NMSChatComponent extends ChatComponent {
    
    private static final Class clazzIChatBaseComponent = ReflectionUtil.getNMSClass("IChatBaseComponent");
    private static final ConstructorWrapper constructor = ReflectionUtil.getNMSConstructor("ChatComponentText").withArgs(String.class);
    private static final MethodWrapper addSibling = ReflectionUtil.getNMSMethod("ChatComponentText", "addSibling", clazzIChatBaseComponent);
    
    private final Object component;
    
    /**
     * Creates a {@link NMSChatComponent} with the provided component as a child.
     * The provided component is added as a child to prevent modification from the API.
     * I promise no behavior with this class.
     * 
     * @param component the NMS component to display
     */
    public NMSChatComponent(Object component) {
        this.component = component;
    }

    @Override
    protected Object compile() {
        Object base = constructor.construct("");
        addSibling.invoke(base, component);
        return base;
    }
    
}
