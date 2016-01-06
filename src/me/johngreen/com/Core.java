package me.johngreen.com;

import java.util.ArrayList;

import me.johngreen.com.SorterParameters.DepthParameters;
import me.johngreen.com.SorterParameters.WrightParameters;

public class Core {
	private boolean running;
	private int reqTps;
	private ArrayList<SorterInstance> instances;
	public Core(){
		this.reqTps = 1;
		this.running = false;
		this.instances = new ArrayList<>();
	}
	public void run(){
		running=true;
		SorterParameters params = new SorterParameters(WrightParameters.KeepBoth,DepthParameters.TopLevel);
		params.setIncludeEtcFolder(true);
		params.setMoveExtraFilesToEtc(true);
		SorterInstance sorterInstance = new SorterInstance("C://Users//John Green//Downloads1//", "E://New//",params);
		FolderInstance imageFolder = new FolderInstance("Images");
		imageFolder.addFileType("jpg");
		imageFolder.addFileType("png");
		sorterInstance.addFolderInstance(imageFolder);
		FolderInstance musicFolder = new FolderInstance("Music");
		musicFolder.addFileType("mp3");
		musicFolder.addFileType("wav");
		sorterInstance.addFolderInstance(musicFolder);
		FolderInstance videoFolder = new FolderInstance("Videos");
		videoFolder.addFileType("mp4");
		sorterInstance.addFolderInstance(videoFolder);
		FolderInstance documentFolder = new FolderInstance("Documents");
		documentFolder.addFileType("txt");
		documentFolder.addFileType("xls");
		documentFolder.addFileType("docx");
		documentFolder.addFileType("pdf");
		documentFolder.addFileType("rtf");
		documentFolder.addFileType("csv");
		documentFolder.addFileType("xlsx");
		sorterInstance.addFolderInstance(documentFolder);
		FolderInstance aplicationsFolder = new FolderInstance("Aplications and Programs");
		aplicationsFolder.addFileType("exe");
		aplicationsFolder.addFileType("jar");
		aplicationsFolder.addFileType("jnlp");
		sorterInstance.addFolderInstance(aplicationsFolder);
		FolderInstance compressedFiles = new FolderInstance("CompressedFiles");
		compressedFiles.addFileType("zip");
		sorterInstance.addFolderInstance(compressedFiles);
		
		instances.add(sorterInstance);
		while(running){
			try {
				Thread.sleep(1000/reqTps);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			tick();
		}
	}
	public void tick(){
		for(SorterInstance instance:instances){
			instance.scanAndSort();
		}
	}
}
