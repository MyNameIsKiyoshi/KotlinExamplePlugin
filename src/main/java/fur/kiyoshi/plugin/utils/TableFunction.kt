package fur.kiyoshi.plugin.utils

import fur.kiyoshi.plugin.Main.Companion.SQL
import org.bukkit.entity.Player
import java.sql.SQLException
import java.sql.Statement


object TableFunction {

    @Throws(SQLException::class)
    fun createEmptyTable(player: Player) {
        val statement: Statement = SQL.connection!!.createStatement()
        statement.executeUpdate("CREATE TABLE IF NOT EXISTS " + player.name + " (id INT NOT NULL AUTO_INCREMENT, api VARCHAR(255), PRIMARY KEY (id))")
    }

    @Throws(SQLException::class)
    fun createTable(player: Player, api: String) {
        val statement: Statement = SQL.connection!!.createStatement()
        statement.executeUpdate("CREATE TABLE IF NOT EXISTS " + player.name + " (id INT NOT NULL AUTO_INCREMENT, api VARCHAR(255), PRIMARY KEY (id))")
        statement.executeUpdate("INSERT INTO " + player.name + " (api) VALUES ('$api')")
    }

    @Throws(SQLException::class)
    fun dropTable(player: Player) {
        val statement: Statement = SQL.connection!!.createStatement()
        statement.executeUpdate("DROP TABLE " + player.name)
    }

    @Throws(SQLException::class)
    fun isInTable(player: Player): Boolean {
        val statement: Statement = SQL.connection!!.createStatement()
        val result = statement.executeQuery("SELECT * FROM " + player.name)
        return result.next()
    }

    @Throws(SQLException::class)
    fun removeTable(player: Player) {
        val statement: Statement = SQL.connection!!.createStatement()
        statement.executeUpdate("DELETE FROM " + player.name)
    }

    @Throws(SQLException::class)
    fun removeValue(player: Player, value: String) {
        val statement: Statement = SQL.connection!!.createStatement()
        statement.executeUpdate("DELETE FROM " + player.name + " WHERE api = '$value'")
    }

    @Throws(SQLException::class)
    fun removeValue(player: Player, value: Int) {
        val statement: Statement = SQL.connection!!.createStatement()
        statement.executeUpdate("DELETE FROM " + player.name + " WHERE id = '$value'")
    }


    @Throws(SQLException::class)
    fun tableExists(player: Player): Boolean {
        val statement: Statement = SQL.connection!!.createStatement()
        val resultSet = statement.executeQuery("SHOW TABLES LIKE '" + player.name + "'")
        return resultSet.next()
    }

    @Throws(SQLException::class)
    fun deleteColumnWhenEmpty(player: Player) {
        val statement: Statement = SQL.connection!!.createStatement()
        statement.executeUpdate("DELETE FROM " + player.name + " WHERE api = 'None'")
    }

    @Throws(SQLException::class)
    fun isColumnEmpty(player: Player): Boolean {
        val statement: Statement = SQL.connection!!.createStatement()
        val resultSet = statement.executeQuery("SELECT * FROM " + player.name + " WHERE api = 'None'")
        return resultSet.next()
    }

    @Throws(SQLException::class)
    fun tableNotExists(player: Player): Boolean {
        val statement: Statement = SQL.connection!!.createStatement()
        val resultSet = statement.executeQuery("SHOW TABLES LIKE '" + player.name + "'")
        return !resultSet.next()
    }

    @Throws(SQLException::class)
    fun getTable(player: Player): String {
        val statement: Statement = SQL.connection!!.createStatement()
        val resultSet = statement.executeQuery("SELECT * FROM " + player.name)
        return if (resultSet.next()) {
            resultSet.getString("api")
        } else {
            "None"
        }
    }

    @Throws(SQLException::class)
    fun getAPI(player: Player): String {
        val statement: Statement = SQL.connection!!.createStatement()
        val resultSet = statement.executeQuery("SELECT * FROM " + player.name)
        return if (resultSet.next()) {
            resultSet.getString("api")
        } else {
            "None"
        }
    }


    fun generateAPIKey(): String {
        val chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789"
        val sb = StringBuilder(15)
        for (i in 0..14) {
            val index = (chars.length * Math.random()).toInt()
            sb.append(chars[index])
        }
        return sb.toString()
    }

}
