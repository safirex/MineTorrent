package com.customTorrenter.torrentUIObjects;


import com.customTorrenter.torrentUtility.AbstTorrent;
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
public class TorrentObjectComponent extends AbstUITorrentObj{
	
	
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
	
	
	public void init(AbstTorrent torrent) {
		this.pt=(PendingTorrent) torrent;
		name.setText(pt.getName());
		downloadDirectory.setText(pt.getDownloadPath());
		super.setHbox(hbox);
		initComponentsSize();
	}
	
	private void initComponentsSize() {
		int letterCount=name.getText().length();
		System.out.println(name.getText());
		System.out.println("count = "+letterCount);
		name.setPrefColumnCount(letterCount);
		System.out.println(name.getPrefColumnCount());
		name.autosize();
	}
	public void accepted() {
		torrent= new FrostwireTorrentObj(pt);
	}
	public HBox getUIComponent() {
		return hbox;
	}


	
	
}
