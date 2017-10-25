package com.maja.airmes.beans.websocket

import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import org.springframework.web.socket.CloseStatus
import org.springframework.web.socket.TextMessage
import org.springframework.web.socket.WebSocketSession
import org.springframework.web.socket.handler.TextWebSocketHandler

/**
 * WebSocket handler for Air Sensor messages
 *
 * @author Maja Filakovic
 * @created 24.10.17.
 */
@Component
class AirmesWebSocketMessageHandler(val airmesEndpoint: AirmesWebSocketEndpoint) : TextWebSocketHandler() {

    private final val LOG = LoggerFactory.getLogger(AirmesWebSocketMessageHandler::class.java)

    /**
     * {@inheritDoc}
     */
    override fun handleTransportError(session: WebSocketSession?, exception: Throwable?) {
        LOG.error("Error while communicating with webSocket client: " + session?.remoteAddress?.address
                + ", error: " + exception?.message)
    }

    /**
     * {@inheritDoc}
     */
    override fun afterConnectionClosed(session: WebSocketSession?, status: CloseStatus?) {
        airmesEndpoint.closeSession(session!!)
        LOG.info("Connection closed: " + session.id)
    }

    /**
     * {@inheritDoc}
     */
    override fun handleTextMessage(session: WebSocketSession?, message: TextMessage?) {
        LOG.info("Received from: " + session?.id + ", message: " + message?.payload)
    }

    /**
     * {@inheritDoc}
     */
    override fun afterConnectionEstablished(session: WebSocketSession?) {
        airmesEndpoint.addSession(session!!)
        LOG.info("Connection established: " + session.id)
    }
}