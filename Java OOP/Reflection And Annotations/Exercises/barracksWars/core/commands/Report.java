package reflection.barracksWars.core.commands;

import reflection.barracksWars.interfaces.Inject;
import reflection.barracksWars.interfaces.Repository;


public class Report extends Command {
    @Inject
    private  Repository repository;

    protected Report(String[] data) {
        super(data);
    }

    @Override
    public String execute() {
        return this.repository.getStatistics();
    }
}
