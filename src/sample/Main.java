package sample;
import java.io.FileWriter;
import java.text.DecimalFormat;
import java.util.Random;
import java.util.Scanner;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.Arrays;
import javafx.scene.control.Button;
import java.io.File;  // Import the File class
import java.io.IOException;  // Import the IOException class to handle errors

public class Main extends Application implements EventHandler<ActionEvent> {
    Button Searchbutton;
    Button SortButton;
    Button SearchLinear;
    Button SearchBinary;
    Button SortBubble;
    Button Compare;
    Button SortIsertion;
    Scene SearchScene;
    Scene StartScene;
    Scene SortScene;

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Search & Sort");

        // Main Home Screen Scene
        VBox MainScreen = new VBox(50);
        MainScreen.setAlignment(Pos.CENTER);
        MainScreen.setPadding(new Insets(15, 12, 15, 12));
        Label WelcomeMessage = new Label("Search & Sort Algorithims");
        Searchbutton = new Button("Searching Algorithims");
        SortButton = new Button("Sorting Algorithims");
        HBox Buttons = new HBox(50);
        Buttons.setAlignment(Pos.CENTER);
        Buttons.setPadding(new Insets(15, 12, 15, 12));
        Buttons.getChildren().addAll(Searchbutton, SortButton);
        MainScreen.getChildren().addAll(WelcomeMessage, Buttons);
        Searchbutton.setOnAction(e -> primaryStage.setScene(SearchScene));
        SortButton.setOnAction(e -> primaryStage.setScene(SortScene));
        StartScene = new Scene(MainScreen, 600, 300);
        StartScene.getStylesheets().add("style.css");


        // Searching Screen Scene
        VBox SearchMain = new VBox(50);
        SearchMain.setAlignment(Pos.CENTER);
        SearchMain.setPadding(new Insets(15, 12, 15, 12));
        Label Title = new Label("Searching");
        HBox SButtons = new HBox(50);
        HBox Input1 = new HBox(50);
        HBox Input2 = new HBox(50);
        Label InputValS = new Label("Input Values Here: ");
        TextField SValInput = new TextField();
        Label InputValSea = new Label("Input SearchValue Here: ");
        TextField SeaValInput = new TextField();
        SearchLinear = new Button("Search With Linear");
        SearchBinary = new Button("Search With Binary");
        SButtons.setAlignment(Pos.CENTER);
        SButtons.setPadding(new Insets(15, 12, 15, 0));
        SButtons.getChildren().addAll(SearchLinear, SearchBinary);
        Input1.getChildren().addAll(InputValS, SValInput);
        Input2.getChildren().addAll(InputValSea, SeaValInput);
        Input1.setAlignment(Pos.CENTER);
        Input2.setAlignment(Pos.CENTER);
        SearchLinear.setOnAction((e) -> {
            String linearLabel;
            System.out.println(SValInput.getText());
            ArrayList<Integer> namelist = new ArrayList<>();
            String string = SValInput.getText();
            String[] ArraySplit = string.split("[\\s,]+"); // split on on one or more spaces or commas
            System.out.println(Arrays.toString(ArraySplit));
            int size = ArraySplit.length - 1;
            String newvalue = SeaValInput.getText();
            namelist = linearsearch(ArraySplit, newvalue, size); //Carries Out Linear Search by calling the method
            int number = namelist.size(); //Finds the length of the ArrayList
            switch (number) { //Using Case Statements for each particular situations
                case 1: // When Only One Prints 1 Locations
                    linearLabel = ("Value being searched for is in location " + (namelist.get(0) + 1));
                    break;
                case 0: // When location length is zero, no value had been assigned to the
                    linearLabel = ("Value Cannot be found");
                    break;
                default: //If Value is not 1 or 0, it must be a multiple occurence situation
                    for(int w=0;(w<=(namelist.size()-1));w++) {
                        namelist.set(w,(namelist.get(w)+1));
                    }
                    linearLabel = ("Values Found At Multiple Locations: Locations" + namelist);
                    break;
            }
            AlertBox.display("Searching Result", linearLabel, "Searching");
            SValInput.clear();
            SeaValInput.clear();
            primaryStage.setScene(StartScene);
        });
        SearchBinary.setOnAction((e) -> {
            System.out.println(SValInput.getText());
            String string = SValInput.getText();
            String[] ArraySplit = string.split("[\\s,]+"); // split on on one or more spaces or commas
            System.out.println(Arrays.toString(ArraySplit));
            int size = ArraySplit.length - 1;
            String newvalue = SeaValInput.getText();
            String binaryLabel;
            ArrayList<Integer> Locationlist = new ArrayList<>();
            Locationlist = binarysearch(ArraySplit, newvalue);
            int number = Locationlist.size(); //Finds the length of the ArrayList
            switch (number) { //Using Case Statements for each particular situations
                case 1: // When Only One Prints 1 Locations
                    binaryLabel = ("Value being searched for is in location " + (Locationlist.get(0)+1));
                    break;
                case 0: // When location length is zero, no value had been assigned to the
                    binaryLabel = ("Value Cannot be found");
                    break;
                default: //If Value is not 1 or 0, it must be a multiple occurance situation
                    for(int w=0;(w<=(Locationlist.size()-1));w++) {
                        Locationlist.set(w,(Locationlist.get(w)+1));
                    }
                    binaryLabel = ("Values Found At Multiple Locations: Locations" + Locationlist);
                    break;
            }
            AlertBox.display("Searching Result", binaryLabel, "Searching");
            SValInput.clear();
            SeaValInput.clear();
            primaryStage.setScene(StartScene);
        });
        SearchMain.getChildren().addAll(Title, Input1, Input2, SButtons);
        SearchScene = new Scene(SearchMain, 600, 300);
        SearchScene.getStylesheets().add("style2.css");

        //Sorting Scene
        VBox SortMain = new VBox(50);
        SortMain.setAlignment(Pos.CENTER);
        SortMain.setPadding(new Insets(15, 12, 15, 12));
        Label TitleSort = new Label("Sorting");
        HBox SortButtons = new HBox(50);
        HBox UnsortInput = new HBox(50);
        Label InputValSort = new Label("Input Values Here: ");
        TextField SortValInput = new TextField();
        SortBubble = new Button("Bubble Sort");
        SortIsertion = new Button("Insertion Sort");
        Compare = new Button("Compare Which is more efficent");
        SortButtons.setAlignment(Pos.CENTER);
        SortButtons.setPadding(new Insets(15, 12, 15, 12));
        SortButtons.getChildren().addAll(SortBubble, SortIsertion);
        UnsortInput.getChildren().addAll(InputValSort, SortValInput);
        UnsortInput.setAlignment(Pos.CENTER);
        SortBubble.setOnAction((e) -> {
            String[] BubbleArray;
            String BubbleLabel;
            System.out.println(SortValInput.getText());
            String string = SortValInput.getText();
            String[] array = string.split("[\\s,]+"); // split on on one or more spaces or commas
            System.out.println(Arrays.toString(array));
            try {
                Double[] intarray = new Double[array.length];
                int i = 0;
                for (String str : array) {
                    intarray[i] = Double.parseDouble(str);//Exception in this line
                    i++;
                }
                BubbleArray = new String[array.length];
                Double[] BubbleInt;
                BubbleInt = BubbSort(intarray);
                DecimalFormat df = new DecimalFormat("0.#");
                int w = 0;
                for (double d : BubbleInt) {
                    BubbleArray[i] = (Double.valueOf(df.format(BubbleInt[i]))).toString();
                    w++;
                }
            } catch (Exception g) {
                BubbleArray = BubbSort(array);
            }
            BubbleLabel = "Sorted Array" + Arrays.toString(BubbleArray);
            AlertBox.display("Searching Result", BubbleLabel, "Searching");
            SortValInput.clear();
            primaryStage.setScene(StartScene);
        });
        SortIsertion.setOnAction((e) -> {
            String InsertLabel;
            System.out.println(SortValInput.getText());
            String string = SortValInput.getText();
            String[] array = string.split("[\\s,]+"); // split on on one or more spaces or commas
            System.out.println(Arrays.toString(array));
            String[] InsertArray= new String[array.length];
            Object[] Returnarray;
            try {
                Double[] Doublearray = new Double[array.length];
                int i = 0;
                for (String str : array) {
                    Doublearray[i] = Double.parseDouble(str);//Exception in this line
                    i++;
                }
                Returnarray=InsertSort(Doublearray);
                DecimalFormat df = new DecimalFormat("0.#######");
                int w = 0;
                for (w = 0; w < (Returnarray.length); w++) {
                    InsertArray[w] = df.format(Double.valueOf((Double) Returnarray[w]).toString());
                }
                System.out.println("Sorted Array" + Arrays.toString(InsertArray));
            } catch (Exception f) {
                InsertArray = InsertSort(array);
                System.out.println("Sorted Array" +Arrays.toString(InsertArray));
            }
            InsertLabel = "Sorted Array" + (Arrays.toString(InsertArray));
            AlertBox.display("Sorting Result", InsertLabel, "Sorting");
            SortValInput.clear();
            primaryStage.setScene(StartScene);
        });
        Compare.setOnAction((e) -> {
            String Output;
            System.out.println(SortValInput.getText());
            String string = SortValInput.getText();
            String[] array = string.split("[\\s,]+"); // split on on one or more spaces or commas
            System.out.println(Arrays.toString(array));
            try {
                Double[] intarray = new Double[array.length];
                int i = 0;
                for (String str : array) {
                    intarray[i] = Double.parseDouble(str);//Exception in this line
                    i++;
                }
                Output=Compare(intarray,intarray);
            } catch(Exception g) {
                Output=Compare(array,array);
            }
            AlertBox.display("Effiency Test",Output,"Searching");
            SortValInput.clear();
            primaryStage.setScene(StartScene);
        });
        SortMain.getChildren().addAll(TitleSort, UnsortInput, SortButtons, Compare);
        SortScene = new Scene(SortMain, 600, 300);
        SortScene.getStylesheets().add("style2.css");
        primaryStage.setOnCloseRequest(e -> closeProgram(SeaValInput,SortValInput));
        primaryStage.setScene(StartScene);
        primaryStage.show();
    }

    private void closeProgram(TextField option1, TextField option2) {
        System.out.println("File Save Here");
        try {
            Random rand = new Random();
            String filename = "Random"+rand+".txt";
            FileWriter myWriter = new FileWriter(filename);
            String write1=option1.toString();
            String write2=option2.toString();
            myWriter.write(write1);
            myWriter.write(write2);
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        }

    @Override
    public void handle(ActionEvent event) {
    }

    public static void main(String[] args) {
        launch(args);
    }

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

    public static ArrayList linearsearch(String[] array, String searchvalue, int n) {
        ArrayList<Integer> Locations = new ArrayList<>(); //Initalises the ArrayList
        for (int i = 0; i <= n; i++) {
            if (array[i].equals(searchvalue)) { //Using this equals section
                Locations.add(i); //Adds the index of the value in the Array, into the locations ArrayList
            }
        }
        return Locations; //Returns the locations ArrayList
    }

    public static <T extends Comparable<T>> ArrayList binarysearch(T[] array, T searchvalue) {
        ArrayList<Integer> BinaryLocations = new ArrayList<>(); //Initalises the ArrayList
        boolean Found = true; //Sets both boolan values to true
        boolean checknew = true;
        int upperbound = array.length - 1; //Maximum Search Value
        int Arraytop = array.length - 1;
        int midValue = 0;
        int check = 0;
        int lowerbound = 0;
        if (array[0].compareTo(searchvalue) == 0) { //If in location 0
            BinaryLocations.add(0);
            midValue = 0;
            Found = !Found;
        }
        while ((Found) && (upperbound != lowerbound) && upperbound > lowerbound) {
            midValue = (upperbound + lowerbound + 1) / 2;
            try {
                int no = Integer.parseInt((String) array[midValue]);
                int intSV = Integer.parseInt((String) searchvalue);
                if (no > intSV) {
                    check = 1;
                }
                if (no == intSV) {
                    check = 0;
                }
                if (no < intSV) {
                    check = -1;
                }
            } catch (Exception e) {
                check = (array[midValue].compareTo(searchvalue));
            }

            if (check == 0) {
                BinaryLocations.add(midValue);
                Found = !Found;
            } else {

                if (check < 0) { //If greater than zero, value being searched for is in first half
                    lowerbound = midValue + 1;
                } else { //Would be greater than zero
                    upperbound = midValue - 1;
                }
                if (array[Arraytop].compareTo(searchvalue) == 0) {
                    BinaryLocations.add(Arraytop);
                    Found = !Found;
                    checknew = !checknew;
                }
            }

        }
        while (checknew) {
            for (int j = midValue - 1; j >= lowerbound; j--) { //Checks all values less than first found
                if (array[j].compareTo(searchvalue) == 0) {
                    BinaryLocations.add(j);
                }
            }
            midValue = midValue + 1;
            for (int n = midValue; n <= upperbound; n++) { //Checks all values greater than first found
                if (array[n].compareTo(searchvalue) == 0) {
                    BinaryLocations.add(n);
                }
            }
            checknew = false;
        }
        return BinaryLocations;
    }

    public static <T extends Comparable<T>> T[] BubbSort(T[] arr) {
        int n = arr.length;
        int check = 0;
        int no = 0;
        boolean fullsort = true;
        while (fullsort) {
            fullsort = false;
            check = 0;
            for (int j = 0; j < (n - 1); j++) {
                T Test1 = arr[j];
                T Test2 = arr[j + 1];
                try {
                    Double Val2 = Double.parseDouble((String) Test2);
                    Double Val1 = Double.parseDouble((String) Test1);
                    if (Val2 < Val1) {
                        no = 1;
                    } else {
                        no = -1;
                    }
                } catch (Exception e) {
                    no = (Test1.compareTo(Test2));
                }

                if (no > 0) {
                    T temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    check = check + 1;
                    fullsort = true;
                }
            }
        }
        return arr;
    }

    public static <T extends Comparable<T>> T[] InsertSort(T[] array) {
        for (int i = 1; i < array.length; i++) {
            int currentIndex = i;
            try {
                Double val1 = Double.parseDouble((String)array[currentIndex-1]);
                Double val2= Double.parseDouble((String)array[currentIndex]);
                while (currentIndex >= 0 && val1.compareTo(val2) > 0) {
                    T temp = array[currentIndex];
                    array[currentIndex] = array[currentIndex - 1];
                    array[currentIndex - 1] = temp; //Pushes value in current location up one place
                    currentIndex--;
                    val1 = Double.parseDouble((String)array[currentIndex-1]);
                    val2= Double.parseDouble((String)array[currentIndex]);
                }
            } catch(Exception e) {
                while (currentIndex > 0 && array[currentIndex - 1].compareTo(array[currentIndex]) > 0) {
                    T temp = array[currentIndex];
                    array[currentIndex] = array[currentIndex - 1];
                    array[currentIndex - 1] = temp; //Pushes value in current location up one place
                    currentIndex--;
                }
            }
        }
        return array;
    }

    public static <T extends Comparable<T>> String Compare(T[]Array,  T[]Array2) {
        long CompleteB;
        long CompleteA;
        String message = "";
        long startTime = System.nanoTime(); //Starts Timer
        Array = BubbSort(Array);
        long endTime = System.nanoTime(); // Ends Timer
        CompleteA = endTime-startTime; //Find Bubble Sort time taken
        long secondST = System.nanoTime(); //Starts Timer
        Array2 = InsertSort(Array2); //Gives Copy of unsorted array
        long secondET = System.nanoTime(); //End Timer
        CompleteB= secondET-secondST; // Finds Total Time for Insertion Sort
        if (CompleteB > CompleteA) { //Outputs Message dependant on faster
            message = ("Bubble Sort Preformed Quicker at at Time of "+CompleteA+" microseconds");
        } else {
            if (CompleteA==CompleteB) {
                message= ("Both Completed In Same Time "+CompleteA+" microseconds");
            }
            if (CompleteB < CompleteA) {
                message = ("Insertion Sort Preformed Quicker at at Time of " + CompleteB + " microseconds");
            }
        }
        return message;
    }

}

