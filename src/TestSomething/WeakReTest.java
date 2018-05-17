package TestSomething;

import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

public class WeakReTest {

    public static void main(String[] args) {
        final ReferenceQueue queue = new ReferenceQueue();
        new Thread(){
            @Override
            public void run() {
                try {
                    Reference reference = queue.remove();
                    System.out.println(reference + "被回收");
                } catch (InterruptedException e) {

                }
            }
        }.start();
        Object ob = new Object();
        Reference root = new WeakReference(ob, queue);
        System.out.println(root);
        ob = null;
        System.gc();
    }
}
