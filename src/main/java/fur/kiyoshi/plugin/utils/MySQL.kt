package fur.kiyoshi.plugin.utils

import fur.kiyoshi.plugin.Main.Companion.plugin
import java.sql.Connection
import java.sql.DriverManager
import java.sql.SQLException


class MySQL {
    private val host: String? = plugin.config.getString("MySQL.Host")
    private val port: String? = plugin.config.getString("MySQL.Port")
    private val database: String? = plugin.config.getString("MySQL.DataBase")
    private val username: String? = plugin.config.getString("MySQL.UserName")
    private val password: String? = plugin.config.getString("MySQL.Password")
    var connection: Connection? = null
    val isConnected: Boolean
        get() = connection != null

    @Throws(ClassNotFoundException::class, SQLException::class)
    fun connect() {
        if (!isConnected) {
            connection = try {
                Class.forName("com.mysql.cj.jdbc.Driver")
                val unicode = "useSSL=false&autoReconnect=true&useUnicode=yes&characterEncoding=UTF-8"
                DriverManager.getConnection(
                    "jdbc:mysql://" +
                            host + ":" + port + "/" + database + "?" + unicode, username, password
                )
            } catch (ex: Exception) {
                plugin.logger.severe(ex.message)
                throw RuntimeException(ex)
            }
        }
    }

    fun disconnect() {
        if (isConnected) {
            try {
                connection!!.close()
            } catch (e: SQLException) {
                e.printStackTrace()
            }
        }
    }

    fun getconnection(): Connection? {
        return connection
    }
}
