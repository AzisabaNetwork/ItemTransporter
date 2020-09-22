package net.testusuke.azisaba.itemtransporter.LGW

import org.bukkit.Bukkit
import org.bukkit.entity.Player

/**
 * Created on 2020/09/17
 * Author testusuke
 */
object PurchaseInventory {

    const val INVENTORY_NAME = "";

    fun openInventory(player: Player) {
        //  create inventory
        val inv = Bukkit.createInventory(null, 54, INVENTORY_NAME)
    }
}