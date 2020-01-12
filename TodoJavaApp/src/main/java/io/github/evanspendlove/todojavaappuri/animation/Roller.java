package io.github.evanspendlove.todojavaappuri.animation;

import javafx.animation.RotateTransition;
import javafx.scene.Node;
import javafx.util.Duration;

public class Roller
{
    private RotateTransition  rotateTransition ;

    public Roller(Node node)
    {
        rotateTransition  = new RotateTransition(Duration.millis(1000), node);
        rotateTransition.setByAngle(360);
        rotateTransition.setCycleCount(1);
        rotateTransition.setAutoReverse(false);
    }

    public void roll()
    {
        rotateTransition.playFromStart();
    }
}
