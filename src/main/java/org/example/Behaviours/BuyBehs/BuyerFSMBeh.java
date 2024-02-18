package org.example.Behaviours.BuyBehs;

import jade.core.behaviours.FSMBehaviour;
import org.example.Help.DtoBuyer;

import java.util.List;

public class BuyerFSMBeh extends FSMBehaviour {
    private List<String> books;
    private DtoBuyer dtoBuyer1 = new DtoBuyer();;
    private DtoBuyer dtoBuyer2 = new DtoBuyer();;
    public BuyerFSMBeh(List<String> books) {
        this.books = books;
    }

    private final String
            FIRST = "request",
            SECOND = "second",
            PARALL_1 = "parall_1",
            PARALL_2 = "parall_2",
            ANSBUY_1 = "ansbuy_1",
            ANSBUY_2 = "ansbuy_2",
            FAIL = "Fail";


    @Override
    public void onStart() {
        this.registerFirstState(new RequestBuyBeh(books.get(0)), FIRST);
        this.registerState(new RequestBuyBeh(books.get(1)), SECOND);
        this.registerState(new ParallBuyerBeh(dtoBuyer1), PARALL_1);
        this.registerState(new ParallBuyerBeh(dtoBuyer2), PARALL_2);
        this.registerState(new AnswerBuyBeh(dtoBuyer1), ANSBUY_1);
        this.registerLastState(new AnswerBuyBeh(dtoBuyer2), ANSBUY_2);
        this.registerLastState(new Fail(), FAIL);

        this.registerDefaultTransition(FIRST, PARALL_1);
        this.registerTransition(PARALL_1, ANSBUY_1, 0);
        this.registerTransition(PARALL_1, ANSBUY_1, 1);

        this.registerDefaultTransition(ANSBUY_1, SECOND);
        this.registerDefaultTransition(SECOND, PARALL_2);
        this.registerTransition(PARALL_2, ANSBUY_2, 0);
        this.registerTransition(PARALL_2, FAIL, 1);
    }
}
