import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class Main {
    private static Callable<Integer> task = () -> {
        return 1;
    };

    public static void main(String[] args) throws Exception {
        ExecutorService pool = Executors.newCachedThreadPool();
        List<Future<Integer>> fs = new ArrayList<>();

        for (int i = 0; i < 100; i++) {
            fs.add(pool.submit(task));
        }

        pool.shutdown();

        int kq = 0;
        for (Future<Integer> f : fs) {
            kq += f.get();
        }

        System.out.println("Kết quả: " + kq);
    }
}
