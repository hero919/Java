//assignment 6 
// pair 033 
// partner1-Zheng Xun
// partner1-Xun
// partner2-Zhang Zeqing
// partner2-hero919 

import image.*;

import java.util.Random;

import tester.Tester;
import world.*;
class Pn extends Posn{



	Pn(int x, int y){

		super(x, y);

	}



	int dist(Pn that){

		return Math.abs(this.x - that.x) + Math.abs(this.y - that.y);

	}

}

class Bullets {



	// a random number generator

	Random rand = new Random();



	//image of the Rocket from file

	Image BulletsImage = new FromFile("src/zx-zz-bullets.png");


	int speed;

	Pn pos;



	//constructor of Billets

	Bullets(int speed, Pn pos){

		this.speed=speed;

		this.pos=pos;

	}

	//check if the bullets are gone off the scene,

	// if it is then fire new bullets

	public Bullets moveBullets() {

		if(BulletsVanish()){

			return makeBullets();

		}

		else return

				new Bullets(this.speed,

						new Pn(this.pos.x+ 1+ this.speed,this.pos.y));

	}

	// if the posn of the bullet then, it will gone off the scene

	public boolean BulletsVanish(){

		return this.pos.x>500;


	}

	// if the x position of the bullet is bigger than 500,

	// then shoot new bullet with same speed

	public Bullets makeBullets(){

		if (this.pos.x>500){

			return new Bullets(this.speed, new Pn(0,this.pos.y));

		}

		else return this;

	}

	// new bullets position and image

	Scene drawScene(Scene scn){

		return scn.placeImage(BulletsImage, this.pos);

	}

	// determine when the rocket and bullets will crash

	public boolean crashBullets(Rocket that){

		return(this.pos.dist(that.pos)<30);

	}



}

//Represent the list of bullet


interface Lob{

	Lob moveBullets();

	Scene drawScene(Scene scn);

	boolean crashed(Rocket that);

}



// represent conslist of Lob

class ConsLob implements Lob{

	Bullets first;

	Lob rest;

	ConsLob(Bullets first,Lob rest){

		this.first=first;

		this.rest=rest;

	}

	//moves the list of rocks, folds moveRock

	public Lob moveBullets(){

		return new ConsLob(this.first.moveBullets(),this.rest.moveBullets());

	}



	//drawRocks draws rocks at their pos

	public Scene drawScene (Scene scn){

		return this.first.drawScene(this.rest.drawScene(scn));

	}

	//crashed checks to see if the rock in the ConsL has crashed with the ship given

	public boolean crashed(Rocket that){

		return this.first.crashBullets(that)|| this.rest.crashed(that);

	}

}


//represent the empty class

class Etb implements Lob{

	//MtL list returns an MtL, list done evaluating

	public Lob moveBullets(){

		return new Etb();

	}



	//draws the mt list, in which i just made a square offscreen that the user can't see

	//probably not the best idea, but whatever

	public Scene drawScene(Scene s){

		return s;

	}

	//MtL can never crash because it has no Rock

	public boolean crashed(Rocket that){

		return false;

	}

}



//represent the rocket class

class Rocket {

	Pn pos;

	Rocket(Pn pos){

		this.pos=pos;

	}



	//image of the rocket

	Image RocketImage= new FromFile("src/zx-zz-spaceship.png");



	// represent the key event to control the rocket

	Rocket moveRocket(String key){


		if(key.equals("up")){

			return new Rocket(new Pn(this.pos.x,this.pos.y-10));

		}

		if(key.equals("down")){

			return new Rocket(new Pn(this.pos.x,this.pos.y+10));

		}



		if(key.equals("left")){

			return new Rocket(new Pn(this.pos.x-10,this.pos.y));

		}

		if(key.equals("right")){

			return new Rocket(new Pn(this.pos.x+10,this.pos.y));

		}

		else

			return this;

	}

	// draw the rocket

	Scene drawScene(Scene scn){

		return scn.placeImage(RocketImage, this.pos);

	}

}

class Rocketgame extends World{

	Rocket rocket;

	Lob bullets;

	Integer time;



	Rocketgame(){}

	Rocketgame(Rocket rocket, Lob bullets,int time) {

		this.rocket = rocket;

		this.bullets = bullets;

		this.time=time;

	}



	// represent the empty scene

	Image spaceImage =

			new Rectangle(700,500, "solid", "black");



	public Scene onDraw(){

		return this.bullets.drawScene(this.rocket.drawScene(new EmptyScene(700, 500))).

				placeImage(new Text(this.time.toString(),100,"green"),new Pn(600, 100));

	}



	// if the rocket crash the bullet. then the game is over

	//otherwise the game keep going

	public Rocketgame onTick(){

		return new Rocketgame(this.rocket,this.bullets.moveBullets(),this.time-1);

	}

	public double tickRate(){

		return 0.1;

	}

	public Rocketgame onKey(String key){

		return

				new Rocketgame(this.rocket.moveRocket(key),this.bullets,this.time);

	}

	public boolean stopWhen(){

		return this.bullets.crashed(this.rocket)||

				rocket.pos.y<10 ||

				this.time==0;

	}

	public Scene lastScene(){

		return new EmptyScene(700,500).placeImage(new Text("GameOver",100,"red"), 350,250);

	}

}



class ExamplesRocketgame {


	

	Bullets b1 = new Bullets(80, new Pn(1, 50));

	Bullets b2 = new Bullets(50, new Pn(1, 100));

	Bullets b3 = new Bullets(45, new Pn(1, 150));

	Bullets b4 = new Bullets(30, new Pn(1, 200));

	Bullets b5 = new Bullets(20, new Pn(1, 250));

	Bullets b6 = new Bullets(20, new Pn(1, 300));

	Bullets b7 = new Bullets(5, new Pn(1, 350));

	Bullets b8 = new Bullets(3, new Pn(1, 400));

	


	Lob Lob1 = new ConsLob(b1,new Etb());

	Lob Lob2 = new ConsLob(b2,Lob1);

	Lob Lob3 = new ConsLob(b3,Lob2);

	Lob Lob4 = new ConsLob(b4,Lob3);

	Lob Lob5 = new ConsLob(b5,Lob4);

	Lob Lob6 = new ConsLob(b6,Lob5);

	Lob Lob7 = new ConsLob(b7,Lob6);

	Lob Lob8 = new ConsLob(b8,Lob7);




	Rocket s1 = new Rocket(new Pn(200,350));


	Rocketgame game = new Rocketgame(this.s1,this.Lob8,120);

	World w1 = game.bigBang();
}



class Example2 {
	Bullets b0 = new Bullets(3, new Pn(199, 349));

	Bullets b1 = new Bullets(80, new Pn(1, 50));

	Bullets b2 = new Bullets(50, new Pn(1, 100));

	Bullets b3 = new Bullets(45, new Pn(1, 150));

	Bullets b4 = new Bullets(30, new Pn(1, 200));

	Bullets b5 = new Bullets(20, new Pn(1, 250));

	Bullets b6 = new Bullets(20, new Pn(1, 300));

	Bullets b7 = new Bullets(5, new Pn(1, 350));

	Bullets b8 = new Bullets(3, new Pn(1, 400));

	Bullets b9= new Bullets(10,new Pn(500,20));

	Bullets b10 = new Bullets (10,new Pn(600,400));

	Lob Lob1 = new ConsLob(b1,new Etb());

	Lob Lob2 = new ConsLob(b2,Lob1);

	Lob Lob3 = new ConsLob(b3,Lob2);

	Lob Lob4 = new ConsLob(b4,Lob3);

	Lob Lob5 = new ConsLob(b5,Lob4);

	Lob Lob6 = new ConsLob(b6,Lob5);

	Lob Lob7 = new ConsLob(b7,Lob6);

	Lob Lob8 = new ConsLob(b8,Lob7);




	Rocket s1 = new Rocket(new Pn(200,350));


	Rocketgame game = new Rocketgame(this.s1,this.Lob8,120);

boolean testMove(Tester t){
	return t.checkExpect(b1.BulletsVanish(),false)&&
			t.checkExpect(b10.BulletsVanish(),true)&&
			t.checkExpect(this.b1.moveBullets(),
					new Bullets(80,new Pn(82, 50))) &&				
					t.checkExpect(this.b2.moveBullets(),
							new Bullets(50, new Pn(52,100))) &&
							t.checkExpect(this.s1.moveRocket("up"), 
									new Rocket(new Pn(200 ,340)))&&
									t.checkExpect(this.s1.moveRocket("down"), 
											new Rocket(new Pn(200, 360)))&&
											t.checkExpect(this.s1.moveRocket("left"), 
													new Rocket(new Pn(190, 350))) &&
													t.checkExpect(this.s1.moveRocket("right"), 
															new Rocket(new Pn(210, 350)))&&
															t.checkExpect(b0.crashBullets(s1),true)&&
															t.checkExpect(b2.crashBullets(s1),false)&&
															t.checkExpect(b9.makeBullets(),new Bullets(10, new Pn(500,20)));
															}
}

																	










