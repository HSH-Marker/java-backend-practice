package jdbc01;

public class Try {
    public static void main(String[] args) {

        System.out.println("start");
        try {
            System.out.println(middle(10, 0));
        } catch (ArithmeticException e) {
            System.out.println("caught: " + e.getMessage());
        }
        System.out.println("program continues");
    }
    static int divide(int a, int b) { return a / b; }
    static int middle(int a, int b) { return divide(a, b); }
    }
//    public static void main(String[] args) {
//        System.out.println("start");
//        System.out.println(middle(10, 0));     // 10 / 0，出事
//        System.out.println("this line never runs");
//    }
//}

