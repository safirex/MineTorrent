package com.customTorrenter.torrentUtility;

import java.io.File;

import com.customTorrenter.application.MainApp;

/**
 * torrents waiting to be defined a download directory and name
 */
public class PendingTorrent extends AbstTorrent {
	String torrentPath,downloadPath;
	File torrentFile,downloadDirectory;
	
	public PendingTorrent(File torrentFile) {
		super(torrentFile.getName());
		this.torrentFile=torrentFile;
		downloadDirectory=torrentFile.getParentFile();
		downloadPath=torrentFile.getParent().toString();
		
	}
	
	public File getDownloadDirectory() {
		return downloadDirectory;
	}

	public void setDownloadDirectory(File downloadDirectory) {
		this.downloadDirectory = downloadDirectory;
	}

	public PendingTorrent(String downPath,String downName,String torrPath) {
		super(downName);
		downloadPath=downPath;
		torrentPath=torrPath;
	}
	public void setName(String name) {
		this.name=name;
	}
	public void setDownloadPath(String path) {
		downloadPath=path;
	}
	
	
	public String getTorrentPath() {
		return torrentPath;
	}

	public String getDownloadPath() {
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
		UIcomponent.init(this);
	}
	

}
