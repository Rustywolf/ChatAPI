package codes.rusty.chatapi.components;

import net.minecraft.server.v1_8_R2.ChatComponentText;
import net.minecraft.server.v1_8_R2.IChatBaseComponent;

/**
 * ChatAPI representation of NMS ChatComponentText. 
 * 
 * @author Rustywolf
 */
public class TextChatComponent extends ChatComponent {

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
        public IChatBaseComponent compile() {
            return new ChatComponentText(text);
        }
        
    }
