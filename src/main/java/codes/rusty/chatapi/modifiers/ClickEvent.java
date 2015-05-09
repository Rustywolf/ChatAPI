package codes.rusty.chatapi.modifiers;

import java.util.Objects;
import net.minecraft.server.v1_8_R2.ChatClickable;

/**
 * ChatAPI representation of the NMS ChatClickable
 */
public class ClickEvent {
    
    private final ClickAction action;
    private final String text;
    
    /**
     * Creates a new instance of {@link ClickEvent} with the specified action and text
     * 
     * @param action the form of action to take upon clicking the component
     * @param text the text passed upon the action being completed
     */
    public ClickEvent(ClickAction action, String text) {
        this.action = action;
        this.text = text;
    }
        
    /**
     * Creates an NMS representation of this {@link ClickEvent}
     * 
     * @return an NMS representation of this {@link ClickEvent}
     */
    public ChatClickable build() {
        return new ChatClickable(action.getNMSValue(), text);
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + Objects.hashCode(this.action);
        hash = 53 * hash + Objects.hashCode(this.text);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ClickEvent other = (ClickEvent) obj;
        if (this.action != other.action) {
            return false;
        }
        if (!Objects.equals(this.text, other.text)) {
            return false;
        }
        return true;
    }
}
