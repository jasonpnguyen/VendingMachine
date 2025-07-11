package application;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.layout.BorderPane;
import javafx.geometry.Pos;

/**
 *  A BorderPane Demo
 */

public class BorderPaneDemo extends Application
{
   public static void main(String[] args)
   {
      // Launch the application.
      launch(args);
   }
   
   @Override
   public void start(Stage primaryStage)
   {
      Button centerButton = new Button("This is Center");
      Button topButton = new Button("This is Top");
      Button bottomButton = new Button("This is Bottom");
      Button leftButton = new Button("This is Left");
      Button rightButton = new Button("This is Right");
      
      HBox centerHBox = new HBox(centerButton);
      HBox topHBox = new HBox(topButton);
      HBox bottomHBox = new HBox(bottomButton);
      VBox leftVBox = new VBox(leftButton);
      VBox rightVBox = new VBox(rightButton);

      topHBox.setAlignment(Pos.CENTER);
      bottomHBox.setAlignment(Pos.CENTER);
      
      BorderPane borderPane = 
         new BorderPane(centerHBox, topHBox, rightVBox,
                        bottomHBox, leftVBox);

      Scene scene = new Scene(borderPane);

      primaryStage.setScene(scene);

      primaryStage.show();
   }
}