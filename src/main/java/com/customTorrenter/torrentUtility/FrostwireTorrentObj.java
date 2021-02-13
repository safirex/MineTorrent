package com.customTorrenter.torrentUtility;

import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import com.customTorrenter.application.MainApp;
import com.customTorrenter.torrentUIObjects.AbstUITorrentObj;
import com.frostwire.jlibtorrent.TorrentInfo;


public class FrostwireTorrentObj extends AbstTorrent implements Runnable {

	Thread downloader,uploader;
	String torrentPath,downloadPath;
	TorrentInfo torrentInfo;
	File downloadDirectory;
	State state=State.Wait;
	double percentDownloaded;
	//Torrent torrent;

	
	public FrostwireTorrentObj(PendingTorrent pt) {
		super(pt.getName());
		this.downloadDirectory=pt.getDownloadDirectory();
		this.torrentInfo= new TorrentInfo(pt.getTorrentFile());
	}
	
	public FrostwireTorrentObj(File torrentFile,File downloadDirectory,String downloadName) {
		super(downloadName);
		this.downloadDirectory=downloadDirectory;
		this.torrentInfo= new TorrentInfo(torrentFile);
	}
	


	//	System.out.println(torrent.getTorrentDisk().getCompleted());
	public void download() {
		downloader=new Thread(this);
		downloader.start();
	}
	
	public void run() {
		try {
			state=State.Downloading;
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		//torrent.stopDownload();
	}
	
	
	public void initUIControler() {
		// TODO Auto-generated method stub
		//UIcomponent= MainApp.getTorrentUIObject();
		//UIcomponent.init(this);
	}

}
