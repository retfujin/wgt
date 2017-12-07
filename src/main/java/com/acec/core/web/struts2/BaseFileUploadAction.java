package com.acec.core.web.struts2;

import java.io.File;

public class BaseFileUploadAction extends BaseAction {
	protected File theFile;
	protected String fileName;
	protected String contenttype;
	public File getTheFile() {
		return theFile;
	}

	public void setTheFile(File theFile) {
		this.theFile = theFile;
	}

	public String getTheFileFileName() {
		return fileName;
	}

	public void setTheFileFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getTheFileContentType() {
		return contenttype;
	}

	public void setTheFileContentType(String contenttype) {
		this.contenttype = contenttype;
	}
}
