package com.customTorrenter.torrentUtility;

import com.sun.javafx.binding.SelectBinding.AsBoolean;

import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class TorrentGestionner {
	ObservableList<FrostwireTorrentObj> readyTorrentList =FXCollections.observableArrayList();
	ObservableList<PendingTorrent> PendingTorrentList =FXCollections.observableArrayList();
	
	public void init(ObservableList<PendingTorrent> list) {
		Bindings.bindContentBidirectional(PendingTorrentList, list);
	}
	
	
	public boolean addTorrentToConfig(AbstTorrent torrent) {
		return PendingTorrentList.add((PendingTorrent) torrent);
	}
	public boolean addTorrentToDownload(AbstTorrent torrent) {
		return readyTorrentList.add((FrostwireTorrentObj)torrent);
	}
	
}
