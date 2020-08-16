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
            //  Statement
            val statement = connection.createStatement()
            val sql = "SELECT amount FROM item_transport_emerald WHERE uuid='${uuid}' LIMIT 1;"
            val result = statement.executeQuery(sql)
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
            //  Statement
            val statement = connection.createStatement()
            val mcid = BukkitUtil.getMinecraftID(uuid)?.toString() ?: "none"
            val sql = "INSERT INTO item_transport_emerald (uuid,mcid,amount) VALUES ('$uuid','$mcid',0);"
            statement.executeUpdate(sql)
            return true
        }catch (e:Exception){
            e.printStackTrace()
            return false
        }
    }

}