import java.util.Scanner;

public class SolutionWithCheckers {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int numberOfTests = in.nextInt();
        long shield;
        String commands;
        int result;
        int test;
        for (int i = 0; i < numberOfTests; i++) {
            shield = in.nextLong();
            commands = in.nextLine();
            result = solve(shield, commands);
            test = i + 1;
            if (result == -1)
                System.out.println("Case #" + test +": IMPOSSIBLE");
            else
                System.out.println("Case #" + test + ": " + result);
        }
    }

    private static int solve(long shield, String commands) {
        int[] positionsOfS = new int[commands.length()];
        int writerPosOfS = 1;
        int numberOfS = 0;
        long sumDealtDmg = 0;
        int tmpDmgCharge = 1;
        int lastSPos;
        int[] dealDmg = new int[commands.length()];
        for (int i = 1; i < commands.length(); i++) {
            System.out.println(commands.charAt(i) + " commands.charAt(i)");     //          !!!
            if (commands.charAt(i) == 'S') {
                dealDmg[writerPosOfS] = tmpDmgCharge;
                System.out.println(dealDmg[writerPosOfS] + " dealDmg[writerPosOfS]");   //          !!!
                sumDealtDmg += tmpDmgCharge;
                System.out.println(sumDealtDmg + " sumDealtDmg");     //             !!!
                positionsOfS[writerPosOfS] = i;
                System.out.println(positionsOfS[writerPosOfS] + " positionsOfS[writerPosOfS]");     //      !!!
                writerPosOfS++;
                System.out.println(writerPosOfS + " writerPosOfS");   //      !!!
                numberOfS++;
            } else {
                tmpDmgCharge *= 2;
                System.out.println(tmpDmgCharge + " tmpDmgCharge");   //          !!!
            }
            System.out.println();   //          !!!
        }
        lastSPos = writerPosOfS - 1;

        for (int i = 1; i <= numberOfS; i++) {       //      !!!
            System.out.println(positionsOfS[i]);
        }

        System.out.println(lastSPos + " lastSPos");       //          !!!
        int numberOfSwaps = 0;
        int sameChar;
        int whichS;
        char swap;
        if (sumDealtDmg <= shield)
            return numberOfSwaps;
        while (positionsOfS[lastSPos] != numberOfS) {
            System.out.println("Main");     //      !!!


            if (positionsOfS[lastSPos] - positionsOfS[lastSPos - 1] == 1) {
                sameChar = positionsOfS[lastSPos];      //case "CSSSS"
                whichS = numberOfS;
                while (commands.charAt(sameChar - 1) == 'S') {
                    System.out.println("Side1");     //      !!!
                    System.out.println(sameChar + " sameChar");       //      !!!
                    System.out.println(whichS + " whichS");       //      !!!
                    sameChar--;
                    whichS--;
                }
                commands = replaceCharAt(commands, sameChar - 1, 'S');
                commands = replaceCharAt(commands, sameChar, 'C');
                sumDealtDmg -= dealDmg[whichS];
                dealDmg[whichS] /= 2;
                sumDealtDmg += dealDmg[whichS];
                positionsOfS[whichS]--;
                numberOfSwaps++;
            } else {
                System.out.println("Side2");
                commands = replaceCharAt(commands, positionsOfS[lastSPos], 'C');
                commands = replaceCharAt(commands, positionsOfS[lastSPos] - 1, 'S');
                sumDealtDmg -= dealDmg[lastSPos];
                dealDmg[lastSPos] /= 2;
                sumDealtDmg += dealDmg[lastSPos];
                positionsOfS[lastSPos]--;
                numberOfSwaps++;
            }
            if (sumDealtDmg <= shield)
                return numberOfSwaps;
            System.out.println(commands);       //      !!!
            System.out.println();
            System.out.println();
        }
        return -1;
    }

    private static String replaceCharAt(String s, int pos, char c) {
        return s.substring(0, pos) + c + s.substring(pos + 1);
    }

}
