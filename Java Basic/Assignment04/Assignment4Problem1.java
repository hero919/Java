import tester.Tester;

//Assignment 4
//pair 006
//partner1 Li Haolin 
//partner1-jl412 
//partner2-Zhang Zeqing 
//partner2-hero919 


//GRADER NOTES:
//Great work!

//Problem 1(20.1):

class Date{
	int year;
	int month;
	int day;
	Date(int year,int month,int day){
		this.year=year;
		if (this.validMonth(month))
			this.month=month;
		else 
			throw new IllegalArgumentException("Invalid month: " + month);
        if (this.validDay(day))
        	this.day=day;
        else 
			throw new IllegalArgumentException("Invalid day: " + day);
	}

    //Define valid month.
	boolean validMonth(int givenMonth){
		return givenMonth >= 1 &&
				givenMonth <= 12;
	}
   //Define valid day.
	boolean validDay (int givenDay){
		if (this.month ==1||this.month==3||this.month==5||this.month==7||this.month==8||this.month==10||this.month==12) {
			return givenDay>=1 && givenDay<=31;
		}
		else if (this.month ==2&& this.year%4 == 0 ) {
			return givenDay >=1 && givenDay <= 29;
		}
		else if (this.month ==2){
			return givenDay >= 1 && givenDay <= 28;
		}
		else return givenDay >= 1 && givenDay <= 30;
		}				
	}

class Example{
	Date d1 = new Date (2013,6,16);
	Date d2 = new Date (2015,3,31);
	
	boolean testDate(Tester t) {
		return t.checkConstructorException(new IllegalArgumentException("Invalid day: 30"), "Date", 2014, 2, 30)&&
				 t.checkConstructorException(new IllegalArgumentException("Invalid day: 30"), "Date", 2014, 4, 31);
	}	 
}

	

