package net.testusuke.azisaba.itemtransporter.LGW

import net.testusuke.azisaba.itemtransporter.Main
import net.testusuke.azisaba.itemtransporter.ResultType
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.event.inventory.InventoryCloseEvent
import org.bukkit.inventory.ItemStack

/**
 * Created by testusuke on 2020/09/10
 * @author testusuke
 */
object EmeraldListener:Listener {

    //  Inventory Click
    @EventHandler
    fun onClickInventory(event: InventoryClickEvent){
        if(event.view.title == EmeraldInventory.INVENTORY_NAME) {
            val index = event.slot
            //  Glass Index
            if (index in 18..26) {
                event.isCancelled = true
                return
            }
        }

    }

    //  Inventory Close
    @EventHandler
    fun onCloseInventory(event: InventoryCloseEvent){
        val player = event.player as Player
        if(event.view.title == EmeraldInventory.INVENTORY_NAME) {
            //  get amount
            val amount = EmeraldInventory.getEmeraldAmount(player,event.inventory)
            if(amount == 0){    //  if amount is 0
                player.sendMessage("${Main.prefix}§aGUIを閉じます")
                return
            }
            //  add emerald to database
            val result = DataBase.addEmerald(player.uniqueId.toString(),amount)
            if(result is ResultType.Success){
                player.sendMessage("${Main.prefix}§6$amount§a個のエメラルドを入れました。")
            }else if (result is ResultType.Error){
                player.sendMessage("${Main.prefix}§cエラーが発生しました。エメラルドを返却します。")
                //  return
                val emeraldBlockAmount:Int = amount / 9
                val emeraldAmount:Int = amount % 9
                //  ItemStack
                val emeraldBlock = ItemStack(Material.EMERALD_BLOCK,emeraldBlockAmount)
                val emerald = ItemStack(Material.EMERALD,emeraldAmount)
                //  add
                player.inventory.addItem(emeraldBlock)
                player.inventory.addItem(emerald)
            }
        }

    }
}