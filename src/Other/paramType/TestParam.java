package Other.paramType;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;

public class TestParam extends Bclass<TestParam> implements Asuper<TestParam> {

    public static void main(String[] args) {
        TestParam ins = new TestParam();
        Type[] arr = ins.getClass().getGenericInterfaces();
        for (Type t : arr) {
            System.out.println("Type name: "+t.getTypeName());
            ParameterizedType p = (ParameterizedType) t;
            System.out.println("RawType: "+p.getRawType());
            System.out.println("Type arguments: "+ Arrays.toString(p.getActualTypeArguments()));
            System.out.println(p.getActualTypeArguments()[0] == TestParam.class);
            System.out.println("type owner: "+p.getOwnerType());
        }
        Type type = ins.getClass().getGenericSuperclass();
        System.out.println(type.getTypeName());
    }
}
