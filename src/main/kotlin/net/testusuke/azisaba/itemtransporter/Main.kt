package net.testusuke.azisaba.itemtransporter

import org.bukkit.plugin.java.JavaPlugin

/**
 * Created by testusuke on 2020/08/15
 * @author testusuke
 */
class Main:JavaPlugin() {

    companion object{
        lateinit var plugin: Main

    }

    override fun onEnable() {
        //  instance
        plugin = this

    }

    override fun onDisable() {

    }

}