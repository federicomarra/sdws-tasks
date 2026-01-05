public class StringCalculator {
    
    StringCalculator() {}
    
    public int sum(int a, int b) {
        return a + b;
    }
    
    public int sum(String a, String b) {
        int num1 = (a == null || a.trim().isEmpty()) ? 0 : Integer.parseInt(a.trim());
        int num2 = (b == null || b.trim().isEmpty()) ? 0 : Integer.parseInt(b.trim());
        return sum(num1, num2);
    }

    public int sum(String a) {
        return sum(a, "");
    }
    
    public int sum(int[] numbers) {
        if (numbers == null || numbers.length == 0) {
            return 0;
        }
        int sum = 0;
        for (int n : numbers) {
            sum = sum(sum, n);
        }
        return sum;
    }
    
    public int sum(String[] numbers) {
        if (numbers == null || numbers.length == 0) {
            return 0;
        }
        int sum = 0;
        for (String n : numbers) {
            if (n != null && !n.trim().isEmpty()) {
                sum = sum(String.valueOf(sum), n);
            }
        }
        return sum;
    }
    
    public int mult(int a, int b) {
        return a * b;
    }
    
    public int mult(String a, String b) {
        int num1 = (a == null || a.trim().isEmpty()) ? 0 : Integer.parseInt(a.trim());
        int num2 = (b == null || b.trim().isEmpty()) ? 0 : Integer.parseInt(b.trim());
        return mult(num1, num2);
    }

    public int mult(int[] numbers) {
        if (numbers == null || numbers.length == 0) {
            return 0;
        }
        int mult = 1;
        for (int n : numbers) {
            mult = mult(mult, n);
        }
        return mult;
    }
    
    public int mult(String[] numbers) {
        if (numbers == null || numbers.length == 0) {
            return 0;
        }
        int mult = 1;
        for (String n : numbers) {
            if (n != null && !n.trim().isEmpty()) {
                mult = mult(String.valueOf(mult), n);
            }
        }
        return mult;
    }
}
