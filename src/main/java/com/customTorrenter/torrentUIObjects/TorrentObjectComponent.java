package com.customTorrenter.torrentUIObjects;


import java.io.File;

import org.omg.CORBA.SystemException;

import com.customTorrenter.torrentUtility.AbstTorrent;
import com.customTorrenter.torrentUtility.FrostwireTorrentObj;
import com.customTorrenter.torrentUtility.PendingTorrent;
import com.jfoenix.controls.JFXTextField;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
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
	FrostwireTorrentObj torrent;
	
	private BooleanProperty isSelected = new SimpleBooleanProperty();
	
	
	@FXML
	private void acceptButtonClicked() {
		System.out.print(pt.getName());
		
		pt.setDownloadDirectory(new File(downloadDirectory.getText()));
		System.out.print(" got accepted to "+ downloadDirectory.getText());
	}
	
	
	
	public void init(AbstTorrent torrent) {
		this.pt=(PendingTorrent) torrent;
		name.setText(pt.getName());
		downloadDirectory.setText(pt.getDownloadPath());
		super.setHbox(hbox);
		initComponentsSize();
		
		isSelected.bind(checkbox.selectedProperty());
	}
	
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
	public void accepted() {
		torrent= new FrostwireTorrentObj(pt);
	}
	public HBox getUIComponent() {
		return hbox;
	}	
	public boolean isSelected() {
		return isSelected.get();
	}

	
}
