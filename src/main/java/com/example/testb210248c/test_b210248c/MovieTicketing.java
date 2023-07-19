package com.example.testb210248c.test_b210248c;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class MovieTicketing extends Application {

    private ComboBox<String> cbExperience;
    private TextField textMovie;
    private ToggleGroup tgSession;
    private ToggleGroup tgpopcorn;
    private TextField textSeats;
    private String[] fbArray = {"Royal Popcorn Combo - Member Special", "Royal Popcorn", "Royal Popcorn Combo"};
    private double[] foodBeveragePrice = {19.9,17.9,21.9};
    private double[] sessionPrice = {19.9,15.9,23.9,23.9,25.9,25.9,120,120,15.9,89.9};

    @Override
    public void start(Stage stage) throws IOException {
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(10));
        gridPane.setHgap(8);
        gridPane.setVgap(12);

        //--------------------------------------------------------------------------------------
        //Movie name

        Label labelMovie = new Label("Movie");
        textMovie = new TextField();
        textMovie.setPromptText("Enter the movie");
        gridPane.add(labelMovie, 0,0,1,1);
        gridPane.add(textMovie, 1,0,1,1);

        //------------------------------------------------------------------------------------
        //Experience

        Label labelExperience = new Label("Experience");
        cbExperience = new ComboBox<>();
        cbExperience.setPromptText("Choose an experience");
        cbExperience.getItems().addAll("Beanie", "Classic", "Deluxe", "Family-Friendly", "Flexound", "IMAX", "Indulge", "Infinity", "Junior", "Onyx");
        cbExperience.setMinWidth(250);
        gridPane.add(labelExperience, 0,1,1,1);
        gridPane.add(cbExperience, 1,1,1,1);

        //-------------------------------------------------------------------------------------
        //Session

        Label labelSession = new Label("Session");

        VBox vbSessionChoice = new VBox();

        RadioButton time11am = new RadioButton("11:00 AM");
        RadioButton time1330pm = new RadioButton("01:30 PM");
        RadioButton time1600pm = new RadioButton("04:00 PM");
        RadioButton time1830pm = new RadioButton("06:30 PM");
        RadioButton time2100pm = new RadioButton("09:00 PM");
        time11am.setUserData("11:00 AM");
        time1330pm.setUserData("01:30 PM");
        time1600pm.setUserData("04:00 PM");
        time1830pm.setUserData("06:30 PM");
        time2100pm.setUserData("09:00 PM");
        tgSession = new ToggleGroup();
        time11am.setToggleGroup(tgSession);
        time1330pm.setToggleGroup(tgSession);
        time1600pm.setToggleGroup(tgSession);
        time1830pm.setToggleGroup(tgSession);
        time2100pm.setToggleGroup(tgSession);


        vbSessionChoice.getChildren().addAll(time11am,time1330pm,time1600pm,time1830pm,time2100pm);
        vbSessionChoice.setSpacing(5);
        gridPane.add(labelSession, 0,2,1,1);
        gridPane.add(vbSessionChoice, 1,2,1,1);

        //-----------------------------------------------------------------------------------------------
        //Seats

        Label labelSeats = new Label("Seats");
        textSeats = new TextField();
        textSeats.setPromptText("Enter the seats, use comma for next seat. Example: A3, A6");
        gridPane.add(labelSeats, 0,3,1,1);
        gridPane.add(textSeats, 1,3,1,1);

        //------------------------------------------------------------------------------------------------
        //Food and Beverage

        Label labelFoodAndBeverage = new Label("Food & Beverages");

        int col = 0;
        String[] fbPrice = {"RM 19.90", "RM 17.90", "RM 21.90"};

        RadioButton popcorn;
        tgpopcorn = new ToggleGroup();
        HBox hb = new HBox();
        GridPane fbGrid = new GridPane();
        fbGrid.setHgap(12);

        //for loop each food and beverage including their picture, name, and price
        for(int i=1; i < 4; i++) {

            VBox vbFB = new VBox();
            vbFB.setSpacing(12);

            Image image = new Image(MovieTicketing.class.getResource("popcorn"+i+".png").toString());
            ImageView imageView = new ImageView(image);
            imageView.setFitHeight(155);
            imageView.setFitWidth(215);
            Text textPopcorn = new Text(fbArray[i-1]+"");
            popcorn = new RadioButton(fbPrice[i-1]+"");
            popcorn.setToggleGroup(tgpopcorn);
            popcorn.setUserData(fbArray[i-1]+"");

            vbFB.getChildren().addAll(imageView,textPopcorn,popcorn);
            fbGrid.add(vbFB, col,0,1,1);

            col++;
        }

        hb.getChildren().add(fbGrid);
        gridPane.add(labelFoodAndBeverage, 0,4,1,1);
        gridPane.add(hb, 1,4,1,1);

        //-------------------------------------------------------------------------------------------------
        //Submit button

        Button btnSubmit = new Button("Submit");
        btnSubmit.setPrefWidth(100);
        HBox buttonSubmit = new HBox(btnSubmit);
        buttonSubmit.setAlignment(Pos.BASELINE_RIGHT);
        gridPane.add(buttonSubmit, 1,5,1,1);

        btnSubmit.setOnAction(e -> checkDetail());

        //--------------------------------------------------------------------------------------------------

        Scene scene = new Scene(gridPane);
        stage.setTitle("Movie Ticketing System");
        stage.setScene(scene);
        stage.show();
    }

    //validate that all text fields, combo boxes, and radio buttons are filled and selected appropriately
    public void checkDetail(){

        String validateTextMovie = textMovie.getText();
        String validateCB = cbExperience.getValue();
        String validateTextSeat = textSeats.getText();
        String seatPattern = "[A-Z][0-9]";
        String message = "";
        String submitConfirm = "";
        Boolean validate = false;

        try{
            //validate the data fields
            if(validateTextMovie.isEmpty() || validateCB == null || tgSession.getSelectedToggle() == null || validateTextSeat.isEmpty()|| validateTextSeat.contains(",") || !validateTextSeat.isEmpty()){

                //no Movie
                if(validateTextMovie.isEmpty()){
                    message += "Please enter the movie.\n";
                    validate = true;
                }

                //no select combo box experience
                if(validateCB == null){
                    message += "Please choose your experience.\n";
                    validate = true;
                }

                //no select radio button session
                if(tgSession.getSelectedToggle() == null){
                    message += "Please choose the session.\n";
                    validate = true;
                }

                //validate seats field
                if(validateTextSeat.isEmpty()|| validateTextSeat.contains(",") || !validateTextSeat.isEmpty()){

                    Boolean check = false;

                    //seats field is empty
                    if(validateTextSeat.isEmpty()){
                        message += "Please enter the seats.\n";
                        validate = true;
                    }

                    //seats are not null and length>2
                    if(!validateTextSeat.isEmpty() && validateTextSeat.length() > 2){

                        //seats field not contain "," but length>2
                        if(!validateTextSeat.contains(",")&& validateTextSeat.length() >2) {
                            message += "Please follow the correct pattern.([A-Z][0-9]).\n";
                            validate = true;
                        }

                        //seats field not contain "," but length <= 3
                        if(validateTextSeat.contains(",")&& validateTextSeat.length() < 4) {
                            message += "Please follow the correct pattern.([A-Z][0-9]).\n";
                            validate = true;
                        }else if (validateTextSeat.contains(",") && validateTextSeat.length() >3) {
                            String[] perseats = validateTextSeat.split(",");
                            for (String perseat : perseats) {
                                if (!perseat.trim().matches(seatPattern)) {
                                    check = true;
                                }
                            }
                            if(check){
                                validate = true;
                                message += "Please enter the right pattern before comma. ([A-Z][0-9]).\n";
                            }
                        }
                    }

                    //seats field not and length <=2
                    if(!validateTextSeat.isEmpty() && validateTextSeat.length() < 3){
                        if (!validateTextSeat.matches(seatPattern)) {
                            message += "Please enter the right pattern. ([A-Z][0-9]).\n";
                            validate = true;
                        }
                    }
                }

                //popcorn radio button not selected
                if(tgpopcorn.getSelectedToggle() == null){
                    message += "Please choose any one of food and beverage.\n";
                    validate = true;
                }

                //if any error occurs, throw exception else proceed to confirmation
                if( validate == true){
                    throw new Exception();
                }else{
                    int countSeat = 0;
                    double totalPrice = 0.0;

                    //calculate experience price
                    int cbIndex = cbExperience.getSelectionModel().getSelectedIndex();
                    totalPrice += sessionPrice[cbIndex];

                    //count how many person
                    String[] perseats = validateTextSeat.split(",");
                    for (String perseat : perseats) {
                        countSeat++;
                    }
                    totalPrice *= countSeat;

                    //calculate popcorn price
                    if(tgpopcorn.getSelectedToggle().getUserData().toString().equals(fbArray[0])){
                        totalPrice += foodBeveragePrice[0];
                    } else if (tgpopcorn.getSelectedToggle().getUserData().toString().equals(fbArray[1])) {
                        totalPrice += foodBeveragePrice[1];
                    } else if (tgpopcorn.getSelectedToggle().getUserData().toString().equals(fbArray[2])) {
                        totalPrice += foodBeveragePrice[2];
                    }

                    //String message for calculation
                    submitConfirm = "You selected " + validateTextMovie + " with " +validateCB+ " experience at " +tgSession.getSelectedToggle().getUserData().toString()+ " for " +countSeat+ " seat(s) and a " +tgpopcorn.getSelectedToggle().getUserData().toString()+ ".\nThe total is RM "+String.format("%.2f",totalPrice)+".";
                    showConfirmMessage(submitConfirm);
                }
            }
        }catch(Exception ex){
            showErrorMessage(message);
        }
    }

    //WARNING
    public void showErrorMessage(String message){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning!");
        alert.setContentText(message);
        alert.showAndWait();
    }

    //CONFIRMATION
    public void showConfirmMessage(String message){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Thank You!");
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch();
    }


}

