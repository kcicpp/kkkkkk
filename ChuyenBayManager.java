package service;

import entity.ChuyenBay;
import entity.PhiCong;
import entity.TiepVien;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ChuyenBayManager {
    private List<ChuyenBay> chuyenBayList = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);
    private final String FILE_PATH = "chuyenbay.txt"; // Đường dẫn đến tệp lưu thông tin chuyến bay

    public ChuyenBayManager() {
        readChuyenBayFromFile(); // Đọc thông tin chuyến bay từ tệp khi khởi tạo
    }

    public void showChuyenBay() {
        if (chuyenBayList.isEmpty()) {
            System.out.println("Không có chuyến bay nào.");
            return;
        }

        System.out.println("\n=== Danh sách chuyến bay ===");
        for (ChuyenBay chuyenBay : chuyenBayList) {
            chuyenBay.displayChuyenBayInfo(); // Hiển thị thông tin chuyến bay
        }
    }

    public void addChuyenBay() {
        System.out.print("Nhập mã chuyến bay: ");
        int maChuyenBay = scanner.nextInt();
        scanner.nextLine(); // Đọc dòng còn lại
        System.out.print("Nhập ngày giờ bay: ");
        String ngayGioBay = scanner.nextLine();
        System.out.print("Nhập điểm đến: ");
        String diemDen = scanner.nextLine();
        System.out.print("Nhập giờ di chuyển: ");
        String gioDiChuyen = scanner.nextLine();
        System.out.print("Nhập số ghế: ");
        int soGhe = scanner.nextInt();

        ChuyenBay chuyenBay = new ChuyenBay(maChuyenBay, ngayGioBay, diemDen, gioDiChuyen, soGhe);
        chuyenBayList.add(chuyenBay);
        writeChuyenBayToFile(); // Ghi vào tệp khi thêm chuyến bay
        System.out.println("Thêm chuyến bay thành công!");
    }

    private void writeChuyenBayToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (ChuyenBay chuyenBay : chuyenBayList) {
                writer.write(chuyenBay.getMaChuyenBay() + "," +
                        chuyenBay.getNgayGioBay() + "," +
                        chuyenBay.getDiemDen() + "," +
                        chuyenBay.getGioDiChuyen() + "," +
                        chuyenBay.getSoGhe());
                writer.newLine();

                // Ghi danh sách phi công
                for (PhiCong phiCong : chuyenBay.getPhiCongList()) {
                    writer.write("P," + phiCong.getMaPhiCong() + "," + phiCong.getTenPhiCong());
                    writer.newLine();
                }

                // Ghi danh sách tiếp viên
                for (TiepVien tiepVien : chuyenBay.getTiepVienList()) {
                    writer.write("T," + tiepVien.getMaTiepVien() + "," + tiepVien.getTenTiepVien());
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            System.out.println("Lỗi khi ghi thông tin chuyến bay: " + e.getMessage());
        }
    }

    private void readChuyenBayFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length == 5) {
                    // Nếu là thông tin chuyến bay
                    ChuyenBay chuyenBay = new ChuyenBay(
                            Integer.parseInt(data[0]),
                            data[1],
                            data[2],
                            data[3],
                            Integer.parseInt(data[4])
                    );
                    chuyenBayList.add(chuyenBay);

                    // Đọc phi công
                    while ((line = reader.readLine()) != null && line.startsWith("P")) {
                        String[] phiCongData = line.split(",");
                        PhiCong phiCong = new PhiCong(Integer.parseInt(phiCongData[1]), phiCongData[2]);
                        chuyenBay.addPhiCong(phiCong);
                    }

                    // Đọc tiếp viên
                    while (line != null && line.startsWith("T")) {
                        String[] tiepVienData = line.split(",");
                        TiepVien tiepVien = new TiepVien(Integer.parseInt(tiepVienData[1]), tiepVienData[2]);
                        chuyenBay.addTiepVien(tiepVien);
                        line = reader.readLine(); // Tiếp tục đọc
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Lỗi khi đọc thông tin chuyến bay: " + e.getMessage());
        }
    }
}