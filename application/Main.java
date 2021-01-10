package application;
	

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.Stage;
import torrentUtility.TorrentObject;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;


public class Main extends Application {
	
	ObservableList<TorrentObject> torrentList= FXCollections.observableArrayList();
	
	

	
	
	
	
	
	
	@Override
	public void start(Stage primaryStage) {
		
			BorderPane root = new BorderPane();
			Scene scene = new Scene(root,400,400);
			primaryStage.setScene(scene);
			primaryStage.show();
			
			//initActions();
	}
	
	
	private void initActions() {
		try {
			String filePath="C:\\Users\\Xxsafirex\\Desktop\\testTorrent\\(C78) [Number2 (Takuji)] Ore no Imouto to, Imouto no Tomodachi ni Nama de Nakadashi Suru Going Bareback and Coming Inside My Sister and My Sister’s Friend (Ore no Imouto ga Konna ni Kawaii Wake ga Nai) [English] [.torrent";
			String downloadPath=filePath.substring(0, filePath.lastIndexOf("\\"));
			String name=filePath.substring(filePath.lastIndexOf("\\"));
			System.out.println(name);
			TorrentObject torObj=new TorrentObject(filePath, downloadPath, name);
			torObj.download();
			
			String filePath2="C:\\Users\\Xxsafirex\\Desktop\\testTorrent\\{EHT PERSONALIZED TORRENT - DO NOT REDISTRIBUTE} [Mashiro Shirako] Yappari Kimi ga Suki I Really Love You (COMIC Anthurium 2018-12) [Digital] [English] [Team Koinaka + Redlantern].zip.torrent";
			String downloadPath2=filePath2.substring(0, filePath2.lastIndexOf("\\"));
			
			String name2=filePath2.substring(filePath2.lastIndexOf("\\"));
			TorrentObject torObj2=new TorrentObject(filePath2, downloadPath2, name2);
			torObj2.download();
			
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
		
		
		
		
	}
}
