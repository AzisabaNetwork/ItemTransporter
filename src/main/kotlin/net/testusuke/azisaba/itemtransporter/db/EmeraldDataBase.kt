package net.testusuke.azisaba.itemtransporter.db

import net.testusuke.azisaba.itemtransporter.BukkitUtil
import net.testusuke.azisaba.itemtransporter.ErrorReason
import net.testusuke.azisaba.itemtransporter.Main
import net.testusuke.azisaba.itemtransporter.ResultType
import java.sql.SQLException

/**
 * Created by testusuke on 2020/08/16
 * @author testusuke
 */
object EmeraldDataBase {

    fun getAmount(uuid: String): Int? {
        return try {
            val connection = Main.database.getConnection() ?: return null
            //  Statement )
            val sql = "SELECT amount FROM item_transport_emerald WHERE uuid=? LIMIT 1;"
            val statement = connection.prepareStatement(sql)
            statement.setString(1,uuid)
            val result = statement.executeQuery()

            val amount = if(!result.next()){
                createUser(uuid)
                0
            }else {
                result.getInt("amount")
            }
            result.close()
            statement.close()
            connection.close()
            //  return
            amount
        }catch (e:SQLException){
            e.printStackTrace()
            null
        }
    }

    fun createUser(uuid: String):Boolean{
        try {
            //  check exist
            if(isRegistered(uuid)) return true
            val connection = Main.database.getConnection() ?: return false
            val mcid = BukkitUtil.getMinecraftID(uuid) ?: "none"
            val sql = "INSERT INTO item_transport_emerald (uuid,mcid,amount) VALUES (?,?,0);"
            //  Statement
            val statement = connection.prepareStatement(sql)
            statement.setString(1,uuid)
            statement.setString(2,mcid)
            statement.executeUpdate()

            statement.close()
            connection.close()

            return true
        }catch (e:SQLException){
            e.printStackTrace()
            return false
        }
    }

    fun addEmerald(uuid: String,amount:Int): ResultType{
        try {
            val connection = Main.database.getConnection() ?: return ResultType.Error(ErrorReason.CAN_NOT_ACCESS_DB)
            val selectSQL = "SELECT amount FROM item_transport_emerald WHERE uuid=? LIMIT 1;"
            //  Statement
            val selectStatement = connection.prepareStatement(selectSQL)
            selectStatement.setString(1,uuid)
            val result = selectStatement.executeQuery()

            val amountInDB = if(!result.next()){
                createUser(uuid)
                0
            }else {
                result.getInt("amount")
            }
            //  close
            result.close()
            selectStatement.close()

            //  UPDATE
            val updateSQL = "UPDATE item_transport_emerald SET amount=? WHERE uuid=?;"
            //  statement
            val updateStatement = connection.prepareStatement(updateSQL)
            updateStatement.setInt(1,amountInDB + amount)
            updateStatement.setString(2,uuid)
            updateStatement.executeUpdate()

            //  close
            updateStatement.close()
            connection.close()
        }catch (e:SQLException){
            e.printStackTrace()
            return ResultType.Error(ErrorReason.FAILED_SQL)
        }
        return ResultType.Success(0)
    }

    private fun isRegistered(uuid: String): Boolean{
        return try {
            val connection = Main.database.getConnection() ?: return false
            //  Statement )
            val sql = "SELECT id FROM item_transport_emerald WHERE uuid=? LIMIT 1;"
            val statement = connection.prepareStatement(sql)
            statement.setString(1,uuid)
            val result = statement.executeQuery()
            val b = result.next()
            result.close()
            statement.close()
            connection.close()
            b
        }catch (e:SQLException){
            e.printStackTrace()
            false
        }
    }
}