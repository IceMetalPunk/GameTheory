package com.icemetalpunk.testpackage;

import com.icemetalpunk.gametheory.guis.Game;
import com.icemetalpunk.gametheory.guis.Room;
import com.icemetalpunk.gametheory.objects.GTObject;
import com.icemetalpunk.gametheory.sprites.Sprite;

public class TestMain {
	public static void main(String[] args) {
		Game game = new Game();
		Room room1 = new Room(200, 200, "Testing Room 1!");
		Room room2 = new Room(800, 600, "Room 2 is here!");

		room1.attachListener(new GlobalClick());
		room2.attachListener(new MyStepEvent());
		room2.attachListener(new MyRoomStartEvent());
		room2.attachListener(new MyRoomEndEvent());

		room2.setSpeed(30);
		GTObject testInstance = new TestObject(50, 50);
		Sprite mainSpr = Sprite.load(TestMain.class.getResource("falsesymmetry.png"));
		testInstance.setSprite(mainSpr);
		testInstance.hspeed = 1;
		testInstance.gravity = 0.2;
		testInstance.attachListener(new MyOutsideRoom());
		room2.addObject(testInstance);

		game.addRoom(room1);
		game.addRoom(room2);

		game.run();

	}
}
