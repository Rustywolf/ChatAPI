package codes.rusty.chatapi.nms;

import net.minecraft.server.v1_8_R2.BlockPosition;
import net.minecraft.server.v1_8_R2.ChatComponentText;
import net.minecraft.server.v1_8_R2.CommandObjectiveExecutor;
import net.minecraft.server.v1_8_R2.Entity;
import net.minecraft.server.v1_8_R2.IChatBaseComponent;
import net.minecraft.server.v1_8_R2.ICommandListener;
import net.minecraft.server.v1_8_R2.Vec3D;
import net.minecraft.server.v1_8_R2.World;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_8_R2.CraftWorld;

public class LocationCommandListener implements ICommandListener {

    private final String name;
    private final World world;
    private final BlockPosition pos;
    
    public LocationCommandListener(Location location) {
        this.name = location.getWorld().getName() + "@(" + location.getX() + ", " + location.getY() + ", " + location.getZ() + ")";
        this.world = ((CraftWorld) location.getWorld()).getHandle();
        this.pos = new BlockPosition(location.getX(), location.getY(), location.getZ());
    }
    
    @Override
    public String getName() {
        return name;
    }

    @Override
    public IChatBaseComponent getScoreboardDisplayName() {
        return new ChatComponentText(name);
    }

    @Override
    public void sendMessage(IChatBaseComponent icbc) {}

    @Override
    public boolean a(int i, String string) {
        return true;
    }

    @Override
    public BlockPosition getChunkCoordinates() {
        return pos;
    }

    @Override
    public Vec3D d() {
        return new Vec3D(0.0D, 0.0D, 0.0D);
    }

    @Override
    public World getWorld() {
        return world;
    }

    @Override
    public Entity f() {
        return null;
    }

    @Override
    public boolean getSendCommandFeedback() {
        return world.getGameRules().getBoolean("sendCommandFeedback");
    }

    @Override
    public void a(CommandObjectiveExecutor.EnumCommandResult ecr, int i) {}
    
}
