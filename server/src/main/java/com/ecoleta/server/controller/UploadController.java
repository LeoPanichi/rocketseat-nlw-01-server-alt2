package com.ecoleta.server.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/uploads")
public class UploadController {

    @GetMapping("/{img}")
    @ResponseBody
    public ResponseEntity<InputStreamResource> getImage(@PathVariable String img) throws IOException {
        File file = new File("src/main/resources/static/uploads/" + img);
        FileInputStream fis = new FileInputStream(file);
        return ResponseEntity.ok().header("Content-Type","image/svg+xml")
        .body(new InputStreamResource(fis));
    }

    @GetMapping("/user/{img}")
    @ResponseBody
    public ResponseEntity<InputStreamResource> getUserImage(@PathVariable String img) throws FileNotFoundException {
        String fileExt = StringUtils.getFilenameExtension(img);
        File file = new File("src/main/resources/static/uploads/user/" + img);
        FileInputStream fis = new FileInputStream(file);
        return ResponseEntity.ok().header("Content-Type","image/"+fileExt)
        .body(new InputStreamResource(fis));
    }

}
