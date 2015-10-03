import tester.Tester;



interface XMLFrag {
	int contentLength();
	int maxNest();
	boolean hasTag(String givenTag);
	boolean hasAttribute(String givenAttribute);
	boolean hasAttributeInTag(String givenTag, String givenAttribute);
	void mute();
	void muteYell();
	String renderAsString();
	void updateAttribute(String givenAttribute, String givenValue);
	void renameTag(String givenTag1, String givenTag2);

}

class Plaintext implements XMLFrag{
	String content;
	Plaintext(String content){
		this.content = content;
	}
	//computes the length of the content in an XML
	public int contentLength(){
		return 1;
	}
	//computes the maximum number of tag nesting in an XML
	public int maxNest(){
		return 0;
	}
	//determines if an XML contains a given tag
	public boolean hasTag(String givenTag){
		return false;
	}
	//determines if an XML contains a given attribute
	public boolean hasAttribute(String givenAttribute){
		return false;
	}
	//determines if an XML contains a given attribute within a given tag
	public boolean hasAttributeInTag(String givenTag, String givenAttribute){
		return false;
	}
	//replaces the value of any volume attribute with a value of 0db
	public void mute(){
	}
	//replaces the value of any volume attribute within a yell tag with a value of 0db
	public void muteYell(){
	}
	//converts XML to plain strings
	public String renderAsString(){
		return this.content;
	}
	//updates all occurrence of the give attribute to the given value
	public void updateAttribute(String givenAttribute, String givenValue){
	}
	//changes all occurrences of givenTag1 to givenTag2
	public void renameTag(String givenTag1, String givenTag2){
	}

}

class Tagged implements XMLFrag {
	String tag;
	Atts attribute;
	XML content;

	Tagged (String tag, Atts attribute, XML content){
		this.tag = tag;
		this.attribute = attribute;
		this.content = content;
	}
	//computes the length of the content in an XML
	public int contentLength(){
		return this.content.contentLength();
	}
	//computes the maximum number of tag nesting in an XML
	public int maxNest(){
		return 1 + this.content.maxNest();
	}
	//determines if an XML contains a given tag
	public boolean hasTag(String givenTag){
		return this.tag.equals(givenTag) | this.content.hasTag(givenTag);
	}
	//determines if an XML contains a given attribute
	public boolean hasAttribute(String givenAttribute){
		return this.attribute.hasAttribute(givenAttribute) | this.content.hasAttribute(givenAttribute);
	}
	//determines if an XML contains a given attribute within a given tag
	public boolean hasAttributeInTag(String givenTag, String givenAttribute){
		return (this.tag.equals(givenTag) &&
				this.attribute.hasAttribute(givenAttribute)) | 
				this.content.hasAttributeInTag(givenTag, givenAttribute);
	}
	//replaces the value of any volume attribute with a value of 0db
	public void mute(){
		this.attribute.mute();
		this.content.mute();
	}
	//replaces the value of any volume attribute within a yell tag with a value of 0db
	public void muteYell(){
		if (this.tag.equals("yell")){
			this.attribute.mute();
		}
		this.content.muteYell();
	}
	//converts XML to plain strings
	public String renderAsString(){
		return this.content.renderAsString();
	}
	//updates all occurrence of the give attribute to the given value
	public void updateAttribute(String givenAttribute, String givenValue){
		this.attribute.updateAttribute(givenAttribute, givenValue);
		this.content.updateAttribute(givenAttribute, givenValue);
	}
	//changes all occurrences of givenTag1 to givenTag2
	public void renameTag(String givenTag1, String givenTag2){
		if (this.tag.equals(givenTag1)){
			this.tag = givenTag2;
		}
		this.content.renameTag(givenTag1, givenTag2);
	}
}



interface XML {
	int contentLength();
	int maxNest();
	boolean hasTag(String givenTag);
	boolean hasAttribute(String givenAttribute);
	boolean hasAttributeInTag(String givenTag, String givenAttribute);
	XML mute();
	XML muteYell();
	String renderAsString();
	XML updateAttribute(String givenAttribute, String givenValue);
	XML renameTag(String givenTag1, String givenTag2);
}

class EmptyXML implements XML{

	//computes the length of the content in an XML
	public int contentLength(){
		return 0;
	}
	//computes the maximum number of tag nesting in a XML
	public int maxNest(){
		return 0;
	}
	//determines if an XML contains a given tag
	public boolean hasTag(String givenTag){
		return false;
	}
	//determines if an XML contains a given attribute
	public boolean hasAttribute(String givenAttribute){
		return false;
	}
	//determines if an XML contains a given attribute within a given tag
	public boolean hasAttributeInTag(String givenTag, String givenAttribute){
		return false;
	}
	//replaces the value of any volume attribute with a value of 0db
	public XML mute(){
		return this;
	}
	//replaces the value of any volume attribute within a yell tag with a value of 0db
	public XML muteYell(){
		return this;
	}
	//converts XML to plain strings
	public String renderAsString(){
		return "";
	}
	//updates all occurrence of the give attribute to the given value
	public XML updateAttribute(String givenAttribute, String givenValue){
		return this;
	}
	//changes all occurrences of givenTag1 to givenTag2
	public XML renameTag(String givenTag1, String givenTag2){
		return this;
	}
}

class LoXML implements XML{
	XMLFrag content;
	XML recursive;

	LoXML (XMLFrag content, XML recursive){
		this.content = content;
		this.recursive = recursive;
	}

	//computes the length of the content in an XML
	public int contentLength(){
		return this.content.contentLength() + this.recursive.contentLength();
	}
	//computes the maximum number of tag nesting in an XML
	public int maxNest(){
		return this.content.maxNest() + this.recursive.maxNest();
	}
	//determines if an XML contains a given tag
	public boolean hasTag(String givenTag){
		return this.content.hasTag(givenTag) | this.recursive.hasTag(givenTag);
	}
	//determines if an XML contains a given attribute
	public boolean hasAttribute(String givenAttribute){
		return this.content.hasAttribute(givenAttribute) | this.recursive.hasAttribute(givenAttribute);
	}
	//determines if an XML contains a given attribute within a given tag
	public boolean hasAttributeInTag(String givenTag, String givenAttribute){
		return this.content.hasAttributeInTag(givenTag, givenAttribute) | this.recursive.hasAttributeInTag(givenTag, givenAttribute);
	}
	//replaces the value of any volume attribute with a value of 0db
	public XML mute(){
		this.content.mute();
		this.recursive.mute();
		return this;
	}
	//replaces the value of any volume attribute within a yell tag with a value of 0db
	public XML muteYell(){
		this.content.muteYell();
		this.recursive.muteYell();
		return this;
	}
	//converts XML to plain strings
	public String renderAsString(){
		return this.content.renderAsString().concat(this.recursive.renderAsString());
	}
	//updates all occurrence of the give attribute to the given value
	public XML updateAttribute(String givenAttribute, String givenValue){
		this.content.updateAttribute(givenAttribute, givenValue);
		this.recursive.updateAttribute(givenAttribute, givenValue);
		return this;
	}
	//changes all occurrences of givenTag1 to givenTag2
	public XML renameTag(String givenTag1, String givenTag2){
		this.content.renameTag(givenTag1, givenTag2);
		this.recursive.renameTag(givenTag1, givenTag2);
		return this;
	}
}



interface Atts {
	boolean hasAttribute(String givenAttribute);
	void mute();
	void updateAttribute(String givenAttribute, String givenValue);
}

class EmptyLoA  implements Atts{
	//determines if an XML contains a given attribute
	public boolean hasAttribute(String givenAttribute){
		return false;
	}
	//replaces the value of any volume attribute with a value of 0db
	public void mute(){
	}
	//updates all occurrence of the give attribute to the given value
	public void updateAttribute(String givenAttribute, String givenValue){	
	}
}

class LoA implements Atts{
	Att content;
	Atts recursive;

	LoA(Att content, Atts recursive){
		this.content = content;
		this.recursive = recursive;
	}

	//determines if an XML contains a given attribute
	public boolean hasAttribute(String givenAttribute){
		return this.content.name.equals(givenAttribute) | this.recursive.hasAttribute(givenAttribute);
	}
	//replaces the value of any volume attribute with a value of 0db
	public void mute(){
		if (this.content.name.equals("volume")){
			this.content.value = "0db";
		}
		else{
			this.recursive.mute();
		}
	}
	//updates all occurrence of the give attribute to the given value
	public void updateAttribute(String givenName, String givenValue){
		if (this.content.name.equals(givenName)){
			this.content.value = givenValue;
		} 
		else{
			this.recursive.updateAttribute(givenName, givenValue);
		}
	}
}

class Att{
	String name;
	String value;

	Att(String name, String value){
		this.name = name;
		this.value = value;
	}
}

class ExampleXML {
	//I am <yell><italic>X</italic>ML</yell>!
	XML xml1 = new LoXML(new Plaintext("I am"), 
			new LoXML(new Tagged("yell", 
					new EmptyLoA(),
					new LoXML(new Tagged("italic",
							new EmptyLoA(), 
							new LoXML(new Plaintext("X"), new EmptyXML())),
							(new LoXML(new Plaintext("ML"),new EmptyXML())))), 
							(new LoXML(new Plaintext("!"), new EmptyXML()))));

	// I am <yell volume="30db" duration="5sec"><italic>X</italic>ML</yell>!
	XML xml2 = new LoXML(new Plaintext("I am "), 
			new LoXML(new Tagged("yell", 
					new LoA(new Att("volume","30db"), new LoA(new Att("duration","5sec"), new EmptyLoA())),
					new LoXML(new Tagged("italic",
							new EmptyLoA(), 
							new LoXML(new Plaintext("X"), new EmptyXML())),
							(new LoXML(new Plaintext("ML"),new EmptyXML())))), 
							(new LoXML(new Plaintext("!"), new EmptyXML()))));
	// I am <yell volume="30db" duration="5sec"><italic volume="30db" duration="5sec">X</italic>ML</yell>!
	XML xml2a = new LoXML(new Plaintext("I am "), 
			new LoXML(new Tagged("yell", 
					new LoA(new Att("volume","30db"), new LoA(new Att("duration","5sec"), new EmptyLoA())),
					new LoXML(new Tagged("italic",
							new LoA(new Att("volume","30db"), new LoA(new Att("duration","5sec"), new EmptyLoA())), 
							new LoXML(new Plaintext("X"), new EmptyXML())),
							(new LoXML(new Plaintext("ML"),new EmptyXML())))), 
							(new LoXML(new Plaintext("!"), new EmptyXML()))));


	boolean testXML (Tester t){
		return t.checkExpect(xml1.contentLength(), 4) &&
				t.checkExpect(xml1.maxNest(), 2) &&
				t.checkExpect(xml2.hasTag("italic"), true) &&
				t.checkExpect(xml2.hasTag("bold"), false) &&
				t.checkExpect(xml2.hasAttribute("volume"), true) &&
				t.checkExpect(xml1.hasAttribute("volume"), false) &&
				t.checkExpect(xml2.hasAttributeInTag("yell","duration"),true) &&
				t.checkExpect(xml2.hasAttributeInTag("italic","duration"), false) &&
				t.checkExpect(xml2.mute(), new LoXML(new Plaintext("I am "), 
						new LoXML(new Tagged("yell", 
								new LoA(new Att("volume","0db"), new LoA(new Att("duration","5sec"), new EmptyLoA())),
								new LoXML(new Tagged("italic",
										new EmptyLoA(), 
										new LoXML(new Plaintext("X"), new EmptyXML())),
										(new LoXML(new Plaintext("ML"),new EmptyXML())))), 
										(new LoXML(new Plaintext("!"), new EmptyXML())))))
										&&
										t.checkExpect(xml2.muteYell(), new LoXML(new Plaintext("I am "), 
												new LoXML(new Tagged("yell", 
														new LoA(new Att("volume","0db"), new LoA(new Att("duration","5sec"), new EmptyLoA())),
														new LoXML(new Tagged("italic",
																new EmptyLoA(), 
																new LoXML(new Plaintext("X"), new EmptyXML())),
																(new LoXML(new Plaintext("ML"),new EmptyXML())))), 
																(new LoXML(new Plaintext("!"), new EmptyXML())))))
																&&
																t.checkExpect(xml2a.muteYell(), new LoXML(new Plaintext("I am "), 
																		new LoXML(new Tagged("yell", 
																				new LoA(new Att("volume","0db"), new LoA(new Att("duration","5sec"), new EmptyLoA())),
																				new LoXML(new Tagged("italic",
																						new LoA(new Att("volume","30db"), new LoA(new Att("duration","5sec"), new EmptyLoA())), 
																						new LoXML(new Plaintext("X"), new EmptyXML())),
																						(new LoXML(new Plaintext("ML"),new EmptyXML())))), 
																						(new LoXML(new Plaintext("!"), new EmptyXML())))))
																						&&
																						t.checkExpect(xml2.renderAsString(), "I am XML!")&&
																						t.checkExpect(xml2a.updateAttribute("duration","10sec"), new LoXML(new Plaintext("I am "), 
																								new LoXML(new Tagged("yell", 
																										new LoA(new Att("volume","0db"), new LoA(new Att("duration","10sec"), new EmptyLoA())),
																										new LoXML(new Tagged("italic",
																												new LoA(new Att("volume","30db"), new LoA(new Att("duration","10sec"), new EmptyLoA())), 
																												new LoXML(new Plaintext("X"), new EmptyXML())),
																												(new LoXML(new Plaintext("ML"),new EmptyXML())))), 
																												(new LoXML(new Plaintext("!"), new EmptyXML())))))
																												&&
																												t.checkExpect(xml2a.renameTag("italic","slop"), new LoXML(new Plaintext("I am "), 
																														new LoXML(new Tagged("yell", 
																																new LoA(new Att("volume","0db"), new LoA(new Att("duration","10sec"), new EmptyLoA())),
																																new LoXML(new Tagged("slop",
																																		new LoA(new Att("volume","30db"), new LoA(new Att("duration","10sec"), new EmptyLoA())), 
																																		new LoXML(new Plaintext("X"), new EmptyXML())),
																																		(new LoXML(new Plaintext("ML"),new EmptyXML())))), 
																																		(new LoXML(new Plaintext("!"), new EmptyXML())))));

	}
}








//Problem 18.2:

//Abstract the common fields then define the class.

interface IZooAnimal{}

abstract class AbsAnimal implements IZooAnimal {
	String name;
	int weight;
	AbsAnimal (String name,int weight){
		this.name =name;
		this.weight =weight;
	}
}

//Represent Lion.
class Lion extends AbsAnimal{
	int meat;
	Lion (int meat,String name,int weight){
		super (name,weight);
		this.meat=meat;
	}
}


//Represent Snake.
class Snake extends AbsAnimal {
	int length;
	Snake (int length,String name,int weight){
		super (name,weight);
		this.length=length;
	}
}


//Represent Monkey.
class Monkey extends AbsAnimal{
	String food;
	Monkey (String food,String name,int weight){
		super (name,weight);
		this.food=food;
	}
}

class Example {
	IZooAnimal a1 =new Lion (50 ,"Alice",500);
	IZooAnimal a2 =new Snake (30, "Jack",10);
	IZooAnimal a3 =new Monkey ("Banana","Terry",60);
}


//Problem 18.3:
//Abstract the common fields then define the class.


interface ITaxiVehicle {}

abstract class AbsVehicle implements ITaxiVehicle {
	int idNum;
	int passengers;
	int pricePerMile;
	AbsVehicle (int idNum,int passengers,int pricePerMile){
		this.idNum=idNum;
		this.passengers=passengers;
		this.pricePerMile=pricePerMile;

	}
}

//Represent a Cab.
class Cab extends AbsVehicle{
	Cab (int idNum,int passengers,int pricePerMile){
		super (idNum,passengers,pricePerMile);
	}
}

//Represent a Limo.
class Limo extends AbsVehicle {
	int minRental;
	Limo (int minRental,int idNum,int passengers,int pricePerMile){
		super (idNum,passengers,pricePerMile);
		this.minRental=minRental;
	}
}

	//Represent a Van.
	class Van extends AbsVehicle {
		boolean access;
		public Van(boolean access,int idNum,int passengers,int pricePerMile){
			super (idNum,passengers,pricePerMile);
			this.access=access;
		}
	}






class VehicleExample {
	ITaxiVehicle v1 =new Cab (325,5,5);
	ITaxiVehicle v2 =new Limo(100,456,20,10);
	ITaxiVehicle v3=  new Van(true,689,35,30);
}