package by.it.gut.at01;

public class Main {

    public static void main(String[] args) {

        Hello hello = new Hello();
        hello.printSlogan();
        hello.setSlogan("Привет, мир!");
        hello.printSlogan();
        Args argObj = new Args(args);
        argObj.printArgs();
    }
}
