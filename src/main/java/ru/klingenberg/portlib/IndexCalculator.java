package ru.klingenberg.portlib;

import java.util.*;

public class IndexCalculator {

    public Integer[][] allPossibleCombinations(String[] strings) {
        return allPossibleCombinations(stringArrayToIntArray(strings));
    }

    public Integer[][] allPossibleCombinations(Integer[][] arrays){
        List<Integer[]> result = new ArrayList<>();
        Integer[] array = arrays[0];
        Integer[][] tmp = Arrays.copyOfRange(arrays, 1, arrays.length);
        for (Integer integer : array) {
            result.addAll(Arrays.asList(allPossibleCombinations(integer, tmp)));
        }
        return result.toArray(new Integer[0][]);
    }

    private Integer[][] allPossibleCombinations(Integer i, Integer[][] arrays){
        if (arrays.length == 0) return new Integer[][]{ {i} };
        List<Integer[]> result = new ArrayList<>();
        Integer[] array = arrays[0];
        Integer[][] newArrays = Arrays.copyOfRange(arrays, 1, arrays.length);
        for (Integer integer : array) {
            for (Integer[] x : allPossibleCombinations(integer, newArrays)) {
                result.add(addToStartOfArray(i, x));
            }
        }
        return result.toArray(new Integer[0][0]);
    }

    private Integer[] addToStartOfArray(Integer i, Integer[] array){
        Integer[] result = new Integer[array.length + 1];
        result[0] = i;
        System.arraycopy(array, 0, result, 1, array.length);
        return result;
    }

    public Integer[][] stringArrayToIntArray(String[] strings){
        Integer[][] integers = new Integer[strings.length][];
        for (int i = 0; i<strings.length; i++){
            integers[i] = stringToIntArray(strings[i]);
        }
        return integers;
    }

    public Integer[] stringToIntArray(String string) {
        if (checkString(string)) {
            Set<Integer> nums = new LinkedHashSet<>();
            for (String numRange : string.split(",")) {
                nums.addAll(Arrays.asList(numsFromRange(numRange)));
            }
            return nums.toArray(new Integer[0]);
        } else throw new RuntimeException("Wrong data format");
    }

    private Integer[] numsFromRange(String string) {
        if(string.matches("\\d+-\\d+")){
            String[] nums = string.split("-");
            int min = Integer.parseInt(nums[0]);
            int max = Integer.parseInt(nums[1]);
            if(max <= min) throw new RuntimeException("Wrong data format");
            Integer[] result = new Integer[max - min + 1];
            for (int i = min; i <= max; i++){
                result[i-min] = i;
            }
            return result;
        } else return new Integer[] {Integer.parseInt(string)};
    }

    private boolean checkString(String string) {
        return string.matches("[\\d,\\-]+");
    }
}
