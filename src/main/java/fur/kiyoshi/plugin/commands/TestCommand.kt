package fur.kiyoshi.plugin.commands

import fur.kiyoshi.plugin.utils.Format
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.command.TabCompleter
import org.bukkit.entity.Player
import java.util.*
import kotlin.collections.ArrayList

class TestCommand: CommandExecutor, TabCompleter {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if(sender !is Player){
            sender.sendMessage("You must be a player to use this command!")
            return true
        }

        val player = sender as Player

        if(args.isNotEmpty()){
            when(args[0]){
                "one" -> {
                    player.sendMessage(Format.color("This is a test message!"))
                }
            }
        } else {
            player.sendMessage("You must provide an argument!")
        }

        return true
    }
    private fun a(s: MutableList<String>, arg: String, @Suppress("SameParameterValue") test: String) {
        if (test.startsWith(arg.lowercase(Locale.getDefault()))) s.add(test)
    }

    override fun onTabComplete(
        arg0: CommandSender,
        arg1: Command,
        arg2: String,
        args: Array<String?>
    ): List<String>? {
        val s: List<String> = ArrayList()
        if (arg1.name == "test") {
            if (args.size == 1) {
                a(s as MutableList<String>, args[0]!!, "one")
                a(s, args[0]!!, "two")
                return s
            }
        }
        return null
    }
}