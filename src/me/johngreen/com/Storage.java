package me.johngreen.com;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class Storage {
	public static boolean fileExist(String path){
		File f = new File(path);
		if(f.exists()){
			return true;
		}
		return false;
	}
	public static void deleteFile(String path){
		File f = new File(path);
		f.delete();
	}
	public static int getFolderItemCount(String path){
		File f = new File(path);
		return f.list().length;
	}
	public static String[] getFiles(String path){
		File f = new File(path);
		String[] folders = f.list();
		return folders;
	}
	public static void createFile(String path){
		File f = new File(path);
		String[] parts = path.split("/");
		String folderName = "";
		for(int i = 0; i < parts.length-1; i++){
			folderName+=parts[i]+"/";
		}
		createFolder(folderName);
		if(!f.exists()){
			try {
				f.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	//Create the file if it dose not exist
	public static void createFile(String path,boolean createIfNull){
		if(createIfNull==false||(!fileExist(path)&&createIfNull)){
			createFile(path);
		}
	}
	public static void createFolder(String path){
		File f = new File(path);
		f.mkdirs();
	}
	public static Long lastModified(String path){
		File f = new File(path);
		if(!f.exists())return 0L;
		return f.lastModified();
	}
	public static void writeToFile(String path,ArrayList<String> lines){
		File f = new File(path);
		try (BufferedWriter out = new BufferedWriter(new FileWriter(f))){
			for(String s:lines){
				out.write(s);
				out.newLine();
			}
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void appendToFile(String path,ArrayList<String> lines){
		try (BufferedWriter out = new BufferedWriter(new OutputStreamWriter(
				new FileOutputStream(path), "UTF8"))){
			for(String s:lines){
				out.write(s);
				out.newLine();
				out.newLine();
			}
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void appendToFile(String path,String line){
		try (BufferedWriter out = new BufferedWriter(new OutputStreamWriter(
				new FileOutputStream(path), "UTF-8"))){
			out.write(line);
			out.newLine();
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static ArrayList<String> readTextFile(String path){
		ArrayList<String> lines = new ArrayList<>();
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(
			    new FileInputStream(path), "UTF-8"))) {
		    String line;
		    while ((line = reader.readLine()) != null) {
		    	lines.add(line);
		    }
		} catch (IOException e) {
			e.printStackTrace();
		}
		return lines;
	}
}
