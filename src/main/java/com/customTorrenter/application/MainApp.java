package com.customTorrenter.application;
	

import java.io.IOException;

import com.customTorrenter.mainPageLayout.MainPage;
import com.customTorrenter.mainPageLayout.subPanels.torrentInfoConfigPanel.TorrentInfoConfigPanel;
import com.customTorrenter.torrentUIObjects.TorrentObjectComponent;
import com.customTorrenter.torrentUtility.TorrentObject;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class MainApp extends Application {
	
	ObservableList<TorrentObject> torrentList= FXCollections.observableArrayList();
	public BorderPane root;
	public Scene scene;
	public Stage stage;

	
	
	
	
	
	
	@Override
	public void start(Stage primaryStage) {
			stage=primaryStage;
			root = new BorderPane();
			scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.show();
			setStartPage();
			//initActions();
	}
	/**
	 * Set the start menu layout as the active layout
	 */
	public void setStartPage() {
		try {
			// Load fxml layout
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("../mainPageLayout/mainPage.fxml"));
			VBox gp=(VBox) loader.load();
			
			//link the fxml controller with MainApp
			MainPage startP=loader.getController();
			
			
			startP.setMainApp(this);
			root.setCenter(gp);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	static public TorrentObjectComponent getTorrentUIObject() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("../torrentUIObjects/TorrentObject.fxml"));
			loader.load();
			
			//link the fxml controller with MainApp
			TorrentObjectComponent startP=loader.getController();
			return startP;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	public TorrentInfoConfigPanel getTorrentInfoSetupPanel() {
		try {
			// Load fxml layout
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("../mainPageLayout/subPanels/torrentInfoConfigPanel/torrentInfoConfigPanel.fxml"));
			ScrollPane gp=(ScrollPane) loader.load();
			
			//link the fxml controller with MainApp
			TorrentInfoConfigPanel startP=loader.getController();
			return startP;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	private void initActions() {
		try {
			String filePath="C:\\Users\\Xxsafirex\\Desktop\\testTorrent\\(C78) [Number2 (Takuji)] Ore no Imouto to, Imouto no Tomodachi ni Nama de Nakadashi Suru Going Bareback and Coming Inside My Sister and My Sister�s Friend (Ore no Imouto ga Konna ni Kawaii Wake ga Nai) [English] [.torrent";
			String downloadPath="C:\\Users\\Xxsafirex\\Desktop\\testTorrent\\";
			String name=filePath.substring(filePath.lastIndexOf("/"));
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
