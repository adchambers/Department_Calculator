package calculator;

/**
 * Created by adcha on 5/17/2018.
 */
public class Employee {

    public String firstName;
    public String lastName;
    public String title;

    Employee(String firstName, String lastName, String title){
        this.firstName = firstName;
        this.lastName = lastName;
        this.title = title;
    }

    public String getFirstName(){
        return firstName;
    }

    public String getLastName(){
        return lastName;
    }

    public String getTitle(){
        return title;
    }
}
