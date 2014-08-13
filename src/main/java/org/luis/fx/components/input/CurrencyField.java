package org.luis.fx.components.input;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.Currency;
import java.util.Locale;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

/**
 * @author luis
 */
public class CurrencyField extends TextField implements ChangeListener<Boolean>, EventHandler<KeyEvent> {

    private static final Logger log = Logger.getLogger(CurrencyField.class.getName());

    private final String currencySymbol;
    private final DecimalFormat formatter = new DecimalFormat("###,###.00");

    private Double realValue;

    public CurrencyField() {
        this("");
    }

    public CurrencyField(String text) {
        super(text);
        setValue(text);
        currencySymbol
                = Currency.getInstance(Locale.getDefault()).
                getSymbol(Locale.getDefault());

        realValue = getValue(text);
        setupListeners();
    }

    public void setValue(Double value) {
        realValue = value;
        updateValue();
    }

    private void setValue(String text) {
        realValue = getValue(text);
        updateValue();
    }

    private String format(Double value) {
        if (value == null) {
            return "";
        }
        return formatter.format(value);
    }

    private Double getValue(String text) {

        if (text == null || text.equals("")) {
            return null;
        }

        try {
            return formatter.parse(text).doubleValue();
        } catch (ParseException ex) {

            throw new NumberFormatException("Failed to parse value " + text);
        }
    }

    public Double getValue() {
        if (isFocused()) {
            return getValue(getText());
        } else {
            return getValue(getText().replace(currencySymbol + " ", ""));
        }
    }

    private void updateValue() {
        updateValue(isFocused());
    }

    private void updateValue(boolean focused) {
        if (!focused) {
            setText(currencySymbol + " " + format(realValue));
        } else {
            setText(format(realValue));
        }
    }

    @Override
    public void changed(
            ObservableValue<? extends Boolean> observable,
            Boolean oldValue, Boolean newValue) {

        if (newValue == null) {
            return;
        }

        updateValue(newValue);
    }

    @Override
    public void handle(KeyEvent t) {
        
        char ar[] = t.getCharacter().toCharArray();
        char ch = ar[t.getCharacter().toCharArray().length - 1];
        
        if (!(ch >= '0' && ch <= '9') && (ch != ',' || getText().contains(",")))  {
            t.consume();
        }
        
        realValue = getValue(getText());
    }

    private void setupListeners() {
        focusedProperty().addListener(this);
        addEventFilter(KeyEvent.KEY_TYPED, this);
    }
}
