import entity.HanhKhach;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import service.ChuyenBayManager;
import service.HanhKhachManager;
import service.PhiCongManager;
import service.TiepVienManager;





public class Main {
    public static void main(String[] args) {

        System.setOut(new java.io.PrintStream(System.out, true, StandardCharsets.UTF_8));

        login();
    }




    public static void login() {
        Scanner sc = new Scanner(System.in, StandardCharsets.UTF_8);
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
        Scanner sc = new Scanner(System.in, StandardCharsets.UTF_8);
        int choice;

        do {
            System.out.println("\n=== Menu chính ===");
            System.out.println("1. Quản lý chuyến bay");
            System.out.println("2. Quản lý phi công");
            System.out.println("3. Quản lý tiếp viên");
            System.out.println("4. Quản lý khách hàng");
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
                    quanlytiepvien();
                    break;
                case 4:
                    quanlykhachhang();
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
        Scanner sc = new Scanner(System.in, StandardCharsets.UTF_8);
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
        Scanner sc = new Scanner(System.in, StandardCharsets.UTF_8);
        int choice;

        do {
            System.out.println("\n=== Menu Quản lý Phi Công ===");
            System.out.println("1. Hiển thị phi công");
            System.out.println("2. Thêm phi công");
            System.out.println("3. Chỉnh sửa phi công");
            System.out.println("4. Xóa phi công"); // Thêm chức năng xóa phi công
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
                case 4:
                    phiCongManager.deletePhiCong(); // Gọi hàm xóa phi công
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
        Scanner sc= new Scanner(System.in, StandardCharsets.UTF_8);
        int choice;

        do {
            System.out.println("\n=== Menu Quản lý Tiếp Viên ===");
            System.out.println("1. Hiển thị tiếp viên");
            System.out.println("2. Thêm tiếp viên");
            System.out.println("3. Chỉnh sửa tiếp viên");
            System.out.println("4. Xóa tiếp viên");
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
                case 4:
                    tiepVienManager.deleteTiepVien();
                    break;
                case 0:
                    System.out.println("Quay lại menu chính.");
                    break; // Thoát khỏi vòng lặp
                default:
                    System.out.println("Lựa chọn không hợp lệ. Vui lòng thử lại.");
            }
        } while (choice != 0);
    }


    public static void quanlykhachhang() {
        HanhKhachManager hanhKhachManager = new HanhKhachManager();
        Scanner sc = new Scanner(System.in, StandardCharsets.UTF_8);
        int choice;

        do {
            System.out.println("\n=== Menu Quản lý Khách Hàng ===");
            System.out.println("1. Hiển thị khách hàng");
            System.out.println("2. Thêm khách hàng");
            System.out.println("3. Chỉnh sửa khách hàng");
            System.out.println("4. Xóa khách hàng");
            System.out.println("0. Quay lại menu chính");

            System.out.print("Nhập lựa chọn của bạn: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    hanhKhachManager.showHanhKhach();
                    break;
                case 2:
                    // Thêm khách hàng
                    System.out.print("Nhập mã khách hàng: ");
                    int maKhachHang = sc.nextInt();
                    sc.nextLine(); // Đọc dòng còn lại
                    System.out.print("Nhập tên khách hàng: ");
                    String tenKhachHang = sc.nextLine();
                    System.out.print("Nhập số điện thoại: ");
                    String soDienThoai = sc.nextLine();
                    System.out.print("Nhập địa chỉ: ");
                    String diaChi = sc.nextLine();
                    System.out.print("Nhập mã chuyến bay: ");
                    int maChuyenBay = sc.nextInt();
                    System.out.print("Nhập mã vé: ");
                    int maVe = sc.nextInt();

                    HanhKhach hanhKhach = new HanhKhach(maKhachHang, tenKhachHang, soDienThoai, diaChi, maChuyenBay, maVe);
                    hanhKhachManager.addHanhKhach(hanhKhach);
                    break;
                case 3:
                    // Chỉnh sửa khách hàng
                    System.out.print("Nhập mã khách hàng cần chỉnh sửa: ");
                    int maKhachHangEdit = sc.nextInt();
                    hanhKhachManager.editHanhKhach(maKhachHangEdit);
                    break;
                case 4:
                    // Xóa khách hàng
                    System.out.print("Nhập mã khách hàng cần xóa: ");
                    int maKhachHangDelete = sc.nextInt();
                    hanhKhachManager.deleteHanhKhach(maKhachHangDelete);
                    break;
                case 0:
                    System.out.println("Quay lại menu chính.");
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ. Vui lòng thử lại.");
            }
        } while (choice != 0);
    }




}
