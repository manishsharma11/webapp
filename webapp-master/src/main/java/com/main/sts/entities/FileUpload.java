package com.main.sts.entities;

import java.io.File;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

 

 

 
public class FileUpload {

	private String name=null;
	private File file;
	public String getName() {
		return name;
	}
	public File getFile() {
		return file;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setFile(File file) {
		this.file = file;
		//this.name=file.getOriginalFilename();
	}

}
