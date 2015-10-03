import tester.Tester;
//assignment 2 
//pair 006
//partner1 Li Haolin 
//partner1-jl412 
//partner2-Zhang Zeqing 
//partner2-hero919 


//Problem1:
interface Ivehicle {
	int CountLength ();
}


//Represent a car.
class Car implements Ivehicle{
	int size;
	int consumption;
	boolean trailer;
	Car (int size, int consumption,boolean trailer) {
		this.size= size;
		this.consumption= consumption;
		this.trailer = trailer;
	}


	//Count the maximum distance a vehicle can travel on one tank of fuel.
	public int CountLength () {
		return this.size* this.consumption;
	}
}

//Represent a bus.
class Bus implements Ivehicle{
	int size;
	int consumption;
	int passengers;
	Bus(int size, int consumption, int passengers){
		this.size = size ;
		this.consumption = consumption;
		this.passengers = passengers;
	}

	//Count the maximum distance a vehicle can travel on one tank of fuel.
	public int CountLength () {
		return this.size* this.consumption;
	}
}

//Represent a Truck.
class Truck implements Ivehicle{
	int size;
	int consumption;
	int load;
	Truck(int size, int consumption, int load){
		this.size = size;
		this.consumption = consumption;
		this.load = load;				
	}

	//Count the maximum distance a vehicle can travel on one tank of fuel.
	public int CountLength () {
		return this.size* this.consumption;
	}
}

// Example of vehicles and the tests.
class Example1 {
	Example1(){}
	Ivehicle car1 = new Car (40,2,true);
	Ivehicle car2 = new Car (50,3,false);
	Ivehicle bus1 = new Bus (80,1,25);
	Ivehicle bus2 = new Bus (100,1,50);
	Ivehicle truck1 = new Truck (120,1,5000);
	Ivehicle truck2 = new Truck (200,1,10000);

	boolean testCountLength (Tester t) {
		return t.checkExpect (car1.CountLength(),80)&&
				t.checkExpect (car2.CountLength(),150)&&
				t.checkExpect (bus1.CountLength(),80)&&
				t.checkExpect (bus2.CountLength(),100)&&
				t.checkExpect (truck1.CountLength(),120)&&
				t.checkExpect (truck2.CountLength(),200);				
	}
}

//Problem2:

// Represent a list of number
interface Ilist {
	//Calculate the sum of the numbers in the list.
	int sum ();
	//Calculate the sum of the square of the numbers in the list.
	int sumsqrs ();
}

// Represent empty
class Empty implements Ilist{
	public Empty(){
	}

	//Calculate the sum of the numbers in the list.
	public int sum (){
		return 0;
	}
	//Calculate the sum of the square of the numbers in the list.
	public int sumsqrs (){
		return 0;
	}
}

// Represent recursive of a list
class list implements Ilist {
	int Number;
	Ilist rest;
	list(int Number,Ilist rest ){
		this.Number = Number;
		this.rest = rest;
	}
	//Calculate the sum of the numbers in the list.
	public int sum (){
		return this.Number + rest.sum();

	}
	//Calculate the sum of the square of the numbers in the list.
	public int sumsqrs (){
		return this.Number*this.Number + rest.sumsqrs();
	}
}

// Examples of lists and the test of methods.
class Example {
	Ilist list1 = new list (1, new list (2, new Empty()) );
	Ilist list2 = new list (3 ,new list (5,new Empty()));
	boolean testsum (Tester t) {
		return t.checkExpect (list1.sum(),3)&&
				t.checkExpect (list2.sum(),8)&&
				t.checkExpect (list1.sumsqrs(),5)&&
				t.checkExpect (list2.sumsqrs(),34);
	}
}


//Problem 3 (3.1),6(10.2):

// Represent a house
class House {
	String kind;
	int rooms;
	Address addr;
	int price;
	House (String kind,int rooms, Address addr,int price){
		this.kind=kind;
		this.rooms= rooms;
		this.addr= addr;
		this.price= price; 
	}


	//Check whether one house has more rooms than
	//some other house.
	boolean isBigger (House a) {
		return this.rooms > a.rooms;		
	}

	//Check whether the advertised house is in some given city.
	boolean thisCity (String city) {
		return this.addr.cityname.equals(city);
	}
	//Check whether the one house is in the same city as
	//some other house.
	boolean sameCity (House h){
		return this.addr.cityname.equals(h.addr.cityname);
	}
}

// Represent address
class Address {
	int streetnumber;
	String streetname;
	String cityname;
	Address (int streetnumber,String streetname,String cityname) {
		this.streetnumber = streetnumber;
		this.streetname = streetname;
		this.cityname = cityname;
	}
}

//Test for House
class HouseExample {
	Address a1 = new Address(23,"Maple Street", "Brookline");
	Address a2 = new Address(5,"Joye Road","Newton");
	Address a3 = new Address (83,"Winslow Road","Waltham");
	House h1 = new House ("Ranch",7,a1,37500);
	House h2 = new House ("Colonial",9,a2, 450000);
	House h3 = new House ("Cape",6,a3,23500);

	boolean testsum (Tester t) {
		return t.checkExpect (h1.isBigger(h2),false)&&
				t.checkExpect (h2.isBigger(h1),true)&&
				t.checkExpect (h2.thisCity("Newton"),true)&&
				t.checkExpect (h3.thisCity("Newton"),false)&&
				t.checkExpect (h2.sameCity(h1),false);
	}
}






//Problem 5(4.4):

// Represent bank account
interface BankAccount {}

// Represent checking account
class Checking implements BankAccount{
	int balance;
	int minbalance;
	String name;
	int id;
	Checking (int balance, int minbalance,String name,int id) {
		this.balance = balance;
		this.minbalance = balance;
		this.name = name;
		this.id = id;
	}
}

// Represent saving account
class Saving implements BankAccount {
	int balance;
	double interest;
	String name;
	int id;
	Saving (int balance,double interest, String name,int id) {
		this.balance = balance;
		this.interest = interest;
		this.name = name;
		this.id = id;

	}
}

// Represent certificate of deposit
class CD implements BankAccount {
	int balance;
	int interest;
	String date;
	String name;
	int id;
	CD (int balance,int interest, String date, String name,int id){
		this.balance = balance;
		this.interest = interest;
		this.date = date;
		this.name = name;
		this.id = id;
	}
}

// examples of bank account
class ExampleBank {
	BankAccount acc1 = new Checking (1250, 500,"Earl Gray",1729);
	BankAccount acc2 = new CD (10123,4,"June 1, 2005","Ima Flatt",4104);
	BankAccount acc3 = new Saving (800,3.5,"Annie Proulx",2992); 
}


//Problem 5(5.3):

// represent a list of house
interface Iloh {}

// represent empty
class Emptyhouse implements Iloh{
	Emptyhouse(){}
}

// represent recursive
class loh implements Iloh{
	House house;
	Iloh rest;
	loh (House house,Iloh rest){
		this.house = house;
		this.rest = rest;
	}
}



//Examples of lists of house.
class Example2 {
	Address a1 = new Address(23,"Maple Street", "Brookline");
	Address a2 = new Address(5,"Joye Road","Newton");
	Address a3 = new Address (83,"Winslow Road","Waltham");
	House h1 = new House ("Ranch",7,a1,37500);
	House h2 = new House ("Colonial",9,a2, 450000);
	House h3 = new House ("Cape",6,a3,23500);


	Iloh loh0 = new Emptyhouse();

	Iloh loh1 = new loh(h1,new Emptyhouse());

	Iloh loh2 = new loh(h1,new loh(h2,new Emptyhouse()));

	Iloh loh3 = new loh (h1,new loh(h2,loh2));
}