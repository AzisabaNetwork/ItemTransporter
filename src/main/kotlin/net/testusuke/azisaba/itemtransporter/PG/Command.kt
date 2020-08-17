package net.testusuke.azisaba.itemtransporter.PG

import net.testusuke.azisaba.itemtransporter.Main.Companion.prefix
import net.testusuke.azisaba.itemtransporter.Permission
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
        if(sender !is Player){
            sender.sendMessage("Can't use console")
            return false
        }
        //  Permission
        if(!sender.hasPermission(Permission.PG_GENERAL)){
            sender.sendMessage("${prefix}§cYou don't have permission.")
            return false
        }

        //  empty
        if(args.isEmpty()){

        }

        when(args[0]){

        }
        return false
    }
}