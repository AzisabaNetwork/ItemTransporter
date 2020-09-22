package net.testusuke.azisaba.itemtransporter

import net.testusuke.azisaba.itemtransporter.Main.Companion.prefix
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

/**
 * Created on 2020/09/16
 * Author testusuke
 */
object MessageUtil {

    fun sendPermissionError(player:Player){
        player.sendMessage("${prefix}§c権限がありません。")
    }
    fun sendPermissionError(sender:CommandSender){
        sender.sendMessage("${prefix}§c権限がありません。")
    }

}