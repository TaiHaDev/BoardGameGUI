package comp1140.ass2.pipeline;

public record Pipeline<T>(ChainableHandler<T> currentHandler) {

    public Pipeline<T> addHandler(ChainableHandler<T> handler) {
        return new Pipeline<>(state -> handler.handle(currentHandler.handle(state)));
    }

    public void execute(T state) {
        currentHandler.handle(state);
    }

}
