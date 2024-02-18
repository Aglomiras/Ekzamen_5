package org.example.Behaviours.BuyBehs;

import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;
import lombok.extern.slf4j.Slf4j;
import org.example.Help.DfHelper;
@Slf4j
public class RequestBuyBeh extends OneShotBehaviour {
    private String nameBook;

    public RequestBuyBeh(String nameBook) {
        this.nameBook = nameBook;
    }

    @Override
    public void action() {
        ACLMessage message = new ACLMessage(ACLMessage.AGREE);
        message.setContent(nameBook);
        var ags = DfHelper.search(myAgent, "selling_books");
        ags.forEach(e -> message.addReceiver(e));
        myAgent.send(message);
        log.info(this.myAgent.getLocalName() + " Отправил сообщение о покупке книги: " + nameBook);
    }
}
