import java.util.Arrays;

/**
 * Class for doing Radix sort
 *
 * @author Akhil Batra, Alexander Hwang
 *
 */
public class RadixSort {
    /**
     * Does LSD radix sort on the passed in array with the following restrictions:
     * The array can only have ASCII Strings (sequence of 1 byte characters)
     * The sorting is stable and non-destructive
     * The Strings can be variable length (all Strings are not constrained to 1 length)
     *
     * @param asciis String[] that needs to be sorted
     *
     * @return String[] the sorted array
     */
    public static String[] sort(String[] asciis) {
        int maxLen = Integer.MIN_VALUE;
        for (String s : asciis) {
            maxLen = maxLen > s.length() ? maxLen : s.length();
        }

        String[] sorted = new String[asciis.length];
        System.arraycopy(asciis, 0, sorted, 0, asciis.length);
        for (int i = maxLen - 1; i >= 0; i--) {
            sortHelperLSD(sorted, i);
        }

        return sorted;
    }

    /**
     * LSD helper method that performs a destructive counting sort the array of
     * Strings based off characters at a specific index.
     * @param asciis Input array of Strings
     * @param index The position to sort the Strings on.
     */
    private static void sortHelperLSD(String[] asciis, int index) {
        int[] counts = new int[257];
        for (String s : asciis) {
            int id = index < s.length() ? (int) s.charAt(index) : 256;
            counts[id]++;
        }

        int[] starts = new int[257];
        int pos = 0;
        for (int i = 0; i < counts.length; i++) {
            starts[i] = pos;
            pos += counts[i];
        }

        String[] original = new String[asciis.length];
        System.arraycopy(asciis, 0, original, 0, asciis.length);
        for (String s : original) {
            int id = index < s.length() ? (int) s.charAt(index) : 256;
            int place = starts[id]++;
            asciis[place] = s;
        }
    }

    /**
     * MSD radix sort helper function that recursively calls itself to achieve the sorted array.
     * Destructive method that changes the passed in array, asciis.
     *
     * @param asciis String[] to be sorted
     * @param start int for where to start sorting in this method (includes String at start)
     * @param end int for where to end sorting in this method (does not include String at end)
     * @param index the index of the character the method is currently sorting on
     *
     **/
    private static void sortHelperMSD(String[] asciis, int start, int end, int index) {
        // Optional MSD helper method for optional MSD radix sort
        return;
    }

    public static void main(String[] args) {
        StringBuffer sb = new StringBuffer();
        String[] arr = new String[2];
        sb.append((char) 57);
        sb.append((char) 13);
        arr[0] = sb.toString();
//        arr[0] = "5;\u0086\u0010·«\u0092B\u0006¤\u0082^";

        sb = new StringBuffer();
        sb.append((char) 245);
        arr[1] = sb.toString();
//        arr[1] = "û\u0002\u0014\u009E¿\u0018Ü¹D'â]";

        String[] sorted = sort(arr);

        for (int i = 0; i < arr.length; i++) {
            System.out.print("String " + i + " : ");
            System.out.println(sorted[i]);
            for (int k = 0; k < sorted[i].length(); k++) {
                System.out.print((int) sorted[i].charAt(k) + " ");
            }
            System.out.println();
        }
    }
}
