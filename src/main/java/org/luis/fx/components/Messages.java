package org.luis.fx.components;

import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import org.luis.fx.components.message.Type;

public class Messages extends Pane {

    private final VBox hbox = new VBox();

    public Messages() {
        super();

        hbox.setSpacing(10d);
        getChildren().add(hbox);

        this.setVisible(false);

        hbox.setPrefHeight(getHeight());
        hbox.setPrefWidth(getWidth());
    }

    public void showMessage(final String msg) {
        showMessage(msg, Type.INFO);
    }

    public void showMessage(final String msg, final Type type) {

        final Messages myself = this;

        myself.setVisible(true);

        final Message message = new Message(msg, type);

        final FadeTransition startFade = new FadeTransition(Duration.millis(200), message);
        startFade.setFromValue(0d);
        startFade.setToValue(1d);
        startFade.play();
        
        hbox.getChildren().add(message);

        updateHeigth(message);
        
        
        EventHandler finishEvt = new EventHandler() {

            @Override
            public void handle(Event event) {

                final FadeTransition finalFade = new FadeTransition(Duration.millis(200), message);
                finalFade.setFromValue(1);
                finalFade.setToValue(0);
                finalFade.play();
                
                finalFade.setOnFinished(new EventHandler<ActionEvent>() {

                    @Override
                    public void handle(ActionEvent event) {
                        message.setVisible(false);
                        hbox.getChildren().remove(message);
                        updateHeigth(message);
                        if (hbox.getChildren().isEmpty()) {
                            myself.setVisible(false);
                        }
                    }
                });

            }
        };
        
        
        final Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(30), "showing", finishEvt));
        message.setOnMouseReleased(finishEvt);
        
        timeline.play();

    }

    private void updateHeigth(Message m) {

        if (hbox.getChildren().isEmpty()) {
            hbox.setMinHeight(0d);
            this.setMinHeight(0d);
            return;
        }

        final Double height = (m.getPrefHeight() + hbox.getSpacing()) * hbox.getChildren().size();

        hbox.setMinHeight(height);
        this.setMinHeight(height);
        
    }
}
