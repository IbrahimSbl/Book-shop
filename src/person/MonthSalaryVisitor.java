package person;
//After  making the view get the id of text field and write the answer in it
public class MonthSalaryVisitor extends EmployeeVisitor{
	double salary = 0;
	@Override
	public void visit(Keeper k) {
		// TODO Auto-generated method stub
		salary=(k.getPayPerHour()*(30 - k.getOffDays())*k.getHoursPerDay());
	}

	@Override
	public void visit(Manager m) {
		// TODO Auto-generated method stub
		salary =(m.getPayPerHour()*(30 - m.getOffDays())*m.getHoursPerDay());
	}
	public double getSalary() {
		return salary;
	}
}
