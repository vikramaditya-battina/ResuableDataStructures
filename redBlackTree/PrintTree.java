package redBlackTree;

public class PrintTree {
  PrintNode root;
  PrintNode nullNode = null;
  int hght ;
  int rows;
  int cols;
  char[][] buffer;
  public PrintTree(PrintNode root){
	this.root = root;
	this.nullNode = null;
	this.hght = height();
	int rows = 3*((hght*(hght+1))/2)+hght*2;
	int cols = rows*2;
	buffer = new char[rows][cols];
	for(int i = 0 ; i < rows ; i++){
		for(int j = 0 ; j < cols ; j++){
			buffer[i][j] = ' ';
		}
	}
	this.cols = cols;
	this.rows = rows;
  }
  public PrintTree(PrintNode root,PrintNode nullNode){
	  this(root);
	  this.nullNode = nullNode;
	  
  }
  private int height(){
	  return height(this.root);
  }
  private int height(PrintNode root){
	  if(root == this.nullNode){
		  return -1;
	  }
	  int leftHeight = height(root.getLeft());
	  int rightHeight = height(root.getRight());
	  int height;
	  if( leftHeight > rightHeight){
		  height = leftHeight;
	  }else{
		  height = rightHeight;
	  }
	  return height+1;
  }
  public void printTree(){
	  fillBuffer(this.root,this.hght,0,this.cols/2);
	  for(int i = 0; i < this.rows; i++){
		  for(int j = 0 ; j < this.cols ; j++){
			  System.out.print(this.buffer[i][j]);
		  }
		  System.out.println();
	  }
  }
  public void fillBuffer(PrintNode root,int currentHeight,int x,int y){
	  char[] arr = root.getPrintChars();
	  int i,j,k;
	  for(i = y-2 ; i < (y-2+arr.length);i++){
		  this.buffer[x][i] = arr[i-(y-2)];
	  }
	  if(root.getLeft() != this.nullNode){
		  for(i = x+1,j=y-1,k=0;k<currentHeight*3;k++,i++,j--){
			  this.buffer[i][j] = '/';
		  }
		  fillBuffer(root.getLeft(),currentHeight-1,i,j);
	  }
	  if(root.getRight() != this.nullNode){
		  for(i = x+1,j=y+1,k=0;k< currentHeight*3;k++,i++,j++){
			  this.buffer[i][j] = '\\';
		  }
		  fillBuffer(root.getRight(),currentHeight-1,i,j);
	  }
	  
  }
  
}
