package DisjoinSet;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/*
 * check interface for the API discription
 * This class we will implement using the arrays
 */
public class ArrayDisjointSet<T>{
	Map<T,Integer> indexLookup;
	private static final int DEFAULT_SIZE = 3;
	/*    In array if it is non-representative element we will store the index of the parent
    If it is the reperesentative element we will store the negative of the size of the tree*/
	private int[] arr;
	private int size;
	private int currentIndex;
	public ArrayDisjointSet(){
		this(ArrayDisjointSet.DEFAULT_SIZE);
	}
	public ArrayDisjointSet(int size){
		if(size <= 0){
			throw new IllegalArgumentException("Size must be greater than 0");
		}
		arr = new int[size];
		this.currentIndex = 0;
		this.size = size;
		//here Integer is index of the element in array
		indexLookup = new HashMap<T,Integer>();
	}
	/*
	 * Takes array as input and it will make set of the each element
	 */
	public ArrayDisjointSet(T[] t){
		if(t.length <= 0){
			throw new IllegalArgumentException("Array should not be empty otherwise initialise with default constructor");			
		}
		this.arr = new int[t.length];
		this.size = t.length;
		this.currentIndex = 0;
		indexLookup = new HashMap<T,Integer>();
		for(int i = 0 ; i < t.length ; i++){
			makeSet(t[i]);
		}
	}
	/*
	 * This will take collections such as maps,sets 
	 */
	public ArrayDisjointSet(Collection<? extends T> col){
		if(col.size() <= 0){
			throw new IllegalArgumentException("Array should not be empty otherwise initialise with default constructor");			
		}
		this.arr = new int[col.size()];
		this.size =col.size();
		this.currentIndex = 0;
		indexLookup = new HashMap<T,Integer>();
		Iterator<? extends T> iterator = col.iterator();
		while(iterator.hasNext()){
			T t  = iterator.next();
			makeSet(t);
		}
	}
	public void makeSet(T t) {
		// TODO Auto-generated method stub
		if(t == null){
			throw new IllegalArgumentException("Input should not be null");
		}
		if(this.currentIndex >= size){
			doubleTheSize();
		}
		indexLookup.put(t,this.currentIndex);
		//during makeSet each element will in disjoint set and element it self is the
		//reperesentative element size is 1
		this.arr[this.currentIndex] = -1;
		this.currentIndex++;
	}

	/*
	 * In find we will return the set number
	 * In Array implementation we will maintain the set numbers
	 */
	public int find(T t) {
		// TODO Auto-generated method stub
		Integer index  = indexLookup.get(t);
		if(index == null){
			throw new IllegalArgumentException("Element not found in any set");
		}
		int temp = index;
		while(this.arr[temp] >= 0){
			temp = this.arr[temp];
		}
		int parent = temp;
		//Doing the path compression
		while(this.arr[index] >= 0){
			temp = this.arr[index];
			this.arr[index] = parent;
			index = temp;
		}
		return parent;
	}

	/*
	 * two parameters are set numbers 
	 * It we give illegal set Number it will throw Illegal Argument exception
	 * It will return new set number after the union
	 */
	public int union(int set1, int set2) {
		boolean validArg1 = false, validArg2 = false;
		if( set1 >= 0 && set1 < this.size && this.arr[set1] < 0){
            validArg1 = true;
		}
		if( set2 >= 0 && set2 < this.size && this.arr[set2] < 0){
            validArg2 = true;
		}
		if(validArg1 == false){
			throw new IllegalArgumentException("Illegal First Argument");			
		}
		if(validArg2 == false){
			throw new IllegalArgumentException("Illegal Second Argument");			
		}
		int set1Size = this.arr[set1] * -1;
		int set2Size = this.arr[set2] * -1;
		if(set1Size > set2Size){
			set2Size = this.arr[set2];
			this.arr[set2] = set1;
			this.arr[set1] = this.arr[set1]+set2Size;
			return set1;
		}else{
			set1Size = this.arr[set1];
			this.arr[set1] = set2;
			this.arr[set2] = this.arr[set2]+set1Size;
			return set2;
		}
		
	}
    
	private void doubleTheSize(){
		int tempArr[] = new int[this.size*2];
		for(int i = 0 ; i < this.arr.length ; i++){
			tempArr[i] = this.arr[i];
		}
		this.arr = tempArr;
		this.size = this.size*2;
	}


}
