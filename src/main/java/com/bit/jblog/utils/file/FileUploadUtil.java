package com.bit.jblog.utils.file;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

public class FileUploadUtil {

	private static String uploadAction(byte[] bytes, String name, String parentPath, String uploadPath) {

		String dividedName = new Date().getTime() + name;
		String path = parentPath + uploadPath + dividedName;
		File file = new File(path);

		try {
			BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file));
			bos.write(bytes);

			return uploadPath + dividedName;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		throw new RuntimeException("File Upload 중에 문제가 발생하였습니다.");

	}

	public static String uploadFile(MultipartFile file, String name, String parentPath, String uploadPath) {
		byte[] content = null;
		try {
			content = file.getBytes();
		} catch (Exception e) {

			return null;
		}
		return uploadAction(content, name, parentPath, uploadPath);

	}

	public static String uploadFile(byte[] file, String name, String parentPath, String uploadPath) {

		return uploadAction(file, name, parentPath, uploadPath);

	}

}
