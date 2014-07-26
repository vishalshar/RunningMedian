
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 *
 * Program to find running median from a stream of integers
 *
 */
class IntegerMaxComparator implements Comparator<Integer> {

    @Override
    public int compare(Integer o1, Integer o2) {
        if (o1 < o2) {
            return -1;
        }
        if (o1 > o2) {
            return 1;
        }
        return 0;
    }
}

class IntegerMinComparator implements Comparator<Integer> {

    @Override
    public int compare(Integer o1, Integer o2) {
        if (o1 < o2) {
            return 1;
        }
        if (o1 > o2) {
            return -1;
        }
        return 0;
    }
}

/**
 * We can use a max heap on left side to represent elements that are less than
 * effective median, and a min heap on right side to represent elements that are
 * greater than effective median.
 *
 * After processing an incoming element, the number of elements in heaps differ
 * at most by 1 element. When both heaps contain same number of elements, we
 * pick average of heaps root data as effective median. When the heaps are not
 * balanced, we select effective median from the root of heap containing more
 * elements. Making sure that both heaps doesn't have size difference of more
 * than 1.
 *
 * Currently code handle only valid inputs.
 *
 * @author Vishal
 */
public class RunningMedian {

    public static void main(String args[]) throws IOException {

        // min and max heaps for storing left and right values.
        Comparator<Integer> comparatorMax = new IntegerMaxComparator();
        Comparator<Integer> comparatorMin = new IntegerMinComparator();
        PriorityQueue<Integer> min = new PriorityQueue<Integer>(100, comparatorMax);
        PriorityQueue<Integer> max = new PriorityQueue<Integer>(100, comparatorMin);

        // median
        int median = 0;

        // input stream
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();

        // input number from user
        while (!input.isEmpty()) {
            int num = Integer.parseInt(input);

            // check which heap to choose
            if (num < median) {
                /**
                 * check if there size are not equal
                 *
                 * if not equal move top element to other heap and insert new
                 * element else insert new element
                 *
                 */
                if (max.size() > min.size()) {
                    min.add(max.poll());
                    max.add(num);
                } else {
                    max.add(num);

                }
            } else {
                /**
                 * check if there size are not equal
                 *
                 * if not equal move top element to other heap and insert new
                 * element else insert new element
                 *
                 */
                if (max.size() < min.size()) {
                    max.add(min.poll());
                    min.add(num);
                } else {
                    min.add(num);
                }
            }

            /**
             * find median if size of both heaps are equal median is mean of top
             * elements of both heap else median is the one having more elements
             */
            if (max.size() == min.size()) {
                median = (max.peek() + min.peek()) / 2;
            } else {
                median = (max.size() > min.size()) ? (max.peek()) : (min.peek());
            }

            // Print median
            System.out.println(" Median " + median);
            input = br.readLine();
        }
    }
}
