package entity;

import java.util.ArrayList;
import java.util.List;

public class ChuyenBay {
    private int maChuyenBay;
    private String ngayGioBay;
    private String diemDen;
    private String gioDiChuyen;
    private int soGhe;
    private List<PhiCong> phiCongList; // Danh sách phi công
    private List<TiepVien> tiepVienList; // Danh sách tiếp viên

    public ChuyenBay() {
        this.phiCongList = new ArrayList<>();
        this.tiepVienList = new ArrayList<>();
    }

    public ChuyenBay(int maChuyenBay, String ngayGioBay, String diemDen, String gioDiChuyen, int soGhe) {
        this();
        this.maChuyenBay = maChuyenBay;
        this.ngayGioBay = ngayGioBay;
        this.diemDen = diemDen;
        this.gioDiChuyen = gioDiChuyen;
        this.soGhe = soGhe;
    }

    public int getMaChuyenBay() {
        return maChuyenBay;
    }

    public void setMaChuyenBay(int maChuyenBay) {
        this.maChuyenBay = maChuyenBay;
    }

    public String getNgayGioBay() {
        return ngayGioBay;
    }

    public void setNgayGioBay(String ngayGioBay) {
        this.ngayGioBay = ngayGioBay;
    }

    public String getDiemDen() {
        return diemDen;
    }

    public void setDiemDen(String diemDen) {
        this.diemDen = diemDen;
    }

    public String getGioDiChuyen() {
        return gioDiChuyen;
    }

    public void setGioDiChuyen(String gioDiChuyen) {
        this.gioDiChuyen = gioDiChuyen;
    }

    public int getSoGhe() {
        return soGhe;
    }

    public void setSoGhe(int soGhe) {
        this.soGhe = soGhe;
    }
// Getters và setters cho các trường hiện tại

    public List<PhiCong> getPhiCongList() {
        return phiCongList;
    }

    public void setPhiCongList(List<PhiCong> phiCongList) {
        this.phiCongList = phiCongList;
    }

    public List<TiepVien> getTiepVienList() {
        return tiepVienList;
    }

    public void setTiepVienList(List<TiepVien> tiepVienList) {
        this.tiepVienList = tiepVienList;
    }

    // Phương thức để thêm phi công và tiếp viên
    public void addPhiCong(PhiCong phiCong) {
        this.phiCongList.add(phiCong);
    }

    public void addTiepVien(TiepVien tiepVien) {
        this.tiepVienList.add(tiepVien);
    }

    // Phương thức hiển thị thông tin chuyến bay
    public void displayChuyenBayInfo() {
        System.out.println("Mã chuyến bay: " + maChuyenBay +
                ", Ngày giờ bay: " + ngayGioBay +
                ", Điểm đến: " + diemDen +
                ", Giờ đi chuyển: " + gioDiChuyen +
                ", Số ghế: " + soGhe);

        System.out.println("=== Danh sách phi công ===");
        for (PhiCong phiCong : phiCongList) {
            System.out.println("Mã phi công: " + phiCong.getMaPhiCong() +
                    ", Tên phi công: " + phiCong.getTenPhiCong());
        }

        System.out.println("=== Danh sách tiếp viên ===");
        for (TiepVien tiepVien : tiepVienList) {
            System.out.println("Mã tiếp viên: " + tiepVien.getMaTiepVien() +
                    ", Tên tiếp viên: " + tiepVien.getTenTiepVien());
        }
    }
}
