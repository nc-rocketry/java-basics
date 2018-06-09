import inc.morsecode.util.SimpleCalendar;

import java.lang.reflect.Method;

public class Sandbox {


    public static void main(String[] args) {

        SimpleCalendar now= new SimpleCalendar();


        ClassMeta meta= new ClassMeta(now);

        meta.getters().forEach(m -> System.out.println(m.getMethod().getName()));
        meta.setters().stream()
                .forEach(m -> inspect(m))
                ;

    }

    public static void inspect(MethodMeta m) {

        Class retType= m.getMethod().getReturnType();

        if (m.returnsVoid()) {
            System.out.println(m);
        }

    }
}
