package service;

import entity.PhiCong;
import java.io.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class PhiCongManager {
    private List<PhiCong> phiCongList = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);
    private final String FILE_PATH = "phicong.txt"; // Đường dẫn đến tệp lưu thông tin phi công

    public PhiCongManager() {
        readPhiCongFromFile(); // Đọc thông tin phi công từ tệp khi khởi tạo
    }

    public void showPhiCong() {
        if (phiCongList.isEmpty()) {
            System.out.println("Không có phi công nào.");
            return;
        }

        System.out.println("\n=== Danh sách phi công ===");
        for (PhiCong phiCong : phiCongList) {
            System.out.println("Mã phi công: " + phiCong.getMaPhiCong() +
                    ", Tên phi công: " + phiCong.getTenPhiCong());
        }
    }

    public void addPhiCong() {
        int maPhiCong = 0;
        boolean validInput = false;

        // Kiểm tra mã phi công có phải là số nguyên hay không
        while (!validInput) {
            try {
                System.out.print("Nhập mã phi công: ");
                maPhiCong = scanner.nextInt();
                scanner.nextLine(); // Đọc dòng còn lại
                validInput = true;
            } catch (InputMismatchException e) {
                System.out.println("Mã phi công phải là một số nguyên. Vui lòng nhập lại.");
                scanner.nextLine(); // Xóa dữ liệu lỗi còn lại trong scanner
            }
        }

        // Kiểm tra tên phi công không được để trống
        String tenPhiCong = "";
        while (tenPhiCong.trim().isEmpty()) {
            System.out.print("Nhập tên phi công: ");
            tenPhiCong = scanner.nextLine();
            if (tenPhiCong.trim().isEmpty()) {
                System.out.println("Tên phi công không được để trống. Vui lòng nhập lại.");
            }
        }

        PhiCong phiCong = new PhiCong(maPhiCong, tenPhiCong);
        phiCongList.add(phiCong);
        writePhiCongToFile(); // Ghi vào tệp khi thêm phi công
        System.out.println("Thêm phi công thành công!");
    }

    public void editPhiCong() {
        int maPhiCong = 0;
        boolean validInput = false;

        // Kiểm tra mã phi công có phải là số nguyên hợp lệ hay không
        while (!validInput) {
            try {
                System.out.print("Nhập mã phi công cần chỉnh sửa: ");
                maPhiCong = scanner.nextInt();
                scanner.nextLine(); // Đọc dòng còn lại
                validInput = true;
            } catch (InputMismatchException e) {
                System.out.println("Mã phi công phải là một số nguyên. Vui lòng nhập lại.");
                scanner.nextLine(); // Xóa dữ liệu lỗi còn lại trong scanner
            }
        }

        PhiCong found = null;
        for (PhiCong phiCong : phiCongList) {
            if (phiCong.getMaPhiCong() == maPhiCong) {
                found = phiCong;
                break;
            }
        }

        if (found != null) {
            // Kiểm tra tên phi công không được để trống
            String tenPhiCong = "";
            while (tenPhiCong.trim().isEmpty()) {
                System.out.print("Nhập tên phi công mới: ");
                tenPhiCong = scanner.nextLine();
                if (tenPhiCong.trim().isEmpty()) {
                    System.out.println("Tên phi công không được để trống. Vui lòng nhập lại.");
                }
            }

            found.setTenPhiCong(tenPhiCong);
            writePhiCongToFile(); // Ghi lại thông tin sau khi chỉnh sửa
            System.out.println("Chỉnh sửa phi công thành công!");
        } else {
            System.out.println("Không tìm thấy phi công với mã đã nhập.");
        }
    }

    private void writePhiCongToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (PhiCong phiCong : phiCongList) {
                writer.write(phiCong.getMaPhiCong() + "," + phiCong.getTenPhiCong());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Lỗi khi ghi thông tin phi công: " + e.getMessage());
        }
    }

    private void readPhiCongFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length == 2) {
                    try {
                        PhiCong phiCong = new PhiCong(Integer.parseInt(data[0]), data[1]);
                        phiCongList.add(phiCong);
                    } catch (NumberFormatException e) {
                        System.out.println("Lỗi định dạng mã phi công trong tệp: " + line);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Lỗi khi đọc thông tin phi công: " + e.getMessage());
        }
    }
}
