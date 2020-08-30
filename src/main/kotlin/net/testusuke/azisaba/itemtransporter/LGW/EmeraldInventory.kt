package net.testusuke.azisaba.itemtransporter.LGW

import net.testusuke.azisaba.itemtransporter.Main
import net.testusuke.azisaba.itemtransporter.Main.Companion.plugin
import net.testusuke.azisaba.itemtransporter.Main.Companion.prefix
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.ItemStack

/**
 * Created by testusuke on 2020/08/16
 * @author testusuke
 */
object EmeraldInventory {

    //  Inventory Name
    const val INVENTORY_NAME = "§eItemTransporter §dエメラルドを入れてください"

    fun open(player: Player){
        //  start thread
        Thread(Runnable {
            val inv = createInventory(player.uniqueId.toString())
            if(inv == null){
                player.sendMessage("${prefix}§cエラーが発生しました。")
                return@Runnable
            }
            //  open Inventory with Bukkit Runnable
            Bukkit.getScheduler().runTask(plugin, Runnable {
                player.openInventory(inv)
            })
        }).start()
    }

    private fun createInventory(uuid:String):Inventory?{
        val inventory = Bukkit.createInventory(null,27, INVENTORY_NAME)
        //  Glass
        val glassIndex = arrayOf(18,19,20,21,23,25,26)
        val glass = ItemStack(Material.STAINED_GLASS,1,5)
        val glassMeta = glass.itemMeta
        glassMeta.displayName = ""
        glass.itemMeta = glassMeta
        for(i in glassIndex) {   //  put
            inventory.setItem(i,glass)
        }

        //  has amount of emerald
        val dataBaseEmeraldAmount = DataBase.getAmount(uuid) ?: return null
        val hasAmount = ItemStack(Material.EMERALD)
        val hasMeta = hasAmount.itemMeta
        hasMeta.displayName = "§aあなたが所持しているエメラルド: §6$dataBaseEmeraldAmount 個"
        hasAmount.itemMeta = hasMeta
        inventory.setItem(22,hasAmount)

        return inventory
    }

}