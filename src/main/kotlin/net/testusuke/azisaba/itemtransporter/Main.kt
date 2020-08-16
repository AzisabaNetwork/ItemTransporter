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
        val prefix = "§e[§aItem§6Transport§e]§f"
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
        //  Create Table
        createTable()
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

    //  SQL
    //  emerald
    private val emeraldSQL = "create table IF NOT EXISTS item_transport_emerald\n" +
            "(\n" +
            "    id int auto_increment,\n" +
            "    uuid varchar(36) not null,\n" +
            "    mcid varchar(20) not null,\n" +
            "    amount int not null,\n" +
            "    constraint item_transport_emerald_pk\n" +
            "        primary key (id)\n" +
            ");"
    private val itemSQL = "create table IF NOT EXISTS item_transport_item\n" +
            "(\n" +
            "    id int auto_increment,\n" +
            "    uuid varchar(36) not null,\n" +
            "    mcid varchar(20) not null,\n" +
            "    item_id int not null,\n" +
            "    item_base64 text not null,\n" +
            "    date datetime not null,\n" +
            "    constraint item_transport_item_pk\n" +
            "        primary key (id)\n" +
            ");"
    private val itemListSQL = "create table IF NOT EXISTS item_transport_item_list\n" +
            "(\n" +
            "    id int auto_increment,\n" +
            "    base64 text not null,\n" +
            "    price int null,\n" +
            "    available boolean not null,\n" +
            "    constraint item_transport_item_list_pk\n" +
            "        primary key (id)\n" +
            ");"
    private val logSQL = "create table IF NOT EXISTS item_transport_log\n" +
            "(\n" +
            "    id int auto_increment,\n" +
            "    uuid varchar(36) not null,\n" +
            "    mcid varchar(20) null,\n" +
            "    server varchar(10) not null,\n" +
            "    action text not null,\n" +
            "    amount int not null,\n" +
            "    constraint item_transport_log_pk\n" +
            "        primary key (id)\n" +
            ");"
    private fun createTable() {
        val connection = database.getConnection()
        if (connection == null) {
            plugin.logger.info("Can't get connection!")
            return
        }
        //  Statement
        val statement = connection.createStatement()
        //  execute
        statement.execute(emeraldSQL)
        statement.execute(itemSQL)
        statement.execute(itemListSQL)
        statement.execute(logSQL)

        //  close
        statement.close()
        connection.close()

    }
}