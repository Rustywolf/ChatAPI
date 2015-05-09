package codes.rusty.chatapi.components;

import net.minecraft.server.v1_8_R2.ChatComponentText;
import net.minecraft.server.v1_8_R2.IChatBaseComponent;

/**
 * ChatAPI utility {@link ChatComponent} for accepting an NMS IChatBaseComponent.
 * 
 * @author Rusty
 */
public class NMSChatComponent extends ChatComponent {
    
    private final IChatBaseComponent component;
    
    /**
     * Creates a {@link NMSChatComponent} with the provided component as a child.
     * The provided component is added as a child to prevent modification from the API.
     * I promise no behavior with this class.
     * 
     * @param component the NMS component to display
     */
    public NMSChatComponent(IChatBaseComponent component) {
        this.component = component;
    }

    @Override
    protected IChatBaseComponent compile() {
        return new ChatComponentText("").addSibling(component);
    }
    
}
