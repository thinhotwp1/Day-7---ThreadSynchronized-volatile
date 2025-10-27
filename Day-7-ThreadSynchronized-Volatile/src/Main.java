//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("-----------RaceCondition Problem Start-----------------");
        /** Khi nhiều luồng cùng update cùng 1 thuộc tính sẽ xảy ra lỗi Race Condition, dữ liệu không được đảm bảo */
        new SynchronizedSolution().raceConditionProblem();

        System.out.println("-----------Synchronized Solution Start-----------------");
        /**  Khi nhiều luồng cùng update cùng 1 thuộc tính đồng bộ dùng Synchronized, kết quả sẽ đúng nhưng vì lock nên sẽ dính performance */
        new SynchronizedSolution().synchronizedSolution();

        System.out.println("-----------Visibility Problem Start-----------------");
        /** TÍnh hiển thị sẽ sai khi cả 2 luồng cùng update 1 trường */
        new VolatileSolution().visibilityProblem();

        System.out.println("-----------Volatile Solution Start-----------------");
        /**
         * Volatile chỉ đảm bảo kết quả được đẩy xuống ram luôn để các luồng khác đọc được kết quả mới nhất,
         * không đảm bảo cho cập nhật nhiều luồng cùng lúc,
         * điều này giúp tránh performance với solution là chỉ duy nhất 1 luồng ghi và các luồng khác luôn đọc được kết quả mới nhất
         * --> Hậu quả: đọc chậm hơn nhiều so với đọc từ cache CPU thông thường vì khi dùng volatile thì luôn đọc từ RAM.
         * --> Nhưng so với dùng Synchronized, chi phí này là rất nhỏ so với lợi ích đảm bảo đúng hành vi đa luồng, và Synchronized có thể gây dead block
         */
        new VolatileSolution().volatileSolution();
    }
}