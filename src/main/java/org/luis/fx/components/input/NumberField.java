/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.luis.fx.components.input;

import javafx.event.EventHandler;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

/**
 *
 * @author luis
 */
public class NumberField extends TextField implements EventHandler<KeyEvent> {

    public NumberField() {
        this.addEventFilter(KeyEvent.KEY_TYPED, this);
    }

    @Override
    public void handle(KeyEvent t) {
        char ar[] = t.getCharacter().toCharArray();
        char ch = ar[t.getCharacter().toCharArray().length - 1];
        if (!(ch >= '0' && ch <= '9')) {
            t.consume();
        }
    }

    public Integer getValue() {

        if (getText() == null || getText().isEmpty()) {
            return null;
        }

        return Integer.valueOf(getText());
    }

    public void setValue(Integer value) {
        if (value == null) {
            setText("");
        } else {
            setText(String.valueOf(value));
        }
    }
}
