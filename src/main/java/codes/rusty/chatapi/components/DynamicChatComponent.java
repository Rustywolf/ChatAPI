package codes.rusty.chatapi.components;

import codes.rusty.chatapi.util.ComponentConsumer;
import net.minecraft.server.v1_8_R2.ChatComponentText;
import net.minecraft.server.v1_8_R2.IChatBaseComponent;

/**
 * ChatAPI utility {@link ChatComponent} for modifying a ChatAPI message after creation.
 * 
 * @author Rustywolf
 */
public class DynamicChatComponent extends ChatComponent {

    /**
     * Adds the given component at the given index.
     * 
     * @param component the component to add as a child
     * @param index the index to insert the child at
     * @return this component
     * @see ChatComponent#addChild(codes.nova.util.chat.components.ChatComponent)
     */
    public DynamicChatComponent addChild(ChatComponent component, int index) {
        this.children.add(index, component);
        return this;
    }
    
    /**
     * Removes all children from this component.
     * 
     * @return this component
     */
    public DynamicChatComponent clearChildren() {
        this.children.clear();
        return this;
    }
    
    /**
     * Returns whether this component contains children.
     * 
     * @return {@code true} if this component contains children, otherwise {@code false}
     */
    public boolean hasChildren() {
        return this.children.size() > 0;
    }
    
    /**
     * Executes the provided {@link ComponentConsumer}, accepting the child at the provided index.
     * 
     * @param childConsumer the consumer to execute
     * @param index the requested child's index
     * @return this component
     * @see ChatComponent#onLastChild(codes.nova.util.chat.util.ComponentConsumer)
     */
    public DynamicChatComponent onChild(ComponentConsumer childConsumer, int index) {
        childConsumer.accept(getChild(index));
        return this;
    }
    
    /**
     * Sets the child at the given index to the given component.
     * 
     * @param component the component to set as child
     * @param index the index to set component at
     * @return this component
     */
    public DynamicChatComponent setChild(ChatComponent component, int index) {
        this.children.set(index, component);
        return this;
    }
    
    /**
     * Removes child at the given index.
     * 
     * @param index the index to remove from
     * @return this component
     */
    public DynamicChatComponent removeChild(int index) {
        this.children.remove(index);
        return this;
    }
    
    /**
     * Removes the most recent child.
     * 
     * @return this component
     * @see DynamicChatComponent#removeChild(int)
     */
    public DynamicChatComponent removeLastChild() {
        if (this.children.size() <= 0) {
            throw new IllegalStateException("Cannot remove previous child if no child was born!");
        }
        
        this.children.remove(this.children.size() - 1);
        return this;
    }
    
    @Override
    protected IChatBaseComponent compile() {
        return new ChatComponentText("");
    }
    
}
