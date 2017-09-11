import java.util.*;

public class Vector {

	private Long sum;
	private Long mode;
	private Long median;
	private Long minimum;
	private Long maximum;
	private boolean sorted;
	private String generator;

	private final int length;
	private final long[] elements;

	// ===========================================================================
	// INITIALIZATION
	// ===========================================================================

	/**
	 * Constructs new vector with the given
	 * length and all elements set to zero.
	 */
	public Vector(int length) {

		this.sum = null;
		this.mode = null;
		this.median = null;
		this.minimum = null;
		this.maximum = null;
		this.sorted = false;
		this.generator = null;

		this.length = length;
		this.elements = new long[length];
	}

	/**
	 * Returns new vector with elements generated at random up to 100.
	 */
	public static Vector random(int length, long seed) {

		Vector vector = new Vector(length);
		Random random = new Random(seed);

		for (int i = 0; i < length; i++) {
			vector.elements[i] = (long) random.nextInt(101);
		}
		vector.generator = "random";
		return vector;
	}

	/**
	 * Returns new vector with all elements set to given value.
	 */
	public static Vector uniform(int length, long value) {

		Vector vector = new Vector(length);
		for (int i = 0; i < length; i++) {
			vector.elements[i] = value;
			//System.out.println(i + " " + vector.elements[i]);
		}
		//System.out.println(Arrays.toString(vector.elements));
		vector.generator = "uniform";
		vector.sorted = true;
		return vector;
	}

	/**
	 * Returns new vector with elements in sequence from given start and step.
	 */
	public static Vector sequence(int length, long start, long step) {

		Vector vector = new Vector(length);
		for (int i = 0; i < length; i++) {
			vector.elements[i] = start + (step * i);
		}
		vector.generator = "sequence";
		vector.sorted = true;
		return vector;
	}

	/**
	 * Returns whether the number is semiprime.
	 */
	public static boolean isPQ(long number) {

		/*ArrayList<Long> primeFactors = new ArrayList<Long>();
		for (long i = 1; i < number; i++) {
			if (number % i == 0) {
				if (Vector.isPrimeForPQ(number/i)) {
					primeFactors.add(number/i);
				}
			}
		}
		for (int i = 0; i < primeFactors.size(); i++) {
			for (int j = 0; j < primeFactors.size(); j++) {
				if (primeFactors.get(i) * primeFactors.get(j) == number) {
					return true;
				}
			}
		}*/
	/*	for (long i = 0; i < (number/2 +1); i++) {
			if(isPrime(i) == true) {
				if (number % i == 0 ) {
					if (isPrime(number/i) == true) {
						return true;
					}
				}
			}
		}
		return false;*/
		if (number < 4) {
			return false;
		} else {
			int primeFactors = 0;
			for (long i = 2; i < (number/2 +1); i++) {
					if(number % i == 0) {
						if (isPrime(i) == true) {
							if (isPrime(number/i) == true) {
								return true;
							} else {
								return false;
							}
						}
					}
				}
				return false;
			}
		}

	public static boolean isPrimeForPQ(long number) {

		if (number == 1 || number == -1 || number == 0) {
			return false;
		}
		for (long i = 2; i < number; i++) {
			if (isPrime(i)) {
				if (number % i == 0) {
					return false;
				}
			}
		}

		return true;
	}


	/*
	 * Returns new vector with elements generated from the
	 * pq number sequence starting from the specified value.
	 */
	public static Vector pq(int length, long start) {

		Vector vector = new Vector(length);
		int place = 0;
		for (long i = start; place < length; i++) {
			if (Vector.isPQ(i) == true) {
				vector.elements[place] = i;
				place++;
			}
		}
		vector.generator = "pq";
		vector.sorted = true;
		return vector;
	}

	/**
	 * Returns whether the number is prime.
	 */
	public static boolean isPrime(long number) {

		/*if (number == 1 || number == -1 || number == 0) {
			return false;
		}
		long top = (long)Math.sqrt(number) + 1;
		for (long i = 2; i < top; i++) {
			if (number % i == 0) {
				return false;
			}
		}

		return true;*/
		if (number <= 1) {
			return false;
		} else if (number <= 3) {
			return true;
		} else if (number % 2 == 0 || number % 3 == 0) {
			return false;
		}
		Long i = 5l;
		while(i*i <= number) {
			if (number % i == 0 || number % (i + 2) == 0) {
				return false;
			}
			i += 6;
		}
		return true;
	}

	/**
	 * Returns new vector with elements generated from the
	 * prime number sequence starting from the specified value.
	 */
	public static Vector prime(int length, long start) {

		Vector vector = new Vector(length);
		int place = 0;
		for (long i = start; place < length; i ++) {
			if (Vector.isPrime(i) == true) {
				vector.elements[place] = i;
				place++;
			}
		}
		vector.generator = "prime";
		vector.sorted = true;
		return vector;
	}

	/**
	 * Returns whether the number is abundant.
	 */
	public static boolean isAbundant(long number) {

		long sum = 1;
		for (long i = 2; i < number; i++) {
			if (number % i == 0) {
				sum = sum + i;
			}
		}
		if (sum > number) {
			return true;
		}
		return false;

	}

	/**
	 * Returns new vector with elements generated from the
	 * abundant number sequence starting from the specified value.
	 */
	public static Vector abundant(int length, long start) {

		Vector vector = new Vector(length);
		int place = 0;
		if (start < 12) {
			start = 12;
		}
		for (long i = start; place < length; i++) {
			if (Vector.isAbundant(i) == true) {
				vector.elements[place] = i;
				place++;
			}
		}
		vector.generator = "abundant";
		vector.sorted = true;
		return vector;
	}

	/**
	 * Returns whether the number is composite.
	 */
	public static boolean isComposite(long number) {

		long top = (long)Math.sqrt(number) + 1;
		for (long i = 2; i < top; i++) {
			if (number % i == 0) {
				return true;
			}
		}

		return false;
	}

	/**
	 * Returns new vector with elements generated from the
	 * composite number sequence starting from the specified value.
	 */
	public static Vector composite(int length, long start) {

		Vector vector = new Vector(length);
		int place = 0;
		for (long i = start; place < length; i++) {
			if (Vector.isComposite(i) == true) {
				vector.elements[place] = i;
				place++;
			}
		}
		vector.generator = "composite";
		vector.sorted = true;
		return vector;
	}

	// ===========================================================================
	// VECTOR OPERATIONS
	// ===========================================================================

	/**
	 * Returns new vector that is a copy of the current vector.
	 */
	public Vector cloned() {

		Vector vector = new Vector(this.length);
		for (int i = 0; i < this.length; i++) {
			vector.elements[i] = this.elements[i];
		}
		vector.sorted = this.sorted;
		vector.generator = this.generator;
		return vector;
	}

	/**
	 * Returns new vector with elements ordered from smallest to largest.
	 */
	public Vector sorted() {

		Vector vector = new Vector(this.length);
		/*for (int i = 0; i < this.length; i++) {
			vector.elements[i] = this.elements[i];
		}*/
		if (this.sorted == false || (this.generator.equals("sequence") && this.elements[0] > this.elements[1])) {
			System.arraycopy(this.elements, 0, vector.elements, 0, this.length);
			Arrays.sort(vector.elements);
			vector.sorted = true;
			vector.generator = "sorted";
			return vector;
		} else {
			return this;
		}

	}

	/**
	 * Returns new vector with elements ordered in reverse.
	 */
	public Vector reversed() {

		Vector vector = new Vector(this.length);
		int end = this.length - 1;
		for (int i = 0; i < this.length; i++) {
			vector.elements[i] = this.elements[end - i];
		}
		vector.sorted = false;
		vector.generator = "reversed";
		return vector;
	}

	/**
	 * Returns new vector with elements shifted right by a given number of positions.
	 */
	 // [1,2,3,4,5] 6 = [5,1,2,3,4]
	 // [i] =
	public Vector shifted(int amount) {

		Vector shiftedVector = new Vector(this.length);
		while (true) {
			if (amount > this.length) {
				amount -= this.length;
				continue;
			}
			break;
		}
		// find position of first element
    for (int i = 0; i < this.length; i++) {
      int originalPlace = i;
      int shiftedPlace = i + amount;
			if (shiftedPlace >= this.length) {
				shiftedPlace = shiftedPlace - this.length;
			}
      shiftedVector.elements[shiftedPlace] = this.elements[i];
    }

		if (amount == 0) {
			shiftedVector.sorted = this.sorted;
		} else {
			shiftedVector.sorted = false;
		}
		shiftedVector.generator = "shifted";
		return shiftedVector;
	}

	/**
	 * Returns new vector, adding scalar to each element.
	 */
	public Vector scalarAdd(long scalar) {

		Vector vector = new Vector(this.length);
		for (int i = 0; i < this.length; i++) {
      vector.elements[i] = this.elements[i] + scalar;
    }
		vector.generator = "sA";
		vector.sorted = false;
		return vector;
	}

	/**
	 * Returns new vector, multiplying scalar to each element.
	 */
	public Vector scalarMultiply(long scalar) {

		Vector vector = new Vector(this.length);
    for (int i = 0; i < this.length; i++) {
      vector.elements[i] = this.elements[i] * scalar;
    }
		vector.generator = "sM";
		vector.sorted = false;
		return vector;
	}

	/**
	 * Returns new vector, adding elements with the same index.
	 */
	public Vector vectorAdd(Vector other) {

		Vector vector = new Vector(this.length);
    for (int i = 0; i < this.length; i++) {
      vector.elements[i] = this.elements[i] + other.elements[i];
    }
		vector.generator = "vA";
		vector.sorted = false;
		return vector;
	}

	/**
	 * Returns new vector, multiplying elements with the same index.
	 */
	public Vector vectorMultiply(Vector other) {

		Vector vector = new Vector(this.length);
    for (int i = 0; i < this.length; i++) {
      vector.elements[i] = this.elements[i] * other.elements[i];
    }
		vector.generator = "vM";
		vector.sorted = false;
		return vector;
	}

	// ===========================================================================
	// VECTOR COMPUTATIONS
	// ===========================================================================

	/**
	 * Returns the sum of all elements.
	 */
	public Long getSum() {

		// Calculate and store the sum when unknown
		if (this.sum == null) {

			this.sum = 0l;

		}
		long sum = 0;
    for (int i = 0; i < this.length; i++) {
      sum += this.elements[i];
    }

		return sum;
	}

	/**
	 * Returns the most frequently occuring element
	 * or -1 if there is no such unique element.
	 */
	public Long getMode() {

		if (this.mode != null) {
			return this.mode;
		} else if (this.generator.equals("uniform")) {
			return this.elements[0];
		} else if (this.generator.equals("prime") || this.generator.equals("pq") || this.generator.equals("abundant") || this.generator.equals("sequence") || this.equals("composite")){
			this.mode = -1l;
		} else {
			Hashtable <Long, Integer> frequency = new Hashtable<Long, Integer>();
	    for(int i = 0; i < this.length; i++) {
	      Long key = this.elements[i];
	      if (frequency.containsKey(key) == false) {
	        frequency.put(key, 1);
	        //System.out.println("FALSE");
	      } else {
	        int previousFreq = frequency.get(key);
	        frequency.put(key, previousFreq + 1);
	        //System.out.println(frequency.get(key));
	      }

	    }
	    int max = 0;
	    for (int i = 0; i <= this.length; i++) {
	      if (frequency.contains(i) == true && i > max) {
	        max = i;
	        //System.out.println(max);
	      }
	    }
	    if (max == 1) {
	      this.mode = -1l;
	      return this.mode;
	    }
	    for (int i = 0; i < this.length; i++) {
	      if (frequency.get(this.elements[i]) == max) {
	        this.mode = this.elements[i];
	        break;
	      }
	    }
		}
		return this.mode;
	}

	/**
	 * Returns the upper median.
	 */
	public Long getMedian() {
		if (this.generator.equals("uniform")) {
			return this.elements[0];
		} else if (this.sorted == false) {
			Arrays.sort(this.elements);
		}
		if (this.length % 2 == 0) {
			this.median = this.elements[this.length/2];
		} else {
			this.median = this.elements[Math.round(this.length/2)];
		}

		return this.median;
	}

	/**
	 * Returns the smallest value in the vector.
	 */
	public Long getMinimum() {

		if (this.sorted == false) {
			this.minimum = this.elements[0];
			for (int i = 0; i < this.length; i++) {
				if (this.minimum > this.elements[i]) {
					this.minimum = this.elements[i];
				}
			}
		} else if (this.generator.equals("sequence") && this.elements[0] > this.elements[1]) {
			this.minimum = this.elements[this.length -1];
		} else {
			this.minimum = this.elements[0];
		}

		return this.minimum;
	}

	/**
	 * Returns the largest value in the vector.
	 */
	public Long getMaximum() {

		if (this.sorted == false) {
			this.maximum = this.elements[0];
			for (int i = 0; i < this.length; i++) {
				if (this.maximum < this.elements[i]) {
					this.maximum = this.elements[i];
				}
			}
		} else if (this.generator.equals("sequence") && this.elements[0] > this.elements[1]) {
			this.maximum = this.elements[0];
		} else {
			this.maximum = this.elements[this.length-1];
		}
		return this.maximum;
	}

	/**
	 * Returns the frequency of the value in the vector.
	 */
	public long getFrequency(long value) {

		int frequency = 0;
		if (this.sorted == false) {
			for (int i = 0; i < this.length; i++) {
				if (this.elements[i] == value) {
					frequency += 1;
				}
			}
		} else {
			for (int i = 0; i < this.length; i++) {
				if (this.elements[i] > value) {
					break;
				} else if (this.elements[i] == value) {
					frequency += 1;
				}
			}
		}

		return frequency;
	}

	// ===========================================================================
	// DISPLAY OPERATIONS
	// ===========================================================================

	/**
	 * Displays the vector.
	 */
	public void display() {

		String output = Arrays.toString(this.elements);
		output = output.replace("[", "");
		output = output.replace("]", "");
		output = output.replace(",", "");
		System.out.println(output);
		return;
	}

	/**
	 * Displays the element at the specified index.
	 */
	public void displayElement(int index) {

		System.out.println(this.elements[index]);
		return;
	}

	// ===========================================================================
	// ACCESSOR METHODS
	// ===========================================================================

	/**
	 * Returns the vector length.
	 */
	public int getLength() {

		return this.length;
	}

	/**
	 * Returns the vector elements.
	 */
	public long[] getElements() {

		return this.elements;
	}
}

