package redBlackTree;

public class RedBlackTreeDriver {
  public static void main(String[] args){
	  RedBlackTree<Integer> tree = new RedBlackTree<Integer>();
	  tree.insert(41);
	  tree.insert(38);
	  tree.insert(31);
	  tree.insert(12);
	  tree.insert(19);
	  tree.insert(8);
	  PrintTree print = new PrintTree(tree.root,tree.Nil);
	  print.printTree();
	  
  }
}
