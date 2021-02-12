package com.customTorrenter.torrentUtility;

import java.io.File;

import com.customTorrenter.application.MainApp;
import com.customTorrenter.torrentUIObjects.AbstUITorrentObj;
import com.customTorrenter.torrentUIObjects.TorrentObjectComponent;

import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableStringValue;

/**
 * torrents waiting to be defined a download directory and name
 */
public class PendingTorrent extends AbstTorrent {
	String torrentPath;
	StringProperty downloadPath=new SimpleStringProperty("");
	File torrentFile,downloadDirectory;
	
	TorrentObjectComponent UIcomponent;
	public TorrentObjectComponent getUIControler() {
		return UIcomponent;
	}
	public PendingTorrent(File torrentFile) {
		super(torrentFile.getName());
		this.torrentFile=torrentFile;
		System.out.println("parent file = "+torrentFile.getParentFile().getPath());
		downloadDirectory=torrentFile.getParentFile();
		Bindings.bindBidirectional(downloadPath,UIcomponent.getDownloadPathProperty());
		downloadPath.setValue(torrentFile.getParent());
	}
	
	
	
	
	
	public File getDownloadDirectory() {
		return downloadDirectory;
	}

	public void setDownloadDirectory(File downloadDirectory) {
		this.downloadDirectory = downloadDirectory;
	}

	public void setName(String name) {
		this.name=name;
	}
	
	
	public String getTorrentPath() {
		return torrentPath;
	}

	public StringProperty getDownloadPath() {
		return downloadPath;
	}

	public String getName() {
		return name;
	}

	public File getTorrentFile() {
		return torrentFile;
	}

	/*
	 * generate a torrentObject and store it in the torrent list of MainApp
	 */
	public void confirmed() {
		
	}
	
	
	

	/**
	 * gives the object the appropriate JFX element
	 */
	public void initUIControler() {
		UIcomponent= MainApp.getTorrentUIObject();
		UIcomponent.init((PendingTorrent)this);
	}
	
	public void bindToUI(StringProperty property) {
		Bindings.bindBidirectional(property, downloadPath);
	}
}	
