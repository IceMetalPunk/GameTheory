package com.icemetalpunk.gametheory.events;

import java.awt.Component;
import java.util.ArrayList;
import java.util.List;

import com.icemetalpunk.gametheory.guis.Game;

public abstract class GTEventHandler {
	public abstract <T extends Component> T getFrame();

	public final List<GTStepEvent> stepEvents = new ArrayList<GTStepEvent>();
	public final List<GTResizeEvent> resizeEvents = new ArrayList<GTResizeEvent>();

	public void attach(GTEvent event) {
		event.attachTo(new GTEventProcessor(), this);
	}

	public void detach(GTEvent event) {
		event.detachFrom(new GTEventProcessor(), this);
	}

	public abstract Game getWindow();

}
