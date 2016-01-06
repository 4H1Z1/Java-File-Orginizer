package me.johngreen.com;

public class FileInstance {
	private String fileName;
	private String fileType;
	private String file;
	private boolean isFile = false;;
	public FileInstance(String file){
		String[] parts = file.split("\\.");
		this.file = file;
		this.fileName = parts[0];
		if(parts.length>1){
			isFile=true;
			this.fileType = parts[parts.length-1];
			this.fileName = file.replace("."+fileType, "");
		}
	}
	public String getNameAndType(){
		return file;
	}
	public String getFileName(){
		return fileName;
	}
	public String getFileType(){
		return fileType;
	}
	public boolean isFile(){
		return isFile;
	}
	public boolean isDirectory(){
		return !isFile;
	}
}
