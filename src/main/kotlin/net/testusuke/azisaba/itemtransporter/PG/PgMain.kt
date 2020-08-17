package net.testusuke.azisaba.itemtransporter.PG

import net.testusuke.azisaba.itemtransporter.Main.Companion.plugin

/**
 * Created by testusuke on 2020/08/15
 * @author testusuke
 */
class PgMain {

    init {
        plugin.logger.info("preparing system for pg...")
        //  Vault
        VaultManager.setup()
        //  Command
        plugin.getCommand("it")?.executor = Command
        //  Listener
        plugin.getServer().pluginManager.registerEvents(Listener, plugin)
    }
}