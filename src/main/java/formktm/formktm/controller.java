/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package formktm.formktm;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author NITRO
 */
@Controller
public class controller {
    @RequestMapping("/ktm")
    @ResponseBody
    
    public String simpan(){
        return "index";
    }
    @RequestMapping("/save")
    
    public String simpan(
            @RequestParam(value = "nama") String name,
            @RequestParam(value = "nim") String nim,
            @RequestParam(value = "jurusan") String jurusan,
            @RequestParam(value = "gambar") MultipartFile gambar,
            ModelMap model){
        
        Index index = new Index();
        index.setname(name);
        index.setnim(nim);
        index.setjurusan(jurusan);
        model.addAttribute("nama", name);
        model.addAttribute("nim", nim);
        model.addAttribute("jurusan", jurusan);
        model.addAttribute("gambar", gambar);
        
        
        
        if (gambar.isEmpty()){
            return "index";
        }
        Path path = Paths.get("/gambar");
        try{
            InputStream inputStream = gambar.getInputStream();
            Files.copy(inputStream, path.resolve(gambar.getOriginalFilename()),
                    StandardCopyOption.REPLACE_EXISTING);
            index.setgambar(gambar.getOriginalFilename().toLowerCase());
            //
            model.addAttribute("name", name);
            model.addAttribute("nim", nim);
            model.addAttribute("jurusan", jurusan);
            model.addAttribute("gambar", gambar);
        }catch (Exception e) {
            e.printStackTrace();
        }
        return "viewpage";
    }
}
