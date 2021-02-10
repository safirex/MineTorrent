package com.customTorrenter.mainPageLayout.subPanels.torrentInfoConfigPanel;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.customTorrenter.application.MainApp;
import com.customTorrenter.torrentUIObjects.AbstUITorrentObj;
import com.customTorrenter.torrentUIObjects.TorrentObjectComponent;
import com.customTorrenter.torrentUtility.PendingTorrent;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.layout.VBox;

public class TorrentInfoConfigPanel {
	private List<PendingTorrent> torrentList= new ArrayList<PendingTorrent>();
	
	//binded to torrentGestionner obs list
	private ObservableList<PendingTorrent> torrentListObserver=FXCollections.observableArrayList(torrentList);
	
	
	File lastDownloadDir;
	List<File> downloadDirSuggestions; 
	
	
	@FXML
	VBox torrentVBox;
	
	
	
	public Node getPaneContent() {
		return torrentVBox;
	}
	public void init() {
		//setup a listener that wil create the GuI element when obj added
		/*torrentListObserver.addListener(new ListChangeListener<PendingTorrent>() {
			public void onChanged(Change<? extends PendingTorrent> c) {
				// TODO Auto-generated method stub
				if(c.wasAdded()) {
					//if (c.wasUpdated()) {}
					for (int i=c.getFrom();i<c.getTo();i++) {
						
						//PendingTorrent torr=torrentListObserver.get(i);
						//AbstUITorrentObj obj=torr.getUIComponent();
						
						//addUIObject(obj.getUIComponent());
					}
				}
			}
		});*/
	}
	public void sendPendingList(List<PendingTorrent> list) {
		torrentList.addAll(list);
		for (PendingTorrent torr:list) {
			AbstUITorrentObj obj=torr.getUIControler();
			torrentVBox.getChildren().add(obj.getUIComponent());
		}
	}
	
	
	public void addUIObject(Node obj) {
		torrentVBox.getChildren().add(obj);
	}
	
	
	
}
