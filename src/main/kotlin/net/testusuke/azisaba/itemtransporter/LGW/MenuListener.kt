package net.testusuke.azisaba.itemtransporter.LGW

import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.inventory.InventoryClickEvent

/**
 * Created by testusuke on 2020/09/10
 * @author testusuke
 */
object MenuListener:Listener {

    //  ClickEvent
    @EventHandler
    fun onClick(event:InventoryClickEvent){
        if(event.view.title.equals(MenuInventory.INVENTORY_NAME)){
            //  cancel event
            event.isCancelled = true

            //  player
            val player = event.whoClicked as Player
            //  slot
            val slot = event.slot

            //  emerald
            if(slot == 2){

            }
            //  purchase
            if(slot == 5){

            }
        }
    }

}