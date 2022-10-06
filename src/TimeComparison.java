import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class TimeComparison {
    

    public static int[] createRandomArray(int arrayLength){
        int[] array = new int[arrayLength];
        Random random = new Random();

        for (int i = 0; i < arrayLength; i++){
            array[i] = random.nextInt(100);
        }
        return array;
    }

    public static void writeArrayToFile(int[] array, String fileName) throws Exception {
        try {
            FileWriter writer = new FileWriter(fileName);
            for (int j : array){
                writer.write(j + "\n");
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Encountered IO Exception!");
            e.printStackTrace();
        }
        
        
    }

    public static int[] readFileToArray(String fileName){
        
        try{

        File file = new File(fileName);
        Scanner scanner = new Scanner(file);
        ArrayList<Integer> arrayList = new ArrayList<>();
        while (scanner.hasNextLine()){
            String str = scanner.nextLine();
            int a = Integer.parseInt(str);
            arrayList.add(a);
        }
        int[] array = new int[arrayList.size()];
        for (int i = 0; i < arrayList.size(); i++){
            array[i] = arrayList.get(i);
        }

        scanner.close();
        return array;

        } catch (FileNotFoundException e){
            e.printStackTrace();
        } 
        return null;
    }

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

    public static boolean isSorted(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            if (array[i] > array[i+1]) {
                return false;
            }
        }
        return true;
    }

    public static void printArray(int[] array) {
        for (int a : array) {
            System.out.print(a + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) throws Exception{
        long startTime;
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Please input an integer to define the length of the array:");
            int arrayLength = scanner.nextInt();
            startTime = System.currentTimeMillis();
        
            int[] array = createRandomArray(arrayLength);
            System.out.printf("Time to create an integer array of length %d is %.2f s", arrayLength, (System.currentTimeMillis() - startTime) / 1000.0);
            System.out.println();
            startTime = System.currentTimeMillis();
            // printArray(array);
            int [] sortedArray = mergeSort(array);
            System.out.printf("Merge sort integer array of length %d is %.2f s", arrayLength, (System.currentTimeMillis() - startTime) / 1000.0);
            System.out.println();
            // printArray(sortedArray);
            System.out.println("The sorted array is in correct order: " + isSorted(sortedArray));
            System.out.println();
            startTime = System.currentTimeMillis();
            bubbleSort(array);
            System.out.printf("Bubble sort integer array of length %d is %.2f s", arrayLength, (System.currentTimeMillis() - startTime) / 1000.0);
            System.out.println();
            System.out.println("Merge sort and bubble sort generate the same result: " + Arrays.equals(sortedArray, array));
            
            System.out.println("\n" + "Continue? Please enter yes or no: ");
            String yesNo = scanner.next().toLowerCase();
            if (yesNo.equals("no")){
                break;
            }
        }
        scanner.close();
        System.out.println("Goodbye!");
    }
}