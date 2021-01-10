package torrentUtility;

/*
 * class used a temporary folder for torrent waiting for user's ok
 */
public class PendingTorrent {
	String torrentPath,downloadPath,name;

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
