package comp1140.ass2.pipeline;

@FunctionalInterface
public interface ChainableHandler<T> {

    T handle(T input);

}
