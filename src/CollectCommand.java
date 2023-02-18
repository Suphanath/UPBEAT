class CollectCommand extends ActionCommand {
    private Expression region;

    public CollectCommand(Expression region) {
        this.region = region;
    }

    public void execute() {

    }

    @Override
    public void execute(Environment env) {
        boolean regionValue = region.evaluate();
        env.collect(regionValue);
    }
}
