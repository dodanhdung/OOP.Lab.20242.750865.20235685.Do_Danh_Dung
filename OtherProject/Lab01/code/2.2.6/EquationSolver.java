import javax.swing.JOptionPane;
 public class EquationSolver {
     public static void main(String[] args) {
         String[] options = {"Phương trình bậc nhất", "Hệ phương trình hai ẩn", "Phương trình bậc hai"};
         int choice = JOptionPane.showOptionDialog(null, "Chọn bài toán:",
                 "Chọn bài toán", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
         switch (choice) {
             case 0:
                 solveLinearEquation();
                 break;
             case 1:
                 solveLinearSystem();
                 break;
             case 2:
                 solveQuadraticEquation();
                 break;
         }
         System.exit(0);
     }
     private static void solveLinearEquation() {
          double a = Double.parseDouble(JOptionPane.showInputDialog("Nhập a:"));
         double b = Double.parseDouble(JOptionPane.showInputDialog("Nhập b:"));
         if (a == 0) {
             if (b == 0) {
                 JOptionPane.showMessageDialog(null, "Vô số nghiệm", "Kết quả", JOptionPane.INFORMATION_MESSAGE);
             } else {
                 JOptionPane.showMessageDialog(null, "Vô nghiệm!", "Kết quả", JOptionPane.INFORMATION_MESSAGE);
             }
             } else {
                 double x = -b / a;
                 JOptionPane.showMessageDialog(null, "Nghiệm của phương trình: x = " + x, "Kết quả", JOptionPane.INFORMATION_MESSAGE);
             }
         
     }
     private static void solveLinearSystem() {
         double a11 = Double.parseDouble(JOptionPane.showInputDialog("Nhập a11:"));
         double a12 = Double.parseDouble(JOptionPane.showInputDialog("Nhập a12:"));
         double b1 = Double.parseDouble(JOptionPane.showInputDialog("Nhập b1:"));
         double a21 = Double.parseDouble(JOptionPane.showInputDialog("Nhập a21:"));
         double a22 = Double.parseDouble(JOptionPane.showInputDialog("Nhập a22:"));
         double b2 = Double.parseDouble(JOptionPane.showInputDialog("Nhập b2:"));
         double D = a11 * a22 - a12 * a21;
         double D1 = b1 * a22 - b2 * a12;
         double D2 = a11 * b2 - a21 * b1;
         if (D == 0) {
             if (D1 == 0 && D2 == 0) {
                  JOptionPane.showMessageDialog(null, "Vô số nghiệm!", "Kết quả", JOptionPane.INFORMATION_MESSAGE);
             } else {
                 JOptionPane.showMessageDialog(null, "Vô nghiệm!", "Kết quả", JOptionPane.INFORMATION_MESSAGE);
             }
         } else {
             double x = D1 / D;
             double y = D2 / D;
             JOptionPane.showMessageDialog(null, "Nghiệm của hệ phương trình:\nx = " + x + "\ny = " + y, "Kết quả", JOptionPane.INFORMATION_MESSAGE);
         }
     }
     private static void solveQuadraticEquation() {
         double a = Double.parseDouble(JOptionPane.showInputDialog("Nhập a:"));
         double b = Double.parseDouble(JOptionPane.showInputDialog("Nhập b:"));
         double c = Double.parseDouble(JOptionPane.showInputDialog("Nhập c:"));
         if (a == 0) {
             if (b == 0) {
                 if (c == 0) {
                     JOptionPane.showMessageDialog(null, "Vô số nghiệm", "Kết quả", JOptionPane.INFORMATION_MESSAGE);
                 } else {
                     JOptionPane.showMessageDialog(null, "Vô nghiệm!", "Kết quả", JOptionPane.INFORMATION_MESSAGE);
                 }
                 } else {
                     double x = -c / b;
                     JOptionPane.showMessageDialog(null, "Phương trình có nghiệm đơn: x = " + x, "Kết quả", JOptionPane.INFORMATION_MESSAGE);
                 }
         }else{
             double delta = b * b - 4 * a * c;
             if (delta > 0) {
                 double x1 = (-b + Math.sqrt(delta)) / (2 * a);
                 double x2 = (-b - Math.sqrt(delta)) / (2 * a);
                 JOptionPane.showMessageDialog(null, "Phương trình có 2 nghiệm:\nx1 = " + x1 + "\nx2 = " + x2, "Kết quả", JOptionPane.INFORMATION_MESSAGE);
             } else if (delta == 0) {
                 double x = -b / (2 * a);
                 JOptionPane.showMessageDialog(null, "Phương trình có nghiệm kép: x = " + x, "Kết quả", JOptionPane.INFORMATION_MESSAGE);
             } else {
                 JOptionPane.showMessageDialog(null, "Vô nghiệm!", "Kết quả", JOptionPane.INFORMATION_MESSAGE);
             }
         }
     }
 }