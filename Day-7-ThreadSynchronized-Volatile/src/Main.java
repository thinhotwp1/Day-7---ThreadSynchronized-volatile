public class Main {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("-----------RaceCondition Problem Start-----------------");
        /** Khi nhiều luồng cùng update cùng 1 biến sẽ xảy ra lỗi Race Condition, dữ liệu không được đảm bảo */
        new SynchronizedSolution().raceConditionProblem();

        System.out.println("\n-----------Synchronized Solution Start-----------------");
        /**  Khi nhiều luồng cùng update cùng 1 thuộc tính đồng bộ dùng Synchronized, kết quả sẽ đúng nhưng vì lock nên time tăng cao */
        new SynchronizedSolution().synchronizedSolution();

        System.out.println("\n-----------Visibility Problem Start-----------------");
        /** TÍnh hiển thị sẽ sai khi cả 2 luồng cùng update 1 trường */
        new VolatileSolution().visibilityProblem();

        System.out.println("\n-----------Volatile Solution Start-----------------");
        /**
         * Volatile đảm bảo kết quả được đẩy xuống ram luôn để các luồng khác đọc được kết quả mới nhất, không đảm bảo cho cập nhật nhiều luồng cùng lúc,
         * điều này giúp tránh performance với solution là chỉ duy nhất 1 luồng ghi và các luồng khác luôn đọc được kết quả mới nhất
         * --> Hậu quả: đọc chậm hơn nhiều so với đọc từ cache CPU thông thường vì khi dùng volatile thì luôn đọc từ RAM.
         * --> Nhưng so với dùng Synchronized, chi phí này là rất nhỏ so với lợi ích đảm bảo đúng hành vi đa luồng, ngoài ra Synchronized có thể gây deadlock
         * --> Lưu ý: chỉ dùng volatile với dữ liệu nguyên thủy và object immutable (bất biến) như String, vì khi gán giá trị mới thì String gán vào địa chỉ ô nhớ mới chứ không thay đổi dữ liệu cũ
         *     Với các object như List<> Map<> thì volatile chỉ đảm bảo luôn đọc địa chỉ ô nhớ mới nhất, chứ không đảm bảo dữ liệu ở ô nhớ đó
         */
        new VolatileSolution().volatileSolution();
    }
}