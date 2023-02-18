public abstract class ActionCommand extends Statement {
    public static Object Type;

    public abstract void execute();

    public abstract void execute(Environment env);
}
