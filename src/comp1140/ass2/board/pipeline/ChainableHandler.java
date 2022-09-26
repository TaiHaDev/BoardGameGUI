package comp1140.ass2.board.pipeline;

@FunctionalInterface
public interface ChainableHandler<T> {

    T handle(T input);

}
