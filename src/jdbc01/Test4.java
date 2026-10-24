package jdbc01;


class Res implements AutoCloseable{
    private String name;
    Res(String name){
        this.name=name;
    }

    @Override
    public void close()  {
        System.out.println("关闭了"+name);
    }
}

public class Test4 {
    public static void main(String[] args) {
        try (Res r1 = new Res("第一个");
             Res r2 = new Res("第二个")) {
            System.out.println("在 try 块里干活");
        }
        System.out.println("try 块结束");
    }
}
