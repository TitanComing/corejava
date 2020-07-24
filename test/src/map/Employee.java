package map;

/**
 * Create by peng on 2020/7/24.
 */
public class Employee {
    private String name;
    private double salary;

    /**
     * Constructs an employee with $0 salary.
     * @param n the employee name
     */
    public Employee(String name)
    {
        this.name = name;
        this.salary = 0;
    }

    public String toString()
    {
        return "[name=" + name + ", salary=" + salary + "]";
    }
}
