import java.io.FileReader;
import java.io.BufferedReader;
import java.util.Scanner;

public class MotorPHEmployee {

	
	// global variables
		public static String[] employee;
		public static String msalary;
		public static String hsalary;
		public static String userInput;
		public static String file = System.getProperty("user.dir") + "/EmployeeDetails.csv";
		
		
	public static void main(String[] args) {

		resetData();
		getUserInput();
		getEmpDetails();
		
		main(args); // allows repeated sessions
	}
	
	
	public static void resetData() { // sets data to null after each repetition, avoid incorrect values
		employee = null;
		msalary = null;
		hsalary = null;
		userInput = null;
	}
	
	public static String sanitizeData(String info) { // Cleans up the read csv data to prevent bugs
		return info.replace(";x;", ",");
	}
	
	
	public static void getUserInput() { // defines user input to search for
		Scanner input = new Scanner(System.in);
		
		System.out.println("===Start===\n");
		System.out.println("Enter Employee Number:");
		userInput = input.next();
	}
	
	
	public static void getEmpDetails() { // for displaying employee details
		
		BufferedReader reader = null;
		
		String line = "";
		boolean found = false;

		try {
			reader = new BufferedReader(new FileReader(file));
			
			while((line = reader.readLine()) != null) {
				
				String repl = line.replaceAll(",(?!(([^\"]*\"){2})*[^\"]*$)", ";x;");
				employee = repl.split(",");
				
				if (employee[0].equalsIgnoreCase(userInput)) {
					found = true;
					
					if (found = true) { // Displays employee info and salary calculations
						System.out.println("Employee Name: "+employee[1]+", "+employee[2]);
						System.out.println("Birthday: "+employee[3]);
						System.out.println("Address: "+sanitizeData(employee[4]));
						
						System.out.println("\n===CALCULATIONS===");
						
						msalary = employee[13].replace(";x;", "").replace("\"", "");
						hsalary = employee[18].replace(";x;", "");
						
						double msalaryd = Double.parseDouble(msalary); // Parses the salary strings...
						double hsalaryd = Double.parseDouble(hsalary); // into doubles for calculation

							System.out.println("Monthly Salary: "+msalaryd);
							System.out.println("Hourly Salary: "+hsalaryd);
							System.out.println();
							
						double sssDeduction = 1125;
						double philhealthDeduction = (msalaryd*0.03);
						double pagibigDeduction = 100;
						
							System.out.println("SSS Deduction: "+sssDeduction);
							System.out.println("Philhealth Deduction: "+philhealthDeduction);
							System.out.println("Pag-ibig Deduction: "+pagibigDeduction);
							System.out.println();
							
						System.out.println("Net Salary: "+(msalaryd-sssDeduction-philhealthDeduction-pagibigDeduction));
						
						System.out.println("===End===\n");
						System.out.println("================================\n");

					}
				
				}
				
			}
		} catch (Exception e) {e.printStackTrace(); }
				
	}
}
