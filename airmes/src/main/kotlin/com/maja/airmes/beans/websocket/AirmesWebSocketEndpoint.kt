package com.maja.airmes.beans.websocket

import com.maja.airmes.dtos.NewSensorData
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import org.springframework.web.socket.TextMessage
import org.springframework.web.socket.WebSocketSession

/**
 * Endpoint which handles sending data to webSocket activeSessions
 *
 * @author Maja Filakovic
 * @created 24.10.17.
 */
@Component
class AirmesWebSocketEndpoint {

    private final val LOG = LoggerFactory.getLogger(AirmesWebSocketEndpoint::class.java)

    val activeSessions = HashMap<String, WebSocketSession>()

    /**
     * Adds new session to map of active sessions
     * @param session New session
     */
    fun addSession(session: WebSocketSession) {
        activeSessions.put(session.id, session)
    }

    /**
     * Closes session and removes it from map of active activeSessions
     * @param session Closed session
     */
    fun closeSession(session: WebSocketSession) {
        session.close()
        activeSessions.remove(session.id)
    }

    /**
     * Sends new data to all open activeSessions
     * @param newData New data that should be sent
     */
    fun sendNewDataToActiveSessions(newData: NewSensorData) {
        activeSessions.forEach({ (id, session) ->
            LOG.info("MapperString: " + newData.toString())
            val message = TextMessage(newData.toString())
            LOG.info("Websocket message: " + message)
            session.sendMessage(message)
        })
    }
}