package io.varun.garg.sorting;

import java.util.List;
import java.util.Random;

public class Sorting {

	public void bubbleSort(int[] array){
		int n = array.length;
		for(int i=0; i<n-1; i++){
			for(int j=0; j<n-i-1; j++){
				if(array[j] > array[j+1]){
					swap(array, j, j+1);
				}
			}
		}				
	}
	
	public void selectionSort(int[] array){
		int n= array.length;
		int min;
		for(int i=0; i<n-1; i++){
			min = i;
			for(int j=i+1; j<n; j++){
				if(array[j] < array[min]){
					min = j;
				}
			}
			
			if(min!=i){
				swap(array, i, min);
			}
			
		}	
	}
	
	public void insertionSort(int[] array){
		int n= array.length;
		
		for(int i=1; i<n; i++){
			int j=i;
			int temp = array[j];
						
			while(j>0 && temp<array[j-1]){
				array[j] = array[j-1];
				j--;
			}
			
			array[j] = temp;
		}		
	}
	
	public void shellSort(int[] array){
		
		int n = array.length;
		int gap = (n/2);
		
		while(gap>0){
			for(int i=gap; i<n; i++){
				for(int j=i-gap; (j>=0 && array[j] > array[j+gap]); j-=gap){
					swap(array, j, j+gap);
				}
			}
			gap = (gap/2);
		}		
	}
	
	public void mergeSort(int[] array, int start, int end){
		if(start<end){
			int mid = (start+end)/2;
			mergeSort(array, start, mid);
			mergeSort(array, mid+1, end);
			merge(array, start, mid, end);
		}
	}
	
	public void merge(int[] array, int start, int mid, int end){
				
		//Creating a temporary array and copy elements to temporary array
		int[] temp = new int[end-start+1];
			
		int i = start, j = mid+1;
		int k=0;
		while(i<=mid && j<=end){
			if(array[i]<=array[j]){
				temp[k] = array[i];
				i++;
			}			
			else{
				temp[k] = array[j];
				j++;
			}
			k++;			
		}
		
		while(i<=mid){
			temp[k] = array[i];
			i++;
			k++;
		}
		while(j<=end){
			temp[k] = array[j];
			j++;
			k++;
		}		
		
		k=0;
		i=start;
		while(i<=end){
			array[i] = temp[k];
			k++;
			i++;
		}
	}
	
	public void quickSort(int[] array, int start, int end){
		
		if(start<end){
			int pivot = partition(array, start, end);
			quickSort(array, start, pivot-1);
			quickSort(array, pivot+1, end);
		}
		
	}
	
	public int partition(int[] array, int start, int end){
		
		//int pivot = end;
		
		/* 
		 * This is used to generate the pivot randomly so that
		 * we don't have to face running time of O(n^2).
		 */
		
		int pivot = randomWithRange(start, end);
		swap(array, pivot, end);
		pivot = end;
		
		int pindex = start;
		for(int i=start; i<end; i++){
			if(array[i] <= array[pivot]){
				swap(array, i, pindex);
				pindex++;
			}
		}
		swap(array, pindex, pivot);
		return pindex;
	}
	
	public void swap(int[] array, int num1, int num2){
		int temp = array[num1];
		array[num1] = array[num2];
		array[num2] = temp;
	}
	
	int randomWithRange(int min, int max)
	{
	   int range = (max - min) + 1;     
	   return (int)(Math.random() * range) + min;
	}
	
	public static void main(String[] args) {
		
		int[] array = {5, 1, 4, 2, 6, 10, 45, 34, 123, 4, 1, 6, 8, 9};
		int[] array1 = {5, 1, 4, 2, 6, 10, 45, 34, 123, 4, 1, 6, 8, 9};
		Sorting sorting = new Sorting();
		//array = sorting.bubbleSort(array);
		
		/*sorting.selectionSort(array);
		System.out.print("Selection Sort -->\t");
		for (int arr : array) {
			System.out.print(arr + " ");
		}
		
		System.out.println();
		sorting.insertionSort(array);
		System.out.print("Insertion Sort -->\t");
		for (int arr : array) {
			System.out.print(arr + " ");
		}*/
		
		System.out.println();
		sorting.shellSort(array);
		System.out.print("Shell Sort -->\t");
		for (int arr : array) {
			System.out.print(arr + " ");
		}

		/*System.out.println();
		sorting.mergeSort(array1, 0, array1.length-1);
		System.out.print("Merge Sort -->\t");
		for (int arr : array1) {
			System.out.print(arr + " ");
		}*/
		
	/*	System.out.println();
		sorting.quickSort(array1, 0, array1.length-1);
		System.out.print("Quick Sort -->\t");
		for (int arr : array1) {
			System.out.print(arr + " ");
		}*/
	}

}
