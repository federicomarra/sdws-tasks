package dtu.example;

/**
 * A test class to shwo how to run JUnit 5 tests.
 *
 * Remove the tests in your own projects.
 */
public class TestJUnit {

	/* 
	 * JUnit 4 tests are also possible 
	 * because of the JUnit Vintage engine	
	*/
	@org.junit.Test 
	public void junit4Test() {
		System.out.println("JUnit 4");
		org.junit.Assert.assertTrue(true);
	}
	
	@org.junit.jupiter.api.Test
	public void junit5Test() {
		System.out.println("JUnit 5");
		org.junit.jupiter.api.Assertions.assertTrue(true);
	}

}
