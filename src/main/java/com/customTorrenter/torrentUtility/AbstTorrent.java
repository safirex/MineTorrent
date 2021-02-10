package com.customTorrenter.torrentUtility;

import com.customTorrenter.torrentUIObjects.AbstUITorrentObj;

public abstract class AbstTorrent implements ImplTorrent{
	String name;
	AbstUITorrentObj UIcomponent;
	public AbstTorrent(String name) {
		this.name=name;
		initUIControler();
	}
	public AbstUITorrentObj getUIControler() {
		return UIcomponent;
	}
}
