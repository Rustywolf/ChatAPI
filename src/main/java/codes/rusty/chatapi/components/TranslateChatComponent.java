package codes.rusty.chatapi.components;

import codes.rusty.chatapi.util.ConstructorWrapper;
import codes.rusty.chatapi.util.ReflectionUtil;

/**
 * ChatAPI representation of NMS ChatMessage.
 * 
 * @author Rustywolf
 */
public class TranslateChatComponent extends ChatComponent {

    private static final ConstructorWrapper constructor = ReflectionUtil.getNMSConstructor("ChatMessage").withArgs(String.class, Object[].class);
    
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
    protected Object compile() {
        return constructor.construct(key, (Object) values);
    }
    
}
