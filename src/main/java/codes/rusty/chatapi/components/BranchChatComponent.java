package codes.rusty.chatapi.components;

import net.minecraft.server.v1_8_R2.ChatComponentText;
import net.minecraft.server.v1_8_R2.IChatBaseComponent;

/**
 * ChatAPI utility {@link ChatComponent} for containing children.
 * 
 * @author Rustywolf
 */
public class BranchChatComponent extends ChatComponent {

    public BranchChatComponent() {}
    
    @Override
    protected IChatBaseComponent compile() {
        return new ChatComponentText("");
    }
    
}
