package net.testusuke.azisaba.itemtransporter

import net.testusuke.azisaba.itemtransporter.Main.Companion.plugin
import java.sql.Connection
import java.sql.DriverManager
import java.sql.SQLException

class DataBase(private val databaseType: DatabaseType) {

    //  Connect information
    private var host: String? = null
    private var user: String? = null
    private var pass: String? = null
    private var port: String? = null
    private var db: String? = null

    init {
        //  Logger
        plugin.logger.info("DataBaseを読み込みます。")
        //  load config
        loadConfig()
        //  クラスローダー
        loadClass()
        //  Test Connect
        testConnect()
        //  Logger
        plugin.logger.info("DataBaseを読み込みました。")
    }

    private fun loadConfig() {
        host = plugin.config.getString("database.host")
        user = plugin.config.getString("database.user")
        pass = plugin.config.getString("database.pass")
        port = plugin.config.getString("database.port")
        db = plugin.config.getString("database.db")
    }

    private fun loadClass() {
        try {
            when(databaseType){
                DatabaseType.MARIADB -> {
                    Class.forName("org.mariadb.jdbc.Driver")
                }
                DatabaseType.MYSQL -> {
                    Class.forName("com.mysql.jdbc.Driver")
                }
            }

            plugin.logger.info("Load class.")
        } catch (e: ClassNotFoundException) {
            e.printStackTrace()
            plugin.logger.info("DataBase connection class not found!")
        }
    }

    fun getConnection(): Connection? {
        val connection: Connection
        try {
            connection = when(databaseType){
                DatabaseType.MARIADB -> {
                    DriverManager.getConnection("jdbc:mariadb://$host:$port/$db", user, pass)
                }
                DatabaseType.MYSQL -> {
                    DriverManager.getConnection("jdbc:mysql://$host:$port/$db?autoReconnect=true&useSSL=false", user, pass)
                }
            }
        } catch (e: SQLException) {
            e.printStackTrace()
            return null
        }
        return connection
    }

    private fun testConnect(): Boolean? {
        plugin.logger.info("接続テスト中....")
        if (getConnection() == null) {
            plugin.logger.info("接続に失敗しました。")
            return false
        }
        plugin.logger.info("接続に成功しました！")
        return true
    }

}

enum class DatabaseType{
    MYSQL,MARIADB
}