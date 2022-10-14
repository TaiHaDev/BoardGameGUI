package comp1140.ass2.actionstrategies;

public interface ActionStrategy {
    boolean isApplicable(String argument);
    void apply(String argument);
    int inputs = 1;
}
