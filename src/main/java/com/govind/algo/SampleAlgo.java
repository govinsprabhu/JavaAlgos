package com.govind.algo;

import java.util.*;
import java.util.regex.Matcher;

/**
 * Created by govindp on 9/2/2015.
 */
public class SampleAlgo {
    public static void main(String[] args) {
        Integer[] arrive ={ 444, 994, 508, 72, 125, 299, 181, 238, 354, 223, 691, 249, 838, 890, 758, 675, 424, 199, 201, 788, 609, 582, 979, 259, 901, 371, 766, 759, 983, 728, 220, 16, 158, 822, 515, 488, 846, 321, 908, 469, 84, 460, 961, 285, 417, 142, 952, 626, 916, 247, 116, 975, 202, 734, 128, 312, 499, 274, 213, 208, 472, 265, 315, 335, 205, 784, 708, 681, 160, 448, 365, 165, 190, 693, 606, 226, 351, 241, 526, 311, 164, 98, 422, 363, 103, 747, 507, 669, 153, 856, 701, 319, 695, 52 };
        Integer[] depart ={ 71, 73, 85, 8, 11, 62, 64, 76, 25, 65, 25, 30, 36, 81 };

        ArrayList<ArrayList<Integer>> arrayOfArrayList = new ArrayList();
        ArrayList<Integer> arrayList1 = new ArrayList<>(Arrays.asList(arrive));
        arrayList1.add(9);
        arrayList1.add(3);
        arrayList1.add(2);
        arrayList1.add(1);
        arrayList1.add(1101513929);
        arrayList1.add(-1369133069);
        arrayList1.add(1059961393);
        arrayList1.add(628175011);
        arrayOfArrayList.add(arrayList1);

        ArrayList<Integer> arrayList2 = new ArrayList<Integer>(Arrays.asList(depart));
        arrayList2.add(6);
        arrayList2.add(8);
        arrayList2.add(2);
        arrayList2.add(9);
        arrayOfArrayList.add(arrayList2);

        ArrayList<Integer> arrayList3 = new ArrayList<Integer>();
        arrayList3.add(21);
        arrayList3.add(24);
        arrayList3.add(25);
        arrayList3.add(27);
        arrayOfArrayList.add(arrayList3);
        /*searchMatrix(arrayOfArrayList);
        System.out.println(arrayList1);*/

        //System.out.println(Integer.MAX_VALUE);

        searchMatrix(8).forEach(x -> System.out.println(x));
    }

    public static Set searchMatrix(int A) {
        int copy = A;
        Set<Integer> prime = new TreeSet<>();
        if( A >= 2){
            prime.add(2);
        }
        for(int i = 3; i <= A; i++){
            int number = i;
            boolean isPrime = true;
            for(int j = 2; j <= Math.sqrt(number); j++){
                if(number % j == 0){
                    isPrime = false;
                    break;
                }
            }
            if(isPrime){
                prime.add(number);
            }
        }
        System.out.println(prime);
        Set<Integer> resultSet = new HashSet<>();
        Iterator iterator = prime.iterator();
        while (iterator.hasNext()){
            int number = (int) iterator.next();
            int other = copy - number;
            if(prime.contains(other)){
                resultSet.add(number);
                resultSet.add(other);
            }
        }
        return resultSet;

    }
    public static  int isPrime(int a) {
        if(a == 1){
            return 0;
        }
        for(int i = 2; i <= Math.sqrt(a); i++){
            if(a % i == 0){
                return 0;
            }

        }
        return 1;
    }
}
