/** NetId: pjc272 Time spent: 3 hours, 30 minutes. 
 An instance maintains info about the PhD of a person. */
//Javadoc output looked fine to me
public class PhD {
	private String name; //Name of the person w/PhD; must have length >1
	private int month; //Month PhD was received; In 1..12, with 1=January, etc.
	private int  year; //Year PhD was received; no preconditon
	private PhD advisor1; //The person's first advisor;type null if not known
	private PhD advisor2; //The person's second advisor;type null if not known
				  		  // or number of advisors <2
	private int advisees; //The number of advisees; set by the program,
					       //increases when the PhD becomes someone's advisor

	/**Constructor: an instance for a person with name n, PhD year y, and PhD 
	month m. The advisors are unknown, and there are 0 advisees.
	Precondition: n has at least 2 chars, and m is in 1..12.*/
	public PhD(String n, int y,int m){
		assert n!=null&&n.length()>=2 && 12>=m && m>=1; //asserts preconditions
		name=n; year=y; month=m;// sets the variables
		}
	
	/**Constructor: a PhD with name n, PhD year y, PhD month m, first advisor
	adv1, and no second advisor.
	Precondition: n has at least 2 chars, m is in 1..12, and adv1 is not null*/
	
	public PhD(String n, int y, int m, PhD adv1){
		this(n,y,m); //calls simpler constructor
		assert adv1!=null;//asserts the precondition; also asserted in setter 
		this.addAdvisor1(adv1);//adds advisor 1 and increments their numAdvisees		
	}
	
	/**Constructor: a PhD with name n, PhD year y, PhD month m, first advisor
	adv1, and second advisor adv2.
	Precondition: n has at least 2 chars, m is in 1..12,
	adv1 and adv2 are not null, and adv1 and adv2 are different.*/
	public PhD(String n, int y, int m, PhD adv1, PhD adv2){
		this(n,y,m,adv1); // calls constructor for 1 advisor
		assert adv2!=adv1 && adv2!=null; //assert preconditions; also in setter
		this.addAdvisor2(adv2);	//set second advisor;increment their numAdvisees
	}
	
	/**Return the name of this person.*/
	public String name(){
		return name;
	}

	/**Return the year this person got their PhD.*/
	public int year(){
		return year;
	}

	/**Return the month this person got their PhD.*/
	public int month(){
		return month;
	}
	
	/**Return the first advisor of this PhD (null if unknown).*/
	public PhD advisor1(){
		return advisor1;
	}

	/**Return the second advisor of this PhD (null if unknown or nonexistent).*/
	public PhD advisor2() {
		return advisor2;
	}

	/**Return the number of PhD advisees of this person.*/
	public int numAdvisees(){
		return advisees;
	}

	/**Add p as the first advisor of this person.
	Precondition: the first advisor is unknown and p is not null.*/
	public void addAdvisor1(PhD p) {
		assert p!=null && advisor1==null;//assert preconditions
		advisor1=p;//set advisor 1
		p.advisees+=1;// increment the new advisor's number of advisees
	}

	/**Add p as the second advisor of this PhD.
	Precondition: The first advisor is known, the second advisor is unknown, p is
	not null, and p is different from the first advisor.*/
	public void addAdvisor2(PhD p) {
		//assert the preconditions
		assert p!=null && advisor1!=null && advisor1!=p && advisor2==null;
		advisor2=p;//set advisor 2
		p.advisees+=1;//increment the new advisor's number of advisees
	}
	
	/**Return value of: p is not null and this PhD got the PhD
	before p.*/
	public boolean gotBefore(PhD p) {
		return p!=null &&(this.year()<p.year()||(this.year()==p.year()&&
				this.month()<p.month())); //returns true if p is not null and 
		//either the year the current PhD received theirs is less than p or 
		//the years are the same and the month is less
	}
	
	/**Return value of: this PhD is an intellectual sibling of p.
	Precondition: p is not null.*/
	public boolean isSiblingOf(PhD p) {
		assert p!=null; //assert precondition
		return this!=p && (((advisor1==p.advisor1() ||advisor1==p.advisor2()) 
				&& advisor1 != null) || ((advisor2 == p.advisor2() ||
				advisor2==p.advisor1())	&& advisor2!=null)); 
		//makes sure the two PhD's are not the same person and checks to see if 
		//any of the four advisors are the same and non-null
	}
}
