package assignment3;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Random;


public class ArrayBasedCollectionTimer 
{	
	/**
	 *
	 */
	public static void main(String[] args)
	{		
		// TODO add any preparations needed for the timing experiment

		timer();//Done
	}

	/**
	 *
	 */
	public static void timer()
	{



		//TIME SORT


		//Its a hashtable if we were going to save it to a csv contac card format #table
		Hashtable<Integer, Double> sortTimes = GrowthBinary(3, 250, 1000000);

		//TIME BINARY


	}

	/// <summary>
	/// Creates a dictionary of points representing growth rates of the algorithm
	/// </summary>
	/// <param name="range">The range ( n to dispaly the growth rate)</param>
	/// <returns></returns>
	public static Hashtable<Integer, Double> GrowthSort(int start, int range, int averageCount)
	{
		Random rnd = new Random();
		Hashtable<Integer, Double> NTable = new Hashtable<Integer, Double>();
		
		
		
		
		//Go through the range
		for (int n = start; n < range+1; n+=50000)
		{

			int warm = 0;
			while (warm < 10000)
			{ warm++; /*Let the thread warm up.*/ System.nanoTime(); }
			
			//Take the average algorithm time at range r
			long algorithmTime = 0;
			for (int a = 0; a < averageCount; a++)
			{
				//Timing
				ArrayBasedCollection<Integer> arr = new ArrayBasedCollection<Integer>();
				arr.addAll(permuteInts(n));

				long warmUptime = System.nanoTime();

				//Run the algorithm
				arr.toSortedList(new IntegerComparator());
				long sortTime = System.nanoTime();
				//OVERHEAD

				for(int i = 1; i<n; i++){
					int j;
					for(j = i-1; j>=0; j--);
				}

				long overHead = System.nanoTime();
				
				//Calculate time
				algorithmTime += (sortTime - warmUptime) - (overHead - sortTime);

			}
			System.out.println(n + "\t" + (double) algorithmTime/averageCount);
			NTable.put(n ,(double) (algorithmTime/averageCount));
		}
		
		return NTable;
	}
	
	
	/// <summary>
	/// Creates a dictionary of points representing growth rates of the algorithm
	/// </summary>
	/// <param name="range">The range ( n to dispaly the growth rate)</param>
	/// <returns></returns>
	public static Hashtable<Integer, Double> GrowthContains(int start, int range, int averageCount)
	{
		Random rnd = new Random();
		Hashtable<Integer, Double> NTable = new Hashtable<Integer, Double>();
		
		
		
		
		//Go through the range
		for (int n = start; n < range+1; n+=500)
		{

			int warm = 0;
			while (warm < 10000)
			{ warm++; /*Let the thread warm up.*/ System.nanoTime(); }
			
			//Take the average algorithm time at range r
			long algorithmTime = 0;
			for (int a = 0; a < averageCount; a++)
			{
				//Timing
				ArrayBasedCollection<Integer> arr = new ArrayBasedCollection<Integer>();
				arr.addAll(ascendingInts(n));
				
				int elemLoc = rnd.nextInt(arr.size-1);
				Integer element = arr.get(elemLoc);

				long warmUptime = System.nanoTime();

				//Run the algorithm
				arr.contains(element);
				long containsTime = System.nanoTime();
				//OVERHEAD

				for(int i = 1; i<elemLoc; i++){
				}

				long overHead = System.nanoTime();
				
				//Calculate time
				algorithmTime += (containsTime - warmUptime) - (overHead - containsTime);

			}
			System.out.println(n + "\t" + (double) algorithmTime/averageCount);
			NTable.put(n ,(double) (algorithmTime/averageCount));
		}
		
		return NTable;
	}

	/// <summary>
	/// Creates a dictionary of points representing growth rates of the algorithm
	/// </summary>
	/// <param name="range">The range ( n to dispaly the growth rate)</param>
	/// <returns></returns>
	public static Hashtable<Integer, Double> GrowthBinary(int start, int range, int averageCount)
	{
		Random rnd = new Random();
		Hashtable<Integer, Double> NTable = new Hashtable<Integer, Double>();
		
		
		IntegerComparator cmp = new IntegerComparator();
		
		//Go through the range
		for (int n = start; n < range+1; n+=1)
		{
			int warm = 0;
			while (warm < 10000)
			{ warm++; /*Let the thread warm up.*/ System.nanoTime(); }
			
			
	
			//Take the average algorithm time at range r
			long algorithmTime = 0;
			for (int a = 0; a < averageCount; a++)
			{
				//Timing
				ArrayBasedCollection<Integer> arr = new ArrayBasedCollection<Integer>();
				ArrayList<Integer> searchList = (ascendingInts(n));
				
				int elemLoc = rnd.nextInt(searchList.size()-1);

				Integer element = searchList.get(elemLoc);
				long warmUptime = System.nanoTime();

				//Run the algorithm
				SearchUtil.binarySearch(searchList, element, cmp);
				long containsTime = System.nanoTime();
				//OVERHEAD

				overHead(1, (int)(Math.log(n)/Math.log(2)) -1 );

				long overHead = System.nanoTime();
				
				//Calculate time
				algorithmTime += (containsTime - warmUptime) - (overHead - containsTime);

			}
			System.out.println(n + "\t" + (double) algorithmTime/averageCount);
			NTable.put(n ,(double) (algorithmTime/averageCount));
		}
		
		return NTable;
	}

	public static void overHead(int level, int stop){
		if(level < stop)
			overHead(level +1, stop);
		else
			return;
	}


	// Generate an array of random integers [0..size - 1]
	public static ArrayList<Integer> randomInts(int size)
	{
		Random rand = new Random();
		ArrayList<Integer> retval = new ArrayList<Integer>();
		for(int i=0; i < size; i++)
			retval.add(rand.nextInt(size));
		return retval;
	}
	public static ArrayList<Integer> permuteInts(int size)
	{
		Random rand = new Random();
		int retval[] = ascendingInts(size, 1);
		for(int i=0; i < size; i++)
			swap(retval, i, rand.nextInt(size));
		
		ArrayList<Integer> retvaasdl = new ArrayList<Integer>();
		for(int i = 0; i < size;i++)
			retvaasdl.add(retval[i]);
		return retvaasdl;
	}

	// Generate an array of ascending integers
	public static ArrayList<Integer> ascendingInts(int size)
	{
		int retval[] = new int[size];
		for(int i=0; i < size; i++)
			retval[i] = i;
		ArrayList<Integer> retvaasdl = new ArrayList<Integer>();
		for(int i = 0; i < size;i++)
			retvaasdl.add(retval[i]);
		return retvaasdl;
	}
	
	// Generate an array of ascending integers
	public static int[] ascendingInts(int size, int x)
	{
		int retval[] = new int[size];
		for(int i=0; i < size; i++)
			retval[i] = i;
		return retval;
	}

	// Generate an array of descending integers
	public static ArrayList<Integer> descendingInts(int size)
	{
		int retval[] = new int[size];
		for(int i=0; i< size; i++)
			retval[i] = size - i - 1;
		ArrayList<Integer> retvaasdl = new ArrayList<Integer>();
		for(int i = 0; i < size;i++)
			retvaasdl.add(retval[i]);
		return retvaasdl;
	} 

	// Swaps two items in the given array
	public static void swap(int[] arr, int i, int j)
	{
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}

	public static void printArray(int[] nums)
	{
		for(int k=0; k < nums.length; k++)
			System.out.print(nums[k] + " ");	
		System.out.println();
	}

	public static int[] copyArray(int[] nums)
	{
		int retval[] = new int[nums.length];		
		for(int i = 0; i < nums.length; i++)
			retval[i] = nums[i];		
		return retval;
	}

	// Determines if an array is sorted
	public static boolean isSorted(int[] nums)
	{
		for(int i=0; i < nums.length-1; i++)
			if(nums[i] > nums[i+1])
				return false;
		return true;
	}
}
