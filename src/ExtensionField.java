public class ExtensionField {
    private long x;
    private long y;
    private long a;
    private long n;
    private long p;

    public ExtensionField(long x, long y, long a, long n, long p){
        this.x=x;
        this.y=y;
        this.a=a;
        this.n=n;
        this.p=p;
    }

    public long getX() {
        return this.x;
    }
    public long getY() {
        return this.y;
    }
    public long getA() {
        return this.a;
    }
    public long getN() {
        return this.n;
    }
    public long getP() {
        return this.p;
    }

    public ExtensionField multiplication(long x, long y,long d){
        long x0=this.x;
        long y0=this.y;
        long x1=x;
        long y1=y;

        long new_x=Main.modulo(((x0*x1)+(y0*y1*d)),this.p);
        long new_y=Main.modulo(x0*y1+y0*x1,this.p);
        return new ExtensionField(new_x,new_y,this.a,this.n,this.p);
    }
    public ExtensionField pow(long exp){
        ExtensionField result = new ExtensionField(1,1,this.a,this.n,this.p);
        ExtensionField base = new ExtensionField(this.a,this.y,this.a,this.n,this.p);
        long d = (long) (Math.pow(this.a,2)-n);
        while(exp>0){
            if(exp%2==1){
                result=result.multiplication(base.getX(),base.getY(),d);
            }
            base=base.multiplication(base.getX(),base.getY(),d);
            exp=exp/2;
        }

        return result;
    }
}
