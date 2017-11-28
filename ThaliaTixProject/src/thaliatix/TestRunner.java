package thaliatix;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;

public class TestRunner {

	public static void main(String[] args) {
		Result result = JUnitCore.runClasses(UnitTests.class);
		
		System.out.println(result.toString());
		System.out.println(result.wasSuccessful());
		
	}
}
