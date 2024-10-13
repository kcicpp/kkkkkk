package service;

import entity.TiepVien;
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
                    ", Tên tiếp viên: " + tiepVien.getTenTiepVien());
        }
    }

    public void addTiepVien() {
        int maTiepVien = 0;
        boolean validInput = false;

        // Kiểm tra mã tiếp viên có phải là số nguyên hay không
        while (!validInput) {
            try {
                System.out.print("Nhập mã tiếp viên: ");
                maTiepVien = scanner.nextInt();
                scanner.nextLine(); // Đọc dòng còn lại
                validInput = true;
            } catch (InputMismatchException e) {
                System.out.println("Mã tiếp viên phải là một số nguyên. Vui lòng nhập lại.");
                scanner.nextLine(); // Xóa dữ liệu lỗi còn lại trong scanner
            }
        }

        // Kiểm tra tên tiếp viên không được để trống
        String tenTiepVien = "";
        while (tenTiepVien.trim().isEmpty()) {
            System.out.print("Nhập tên tiếp viên: ");
            tenTiepVien = scanner.nextLine();
            if (tenTiepVien.trim().isEmpty()) {
                System.out.println("Tên tiếp viên không được để trống. Vui lòng nhập lại.");
            }
        }

        TiepVien tiepVien = new TiepVien(maTiepVien, tenTiepVien);
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

        TiepVien found = null;
        for (TiepVien tiepVien : tiepVienList) {
            if (tiepVien.getMaTiepVien() == maTiepVien) {
                found = tiepVien;
                break;
            }
        }

        if (found != null) {
            // Kiểm tra tên tiếp viên không được để trống
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

    private void writeTiepVienToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (TiepVien tiepVien : tiepVienList) {
                writer.write(tiepVien.getMaTiepVien() + "," + tiepVien.getTenTiepVien());
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
                if (data.length == 2) {
                    try {
                        TiepVien tiepVien = new TiepVien(Integer.parseInt(data[0]), data[1]);
                        tiepVienList.add(tiepVien);
                    } catch (NumberFormatException e) {
                        System.out.println("Lỗi định dạng mã tiếp viên trong tệp: " + line);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Lỗi khi đọc thông tin tiếp viên: " + e.getMessage());
        }
    }
}
