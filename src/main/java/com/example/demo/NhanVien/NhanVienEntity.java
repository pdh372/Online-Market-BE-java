package com.example.demo.NhanVien;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "nhanvien")
public class NhanVienEntity {
    private String _id;  // Manv

    private String hoten;

    private int soNamKinhNghiem;

    private boolean thuviec;

    public boolean getThuViec(){
        return this.thuviec;
    }
}
