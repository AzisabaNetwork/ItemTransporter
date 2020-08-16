package net.testusuke.azisaba.itemtransporter.LGW

import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.inventory.InventoryClickEvent

/**
 * Created by testusuke on 2020/08/16
 * @author testusuke
 */
object Listener:Listener {

    //  Inventory
    @EventHandler
    fun onClickInventory(event:InventoryClickEvent){
        if(event.view.title != EmeraldInventory.INVENTORY_NAME)return
        val player = event.whoClicked as Player
        val inventory = event.inventory
        val index = event.slot

        //  Glass Index
        if(index in 18..26){
            event.isCancelled = true
            return
        }


    }
}