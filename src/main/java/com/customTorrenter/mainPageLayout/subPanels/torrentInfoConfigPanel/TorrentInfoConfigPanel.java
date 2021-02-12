package com.customTorrenter.mainPageLayout.subPanels.torrentInfoConfigPanel;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.customTorrenter.application.MainApp;
import com.customTorrenter.torrentUIObjects.AbstUITorrentObj;
import com.customTorrenter.torrentUIObjects.TorrentObjectComponent;
import com.customTorrenter.torrentUtility.AbstTorrent;
import com.customTorrenter.torrentUtility.PendingTorrent;
import com.customTorrenter.torrentUtility.TorrentGestionner;
import com.jfoenix.controls.JFXButton;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventTarget;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class TorrentInfoConfigPanel {
	private List<PendingTorrent> torrentList= new ArrayList<PendingTorrent>();
	
	//binded to torrentGestionner obs list
	private ObservableList<AbstTorrent> torrentListObserver=FXCollections.observableArrayList(torrentList);
	
	
	File lastDownloadDir;
	List<File> downloadDirSuggestions; 
	
	
	@FXML
	VBox torrentVBox;
	
	@FXML
	CheckBox selectAllCheckbox;
	@FXML
	JFXButton acceptSelectedButton;
	
	@FXML
	private void acceptButtonAction(){
		for(PendingTorrent torrent:torrentList) {
			if(torrent.getUIControler().isSelected())
				torrent.confirmed();
		}
	}
	public Node getPaneContent() {
		return torrentVBox;
	}
	
	private void setMainCheckBox() {
		selectAllCheckbox.setOnAction(event->{
			List<Node> hboxList=torrentVBox.getChildren();
			for(Node node:hboxList) {
				System.out.println(node);
				HBox hbox=(HBox)node;
				CheckBox cb=(CheckBox)hbox.getChildren().get(0);
				cb.setSelected(true);
			}
		});
	}
	
	public void init() {
		setMainCheckBox();
		TorrentGestionner.getInstance().bindPendingTorrentList(torrentListObserver);
		
		//setup a listener that wil create the GuI element when obj added
		torrentListObserver.addListener(new ListChangeListener<AbstTorrent>() {
			public void onChanged(Change<? extends AbstTorrent> c) {
				// TODO Auto-generated method stub
				if(c.wasAdded()) {
					for (int i=c.getFrom();i<c.getTo();i++) {
						
						//PendingTorrent torr=torrentListObserver.get(i);
						//AbstUITorrentObj obj=torr.getUIComponent();
						//addUIObject(obj.getUIComponent());
					}
				}
			}
		});
	}
	
	private void addTorrentToUI(PendingTorrent torr) {
		AbstUITorrentObj obj=torr.getUIControler();
		torrentVBox.getChildren().add(obj.getUIComponent());
	}
	
	
	public void sendPendingList(List<PendingTorrent> list) {
		torrentList.addAll(list);
		for (PendingTorrent torr:list) {
			addTorrentToUI(torr);
		}
	}
	
	
	public void addUIObject(Node obj) {
		torrentVBox.getChildren().add(obj);
	}
	
	
	
}
