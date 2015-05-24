package codes.rusty.chatapi.modifiers;

import codes.rusty.chatapi.components.ChatComponent;
import codes.rusty.chatapi.components.RawTextChatComponent;
import codes.rusty.chatapi.util.ConstructorWrapper;
import codes.rusty.chatapi.util.ReflectionUtil;
import java.util.Objects;

/**
 * ChatAPI representation of the NMS ChatHoverable
 */
public class HoverEvent {
    
    private static final Class clazzEnumHoverAction = ReflectionUtil.getNMSClass("EnumHoverAction");
    private static final ConstructorWrapper constructor = ReflectionUtil.getNMSConstructor("ChatHoverable").withArgs(clazzEnumHoverAction, String.class);
    
    private final HoverAction action;
    private final ChatComponent text;

    /**
     * Creates a new {@link HoverEvent} with the passed action and text
     * 
     * @param action the form of action to take upon clicking the component
     * @param component the component used within the event
     */
    public HoverEvent(HoverAction action, ChatComponent component) {
        this.action = action;
        this.text = component;
    }
    
    /**
     * Creates a new {@link HoverEvent} with the passed action and text
     * 
     * @param action the form of action to take upon clicking the component
     * @param lines the lines used within the event
     */
    public HoverEvent(HoverAction action, String... lines) {
        this.action = action;
        this.text = new RawTextChatComponent(lines);
    }
    
    /**
     * Creates an NMS representation of this {@link HoverEvent}
     * 
     * @return an NMS representation of this {@link HoverEvent}
     */
    public Object build() {
        return constructor.construct(action.getNMSValue(), text.build());
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + Objects.hashCode(this.action);
        hash = 47 * hash + Objects.hashCode(this.text);
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
        final HoverEvent other = (HoverEvent) obj;
        if (this.action != other.action) {
            return false;
        }
        if (!Objects.equals(this.text, other.text)) {
            return false;
        }
        return true;
    }
}
