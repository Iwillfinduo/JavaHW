package edu.phystech.hw1;

import java.sql.Array;
import java.util.Arrays;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;

public class ReverseTest {

    private static int[] reverse(int[] nums) {

        int[] ret = new int[nums.length];
        for (int i = 0; i < nums.length; i++){
            ret[i] = nums[nums.length - i - 1];
        }
        return ret;
    }


    @Test
    public void reverseWorks() {
        Assertions.assertArrayEquals(new int[]{1}, reverse(new int[]{1}));
        Assertions.assertArrayEquals(new int[]{5, 4, 3, 2, 1}, reverse(new int[]{1, 2, 3, 4, 5}));
    }

    @Test
    public void reverseReturnsNewArray() {
        int[] input = {1, 2, 3, 4, 5};
        int[] copy = Arrays.copyOf(input, input.length);

        int[] reversed = reverse(input);

        Assertions.assertArrayEquals(new int[]{5, 4, 3, 2, 1}, reversed);
        Assertions.assertArrayEquals(copy, input);
    }
}
