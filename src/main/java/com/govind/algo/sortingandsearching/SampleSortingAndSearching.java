package com.govind.algo.sortingandsearching;

/**
 * Created by govindp on 9/15/2015.
 */
public class SampleSortingAndSearching {
    public static void main(String[] args) {
        int[] a = {4,5,6,7,8,9,10,11};
        int[] b= {1,2,3};
        merge(a,b,5,b.length);
        for(int a1 : a){
            System.out.println(a1);
        }

    }
    public static void merge(int[] a, int[] b, int lastA, int lastB) {
       int indexA = lastA - 1; /* Index of last element in array a */
       int indexB = lastB - 1; /* Index of last element in array b */
       int indexMerged = lastB + lastA - 1; /*I* end of merged array */
              /* Merge a and b, starting from the last element in each */
     while (/*indexA >= 0 &&*/ indexB >= 0) {
            /* end of a is > than end of b */
            if (a[indexA] > b[indexB]) {
                 a[indexMerged] = a[indexA]; // copy element
                 indexMerged--; // move indices
                 indexA--;
                 } else {
                 a[indexMerged] = b[indexB]; // copy element
                 indexMerged--; // move indices
                 indexB--;
                 }
            }

         /* Copy remaining elements from b into place */
         /*while (indexB >= 0) {
             a[indexMerged] = b[indexB];
             indexMerged--;
             indexB--;
             }*/
         }
}
