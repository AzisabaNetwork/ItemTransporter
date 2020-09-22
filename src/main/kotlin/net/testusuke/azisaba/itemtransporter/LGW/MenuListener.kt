package net.testusuke.azisaba.itemtransporter.LGW

import net.testusuke.azisaba.itemtransporter.MessageUtil
import net.testusuke.azisaba.itemtransporter.Permission
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
                //  pex
                if(!player.hasPermission(Permission.LGW_GENERAL)){
                    MessageUtil.sendPermissionError(player);
                    return
                }
                //  open Inventory
                EmeraldInventory.open(player)
            }
            //  purchase
            if(slot == 5){
                //  pex
                if(!player.hasPermission(Permission.LGW_GENERAL)){
                    MessageUtil.sendPermissionError(player);
                    return
                }
                //  open Inventory
                PurchaseInventory.open(player)
            }
        }
    }

}