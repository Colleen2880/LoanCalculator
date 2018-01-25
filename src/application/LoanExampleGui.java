package application;


import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class LoanExampleGui extends Application {


	//We create the textfields to enter the loan amt, years, and textarea that will hold our table
	TextField tfLoanAmt = new TextField();
	TextField tfYears = new TextField();
	TextArea taTable = new TextArea();
	
	
	
	@Override
	public void start(Stage primaryStage) {
		
		//we create a horizontal box, set padding, spacing. 
		HBox hbox = new HBox(5);
		hbox.setSpacing(10);
		hbox.setPadding(new Insets(4,4,4,4));
		hbox.setStyle("-fx-background-color: silver");
		
		
		
		
		
		//we create our labels for our textfields
		Label lbAmt = new Label("Loan Amount", tfLoanAmt);
		lbAmt.setContentDisplay(ContentDisplay.RIGHT);
		lbAmt.setStyle("-fx-text-fill:blue");
		lbAmt.setFont(Font.font("Times", FontWeight.BOLD, 18));
		
		Label lbYears = new Label("Numbers of Years ", tfYears);
		lbYears.setContentDisplay(ContentDisplay.RIGHT);
		lbYears.setStyle("-fx-text-fill:blue");
		lbYears.setFont(Font.font("Times", FontWeight.BOLD, 18));
		
		
		//we create our button, and add our labels and button to the hbox
		Button btShowTable = new Button("Show Table");
		btShowTable.setStyle("-fx-text-fill:blue");
		hbox.getChildren().addAll(lbAmt, lbYears);
		hbox.getChildren().add(btShowTable);
		
		//we create our columns and rows for the text area, and a scrollpane
		taTable.setPrefColumnCount(3);
		taTable.setPrefRowCount(15);
		ScrollPane scroll = new ScrollPane(taTable);
		
		//we create a borderpane
		BorderPane borderpane = new BorderPane();
		
		//we set our hbox to the top of the borderpane, and set the text area to the bottom of the borderpane
		borderpane.setTop(hbox);
		borderpane.setBottom(taTable);
		
		//we create a set on action to Handler class
		btShowTable.setOnAction(new Handler());
		
		//we set the scene
		Scene scene = new Scene(borderpane,725,300);
		primaryStage.setTitle("Loan Calculator");
		primaryStage.setScene(scene);
		primaryStage.show();
		
		
	}

//we create a Handler class the implements EventHandler
class Handler implements EventHandler<ActionEvent> {

	//we create a handle method
	@Override
	public void handle(ActionEvent arg0) {
		
		//we call newnums method
		newnums();
		
	}

	//we create the method newnums
	private void newnums() {
	
	
	//we get text inputed in loanamt, and set it to double parse double
	double loanamt = Double.parseDouble(tfLoanAmt.getText());
	//we get text inputed into years text and times it to 12
	int yrs = Integer.parseInt(tfYears.getText()) *12;
	//we create a variable set to 5
	double r = 5.00;
	
	//we create a text color, and font, and font size
	taTable.setStyle("-fx-text-fill:blue");
	

	taTable.setFont(Font.font("Times", 16));
	
	//we create a string to print out the titles for the columns
	String s1 = String.format("Interest Rate\t\tMonthly Payment\t\tTotal Payment\n\n");
	
	//we insert string s1 at index 0
	taTable.insertText(0, s1);
	
	// we create a loop, starting at 5, and stops at 8, and increases at 0.125
	for(double rrate = 5.00; rrate <=8.00; rrate = rrate+0.125){
		
		//we assign our equations to calculate loans to variables
		double interest = (((r)*(0.01))/12);
		double month = loanamt* (((Math.pow((interest+1), (yrs))*interest))/ ((Math.pow((interest+1), yrs))-1));
		double total = (month)*(yrs);
		
		//we create a string, using format the output
		String line = String.format("%2.3f\t\t\t$%,3.2f\t\t\t\t$%,5.2f\n", r, month, total);
		
		//we set the table to text area
		taTable.setText(taTable.getText() + line);
		//we add 0.125 to r each loop
		r = r+0.125;
	}
		
		}
}

	public static void main(String[] args) {
		launch(args);
	}
}
