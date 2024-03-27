package practice.tax;

import org.junit.Test;


public class TaxType2 {
    public static Tax2 calculateTax(String type, int supplyValue) {
        double taxRate = 0.1;
        int totalAmount = 0;
        int tax;

        if (type.contains("면세")) {
            totalAmount = supplyValue;
            tax = 0;
        } else if (type.contains("간이")) {
            if (supplyValue < 4000000) {
                totalAmount = supplyValue;
                tax = 0;
            } else {
                totalAmount = (int) Math.round(supplyValue * 1.1);
                tax = (int) Math.round(supplyValue * taxRate);
            }
        } else {
            throw new IllegalArgumentException("유효하지 않은 사업자 유형입니다.");
        }
        return new Tax2(totalAmount, tax);
    }
    @Test
    public void testCalculateTax() {
        String type = "간이";
        int supplyValue = 10000000;
        Tax2 result = calculateTax(type, supplyValue);



        System.out.println("사업자 유형: " + type);
        System.out.println("합계금액: " + result.getTotalAmount()
                + ", 공급가액: " + supplyValue
                + ", 부가세: " + result.getTax()
        );

    }
}

class Tax2{
    private int totalAmount;
    private int tax;

    public Tax2(int totalAmount, int tax) {
        this.totalAmount = totalAmount;
        this.tax = tax;
    }

    public int getTotalAmount() {
        return totalAmount;
    }

    public int getTax() {
        return tax;
    }
}