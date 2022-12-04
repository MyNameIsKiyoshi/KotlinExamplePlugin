package fur.kiyoshi.plugin

import fur.kiyoshi.plugin.commands.TestCommand
import fur.kiyoshi.plugin.events.TestEvent
import fur.kiyoshi.plugin.utils.MySQL
import org.bukkit.Bukkit
import org.bukkit.plugin.java.JavaPlugin
import java.sql.SQLException
import kotlin.properties.Delegates

class Main : JavaPlugin() {

    companion object {
        var plugin: Main by Delegates.notNull()
        var SQL: MySQL by Delegates.notNull()
    }

    private fun initialize(){
        plugin = this
        saveDefaultConfig()
        config.options().copyDefaults(true)
        config.options().parseComments(true)
    }

    private fun database() {
        SQL = MySQL()
        try {
            SQL.connect()
        } catch (e: ClassNotFoundException) {
            Bukkit.getLogger()
                .severe("[MySQL] Error While Pulling Requests From Plugin, Check If Credentials Are Correct In config.yml")
            server.pluginManager.disablePlugin(this)
        } catch (e: SQLException) {
            Bukkit.getLogger()
                .severe("[MySQL] Error While Pulling Requests From Plugin, Check If Credentials Are Correct In config.yml")
            server.pluginManager.disablePlugin(this)
        }
        if (SQL.isConnected) {
            Bukkit.getLogger().info("[MySQL] Pulling MySQL Requests From Plugin...")
        }
    }

    private fun commands(){
        getCommand("test")!!.setExecutor(TestCommand())
        getCommand("test")!!.tabCompleter = TestCommand()
    }

    private fun events(){
        server.pluginManager.registerEvents(TestEvent, this)
    }

    override fun onEnable() {
        initialize()
        database()
        commands()
        events()
    }
}