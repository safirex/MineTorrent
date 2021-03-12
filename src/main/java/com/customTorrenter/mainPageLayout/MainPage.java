package com.customTorrenter.mainPageLayout;

import java.awt.event.ActionListener;
import java.beans.Beans;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.customTorrenter.application.MainApp;
import com.customTorrenter.mainPageLayout.subPanels.torrentInfoConfigPanel.TorrentInfoConfigPanel;
import com.customTorrenter.torrentUIObjects.TorrentObjectComponent;
import com.customTorrenter.torrentUtility.AbstTorrent;
import com.customTorrenter.torrentUtility.PendingTorrent;
import com.customTorrenter.torrentUtility.TorrentGestionner;
import com.frostwire.jlibtorrent.SessionManager;

import javafx.beans.binding.Bindings;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.collections.ListChangeListener.Change;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

public class MainPage {
	public MainApp app;
	private Scene scene;
	final SessionManager s = new SessionManager();
	TorrentInfoConfigPanel torrentConfigPanel;
	TorrentGestionner gestion;
	
	@FXML
	ImageView pendingAlert;
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
		gestion=TorrentGestionner.getInstance();
		
		//TODO: ajouter alerte signe quand un torrent est en config
		//pendingAlert.visibleProperty().bind(gestion.isPending);
	}
	
	/*
	 *  Initializes the controller class. This method is automatically called after
	 * the fxml file has been loaded.
	 */
	@FXML
	private void initialize() {}
	
	
	
	/**
	 * open the file chooser
	 * @param event
	 */
	@FXML
    void addTorrentAction(ActionEvent event) {
		FileChooser chooser = new FileChooser();
        chooser.setInitialDirectory(new File("C:\\Users\\Xxsafirex\\Desktop\\nh"));
		List<File> fileList = chooser.showOpenMultipleDialog(app.stage);
		FileChooser.ExtensionFilter extfilt = 
				  new FileChooser.ExtensionFilter("torrent files", "*.torrent");
		chooser.setSelectedExtensionFilter(extfilt);
        if (fileList != null) {
            for (File file: fileList) {
	        		PendingTorrent pt=new PendingTorrent(file);   	
	        		gestion.addTorrent(pt);
            }
        }
    }
	
	
	
	
	
	
	
	@FXML
    void switchToTorrentConfigPanel() {
		scrollPane_MainZone.setContent(torrentConfigPanel.getPaneContent());
	}
}
