package com.customTorrenter.mainPageLayout.subPanels.torrentInfoConfigPanel;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.customTorrenter.application.MainApp;
import com.customTorrenter.torrentUIObjects.TorrentObjectComponent;
import com.customTorrenter.torrentUtility.PendingTorrent;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.layout.VBox;

public class TorrentInfoConfigPanel {
	private List<PendingTorrent> torrentList= new ArrayList<PendingTorrent>();
	
	
	File lastDownloadDir;
	List<File> downloadDirSuggestions; 
	
	
	@FXML
	VBox torrentVBox;
	
	
	public Node getPaneContent() {
		return torrentVBox;
	}
	public void init() {
		
		
	}
	public void sendPendingList(List<PendingTorrent> list) {
		torrentList.addAll(list);
		for (PendingTorrent torr:list) {
			TorrentObjectComponent obj=MainApp.getTorrentUIObject();
			obj.init(torr);
			torrentVBox.getChildren().add(obj.getUIComponent());
		}
	}
	
	
	
}
