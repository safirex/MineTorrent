package com.customTorrenter.torrentUtility;

import java.io.File;

/*
 * class used a temporary folder for torrent waiting for user's ok
 */
public class PendingTorrent {
	String torrentPath,downloadPath,name;
	
	File torrentFile,downloadDirectory;
	
	public PendingTorrent(File torrentFile) {
		this.torrentFile=torrentFile;
		downloadDirectory=torrentFile.getParentFile();
		name=torrentFile.getName();
		System.out.println(name);
		downloadPath=torrentFile.getParent().toString();
	}
	
	public File getDownloadDirectory() {
		return downloadDirectory;
	}

	public void setDownloadDirectory(File downloadDirectory) {
		this.downloadDirectory = downloadDirectory;
	}

	public PendingTorrent(String downPath,String downName,String torrPath) {
		name=downName;
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
	

}
