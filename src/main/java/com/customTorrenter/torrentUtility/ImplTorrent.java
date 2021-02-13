package com.customTorrenter.torrentUtility;

import com.customTorrenter.torrentUIObjects.AbstUITorrentObj;

public interface ImplTorrent {
	public AbstUITorrentObj getUIControler();
	public void initUIControler();
	public boolean isSelected();
}
