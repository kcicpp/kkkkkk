package entity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TiepVien {
    private int maTiepVien;
    private String tenTiepVien;
    private double luong; // Thuộc tính lương
    private Date ngaySinh; // Thuộc tính ngày sinh
    private String gioiTinh; // Thuộc tính giới tính

    public TiepVien(int maTiepVien, String tenTiepVien, double luong, String ngaySinh, String gioiTinh) {
        this.maTiepVien = maTiepVien;
        this.tenTiepVien = tenTiepVien;
        this.luong = luong;
        this.gioiTinh = gioiTinh;
        try {
            this.ngaySinh = new SimpleDateFormat("dd/MM/yyyy").parse(ngaySinh); // Chuyển đổi chuỗi thành Date
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public TiepVien(int maTiepVien, String tiepVienDatum) {
    }

    public int getMaTiepVien() {
        return maTiepVien;
    }

    public String getTenTiepVien() {
        return tenTiepVien;
    }

    public double getLuong() {
        return luong;
    }

    public Date getNgaySinh() {
        return ngaySinh;
    }

    public String getGioiTinh() {
        return gioiTinh;
    }

    public void setTenTiepVien(String tenTiepVien) {
        this.tenTiepVien = tenTiepVien;
    }

    public void setLuong(double luong) {
        this.luong = luong;
    }

    public void setNgaySinh(String ngaySinh) {
        try {
            this.ngaySinh = new SimpleDateFormat("dd/MM/yyyy").parse(ngaySinh);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }
}
