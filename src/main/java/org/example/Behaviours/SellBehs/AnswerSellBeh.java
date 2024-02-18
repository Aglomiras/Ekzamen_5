package org.example.Behaviours.SellBehs;

import jade.core.AID;
import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Random;
@Slf4j
public class AnswerSellBeh extends Behaviour {
    private MessageTemplate messageTemplate;
    private List<String> booksSell;

    public AnswerSellBeh(List<String> booksSell) {
        this.booksSell = booksSell;
    }

    @Override
    public void onStart() {
        messageTemplate = MessageTemplate.MatchPerformative(ACLMessage.AGREE);
    }

    @Override
    public void action() {
        ACLMessage receive = myAgent.receive(messageTemplate);
        if (receive != null) {
            for (int i = 0; i < booksSell.size(); i++) {
                if (receive.getContent().equals(booksSell.get(i))) {
                    log.info(this.myAgent.getLocalName() + " имеет такую книгу!");
                    createMsg(formPrice(), receive.getSender());
                }
            }
        } else {
            block();
        }

    }

    @Override
    public boolean done() {
        return false;
    }

    public int formPrice() {
        Random random = new Random();
        return random.nextInt(200) + 200;
    }
    public void createMsg(int price, AID aid) {
        ACLMessage message = new ACLMessage(ACLMessage.INFORM);
        message.setContent(price + "");
        message.addReceiver(aid);
        myAgent.send(message);
    }
}
