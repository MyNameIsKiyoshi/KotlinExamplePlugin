package fur.kiyoshi.plugin.events

import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent

object TestEvent: Listener {

    @EventHandler
    fun onJoin(event: PlayerJoinEvent){
        val player = event.player
        event.joinMessage = "Welcome to the server, ${player.name}!"
    }

}