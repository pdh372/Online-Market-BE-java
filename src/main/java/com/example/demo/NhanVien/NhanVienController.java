package com.example.demo.NhanVien;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/nhanviens")
public class NhanVienController {
    @Autowired
    NhanVienRepository nhanvienRepository;

    @GetMapping("")
    public ResponseEntity<?> getUsers(){

        List<NhanVienEntity> nhanViens =  nhanvienRepository.findAll();

        if(nhanViens.size() > 0)
            return new ResponseEntity<List<NhanVienEntity>>(nhanViens, HttpStatus.OK);
        else
            return new ResponseEntity<>("Not Found", HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{_id}")
    public ResponseEntity<?> putNhanVien(@PathVariable("_id") String _id, @RequestBody NhanVienEntity newNhanvien){
        Optional<NhanVienEntity> nhanvien = nhanvienRepository.findById(_id);
        if(nhanvien.isPresent()){

            NhanVienEntity resNhanvien = nhanvien.get();
            resNhanvien.setHoten(newNhanvien.getHoten());
            resNhanvien.setSoNamKinhNghiem(newNhanvien.getSoNamKinhNghiem());
            resNhanvien.setThuviec(newNhanvien.getThuViec());
            return new ResponseEntity<NhanVienEntity>(nhanvienRepository.save(resNhanvien), HttpStatus.OK);
        }
        else
            return new ResponseEntity<String>("Not Found id: " + _id, HttpStatus.NOT_FOUND);
    }

    @PostMapping("")
    public ResponseEntity<?> postNhanVien(@RequestBody NhanVienEntity nhanvien){
        try {
            nhanvienRepository.save(nhanvien);
            return new ResponseEntity<NhanVienEntity>(nhanvien, HttpStatus.CREATED);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{_id}")
    public ResponseEntity<?> deleteNhanVien(@PathVariable("_id") String _id) {
        Optional<NhanVienEntity> nhanvien = nhanvienRepository.findById(_id);
        if(nhanvien.isPresent()){
            NhanVienEntity resNhanvien = nhanvien.get();
            nhanvienRepository.deleteById(_id);

            return new ResponseEntity<NhanVienEntity>(resNhanvien, HttpStatus.OK);
        }
        else
            return new ResponseEntity<String>("Not Found id: " + _id, HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/all")
    public ResponseEntity<HttpStatus> deleteNhanViens() {
        try {
            nhanvienRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
