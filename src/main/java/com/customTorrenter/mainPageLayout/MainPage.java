package com.customTorrenter.mainPageLayout;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.customTorrenter.application.MainApp;
import com.customTorrenter.mainPageLayout.subPanels.torrentInfoConfigPanel.TorrentInfoConfigPanel;
import com.customTorrenter.torrentUIObjects.TorrentObjectComponent;
import com.customTorrenter.torrentUtility.PendingTorrent;
import com.frostwire.jlibtorrent.SessionManager;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

public class MainPage {
	public MainApp app;
	private Scene scene;
	final SessionManager s = new SessionManager();
	final List<PendingTorrent> pendingtTorrentList=new ArrayList<PendingTorrent>();
	TorrentInfoConfigPanel torrentConfigPanel;
	
	
	@FXML
	ScrollPane scrollPane_MainZone;
	 
	public void setMainApp(MainApp	mapp) {
		app=mapp;
		init();
	}
	
	/**
	 * is called after fxml loading (and no trouble with param instantiation)
	 */
	public void init() {
		torrentConfigPanel=app.getTorrentInfoSetupPanel();
		torrentConfigPanel.init();
	}
	
	/*
	 *  Initializes the controller class. This method is automatically called after
	 * the fxml file has been loaded.
	 */
	@FXML
	private void initialize() {}
	
	@FXML
    void addTorrentAction(ActionEvent event) {
		FileChooser chooser = new FileChooser();
        //chooser.setInitialDirectory(f);
		List<File> fileList = chooser.showOpenMultipleDialog(app.stage);
		FileChooser.ExtensionFilter extfilt = 
				  new FileChooser.ExtensionFilter("torrent files", "*.torrent");
		chooser.setSelectedExtensionFilter(extfilt);
        if (fileList != null) {
            for (File file: fileList) {
	        		System.out.println(file.getAbsolutePath());
	        		PendingTorrent pt=new PendingTorrent(file);
	        		pendingtTorrentList.add(pt);
            }
        }
        torrentConfigPanel.sendPendingList(pendingtTorrentList);
        pendingtTorrentList.clear();
    }
	
	
	
	
	
	
	
	@FXML
    void switchToTorrentConfigPanel() {
		scrollPane_MainZone.setContent(torrentConfigPanel.getPaneContent());
	}
}
