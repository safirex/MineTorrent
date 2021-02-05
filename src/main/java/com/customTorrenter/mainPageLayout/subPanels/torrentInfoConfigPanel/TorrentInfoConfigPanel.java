package com.customTorrenter.mainPageLayout.subPanels.torrentInfoConfigPanel;

import java.util.ArrayList;
import java.util.List;

import com.customTorrenter.torrentUtility.PendingTorrent;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.layout.VBox;

public class TorrentInfoConfigPanel {
	private List<PendingTorrent> torrentList= new ArrayList<PendingTorrent>();

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
			
		}
	}
	
	
	
}
