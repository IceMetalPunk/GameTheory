/* TODO:
 * 
 * Restructure event handling with Visitor pattern
 * --> and create GTTriggeredEvent superclass to cover simple triggered events
 * 
 * Audio
 * More events
 * Room properties such as background color, background image, etc.
 * Wrap swing components
 * Animated sprites
 * 
 */

package com.icemetalpunk.testpackage;

import java.io.IOException;

import com.icemetalpunk.gametheory.guis.GTBackground;
import com.icemetalpunk.gametheory.guis.Game;
import com.icemetalpunk.gametheory.guis.Room;
import com.icemetalpunk.gametheory.objects.GTObject;
import com.icemetalpunk.gametheory.sprites.GTSprite;

public class TestMain {

	public static GTBackground BG1;
	public static GTBackground BG2;
	public static Game game;

	public static void main(String[] args) {
		game = new Game();
		Room room1 = new Room(200, 200, "Testing Room 1!");
		Room room2 = new Room(800, 600, "Room 2 is here!");

		try {
			BG1 = new GTBackground(TestMain.class.getResource("Bg.png"));
			BG2 = new GTBackground(TestMain.class.getResource("falsesymmetry.png"));
			room1.setBackground(BG1);
			room2.setBackground(BG1);
		} catch (IOException e) {
			System.err.println("ERROR: Could not load background.");
		}

		room1.attach(new GlobalClick());
		room2.attach(new MyStepEvent());
		room2.attach(new MyRoomStartEvent());
		room2.attach(new MyRoomEndEvent());
		room2.attach(new MyResizer());

		room2.setSpeed(30);
		GTObject testInstance = new TestObject(50, 50);
		GTSprite mainSpr = GTSprite.load(TestMain.class.getResource("falsesymmetry.png"));
		testInstance.setSprite(mainSpr);
		testInstance.hspeed = 1;
		testInstance.gravity = 0.2;
		testInstance.attach(new MyOutsideRoom());
		room2.addObject(testInstance);

		game.addRoom(room1);
		game.addRoom(room2);

		game.run();

	}
}
