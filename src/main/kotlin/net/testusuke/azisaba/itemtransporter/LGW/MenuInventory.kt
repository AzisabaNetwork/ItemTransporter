package net.testusuke.azisaba.itemtransporter.LGW

import net.testusuke.azisaba.itemtransporter.Main.Companion.prefix
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack

/**
 * Created by testusuke on 2020/09/09
 * @author testusuke
 */
object MenuInventory {

    const val INVENTORY_NAME = "§eItemTransporter §6Menu"

    fun open(player: Player){
        //  create
        val inv = Bukkit.createInventory(null,9, INVENTORY_NAME)

        //  prepare ItemStack
        //  emerald
        val emerald = ItemStack(Material.EMERALD)
        val emeraldMeta = emerald.itemMeta
        emeraldMeta.displayName = "§aエメラルドメニュー"
        emerald.itemMeta = emeraldMeta

        //  purchase
        val purchase = ItemStack(Material.CHEST)
        val purchaseMeta = purchase.itemMeta
        purchaseMeta.displayName = "§6商品受け取りメニュー"
        purchase.itemMeta = purchaseMeta

        //  addItem
        inv.setItem(2,emerald)
        inv.setItem(5,purchase)

        //  open inventory
        player.openInventory(inv)

        //  message
        player.sendMessage("${prefix}§aメニューを開きます。")
    }

}