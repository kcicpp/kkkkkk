import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import service.ChuyenBayManager;
import service.PhiCongManager;
import service.TiepVienManager;




public class Main {
    public static void main(String[] args) {
        try (FileWriter writer = new FileWriter("output.txt")) {
            writer.write("Lê Văn Liêm\n");
            writer.write("Liêm Văn Lê\n");
            System.out.println("Ghi vào tệp thành công.");
        } catch (IOException e) {
            System.out.println("Lỗi ghi tệp: " + e.getMessage());
        }

        login();
    }




    public static void login() {
        Scanner sc = new Scanner(System.in);
        String username = "admin";
        String password = "123";
        String inputUsername;
        String inputPassword;

        while (true) {
            System.out.print("Nhập tên đăng nhập: ");
            inputUsername = sc.nextLine();
            System.out.print("Nhập mật khẩu: ");
            inputPassword = sc.nextLine();

            if (inputUsername.equals(username) && inputPassword.equals(password)) {
                System.out.println("Đăng nhập thành công!");
                showMenu();
                break;
            } else {
                System.out.println("Tên đăng nhập hoặc mật khẩu không đúng. Vui lòng thử lại.");
            }
        }
    }

    public static void showMenu() {
        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n=== Menu chính ===");
            System.out.println("1. Quản lý chuyến bay");
            System.out.println("2. Quản lý phi công");
            System.out.println("3. Quản lý tiếp viên"); // Thêm chức năng quản lý tiếp viên
            System.out.println("0. Thoát");

            System.out.print("Nhập lựa chọn của bạn: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    quanlychuyenbay();
                    break;
                case 2:
                    quanlyphicong();
                    break;
                case 3:
                    quanlytiepvien(); // Gọi hàm quản lý tiếp viên
                    break;
                case 0:
                    System.out.println("Thoát chương trình.");
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ. Vui lòng thử lại.");
            }
        } while (choice != 0);

        sc.close();
    }


    public static void quanlychuyenbay() {
        ChuyenBayManager chuyenBayManager = new ChuyenBayManager();
        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n=== Menu Quản lý Chuyến Bay ===");
            System.out.println("1. Hiển thị chuyến bay");
            System.out.println("2. Thêm chuyến bay");
            System.out.println("3. Chỉnh sửa chuyến bay");
            System.out.println("0. Quay lại menu chính");

            System.out.print("Nhập lựa chọn của bạn: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    chuyenBayManager.showChuyenBay();
                    break;
                case 2:
                    chuyenBayManager.addChuyenBay();
                    break;
                case 3:

                    break;
                case 0:
                    System.out.println("Quay lại menu chính.");
                    break; // Thoát khỏi vòng lặp
                default:
                    System.out.println("Lựa chọn không hợp lệ. Vui lòng thử lại.");
            }
        } while (choice != 0);
    }

    public static void quanlyphicong() {
        PhiCongManager phiCongManager = new PhiCongManager();
        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n=== Menu Quản lý Phi Công ===");
            System.out.println("1. Hiển thị phi công");
            System.out.println("2. Thêm phi công");
            System.out.println("3. Chỉnh sửa phi công");
            System.out.println("0. Quay lại menu chính");

            System.out.print("Nhập lựa chọn của bạn: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    phiCongManager.showPhiCong();
                    break;
                case 2:
                    phiCongManager.addPhiCong();
                    break;
                case 3:
                    phiCongManager.editPhiCong();
                    break;
                case 0:
                    System.out.println("Quay lại menu chính.");
                    break; // Thoát khỏi vòng lặp
                default:
                    System.out.println("Lựa chọn không hợp lệ. Vui lòng thử lại.");
            }
        } while (choice != 0);
    }
    public static void quanlytiepvien() {
        TiepVienManager tiepVienManager = new TiepVienManager();
        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n=== Menu Quản lý Tiếp Viên ===");
            System.out.println("1. Hiển thị tiếp viên");
            System.out.println("2. Thêm tiếp viên");
            System.out.println("3. Chỉnh sửa tiếp viên");
            System.out.println("0. Quay lại menu chính");

            System.out.print("Nhập lựa chọn của bạn: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    tiepVienManager.showTiepVien();
                    break;
                case 2:
                    tiepVienManager.addTiepVien();
                    break;
                case 3:
                    tiepVienManager.editTiepVien();
                    break;
                case 0:
                    System.out.println("Quay lại menu chính.");
                    break; // Thoát khỏi vòng lặp
                default:
                    System.out.println("Lựa chọn không hợp lệ. Vui lòng thử lại.");
            }
        } while (choice != 0);
    }


}