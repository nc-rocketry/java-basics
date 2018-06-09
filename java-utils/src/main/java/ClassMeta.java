import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ClassMeta {

    private Class definition;

    public ClassMeta(Object instance) {
        this(instance.getClass());
    }

    public ClassMeta(Class definition) {
        this.definition= definition;
    }

    public List<MethodMeta> methodsWith(Annotation annotation) {
        return Arrays.stream(definition.getMethods())
                .filter(m -> Arrays.stream(m.getAnnotations()).collect(Collectors.toList()).contains(annotation))
                .map(m -> new MethodMeta(definition, m))
                .collect(Collectors.toList());
    }
    public List<MethodMeta> methodsMatching(String regex) {
        return Arrays.stream(definition.getMethods())
                .filter(m -> m.getName().matches(regex))
                .map(m -> new MethodMeta(definition, m))
                .collect(Collectors.toList());
    }

    public List<MethodMeta> setters() { return methodsMatching("^set.*"); }
    public List<MethodMeta> getters() { return methodsMatching("^get.*"); }

}
