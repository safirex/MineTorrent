package com.customTorrenter.torrentUIObjects;

import javafx.scene.layout.HBox;

public abstract class AbstUITorrentObj  implements ImplTorrentObj{
	HBox hbox;
	public void setHbox(HBox hb) {
		hbox=hb;
	}
	
	
	public HBox getUIComponent() {
		return hbox;
	}

}
