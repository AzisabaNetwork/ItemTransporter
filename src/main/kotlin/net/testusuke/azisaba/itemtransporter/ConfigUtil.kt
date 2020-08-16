package net.testusuke.azisaba.itemtransporter

import net.testusuke.azisaba.itemtransporter.Main.Companion.plugin
import org.bukkit.configuration.file.FileConfiguration
import org.bukkit.configuration.file.YamlConfiguration
import java.io.File
import java.io.IOException

/**
 * Created by testusuke on 2020/08/15
 * @author testusuke
 */
object ConfigUtil {

    /**
     * function for load/save status.
     */
    lateinit var statusFile: File
    /**
     * function of load Plugin Status Config file.
     * @param filePath[String] file path. example status.yml
     * @return config[YamlConfiguration] if happen exception,return null.
     */
    fun loadStatusConfig(filePath: String):YamlConfiguration?{
        try {
            val directory = plugin.dataFolder
            if (!directory.exists()) directory.mkdir()
            //  File
            this.statusFile = File(directory, filePath)
            //  Yaml
            return if (!statusFile.exists()) {
                statusFile.createNewFile()
                val c = YamlConfiguration.loadConfiguration(statusFile)
                c.set("enable", false)
                c
            } else {
                YamlConfiguration.loadConfiguration(statusFile)
            }
        }catch (e:IOException){
            return null
        }
    }

    /**
     * function of save status config
     * @param config[YamlConfiguration] config
     */
    fun saveStatusConfig(config: YamlConfiguration?){
        if(config == null)return
        config.save(this.statusFile)
        plugin.logger.info("saved status config.")
    }

    /**
     * function of load plugin status.
     * @param config[YamlConfiguration] config file.
     */
    fun loadStatus(config:YamlConfiguration?){
        if(config == null){
            plugin.logger.info("status configuration has not loaded yet. loadStatus()")
            return
        }
        try {
            plugin.enable = config.getBoolean("enable")
        }catch (e:Exception){
            e.printStackTrace()
            plugin.enable = false
        }
    }

    /**
     * function of load main configuration.
     * @param config[FileConfiguration]
     */
    fun loadMain(config:FileConfiguration){
        plugin.server = try {
            val c = config.getString("server").toString()
            plugin.logger.info("server is $c")
            c
        }catch (e:Exception){
            e.printStackTrace()
            "none"
        }
    }

}