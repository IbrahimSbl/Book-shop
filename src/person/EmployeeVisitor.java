package person;

public abstract class EmployeeVisitor {
	public abstract void visit(Keeper k);
	public abstract void visit(Manager m);
}
