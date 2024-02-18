package org.example.Behaviours.BuyBehs;

import jade.core.behaviours.Behaviour;
import jade.core.behaviours.ParallelBehaviour;
import jade.core.behaviours.WakerBehaviour;
import lombok.extern.slf4j.Slf4j;
import org.example.Help.DtoBuyer;

@Slf4j
public class ParallBuyerBeh extends ParallelBehaviour {
    private Behaviour wakeupBeh;
    private ReceiveBuyBeh receiveBeh;
    private DtoBuyer dtoBuyer;

    public ParallBuyerBeh(DtoBuyer dtoBuyer) {
        super(WHEN_ANY);
        this.dtoBuyer = dtoBuyer;
    }

    @Override
    public void onStart() {
        receiveBeh = new ReceiveBuyBeh(dtoBuyer);
        wakeupBeh = new WakerBehaviour(myAgent, 5000) {
            boolean wake = false;

            @Override
            protected void onWake() {
                wake = true;
                log.info("TIME IS UP ParallBuyerBeh");
            }

            @Override
            public int onEnd() {
                return wake ? 0 : 1;
            }
        };

        this.addSubBehaviour(receiveBeh);
        this.addSubBehaviour(wakeupBeh);
    }

    @Override
    public int onEnd() {
        if (wakeupBeh.done()) {
            if (dtoBuyer.getMinPrice() != 0) {
                log.info(this.myAgent.getLocalName() + " the purchase is completed!");
                return 0;
            } else {
                log.info(this.myAgent.getLocalName() + " the sellers do not have this book...");
                return 1;
            }
        } else {
            if (dtoBuyer.getMinPrice() != 0) {
                log.info(this.myAgent.getLocalName() + " the purchase is completed!");
                return 0;
            } else {
                log.info(this.myAgent.getLocalName() + " the sellers do not have this book...");
                return 1;
            }
        }
    }
}
