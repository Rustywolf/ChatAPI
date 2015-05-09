package codes.rusty.chatapi.components;

import net.minecraft.server.v1_8_R2.IChatBaseComponent;
import net.minecraft.server.v1_8_R2.ChatMessage;

/**
 * ChatAPI representation of NMS ChatMessage.
 * 
 * @author Rustywolf
 */
public class TranslateChatComponent extends ChatComponent {

    private final String key;
    private final Object[] values;
    
    /**
     * Creates a new {@link TranslateChatComponent} to handle the key and values provided.
     * 
     * @param key the translation key to be used (for example, {@code item.ghastTear.name})
     * @param values the arguments to be passed for the given translation key
     */
    public TranslateChatComponent(String key, Object... values) {
        this.key = key;
        this.values = values;
    }

    @Override
    protected IChatBaseComponent compile() {
        return new ChatMessage(key, (Object[]) values);
    }
    
}
