package entity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PhiCong {
    private int maPhiCong;
    private String tenPhiCong;
    private double luong; // Thuộc tính lương
    private Date ngaySinh; // Thuộc tính ngày sinh
    private String gioiTinh; // Thuộc tính giới tính

    public PhiCong(int maPhiCong, String tenPhiCong, double luong, String ngaySinh, String gioiTinh) {
        this.maPhiCong = maPhiCong;
        this.tenPhiCong = tenPhiCong;
        this.luong = luong;
        this.gioiTinh = gioiTinh;
        try {
            this.ngaySinh = new SimpleDateFormat("dd/MM/yyyy").parse(ngaySinh); // Chuyển đổi chuỗi thành Date
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public PhiCong(int maPhiCong, String phiCongDatum) {
    }

    public int getMaPhiCong() {
        return maPhiCong;
    }

    public String getTenPhiCong() {
        return tenPhiCong;
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

    public void setTenPhiCong(String tenPhiCong) {
        this.tenPhiCong = tenPhiCong;
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
