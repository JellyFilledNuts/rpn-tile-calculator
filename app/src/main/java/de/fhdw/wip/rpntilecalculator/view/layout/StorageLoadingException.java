package de.fhdw.wip.rpntilecalculator.view.layout;

public class StorageLoadingException extends Exception {

    public StorageLoadingException(String msg) {
        super(msg);
    }

    public StorageLoadingException() {
        this("The internal storage could not be read.");
    }

}
