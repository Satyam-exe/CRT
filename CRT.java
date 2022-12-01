import java.lang.*;
import java.util.*;

public class CRT{

    public static int HighestCommonFactor(int a,int b){

        int res = 0;

        while (b > 0){

            int temp = b;

            b = a % b;

            a = temp;

            res = a;

        }

        return res;

    }

    public static int[] BezoutsIdentity(int a, int b) {

        int x = 0, y = 1, lastx = 1, lasty = 0, temp;

        while (b != 0) {

            int q = a / b;
            int r = a % b;

            a = b;
            b = r;

            temp = x;
            x = lastx - q * x;
            lastx = temp;

            temp = y;
            y = lasty - q * y;
            lasty = temp;

        }

        int[] bezoutOut = {lastx, lasty};

        return bezoutOut;

    }

    public static void main(String[] args) {

        ArrayList<Integer> inputRem = new ArrayList<>();
        ArrayList<Integer> inputDivisor = new ArrayList<>();

        Scanner in = new Scanner(System.in);

        System.out.println("How many combinations of remainders and divisors do you wish to enter?");
        int howManyCongruences = in.nextInt();

        System.out.println("Please enter both the remainder and divisor in the same line separated by a space (\" \").");

        for (int i = 0; i < howManyCongruences; i++) {

            int y = in.nextInt();
            int z = in.nextInt();

            inputRem.add(y);
            inputDivisor.add(z);

        }

        int hcf = HighestCommonFactor(inputDivisor.get(0), inputDivisor.get(1));
        for (int i = 2; i < howManyCongruences - 1; i++) {
            hcf = HighestCommonFactor(hcf, inputDivisor.get(i));
        }

        int N = 1;

        for (int i = 0; i < howManyCongruences; i++) {

            N *= inputDivisor.get(i);

        }


        int[] m = new int[inputDivisor.size()];

        for (int i = 0; i < howManyCongruences; i++) {

            m[i] = N / inputDivisor.get(i);

        }

        int[] bezoutListU = new int[inputDivisor.size()];
        int[] bezoutListV = new int[inputDivisor.size()];

        for (int i = 0; i < howManyCongruences; i++) {

            bezoutListU[i] = BezoutsIdentity(inputDivisor.get(i), m[i])[0];
            bezoutListV[i] = BezoutsIdentity(inputDivisor.get(i), m[i])[1];

        }

        int[] e = new int[howManyCongruences];

        for (int i = 0; i < howManyCongruences; i++) {

            e[i] = bezoutListV[i] * m[i];

        }

        int answerWithoutMod = 0;

        for (int i = 0; i < howManyCongruences; i++) {

            answerWithoutMod += e[i] * inputRem.get(i);

        }

        int answer = answerWithoutMod%N;

        while(answer<=0){

            answer+=N;

        }

        System.out.println("The answer is " + answer);



    }
}