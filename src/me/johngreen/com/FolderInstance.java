package me.johngreen.com;

import java.util.ArrayList;

public class FolderInstance {
	private String folderName;
	private ArrayList<String> fileTypes;
	public FolderInstance(String folderName){
		this.fileTypes = new ArrayList<String>();
		this.folderName = folderName;
	}
	public String getFolderName(){
		return folderName;
	}
	public boolean containsFileType(String fileType){
		for(String s:fileTypes){
			if(s.equalsIgnoreCase(fileType)){
				return true;
			}
		}
		return false;
	}
	public void addFileType(String fileType){
		fileTypes.add(fileType);
	}
	public ArrayList<String> getRequestedFileTypes(){
		return fileTypes;
	}
}
