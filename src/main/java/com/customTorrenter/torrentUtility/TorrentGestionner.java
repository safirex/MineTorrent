package com.customTorrenter.torrentUtility;


import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;

/**
 * 
 * @author Xxsafirex
 *	the medium to transfer torrents around the app
 */
public class TorrentGestionner {
	ObservableList<AbstTorrent> appTorrentList=FXCollections.observableArrayList();
	FilteredList<AbstTorrent> pendingTorrentList,activeTorrentList;
	
	private final static TorrentGestionner instance=new TorrentGestionner();
	
	private TorrentGestionner() {
		Predicate<AbstTorrent> pending = torr -> torr.getClass().equals(PendingTorrent.class);
		Predicate<AbstTorrent> active = torr -> torr.getClass().equals(PendingTorrent.class);
		
	
		pendingTorrentList= appTorrentList.filtered(pending);
		activeTorrentList=appTorrentList.filtered(active);
	}
	
	public static TorrentGestionner getInstance() {
		return instance;
	}
	
	/**
	 * binds the torrentList with torrentConfigPage's list
	 * @param list
	 */
	public void bindPendingTorrentList(ObservableList<AbstTorrent> list) {
		

		Bindings.bindContentBidirectional(list,pendingTorrentList);//.filtered(byType));
		
		//Bindings.bindContentBidirectional(PendingTorrentList, list);
	}
	
	
	
	public boolean addTorrent(AbstTorrent torrent) {
		System.out.println(torrent.getClass());
		boolean t= appTorrentList.add(torrent);
		check();
		return t;
	}
	private void check() {
		
	}
	
	
	
	
	public void confirm(AbstTorrent torrent) {
		System.out.println(torrent.name+" got confirmed");
		System.out.println(appTorrentList.size());
		appTorrentList.remove(torrent);
		System.out.println(appTorrentList.size());
		appTorrentList.add(new FrostwireTorrentObj((PendingTorrent)torrent));
	}
	
}
