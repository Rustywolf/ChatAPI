package codes.rusty.chatapi;

import codes.rusty.chatapi.components.ChatMessage;

/**
 * ChatAPI factory and utility methods.
 * 
 * @author Rustywolf
 */
public class ChatAPI {
    
    /**
     * Returns a new instance of {@link ChatMessage}
     * @return the created {@link ChatMessage}
     */
    public static ChatMessage create() {
        return new ChatMessage();
    }
    
    private ChatAPI(){}
    
}
