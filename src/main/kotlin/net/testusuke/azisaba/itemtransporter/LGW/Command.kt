package net.testusuke.azisaba.itemtransporter.LGW

import net.testusuke.azisaba.itemtransporter.Main.Companion.prefix
import net.testusuke.azisaba.itemtransporter.Permission
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

/**
 * Created by testusuke on 2020/08/16
 * @author testusuke
 */
object Command:CommandExecutor {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if(sender !is Player)return false
        //  Permission
        if(!sender.hasPermission(Permission.LGW_GENERAL)){
            sender.sendMessage("${prefix}§c権限がありません")
            return false
        }

        if(args.isEmpty()){
            MenuInventory.open(sender)
            return true
        }

        when(args[0]){
            "help" -> sendHelp(sender)
            "emerald" -> {

            }
        }
        return false
    }

    private fun sendHelp(player: Player){
        val msg = """
            /it
        """.trimIndent()
        //  send
        player.sendMessage(msg)
    }

}