/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projecteuler;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Random;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Juan José
 */
public class ProjectEuler {

    public static long summatory(long n) {
        return (n * (n + 1)) / 2;
    }

    public static boolean checkPrime(double p) {
        if (p < 2 || p % 2 == 0) {
            return false;
        }
        if (p > 60) {
            System.out.println("Not trustful.");
        }
        double aux = Math.pow(2, p);
        return (aux - 2) % p == 0;
    }

    public static boolean checkBigPrime(int n) {
        boolean[] primes = sieveOfEratosthenes(n);

        return primes[n];
    }

    public static boolean isPerfectSquare(long n) {
        if (n < 0) {
            return false;
        }
        if (n == 0) {
            return true;
        }
        double a = Math.sqrt(n);
        return (a % (int) a == 0);
    }

    /**
     * Generates an array of primes. O(n*log(log(n))).
     *
     * @param n Number of primes to generate.
     * @return boolean array. If a number (position) is prime, its value is
     * true.
     */
    public static boolean[] sieveOfEratosthenes(int n) {
        boolean[] primes = new boolean[n + 1];
        primes[0] = primes[1] = false;
        int total = 0;

        for (int i = 2; i <= n; i++) {
            primes[i] = true;
        }

        for (int i = 2; i <= (int) Math.sqrt(n); i++) {
            if (primes[i]) {
                for (int j = i; j * i <= n; j++) {
                    primes[j * i] = false;
                }
                total++;
            }
        }

        return primes;
    }

    /**
     * @param args the command line arguments
     */
    public static ArrayList<Integer> generatePrimes(int n) {
        ArrayList<Integer> ans = new ArrayList<>();
        boolean canAdd = true;
        for (int i = 2; i <= n; i++) {
            for (int j : ans) {
                if (i % j == 0) {
                    canAdd = false;
                    break;
                } else {
                    canAdd = true;
                }
            }
            if (canAdd) {
                ans.add(i);
            }
        }
        return ans;
    }

    public static int countPrimesUpperBound(int max) {
        return max > 1 ? (int) (1.255058712932479797 * max / Math.log((double) max)) : 0;
    }

    public static ArrayList<Integer> generatePrimes2(int limit) {
        final int numPrimes = countPrimesUpperBound(limit);
        ArrayList<Integer> primes = new ArrayList<Integer>(numPrimes);
        boolean[] isComposite = new boolean[limit];   // all false
        final int sqrtLimit = (int) Math.sqrt(limit); // floor
        for (int i = 2; i <= sqrtLimit; i++) {
            if (!isComposite[i]) {
                primes.add(i);
                for (int j = i * i; j < limit; j += i) // `j+=i` can overflow
                {
                    isComposite[j] = true;
                }
            }
        }
        for (int i = sqrtLimit + 1; i < limit; i++) {
            if (!isComposite[i]) {
                primes.add(i);
            }
        }
        return primes;
    }

    /**
     * Finds the biggest integer that's a power of b, but it's less or equal
     * than n. O(1).
     *
     * @param b base
     * @param n limit
     * @return biggest power of b less than n. Log base b of n.
     */
    public static int largestPowerOf(int b, int n) {
        return ((int) Math.pow(b, (Math.floor(Math.log(n) / Math.log(b)))));
    }

    public static boolean isAPowerOf(long n, long b) {
        double aux = Math.log(n) / Math.log(b);
        boolean ans = ((long) aux) % aux == 0;
        return ans;
    }

    public static ArrayList<Integer> generatePrimesTill(int n) {
        ArrayList<Integer> ans = new ArrayList<>();
        boolean canAdd = true;
        int stop = 0;
        int i = 2;

        while (true) {
            for (int j : ans) {
                if (i % j == 0) {
                    canAdd = false;
                    break;
                } else {
                    canAdd = true;
                }
            }
            if (canAdd) {
                ans.add(i);
                stop++;
            }
            if (stop == n) {
                break;
            }
            i++;
        }
        return ans;
    }

    public static int largestPrimeFactor(double n) {
        ArrayList<Double> array = new ArrayList<>();
        double sqr = (int) Math.sqrt(n);
        for (double i = 2; i < sqr; i++) {
            if (n % i == 0.0) {
                boolean prime = true;
                for (double j : array) {
                    if (i % j == 0) {
                        prime = false;
                    }
                }
                if (prime) {
                    array.add(i);
                }
            }
        }

        return (array.get(array.size() - 1)).intValue();
    }

    public static boolean checkPalindrome(int n) {
        Stack<Integer> num = new Stack<>();
        for (int i = n; i >= 1; i = i / 10) {
            num.push(i % 10);
        }

        for (int i = n; i > 1; i = i / 10) {
            if (i % 10 != num.pop()) {
                return false;
            }
        }
        return true;
    }

    public static int biggestPalindrome(int n) {
        int a = 0;
        int a2;
        int b = 0;
        int ans = 0;
        boolean aux = true;
        for (int i = 0; i < n; i++) {
            a += 9 * (int) Math.pow(10, i);
            b += 9 * (int) Math.pow(10, i);
        }
        a2 = a;
        while (b > (int) Math.pow(10, n - 1)) {
            int ans2 = a * b;
            if (checkPalindrome(ans2)) {
                if (ans2 > ans) {
                    ans = ans2;
                }
            }
            if (a != (int) Math.pow(10, n - 1)) {
                a--;
                aux = false;
            } else {
                b--;
                a = a2;
                aux = true;
            }
        }

        return ans;
    }

    public static long smallestDivisible(long n) {
        boolean aux = false;
        boolean aux1 = true;
        for (long i = 2 * n; aux1; i += n) {
            for (long j = 1; j <= n; j++) {
                if (i % j != 0) {
                    aux = false;
                    break;
                } else {
                    aux = true;
                }
            }
            if (aux) {
                return i;
            }
        }
        return 0;
    }

    public static long smallestDivisible2(long n) {
        ArrayList<Integer> primes = generatePrimes((int) n);
        long ans = 1;

        for (int i : primes) {
            ans *= (long) i;
        }

        for (int i : primes) {
            if ((long) largestPowerOf(i, (int) n) <= n) {
                ans *= (long) largestPowerOf(i, (int) n) / i;
            }
        }
        return ans;
    }

    public static long smallestDivisible3(long n) {
        boolean[] primes = sieveOfEratosthenes((int) n);
        long ans = 1;

        for (int i = 2; i <= n; i++) {
            if (primes[i]) {
                ans *= (long) i;
            }
        }

        for (int i = 2; i <= n; i++) {
            if (primes[i]) {
                if ((long) largestPowerOf(i, (int) n) <= n) {
                    ans *= (long) largestPowerOf(i, (int) n) / i;
                }
            }
        }
        return ans;
    }

    public static int smallestDivisible4(double n) {
        double ans = 1.0;
        for (double i = 2.0; i <= n; i++) {
            if (checkPrime(i)) {
                ans *= largestPowerOf((int) i, (int) n);
            }
        }
        return (int) ans;
    }

    public static double sumSquareDifference(int n) {
        double sum1 = 0.0;
        double sum2 = 0.0;

        for (double i = 1; i <= n; i++) {
            sum1 += Math.pow(i, 2);
            sum2 += i;
        }

        return (Math.pow(sum2, 2) - sum1);
    }

    public static long greatestProduct(String path, int n) throws FileNotFoundException, IOException {
        BufferedReader br = new BufferedReader(new FileReader(path));
        String num = br.readLine();

        //System.out.println("7316717653133062491922511967442657474235534919493496983520312774506326239578318016984801869478851843858615607891129494954595017379583319528532088055111254069874715852386305071569329096329522744304355766896648950445244523161731856403098711121722383113622298934233803081353362766142828064444866452387493035890729629049156044077239071381051585930796086670172427121883998797908792274921901699720888093776657273330010533678812202354218097512545405947522435258490771167055601360483958644670632441572215539753697817977846174064955149290862569321978468622482839722413756570560574902614079729686524145351004748216637048440319989000889524345065854122758866688116427171479924442928230863465674813919123162824586178664583591245665294765456828489128831426076900422421902267105562632111110937054421750694165896040807198403850962455444362981230987879927244284909188845801561660979191338754992005240636899125607176060588611646710940507754100225698315520005593572972571636269561882670428252483600823257530420752963450".equals(num));
        long biggestProduct = 0;
        long ans = 0;

        for (int i = 0; i < num.length() - n; i++) {
            String aux = num.substring(i, i + n);
            long auxProd = 1;
            for (int j = 0; j < n; j++) {
                auxProd *= Long.parseLong(aux.substring(j, j + 1));
            }
            if (auxProd > biggestProduct) {
                biggestProduct = auxProd;
                ans = Long.parseLong(aux);
            }
        }
        System.out.println("Biggest Product: " + biggestProduct + ".\nNumbers: " + ans + ".");
        return ans;
    }

    public static long pythagoreanTriplet(long n) {
        long a;
        long b;
        long c;

        for (c = 1; c <= (long) Math.pow(n, 2); c++) {
            for (long j = (long) Math.pow(c, 2) - 1; j > 0; j--) {
                if (isPerfectSquare(j)) {
                    a = (long) Math.sqrt(j);
                    b = (long) Math.pow(c, 2) - (long) Math.pow(a, 2);
                    if (isPerfectSquare(b)) {
                        b = (long) Math.sqrt(b);
                        System.out.println(c + "² = " + a + "² + " + b + "²");
                        if (a + b + c == n) {
                            return a * b * c;
                        }
                    }
                }
            }

        }

        return 0;
    }

    public static long sumOfPrimesBelowN(int n) {
        long ans = 0;
        boolean[] primes = sieveOfEratosthenes(n);
        int total = 0;

        for (int i = 2; i <= n; i++) {
            if (primes[i]) {
                ans += i;
                total++;
            }
        }

        return ans;
    }

    public static long numberOfDivisors(long n, boolean primes[]) {
        if (n == 1) {
            return 1;
        }

        long ans = 1;
        int aux = 0;
        for (int i = 2; i <= (int) Math.sqrt(primes.length - 1); i++) {
            if (primes[i]) {
                if (isAPowerOf(n, i)) {
                    return (long) (Math.log(n) / Math.log(i)) + 1;
                }
                long auxN = n;
                while (auxN % i == 0) {
                    auxN /= i;
                    aux++;
                }
                ans *= (aux + 1);
                aux = 0;
            }
        }
        return ans;
    }

    public static long highlyDivisibleTriangularNumber(int n) {
        int i = 1;
        long triangularN = 1;
        long divisors = 1;
        boolean primes[] = sieveOfEratosthenes(2000000);

        while (divisors <= n) {
            i++;
            triangularN = summatory(i);
            divisors = numberOfDivisors(triangularN, primes);
        }
        return triangularN;
    }

    public static long longestCollatzSequence(long n) {
        long ans = 1;
        long rSum = 1;
        long sum;
        long[] vec = new long[(int) n];

        vec[0] = vec[1] = 1;
        for (int i = 2; i < n; i++) {
            vec[i] = 0;
        }

        for (int i = 2; i <= n; i++) {
            int j = i;
            sum = 0;

            while (j > 1) {
                if (vec[j] == 0) {
                    sum++;

                    if (j % 2 == 0) {
                        j = j / 2;
                    } else {
                        j = 3 * j + 1;
                    }
                } else {
                    sum += vec[j];
                    break;
                }
            }
            vec[i] = sum;
            if (sum > rSum) {
                rSum = sum;
                ans = i;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
//        try {
        //        System.out.println(largestPrimeFactor(600851475143.0));
//        System.out.println(biggestPalindrome(3));
//        System.out.println(smallestDivisible4(20));
//        System.out.println((long)sumSquareDifference(100));
//        ArrayList<Integer> primes = generatePrimes2(2000000);
//        System.out.println(primes.get(primes.size()-1));
//        boolean[] primes = sieveOfEratosthenes(2000000);
//        System.out.println(primes2[4]);
//        System.out.println(greatestProduct("problem8.txt", 13));
//        } catch (IOException ex) {
//            Logger.getLogger(ProjectEuler.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        System.out.println(pythagoreanTriplet(1000));
//          System.out.println(isPerfectSquare(10000));
//        System.out.println(checkPrime(45));
//        Thread contador = new ContadorPrimos(45);
//        contador.run();
//
//        System.out.println(highlyDivisibleTriangularNumber(500000));

//        System.out.println(longestCollatzSequence(1000000));
        Random r = new Random();
        int n = r.nextInt(30);
        char[] caracs = new char[n];
        for (int i = 0; i < n; i++) {
            int aux = r.nextInt(152) % 2;
            caracs[i] = Integer.toString(aux).charAt(0);
        }
        String cadena = String.copyValueOf(caracs);
        System.out.println(cadena);

        int estado = 0;
        for (int i = 0; i < n; i++) {
            switch (caracs[i]) {
                case '1':
                    if (estado == 1) {
                        estado = 0;
                    }
                    break;
                case '0':
                    if (estado == 0) {
                        estado = 1;
                        break;
                    }
                    if (estado == 1) {
                        estado = 2;
                    }
                    break;
            }
        }
        
        System.out.println(estado == 2 ? "No acepta" : "Sí acepta");
    }
}
