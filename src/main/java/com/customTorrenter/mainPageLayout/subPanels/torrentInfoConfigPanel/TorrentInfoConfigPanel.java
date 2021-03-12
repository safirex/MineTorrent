package com.customTorrenter.mainPageLayout.subPanels.torrentInfoConfigPanel;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
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
	private void acceptButtonAction() {
		Predicate<AbstTorrent> torrentPredicate= torrent -> torrent.isSelected();
		
		//has to be done like that because of in loop modification
		Object[] torrList= torrentListObserver.filtered(torrentPredicate).toArray();
		for(int i=0;i<torrList.length;i++ ) {
			PendingTorrent torrent=(PendingTorrent)torrList[i];			
			torrent.setDownloadDirectory(lastDownloadDir);
			torrent.confirm();
			
			//TODO: generate Frostwire Torrent obj
			//TorrentGestionner.getInstance().confirm(torrent);
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
			}
		});
	}
	
	private void addTorrentToUI(PendingTorrent torrent) {
		System.out.println("add torrent to ui");
		AbstUITorrentObj obj=torrent.getUIControler();
		System.out.println(obj.getUIComponent());
		addUIObject(obj.getUIComponent());
	}
	
	
	/**
	 * might not even be useful if bind vbox.getchildren to abstTorrentList
	 * @param obj
	 */
	private void addUIObject(Node obj) {
		torrentVBox.getChildren().add(obj);
	}
	private void removeUIObject(Node obj) {
		torrentVBox.getChildren().remove(obj);
	}
	
	
}
