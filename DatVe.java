package entity;

public class DatVe {
    private int maVe;
    private int maChuyenBay;

    public DatVe() {
    }

    public DatVe(int maVe, int maChuyenBay) {
        this.maVe = maVe;
        this.maChuyenBay = maChuyenBay;
    }

    public int getMaVe() {
        return maVe;
    }

    public void setMaVe(int maVe) {
        this.maVe = maVe;
    }

    public int getMaChuyenBay() {
        return maChuyenBay;
    }

    public void setMaChuyenBay(int maChuyenBay) {
        this.maChuyenBay = maChuyenBay;
    }
}
