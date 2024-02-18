package org.example.Behaviours.BuyBehs;

import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import org.example.Help.DtoBuyer;

public class ReceiveBuyBeh extends Behaviour {
    private MessageTemplate messageTemplate;
    private DtoBuyer dtoBuyer;

    public ReceiveBuyBeh(DtoBuyer dtoBuyer) {
        this.dtoBuyer = dtoBuyer;
    }

    @Override
    public void onStart() {
        messageTemplate = MessageTemplate.MatchPerformative(ACLMessage.INFORM);
    }

    @Override
    public void action() {
        ACLMessage receive = myAgent.receive(messageTemplate);
        if (receive != null) {
            int price = Integer.parseInt(receive.getContent());

            System.out.println(receive.getSender().getLocalName() + " предлагает " + receive.getContent() + " за цену.");

            if (dtoBuyer.getMinPrice() == 0 || dtoBuyer.getMinPrice() > price) {
                dtoBuyer.setMinPrice(price);
                dtoBuyer.setAid(receive.getSender());
                dtoBuyer.setNameAgent(receive.getSender().getLocalName());
            }
        } else {
            block();
        }
    }

    @Override
    public boolean done() {
        return false;
    }
}
