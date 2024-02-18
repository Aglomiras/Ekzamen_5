package org.example.Agents;

import jade.core.Agent;
import lombok.extern.slf4j.Slf4j;
import org.example.Behaviours.SellBehs.AnswerSellBeh;
import org.example.Help.DfHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Slf4j
public class AgentSeller extends Agent {
    @Override
    protected void setup() {
        log.info(this.getLocalName() + " was born!");
        DfHelper.register(this, "selling_books");
        log.info(this.getLocalName() + " passed the registration!");

        this.addBehaviour(new AnswerSellBeh(getBooks()));
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
