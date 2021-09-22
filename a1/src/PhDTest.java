import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class PhDTest {

	@Test
	void testConstructor1() {
		PhD a=new PhD("John Smith", 1997, 3);
		assertEquals("John Smith",a.name());//name is set properly
		assertEquals(1997,a.year());//year is set properly
		assertEquals(3,a.month());//month is set properly
		assertEquals(null,a.advisor1());//advisor 1 is not set
		assertEquals(null,a.advisor2());//advisor 2 is not set
		assertEquals(0,a.numAdvisees());//has no advisees
		//Name is not null
		assertThrows(AssertionError.class,()->{new PhD(null,1,2);});
		//length of name>=2
		assertThrows(AssertionError.class,()->{new PhD("A",1,2);});
		//month is not <1
		assertThrows(AssertionError.class,()->{new PhD("ABBA",1,-2);});
		assertThrows(AssertionError.class,()->{new PhD("ABBA",1,0);});
		assertThrows(AssertionError.class,()->{new PhD("ABBA",1,
				Integer.MIN_VALUE);});
		//month is not >12
		assertThrows(AssertionError.class,()->{new PhD("ABBA",2,15);});
		assertThrows(AssertionError.class,()->{new PhD("ABBA",2,
				Integer.MAX_VALUE);});
		
	}
	@Test
	void testSetters() {
		PhD a=new PhD("John Smith", 1997, 3);
		PhD b= new PhD("Jane", 1950, 2);
		PhD c=new PhD("Adam", 1970, 11);
		PhD d=new PhD("Redundant", 1970, 11);
		//test advisor 1 is known before adding advisor 2
		assertThrows(AssertionError.class,()->{a.addAdvisor2(b);} );
		//test advisor 1 is not null
		assertThrows(AssertionError.class,()->{a.addAdvisor1(null);} );
		a.addAdvisor1(b);
		//cannot add new advisor 1 if advisor 1 is known
		assertThrows(AssertionError.class,()->{a.addAdvisor1(c);} );
		//test that advisor 2 cannot be same as advisor 1
		assertThrows(AssertionError.class,()->{a.addAdvisor2(b);} );
		//test that advisor 2 cannot be null
		assertThrows(AssertionError.class,()->{a.addAdvisor2(null);} );
		a.addAdvisor2(c);
		//test that we cannot ad a new advisor 2 if advisor 2 is known
		assertThrows(AssertionError.class,()->{a.addAdvisor2(d);} );
		assertEquals(b,a.advisor1());
		assertEquals(c,a.advisor2());
		assertEquals(1,b.numAdvisees());
		assertEquals(1,c.numAdvisees());
		
	}
	@Test
	void testConstructor2() {
		PhD a=new PhD("John Smith", 1950, 3);
		PhD b= new PhD("Jane", 1990, 2, a);
		PhD c=new PhD("Adam", 1999, 11, a, b);
		assertEquals(b,c.advisor2());
		assertEquals(a,c.advisor1());
		//Name is not null
		assertThrows(AssertionError.class,()->{new PhD(null,1,2);});
		//length of name>=2
		assertThrows(AssertionError.class,()->{new PhD("A",1,2);});
		//month is not <1
		assertThrows(AssertionError.class,()->{new PhD("ABBA",1,-2);});
		assertThrows(AssertionError.class,()->{new PhD("ABBA",1,0);});
		assertThrows(AssertionError.class,()->{new PhD("ABBA",1,
					Integer.MIN_VALUE);});
		//month is not >12
		assertThrows(AssertionError.class,()->{new PhD("ABBA",2,15);});
		assertThrows(AssertionError.class,()->{new PhD("ABBA",2,
				Integer.MAX_VALUE);});
		//advisor 1 is not null
		assertThrows(AssertionError.class,()->{new PhD("Test",1888,3,null);});
		assertThrows(AssertionError.class,()->{new PhD("Test",1888,3,null,a);});
		//advisor 2 is not null
		assertThrows(AssertionError.class,()->{new PhD("Test",1888,3,a,null);});
		assertThrows(AssertionError.class,()->{new PhD("Test",1888,3,null,null);});
		//advisor 1 and 2 are not the same
		assertThrows(AssertionError.class,()->{new PhD("Test",1888,3,a,a);});
		


	}
	@Test
	void testgotBeforeandisSiblingOf() {
		PhD Jan501=new PhD("John Smith", 1950, 1);
		PhD jan50=new PhD("jan1950", 1950,1);
		PhD nov50=new PhD("Adam", 1950, 11, jan50,Jan501);
		PhD feb90= new PhD("Jane", 1990, 2, jan50,nov50);
		PhD jan90=new PhD("jan1990",1990,1,Jan501,nov50);
		assertTrue(Jan501.gotBefore(feb90));//year before, month before
		assertTrue(nov50.gotBefore(feb90));//year before, month after
		assertTrue(Jan501.gotBefore(jan90));//year before, month same
		assertTrue(Jan501.gotBefore(nov50));//year same, month before
		assertFalse(jan50.gotBefore(Jan501));//year same,month same
		assertFalse(nov50.gotBefore(Jan501));//year same, month after
		assertFalse(feb90.gotBefore(nov50));//year after, month before
		assertFalse(jan90.gotBefore(jan50));//year after, month same
		assertFalse(feb90.gotBefore(jan50));//year after, month after
		assertFalse(jan90.gotBefore(null));//p is null
		assertFalse(Jan501.isSiblingOf(jan50));//No advisors
		assertFalse(jan50.isSiblingOf(jan50));//A and B are same
		assertTrue(nov50.isSiblingOf(feb90));//advisor 1 not null = advisor 1
		assertTrue(nov50.isSiblingOf(jan90));//advisor 2 not null = advisor 1
		assertTrue(jan90.isSiblingOf(nov50));//advisor 1 not null = advisor 2
		assertTrue(jan90.isSiblingOf(feb90));//advisor 2 not null = advisor 2
		//p is not null
		assertThrows(AssertionError.class,()->{jan90.isSiblingOf(null);});
		

	}
}
