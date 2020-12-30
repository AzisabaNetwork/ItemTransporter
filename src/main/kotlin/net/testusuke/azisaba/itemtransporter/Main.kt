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
        const val prefix = "§e[§aItem§6Transport§e]§f"
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
        database = DataBase(DatabaseType.MARIADB)
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
    /*
    value type description
    - id int index
    - uuid char uuid
    - mcid char mcid
    - amount int amount
     */
    private val emeraldSQL = "create table IF NOT EXISTS item_transport_emerald\n" +
            "(\n" +
            "    id int auto_increment,\n" +
            "    uuid varchar(36) not null,\n" +
            "    mcid varchar(20) not null,\n" +
            "    amount int not null,\n" +
            "    constraint item_transport_emerald_pk\n" +
            "        primary key (id)\n" +
            ");"
    /*
    This table is purchase item
    value type description
    - id int index
    - uuid char uuid
    - mcid char mcid
    - item_id int item id
    - date datetime
     */
    private val itemSQL = "create table IF NOT EXISTS item_transport_item\n" +
            "(\n" +
            "    id int auto_increment,\n" +
            "    uuid varchar(36) not null,\n" +
            "    mcid varchar(20) not null,\n" +
            "    item_id int not null,\n" +
            "    date datetime not null,\n" +
            "    constraint item_transport_item_pk\n" +
            "        primary key (id)\n" +
            ");"
    /*
    This table is store item list
    value type description
    - id int index/item id
    - base64 text base64
    - price int price
    - available bool to buy is available?
    - stop bool stop exchange
     */
    private val itemListSQL = "create table IF NOT EXISTS item_transport_item_list\n" +
            "(\n" +
            "    id int auto_increment,\n" +
            "    base64 text not null,\n" +
            "    price int null,\n" +
            "    available boolean not null,\n" +
            "    stop boolean not null,\n" +
            "    constraint item_transport_item_list_pk\n" +
            "        primary key (id)\n" +
            ");"
    /*
    This is log table
    value type description
    - id int index
    - uuid char uuid
    - mcid char mcid
    - server char server name
    - action char action type
    - amount char amount of action
     */
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
    //   create three table
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