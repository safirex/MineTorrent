package com.customTorrenter.torrentUtility;


import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * 
 * @author Xxsafirex
 *	the medium to transfer torrents around the app
 */
public class TorrentGestionner {
	ObservableList<AbstTorrent> appTorrentList=FXCollections.observableArrayList();
	ObservableList<FrostwireTorrentObj> readyTorrentList =FXCollections.observableArrayList();
	ObservableList<PendingTorrent> PendingTorrentList =FXCollections.observableArrayList();
	
	private final static TorrentGestionner instance=new TorrentGestionner();
	
	private TorrentGestionner() {}
	
	public static TorrentGestionner getInstance() {
		return instance;
	}
	
	/**
	 * binds the torrentList with torrentConfigPage's list
	 * @param list
	 */
	public void bindPendingTorrentList(ObservableList<AbstTorrent> list) {
		Predicate<AbstTorrent> byType = torr -> torr.getClass().equals(PendingTorrent.class);
		
		Bindings.bindContentBidirectional(list,appTorrentList.filtered(byType));
		//Bindings.bindContentBidirectional(PendingTorrentList, list);
	}
	
	
	
	public boolean addTorrent(AbstTorrent torrent) {
		System.out.println(torrent.getClass());
		return appTorrentList.add(torrent);
	}
	
	public boolean addTorrentToConfig(AbstTorrent torrent) {
		return PendingTorrentList.add((PendingTorrent) torrent);
	}
	
	
	
	
	public boolean addTorrentToDownload(AbstTorrent torrent) {
		return readyTorrentList.add((FrostwireTorrentObj)torrent);
	}
	
}
