package morningcheck

import chat.ChatObject
import grails.transaction.Transactional
import com.corundumstudio.socketio.listener.*;
import com.corundumstudio.socketio.*;

@Transactional
class SocketService {

    public static SocketIOServer server
    static transactional = false

    def serviceMethod() {

    }

    def start(){
        Configuration config = new Configuration();
        config.setHostname("localhost");
        config.setPort(9092);

        server = new SocketIOServer(config);

        server.addEventListener("chatevent", ChatObject.class, new DataListener<ChatObject>() {
            @Override
            public void onData(SocketIOClient client, ChatObject data, AckRequest ackRequest) {
                log.info(data.message)
                server.getBroadcastOperations().sendEvent("chatevent", data);
            }
        });


        server.start();
    }

    def broadCast(){
    }
}
