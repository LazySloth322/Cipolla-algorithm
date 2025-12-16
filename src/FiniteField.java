import java.util.*;

public class FiniteField {
    private int p;
    private int[] field;
    public FiniteField(int p){
        this.p=p;
        this.field = new int[p];
        for(int i=0;i<p;i++){
            this.field[i]=i;
        }
    }

    public int[] getField(){
        return this.field;
    }

    public long getRandomElem(){
        int rnd = new Random().nextInt(this.field.length);
        return this.field[rnd];
    }

    public int[] removeDuplicates(int[] array){
        Arrays.sort(array);

        int current = array[0];
        boolean found = false;
        String str="";
        for(int i = 0; i < array.length; i++) {
            if (current == array[i] && !found) {
                found = true;
            } else if (current != array[i]) {
                str+=current+" ";
                current = array[i];
                found = false;
            }
        }
        str+=current+" ";

        String[] tempStrArray = str.split(" ");
        int[] intArray = new int[tempStrArray.length];
        for(int i=0;i < tempStrArray.length;i++){
            intArray[i]=Integer.parseInt(tempStrArray[i]);
        }

        return intArray;
    }

    public int quadraticResidueCheck(int num){
        FastPow fastPow = new FastPow(this.p);
        int result;

        result = Main.modulo(fastPow.run(num,((this.p-1)/2)), this.p);

        return result;
    }
}

