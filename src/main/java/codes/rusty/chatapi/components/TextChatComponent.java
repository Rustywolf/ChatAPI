package codes.rusty.chatapi.components;

import codes.rusty.chatapi.util.ConstructorWrapper;
import codes.rusty.chatapi.util.ReflectionUtil;

/**
 * ChatAPI representation of NMS ChatComponentText. 
 * 
 * @author Rustywolf
 */
public class TextChatComponent extends ChatComponent {

    private static final ConstructorWrapper constructor = ReflectionUtil.getNMSConstructor("ChatComponentText").withArgs(String.class);
    
    public final String text;

    /**
     * Creates a {@link TextChatComponent} to handle the text provided.
     * 
     * @param text the text to be displayed
     */
    public TextChatComponent(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    @Override
    public Object compile() {
        return constructor.construct(text);
    }
        
    }
