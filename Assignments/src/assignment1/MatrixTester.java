/*
 * Here is a starting point for your Matrix tester. You will have to fill in the rest of "main" with
 * more code to sufficiently test your Matrix class. We will be using our own MatrixTester for grading. 
*/
package assignment1;

import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Random;

public class MatrixTester {
	public static void main(String[] args)
	{	
		
		/***
		 * Dear TA's,
		 * 
		 *  There are certainly better ways of doing unit testing...
		 *  I know this isn't software practices so I won't complain. However
		 *  it may be advisable in the future to allow JUnit or frameworks of
		 *  our own creation.
		 *  
		 *  <3 <3 <3 <3 <3
		 */
		
		
		{
		//region Equals
		
		//Cross object test
		String a = "asd";
		Matrix m = new Matrix(new int[][]{{10,10},{5,12}});
		if(m.equals(a))
			System.out.println("EQUALS TEST: Cross object comparison failed");
		else
			System.out.println("EQUALS TEST: Cross object comparison succeeded");	
		
		//Bounds comparison
		Matrix o = new Matrix(new int[][]{{10, 10}});
		
		if(m.equals(o))
			System.out.println("EQUALS TEST: Cross matrix bounds comparison failed");
		else
			System.out.println("EQUALS TEST: Cross matrix bounds comparison succeeded");	
		
		
		//Element wise comparison
		o = new Matrix(new int[][]{{10, 10}, {5, 12}});
		
		if(m.equals(o))
			System.out.println("EQUALS TEST: Element matrix comparison succeeded");
		else
			System.out.println("EQUALS TEST: Element matrix comparison failed");	
		
		//end
		}
		
		{
		//region Multiplication
			
			
			//Bounds test
			Matrix a = new Matrix(new int[][]{{10}});
			Matrix b = new Matrix(new int[][]{{10,10},{10,10},{15,15}});
			
			if(a.times(b) == null)
				System.out.println("MULTIPLICATION TEST: "
						+ "Bounds comparison->null-pointer succesful; error successfully triggered");
			else
				System.out.println("MULTIPLICATION TEST: "
						+ "Bounds comparison->null-pointer failed");
			
			//Addition test
			Matrix M1 = new Matrix(new int[][]
                    {{1, 2, 3},
					  {2, 5, 6}});

			Matrix M2 = new Matrix(new int[][]
            	{{4, 5},
				  {3, 2},
				  {1, 1}});
			
			// this is the known correct result of multiplying M1 by M2
			Matrix M1_M2 = new Matrix(new int[][]
					{{13, 12},
					{29, 26}});
						
			if(M1.times(M2).equals(M1_M2))
				System.out.println("MULTIPLICATION TEST: "
						+ "Two matrices successfully added:\n"
						+ M1 + "\n"
						+ "****\n"
						+ M2 + "\n"
						+ "====\n"
						+ M1.times(M2));
			else
				System.out.println("MULTIPLICATION TEST: "
						+ "Two matrices failed to add:\n"
						+ M1 + "\n"
						+ "****\n"
						+ M2 + "\n"
						+ "====\n"
						+ M1.times(M2) +"\n"
						+ "Should be--\n"
						+ M1_M2);
		//end
		}
		
		{
		//region Addition
		
		//Bounds test
		Matrix a = new Matrix(new int[][]{{10}});
		Matrix b = new Matrix(new int[][]{{10,10},{10,10},{15,15}});
		
		if(a.plus(b) == null)
			System.out.println("ADDITIONS TEST: "
					+ "Bounds comparison->null-pointer succesful");
		else
			System.out.println("ADDITIONS TEST: "
					+ "Bounds comparison->null-pointer failed");
		
		//Addition test
		Matrix M1 = new Matrix(new int[][]
				{{1,2,3},
				{4,5,6}});
		Matrix M2 = new Matrix(new int[][]
				{{7,8,9},
				{10,11,12}});
		
		Matrix M1_M2 = new Matrix(new int[][]
				{{8,10,12},
				{14,16,18}});
		
		if(M1.plus(M2).equals(M1_M2))
			System.out.println("ADDITIONS TEST: "
					+ "Two matrices successfully added:\n"
					+ M1 + "\n"
					+ "++++\n"
					+ M2 + "\n"
					+ "====\n"
					+ M1.plus(M2));
		else
			System.out.println("ADDITIONS TEST: "
					+ "Two matrices failed to add:\n"
					+ M1 + "\n"
					+ "++++\n"
					+ M2 + "\n"
					+ "====\n"
					+ M1.plus(M2) +"\n"
					+ "Should be--\n"
					+ M1_M2);
		
		
			}
		//end
		
		
		
		int runsB = 100000;
		//PERFORM FINAL EXHAUSTIVE BOUNDS TEST
		System.out.println("\n\nRunning exhaustive bounds test at: " + runsB);
		
		if(MatrixTester.exhaustiveBoundsChecking(runsB))
			System.out.println("Bounds checking successful; tests succeded");
		else
			System.out.println("Bounds checking FAILED; one or more tests failed");
		
		
		
	}
	
		
	/***
	 * 
	 * Checks the bounds exhaustively for the matrices.
	 * @return
	 */
	public static boolean exhaustiveBoundsChecking(int trials)
	{
		//SUPRESS OUTPUT
		PrintStream console = System.out;
		
		System.setOut(new PrintStream(new OutputStream()
		{
			public void write(int b){
			//Do nothing
			}
			
		}));
		
		Random r= new Random();
		

		for(int i = 0; i < trials; i++)
		{
			int c1 = r.nextInt(10), r1= r.nextInt(10);
			int c2 = r.nextInt(10), r2 = r.nextInt(10);
			
			Matrix m1 = new Matrix(r1,c1);
			Matrix m2 = new Matrix(r2,c2);
			
			//Test multiplication bounds
			Object result = m1.times(m2);
			if((result == null && m1.numColumns == m2.numRows)
					|| (result != null && m1.numColumns != m2.numRows)){
				System.setOut(console);
				return false;
			}
			
			//Test addition
			
			result = m1.plus(m2);
			if((result == null && m1.numColumns == m2.numColumns && m1.numRows == m2.numRows) 
					|| (result != null && (m1.numColumns != m2.numColumns || m1.numRows != m1.numRows)) )
			{
				System.setOut(console);
				return false;
			}
			
		}
		System.setOut(console);
		return true;
		
	}
}
