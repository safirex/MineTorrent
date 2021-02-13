package com.customTorrenter.torrentUtility;

import com.customTorrenter.torrentUIObjects.AbstUITorrentObj;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

public abstract class AbstTorrent implements ImplTorrent{ 
	String name;
	BooleanProperty isSelected=new SimpleBooleanProperty();
	AbstUITorrentObj UIcomponent;
	public AbstTorrent(String name) {
		this.name=name;
		initUIControler();
	}
	public AbstUITorrentObj getUIControler() {
		return UIcomponent;
	}
	
	public boolean isSelected() {
		return isSelected.getValue();
	}
}
