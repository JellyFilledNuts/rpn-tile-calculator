package de.fhdw.wip.rpntilecalculator.core.ui;

public class StorageLoadingException extends Exception {

    public StorageLoadingException(String msg) {
        super(msg);
    }

    public StorageLoadingException() {
        this("The internal storage could not be read.");
    }

}
