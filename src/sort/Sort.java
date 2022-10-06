package sort;
import java.util.Arrays;

public class Sort {
    public static void bubbleSort(int[] array){
        
        for (int i = array.length - 1; i > 0; i--){
            for (int j = 0; j < i; j++){
                if (array[j] > array[j+1]){
                    int temp = array[j];
                    array[j] = array[j+1];
                    array[j+1] = temp;
                }
            }            
        }
    }

    public static int[] merge(int[] array1, int[] array2) {
        int[] mergedArray = new int[array1.length + array2.length];
        int i = 0, j = 0, k = 0;
        while (j < array1.length && k < array2.length) {
            if (array1[j] <= array2[k]) {
                mergedArray[i++] = array1[j++];
            }else {
                mergedArray[i++] = array2[k++];
            }
        }
        while (j < array1.length) {
            mergedArray[i++] = array1[j++];
        }
        while (k < array2.length){
            mergedArray[i++] = array2[k++];
        }
        return mergedArray;
    }

    public static int[] mergeSort(int[] array) {
        if (array.length <= 1) {
            return array;
        }
        
        int arrayLength1 = array.length / 2;
        int [] array1 = Arrays.copyOfRange(array, 0, arrayLength1);
        int [] array2 = Arrays.copyOfRange(array, arrayLength1, array.length);
        array1 = mergeSort(array1);
        array2 = mergeSort(array2);
        int [] mergedArray = merge(array1, array2);

        return mergedArray;
        
    }
}
