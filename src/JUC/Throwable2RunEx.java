package JUC;

public class Throwable2RunEx {

    public static RuntimeException launderThrowable(Throwable t){
        if (t instanceof RuntimeException){
            return (RuntimeException) t;
        }else if(t instanceof Error){
            throw (Error)t;
        }else {
            throw new IllegalStateException("Not Checked", t);
        }
    }
}
