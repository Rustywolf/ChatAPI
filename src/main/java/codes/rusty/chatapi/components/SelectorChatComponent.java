package codes.rusty.chatapi.components;

import net.minecraft.server.v1_8_R2.ChatComponentSelector;
import net.minecraft.server.v1_8_R2.IChatBaseComponent;

/**
 * ChatAPI representation of NMS ChatComponentSelector.
 * 
 * @author Rustywolf
 */
public class SelectorChatComponent extends ChatComponent {

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
    protected IChatBaseComponent compile() {
        return new ChatComponentSelector(selector);
    }
    
}
