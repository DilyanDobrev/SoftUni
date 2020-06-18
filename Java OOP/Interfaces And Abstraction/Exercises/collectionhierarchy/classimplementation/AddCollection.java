package interfacesandabstraction.collectionhierarchy.classimplementation;

import interfacesandabstraction.collectionhierarchy.interfaces.Addable;

public class AddCollection extends Collection implements Addable {

    @Override
    public int add(String element) {
        super.getItems().add(0,element);
        return 0;
    }
}
