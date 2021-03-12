package com.customTorrenter.torrentUtility;

import com.customTorrenter.torrentUIObjects.AbstUITorrentObj;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

public abstract class AbstTorrent implements ImplTorrent{ 
	String name;
	BooleanProperty isSelected=new SimpleBooleanProperty();
	private boolean download=true,upload=true;
	
	
	AbstUITorrentObj UIcomponent;
	public AbstTorrent(String name) {
		this.name=name;
		initUIControler();
	}
	public String getName() {
		return name;
	}
	public BooleanProperty getIsSelected() {
		return isSelected;
	}
	public AbstUITorrentObj getUIControler() {
		return UIcomponent;
	}
	
	public boolean isSelected() {
		return isSelected.getValue();
	}
	public boolean isDownload() {
		return download;
	}
	public boolean isUpload() {
		return upload;
	}
}
