package com.customTorrenter.torrentUtility;


import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import javafx.beans.binding.Bindings;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.ListChangeListener.Change;
import javafx.collections.transformation.FilteredList;

/**
 * the medium to transfer torrents around the app
 * @author Xxsafirex
 *	
 */
public class TorrentGestionner {
	ObservableList<AbstTorrent> appTorrentList=FXCollections.observableArrayList();
	FilteredList<AbstTorrent> pendingTorrentList,activeTorrentList;
	
	
	
	
	
	public boolean isPending=false;
	private final static TorrentGestionner instance=new TorrentGestionner();
	
	private TorrentGestionner() {
		Predicate<AbstTorrent> pending = torr -> torr.getClass().equals(PendingTorrent.class);
		Predicate<AbstTorrent> active = torr -> (torr.getClass().equals(FrostwireTorrentObj.class));// && torr.isDownload());
		pendingTorrentList= appTorrentList.filtered(pending);
		activeTorrentList=appTorrentList.filtered(active);
		
		pendingTorrentList.addListener(new ListChangeListener<AbstTorrent>() {
			public void onChanged(Change<? extends AbstTorrent> c) {
				if( c.next() && c.getList().isEmpty()) {
					isPending=false;
				}else
					isPending=true;
			}
		});
	}
	

	private void updateFilteredLists(){
		Predicate<AbstTorrent> pending = torr -> torr.getClass().equals(PendingTorrent.class);
		Predicate<AbstTorrent> active = torr -> (torr.getClass().equals(FrostwireTorrentObj.class.getClass()));// && torr.isDownload());
		
		
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
		Bindings.bindContentBidirectional(list,pendingTorrentList);
	}
	
	
	
	public boolean addTorrent(AbstTorrent torrent) {
		System.out.println(torrent.getClass());
		boolean t= appTorrentList.add(torrent);
		check();
		return t;
	}
	private void check() {
		
	}
	
	
	
	
	public boolean confirm(AbstTorrent torrent) {
		System.out.println(torrent.name+" got confirmed");
		appTorrentList.remove(torrent);
		appTorrentList.add(new FrostwireTorrentObj((PendingTorrent)torrent));
		//torrent=new FrostwireTorrentObj((PendingTorrent)torrent);
		//updateFilteredLists();
		System.out.println(torrent.getClass());
		System.out.println("active size = "+activeTorrentList.size()+" pending size= "+pendingTorrentList.size());
		return true;
	}
	
}
