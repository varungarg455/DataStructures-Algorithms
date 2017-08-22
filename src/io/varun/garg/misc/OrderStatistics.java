package io.varun.garg.misc;

public class OrderStatistics {

	public int kthSmalestElement(int[] array, int start, int end, int k){
		
		if(start<=end){
			int index = partition(array, start, end);
			if(index == (k-1)){
				return index;
			}
			else if(index < (k-1)){
				return kthSmalestElement(array, index+1, end, k);
			}
			else{ 
				return kthSmalestElement(array, start, index-1, k);
			}
		}
		return 0;
	}
	
	private int partition(int[] array, int start, int end) {
		int pindex = start;
		
		//int pivot = end;
		int pivot = randomWithRange(start, end);
		swap(array, pivot, end);
		pivot = end;
		
		for(int i=start; i<end; i++){
			if(array[i]<=array[pivot]){
				if(pindex != i){
					swap(array, pindex, i);
				}
				//System.out.print(pindex + " " + array[pindex]);
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
	
	public static void main(String[] args){
		
		//int[] array = {12, 3, 5, 70, 4, 99, 26, 7, 12};
		int[] array = {9, 8, 7, 6, 5, 4, 3, 2, 1};
		OrderStatistics orderStatistics = new OrderStatistics();
		int index = orderStatistics.kthSmalestElement(array, 0, array.length-1, 2);
		System.out.println(index + " " + array[index]);
		
		for (int arr : array) {
			System.out.print(arr + " ");
		}
	}
}
