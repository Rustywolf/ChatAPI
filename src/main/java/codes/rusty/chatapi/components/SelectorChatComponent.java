package codes.rusty.chatapi.components;

import codes.rusty.chatapi.util.ConstructorWrapper;
import codes.rusty.chatapi.util.ReflectionUtil;

/**
 * ChatAPI representation of NMS ChatComponentSelector.
 * 
 * @author Rustywolf
 */
public class SelectorChatComponent extends ChatComponent {

    private static final ConstructorWrapper constructor = ReflectionUtil.getNMSConstructor("ChatComponentSelector").withArgs(String.class);
    
    private final String selector;

    /**
     * Creates a new {@link SelectorChatComponent} to handle the selector provided.
     * 
     * @param selector the selector to be used
     */
    public SelectorChatComponent(String selector) {
        this.selector = selector;
    }
    
    @Override
    protected Object compile() {
        return constructor.construct(selector);
    }
    
}
