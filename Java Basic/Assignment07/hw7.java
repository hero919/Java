//assignment 7 

// pair 033 

// partner1-Zheng Xun

// partner1-Xun

// partner2-Zhang Zeqing

// partner2-hero919 


//GRADER COMMENTS
//Don't need the list1 in palindrome2
//Split out your test methods so that every method gets it's own test method
//Otherwise perfect

import java.util.ArrayList;

import tester.Tester;




//Create a new interface of IComparator.

interface IComparator<T>{

	boolean equals (ArrayList<T> list1,ArrayList<T> list2);

}




//Create a class including method equals implements IComparator.

class Same<T> implements IComparator<T>{

	public boolean equals (ArrayList<T> list1,ArrayList<T> list2){

		if (list1.size()==list2.size()){

			

		

		for (int i=0;i<list1.size();i=i+1){

			if (!list1.get(i).equals(list2.get(i))){

				return false;

			}

		}

		return true;

		}

		else return false;

		

}

}







//Create a new class including the required method.




class ArrayUtils<T>{

	

//check whether two lists have the same contents in the same order. 

	boolean isSame(ArrayList<T> list1, ArrayList<T> list2, IComparator<T> comp){

		return comp.equals(list1, list2);

			

		}

	

//Use a for loop to reverse a given list.

	public ArrayList<T> reverse (ArrayList<T> list){

		ArrayList<T> list2 = new ArrayList<T>();

		for (int i=list.size()-1 ;i>=0;i=i-1){

			list2.add(list.get(i));

		}

		return list2;

	}

//Use a for-each loop to reverse a given list.

	public ArrayList<T> reverse2 (ArrayList<T> list){

		ArrayList<T> list2 = new ArrayList<T> ();

		

		for (T element:list){

			list2.add(0, element);

		}

		return list2;

	}

//Check whether the given list is a palindrome, using a for loop method.

	public boolean palindrome(ArrayList<T> list){

		ArrayUtils<T> list1=new ArrayUtils<T>();

		ArrayList<T> list2 = list1.reverse(list);

	

		for (int i=0;i<list.size();i=i+1){

			if (!list.get(i).equals(list2.get(i))){

				return false;

			}

		}

		return true;

		}

	

//Check whether the given list is a palindrome, using a for-each loop method.

	public boolean palindrome2(ArrayList<T> list){

		ArrayUtils<T> list1=new ArrayUtils<T>();

		ArrayList<T> list2 = list1.reverse(list);

		

		int i = 0;

		for (T element:list){

			if (!(element==list2.get(i))){

				return false;

			}

			i=i+1;

		}

		return true;

	}

		

}




//Create a new exmaple class including examples of the classes and all the 

//check-expect.

class Example {

	ArrayUtils<Integer> a0;

	ArrayUtils<String> b0;

	ArrayList<Integer> a1;

	ArrayList<Integer> a2;

	ArrayList<Integer> a3;

	ArrayList<Integer> a4;

	ArrayList<Integer> a5;

	ArrayList<String> b1;

	ArrayList<String> b2;

	Same<Integer> s1;

	Same<String> s2;

	

     void check(){

	a0= new ArrayUtils<Integer>();

	b0=new ArrayUtils<String>();

	a1= new ArrayList<Integer>();

	a2= new ArrayList<Integer>();

	a3= new ArrayList<Integer>();

	a4= new ArrayList<Integer>();

	a5= new ArrayList<Integer>();

	b1= new ArrayList<String>();

	b2= new ArrayList<String>();

	s1 = new Same<Integer>();

	s2 = new Same<String>();

	

	

	a1.add(1);

	a1.add(2);

	a1.add(3);

	a1.add(4);

	a1.add(5);

	a2.add(5);

	a2.add(6);

	a2.add(7);

	a2.add(8);

	a2.add(9);

	a3.add(5);

	a3.add(4);

	a3.add(3);

	a3.add(2);

	a3.add(1);

	a4.add(1);

	a4.add(2);

	a4.add(3);

	a4.add(2);

	a4.add(1);

	a5.add(12);

	a5.add(17);

	b2.add("a");

	b2.add("b");

	b2.add("c");

	b2.add("b");

	b2.add("a");

     }

	

     

     

	void testmethod(Tester t){

		check();

		t.checkExpect(a0.isSame(a1,a1,s1),true);

    	t.checkExpect(a0.isSame(a1,a2,s1),false);

    	t.checkExpect(a0.isSame(a1, a5, s1),false);

    	t.checkExpect(b0.isSame(b2, b2, s2),true);

    	t.checkExpect(b0.isSame(b1, b1, s2),true);

    	t.checkExpect(b0.isSame(b1, b2, s2),false);

    	check();

    	t.checkExpect(a0.reverse(a1),a3);

    	t.checkExpect(a0.reverse2(a3),a1);

    	t.checkExpect(b0.reverse(b1),b1);

    	t.checkExpect(b0.reverse2(b2),b2);

    	check();

    	t.checkExpect(a0.palindrome(a3),false);

    	t.checkExpect(a0.palindrome(a4),true);

    	t.checkExpect(a0.palindrome(a5),false);

    	t.checkExpect(b0.palindrome(b2),true);

    	t.checkExpect(b0.palindrome(b1),true);

    	check();

    	t.checkExpect(a0.palindrome2(a2),false);

    	t.checkExpect(a0.palindrome2(a4),true);

    	t.checkExpect(a0.palindrome2(a5),false);

    	t.checkExpect(b0.palindrome2(b1),true);

    			

    }

	

}




