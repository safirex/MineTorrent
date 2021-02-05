package com.customTorrenter.torrentUtility;

import java.io.File;

/*
 * class used a temporary folder for torrent waiting for user's ok
 */
public class PendingTorrent {
	String torrentPath,downloadPath,name;
	
	File torrentFile;
	
	public PendingTorrent(File torrentFile) {
		this.torrentFile=torrentFile;
		name=torrentFile.getName();
		downloadPath=torrentFile.getParent().toString();
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
	
	/*
	 * generate a torrentObject and store it in the torrent list of MainApp
	 */
	public void confirmed() {
		
	}
	

}
