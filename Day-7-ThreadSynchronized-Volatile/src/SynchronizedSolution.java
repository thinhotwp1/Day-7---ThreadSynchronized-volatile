public class SynchronizedSolution {
    private int count = 0;

    // Phương thức tăng biến đếm
    private void increment() {
        count++; // Thao tác này KHÔNG nguyên tử
    }

    // Phương thức tăng biến đếm
    private synchronized void incrementSync() {
        count++; // Thao tác này nguyên tử
    }

    public void raceConditionProblem() throws InterruptedException {
        long start = System.currentTimeMillis();
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 100000; i++) {
                increment();
            }
        });
        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 100000; i++) {
                increment();
            }
        });
        t1.start();
        t2.start();

        // Đợi cho cả 2 luồng chạy xong
        t1.join();
        t2.join();
        long end = System.currentTimeMillis();
        System.out.println("Kết quả cuối cùng (không đồng bộ): " + count + ", time: " + (end - start) + " ms");
    }

    public void synchronizedSolution() throws InterruptedException {
        long start = System.currentTimeMillis();
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 100000; i++) {
                incrementSync();
            }
        });
        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 100000; i++) {
                incrementSync();
            }
        });
        t1.start();
        t2.start();

        // Đợi cho cả 2 luồng chạy xong
        t1.join();
        t2.join();
        long end = System.currentTimeMillis();
        System.out.println("Kết quả cuối cùng (đồng bộ với synchronized): " + count + ", time: " + (end - start) + " ms");
    }

}
