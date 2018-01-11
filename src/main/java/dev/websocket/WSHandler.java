package dev.websocket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class WSHandler extends TextWebSocketHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(WSHandler.class);

    private List<WebSocketSession> sessions = new ArrayList<>();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        LOGGER.info("Connexion établie {}", session);
        this.sessions.add(session);
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        LOGGER.info("J'ai reçu un message texte {} de la part de {}", message, session);
        sessions.forEach(s -> {
            try {
                session.sendMessage(new TextMessage("bien reçu" + message.getPayload()));
            } catch (IOException e) {
                LOGGER.error("oops", e);
            }
        });
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        LOGGER.info("Oops j'ai du mal à communiquer avec {}. J'ai eu l'erreur {}", session, exception.getMessage());
        LOGGER.error("oops", exception);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        LOGGER.info("Connexion fermée {}", session);
    }
}
