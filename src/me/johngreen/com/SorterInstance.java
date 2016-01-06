package me.johngreen.com;

import java.io.File;
import java.util.ArrayList;

import me.johngreen.com.SorterParameters.DepthParameters;
import me.johngreen.com.SorterParameters.WrightParameters;

public class SorterInstance {
	private boolean validPaths;
	private String inputPath;
	private String outputPath;
	private boolean runningInstance;
	private SorterParameters params;
	private ArrayList<FolderInstance> folders;
	public SorterInstance(String inputPath,String outputPath,SorterParameters params){
		Storage.createFolder(outputPath+"//"+"Etc");
		this.folders = new ArrayList<>();
		this.params = params;
		this.runningInstance = false;
		this.inputPath = inputPath;
		this.outputPath = outputPath;
		File f1 = new File(inputPath);
		File f2 = new File(outputPath);
		if(f1.exists()&&f2.exists()&&f1.isDirectory()&&f2.isDirectory()){
			validPaths=true;
		}else{
			validPaths=false;
		}	
	}
	public void addFolderInstance(FolderInstance folderInstance){
		Storage.createFolder(outputPath+"//"+folderInstance.getFolderName());
		folders.add(folderInstance);
	}
	public void scanAndSort(){
		if(validPaths&&!runningInstance){
			runningInstance=true;
			Thread instance = new Thread(new Runnable() {
				public void run(){
					processNewFolder(inputPath);
					//processNewFolder(outputPath+"Etc//");
					runningInstance=false;
				}
			});
			instance.start();
		}
	}
	private void processNewFolder(String path){
		String[] files = Storage.getFiles(path);
		if(files==null){
			System.out.println(path);
			return;
		}
		for(String file:files){
			FileInstance fileInstance = new FileInstance(file);
			for(FolderInstance folderInstance:folders){
				File oldFile = new File(path+fileInstance.getNameAndType());
				File newFile = new File(outputPath+folderInstance.getFolderName()+"//"+fileInstance.getNameAndType());
				if(oldFile.isFile()){
					if(folderInstance.containsFileType(fileInstance.getFileType())){
						processFile(oldFile,newFile,fileInstance,folderInstance.getFolderName());
					}
				}else{
					//isFolder
					if(params.getDepthParameter()==DepthParameters.Full){
						processNewFolder(path+file+"//");
						//Needs to check to see if folder is empty
						if(Storage.getFolderItemCount(oldFile.getAbsolutePath())==0){
							oldFile.delete();
						}
					}
				}
			}
			File oldFile = new File(path+file+"//");
			if(oldFile.exists()){
				File newFile = new File(outputPath+"Etc//"+file);
				processFile(oldFile,newFile,fileInstance,"Etc");
			}
		}
	}
	private void moveFolder(File oldFile,File newFile){
		newFile.mkdir();
		for(String s:oldFile.list()){
			File fileToBeMoved = new File(oldFile.getAbsolutePath()+"//"+s);
			File newlocation = new File(newFile.getAbsolutePath()+"//"+s);
			if(fileToBeMoved.isFile()){
				//file
				fileToBeMoved.renameTo(new File(newFile.getAbsolutePath()+"//"+s));
			}else{
				//directory
				moveFolder(fileToBeMoved,newlocation);
			}
		}
	}
	private void processFile(File oldFile,File newFile,FileInstance fileInstance, String folderName){
		if(!newFile.exists()||params.getWrightParameter()==WrightParameters.OverwriteIfEgssits){
			//Dose not egsist
			if(oldFile.isDirectory()){
				moveFolder(oldFile,newFile);
				oldFile.delete();
			}else{
				oldFile.renameTo(newFile);
			}
		}else if(params.getWrightParameter()==WrightParameters.KeepBoth){
			boolean done = false;
			int tick = 0;
			while(!done){
				tick++;
				newFile = new File(outputPath+folderName+"//"+fileInstance.getFileName()+tick+"."+fileInstance.getFileType());
				if(!newFile.exists()){
					oldFile.renameTo(newFile);
					done=true;
				}
			}
		}else if(params.getWrightParameter()==WrightParameters.OverwriteIfNewer){
			if(oldFile.lastModified()>newFile.lastModified()){
				oldFile.renameTo(newFile);
			}else{
				oldFile.delete();
			}
		}
	}
}
