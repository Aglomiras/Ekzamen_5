package org.example.Agents;

import jade.core.Agent;
import lombok.extern.slf4j.Slf4j;
import org.example.Behaviours.BuyBehs.BuyerFSMBeh;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
@Slf4j
public class AgentBuyer extends Agent {
    @Override
    protected void setup() {
        log.info(this.getLocalName() + " was born!");
        this.addBehaviour(new BuyerFSMBeh(getBooks()));
    }
    public List<String> getBooks() {
        List<String> listBook = List.of("Красная шапочка", "Золотая рыбка", "Драконий жемчуг");
        ArrayList<String> books = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < 2; i++) {
            books.add(listBook.get(random.nextInt(3)));
        }
        log.info(this.getLocalName() + " " + books);
        return books;
    }
}
