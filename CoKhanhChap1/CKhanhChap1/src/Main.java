import java.util.function.Function;
import java.util.stream.IntStream;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    public static void main(String[] args) {

        Runnable task1 = new PrintCharTask('a', 10);
        Runnable task2 = new PrintCharTask('b', 10);
        Runnable task3 = () -> {
                int sum = IntStream.rangeClosed(0, 100).sum();
                System.out.println("Sum is " + sum);
        };

        Thread thread1 = new Thread(task1, "Thread 1");
        Thread thread2 = new Thread(task2, "Thread 2");
        Thread thread3 = new Thread(task3, "Thread 3");

        thread1.start();
        thread2.start();
        thread3.start();

    }
}