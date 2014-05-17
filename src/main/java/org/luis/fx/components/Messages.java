package org.luis.fx.components;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedDeque;
import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import org.luis.fx.components.message.Type;

public class Messages extends Pane {

    private final Queue<Message> queue = new ConcurrentLinkedDeque<>();
    private final HBox hbox = new HBox();

    public Messages() {
        super();

        hbox.setSpacing(10d);
        getChildren().add(hbox);

    }

    public void showMessage(final String msg) {
        showMessage(msg, Type.INFO);
    }

    public void showMessage(final String msg, final Type type) {

        final Message message = new Message(msg, type);

        FadeTransition startFade = new FadeTransition(Duration.millis(200), message);
        startFade.setFromValue(0d);
        startFade.setToValue(1d);
        startFade.play();

        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(30), "showing", new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
            
                FadeTransition finalFade = new FadeTransition(Duration.millis(200), message);
                finalFade.setFromValue(1);
                finalFade.setToValue(0);
                finalFade.play();

                finalFade.setOnFinished(new EventHandler<ActionEvent>() {

                    @Override
                    public void handle(ActionEvent event) {
                        message.setVisible(false);
                        hbox.getChildren().remove(message);

                    }
                });

            }
        }));
        
        timeline.play();

    }
}
