package com.customTorrenter.torrentUIObjects;


import java.io.File;

import org.omg.CORBA.SystemException;

import com.customTorrenter.torrentUtility.AbstTorrent;
import com.customTorrenter.torrentUtility.FrostwireTorrentObj;
import com.customTorrenter.torrentUtility.PendingTorrent;
import com.customTorrenter.torrentUtility.TorrentGestionner;
import com.jfoenix.controls.JFXTextField;

import javafx.beans.binding.Bindings;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.layout.HBox;

/**
 * 
 * @author Xxsafirex
 * UI component for torrents (both pending and download list)
 */
public class TorrentObjectComponent extends AbstUITorrentObj{
	
	
	
	@FXML
	CheckBox checkbox;
	
	@FXML
	JFXTextField name;
	
	@FXML
	JFXTextField downloadDirectory;
	
	@FXML
	ProgressIndicator progressPercent;
	
	@FXML
	HBox hbox;	
	
	PendingTorrent pt;
	
	
	@FXML
	private void acceptButtonClicked() {
		pt.setDownloadDirectory(new File(downloadDirectory.getText()));
		TorrentGestionner.getInstance().confirm(pt);
		
	}
	
	
	
	public void init(AbstTorrent torrent) {
		this.pt=(PendingTorrent) torrent;
		name.setText(pt.getName());
		super.setHbox(hbox);
		initComponentsSize();
		//isSelected.bind(checkbox.selectedProperty());
		pt.getIsSelected().bind(checkbox.selectedProperty());	}
	
	
	private void initComponentsSize() {
		/*
		checkbox.setOnDragDetected(event->{
			boolean nop=!(checkbox.isSelected());
			checkbox.setSelected(nop);
		});
		*/
		int letterCount=name.getText().length();
		System.out.println(name.getText());
		System.out.println("count = "+letterCount);
		//name.setPrefColumnCount(letterCount);
		System.out.println(name.getPrefColumnCount());
		name.autosize();
	}
	public HBox getUIComponent() {
		return hbox;
	}	
	



	public StringProperty getDownloadPathProperty() {
		return downloadDirectory.textProperty();
	}
	
}
