package rpgeeze.view;

import rpgeeze.util.cmd.Commandable;

/**
 * Superclass for the various screens that the game will have to display. The
 * most important method is display(), which will be called whenever the screen
 * must be painted. Additionally, each View must provide a CommandHandler so
 * that it may participate in the command framework. The methods changeFrom()
 * and changeTo() are called when the game state changes from and to this view,
 * respectively. They should be used, for example, to pause/resume the game when
 * moving away from the game screen.
 */

public abstract class View implements Commandable {
	public abstract void display();

	public void changeFrom() {
	}

	public void changeTo() {
	}
}
