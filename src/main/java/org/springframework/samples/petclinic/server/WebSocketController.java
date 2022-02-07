package org.springframework.samples.petclinic.server;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebSocketController {

    @GetMapping("/websocket")
    public String getPetsByOwnerSurname() {
        return "websocket/websocketpage";
    }
}
