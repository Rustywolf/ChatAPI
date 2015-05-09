package codes.rusty.chatapi.components;

import net.minecraft.server.v1_8_R2.ChatComponentText;
import net.minecraft.server.v1_8_R2.IChatBaseComponent;
import org.bukkit.craftbukkit.v1_8_R2.util.CraftChatMessage;

/**
 * ChatAPI utility {@link ChatComponent} for parsing legacy text.
 * 
 * @author Rustywolf
 */
public class RawTextChatComponent extends ChatComponent {

    private final String text;
    
    /**
     * Creates a {@link RawTextChatComponent} to handle the legacy text provided.
     * Takes the lines passed, parse them as legacy text and then assigns the child components under this component.
     * Each line passed is considered a new line.
     * 
     * @param lines the lines to be converted to {@link ChatComponent}s
     */
    public RawTextChatComponent(String... lines) {
        this.text = buildString(lines);
    }

    private String buildString(String... lines) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < lines.length; i++) {
            builder.append(lines[i]);
            if (i < lines.length - 1) {
                builder.append("\n");
            }
        }
        
        return builder.toString();
    }
    
    @Override
    protected IChatBaseComponent compile() {
        IChatBaseComponent base = new ChatComponentText("");
        for (IChatBaseComponent component : CraftChatMessage.fromString(text, true)) {
            base.addSibling(component);
        }
        
        return base;
    }
    
}
