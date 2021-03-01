package sample;
import java.io.Closeable;
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


public class Main extends Application implements EventHandler<ActionEvent> {
    Button Searchbutton;
    Button SortButton;
    Button SearchLinear;
    Button SearchBinary;
    Button CloseButton;
    Button AlertButton;
    Scene SearchScene;
    Scene StartScene;
    Scene SortScene;

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Search & Sort");
        primaryStage.setOnCloseRequest(e -> closeProgram());

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
        StartScene = new Scene(MainScreen, 600, 300);



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
        SearchLinear= new Button("Search With Linear");
        SearchBinary = new Button("Search With Binary");
        SButtons.setAlignment(Pos.CENTER);
        SButtons.setPadding(new Insets(15, 12, 15, 12));
        SButtons.getChildren().addAll(SearchLinear,SearchBinary);
        Input1.getChildren().addAll(InputValS,SValInput);
        Input2.getChildren().addAll(InputValSea,SeaValInput);
        Input1.setAlignment(Pos.CENTER);
        Input2.setAlignment(Pos.CENTER);
        SearchLinear.setOnAction((e) -> {
                System.out.println(SValInput.getText());
                primaryStage.setScene(StartScene);
        });
        SearchMain.getChildren().addAll(Title,Input1,Input2,SButtons);
        SearchScene = new Scene(SearchMain, 600, 300);
        primaryStage.setScene(StartScene);
        primaryStage.show();
    }

    private void closeProgram() {
        System.out.println("File Save Here");
    }

    @Override
    public void handle(ActionEvent event) {
        }

    public static void main(String[] args) {
        ArrayList<Integer> namelist = new ArrayList<>();
        ArrayList<Integer> Locationlist = new ArrayList<>();
        int BV = 0;
        String[] BubbleArray;
        int[] SortAlg;
        int[] SortAlg2;
        String[] array = takingarrayinput(); //Array To Be Searched Input
        var size = array.length; //Find How Many Values are currently stored within the array
        Scanner sc = new Scanner(System.in); //Initalises the scanner
        System.out.println("Enter the value which is being searched for: ");
        String newvalue = sc.nextLine();  //Input Search Algorithim;
        Locationlist = binarysearch(array,newvalue);
        /*
        try {
            Integer [] intarray=new Integer[array.length];
            int i=0;
            for(String str:array){
                intarray[i]=Integer.parseInt(str);//Exception in this line
                i++;
            }
            Integer[] BubbleInt= new Integer[array.length];
            BubbleInt= BubbSort(intarray);
            System.out.println("Sorted Array" + Arrays.toString(BubbleInt));
        } catch(Exception e) {
            BubbleArray = BubbSort(array);
            System.out.println("Sorted Array" + Arrays.toString(BubbleArray));
        }

         */
        int number = Locationlist.size(); //Finds the length of the ArrayList
       // System.out.println("Length Of Location"+number);
        switch (number) { //Using Case Statements for each particular situations
            case 1: // When Only One Prints 1 Locations
                System.out.println("Value being searched for is in location " + (Locationlist.get(0) + 1));
                break;
            case 0: // When location length is zero, no value had been assigned to the
                System.out.println("Value Cannot be found");
                break;
            default: //If Value is not 1 or 0, it must be a multiple occurance situation
                System.out.println("Value Found At Multiple Locations");
                for (int j = 0; j <= (number - 1); j++) { //So i need to print each location using a for loop
                    System.out.println("Location" + (Locationlist.get(j) + 1)); //Printing Each Location
                }
                break;
        }
/*
        int array2[] = {3, 60, 35, 2, 45, 320, 5};
        SortAlg = BubbSort(array2);
        System.out.println(Arrays.toString(SortAlg));
        int array3[] = {3, 60, 35, 2, 45, 320, 5};
        SortAlg2 = InsertSort(array3);
        System.out.println(Arrays.toString(SortAlg2));

 */
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

    public static <T extends Comparable<T>> ArrayList binarysearch(T[] array, T searchvalue) {
        ArrayList<Integer> BinaryLocations = new ArrayList<>(); //Initalises the ArrayList
        boolean Found = true; //Sets both boolan values to true
        boolean checknew = true;
        int upperbound = array.length - 1; //Maximum Search Value
        int Arraytop = array.length - 1;
        int midValue = 0;
        int check;
        int lowerbound = 0;
        if (array[0].compareTo(searchvalue)==0) { //If in location 0
            BinaryLocations.add(0);
            midValue = 0;
            Found = !Found;
        }

        while ((Found) && (upperbound != lowerbound) && upperbound>lowerbound) {
            midValue = (upperbound + lowerbound+1) / 2;
            System.out.println(array[midValue]);
            System.out.println(searchvalue);
          try {
              int no= Integer.parseInt(array[midValue]);
              int intSV = Integer.parseInt(searchvalue);
              if (no > intSV) {
                  check = -1;
              }
              if (no == intSV) {
                  check = 0;
              }
              if (no < intSV) {
                  check = 1;
              }
          } catch (Exception e) {
              check = (array[midValue].compareTo(searchvalue));
            }
              if (check == 0) {
                  BinaryLocations.add(midValue);
                  Found = !Found;
              } else {
                  System.out.println("2midValue" + array[midValue]);
                  System.out.println(searchvalue);
                  System.out.println("check" + check);
                  System.out.println(array[midValue]);
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
        boolean fullsort = true;
        while (fullsort) {
            fullsort = false;
            check = 0;
                for (int j = 0; j < (n - 1); j++) {
                    T Test1= arr[j];
                    T Test2= arr[j+1];
                    int no = (Test1.compareTo(Test2));
                    if (no > 0) {
                        T temp = arr[j];
                        arr[j] = arr[j+1];
                        arr[j+1] = temp;
                        check = check +1;
                        fullsort = true;
                    }
                }
        }
        return arr;
    }
/*
    public static <T extends Comparable<T>> T[] InsertSort(T[] arr) {
        int length= arr.length;
        System.out.println(length);
        T sorted = new int[length];
        T unsorted;
        int temporary=0;
        unsorted = arr;
        sorted[0]=unsorted[0];
        for (int i=0; i<(unsorted.length);i++){
            for(int j=1; j < ((unsorted.length)-i); j++){
                (unsorted[j].compareTo(sorted[i])
               if (unsorted[j].compareTo(sorted[i]) {
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
*/
}

