package calculator;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javax.swing.*;
import java.text.DecimalFormat;

public class Controller {

    @FXML Button addButton;
    @FXML TextField firstNameTextField;
    @FXML TextField lastNameTextField;
    @FXML Label assignedAManagersLabel;
    @FXML Label assignedQATestersLabel;
    @FXML Label assignedDevelopersLabel;
    @FXML Label wagesAManagersLabel;
    @FXML Label wagesQATestersLabel;
    @FXML Label wagesDevelopersLabel;
    @FXML Label wagesTotalLabel;
    @FXML ComboBox titleComboBox;
    @FXML TableView<Employee> employeeTableView;

    int assignedAssistantManagers = 0;
    int assignedTesters = 0;
    int assignedDevelopers = 0;
    int assignedTotal = 0;

    double totalAssistantManagerWages = 0;
    double totalTesterWages = 0;
    double totalDeveloperWages = 0;
    double totalWages = 0;

    double ManagersAllocation = 2100.00;

    double assistantManagerWage = 300.00;
    double testerWage = 500.00;
    double developerWage = 1000.00;

    ObservableList<Employee> employees;

    ObservableList<String> titles = FXCollections.observableArrayList("Assistant Manager", "Developer", "Tester");

    public ObservableList<Employee> getEmployees(){
        employees = FXCollections.observableArrayList();
        employees.add(new Employee("Jane", "Doe", "Lead Manager"));
        employees.add(new Employee("John", "Doe", "Assistant Manager"));
        return employees;
    }

    public void initialize(){
        titleComboBox.setItems(titles);

        TableColumn<Employee, String> firstNameColumn = new TableColumn<>("First Name");
        firstNameColumn.resizableProperty().setValue(false);
        firstNameColumn.setPrefWidth(116);
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<Employee, String>("firstName"));

        TableColumn<Employee, String> lastNameColumn = new TableColumn<>("Last Name");
        lastNameColumn.resizableProperty().setValue(false);
        lastNameColumn.setPrefWidth(116);
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<Employee, String>("lastName"));

        TableColumn<Employee, String> titleColumn = new TableColumn<>("Title");
        titleColumn.resizableProperty().setValue(false);
        titleColumn.setPrefWidth(116);
        titleColumn.setCellValueFactory(new PropertyValueFactory<Employee, String>("title"));

        employeeTableView.getColumns().addAll(firstNameColumn, lastNameColumn, titleColumn);
        employeeTableView.setItems(getEmployees());

        firstNameTextField.setText("Adam");
        lastNameTextField.setText("Chambers");
        titleComboBox.setValue("Developer");

        SumEmployees();
        SumWages();
    }

    public void AddEmployee() {
        // Simple field validation
        if(firstNameTextField.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Please enter a first name.");
        }
        else if (lastNameTextField.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Please enter a last name.");
        }
        else if (titleComboBox.getValue().toString().isEmpty()){
            JOptionPane.showMessageDialog(null, "Please select a job title.");
        }
        // Simple budget validation
        else{

            double newEmployeeWage = 0;

            switch(titleComboBox.getValue().toString()){

                case "Assistant Manager":
                    newEmployeeWage = assistantManagerWage;
                    break;

                case "Tester":
                    newEmployeeWage = testerWage;
                    break;

                case "Developer":
                    newEmployeeWage = developerWage;
                    break;
            }

            totalWages = (assignedAssistantManagers * assistantManagerWage) + (assignedTesters * testerWage) + (assignedDevelopers * developerWage) + newEmployeeWage;
            if(totalWages > 2100.00){
                JOptionPane.showMessageDialog(null, firstNameTextField.getText() + " " + lastNameTextField.getText() + " cannot be added. Doing so would exceed the department's budget of $2100.00.");
            }
            else{
                employees.add(new Employee(firstNameTextField.getText(), lastNameTextField.getText(), titleComboBox.getValue().toString()));
                SumEmployees();
                SumWages();
                firstNameTextField.clear();
                lastNameTextField.clear();
                titleComboBox.setValue("");
            }
        }
    }

    public void RemoveEmployee(){
        ObservableList<Employee> selectedEmployees;
        selectedEmployees = employeeTableView.getSelectionModel().getSelectedItems();
        selectedEmployees.forEach(employees::remove);

        SumEmployees();
        SumWages();
    }

    public void SumEmployees(){

        assignedAssistantManagers = 0;
        assignedTesters = 0;
        assignedDevelopers = 0;
        assignedTotal = 0;

        employees.forEach(employee -> {
            if(employee.getTitle() == "Assistant Manager"){
                assignedAssistantManagers++;
            }
            assignedAManagersLabel.setText(Integer.toString(assignedAssistantManagers));
        });

        employees.forEach(employee -> {
            if(employee.getTitle() == "QA Tester"){
                assignedTesters++;
            }
            assignedQATestersLabel.setText(Integer.toString(assignedTesters));
        });

        employees.forEach(employee -> {
            if(employee.getTitle() == "Developer"){
                assignedDevelopers++;
            }
            assignedDevelopersLabel.setText(Integer.toString(assignedDevelopers));
        });

        if(assignedTotal == 0){
            assignedAManagersLabel.setText("0");
            assignedQATestersLabel.setText("0");
            assignedDevelopersLabel.setText("0");
        }
    }

    public static DecimalFormat decimalFormat = new DecimalFormat("0.00");

    public void SumWages(){

        totalAssistantManagerWages = assignedAssistantManagers * assistantManagerWage;
        wagesAManagersLabel.setText("$" + decimalFormat.format(totalAssistantManagerWages));

        totalTesterWages = assignedTesters * testerWage;
        wagesQATestersLabel.setText("$" + decimalFormat.format(totalTesterWages));

        totalDeveloperWages = assignedDevelopers * developerWage;
        wagesDevelopersLabel.setText("$" + decimalFormat.format(totalDeveloperWages));

        totalWages = totalAssistantManagerWages + totalTesterWages + totalDeveloperWages;
        wagesTotalLabel.setText("$" + decimalFormat.format(totalWages));
    }
}