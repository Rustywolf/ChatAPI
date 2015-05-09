package codes.rusty.chatapi.components;

import net.minecraft.server.v1_8_R2.ChatComponentScore;
import net.minecraft.server.v1_8_R2.IChatBaseComponent;

/**
 * ChatAPI representation of NMS ChatComponentScore.
 * 
 * @author Rustywolf
 */
public class ScoreChatComponent extends ChatComponent {

    private final String name;
    private final String objective;
    
    /**
     * Creates a {@link ScoreChatComponent} to handle the provided player and objective provided.
     * 
     * @param name the name of the player whose score to display, or {@code *} for players to see their own (Supports player selectors)
     * @param objective the name of the objective to display
     */
    public ScoreChatComponent(String name, String objective) {
        this.name = name;
        this.objective = objective;
    }

    @Override
    protected IChatBaseComponent compile() {
        return new ChatComponentScore(name, objective);
    }
    
}
