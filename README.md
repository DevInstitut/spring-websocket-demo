# spring-websocket-demo

Un exemple de création websocket avec Spring Boot.

Pour démarrer le projet :

```
./mvn spring-boot:run
```

Le Websocket est accessible via `ws://localhost:8080/ws`.

Exemple de code JavaScript pour communiquer avec le WebSocket :

```js
// création de la communication
const ws = new WebSocket("ws://localhost:8080/ws")

// une fois la communication établi, envoie d'un message
ws.onopen = (event) => ws.send('start')

// écoute de message venant du serveur
ws.onmessage = (msg) => {
    
    console.log("hihi reçu !", msg)

}
```
