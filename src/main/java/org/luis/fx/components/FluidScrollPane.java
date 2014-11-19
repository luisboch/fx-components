/*
 * FluidScrollPane.java
 */
package org.luis.fx.components;

import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;

/**
 *
 * @author Luis Boch<luis.c.boch@gmail.com>
 */
public class FluidScrollPane extends javafx.scene.control.ScrollPane {

    private boolean isScrolling = false;

    private Pos originalScrollPos;

    private double incrementScrollFactor = 1.5d;

    public FluidScrollPane() {
        super();
        init();
    }

    public FluidScrollPane(Node content) {
        super(content);
        init();
    }

    private void init() {

        final ScrollPane mySelf = this;

        addEventFilter(MouseEvent.ANY, (MouseEvent e) -> {
            if (isScrolling) {
                if (e.getEventType().equals(MouseEvent.MOUSE_RELEASED)) {
                    isScrolling = false;
                } else if (e.getEventType().equals(MouseEvent.MOUSE_DRAGGED)) {

                    double xMoved = e.getX() - originalScrollPos.getX();
                    double yMoved = e.getY() - originalScrollPos.getY();

                    xMoved = (xMoved + (xMoved * incrementScrollFactor)) * -1;
                    yMoved = (yMoved + (yMoved * incrementScrollFactor)) * -1;

                    double viewWidth = mySelf.getViewportBounds().getWidth();
                    double viewHeight = mySelf.getViewportBounds().getHeight();

                    double percentMovedX = xMoved / mySelf.getContent().getBoundsInLocal().getWidth();
                    double percentMovedY = yMoved / mySelf.getContent().getBoundsInLocal().getHeight();

                    xMoved = viewWidth * percentMovedX;
                    yMoved = viewHeight * percentMovedY;

                    double resultX = originalScrollPos.getViewPortX() + xMoved;
                    double resultY = originalScrollPos.getViewPortY() + yMoved;

                    double resultPercentX = resultX / viewWidth;
                    double resultPercentY = resultY / viewHeight;

                    mySelf.setVvalue(resultPercentY);
                    mySelf.setHvalue(resultPercentX);

                }
                e.consume();
            }
        });

        setOnMousePressed((e) -> {
            isScrolling = true;
            originalScrollPos
                    = new Pos(
                            e.getX(), e.getY(),
                            mySelf.getHvalue(),
                            mySelf.getVvalue(),
                            mySelf.getViewportBounds());
        });
    }

    public double getIncrementScrollFactor() {
        return incrementScrollFactor;
    }

    public void setIncrementScrollFactor(double incrementScrollFactor) {
        this.incrementScrollFactor = incrementScrollFactor;
    }

    private static class Pos {

        private Double x, y, sx, sy, viewPortX, viewPortY;

        public Pos() {
        }

        public Pos(Double x, Double y, Double sx, Double sy, Bounds bounds) {
            this.x = x;
            this.y = y;
            this.sx = sx;
            this.sy = sy;

            this.viewPortX = sx * bounds.getWidth();
            this.viewPortY = sy * bounds.getHeight();

        }

        public Double getSx() {
            return sx;
        }

        public void setSx(Double sx) {
            this.sx = sx;
        }

        public Double getSy() {
            return sy;
        }

        public void setSy(Double sy) {
            this.sy = sy;
        }

        public Double getX() {
            return x;
        }

        public void setX(Double x) {
            this.x = x;
        }

        public Double getY() {
            return y;
        }

        public void setY(Double y) {
            this.y = y;
        }

        public Double getViewPortX() {
            return viewPortX;
        }

        public void setViewPortX(Double viewPortX) {
            this.viewPortX = viewPortX;
        }

        public Double getViewPortY() {
            return viewPortY;
        }

        public void setViewPortY(Double viewPortY) {
            this.viewPortY = viewPortY;
        }

    }
}
