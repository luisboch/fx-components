package org.luis.fx.components;

import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import org.luis.fx.components.message.Type;

/**
 *
 * @author luis
 */
public class Message extends Pane {

    private static final Logger log = Logger.getLogger(Message.class.getName());

    private static final Image error;
    private static final Image info;
    private static final Image success;
    private static final Image warn;

    static {
        error = new Image(Message.class.getResource("/image/error_32.png").getPath());
        info = new Image(Message.class.getResource("/image/info_32.png").getPath());
        success = new Image(Message.class.getResource("/image/success_32.png").getPath());
        warn = new Image(Message.class.getResource("/image/warning_32.png").getPath());
    }

    @FXML
    private Label lblMsg;

    @FXML
    private ImageView imgIcon;

    public Message(final String msg, final Type type) {

        URL location = getClass().getResource("/fxml/Message.fxml");
        
        FXMLLoader loader = new FXMLLoader(location);

        loader.setController(this);
        loader.setRoot(this);

        try {
            loader.load();
        } catch (IOException ex) {
            log.log(Level.SEVERE, null, ex);
            throw new RuntimeException(ex);
        }

        set(msg, type);
    }

    private void set(String msg, Type type) {
        
        lblMsg.setText(msg);
        
        switch (type) {
            case ERROR:
                imgIcon.setImage(error);
                break;
            case INFO:
                imgIcon.setImage(info);
                break;
            case SUCCESS:
                imgIcon.setImage(success);
                break;
            case WARNING:
                imgIcon.setImage(warn);
                break;
        }
        
    }
}
