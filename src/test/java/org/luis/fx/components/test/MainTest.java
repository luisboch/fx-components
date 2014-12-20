package org.luis.fx.components.test;

import javafx.application.Application;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.luis.fx.components.FluidScrollPane;
import org.luis.fx.components.Messages;
import org.luis.fx.components.message.Type;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Luis Boch<luis.c.boch@gmail.com>
 */
public class MainTest extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        final AnchorPane pane = new AnchorPane();

        pane.setPrefHeight(400);
        pane.setPrefWidth(600);
        pane.setMinHeight(400);
        pane.setMinWidth(600);
        pane.setMaxHeight(400);
        pane.setMaxWidth(600);

        final Messages m = new Messages();

        AnchorPane.setRightAnchor(m, 0d);
        AnchorPane.setTopAnchor(m, 0d);

        pane.getChildren().add(m);
        
        pane.setOnMouseClicked((e) -> {
            m.showMessage("Clicked!");
        });
        
        pane.setOnMouseReleased((e) -> {
            m.showMessage("Released!", Type.ERROR);
        });

        primaryStage.setScene(new Scene(pane));
        primaryStage.show();

    }

//    @Test
    public void test() {

    }

    public static void main(String[] args) {
        Application.launch();
    }
}
