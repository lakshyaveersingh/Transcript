package com.transcript;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.tomcat.jni.Directory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class HomeController {

	private static String UPLOAD_FOLDER = "C:\\Users\\lakshyaveer.singh\\Documents\\temp\\";

	

	@RequestMapping("/upload")
	public ModelAndView showUpload() {
		return new ModelAndView("upload.jsp");
	}

	

	@PostMapping("/upload")
	public ModelAndView fileUpload(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) {

		if (file.isEmpty()) {
			return new ModelAndView("status", "message", "Please select a file and try again");
		}

		try {
			/*
			// read and write the file to the slelected location-
			byte[] bytes = file.getBytes();
			Path path = Paths.get(UPLOAD_FOLDER + file.getOriginalFilename());
			Files.write(path, bytes);
			*/
			
			String name = "fb";
			String path = UPLOAD_FOLDER;
			String filePath = UPLOAD_FOLDER+name+".txt";
			File f = new File(UPLOAD_FOLDER);
			if(!f.mkdir())
			{
				boolean b = f.mkdirs();
				if(b==true)
				{
					f.createNewFile();
				}
			}
			String s = new String(file.getBytes());
			
			
			Files.write(Paths.get(filePath), Utils.readJSON(s).getBytes());
			//file.transferTo(f);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return new ModelAndView("status.jsp", "message", "File Uploaded sucessfully");
	}

	
}
