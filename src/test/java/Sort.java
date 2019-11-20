import java.util.Arrays;

/**
 * @Author: Haotian
 * @Date: 2019/11/19 15:43
 * @Description: 冒泡排序
 */
public class Sort {
    public static void main(String[] args) throws MyException {
        //测试排序
        int[] array = {2, 18, 1, 19, 16, 20};
        bubbleSort( array );
    }

    /**
     * 冒泡排序
     *
     * @param array 待排序数组
     * @throws MyException 自定义的异常类
     */
    private static void bubbleSort(int[] array) throws MyException {
        //边界判断，保证程序健壮性
        if (array == null || array.length == 0) {
            throw new MyException( "the array is null or no element..." );
        }
        if (array.length == 1) {
            return;
        }
        //冒泡开始
        for (int i = 0; i < array.length; i++) {
            // 若为 true，则表示此次循环没有进行交换，即待排序列已经有序，排序已然完成
            boolean success = true;
            //每次循环后最后一个数不再参与下一次排序，所以减一
            for (int j = 0; j < array.length - i - 1; j++) {
                //前一位数大于后一位数才进行交换
                if (array[j] > array[j + 1]) {
                    //临时变量接收较大数
                    int temp = array[j];
                    //大变小
                    array[j] = array[j + 1];
                    //小变大
                    array[j + 1] = temp;
                    success = false;
                }
            }
            //排序完成即可退出循环
            if (success) {
                break;
            }
            //控制台打印查看
            System.out.println( "第" + (i + 1) + "轮后: " + Arrays.toString( array ) );
        }
    }

    /**
     * 自定义异常用于处理
     */
    public static class MyException extends Exception {
        public MyException(String message) {
            super( message );
        }
    }
}