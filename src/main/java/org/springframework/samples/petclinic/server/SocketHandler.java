package org.springframework.samples.petclinic.server;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.owner.Owner;
import org.springframework.samples.petclinic.owner.OwnerRepository;
import org.springframework.samples.petclinic.owner.Pet;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@Component
public class SocketHandler extends TextWebSocketHandler {
    @Autowired
    private OwnerRepository ownerRepository;

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        Map value = new ObjectMapper().readValue(message.getPayload(), Map.class);

        Collection<Owner> owners = ownerRepository.findByLastName((String) value.get("name"));
        session.sendMessage(new TextMessage("Owners found by this last name: " + owners));

        List<Pet> listOfPets = new ArrayList<>();

        owners.forEach(o -> listOfPets.addAll(o.getPets()));
        session.sendMessage(new TextMessage("Pets of owner with this last name: " + listOfPets));
    }
}
