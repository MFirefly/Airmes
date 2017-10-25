package com.maja.airmes.beans.configs

import com.maja.airmes.beans.websocket.AirmesWebSocketMessageHandler
import org.springframework.context.annotation.Configuration
import org.springframework.web.socket.config.annotation.EnableWebSocket
import org.springframework.web.socket.config.annotation.WebSocketConfigurer
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry

/**
 * Configuration for webSocket
 *
 * @author Maja Filakovic
 * @created 24.10.17.
 */
@Configuration
@EnableWebSocket
class WebSocketConfig(val airmesWebSocketHandler: AirmesWebSocketMessageHandler) : WebSocketConfigurer {

    /**
     * Registers webSocket handler for the connection
     */
    override fun registerWebSocketHandlers(registry: WebSocketHandlerRegistry?) {
        registry?.addHandler(airmesWebSocketHandler, "/airmes/socket")?.setAllowedOrigins("*")
    }
}