package service;
import java.text.SimpleDateFormat;

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
                    ", Tên phi công: " + phiCong.getTenPhiCong() +
                    ", Lương: " + phiCong.getLuong() +
                    ", Ngày sinh: " + new SimpleDateFormat("dd/MM/yyyy").format(phiCong.getNgaySinh()) +
                    ", Giới tính: " + phiCong.getGioiTinh());
        }
    }


    public void addPhiCong() {
        int maPhiCong = 0;
        double luong = 0.0;
        String ngaySinh = "";
        String gioiTinh = "";

        // Nhập mã phi công
        while (true) {
            try {
                System.out.print("Nhập mã phi công: ");
                maPhiCong = scanner.nextInt();
                scanner.nextLine(); // Đọc dòng còn lại
                break;
            } catch (InputMismatchException e) {
                System.out.println("Mã phi công phải là một số nguyên. Vui lòng nhập lại.");
                scanner.nextLine(); // Xóa dữ liệu lỗi còn lại trong scanner
            }
        }

        // Nhập tên phi công
        String tenPhiCong = "";
        while (tenPhiCong.trim().isEmpty()) {
            System.out.print("Nhập tên phi công: ");
            tenPhiCong = scanner.nextLine();
            if (tenPhiCong.trim().isEmpty()) {
                System.out.println("Tên phi công không được để trống. Vui lòng nhập lại.");
            }
        }

        // Nhập lương
        while (true) {
            try {
                System.out.print("Nhập lương phi công: ");
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

        // Tạo đối tượng PhiCong mới
        PhiCong phiCong = new PhiCong(maPhiCong, tenPhiCong, luong, ngaySinh, gioiTinh);
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
            // Chỉnh sửa tên phi công
            String tenPhiCong = "";
            while (tenPhiCong.trim().isEmpty()) {
                System.out.print("Nhập tên phi công mới (hiện tại: " + found.getTenPhiCong() + "): ");
                tenPhiCong = scanner.nextLine();
                if (tenPhiCong.trim().isEmpty()) {
                    System.out.println("Tên phi công không được để trống. Vui lòng nhập lại.");
                }
            }
            found.setTenPhiCong(tenPhiCong);

            // Chỉnh sửa lương
            double luong = 0.0;
            while (true) {
                try {
                    System.out.print("Nhập lương phi công mới (hiện tại: " + found.getLuong() + "): ");
                    luong = scanner.nextDouble();
                    scanner.nextLine(); // Đọc dòng còn lại
                    break;
                } catch (InputMismatchException e) {
                    System.out.println("Lương phải là một số. Vui lòng nhập lại.");
                    scanner.nextLine(); // Xóa dữ liệu lỗi còn lại trong scanner
                }
            }
            found.setLuong(luong);

            // Chỉnh sửa ngày sinh
            String ngaySinh = "";
            while (true) {
                System.out.print("Nhập ngày sinh mới (dd/MM/yyyy, hiện tại: " + new SimpleDateFormat("dd/MM/yyyy").format(found.getNgaySinh()) + "): ");
                ngaySinh = scanner.nextLine();
                if (!ngaySinh.matches("\\d{2}/\\d{2}/\\d{4}")) {
                    System.out.println("Định dạng ngày sinh không hợp lệ. Vui lòng nhập lại.");
                } else {
                    break;
                }
            }
            found.setNgaySinh(ngaySinh);

            // Chỉnh sửa giới tính
            String gioiTinh = "";
            while (true) {
                System.out.print("Nhập giới tính mới (Nam/Nữ, hiện tại: " + found.getGioiTinh() + "): ");
                gioiTinh = scanner.nextLine();
                if (!gioiTinh.equalsIgnoreCase("Nam") && !gioiTinh.equalsIgnoreCase("Nữ")) {
                    System.out.println("Giới tính không hợp lệ. Vui lòng nhập lại.");
                } else {
                    break;
                }
            }
            found.setGioiTinh(gioiTinh);

            writePhiCongToFile(); // Ghi lại thông tin sau khi chỉnh sửa
            System.out.println("Chỉnh sửa phi công thành công!");
        } else {
            System.out.println("Không tìm thấy phi công với mã đã nhập.");
        }
    }

    public void deletePhiCong() {
        int maPhiCong = 0;
        boolean validInput = false;

        // Kiểm tra mã phi công có phải là số nguyên hợp lệ hay không
        while (!validInput) {
            try {
                System.out.print("Nhập mã phi công cần xóa: ");
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
            phiCongList.remove(found); // Xóa phi công khỏi danh sách
            writePhiCongToFile(); // Ghi lại danh sách mới vào tệp
            System.out.println("Xóa phi công thành công!");
        } else {
            System.out.println("Không tìm thấy phi công với mã đã nhập.");
        }
    }



    private void writePhiCongToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (PhiCong phiCong : phiCongList) {
                writer.write(phiCong.getMaPhiCong() + "," +
                        phiCong.getTenPhiCong() + "," +
                        phiCong.getLuong() + "," +
                        new SimpleDateFormat("dd/MM/yyyy").format(phiCong.getNgaySinh()) + "," +
                        phiCong.getGioiTinh());
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
                if (data.length == 5) { // Cần có đủ 5 tham số
                    try {
                        PhiCong phiCong = new PhiCong(
                                Integer.parseInt(data[0]), // maPhiCong
                                data[1],                    // tenPhiCong
                                Double.parseDouble(data[2]), // luong
                                data[3],                    // ngaySinh
                                data[4]                     // gioiTinh
                        );
                        phiCongList.add(phiCong);
                    } catch (NumberFormatException e) {
                        System.out.println("Lỗi định dạng trong tệp: " + line);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Lỗi khi đọc thông tin phi công: " + e.getMessage());
        }
    }

}
