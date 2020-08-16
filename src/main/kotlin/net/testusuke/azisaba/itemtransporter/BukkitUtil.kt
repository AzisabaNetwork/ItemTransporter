package net.testusuke.azisaba.itemtransporter

import org.bukkit.Bukkit
import org.bukkit.OfflinePlayer
import java.util.*

/**
 * Created by testusuke on 2020/08/16
 * @author testusuke
 */
object BukkitUtil {

    fun getMinecraftID(from: String):UUID? {
        try {
            val uuid:UUID = UUID.fromString(from)
            val offlinePlayer = Bukkit.getServer().getOfflinePlayer(uuid)
            if(!existPlayer(offlinePlayer))return null
            return offlinePlayer.uniqueId
        }catch (e:Exception){
            e.printStackTrace()
            return null
        }
    }

    //  Player Exist
    fun existPlayer(player: OfflinePlayer):Boolean {
        return player.hasPlayedBefore()
    }
}