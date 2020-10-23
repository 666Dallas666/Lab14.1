package com.company;
import java.util.*;

class Student{
    private int ID;
    private double GPA = -1;
    private String name;
    void setGPA(double GPA){
        this.GPA = GPA;
    }
    void setName(String name){
        this.name = name;
    }
    void setID(int ID){
        this.ID = ID;
    }
    int getID(){
        return ID;
    }
    double getGPA(){
        return GPA;
    }
    String getName(){
        return name;
    }
    Student(String name, double GPA, int ID){
        setID(ID);
        setName(name);
        setGPA(GPA);
    }
    @Override
    public String toString() {
        return "Student{" +
                "ID=" + ID +
                ", GPA=" + GPA +
                ", name='" + name + '\'' +
                '}';
    }
}
class SortingStudentsByGPA implements Comparator<Student>{
    @Override
    public int compare(Student student1, Student student2) {
        if (student1.getGPA() < student2.getGPA())
            return 1;
        if (student2.getGPA() < student1.getGPA())
            return -1;
        return 0;
    }
}
public class Main {
    public static void main(String[] args) {
        ArrayList<Student> students = new ArrayList();
        Student student1 = new Student("Alex", 2.8, 5);
        Student student2 = new Student("Max", 3.6, 4);
        Student student3 = new Student("Sasha", 4.3, 1);
        Student student4 = new Student("Jeff", 5, 2);
        Student student5 = new Student("Jack", 3.1, 3);
        students.add(student1);
        students.add(student2);
        students.add(student3);
        students.add(student4);
        students.add(student5);
        ArrayList IDNum = new ArrayList();
        students.forEach(student -> IDNum.add(student.getID()));
        int[] ID1 = new int[IDNum.size()];
        for (int i = 0; i < IDNum.size(); i++) {
            ID1[i] = (int) IDNum.get(i);
        }
        System.out.println("Выберите алгоритм сортировки:\n1. Быстрая сортировка\n2. Сортировка слиянием");
        Scanner scanner = new Scanner(System.in);
        int sw = scanner.nextInt();
        switch (sw) {
            case 1:
                quickSort(ID1, 0, IDNum.size() - 1);
                break;
            case 2:
                MergeSort(ID1, 0, IDNum.size() - 1);
                break;
        }
        for (int i = 0; i < ID1.length; i++) {
            System.out.println(ID1[i]);
        }
        sw = 0;
        System.out.println("Выберите алгоритм поиска:\n1. Линейный итеративный поиск\n2. Линейный рекурсивный поиск\n3. Бинарный итеративный поиск\n4. Бинарный рекурсивный поиск");
        sw = scanner.nextInt();
        int sw1;
        switch (sw) {
            case 1:
                System.out.println("Введите искомый ID:");
                sw1 = scanner.nextInt();
                System.out.println(linearSearch(ID1, sw1));
                break;
            case 2:
                System.out.println("Введите искомый ID:");
                sw1 = scanner.nextInt();
                System.out.println(r_linearSearch(ID1, sw1, 0));
                break;
            case 3:
                System.out.println("Введите искомый ID:");
                sw1 = scanner.nextInt();
                System.out.println(binarySearch(ID1, sw1, 0, ID1.length - 1));
                break;
            case 4:
                System.out.println("Введите искомый ID:");
                sw1 = scanner.nextInt();
                System.out.println(binSearch_r(ID1, sw1, 0, ID1.length - 1));
                break;
        }
        Collections.sort(students, new SortingStudentsByGPA());
        students.forEach(student -> System.out.println(student));
    }

    private static void MergeSort(int[] a, int lo, int hi) {
        if (hi <= lo)
            return;
        int mid = lo + (hi - lo) / 2;
        MergeSort(a, lo, mid);
        MergeSort(a, mid + 1, hi);
        int[] buf = Arrays.copyOf(a, a.length);
        for (int k = lo; k <= hi; k++)
            buf[k] = a[k];
        int i = lo, j = mid + 1;
        for (int k = lo; k <= hi; k++) {
            if (i > mid) {
                a[k] = buf[j];
                j++;
            } else if (j > hi) {
                a[k] = buf[i];
                i++;
            } else if (buf[j] < buf[i]) {
                a[k] = buf[j];
                j++;
            } else {
                a[k] = buf[i];
                i++;
            }
        }
    }

    private static void quickSort(int[] array, int low, int high) {
        if (array.length == 0)
            return;
        if (low >= high)
            return;
        int middle = low + (high - low) / 2;
        int opora = array[middle];
        int i = low, j = high;
        while (i <= j) {
            while (array[i] < opora) {
                i++;
            }
            while (array[j] > opora) {
                j--;
            }
            if (i <= j) {
                int temp = array[i];
                array[i] = array[j];
                array[j] = temp;
                i++;
                j--;
            }
        }
        if (low < j)
            quickSort(array, low, j);

        if (high > i)
            quickSort(array, i, high);
    }

    public static int linearSearch(int arr[], int elementToSearch) {

        for (int index = 0; index < arr.length; index++) {
            if (arr[index] == elementToSearch)
                return index;
        }
        return -1;
    }

    public static int binarySearch(int[] sortedArray, int key, int low, int high) {
        int index = -1;
        while (low <= high) {
            int mid = (low + high) / 2;
            if (sortedArray[mid] < key) {
                low = mid + 1;
            } else if (sortedArray[mid] > key) {
                high = mid - 1;
            } else if (sortedArray[mid] == key) {
                index = mid;
                break;
            }
        }
        return index;
    }

    public static int binSearch_r(int[] data, int value, int from, int to) {
        if (from <= to) {
            int middle = (from + to) / 2;
            if (data[middle] > value) {
                return binSearch_r(data, value, from, middle - 1);
            } else if (data[middle] < value) {
                return binSearch_r(data, value, middle + 1, to);
            }
            return middle;
        }
        return -1;
    }

    public static int r_linearSearch(int arr[], int elementToSearch, int index) {
        if (arr[index] == elementToSearch)
            return index;
        else if (index <= arr.length)
            return r_linearSearch(arr, elementToSearch, index+1);
        else return -1;
    }
}