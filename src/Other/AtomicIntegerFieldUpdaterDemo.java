package Other;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

public class AtomicIntegerFieldUpdaterDemo {

    class DemoData{
        public volatile int value1 = 1;
        volatile int value2 = 2;
        protected volatile int value3 = 3;
        private volatile int value4 = 4;
    }

    AtomicIntegerFieldUpdater<DemoData> getUpdate(String fieldName){
        return AtomicIntegerFieldUpdater.newUpdater(DemoData.class, fieldName);
    }

    void doit(){
        DemoData demoData = new DemoData();
        System.out.println("1==>" + getUpdate("value1").getAndSet(demoData, 10));
        System.out.println("3==>" + getUpdate("value2").incrementAndGet(demoData));
        System.out.println("2==>" + getUpdate("value3").decrementAndGet(demoData));
        System.out.println("true==>" + getUpdate("value4").compareAndSet(demoData, 4, 5));
        System.out.println("value4 == " + getUpdate("value4").get(demoData));
    }

    public static void main(String[] args) {
        AtomicIntegerFieldUpdaterDemo demo = new AtomicIntegerFieldUpdaterDemo();
        demo.doit();
    }
}
