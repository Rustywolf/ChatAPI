package codes.rusty.chatapi.components;

import codes.rusty.chatapi.util.ComponentConsumer;
import codes.rusty.chatapi.modifiers.ChatModifier;
import codes.rusty.chatapi.modifiers.ClickAction;
import codes.rusty.chatapi.modifiers.ClickEvent;
import codes.rusty.chatapi.modifiers.HoverAction;
import codes.rusty.chatapi.modifiers.HoverEvent;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.server.v1_8_R2.IChatBaseComponent;
import org.bukkit.ChatColor;

/**
 * ChatAPI base class for the ChatAPI component implementation.
 * Handles the creation/addition and building of child components.
 * 
 * @author Rustywolf
 */
public abstract class ChatComponent {
    
    protected ChatModifier modifier = new ChatModifier();
    protected List<ChatComponent> children = new ArrayList<>();

    public ChatComponent() {}
    
    /**
     * Creates and returns an IChatBaseComponent representing this component.
     * All children of this component are added as siblings on the IChatBaseComponent
     * after being built themselves. 
     * <p>
     * 
     * @return created IChatBaseComponent 
     * @see #build(ChatModifier) 
     */
    public final IChatBaseComponent build() {
        return build(null);
    }
    
    protected final IChatBaseComponent build(ChatModifier parentModifier) {
        IChatBaseComponent component = compile();
        
        ChatModifier inherited = (parentModifier == null) ? modifier : ChatModifier.inherit(modifier, parentModifier);
        component.setChatModifier(inherited.isReset() ? null : inherited.build());
        
        for (ChatComponent child : children) {
            component.addSibling(child.build(inherited));
        }
        
        return component;
    }
    
    /**
     * Gets the {@link ChatModifier} for this component.
     * @return the {@link ChatModifier} for this component
     */
    public ChatModifier getModifier() {
        return modifier;
    }

    /**
     * Sets the {@link ChatModifier} for this component.
     * 
     * @param modifier the new {@link ChatModifier}
     * @return this component
     */
    public ChatComponent setModifier(ChatModifier modifier) {
        this.modifier = modifier;
        return this;
    }

    /**
     * Adds the passed @{link ChatComponent} as a child of this component.
     * 
     * @param component the {@link ChatComponent} to be added as a child
     * @return this component
     */
    public ChatComponent addChild(ChatComponent component) {
        this.children.add(component);
        return this;
    }
    
    /**
     * Creates and adds a new {@link BranchChatComponent} as a child of this component.
     * 
     * @return this component 
     * @see BranchChatComponent#BranchChatComponent() 
     */
    public ChatComponent addBranch() {
        this.addChild(new BranchChatComponent());
        return this;
    }
    
    /**
     * Creates and adds a new {@link RawTextChatComponent} as a child of this component.
     * 
     * @param lines the lines to display
     * @return this component
     * @see RawTextChatComponent#RawTextChatComponent(java.lang.String...) 
     */
    public ChatComponent addRawText(String... lines) {
        this.addChild(new RawTextChatComponent(lines));
        return this;
    }
    
    /**
     * Creates and adds a new {@link ScoreChatComponent} as a child of this component.
     * 
     * @param name the name of the player whose score to display, or {@code *} for players to see their own (Supports player selectors)
     * @param objective the name of the objective to display
     * @return this component
     * @see ScoreChatComponent#ScoreChatComponent(java.lang.String, java.lang.String) 
     */
    public ChatComponent addScore(String name, String objective) {
        this.addChild(new ScoreChatComponent(name, objective));
        return this;
    }
    
    /**
     * Creates and adds a new {@link SelectorChatComponent} as a child of this component.
     * 
     * @param text the selector to be used
     * @return this component
     * @see SelectorChatComponent#SelectorChatComponent(java.lang.String) 
     */
    public ChatComponent addSelector(String text) {
        this.addChild(new SelectorChatComponent(text));
        return this;
    }
    
    /**
     * Creates and adds a new {@link TextChatComponent} as a child of this component.
     * 
     * @param text the text to display
     * @return this component
     * @see TextChatComponent#TextChatComponent(java.lang.String) 
     */
    public ChatComponent addText(String text) {
        this.addChild(new TextChatComponent(text));
        return this;
    }
    
    /**
     * Creates and adds a new {@link TranslateChatComponent} as a child of this component.
     * 
     * @param key the translation key to be used (for example, {@code item.ghastTear.name})
     * @param values the arguments to be passed for the given translation key
     * @return this component
     * @see TranslateChatComponent#TranslateChatComponent(java.lang.String, java.lang.Object...) 
     */
    public ChatComponent addTranslation(String key, Object... values) {
        this.addChild(new TranslateChatComponent(key, (Object[]) values));
        return this;
    }
    
    /**
     * Returns the child at the given index.
     * 
     * @param index the requested child's index
     * @return the child requested
     */
    public ChatComponent getChild(int index) {
        return this.children.get(index);
    }
    
    /**
     * Returns the most recent child.
     * Note that for usage of {@link DynamicChatComponent}, 
     * the most recent child is considered the child at the top of the stack. 
     * Insertions are not considered.
     * 
     * @return the most recent child
     */
    public ChatComponent getLastChild() {
        if (this.children.size() <= 0) {
            return null;
        }
        
        return this.children.get(this.children.size() - 1);
    }
    
    /**
     * Executes the provided {@link ComponentConsumer}, accepting the result of {@link ChatComponent#getLastChild()}.
     * 
     * @param childConsumer the consumer to execute
     * @return this component
     * @see ChatComponent#getLastChild() 
     */
    public ChatComponent onLastChild(ComponentConsumer childConsumer) {
        if (getLastChild() == null) {
            throw new IllegalStateException("Cannot consume unborn child!");
        }
        childConsumer.accept(getLastChild());
        return this;
    }
    
    private ChatComponent getLastChildOrSelf() {
        ChatComponent component = getLastChild();
        if (component == null) {
            return this;
        }
        
        return component;
    }
    
    /**
     * Sets the formatting of the last child added, or itself if no children have been added.
     * Updates the current {@link ChatModifier} of the component, opposed to creating a new one.
     * 
     * @param colors the ChatColors to set formatting with
     * @return this component
     * @see ChatModifier#setFromChatColors(org.bukkit.ChatColor...) 
     */
    public ChatComponent setFormatting(ChatColor... colors) {
        getLastChildOrSelf().getModifier().setFromChatColors(colors);
        return this;
    }
    
    /**
     * Sets the {@link ClickEvent} of the last child added, or itself if no children have been added.
     * Updates the current {@link ChatModifier} of the component, opposed to creating a new one.
     * 
     * @param action the form of action to take upon clicking the component
     * @param text the text passed upon the action being completed
     * @return this component
     * @see ChatModifier#setClick(codes.nova.util.chat.modifiers.ClickAction, java.lang.String) 
     */
    public ChatComponent setClick(ClickAction action, String text) {
        getLastChildOrSelf().getModifier().setClick(new ClickEvent(action, text));
        return this;
    }

    /**
     * Sets the {@link HoverEvent} of the last child added, or itself if no children have been added.
     * Updates the current {@link ChatModifier} of the component, opposed to creating a new one.
     * 
     * @param action the form of action to take upon clicking the component
     * @param lines the lines used within the event
     * @return this component
     * @see ChatModifier#setHover(codes.nova.util.chat.modifiers.HoverAction, java.lang.String...) 
     */
    public ChatComponent setHover(HoverAction action, String... lines) {
        getLastChildOrSelf().getModifier().setHover(new HoverEvent(action, lines));
        return this;
    }

    /**
     * Sets the {@link HoverEvent} of the last child added, or itself if no children have been added.
     * Updates the current {@link ChatModifier} of the component, opposed to creating a new one.
     * 
     * @param action the form of action to take upon clicking the component
     * @param component the component used within the event
     * @return this component
     * @see ChatModifier#setHover(codes.nova.util.chat.modifiers.HoverAction, codes.nova.util.chat.components.ChatComponent) 
     */
    public ChatComponent setHover(HoverAction action, ChatComponent component) {
        getLastChildOrSelf().getModifier().setHover(new HoverEvent(action, component));
        return this;
    }

    protected abstract IChatBaseComponent compile();

}
