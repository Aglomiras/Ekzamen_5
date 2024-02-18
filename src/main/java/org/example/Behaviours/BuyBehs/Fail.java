package org.example.Behaviours.BuyBehs;

import jade.core.behaviours.OneShotBehaviour;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Fail extends OneShotBehaviour {
    @Override
    public void action() {
        log.info(this.myAgent.getLocalName() + " - Fail");
    }

    @Override
    public int onEnd() {
        return 0;
    }
}
