package com.customTorrenter.torrentUtility;

import com.customTorrenter.torrentUIObjects.AbstUITorrentObj;

public abstract class AbstTorrent implements ImplTorrent{
	String name;
	AbstUITorrentObj UIcomponent;
	public AbstTorrent(String name) {
		this.name=name;
		initUIComponent();
	}
	public AbstUITorrentObj getUIComponent() {
		return UIcomponent;
	}
}
