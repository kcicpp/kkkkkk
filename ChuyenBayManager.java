package service;

import entity.ChuyenBay;
import entity.PhiCong;
import entity.TiepVien;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ChuyenBayManager {
    private List<ChuyenBay> chuyenBayList = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);
    private final String FILE_PATH = "chuyenbay.txt"; // Đường dẫn đến tệp lưu thông tin chuyến bay

    // Thêm các manager để lấy danh sách phi công và tiếp viên
    private PhiCongManager phiCongManager = new PhiCongManager();
    private TiepVienManager tiepVienManager = new TiepVienManager();

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

        String ngayGioBay;

        // Nhập ngày giờ
        while (true) {
            System.out.print("Nhập ngày bay (dd/MM/yyyy giờ:phút): ");
            ngayGioBay = scanner.nextLine();
            if (isValidDateTime(ngayGioBay)) { // Kiểm tra tính hợp lệ của ngày giờ
                break; // Thoát khỏi vòng lặp
            } else {
                System.out.println("Định dạng ngày giờ không hợp lệ. Vui lòng nhập lại.");
            }
        }

        System.out.print("Nhập điểm đến: ");
        String diemDen = scanner.nextLine();
        System.out.print("Nhập giờ di chuyển: ");
        String gioDiChuyen = scanner.nextLine();
        System.out.print("Nhập số ghế: ");
        int soGhe = scanner.nextInt();

        // Chọn phi công cho chuyến bay
        List<PhiCong> phiCongList = chonPhiCong();

        // Chọn tiếp viên cho chuyến bay
        List<TiepVien> tiepVienList = chonTiepVien();

        // Tạo chuyến bay mới và thêm phi công, tiếp viên
        ChuyenBay chuyenBay = new ChuyenBay(maChuyenBay, ngayGioBay, diemDen, gioDiChuyen, soGhe, phiCongList, tiepVienList);
        chuyenBayList.add(chuyenBay);

        writeChuyenBayToFile(); // Ghi vào tệp khi thêm chuyến bay
        System.out.println("Thêm chuyến bay thành công!");
    }

    // Chọn phi công từ danh sách có sẵn
    private List<PhiCong> chonPhiCong() {
        List<PhiCong> phiCongList = new ArrayList<>();
        phiCongManager.showPhiCong(); // Hiển thị danh sách phi công hiện có

        System.out.print("Nhập số lượng phi công cần thêm: ");
        int soLuongPhiCong = scanner.nextInt();
        scanner.nextLine(); // Đọc dòng còn lại

        for (int i = 0; i < soLuongPhiCong; i++) {
            System.out.print("Nhập mã phi công: ");
            int maPhiCong = scanner.nextInt();
            scanner.nextLine(); // Đọc dòng còn lại

            PhiCong phiCong = phiCongManager.timPhiCongTheoMa(maPhiCong);
            if (phiCong != null) {
                phiCongList.add(phiCong);
            } else {
                System.out.println("Phi công với mã " + maPhiCong + " không tồn tại.");
            }
        }

        return phiCongList;
    }

    // Chọn tiếp viên từ danh sách có sẵn
    private List<TiepVien> chonTiepVien() {
        List<TiepVien> tiepVienList = new ArrayList<>();
        tiepVienManager.showTiepVien(); // Hiển thị danh sách tiếp viên hiện có

        System.out.print("Nhập số lượng tiếp viên cần thêm: ");
        int soLuongTiepVien = scanner.nextInt();
        scanner.nextLine(); // Đọc dòng còn lại

        for (int i = 0; i < soLuongTiepVien; i++) {
            System.out.print("Nhập mã tiếp viên: ");
            int maTiepVien = scanner.nextInt();
            scanner.nextLine(); // Đọc dòng còn lại

            TiepVien tiepVien = tiepVienManager.timTiepVienTheoMa(maTiepVien);
            if (tiepVien != null) {
                tiepVienList.add(tiepVien);
            } else {
                System.out.println("Tiếp viên với mã " + maTiepVien + " không tồn tại.");
            }
        }

        return tiepVienList;
    }

    public boolean isValidDateTime(String dateTime) {
        // Cập nhật regex để phù hợp với định dạng mới "dd/MM/yyyy giờ:phút"
        String regex = "^\\d{2}/\\d{2}/\\d{4} \\d{1,2}:\\d{2}$";

        // Kiểm tra xem chuỗi có khớp với regex không
        if (!dateTime.matches(regex)) {
            return false; // Không khớp với định dạng quy định
        }

        // Kiểm tra xem ngày giờ có hợp lệ không
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        format.setLenient(false); // Không cho phép các ngày không hợp lệ
        try {
            format.parse(dateTime); // Phân tích chuỗi ngày giờ
        } catch (ParseException e) {
            return false; // Ngày giờ không hợp lệ
        }

        return true; // Định dạng và giá trị ngày giờ hợp lệ
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
            ChuyenBay chuyenBay = null; // Tạo đối tượng chuyến bay để lưu trữ tạm
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length == 5) {
                    // Nếu là thông tin chuyến bay
                    chuyenBay = new ChuyenBay(
                            Integer.parseInt(data[0]),
                            data[1],
                            data[2],
                            data[3],
                            Integer.parseInt(data[4])
                    );
                    chuyenBayList.add(chuyenBay);
                } else if (data[0].equals("P") && chuyenBay != null) {
                    // Đọc phi công
                    PhiCong phiCong = new PhiCong(Integer.parseInt(data[1]), data[2]);
                    chuyenBay.addPhiCong(phiCong);
                } else if (data[0].equals("T") && chuyenBay != null) {
                    // Đọc tiếp viên
                    TiepVien tiepVien = new TiepVien(Integer.parseInt(data[1]), data[2]);
                    chuyenBay.addTiepVien(tiepVien);
                }
            }
        } catch (IOException e) {
            System.out.println("Lỗi khi đọc thông tin chuyến bay: " + e.getMessage());
        }
    }
}
