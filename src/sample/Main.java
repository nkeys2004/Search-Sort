package sample;
import java.io.Closeable;
import java.util.Scanner;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Arrays;
import javafx.scene.control.Button;


public class Main extends Application implements EventHandler<ActionEvent> {
    Button button;
    Button button2;
    Button CloseButton;
    Button AlertButton;
    Scene SearchScene;
    Scene scene2;
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Search & Sort");
        Label WelcomeMessage = new Label("Search & Sort");
        button = new Button();
        button.setText("Searching Algorithim");
        button.setOnAction(this);
        StackPane layout= new StackPane();
        layout.getChildren().add(button);
        Scene scene = new Scene(layout,300,250);
        primaryStage.setOnCloseRequest(e -> closeProgram());

        HBox topMenu = new HBox(20);
        Button buttonA = new Button("ButtonA");
        Button buttonB = new Button("ButtonB");
        Button buttonC = new Button("ButtonC");
        topMenu.getChildren().addAll(buttonA,buttonB,buttonC);

        VBox leftMenu = new VBox(20);
        Button buttonD = new Button("ButtonD");
        Button buttonE = new Button("ButtonE");
        Button buttonF = new Button("ButtonF");
        leftMenu.getChildren().addAll(buttonD,buttonE,buttonF);

        BorderPane borderPane = new BorderPane();
        borderPane.setTop(topMenu);
        borderPane.setLeft(leftMenu);

        GridPane grid = new GridPane();
        




        button.setOnAction( e ->primaryStage.setScene(scene2));
        VBox layout1 = new VBox(20);
        layout1.getChildren().addAll(WelcomeMessage,button);
        SearchScene = new Scene(borderPane);
        button2 = new Button();
        button2.setText("Go Back");
        button2.setOnAction( e ->primaryStage.setScene(SearchScene));
        AlertButton =  new Button();
        AlertButton.setText("Begin The Search");
        AlertButton.setOnAction(e -> {
            boolean result = ConfirmBox.display("Title Of The Window","Wow ths alert box is awesome","Test");
            System.out.println(result);
        });
        StackPane layout2 = new StackPane();
        layout2.getChildren().addAll(button2,AlertButton);
        scene2 = new Scene(layout2,600,300);
        primaryStage.setScene(SearchScene);
        primaryStage.show();
    }

    private void closeProgram() {
        System.out.println("File Save Here");
    }
    @Override
    public void handle(ActionEvent event) {
        if(event.getSource()==button) {
            System.out.println("Test");
        }
    }

    public static void main(String[] args) {
        ArrayList<Integer> namelist = new ArrayList<>();
        ArrayList<Integer> Locationlist = new ArrayList<>();
        int BV = 0;
        int[] SortAlg;
        int[] SortAlg2;
        String[] array = takingarrayinput(); //Array To Be Searched Input
        var size = array.length; //Find How Many Values are currently stored within the array
        Scanner sc = new Scanner(System.in); //Initalises the scanner
        System.out.println("Enter the value which is being searched for: ");
        var newvalue = sc.nextLine();  //Input Search Algorithim
        BV = Integer.parseInt(newvalue);
        int[] arr = new int[size];
        for (int i = 0; i < (size); i++) {
            arr[i] = Integer.parseInt(array[i]);
        }
        // Locationlist = binarysearch(arr, BV);
        int number = Locationlist.size(); //Finds the length of the ArrayList
        switch (number) { //Using Case Statements for each particular situations
            case 1: // When Only One Prints 1 Locations
                System.out.println("Value being searched for is in location " + (Locationlist.get(0) + 1));
                break;
            case 0: // When location length is zero, no value had been assigned to the
                System.out.println("Value Cannot be found");
                break;
            default: //If Value is not 1 or 0, it must be a multiple occurence situation
                System.out.println("Value Found At Multiple Locations");
                for (int j = 0; j <= (number - 1); j++) { //So i need to print each location using a for loop
                    System.out.println("Location" + (Locationlist.get(j) + 1)); //Printing Each Location
                }
                break;
        }
        int array2[] = {3, 60, 35, 2, 45, 320, 5};
        SortAlg = BubbSort(array2);
        System.out.println(Arrays.toString(SortAlg));
        int array3[] = {3, 60, 35, 2, 45, 320, 5};
        SortAlg2 = InsertSort(array3);
        System.out.println(Arrays.toString(SortAlg2));
        launch(args);
    }
        /*
        namelist= linearsearch(array,newvalue,size); //Carries Out Linear Search by calling the method
        int number= namelist.size(); //Finds the length of the ArrayList
        switch (number) { //Using Case Statements for each particular situations
            case 1: // When Only One Prints 1 Locations
                System.out.println("Value being searched for is in location "+(namelist.get(0)));
                break;
            case 0: // When location length is zero, no value had been assigned to the
                System.out.println("Value Cannot be found");
                break;
            default: //If Value is not 1 or 0, it must be a multiple occurence situation
                System.out.println("Value Found At Multiple Locations");
                for(int i=0;i<=(number-1);i++) { //So i need to print each location using a for loop
                    System.out.println("Location"+(namelist.get(i))); //Printing Each Location
                }
                break;
         */

    public static String[] takingarrayinput() {
        int n;
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the number of elements you want to store: ");
        n = sc.nextInt();
        String[] array = new String[n]; //Initalises an array of the size the user say they want to store
        System.out.println("Enter the elements of the array: ");
        array[0] = sc.nextLine();
        for (int i = 0; i < n; i++) {
            array[i] = sc.nextLine();
        }
        return array;
    }

    public static ArrayList linearsearch(String array[], String searchvalue, int n) {
        ArrayList<Integer> Locations = new ArrayList<>(); //Initalises the ArrayList
        for (int i = 0; i <= n; i++) {
            if (array[i].equals(searchvalue)) { //Using this equals section
                Locations.add(i); //Adds the index of the value in the Array, into the locations ArrayList
            }
        }
        return Locations; //Returns the locations ArrayList
    }

/*
    public static <T extends Comparable> ArrayList binarysearch(T[] array, T searchvalue) {
        ArrayList<Integer> BinaryLocations = new ArrayList<>(); //Initalises the ArrayList
        boolean Found = true;
        boolean checknew = true;
        int upperbound = array.length-1;
        int Arraytop = array.length-1;
        int lowerbound = 0;
        int i = 0;
        if (array[0] == searchvalue) {
            BinaryLocations.add(0);
            T midValue = 0;
            Found = !Found;
        }

        while ((Found) && (upperbound != lowerbound)) {
            T midValue = (upperbound + lowerbound) / 2;
            if (array[midValue].compareTo(searchvalue)) {
                BinaryLocations.add(midValue);
                Found = !Found;
            } else {
                if (array[midValue] < searchvalue) {
                    lowerbound = midValue+1;
                } else {
                        upperbound = midValue;
                    }
            }
            if (array[Arraytop] == searchvalue) {
                BinaryLocations.add(Arraytop);
                checknew =  !checknew;
                Found = !Found;
            }
        }
            while (checknew) {
                midValue=midValue-1;
                for (int j = midValue; j > lowerbound; j--) {
                    System.out.println(j);
                    if (array[j] == searchvalue) {
                        BinaryLocations.add(j);
                    }
                }
                midValue=midValue+2;
                for (int n = midValue; n <= upperbound; n++) {
                    if (array[n] == searchvalue) {
                        BinaryLocations.add(n);
                    }
                }
                checknew = !checknew;
            }
        return BinaryLocations;
   }
 */
    public static  int[] BubbSort(int[] arr) {
        int n = arr.length;
        for(int i=0; i < n; i++){
            for(int j=1; j < (n-i); j++){
                if(arr[j-1] > arr[j]){
                    int temporary = arr[j-1];
                    arr[j-1] = arr[j];
                    arr[j] = temporary;
                }
            }
        }
        return arr;
    }

    public static int[] InsertSort(int[] arr) {
        int length= arr.length;
        System.out.println(length);
        int[] sorted = new int[length];
        int[] unsorted;
        int temporary=0;
        unsorted = arr;
        sorted[0]=unsorted[0];
        for (int i=0; i<(unsorted.length);i++){
            for(int j=1; j < ((unsorted.length)-i); j++){
               if (unsorted[j]<sorted[i]) {
                   temporary= sorted[i];
                   sorted[i]= unsorted[j];
                   sorted[i+1] = temporary;
               } else {
                   sorted[i+1]= unsorted[j];
               }
            }
        }
        return unsorted;
     }

}


