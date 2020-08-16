package net.testusuke.azisaba.itemtransporter

import net.testusuke.azisaba.itemtransporter.LGW.LgwMain
import net.testusuke.azisaba.itemtransporter.PG.PgMain
import org.bukkit.configuration.file.YamlConfiguration
import org.bukkit.plugin.java.JavaPlugin

/**
 * Created by testusuke on 2020/08/15
 * @author testusuke
 */
class Main:JavaPlugin() {

    companion object{
        lateinit var plugin: Main
        lateinit var database:DataBase
    }

    //  enable plugin
    var enable:Boolean = false
    //  enable server.pg or lgw.if not select,will be "none"
    lateinit var server:String
    //  Status Config
    var statusConfig:YamlConfiguration? = null

    override fun onEnable() {
        //  instance
        plugin = this
        //  default config
        this.saveDefaultConfig()
        ConfigUtil.loadMain(this.config)
        //  custom config
        statusConfig = ConfigUtil.loadStatusConfig("status.yml")
        ConfigUtil.loadStatus(statusConfig)
        //  DataBase
        database = DataBase()
        //  Server
        when(this.server){
            "lgw" -> {
                LgwMain()
            }
            "pg" -> {
                PgMain()
            }
            else -> {
                logger.info("not enter server name yet.")
            }
        }
    }

    override fun onDisable() {

    }

}