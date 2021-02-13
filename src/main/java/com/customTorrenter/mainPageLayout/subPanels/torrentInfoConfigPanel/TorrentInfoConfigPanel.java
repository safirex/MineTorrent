package com.customTorrenter.mainPageLayout.subPanels.torrentInfoConfigPanel;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

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
	//private List<PendingTorrent> torrentList= new ArrayList<PendingTorrent>();
	
	//binded to torrentGestionner obs list
	private ObservableList<AbstTorrent> torrentListObserver=FXCollections.observableArrayList();
	
	
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
		// torrent is selected  en abstrait ?
		//TODO
		Predicate<AbstTorrent> torrentPredicate= torrent -> torrent.isSelected();
		
		for(AbstTorrent torrent:torrentListObserver.filtered(torrentPredicate)) {
			//the torrent has been checked in UI
			PendingTorrent torr=(PendingTorrent)torrent;
			torr.setDownloadDirectory(lastDownloadDir);
			TorrentGestionner.getInstance().confirm(torrent);
		}
	}
	public Node getPaneContent() {
		return torrentVBox;
	}
	
	/**
	 * define the event handler of the  topmost checkbox 
	 */
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
				if( c.next() ) {
					for (AbstTorrent torrent:c.getAddedSubList()) {
						addTorrentToUI((PendingTorrent)torrent);
					}
					for(AbstTorrent torrent:c.getRemoved()) {
						removeUIObject(torrent.getUIControler().getUIComponent());
					}
				}
				/*if(c.wasAdded()) {
					for (AbstTorrent torrent:c.getAddedSubList()) {
						addTorrentToUI((PendingTorrent)torrent);
						//System.out.println(torrentListObserver.get(i));
						//PendingTorrent torr=torrentListObserver.get(i);
						//AbstUITorrentObj obj=torr.getUIComponent();
						//addUIObject(obj.getUIComponent());
					}
				}*/
			}
		});
	}
	
	private void addTorrentToUI(PendingTorrent torrent) {
		System.out.println("add torrent to ui");
		AbstUITorrentObj obj=torrent.getUIControler();
		System.out.println(obj.getUIComponent());
		torrentVBox.getChildren().add(obj.getUIComponent());
	}
	
	
	
	public void addUIObject(Node obj) {
		torrentVBox.getChildren().add(obj);
	}
	public void removeUIObject(Node obj) {
		torrentVBox.getChildren().remove(obj);
	}
	
	
}
