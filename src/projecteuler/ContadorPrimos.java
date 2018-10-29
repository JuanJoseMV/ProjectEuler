/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projecteuler;

/**
 *
 * @author Juan José
 */
public class ContadorPrimos extends Thread {

    private int n;
    public int last;

    public ContadorPrimos(int n) {
        this.n = n;
    }
    
    public void run() {
        boolean[] primes = new boolean[n + 1];
        primes[0] = primes[1] = false;

        for (int i = 2; i <= n; i++) {
            primes[i] = true;
        }

        for (int i = 2; i <= (int) Math.sqrt(n); i++) {
            if (primes[i]) {
                for (int j = i; j * i <= n; j++) {
                    primes[j * i] = false;
                }
                System.out.println(i);
                last = i;
            }
        }
    }
    
    public int getLast(){
        return last;
    }

}
