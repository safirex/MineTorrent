package com.customTorrenter.torrentUIObjects.workingTorrentObject;

import com.customTorrenter.torrentUIObjects.AbstUITorrentObj;
import com.customTorrenter.torrentUtility.AbstTorrent;
import com.customTorrenter.torrentUtility.FrostwireTorrentObj;
import com.customTorrenter.torrentUtility.PendingTorrent;
import com.jfoenix.controls.JFXTextField;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.layout.HBox;

public class workingTorrentComponent extends AbstUITorrentObj{

	
	@FXML
	CheckBox checkbox;
	@FXML
	JFXTextField name;
	@FXML
	ProgressIndicator progressPercent;
	@FXML
	HBox hbox;	
	
	FrostwireTorrentObj torrent;
	private BooleanProperty isSelected = new SimpleBooleanProperty();
	
	@Override
	public void init(AbstTorrent torrent) {
		this.torrent=(FrostwireTorrentObj) torrent;
		name.setText(this.torrent.getName());
		super.setHbox(hbox);
		initComponentsSize();
		isSelected.bind(checkbox.selectedProperty());
	}
	

	

	public HBox getUIComponent() {
		return hbox;
	}	
	public boolean isSelected() {
		return isSelected.get();
	}
	
	public BooleanProperty getIsSelected() {
		return isSelected;
	}

	

}
