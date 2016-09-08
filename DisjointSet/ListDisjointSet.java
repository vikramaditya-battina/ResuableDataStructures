package DisjoinSet;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

/*Difference betwwen array and list based is
In array based set number is represented by the
set number are the index of the array like 0,1,2
In List based there is no set number we will return the
Representative element in the set*/
public class ListDisjointSet<T> {
  private class DisjointSetNode{
	  T data;
	  int weight;
	  DisjointSetNode parent;
	  public DisjointSetNode(T t){
		  this.data = t;
		  this.weight = 1;
		  this.parent = null;
	  }
  }
  HashMap<T,DisjointSetNode> map;
  
  public ListDisjointSet(){
	  this.map = new HashMap<T,DisjointSetNode>();
  }
  
  public ListDisjointSet(Collection<? extends T> col){
	  Iterator<? extends T> iterator = col.iterator();
	  if(col.size() <= 0){
		  throw new IllegalArgumentException("please send non empty collection otherwise use default constructor");
	  }
	  while( iterator.hasNext()){
		T t = iterator.next();
		this.makeSet(t);
	  }
  }
  
  public ListDisjointSet(T[] t){
	  if(t.length <= 0){
		  throw new IllegalArgumentException("please send non empty array otherwise use default constructor");
	  }
	  for(int i = 0 ; i < t.length ; i++){
		  this.makeSet(t[i]);
	  }
  }
  
  public void makeSet(T t){
	  DisjointSetNode node = new DisjointSetNode(t);
	  node.parent = node;
	  if(map.get(t) == null){
	  map.put(t, node);
	  return;
	 }
	 throw new IllegalArgumentException("Element already exists in some of the disjoint");
  }
  
//This will return the reperesentative of the set
/*  argument should be a element in any one of the disjoint set
  otherwise it will raise a IllegalArgumentException*/
  public T find(T t){
	  if(map.get(t) == null){
		  throw new IllegalArgumentException("Element not exists in any one of the disjoint");
	  }
	  DisjointSetNode node = map.get(t);
	  DisjointSetNode temp = node;
	  while(node.parent != node){
		node = node.parent;
	  }
	  DisjointSetNode parentNode = node;
	  node = temp;
	  while(node.parent != node){
		  temp  = node.parent;
		  node.parent = parentNode;
		  node = temp;
	  }
	  return parentNode.data;
  }
  /*Both elements should be representatives of the set otherwise it will throw the illegal argument exception
  so while passing arguments better to use find before sending the argument*/
  public T union(T set1, T set2){
	  DisjointSetNode set1ReperentativeNode = map.get(set1);
	  DisjointSetNode set2ReperentativeNode = map.get(set2);
	  boolean isSet1Proper = false;
	  boolean isSet2Proper = false;
	  if( set2ReperentativeNode != null && set2ReperentativeNode.parent == set2ReperentativeNode){
		  isSet2Proper = true;
	  }
	  if( set1ReperentativeNode != null && set1ReperentativeNode.parent == set1ReperentativeNode){
		  isSet1Proper = true;
	  }
	  if(!isSet2Proper){
		 throw new IllegalArgumentException("Set2 element is not a reperesentative element"); 
	  }
	  if(!isSet1Proper){
			 throw new IllegalArgumentException("Set1 element is not a reperesentative element"); 
	  }
	  if(set1ReperentativeNode.weight > set2ReperentativeNode.weight){
		  set1ReperentativeNode.weight = set1ReperentativeNode.weight + set2ReperentativeNode.weight;
		  set2ReperentativeNode.parent = set1ReperentativeNode;
		  return set2ReperentativeNode.data;
	  }else{
		  set2ReperentativeNode.weight = set2ReperentativeNode.weight + set1ReperentativeNode.weight;
		  set1ReperentativeNode.parent = set2ReperentativeNode;
		  return set1ReperentativeNode.data;
	  }
  }
}
