package codes.rusty.chatapi.components;

import codes.rusty.chatapi.util.ConstructorWrapper;
import codes.rusty.chatapi.util.ReflectionUtil;

/**
 * ChatAPI representation of NMS ChatComponentScore.
 * 
 * @author Rustywolf
 */
public class ScoreChatComponent extends ChatComponent {

    private static final ConstructorWrapper constructor = ReflectionUtil.getNMSConstructor("ChatComponentScore").withArgs(String.class, String.class);
    
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
    protected Object compile() {
        return constructor.construct(name, objective);
    }
    
}
