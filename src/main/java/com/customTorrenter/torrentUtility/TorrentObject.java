package com.customTorrenter.torrentUtility;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import org.bitlet.wetorrent.Metafile;
import org.bitlet.wetorrent.Torrent;
import org.bitlet.wetorrent.disk.PlainFileSystemTorrentDisk;
import org.bitlet.wetorrent.disk.TorrentDisk;
import org.bitlet.wetorrent.peer.IncomingPeerListener;

import com.frostwire.jlibtorrent.swig.session;

enum State{
	Wait,
	Downloading,
	Pause,
	Finished
}


/*
 * will contain all infos of the GUI object torrent
 */
public class TorrentObject implements Runnable {

	
	static final IncomingPeerListener peerListener = new IncomingPeerListener(0);

	Thread downloader,uploader;
	String torrentPath,downloadPath;

	State state=State.Wait;
	double percentDownloaded;
	Torrent torrent;

	public TorrentObject(String torrentPath,String downloadPath,String downloadName) {
		this.downloadPath=downloadPath;
		this.torrentPath=torrentPath;
		try {
			Metafile metafile = new Metafile(new BufferedInputStream(new FileInputStream(torrentPath)));
			metafile.setName(downloadName);
			init(metafile);
		}catch (Exception e) {
			System.err.println("yikes problem on metafile, its gonna blow up !");
			e.printStackTrace();
		}


	}

	public void init(Metafile metafile) throws NoSuchAlgorithmException {

		try {
			TorrentDisk tdisk = new PlainFileSystemTorrentDisk(metafile, new File(downloadPath));
			tdisk.init();

			//Incomming to share between all torrents  (keep the same)
			if(!TorrentObject.peerListener.isAlive())
				TorrentObject.peerListener.start();


			torrent = new Torrent(metafile, tdisk, TorrentObject.peerListener);


		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	//	System.out.println(torrent.getTorrentDisk().getCompleted());
	public void download() {
		downloader=new Thread(this);
		downloader.start();
	}
	
	public void run() {
		try {
			state=State.Downloading;
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
				 */
			}

		}
		catch (Exception e) {
			// TODO: handle exception
		}
		torrent.stopDownload();
	}

}
