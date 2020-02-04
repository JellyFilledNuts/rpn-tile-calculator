package de.fhdw.wip.rpntilecalculator.view.layout;

/**
 * Summary: Exception that occurs when the layout files cannot be read
 * Author:  Tim Jonas Meinerzhagen
 * Date:    2020/01/08
 */
public class StorageLoadingException extends Exception {

    public StorageLoadingException(String msg) {
        super(msg);
    }

    public StorageLoadingException() {
        this("The internal storage could not be read.");
    }

}
