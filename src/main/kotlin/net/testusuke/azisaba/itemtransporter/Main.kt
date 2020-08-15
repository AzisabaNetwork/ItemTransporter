package net.testusuke.azisaba.itemtransporter

import org.bukkit.configuration.file.YamlConfiguration
import org.bukkit.plugin.java.JavaPlugin

/**
 * Created by testusuke on 2020/08/15
 * @author testusuke
 */
class Main:JavaPlugin() {

    companion object{
        lateinit var plugin: Main
    }

    //  enable plugin
    var enable:Boolean = false
    //  enable server.pg or lgw
    lateinit var server:String
    //  Status Config
    var statusConfig:YamlConfiguration? = null

    override fun onEnable() {
        //  instance
        plugin = this
        //  default config
        this.saveDefaultConfig()
        //  custom config
        statusConfig = ConfigUtil.loadStatusConfig("")
        ConfigUtil.loadStatus(statusConfig)

    }

    override fun onDisable() {

    }

}