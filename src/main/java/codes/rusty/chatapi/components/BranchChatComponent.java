package codes.rusty.chatapi.components;

import codes.rusty.chatapi.util.ConstructorWrapper;
import codes.rusty.chatapi.util.ReflectionUtil;

/**
 * ChatAPI utility {@link ChatComponent} for containing children.
 * 
 * @author Rustywolf
 */
public class BranchChatComponent extends ChatComponent {

    private static final ConstructorWrapper constructor = ReflectionUtil.getNMSConstructor("ChatComponentText").withArgs(String.class);
    
    public BranchChatComponent() {}
    
    @Override
    protected Object compile() {
        return constructor.construct("");
    }
    
}
