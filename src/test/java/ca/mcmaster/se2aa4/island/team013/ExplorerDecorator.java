package ca.mcmaster.se2aa4.island.team013;

import eu.ace_design.island.bot.IExplorerRaid;

public class ExplorerDecorator implements IExplorerRaid {

    private Explorer e = new Explorer();

    @Override
    public void initialize(String s) {
        e.initialize(s);
    }

    @Override
    public String takeDecision() {
        String ss = e.takeDecision();
        System.out.println(ss);
        return ss;
        
    }

    @Override
    public void acknowledgeResults(String s) {
        e.acknowledgeResults(s);
    }

    @Override
    public String deliverFinalReport() {
        return e.deliverFinalReport();
    }

}
