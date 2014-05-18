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
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import org.luis.fx.components.message.Type;

/**
 *
 * @author luis
 */
public class Message extends Pane {

    private static final Logger log = Logger.getLogger(Message.class.getName());

    @FXML
    private Label lblMsg;

    @FXML
    private FlowPane flowPaneImgIcon;

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
        if (type != Type.INFO) {
            flowPaneImgIcon.getChildren().clear();
            switch (type) {
                case ERROR:
                    final Image error = new Image(Message.class.getResource("/image/error_32.png").getPath());
                    final ImageView imgError = new ImageView(error);
                    flowPaneImgIcon.getChildren().add(imgError);
                    break;
                case SUCCESS:
                    final Image success = new Image(Message.class.getResource("/image/success_32.png").getPath());
                    final ImageView imgSuccess = new ImageView(success);
                    flowPaneImgIcon.getChildren().add(imgSuccess);
                    break;
                case WARNING:
                    final Image warn = new Image(Message.class.getResource("/image/warning_32.png").getPath());
                    final ImageView imgWarn = new ImageView(warn);
                    flowPaneImgIcon.getChildren().add(imgWarn);
                    break;

                default:
                    final Image info = new Image(Message.class.getResource("/image/info_32.png").getPath());
                    final ImageView imgInfo = new ImageView(info);
                    flowPaneImgIcon.getChildren().add(imgInfo);
                    break;
            }
        }
    }
}
