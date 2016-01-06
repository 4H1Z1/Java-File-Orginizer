package me.johngreen.com;

public class SorterParameters {
	private WrightParameters wrightParams;
	private DepthParameters depthParams;
	private boolean sortFiles;
	private boolean sortFolders;
	private boolean includeEtcFolder;
	private boolean moveExtraFilesToEtc;
	public SorterParameters(WrightParameters wrightParams,DepthParameters depthParams){
		this.wrightParams = wrightParams;
		this.depthParams = depthParams;
		this.sortFiles = true;
		this.sortFolders = true;
		this.includeEtcFolder = false;
		this.moveExtraFilesToEtc = false;
	}
	public boolean moveExtraFilesToEtc(){
		return moveExtraFilesToEtc;
	}
	public boolean includeEtcFolder(){
		return includeEtcFolder;
	}
	public boolean sortFiles(){
		return sortFiles;
	}
	public boolean sortFolders(){
		return sortFolders;
	}
	public void setMoveExtraFilesToEtc(boolean moveExtraFilesToEtc){
		this.moveExtraFilesToEtc = moveExtraFilesToEtc;
	}
	public void setIncludeEtcFolder(boolean includeEtcFolder){
		this.includeEtcFolder = includeEtcFolder;
	}
	public void setSortFiles(boolean sortFiles){
		this.sortFiles = sortFiles;
	}
	public void setSortFolders(boolean sortFolders){
		this.sortFolders = sortFolders;
	}
	public WrightParameters getWrightParameter(){
		return wrightParams;
	}
	public DepthParameters getDepthParameter(){
		return depthParams;
	}
	public enum WrightParameters{
		OverwriteIfEgssits,OverwriteIfNewer,KeepBoth,None;
	}
	public enum DepthParameters{
		TopLevel,Full;
	}
}
