package codes.rusty.chatapi.modifiers;

import codes.rusty.chatapi.util.ConstructorWrapper;
import codes.rusty.chatapi.util.ReflectionUtil;
import java.util.Objects;

/**
 * ChatAPI representation of the NMS ChatClickable
 */
public class ClickEvent {
    
    private static final Class clazzEnumClickAction = ReflectionUtil.getNMSClass("EnumClickAction");
    private static final ConstructorWrapper constructor = ReflectionUtil.getNMSConstructor("ChatClickable").withArgs(clazzEnumClickAction, String.class);
    
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
    public Object build() {
        return constructor.construct(action.getNMSValue(), text);
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
