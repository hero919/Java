

//assignment 5
//pair 006
//partner1 Li Haolin 
//partner1-jl412 
//partner2-Zhang Zeqing 
//partner2-hero919

import tester.Tester;


//Problem 1:


//Represents a computation over a list of X, producing an R.
interface ListVisitor<X,R> {
	// when visit Empty
	R visitEmpty();
	// when visit Cons
	R visitCons(X first, List<X> rest);

}



//Represents a list of X
interface List<X> {
	// Accept visitor and compute.
	<R> R accept(ListVisitor<X,R> v);
}


//Create empty class
class Empty<X> implements List<X> {
	public <R> R accept(ListVisitor<X,R> v) {
		return v.visitEmpty();
	}
}


//Create cons class
class Cons<X> implements List<X> {
	X first;
	List<X> rest;
	Cons(X first, List<X> rest) {
		this.first = first;
		this.rest = rest;
	}

	public <R> R accept(ListVisitor<X,R> v) {
		return v.visitCons(this.first, this.rest);
	}
}




//Check whether a list of integers has an element greater than 50.
class greaterFifty implements ListVisitor<Integer, Boolean>{
//visit empty.
	public Boolean visitEmpty(){
		return false;
	}
//Visit cons.
	public Boolean visitCons(Integer first,List<Integer> rest){
		return first>50 ||
				rest.accept(this);
	}
}


//Computes whether a list of strings has a given element.
class hasElement implements ListVisitor<String, Boolean>{
	String s;
	hasElement(String s){
		this.s = s;
	}

//Visit empty.
	public Boolean visitEmpty(){
		return false;
	}
//Visit cons.
	public Boolean visitCons(String first,List<String> rest){
		return first.equals(this.s) ||
				rest.accept(this);
	}

}



//  Computes whether a list of integers has an element greater than a given number.
class hasGreater implements ListVisitor<Integer, Boolean>{
	Integer i;
	hasGreater(Integer i){
		this.i=i;
	}
//Visit empty.
	public Boolean visitEmpty(){
		return false;
	}
//Visit cons.
	public Boolean visitCons(Integer first, List<Integer> rest){
		return first > this.i || 
				rest.accept(this);
	}
}

// Examples for List<X>
class Example{
	Cons<Integer> c1= new Cons<Integer>(5,new Empty<Integer>());
	Cons<Integer> c2= new Cons<Integer>(15,c1);
	Cons<Integer> c3= new Cons<Integer>(70,c2);

	Cons<String> c4= new Cons<String>("Apple",new Empty<String>());
	Cons<String> c5= new Cons<String>("Banana",c4);
	Cons<String> c6= new Cons<String>("Lemon",c5);

	greaterFifty g1 = new greaterFifty();
	hasElement s1 = new hasElement("Lemon");
	hasGreater h1 = new hasGreater(10);
	boolean testacceptFifty (Tester t){
		return t.checkExpect(c3.accept(g1),true)&&
				t.checkExpect(c2.accept(g1),false)&&
				t.checkExpect(c6.accept(s1),true)&&
				t.checkExpect(c4.accept(s1),false)&&
				t.checkExpect(c1.accept(h1),false)&&
				t.checkExpect(c3.accept(h1),true);
	}

}


//Problem 2,3:

//To represent a Binary Tree of X
interface BT<X>{
	int height();
	int size();
	BT<X> mirror();
	boolean ormap(Predicate<X> p);
	boolean andmap(Predicate<X> p);
	<R> R accept (BTVisitor<X, R> visitor);

}

//To represent Empty
class EmptyBT<X> implements BT<X>{
	// computes the height of a tree
	public int height(){
		return 0;
	}
	// computes the size of a tree
	public int size(){
		return 0;
	}
	// computes the mirror image of the binary tree
	public BT<X> mirror(){
		return this;
	}
	// ormap for BT<X>
	public boolean ormap(Predicate<X> p){
		return false;
	}
	// andmap for BT<X>
	public boolean andmap(Predicate<X> p){
		return true;
	}
	// accept the visitor
	public <R> R accept (BTVisitor<X, R> visitor){
		return visitor.visitEmpty();
	}

}

//To represent Node
class Node<X> implements BT<X>{
	X data;
	BT<X> left;
	BT<X> right;

	Node(X data, BT<X> left, BT<X> right){
		this.data = data;
		this.left = left;
		this.right = right;
	}

	// computes the height of a tree
	public int height(){
		return 1 + Math.max(this.left.height(), this.right.height());
	}
	// computes the size of a tree
	public int size(){
		return 1 + this.left.size() + this.right.size();
	}
	// computes the mirror image of the binary tree
	public BT<X> mirror(){
		return new Node<X>(this.data, this.right.mirror(), this.left.mirror());
	}
	// ormap for BT<X>
	public boolean ormap(Predicate<X> p){
		return p.isCorrect(this.data) || this.left.ormap(p) || this.right.ormap(p);
	}
	// andmap for BT<X>
	public boolean andmap(Predicate<X> p){
		return p.isCorrect(this.data) && this.left.andmap(p) && this.right.andmap(p);
	}
	// accept the visitor
	public <R> R accept (BTVisitor<X, R> visitor){
		return visitor.visitNode(this.data, this.left, this.right);
	}
}

//to represent predicates
interface Predicate<X>{
	boolean isCorrect(X given);
}

//to represent predicate for even numbers
class isEven implements Predicate<Integer>{
	// is the given correct under the Predicate?
	public boolean isCorrect(Integer given){
		return given%2 == 0;
	}
}

//to represent visitor for BT
interface BTVisitor<X, R>{
	R visitEmpty();
	R visitNode(X data, BT<X> left, BT<X> right);
}

//to represent functional object Height implementing BTVisitor
class Height implements BTVisitor<Integer, Integer>{

	// when visiting EmptyBT
	public Integer visitEmpty(){
		return 0;
	}
	// when visiting Node
	public Integer visitNode(Integer data, BT<Integer> left, BT<Integer> right){
		return 1 + Math.max(left.accept(this), right.accept(this));
	}
}

// to functional object Mirror implementing BTVisitor
class Mirror<X> implements BTVisitor<X, BT<X>>{
	// when visiting EmptyBT
	public BT<X> visitEmpty(){
		return new EmptyBT<X>();
	}
	// when visiting Node
	public BT<X> visitNode(X data, BT<X> left, BT<X> right){
		return new Node<X>(data, right.accept(this), left.accept(this));
	}
}


//Examples for BT
class ExampleBT{
	BT<Integer> bt1 = new Node<Integer>(1, new EmptyBT<Integer>(), new EmptyBT<Integer>());
	BT<Integer> bt2 = new Node<Integer>(2, bt1, new EmptyBT<Integer>());
	BT<Integer> bt3 = new Node<Integer>(3, bt2, bt1);
	BT<Integer> bt4 = new Node<Integer>(3, bt1, new Node<Integer>(2, new EmptyBT<Integer>(), bt1));
	BT<Integer> bt5 = new Node<Integer>(4, new EmptyBT<Integer>(), new EmptyBT<Integer>());

	boolean testBT (Tester t){
		return t.checkExpect(bt3.height(), 3) && 
				t.checkExpect(bt3.size(), 4) &&
				t.checkExpect(bt3.mirror(), bt4) &&
				t.checkExpect(bt3.ormap(new isEven()), true) &&
				t.checkExpect(bt1.ormap(new isEven()), false) &&
				t.checkExpect(bt3.andmap(new isEven()), false) &&
				t.checkExpect(bt5.andmap(new isEven()), true) &&
				t.checkExpect(bt3.accept(new Height()), 3)&&
				t.checkExpect(bt3.accept(new Mirror<Integer>()),bt4);
	}
}


//Problem 4,5:


//To represent ILife
interface Ilife<T>{
	<R> R accept(IVisitor<R,T> v);
}


//to represent Dead
class Dead<T> implements Ilife<T>{

	// accept Visitor
	public <R> R accept(IVisitor<R,T> v){
		return v.visitDead();
	}
}


//to represent Scenario
class Scenario<T> implements Ilife<T>{

	T first;
	Ilife<T> yes;
	Ilife<T> no;
	Scenario(T question, Ilife<T> yes,Ilife<T> no){
		first =question;
		this.yes=yes;
		this.no=no;
	}

	// accept Visitor
	public <R> R accept (IVisitor<R,T> v){
		return v.visitScenario(this.first ,this.yes,this.no);
	}

}


//to represent a Visitor for ILife
interface IVisitor<R,T> {
	R visitDead();
	R visitScenario(T question, Ilife<T> yes,Ilife<T> no);

}


//Determine the number I can make if I always say "no".
class numberDecisions implements IVisitor<Integer,String>{

	// when visit Dead
	public Integer visitDead(){
		return 0;
	}
	// when visit Scenario
	public Integer visitScenario (String first,Ilife<String> yes,Ilife<String> no){
		return 1+ no.accept(this);

	}
}


//Determine the number of decisions I can make if I alternate between "yes" and "no". For "yes"
class numBetweenYes implements IVisitor<Integer,String>{

	// when visit Dead
	public Integer visitDead(){
		return 0;
	}
	// when visit Scenario
	public Integer visitScenario (String first,Ilife<String> yes,Ilife<String> no){
		return 1 + yes.accept(new numBetweenNo()); 
	}
}

//Determine the number of decisions I can make if I alternate between "yes" and "no". For "no"
class numBetweenNo implements IVisitor<Integer,String>{

	// when visit Dead
	public Integer visitDead(){
		return 0;
	}
	// when visit Scenario
	public Integer visitScenario (String first,Ilife<String> yes,Ilife<String> no){
		return 1 + no.accept(new numBetweenYes()); 
	}
}

//Produces the list of questions you face if you always answer ¡°yes¡±
class returnList implements IVisitor<LoQ, String>{

	// when visit Dead
	public LoQ visitDead(){
		return new EtLoQ();
	}
	// when visit Scenario
	public LoQ visitScenario(String first,Ilife<String> yes,Ilife<String> no){
		return new ConsLoQ (first, yes.accept(this));
	}
}

//Produces the list of questions you face if I alternate between ¡°yes¡± and ¡°no¡±. For "yes"
class returnListYes implements IVisitor<LoQ, String>{

	// when visit Dead
	public LoQ visitDead(){
		return new EtLoQ();
	}
	// when visit Scenario
	public LoQ visitScenario(String first,Ilife<String> yes,Ilife<String> no){
		return new ConsLoQ (first, yes.accept(new returnListNo()));
	}
}

//Produces the list of questions you face if I alternate between ¡°yes¡± and ¡°no¡±. For "no"
class returnListNo implements IVisitor<LoQ, String>{

	// when visit Dead
	public LoQ visitDead(){
		return new EtLoQ();
	}
	// when visit Scenario
	public LoQ visitScenario(String first,Ilife<String> yes,Ilife<String> no){
		return new ConsLoQ (first, no.accept(new returnListYes()));
	}
}


//Structure for list of Questions
//to represent a list of Questions
interface LoQ{
}


//to represent an empty list of Questions
class EtLoQ implements LoQ{
}




//to represent an cons list of Questions 
class ConsLoQ implements LoQ{
	String first;
	LoQ rest;
	ConsLoQ (String first, LoQ rest){
		this.first=first;
		this.rest=rest;
	}
}

//Examples for Ilife
class ExampleIlife{
	Ilife<String> ilife1 = new Dead<String>();
	Ilife<String> ilife2 = new Scenario<String>("You¡¯re friend has a Pizza and offers you some. Do you eat some?", ilife1, ilife1);
	Ilife<String> ilife3 = new Scenario<String>("You arrive at your friend¡¯s house. Do you go in?", ilife1, ilife2);
	Ilife<String> ilife4 = new Scenario<String>("You¡¯re texting while driving. Do you stop?", ilife3, ilife2);

	LoQ loq1 = new ConsLoQ("You¡¯re texting while driving. Do you stop?", 
			new ConsLoQ("You arrive at your friend¡¯s house. Do you go in?",  
					new EtLoQ()));
	LoQ loq2 = new ConsLoQ("You¡¯re texting while driving. Do you stop?", 
			new ConsLoQ("You arrive at your friend¡¯s house. Do you go in?", 
					new ConsLoQ("You¡¯re friend has a Pizza and offers you some. Do you eat some?", 
							new EtLoQ())));

	boolean testIlife (Tester t){
		return t.checkExpect(ilife4.accept(new numberDecisions()), 2) &&
				t.checkExpect(ilife4.accept(new numBetweenYes()), 3) &&
				t.checkExpect(ilife4.accept(new returnList()), loq1 ) &&
				t.checkExpect(ilife4.accept(new returnListYes()), loq2 );
	}
}















































