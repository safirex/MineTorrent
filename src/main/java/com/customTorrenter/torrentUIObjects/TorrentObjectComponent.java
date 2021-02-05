package com.customTorrenter.torrentUIObjects;


import com.customTorrenter.torrentUtility.FrostwireTorrentObj;
import com.customTorrenter.torrentUtility.PendingTorrent;
import com.jfoenix.controls.JFXTextField;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.layout.HBox;

/**
 * 
 * @author Xxsafirex
 * UI component for torrents (both pending and download list)
 */
public class TorrentObjectComponent extends Node{
	
	
	@FXML
	JFXTextField name;
	
	@FXML
	JFXTextField downloadDirectory;
	
	@FXML
	ProgressIndicator progressPercent;
	
	@FXML
	HBox hbox;	
	PendingTorrent pt;
	FrostwireTorrentObj torrent;
	
	public void init(PendingTorrent pt) {
		this.pt=pt;
		
		name.setText(pt.getName());
		downloadDirectory.setText(pt.getDownloadPath());
	}
	
	public void accepted() {
		torrent= new FrostwireTorrentObj(pt);
	}
	public HBox getUIComponent() {
		return hbox;
	}
	
}
