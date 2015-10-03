import tester.Tester;

//GRADER NOTES
//Great stuff

interface XMLFrag {
	boolean isPlaintext();
	boolean isTagged();
	Plaintext toPlaintext();
	Tagged toTagged();
	boolean same(XMLFrag that);
	boolean sameUnordered(XMLFrag that);

}

class Plaintext implements XMLFrag{
	String content;
	Plaintext(String content){
		this.content = content;
	}

	// is the this Plaintext?
	public boolean isPlaintext(){
		return true;
	}
	// is the this Tagged?
	public boolean isTagged(){
		return false;
	}
	// Casting Plaintext
	public Plaintext toPlaintext(){
		return this;
	}
	// Casting Tagged
	public Tagged toTagged(){
		throw new RuntimeException("Not a Tagged");
	}
	// Is this XMLFrag same as that XMLFrag?
	public boolean same(XMLFrag that){
		if (that.isPlaintext()){
			return this.samePlaintext(that.toPlaintext());
		}else{
			return false;
		}
	}
	// Is this Plaintext same as that Plaintext?
	boolean samePlaintext(Plaintext that){
		return this.content.equals(that.content);
	}

	// Is this Plaintext same as that Plaintext disregards that order of Attribute?
	public boolean sameUnordered(XMLFrag that){
		if (that.isPlaintext()){
			return this.samePlaintext(that.toPlaintext());
		}else{
			return false;
		}
	}

}

class Tagged implements XMLFrag {
	String tag;
	Atts attribute;
	Assignment4XML content;

	Tagged (String tag, Atts attribute, Assignment4XML content){
		this.tag = tag;
		this.attribute = attribute;
		this.content = content;
	}

	// is the this Plaintext?
	public boolean isPlaintext(){
		return false;
	}
	// is the this Tagged?
	public boolean isTagged(){
		return true;
	}
	// Casting Plaintext
	public Plaintext toPlaintext(){
		throw new RuntimeException("Not a Plaintext");
	}
	// Casting Tagged
	public Tagged toTagged(){
		return this;
	}
	// Is this XMLFrag same as that XMLFrag?
	public boolean same(XMLFrag that){
		if (that.isTagged()){
			return this.sameTagged(that.toTagged());
		}else{
			return false;
		}
	}
	// Is this Tagged same as that Tagged?
	boolean sameTagged(Tagged that){
		return this.tag.equals(that.tag) &&
				this.attribute.same(that.attribute) &&
				this.content.same(that.content);
	}
	// Is this XMLFrag same as that XMLFrag disregards that order of Attribute?
	public boolean sameUnordered(XMLFrag that){
		if (that.isTagged()){
			return this.sameTaggedUnordered(that.toTagged());
		}else{
			return false;
		}
	}
	// Is this Tagged same as that Tagged disregards that order of Attribute?
	boolean sameTaggedUnordered (Tagged that){
		return this.tag.equals(that.tag) &&
				this.attribute.sameUnordered(that.attribute) &&
				this.content.sameUnordered(that.content);
	}

}



interface Assignment4XML {
	boolean isEmptyXML();
	boolean isLoXML();
	LoXML toLoXML();
	boolean same(Assignment4XML that);
	boolean sameUnordered(Assignment4XML that);

}

class EmptyXML implements Assignment4XML{

	// is this EmptyXML?
	public boolean isEmptyXML(){
		return true;
	}
	// is this LoXML?
	public boolean isLoXML(){
		return false;
	}
	// Casting LoXML
	public LoXML toLoXML(){
		throw new RuntimeException("Not a LoXML");
	}
	// is this XML same as that XML?
	public boolean same(Assignment4XML that){
		if (that.isEmptyXML()){
			return true;
		}else{
			return false;
		}
	}
	// is this XML same as that XML disregards that order of Attribute?
	public boolean sameUnordered(Assignment4XML that){
		if (that.isEmptyXML()){
			return true;
		}else{
			return false;
		}
	}
}

class LoXML implements Assignment4XML{
	XMLFrag content;
	Assignment4XML recursive;

	LoXML (XMLFrag content, Assignment4XML recursive){
		this.content = content;
		this.recursive = recursive;
	}

	// is this EmptyXML?
	public boolean isEmptyXML(){
		return false;
	}
	// is this LoXML?
	public boolean isLoXML(){
		return true;
	}
	// Casting LoXML
	public LoXML toLoXML(){
		return this;
	}
	// is this XML same as that XML?
	public boolean same(Assignment4XML that){
		if (that.isLoXML()){
			return this.sameLoXML(that.toLoXML());
		}else{
			return false;
		}
	}
	// is this LoXML same as that LoXML?
	boolean sameLoXML(LoXML that){
		return this.content.same(that.content) &&
				this.recursive.same(that.recursive);
	}
	// is this XML same as that XML disregards that order of Attribute?
	public boolean sameUnordered(Assignment4XML that){
		if (that.isLoXML()){
			return this.sameLoXMLUnordered(that.toLoXML());
		}else{
			return false;
		}
	}
	// is this LoXML same as that LoXML disregards that order of Attribute?
	boolean sameLoXMLUnordered(LoXML that){
		return this.content.sameUnordered(that.content) &&
				this.recursive.sameUnordered(that.recursive);
	}
}



interface Atts {
	boolean same(Atts that);
	boolean sameUnordered(Atts that);
	boolean sameStructure(Atts that);
	boolean isEmptyLoA();
	boolean isLoA();
	LoA toLoA();
	boolean bothHasAttribute(Atts that);
	boolean hasAttribute(Att givenAtt);

}

class EmptyLoA  implements Atts{

	// is this EmptyLoA?
	public boolean isEmptyLoA(){
		return true;
	}
	// is this LoA?
	public boolean isLoA(){
		return false;
	}
	// Casting LoA
	public LoA toLoA(){
		throw new RuntimeException("Not a LoA");
	}
	// is this Atts same as that Atts?
	public boolean same(Atts that){
		if (that.isEmptyLoA()){
			return true;
		}else{
			return false;
		}
	}
	// is this Atts same as that Atts disregards that order of Attribute?
	public boolean sameUnordered(Atts that){
		if (that.isEmptyLoA()){
			return true;
		}else{
			return false;
		}
	}
	// is this Atts same in structure as that Atts?
	public boolean sameStructure(Atts that){
		if (that.isEmptyLoA()){
			return true;
		}else{
			return false;
		}
	}
	// do that Atts has all Att in this Atts?
	public boolean bothHasAttribute(Atts that){
		return true;
	}
	// do this Atts contains the given Att?
	public boolean hasAttribute(Att givenAtt){
		return false;
	}
}

class LoA implements Atts{
	Att content;
	Atts recursive;

	LoA(Att content, Atts recursive){
		this.content = content;
		this.recursive = recursive;
	}

	// is this EmptyLoA?
	public boolean isEmptyLoA(){
		return false;
	}
	// is this LoA?
	public boolean isLoA(){
		return true;
	}
	// Casting LoA
	public LoA toLoA(){
		return this;
	}
	// is this Atts same as that Atts?
	public boolean same(Atts that){
		if (that.isLoA()){
			return this.sameLoA(that.toLoA());
		}else{
			return false;
		}
	}
	// is this LoA same as that LoA?
	boolean sameLoA(LoA that){
		return this.content.sameAttribute(that.content) &&
				this.recursive.same(that.recursive);
	}
	// is this Atts same as that Atts disregards that order of Attribute?
	public boolean sameUnordered(Atts that){
		return this.bothHasAttribute(that) &&
				this.sameStructure(that);
	}
	// is this Atts same in structure as that Atts?
	public boolean sameStructure(Atts that){
		if (that.isLoA()){
			return this.recursive.sameStructure(that.toLoA().recursive);
		}else{
			return false;
		}
	}
	// do that Atts has all Att in this Atts?
	public boolean bothHasAttribute(Atts that){
		return that.hasAttribute(this.content) && this.recursive.bothHasAttribute(that);
				
	}
	// do this Atts contains the given Att?
	public boolean hasAttribute(Att givenAtt){
		return (this.content.sameAttribute(givenAtt) || this.recursive.hasAttribute(givenAtt));
	}
}

class Att{
	String name;
	String value;

	Att(String name, String value){
		this.name = name;
		this.value = value;
	}
	// is this Att same as that Att?
	boolean sameAttribute(Att that){
		return this.name.equals(that.name) &&
				this.value.equals(that.value);
	}
}

class ExampleXML{
	//I am <yell><italic>X</italic>ML</yell>!
	Assignment4XML xml1 = new LoXML(new Plaintext("I am"), 
			new LoXML(new Tagged("yell", 
					new EmptyLoA(),
					new LoXML(new Tagged("italic",
							new EmptyLoA(), 
							new LoXML(new Plaintext("X"), new EmptyXML())),
							(new LoXML(new Plaintext("ML"),new EmptyXML())))), 
							(new LoXML(new Plaintext("!"), new EmptyXML()))));

	// I am <yell volume="30db" duration="5sec"><italic>X</italic>ML</yell>!
	Assignment4XML xml2 = new LoXML(new Plaintext("I am "), 
			new LoXML(new Tagged("yell", 
					new LoA(new Att("volume","30db"), new LoA(new Att("duration","5sec"), new EmptyLoA())),
					new LoXML(new Tagged("italic",
							new EmptyLoA(), 
							new LoXML(new Plaintext("X"), new EmptyXML())),
							(new LoXML(new Plaintext("ML"),new EmptyXML())))), 
							(new LoXML(new Plaintext("!"), new EmptyXML()))));
	// I am <yell volume="30db" duration="5sec"><italic volume="30db" duration="5sec">X</italic>ML</yell>!
	Assignment4XML xml2a = new LoXML(new Plaintext("I am "), 
			new LoXML(new Tagged("yell", 
					new LoA(new Att("volume","30db"), new LoA(new Att("duration","5sec"), new EmptyLoA())),
					new LoXML(new Tagged("italic",
							new LoA(new Att("volume","30db"), new LoA(new Att("duration","5sec"), new EmptyLoA())), 
							new LoXML(new Plaintext("X"), new EmptyXML())),
							(new LoXML(new Plaintext("ML"),new EmptyXML())))), 
							(new LoXML(new Plaintext("!"), new EmptyXML()))));
	// I am <yell duration="5sec" volume="30db"><italic volume="30db" duration="5sec">X</italic>ML</yell>!
	Assignment4XML xml2b = new LoXML(new Plaintext("I am "), 
			new LoXML(new Tagged("yell", 
					new LoA(new Att("duration","5sec"), new LoA(new Att("volume","30db"), new EmptyLoA())),
					new LoXML(new Tagged("italic",
							new LoA(new Att("duration","5sec"), new LoA(new Att("volume","30db"), new EmptyLoA())), 
							new LoXML(new Plaintext("X"), new EmptyXML())),
							(new LoXML(new Plaintext("ML"),new EmptyXML())))), 
							(new LoXML(new Plaintext("!"), new EmptyXML()))));
	// I am <yell duration="5sec" volume="30db"><italic>X</italic>ML</yell>!
	Assignment4XML xml2c = new LoXML(new Plaintext("I am "), 
			new LoXML(new Tagged("yell", 
					new LoA(new Att("duration","5sec"), new LoA(new Att("volume","30db"), new EmptyLoA())),
					new LoXML(new Tagged("italic",
							new EmptyLoA(), 
							new LoXML(new Plaintext("X"), new EmptyXML())),
							(new LoXML(new Plaintext("ML"),new EmptyXML())))), 
							(new LoXML(new Plaintext("!"), new EmptyXML()))));


	boolean testXML (Tester t){
		return t.checkExpect(xml2a.same(xml2a), true) &&
				t.checkExpect(xml2a.same(xml1), false) &&
				t.checkExpect(xml2.sameUnordered(xml2c), true) &&
				t.checkExpect(xml2a.sameUnordered(xml2b), true) &&
				t.checkExpect(xml2a.sameUnordered(xml1), false) ;
			
		
	}

}
