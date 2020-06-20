package reflection.barracksWars.core;

import reflection.barracksWars.interfaces.*;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

public class CommandInterpreterImpl implements CommandInterpreter {
    private static final String COMMAND_PACKAGE = "reflection.barracksWars.core.commands.";

    private Repository repository;
    private UnitFactory unitFactory;

    public CommandInterpreterImpl(Repository repository, UnitFactory unitFactory) {
        this.repository = repository;
        this.unitFactory = unitFactory;
    }

    @Override
    public Executable interpretCommand(String[] data, String commandName) {

        String command = getCorrectName(data[0]);

        Executable executable = null;
        try {
            Class<?> clazz = Class.forName(COMMAND_PACKAGE + command);
            Constructor<?> declaredConstructor =
                    clazz.getDeclaredConstructor(String[].class);
            declaredConstructor.setAccessible(true);
            executable = (Executable) declaredConstructor.newInstance((Object) data);

            setDependencies(executable);
        } catch (ClassNotFoundException
                | NoSuchMethodException
                | InstantiationException
                | IllegalAccessException
                | InvocationTargetException e) {
            e.printStackTrace();
        }

        return executable;
    }

    private void setDependencies(Executable executable) {
        Field[] executableFields = executable.getClass().getDeclaredFields();
        Field[] currentFields = this.getClass().getDeclaredFields();

        for (Field executableField : executableFields) {
            Inject annotation = null;
            try {
                annotation = executableField.getAnnotation(Inject.class);
            } catch (ClassCastException e) {
                continue;
            }
            for (Field currentField : currentFields) {
                if (currentField.getType().equals(executableField.getType())) {
                    executableField.setAccessible(true);
                    try {
                        executableField.set(executable, currentField.get(this));
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    private String getCorrectName(String name) {
        return Character.toUpperCase(name.charAt(0)) +
                name.substring(1);
    }
}
