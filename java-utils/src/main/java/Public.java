import java.lang.annotation.Annotation;

@
public class Public implements Annotation {

    @Override
    public Class<? extends Annotation> annotationType() {
    }
}
