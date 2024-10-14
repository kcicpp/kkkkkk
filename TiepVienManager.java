package service;

import entity.TiepVien;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.io.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class TiepVienManager {
    private List<TiepVien> tiepVienList = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);
    private final String FILE_PATH = "tiepvien.txt"; // Đường dẫn đến tệp lưu thông tin tiếp viên

    public TiepVienManager() {
        readTiepVienFromFile(); // Đọc thông tin tiếp viên từ tệp khi khởi tạo
    }

    public void showTiepVien() {
        if (tiepVienList.isEmpty()) {
            System.out.println("Không có tiếp viên nào.");
            return;
        }

        System.out.println("\n=== Danh sách tiếp viên ===");
        for (TiepVien tiepVien : tiepVienList) {
            System.out.println("Mã tiếp viên: " + tiepVien.getMaTiepVien() +
                    ", Tên tiếp viên: " + tiepVien.getTenTiepVien() +
                    ", Lương: " + tiepVien.getLuong() +
                    ", Ngày sinh: " + new SimpleDateFormat("dd/MM/yyyy").format(tiepVien.getNgaySinh()) +
                    ", Giới tính: " + tiepVien.getGioiTinh());
        }
    }

    public void addTiepVien() {
        int maTiepVien = 0;
        double luong = 0.0;
        String ngaySinh = "";
        String gioiTinh = "";

        // Kiểm tra mã tiếp viên có phải là số nguyên hay không
        while (true) {
            try {
                System.out.print("Nhập mã tiếp viên: ");
                maTiepVien = scanner.nextInt();
                scanner.nextLine(); // Đọc dòng còn lại

                // Kiểm tra mã có bị trùng lặp không
                if (timTiepVienTheoMa(maTiepVien) != null) {
                    System.out.println("Mã tiếp viên đã tồn tại. Vui lòng nhập mã khác.");
                } else {
                    break; // Thoát khỏi vòng lặp nếu mã không trùng
                }
            } catch (InputMismatchException e) {
                System.out.println("Mã tiếp viên phải là một số nguyên. Vui lòng nhập lại.");
                scanner.nextLine(); // Xóa dữ liệu lỗi còn lại trong scanner
            }
        }

        // Nhập tên tiếp viên
        String tenTiepVien = "";
        while (tenTiepVien.trim().isEmpty()) {
            System.out.print("Nhập tên tiếp viên: ");
            tenTiepVien = scanner.nextLine();
            if (tenTiepVien.trim().isEmpty()) {
                System.out.println("Tên tiếp viên không được để trống. Vui lòng nhập lại.");
            }
        }

        // Nhập lương
        while (true) {
            try {
                System.out.print("Nhập lương tiếp viên: ");
                luong = scanner.nextDouble();
                scanner.nextLine(); // Đọc dòng còn lại
                break;
            } catch (InputMismatchException e) {
                System.out.println("Lương phải là một số. Vui lòng nhập lại.");
                scanner.nextLine(); // Xóa dữ liệu lỗi còn lại trong scanner
            }
        }

        // Nhập ngày sinh
        while (true) {
            System.out.print("Nhập ngày sinh (dd/MM/yyyy): ");
            ngaySinh = scanner.nextLine();
            if (!ngaySinh.matches("\\d{2}/\\d{2}/\\d{4}")) {
                System.out.println("Định dạng ngày sinh không hợp lệ. Vui lòng nhập lại.");
            } else {
                break;
            }
        }

        // Nhập giới tính
        while (true) {
            System.out.print("Nhập giới tính (Nam/Nữ): ");
            gioiTinh = scanner.nextLine();
            if (!gioiTinh.equalsIgnoreCase("Nam") && !gioiTinh.equalsIgnoreCase("Nữ")) {
                System.out.println("Giới tính không hợp lệ. Vui lòng nhập lại.");
            } else {
                break;
            }
        }

        TiepVien tiepVien = new TiepVien(maTiepVien, tenTiepVien, luong, ngaySinh, gioiTinh);
        tiepVienList.add(tiepVien);
        writeTiepVienToFile(); // Ghi vào tệp khi thêm tiếp viên
        System.out.println("Thêm tiếp viên thành công!");
    }

    public void editTiepVien() {
        int maTiepVien = 0;
        boolean validInput = false;

        // Kiểm tra mã tiếp viên có phải là số nguyên hợp lệ hay không
        while (!validInput) {
            try {
                System.out.print("Nhập mã tiếp viên cần chỉnh sửa: ");
                maTiepVien = scanner.nextInt();
                scanner.nextLine(); // Đọc dòng còn lại
                validInput = true;
            } catch (InputMismatchException e) {
                System.out.println("Mã tiếp viên phải là một số nguyên. Vui lòng nhập lại.");
                scanner.nextLine(); // Xóa dữ liệu lỗi còn lại trong scanner
            }
        }

        TiepVien found = timTiepVienTheoMa(maTiepVien);

        if (found != null) {
            // Chỉnh sửa thông tin tiếp viên
            String tenTiepVien = "";
            while (tenTiepVien.trim().isEmpty()) {
                System.out.print("Nhập tên tiếp viên mới: ");
                tenTiepVien = scanner.nextLine();
                if (tenTiepVien.trim().isEmpty()) {
                    System.out.println("Tên tiếp viên không được để trống. Vui lòng nhập lại.");
                }
            }

            found.setTenTiepVien(tenTiepVien);
            writeTiepVienToFile(); // Ghi lại thông tin sau khi chỉnh sửa
            System.out.println("Chỉnh sửa tiếp viên thành công!");
        } else {
            System.out.println("Không tìm thấy tiếp viên với mã đã nhập.");
        }
    }

    public void deleteTiepVien() {
        int maTiepVien = 0;
        boolean validInput = false;

        while (!validInput) {
            try {
                System.out.print("Nhập mã tiếp viên cần xóa: ");
                maTiepVien = scanner.nextInt();
                scanner.nextLine(); // Đọc dòng còn lại
                validInput = true;
            } catch (InputMismatchException e) {
                System.out.println("Mã tiếp viên phải là một số nguyên. Vui lòng nhập lại.");
                scanner.nextLine(); // Xóa dữ liệu lỗi còn lại trong scanner
            }
        }

        TiepVien found = timTiepVienTheoMa(maTiepVien);

        if (found != null) {
            tiepVienList.remove(found);
            writeTiepVienToFile(); // Ghi lại thông tin sau khi xóa
            System.out.println("Xóa tiếp viên thành công!");
        } else {
            System.out.println("Không tìm thấy tiếp viên với mã đã nhập.");
        }
    }

    public TiepVien timTiepVienTheoMa(int maTiepVien) {
        for (TiepVien tiepVien : tiepVienList) {
            if (tiepVien.getMaTiepVien() == maTiepVien) {
                return tiepVien; // Tìm thấy tiếp viên
            }
        }
        return null; // Không tìm thấy
    }

    private void writeTiepVienToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (TiepVien tiepVien : tiepVienList) {
                writer.write(tiepVien.getMaTiepVien() + "," +
                        tiepVien.getTenTiepVien() + "," +
                        tiepVien.getLuong() + "," +
                        new SimpleDateFormat("dd/MM/yyyy").format(tiepVien.getNgaySinh()) + "," +
                        tiepVien.getGioiTinh());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Lỗi khi ghi thông tin tiếp viên: " + e.getMessage());
        }
    }

    private void readTiepVienFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length == 5) { // Cần có 5 thuộc tính
                    try {
                        TiepVien tiepVien = new TiepVien(
                                Integer.parseInt(data[0]), // Mã tiếp viên
                                data[1],                    // Tên tiếp viên
                                Double.parseDouble(data[2]), // Lương
                                data[3],                    // Ngày sinh
                                data[4]                     // Giới tính
                        );
                        tiepVienList.add(tiepVien);
                    } catch (NumberFormatException e) {
                        System.out.println("Lỗi định dạng mã tiếp viên hoặc lương trong tệp: " + line);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Lỗi khi đọc thông tin tiếp viên: " + e.getMessage());
        }
    }
}
