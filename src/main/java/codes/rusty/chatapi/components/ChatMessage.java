package codes.rusty.chatapi.components;

import codes.rusty.chatapi.modifiers.ChatModifier;
import codes.rusty.chatapi.modifiers.ClickAction;
import codes.rusty.chatapi.modifiers.HoverAction;
import codes.rusty.chatapi.util.ComponentConsumer;
import codes.rusty.chatapi.nms.LocationCommandListener;
import net.minecraft.server.v1_8_R2.ChatComponentText;
import net.minecraft.server.v1_8_R2.ChatComponentUtils;
import net.minecraft.server.v1_8_R2.EntityPlayer;
import net.minecraft.server.v1_8_R2.IChatBaseComponent;
import net.minecraft.server.v1_8_R2.ICommandListener;
import net.minecraft.server.v1_8_R2.MinecraftServer;
import net.minecraft.server.v1_8_R2.WorldServer;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_8_R2.entity.CraftPlayer;
import org.bukkit.entity.Player;

/**
 * ChatAPI utility {@link ChatComponent} class for the creation and sending of ChatAPI messages to players.
 * 
 * @author Rustywolf
 */
public class ChatMessage extends ChatComponent {

    public ChatMessage() {}

    /**
     * Sends the {@link IChatBaseComponent} built by this object to the given players.
     * Features that require a location (for example, selectors) will be centered on (0, 0, 0) of the default world.
     * 
     * @param players the players to send this component to
     * @see ChatComponent#build() 
     */
    public void send(Player... players) {
        send(null, players);
    }
    
    /**
     * Sends the {@link IChatBaseComponent} built by this object to the given players.
     * Features that require a location (for example, selectors) will be centered on the given location.
     * 
     * @param players the players to send this component to
     * @param location the location to center this component on
     * @see ChatComponent#build() 
     */
    public void send(Location location, Player... players) {
        // Copied from VanillaCommandWrapper.java, <3 ToD
        ICommandListener commandListener = (location == null) ? MinecraftServer.getServer() : new LocationCommandListener(location);
        WorldServer[] prev = MinecraftServer.getServer().worldServer;
        MinecraftServer server = MinecraftServer.getServer();
        server.worldServer = new WorldServer[server.worlds.size()];
        server.worldServer[0] = (WorldServer) commandListener.getWorld();
        int bpos = 0;
        for (int pos = 1; pos < server.worldServer.length; pos++) {
            WorldServer world = server.worlds.get(bpos++);
            if (server.worldServer[0] == world) {
                pos--;
                continue;
            }
            server.worldServer[pos] = world;
        }
        
        IChatBaseComponent built = this.build();
        for (Player player : players) {
            EntityPlayer entityPlayer = ((CraftPlayer) player).getHandle();
            try {
                entityPlayer.sendMessage(ChatComponentUtils.filterForDisplay(commandListener, built, entityPlayer));
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("There was an error building this chat message, attempting safe call");
                entityPlayer.sendMessage(built);
            }
        }
        
        server.worldServer = prev;
    }

    @Override
    protected IChatBaseComponent compile() {
        return new ChatComponentText("");
    }

    @Override
    public ChatMessage onLastChild(ComponentConsumer childConsumer) {
        super.onLastChild(childConsumer);
        return this;
    }

    @Override
    public ChatMessage addChild(ChatComponent component) {
        super.addChild(component);
        return this;
    }

    @Override
    public ChatMessage addBranch() {
        super.addBranch();
        return this;
    }

    @Override
    public ChatMessage addRawText(String... lines) {
        super.addRawText(lines);
        return this;
    }

    @Override
    public ChatMessage addScore(String name, String objective) {
        super.addScore(name, objective);
        return this;
    }

    @Override
    public ChatMessage addSelector(String text) {
        super.addSelector(text);
        return this;
    }
    
    @Override
    public ChatMessage addText(String text) {
        super.addText(text);
        return this;
    }

    @Override
    public ChatMessage addTranslation(String key, Object... values) {
        super.addTranslation(key, values);
        return this;
    }

    @Override
    public ChatMessage setModifier(ChatModifier modifier) {
        super.setModifier(modifier);
        return this;
    }

    @Override
    public ChatMessage setHover(HoverAction action, ChatComponent text) {
        super.setHover(action, text);
        return this;
    }

    @Override
    public ChatMessage setHover(HoverAction action, String... text) {
        super.setHover(action, text);
        return this;
    }

    @Override
    public ChatMessage setClick(ClickAction action, String text) {
        super.setClick(action, text);
        return this;
    }

    @Override
    public ChatMessage setFormatting(ChatColor... colors) {
        super.setFormatting(colors);
        return this;
    }

}
