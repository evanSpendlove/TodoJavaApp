package io.github.evanspendlove.todojavaappuri.animation;

import javafx.animation.TranslateTransition;
import javafx.scene.Node;
import javafx.util.Duration;

/*
    Shaker Class
    Used for animating UI elements to indicate that something has gone wrong or is not correct.
 */
public class Shaker
{
    private TranslateTransition translateTransition;

    public Shaker(Node node)
    {
        // Initialise new TranslateTransition
        translateTransition = new TranslateTransition(Duration.millis(50), node);

        translateTransition.setFromX(0f); // Set X initial coordinate
        translateTransition.setByX(10f); // Set distance to move
        translateTransition.setCycleCount(6); // Set number of times to cycle
        translateTransition.setAutoReverse(true); // Set AutoReverse to true, which means the transition works the same in reverse
    }

    public void shake()
    {
        translateTransition.playFromStart();
    }
}
