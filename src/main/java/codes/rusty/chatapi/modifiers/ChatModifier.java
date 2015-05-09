package codes.rusty.chatapi.modifiers;

import codes.rusty.chatapi.components.ChatComponent;
import java.util.Objects;
import net.minecraft.server.v1_8_R2.EnumChatFormat;
import org.bukkit.ChatColor;

/**
 * ChatAPI representation of NMS ChatModifier
 * 
 * @author Rustywolf
 */
public class ChatModifier {
    
    private Flags flags = new Flags();
    
    private ChatColor color = null;
    private boolean bold = false;
    private boolean italic = false;
    private boolean strikethrough = false;
    private boolean underlined = false;
    private boolean magic = false;
    private ClickEvent click = null;
    private HoverEvent hover = null;
    private String insertion = null;
    
    public ChatModifier() {}
    
    /**
     * Creates an NMS representation of this modifier.
     * 
     * @return the NMS representation of this class
     */
    public net.minecraft.server.v1_8_R2.ChatModifier build() {
        net.minecraft.server.v1_8_R2.ChatModifier modifier = new net.minecraft.server.v1_8_R2.ChatModifier();
        modifier.setColor((color == null) ? null : EnumChatFormat.a(Integer.parseInt(color.getChar() + "", 16)));
        modifier.setBold(bold);
        modifier.setItalic(italic);
        modifier.setStrikethrough(strikethrough);
        modifier.setUnderline(underlined);
        modifier.setRandom(magic);
        modifier.setChatClickable((click == null) ? null : click.build());
        modifier.setChatHoverable((hover == null) ? null : hover.build());
        modifier.setInsertion(insertion);
        return modifier;
    }
    
    /**
     * Resets this modifier to it's default state.
     * This will prevent the parent {@link ChatModifier} from passing on colors to this child.
     */
    public void reset() {
        this.color = null;
        this.bold = false;
        this.italic = false;
        this.strikethrough = false;
        this.underlined = false;
        this.magic = false;
        this.click = null;
        this.hover = null;
        this.insertion = null;
        this.flags.set(true);
    }
    
    /**
     * Whether this modifier has had a property set.
     * This method is for testing whether a components {@link ChatModifier} can be sent as null.
     * This will not return true if {@link ChatModifier#reset()} is called.
     * 
     * @return {@code true} if no properties have been set, otherwise {@code false}
     */
    public boolean isReset() {
        return this.flags.isReset();
    }
    
    /**
     * Returns the ChatColor currently set.
     * 
     * @return the current ChatColor
     */
    public ChatColor getColor() {
        return color;
    }

    /**
     * Sets the ChatColor for this modifier.
     * 
     * @param color the color to set
     * @return this modifier;
     */
    public ChatModifier setColor(ChatColor color) {
        if (!color.isColor()) {
            throw new IllegalArgumentException("Provided ChatColor must be a colour!");
        }
        
        this.color = color;
        this.flags.color = true;
        return this;
    }

    /**
     * Returns whether this modifier is bold.
     * 
     * @return {@code true} if bold has been set to true, otherwise {@code false} 
     */
    public boolean isBold() {
        return bold;
    }

    /**
     * Sets the bold property for this modifier
     * 
     * @param bold the value of the bold property for this modifier
     * @return this modifier
     */
    public ChatModifier setBold(boolean bold) {
        this.bold = bold;
        this.flags.bold = true;
        return this;
    }

    /**
     * Returns whether this modifier is italic.
     * 
     * @return {@code true} if italic has been set to true, otherwise {@code false} 
     */
    public boolean isItalic() {
        return italic;
    }

    /**
     * Sets the italic property for this modifier
     * 
     * @param italic the value of the italic property for this modifier
     * @return this modifier
     */
    public ChatModifier setItalic(boolean italic) {
        this.italic = italic;
        this.flags.italic = true;
        return this;
    }

    /**
     * Returns whether this modifier is strikethrough.
     * 
     * @return {@code true} if strikethrough has been set to true, otherwise {@code false} 
     */
    public boolean isStrikethrough() {
        return strikethrough;
    }

    /**
     * Sets the strikethrough property for this modifier
     * 
     * @param strikethrough the value of the strikethrough property for this modifier
     * @return this modifier
     */
    public ChatModifier setStrikethrough(boolean strikethrough) {
        this.strikethrough = strikethrough;
        this.flags.strikethrough = true;
        return this;
    }

    /**
     * Returns whether this modifier is underlined.
     * 
     * @return {@code true} if underlined has been set to true, otherwise {@code false} 
     */
    public boolean isUnderlined() {
        return underlined;
    }

    /**
     * Sets the underlined property for this modifier
     * 
     * @param underlined the value of the underlined property for this modifier
     * @return this modifier
     */
    public ChatModifier setUnderlined(boolean underlined) {
        this.underlined = underlined;
        this.flags.underlined = true;
        return this;
    }

    /**
     * Returns whether this modifier is magic. Maaaaaaaagic.
     * 
     * @return {@code true} if magic has been set to true, otherwise {@code false} 
     */
    public boolean isMagic() {
        return magic;
    }

    /**
     * Sets the magic property for this modifier
     * 
     * @param magic the value of the magic property for this modifier
     * @return this modifier
     */
    public ChatModifier setMagic(boolean magic) {
        this.magic = magic;
        this.flags.magic = true;
        return this;
    }

    /**
     * Returns the {@link ClickEvent} associated with this modifier
     * 
     * @return the {@link ClickEvent} of this modifier
     * @see ClickEvent
     */
    public ClickEvent getClick() {
        return click;
    }

    /**
     * Sets the {@link ClickEvent} for this modifier.
     * Creates a new instance of {@link ClickEvent} with the passed parameters.
     * 
     * @param action the form of action to take upon clicking the component
     * @param text the text passed upon the action being completed
     * @return this modifier
     */
    public ChatModifier setClick(ClickAction action, String text) {
        this.click = new ClickEvent(action, text);
        this.flags.click = true;
        return this;
    }

    /**
     * Sets the {@link ClickEvent} for this modifier.
     * 
     * @param click the new {@link ClickEvent} for this modifier
     * @return this modifier
     */
    public ChatModifier setClick(ClickEvent click) {
        this.click = click;
        this.flags.click = true;
        return this;
    }

    /**
     * Returns the {@link HoverEvent} associated with this modifier
     * 
     * @return the {@link HoverEvent} of this modifier
     * @see HoverEvent
     */
    public HoverEvent getHover() {
        return hover;
    }

    /**
     * Sets the {@link HoverEvent} for this modifier.
     * Creates a new instance of {@link HoverEvent} with the passed parameters.
     * 
     * @param action the form of action to take upon clicking the component
     * @param lines the lines used within the event
     * @return this modifier
     */
    public ChatModifier setHover(HoverAction action, String... lines) {
        this.hover = new HoverEvent(action, lines);
        this.flags.hover = true;
        return this;
    }

    /**
     * Sets the {@link HoverEvent} for this modifier.
     * Creates a new instance of {@link HoverEvent} with the passed parameters.
     * 
     * @param action the form of action to take upon clicking the component
     * @param component the component used within the event
     * @return this modifier
     */
    public ChatModifier setHover(HoverAction action, ChatComponent component) {
        this.hover = new HoverEvent(action, component);
        this.flags.hover = true;
        return this;
    }

    /**
     * Sets the {@link HoverEvent} for this modifier.
     * 
     * @param hover the new {@link HoverEvent} for this modifier
     * @return this modifier
     */
    public ChatModifier setHover(HoverEvent hover) {
        this.hover = hover;
        this.flags.hover = true;
        return this;
    }

    /**
     * Returns the insertion currently set.
     * 
     * @return the insertion of this modifier 
     */
    public String getInsertion() {
        return insertion;
    }

    /**
     * Sets the insertion property for this modifier
     * 
     * @param insertion the value of the insertion property for this modifier
     * @return 
     */
    public ChatModifier setInsertion(String insertion) {
        this.insertion = insertion;
        this.flags.insertion = true;
        return this;
    }

    /**
     * Sets the relevant properties for this modifier.
     * Supports all ChatColors (including formatting)
     * 
     * @param colors the colors to apply to this modifier
     * @return this modifier
     */
    public ChatModifier setFromChatColors(ChatColor... colors) {
        ChatModifier modifier = this;
        for (ChatColor color : colors) {
            if (color.isColor()) {
                modifier.setColor(color);
            } else switch (color) {
                case BOLD:
                    modifier.setBold(true);
                    break;
                    
                case ITALIC:
                    modifier.setItalic(true);
                    break;
                    
                case STRIKETHROUGH:
                    modifier.setStrikethrough(true);
                    break;
                    
                case UNDERLINE:
                    modifier.setUnderlined(true);
                    break;
                    
                case MAGIC:
                    modifier.setMagic(true);
                    break;
                    
                case RESET:
                    modifier.reset();
            }
        }
        
        return modifier;
    }
    
    /**
     * Returns a new {@link ChatModifier} and executes {@link ChatModifier#setFromChatColors(org.bukkit.ChatColor...)} with the passed parameters.
     * 
     * @param colors the formatting to use in the modifier
     * @return the created modifier
     * @see ChatModifier#setFromChatColors(org.bukkit.ChatColor...) 
     */
    public static ChatModifier of(ChatColor... colors) {
        return new ChatModifier().setFromChatColors(colors);
    }
    
    /**
     * Returns a new {@link ChatModifier} with the properties and flags from the passed modifiers.
     * Any property not set by the child is inherited from the parent.
     * 
     * @param original the child modifier
     * @param parent the parent modifier
     * @return the created modifier
     */
    public static ChatModifier inherit(ChatModifier original, ChatModifier parent) {
        ChatModifier ret = new ChatModifier();
        
        ret.color = (original.flags.color) ? original.color : parent.color;
        ret.bold = (original.flags.bold) ? original.bold : parent.bold;
        ret.italic = (original.flags.italic) ? original.italic : parent.italic;
        ret.strikethrough = (original.flags.strikethrough) ? original.strikethrough : parent.strikethrough;
        ret.underlined = (original.flags.underlined) ? original.underlined : parent.underlined;
        ret.magic = (original.flags.magic) ? original.magic : parent.magic;
        // Don't inherit these because vanilla doesnt seem bugged for that...
        // Nevermind, i was wrong
        ret.click = (original.flags.click) ? original.click : parent.click;
        ret.hover = (original.flags.hover) ? original.hover : parent.hover;
        ret.insertion = (original.flags.insertion) ? original.insertion : parent.insertion;
        ret.flags = Flags.inherit(original.flags, parent.flags);
        
        return ret;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.color);
        hash = 97 * hash + (this.bold ? 1 : 0);
        hash = 97 * hash + (this.italic ? 1 : 0);
        hash = 97 * hash + (this.strikethrough ? 1 : 0);
        hash = 97 * hash + (this.underlined ? 1 : 0);
        hash = 97 * hash + (this.magic ? 1 : 0);
        hash = 97 * hash + Objects.hashCode(this.click);
        hash = 97 * hash + Objects.hashCode(this.hover);
        hash = 97 * hash + Objects.hashCode(this.insertion);
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
        final ChatModifier other = (ChatModifier) obj;
        if (this.color != other.color) {
            return false;
        }
        if (this.bold != other.bold) {
            return false;
        }
        if (this.italic != other.italic) {
            return false;
        }
        if (this.strikethrough != other.strikethrough) {
            return false;
        }
        if (this.underlined != other.underlined) {
            return false;
        }
        if (this.magic != other.magic) {
            return false;
        }
        if (!Objects.equals(this.click, other.click)) {
            return false;
        }
        if (!Objects.equals(this.hover, other.hover)) {
            return false;
        }
        if (!Objects.equals(this.insertion, other.insertion)) {
            return false;
        }
        return true;
    }
    
    private static class Flags {
        
        protected boolean color = false;
        protected boolean bold = false;
        protected boolean italic = false;
        protected boolean strikethrough = false;
        protected boolean underlined = false;
        protected boolean magic = false;
        protected boolean click = false;
        protected boolean hover = false;
        protected boolean insertion = false;
        
        protected Flags() {}
        
        protected void reset() {
            set(false);
        }
        
        protected void set(boolean state) {
            this.color = state;
            this.bold = state;
            this.italic = state;
            this.strikethrough = state;
            this.underlined = state;
            this.magic = state;
            this.click = state;
            this.hover = state;
            this.insertion = state;
        }
        
        protected boolean isReset() {
            return (
                    this.color == false &&
                    this.bold == false &&
                    this.italic == false &&
                    this.strikethrough == false &&
                    this.underlined == false &&
                    this.magic == false &&
                    this.click == false &&
                    this.hover == false &&
                    this.insertion == false
            );
        }
        
        protected static Flags inherit(Flags original, Flags parent) {
            Flags flags = new Flags();
            flags.color = original.color || parent.color;
            flags.bold = original.bold || parent.bold;
            flags.italic = original.italic || parent.italic;
            flags.strikethrough = original.strikethrough || parent.strikethrough;
            flags.underlined = original.underlined || parent.underlined;
            flags.magic = original.magic || parent.magic;
            flags.click = original.click || parent.click;
            flags.hover = original.hover || parent.hover;
            flags.insertion = original.insertion || parent.insertion;
            
            return flags;
        }
    }
    
}
