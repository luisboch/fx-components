package org.luis.fx.components.test;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.junit.Test;
import org.luis.fx.components.FluidScrollPane;

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

        final FlowPane flowPane = new FlowPane();
        final FluidScrollPane scrollPane = new FluidScrollPane(flowPane);

        scrollPane.setIncrementScrollFactor(1.5);
        
        AnchorPane.setBottomAnchor(scrollPane, 0d);
        AnchorPane.setTopAnchor(scrollPane, 0d);
        AnchorPane.setLeftAnchor(scrollPane, 0d);
        AnchorPane.setRightAnchor(scrollPane, 0d);

        final Scene scene = new Scene(scrollPane);

        flowPane.setOrientation(Orientation.VERTICAL);

        for (int i = 0; i < 30; i++) {
            final Pane p = new Pane();

            p.setPrefHeight(80);
            p.setPrefWidth(200);
            p.setStyle("-fx-background-color: " + ((i % 2) == 0 ? "#FFF" : "#AAA"));

            p.setOnMouseClicked((e) -> {
                System.out.println("Child clicked");
            });

            p.setOnMouseReleased((e) -> {
                System.out.println("Child clicked");
            });
            flowPane.getChildren().add(p);
        }

        primaryStage.setScene(scene);
        primaryStage.show();

    }

//    @Test
    public void test() {
        Application.launch();
    }
}
