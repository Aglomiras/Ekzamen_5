package org.example.Help;

import jade.core.AID;
import lombok.Data;

@Data
public class DtoBuyer {
    private int minPrice = 0;
    private AID aid;
    private String nameAgent;
}
