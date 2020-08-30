package net.testusuke.azisaba.itemtransporter.LGW

import net.testusuke.azisaba.itemtransporter.BukkitUtil
import net.testusuke.azisaba.itemtransporter.Main
import java.lang.Exception

/**
 * Created by testusuke on 2020/08/16
 * @author testusuke
 */
object DataBase {

    fun getAmount(uuid: String): Int? {
        return try {
            val connection = Main.database.getConnection() ?: return null
            //  Statement )
            val sql = "SELECT amount FROM item_transport_emerald WHERE uuid=? LIMIT 1;"
            val statement = connection.prepareStatement(sql)
            statement.setString(1,uuid)
            val result = statement.executeQuery()
            if(!result.next()){
                createUser(uuid)
                return 0
            }
            val amount = result.getInt("amount")
            result.close()
            statement.close()
            connection.close()
            //  return
            amount
        }catch (e:Exception){
            e.printStackTrace()
            null
        }
    }

    fun createUser(uuid: String):Boolean{
        try {
            val connection = Main.database.getConnection() ?: return false
            val mcid = BukkitUtil.getMinecraftID(uuid)?.toString() ?: "none"
            val sql = "INSERT INTO item_transport_emerald (uuid,mcid,amount) VALUES (?,?,0);"
            //  Statement
            val statement = connection.prepareStatement(sql)
            statement.setString(1,uuid)
            statement.setString(2,mcid)
            statement.executeUpdate()
            return true
        }catch (e:Exception){
            e.printStackTrace()
            return false
        }
    }

}