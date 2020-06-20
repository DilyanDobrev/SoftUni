package reflection.barracksWars.data;

import reflection.barracksWars.interfaces.Repository;
import reflection.barracksWars.interfaces.Unit;

import javax.naming.OperationNotSupportedException;
import java.util.Map;
import java.util.TreeMap;

public class UnitRepository implements Repository {

	private Map<String, Integer> amountOfUnits;

	public UnitRepository() {
		this.amountOfUnits = new TreeMap<>();
	}

	public void addUnit(Unit unit) {
		String unitType = unit.getClass().getSimpleName();
		if (!this.amountOfUnits.containsKey(unitType)) {
			this.amountOfUnits.put(unitType, 0);
		}

		int newAmount = this.amountOfUnits.get(unitType) + 1;
		this.amountOfUnits.put(unitType, newAmount);
	}

	public String getStatistics() {
		StringBuilder statBuilder = new StringBuilder();
		for (Map.Entry<String, Integer> entry : amountOfUnits.entrySet()) {
			String formatedEntry =
					String.format("%s -> %d%n", entry.getKey(), entry.getValue());
			statBuilder.append(formatedEntry);
		}
		statBuilder.setLength(
				statBuilder.length() - System.lineSeparator().length());

		return statBuilder.toString();
	}

	public void removeUnit(String unitType) throws OperationNotSupportedException {
		if (!this.amountOfUnits.containsKey(unitType)) {
			throw new OperationNotSupportedException("No such units in repository.");
		}
		int newValue = this.amountOfUnits.get(unitType) - 1;
		if (newValue == -1) {
			this.amountOfUnits.remove(unitType);
		} else {
			this.amountOfUnits.put(unitType, newValue);
		}
	}
}
