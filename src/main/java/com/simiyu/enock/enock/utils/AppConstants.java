package com.simiyu.enock.enock.utils;

public class AppConstants {
	public static final String FILE_PROPERTIES_PREFIX = "file";
	public static final String FILE_STORAGE_EXCEPTION_PATH_NOT_FOUND = "could not create the directory where the uploaded files will be stored";
	public static final String JPEG_FILE_FORMAT = ".jpeg";
	public static final String SUCCESS_CODE ="VEH-200";
	public static final String SUCCESS_MSG ="vehicle created successfully";
	public static final String PNG_FILE_FORMAT = ".png";
	public static final String JPG_FILE_FORMAT = ".jpg";
	public static final String DOWNLOAD_PATH = "/downloadFile/";
	public static final String VEHICLE_URI = "/vehicle";
	public static final String VEHICLE_JSON_PARAM ="vehJson";
	public static final String VEHICLE_FILE_PARAM = "file";
	public static final String TEMP_DIR ="C://TMP//";
	public static final String FILE_SEPERATOR = "_";
	public static final String INVALID_FILE_DELIMITERS = "..";
	public static final String INVALID_FILE_PATH_NAME ="FILE CONTAINS INVALID PATH SEQUENCE";
	public static final String INVALID_FILE_DIMENSIONS = "Invalid dimnsions 300 by 300";
	public static final String INVALID_FILE_FORMAT = "ONLY PNG, JPEG, JPG FILES ARE ALLOWED";
	public static final String FILE_STORAGE_EXCEPTION = "could not store file %s";
	public static final String FILE_NOT_FOUND ="file not found";
	public static final String DEFAULT_CONTENT_TYPE = "application/octet-stream";
	public static final String FILE_DOWNLOAD_HTTP_HEADER = "attachment;fileName=\"%s\"";
	public static final String DOWNLOAD_URI ="/downloadFile/{fileName:.+}";

}
