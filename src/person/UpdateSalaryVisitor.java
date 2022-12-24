package person;

	public class UpdateSalaryVisitor extends EmployeeVisitor {
	    //After making the view we will take the entered percentage and make the change to the attribute PayPerHour
	    //that if the percent is negative deduction from the salary will occur
	    //if positive it will increase on the salary

	    private int keeperPercentage;
	    private int managerPercentage;
	    private double payperhour;

	    public UpdateSalaryVisitor(int keeper_per, int manager_per) {
	        keeperPercentage = keeper_per;
	        managerPercentage = manager_per;
	    }
	    @Override
	    public void visit(Keeper k) {
	    	
	        payperhour = k.getPayPerHour();
	        payperhour += payperhour * ((double)keeperPercentage / 100);
	        
	        k.setPayPerHour(payperhour);
	    }

	    @Override
	    public void visit(Manager m) {
	        payperhour = m.getPayPerHour();
	        System.out.println(payperhour);
	        payperhour += payperhour * ((double)managerPercentage / 100);
	        
	        m.setPayPerHour(payperhour);
	    }
	}