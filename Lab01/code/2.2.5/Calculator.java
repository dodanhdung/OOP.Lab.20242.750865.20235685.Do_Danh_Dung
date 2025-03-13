import javax.swing.JOptionPane;

public class Calculator {
    public static void main(String[] args) {
        String strNum1 = JOptionPane.showInputDialog(null,"Số hạng đầu tiên:","Nhập số hạng đầu tiên", JOptionPane.INFORMATION_MESSAGE);
        String strNum2 = JOptionPane.showInputDialog(null,"Số hạng thứ hai:","Nhập số hạng thứ hai", JOptionPane.INFORMATION_MESSAGE);
            double num1 = Double.parseDouble(strNum1);
            double num2 = Double.parseDouble(strNum2);
            double sum = num1 + num2;
            double difference = num1 - num2;
            double product = num1 * num2;
            String quotient = (num2 != 0) ? String.valueOf(num1 / num2) : "Undefined (Không thực hiện được)";
            String message = "Tổng: " + sum + "\n" + "Hiệu: " + difference + "\n" + "Tích: " + product + "\n" + "Thương: " + quotient;
            JOptionPane.showMessageDialog(null, message, 
                        "Kết quả", JOptionPane.INFORMATION_MESSAGE);
        System.exit(0);
    }
}
