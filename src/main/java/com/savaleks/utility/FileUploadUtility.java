package com.savaleks.utility;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

public class FileUploadUtility {

    public static final Logger LOGGER = LoggerFactory.getLogger(FileUploadUtility.class);

    public static final String ABSOLUTE_PATH = "C:\\workspace\\small-store\\src\\main\\resources\\static\\images\\";

    public static String RELATIVE_PATH = "";

    public static void uploadFile(HttpServletRequest request, MultipartFile file, String code) {

        // get real path
        RELATIVE_PATH = request.getSession().getServletContext().getRealPath("/tmp/images/");
        LOGGER.info(RELATIVE_PATH);
        LOGGER.info(ABSOLUTE_PATH);

        // check if all directory exists
        if (!new File(ABSOLUTE_PATH).exists()){
            new File(ABSOLUTE_PATH).mkdirs();
        }
        if (!new File(RELATIVE_PATH).exists()){
            new File(RELATIVE_PATH).mkdirs();
        }

        try {
          // server upload
          file.transferTo(new File(RELATIVE_PATH + code + ".jpg"));
          // project direction (not saving image in local folder directory /static/images/)
          file.transferTo(new File(ABSOLUTE_PATH + code + ".jpg"));
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
