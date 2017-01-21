package com.icemetalpunk.gametheory.events;

import com.icemetalpunk.gametheory.events.objectevents.GTObjectEvent;
import com.icemetalpunk.gametheory.guis.Game;
import com.icemetalpunk.gametheory.guis.Room;
import com.icemetalpunk.gametheory.objects.GTObject;

// The visitor for attaching and detaching events from components.
public class GTEventProcessor {

	public void attach(GTMouseEvent event, GTEventHandler source) {

		if (source instanceof Room) {
			((Room) source).events.add(event);
			event.setSource(source);
		} else if (source instanceof Game) {
			source.getFrame().addMouseListener(event);
		}
	}

	public void detach(GTMouseEvent event, GTEventHandler source) {
		if (source instanceof Room) {
			((Room) source).events.remove(event);
		} else if (source instanceof Game) {
			source.getFrame().removeMouseListener(event);
		}
	}

	public void attach(GTKeyEvent event, GTEventHandler source) {
		if (source instanceof Room) {
			((Room) source).events.add(event);
			event.setSource(source);
		} else if (source instanceof Game) {
			source.getFrame().addKeyListener(event);
		}
	}

	public void detach(GTKeyEvent event, GTEventHandler source) {
		if (source instanceof Room) {
			((Room) source).events.remove(event);
		} else if (source instanceof Game) {
			source.getFrame().removeKeyListener(event);
		}
	}

	public void attach(GTStepEvent event, GTEventHandler source) {
		source.stepEvents.add(event);
		event.setSource(source);
	}

	public void detach(GTStepEvent event, GTEventHandler source) {
		source.stepEvents.remove(event);
		event.setSource(source);
	}

	public void attach(GTResizeEvent event, GTEventHandler source) {
		source.resizeEvents.add(event);
		event.setSource(source);
	}

	public void detach(GTResizeEvent event, GTEventHandler source) {
		source.resizeEvents.remove(event);
		event.setSource(source);
	}

	public void attach(GTRoomEvent.RoomStart event, GTEventHandler source) {
		((Room) source).startEvents.add(event);
		event.setSource(source);
	}

	public void detach(GTRoomEvent.RoomStart event, GTEventHandler source) {
		((Room) source).startEvents.remove(event);
		event.setSource(source);
	}

	public void attach(GTRoomEvent.RoomEnd event, GTEventHandler source) {
		((Room) source).endEvents.add(event);
		event.setSource(source);
	}

	public void detach(GTRoomEvent.RoomEnd event, GTEventHandler source) {
		((Room) source).endEvents.remove(event);
		event.setSource(source);
	}

	public void attach(GTObjectEvent.OutsideRoom event, GTEventHandler source) {
		((GTObject) source).outsideRoomEvents.add(event);
		event.setSource(source);
	}

	public void detach(GTObjectEvent.OutsideRoom event, GTEventHandler source) {
		((GTObject) source).outsideRoomEvents.remove(event);
		event.setSource(source);
	}

	public void attach(GTEvent event, GTEventHandler source) {
		throw new RuntimeException("Trying to attach generic event without a type.");
	}

	public void detach(GTEvent event, GTEventHandler source) {
		throw new RuntimeException("Trying to detach generic event without a type.");
	}

}
