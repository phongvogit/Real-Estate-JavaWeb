package com.laptrinhjavaweb.api.admin;


import com.laptrinhjavaweb.dto.UploadFIleDTO;
import com.laptrinhjavaweb.dto.response.ResponseDTO;
import com.laptrinhjavaweb.util.UploadFileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Base64;

@RestController(value = "fileAPIOfAdmin")
@RequestMapping(value = "/api/file")
public class FileAPI {

    @Autowired
    private UploadFileUtil uploadFileUtil;

    @PostMapping
    public ResponseDTO uploadFile(@RequestBody UploadFIleDTO uploadFIleDTO) {
        ResponseDTO responseDTO = new ResponseDTO();
        byte[] decodeBase64 = Base64.getDecoder().decode(uploadFIleDTO.getBase64().getBytes());
        uploadFileUtil.saveFile(decodeBase64, "/product/" + uploadFIleDTO.getName());
        return responseDTO;
    }
}
