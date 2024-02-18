package org.example.Behaviours.BuyBehs;

import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;
import lombok.extern.slf4j.Slf4j;
import org.example.Help.DtoBuyer;

@Slf4j
public class AnswerBuyBeh extends OneShotBehaviour {
    private DtoBuyer dtoBuyer;

    public AnswerBuyBeh(DtoBuyer dtoBuyer) {
        this.dtoBuyer = dtoBuyer;
    }

    @Override
    public void action() {
        ACLMessage message = new ACLMessage(ACLMessage.CFP);
        message.setContent(dtoBuyer.getMinPrice() + "");
        message.addReceiver(dtoBuyer.getAid());
        myAgent.send(message);
        log.info(this.myAgent.getLocalName() + " Отправил согласие на покупку агенту: " + dtoBuyer.getNameAgent());
    }
}
