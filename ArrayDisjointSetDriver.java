package DisjoinSet;

public class ArrayDisjointSetDriver {
   public static void main(String[] args){
	   ArrayDisjointSet<String> set = new ArrayDisjointSet<String>();
	   set.makeSet("me");
	   set.makeSet("anudeep");
	   set.makeSet("nikhil");
	   set.makeSet("gouse");
	   set.makeSet("rishanth");
	   set.makeSet("phani");
	   set.makeSet("Bill");
	   set.makeSet("warren");
	   set.makeSet("brad");
	   set.union(set.find("me"), set.find("anudeep"));
	   set.union(set.find("phani"), set.find("anudeep"));
	   set.union(set.find("gouse"), set.find("rishanth"));
	   set.union(set.find("anudeep"), set.find("rishanth"));
	   set.union(set.find("nikhil"), set.find("me"));
	   set.union(set.find("warren"), set.find("Bill"));
	   set.union(set.find("warren"), set.find("brad"));
	   if(set.find("gouse") == set.find("me")){
		   System.out.println("frnds");
	   }
	   else{
		   System.out.println("not frnds");
	   }
	   if(set.find("Bill") == set.find("me")){
		   System.out.println("frnds");
	   }
	   else{
		   System.out.println("not frnds");
	   }
	   if(set.find("gouse") == set.find("nikhil")){
		   System.out.println("frnds");
	   }
	   else{
		   System.out.println("not frnds");
	   }
	   if(set.find("Bill") == set.find("warren")){
		   System.out.println("frnds");
	   }
	   else{
		   System.out.println("not frnds");
	   }
	   if(set.find("Bill") == set.find("anudeep")){
		   System.out.println("frnds");
	   }
	   else{
		   System.out.println("not frnds");
	   }
   }
}
