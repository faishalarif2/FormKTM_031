/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package formktm.formktm;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author NITRO
 */
public class ImageController {
    @RequestMapping(value = "image.gambar", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<ByteArrayResource> image(@PathVariable("gambar")String gambar) throws IOException{
        if (!gambar.equals("")|| gambar !=null) {
            try{
                Path filename = Paths.get("gambar",gambar);
                byte[] buffer = Files.readAllBytes(filename);
                ByteArrayResource byteArrayResource = new ByteArrayResource(buffer);
                return ResponseEntity.ok()
                        .contentLength(buffer.length)
                        .contentType(MediaType.parseMediaType(""))
                        .body(byteArrayResource);
            }catch (Exception e) {
            }
            
        }
        return ResponseEntity.badRequest().build();
    }
}
