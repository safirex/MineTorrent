package com.customTorrenter.torrentUtility;

import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import com.frostwire.jlibtorrent.TorrentInfo;


public class FrostwireTorrentObj implements Runnable {

	Thread downloader,uploader;
	String torrentPath,downloadPath;
	TorrentInfo torrentInfo;
	File downloadDirectory;
	State state=State.Wait;
	double percentDownloaded;
	//Torrent torrent;

	
	
	public FrostwireTorrentObj(File torrentFile,File downloadDirectory,String downloadName) {
		this.downloadDirectory=downloadDirectory;
		this.torrentInfo= new TorrentInfo(torrentFile);
	}
	
	/*
	public FrostwireTorrentObj(String torrentPath,String downloadPath,String downloadName) {
		this.downloadPath=downloadPath;
		this.torrentPath=torrentPath;
		
		downloadDirectory=new File(downloadPath.toCharArray());
		torrentInfo=new TorrentInfo(File(downloadPath));
		
		
		try {
			//(new BufferedInputStream(new FileInputStream(torrentPath)));
			
			
		}catch (Exception e) {
			System.err.println("yikes problem on metafile, its gonna blow up !");
			e.printStackTrace();
		}


	}*/

	/*public void init(Metafile metafile) throws NoSuchAlgorithmException {

		

	}*/

	//	System.out.println(torrent.getTorrentDisk().getCompleted());
	public void download() {
		downloader=new Thread(this);
		downloader.start();
	}
	
	public void run() {
		try {
			state=State.Downloading;
			/*
			torrent.startDownload();
			while (!torrent.isCompleted()) {
				try {
					Thread.sleep(1000);
				} catch(InterruptedException ie) {
					break;
				}
				torrent.tick();
				percentDownloaded=Math.round(100*torrent.getPercentageDownloaded())/100;
				System.out.println(torrent+" \t"+torrent.getTorrentDisk().getCompleted()+"\t "+percentDownloaded+
						"\t"+torrent.getUploadBandwidthLimiter()+"\t"+torrent.getPeersManager().getSeedersNumber());
				
				
				
				/*
		    	Torrent.interrupt()
		    	IncomingPeerListener.interrupt()
		    	pour arret
				 
			}
		*/

		}
		catch (Exception e) {
			// TODO: handle exception
		}
		//torrent.stopDownload();
	}
}
