public class VolatileSolution {
    private boolean running = true; // Thử bỏ volatile đi sẽ thấy khác biệt
    private volatile boolean runningVolatile = true; // Thử bỏ volatile đi sẽ thấy khác biệt

    public void visibilityProblem() throws InterruptedException {
        Thread writer = new Thread(() -> {
            System.out.println("Luồng ghi: bắt đầu chạy...");
            try {
                Thread.sleep(2000); // Giả lập xử lý
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Luồng ghi: thay đổi running = false");
            running = false;
        });

        Thread reader = new Thread(() -> {
            System.out.println("Luồng đọc: đang chờ biến running thay đổi...");
            long start = System.currentTimeMillis();
            long timeout = 10_000; // 10 giây
            while (running) {
                // Nếu hết 10s thì tự thoát
                if (System.currentTimeMillis() - start > timeout) {
                    System.out.println("Luồng đọc: đợi 10s không thấy running = false, tự thoát!");
                    break;
                }
            }

            if (!running) {
                System.out.println("Luồng đọc: phát hiện running = false, kết thúc!");
            }
        });

        reader.start();
        writer.start();

        writer.join();
        reader.join();
        System.out.println("Main thread: cả hai thread đã kết thúc!");
    }
    public void volatileSolution() throws InterruptedException {
        Thread writer = new Thread(() -> {
            System.out.println("Writer thread: bắt đầu chạy...");
            try {
                Thread.sleep(2000); // Giả lập xử lý
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Writer thread: thay đổi running = false");
            runningVolatile = false;
        });

        Thread reader = new Thread(() -> {
            System.out.println("Luồng đọc: đang chờ biến running thay đổi...");
            while (runningVolatile) {
                // Nếu không có volatile, thread này có thể bị kẹt mãi ở đây
            }
            System.out.println("Luồng đọc: phát hiện running = false, kết thúc!");
        });

        reader.start();
        writer.start();

        writer.join();
        reader.join();
        System.out.println("Main thread: cả hai thread đã kết thúc!");
    }
}
