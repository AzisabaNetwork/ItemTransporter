package net.testusuke.azisaba.itemtransporter.PG

import net.milkbowl.vault.economy.Economy
import net.testusuke.azisaba.itemtransporter.Main.Companion.plugin

/**
 * @author testusuke
 * Vault連携のためのオブジェクト
 *
 * 残高確認
 * VaultManager.economy?.getBalance(player)
 * 引き出し
 * VaultManager.economy?.withdrawPlayer(player,double)
 * 入金
 * VaultManager.economy?.depositPlayer(player,double)
 *
 */
object VaultManager {
    var enableEconomy = true
        private set

    var economy: Economy? = null
        private set

    fun setup() {
        enableEconomy = setupEconomy()
        if (enableEconomy) plugin.logger.info("Enable Vault")
    }

    private inline val enableVault
        get() = plugin.getServer().pluginManager.isPluginEnabled("Vault")

    private fun setupEconomy(): Boolean {
        if (!enableVault) {
            plugin.logger.info("Vault is not installed")
            return false
        }
        val rsp = plugin.getServer().servicesManager.getRegistration(Economy::class.java)
        if (rsp == null) {
            plugin.logger.info("Can not use Vault service")
            return false
        }
        economy = rsp.provider
        return true
    }
}