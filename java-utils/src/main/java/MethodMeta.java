import java.lang.reflect.Method;

public class MethodMeta {

    final private Class owner;
    final private Method method;

    public MethodMeta(Class owner, Method method) {
        this.owner= owner;
        this.method= method;
    }

    public Class getOwner() { return owner; }
    public Method getMethod() { return method; }

    public boolean returnsSomething() {
        return method.getReturnType() != null;
    }

    public boolean returnsVoid() {
        return "void".equals(method.getReturnType().getName());
    }

    @Override
    public String toString() {
        return method.toString();
    }
}
