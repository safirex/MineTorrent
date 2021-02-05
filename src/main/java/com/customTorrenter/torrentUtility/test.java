package com.customTorrenter.torrentUtility;

import com.frostwire.jlibtorrent.AlertListener;
import com.frostwire.jlibtorrent.LibTorrent;
import com.frostwire.jlibtorrent.SessionManager;
import com.frostwire.jlibtorrent.TorrentInfo;
import com.frostwire.jlibtorrent.alerts.AddTorrentAlert;
import com.frostwire.jlibtorrent.alerts.Alert;
import com.frostwire.jlibtorrent.alerts.AlertType;
import com.frostwire.jlibtorrent.alerts.BlockFinishedAlert;

import java.io.File;
import java.util.concurrent.CountDownLatch;

public class test {


    public static void main(String[] args) throws Throwable {

        // comment this line for a real application
        args = new String[]{"C:\\Users\\Xxsafirex\\Desktop\\nh\\(AC2) [Bochi Bochi no Ki (Borusiti)] Patchouli ga Koakuma ni Shiborareru Hon (Touhou Project) [Chinese] [WTM直接汉化].torrent",
        		"C:\\Users\\Xxsafirex\\Desktop\\nh\\(Akihabara Chou Doujinsai) [Moe Hime Rengou (xin, obiwan)] Carnival 30 ~ Novelty wa Kono Taihou de (Azur Lane).torrent"};

        File torrentFile = new File(args[0]);

        System.out.println("Using libtorrent version: " + LibTorrent.version());

        final SessionManager s = new SessionManager();

        final CountDownLatch signal = new CountDownLatch(1);

        s.addListener(new AlertListener() {
            public int[] types() {
                return null;
            }

            public void alert(Alert<?> alert) {
                AlertType type = alert.type();

                switch (type) {
                    case ADD_TORRENT:
                        System.out.println("Torrent added");
                        ((AddTorrentAlert) alert).handle().resume();
                        break;
                    case BLOCK_FINISHED:
                        BlockFinishedAlert a = (BlockFinishedAlert) alert;
                        int p = (int) (a.handle().status().progress() * 100);
                        System.out.println("Progress: " + p + " for torrent name: " + a.torrentName());
                        System.out.println(s.stats().totalDownload());
                        break;
                    case TORRENT_FINISHED:
                        System.out.println("Torrent finished");
                        signal.countDown();
                        break;
                }
            }
        });

        s.start();
        
        TorrentInfo ti = new TorrentInfo(torrentFile);
        s.download(ti, torrentFile.getParentFile());

        signal.await();

        s.stop();
    }
}

