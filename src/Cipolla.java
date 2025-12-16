public class Cipolla {
    public static int legendreSymbol(int a,int p){
        /*
        =0 если a делаится на p (???)
        =1 если а является квадратичным вычетом по модулю p, но при этом a не делится на p
        =-1 если a является квадратичным невычетом по модулю p
        */
        int legendre;
        FiniteField field = new FiniteField(p);

        if(Main.modulo(a,p)==0){
            legendre=0;
        }else{
            int qrResult = field.quadraticResidueCheck(a);
            if (qrResult==1){
                legendre = 1;
            }else{
                if(qrResult==(p-1)) legendre = -1;
                else return -2;
            }
        }

        return legendre;
    }

    public static void algorithm(int n, int p, int k){
        long a;
        int legendre;
        long ans=-1;
        FiniteField finiteField = new FiniteField(p);

        for(int i=0;i<k;i++) {
            a = finiteField.getRandomElem();
            try {
                Thread.sleep(500);
            }catch (Exception e){
                System.out.println("Error! "+e);
            }
            System.out.println("\nRandom number from field: "+a);
            //a^2-n must return legendre = -1
            legendre=legendreSymbol((int) Main.modulo((int)(Math.pow(a,2)-n),p),p);
            System.out.println("Legendre symbol for random number: "+legendre);
            if(legendre!=-1){
                System.out.println("Warning: a^2-n (where a="+a+") is quadratic residue of F_"+p+
                        ". Trying next random...");
            }else{
                ExtensionField xi = new ExtensionField(a,1,a,n,p);
                ExtensionField b = xi.pow((p+1)/2);
                ans= Main.modulo(b.getX(),p);
                break;
            }
        }
        if(ans!=-1) {
            System.out.println("\n==================== ANSWER =======================");
            System.out.println("||   X={" + ans + ", " + Main.modulo((p - ans), p) + "}; " + ans + "^2 ≡ " +
                    n + " (mod " + p + ")   ");
            System.out.println("==================== ====== =======================");
        }else{
            System.out.println("\nNo answer. Too few attempts");
        }
    }
}
