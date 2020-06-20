package reflection.barracksWars.core.commands;

import reflection.barracksWars.interfaces.Inject;
import reflection.barracksWars.interfaces.Repository;
import reflection.barracksWars.interfaces.Unit;
import reflection.barracksWars.interfaces.UnitFactory;

public class Add extends Command {
    @Inject
    private Repository repository;
    @Inject
    private UnitFactory unitFactory;

    public Add(String[] data) {
        super(data);
    }

    @Override
    public String execute() {
        String unitType = getData()[1];
        Unit unitToAdd = this.unitFactory.createUnit(unitType);
        this.repository.addUnit(unitToAdd);

        return unitType + " added!";
    }
}
