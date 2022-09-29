package restaurantui.ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import restaurantui.Main;
import restaurantui.ui.restaurant.RestauranteController;



public class JavaFXApplication extends Application  {

    private Parent root;

    @Override
    public void init() throws Exception {

    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(Main.getContext()::getBean);
        root = fxmlLoader.load(RestauranteController.class.getResourceAsStream("CrearRestaurante.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Crear Restaurante");
        primaryStage.show();
    }

   @Override
    public void stop() {
        Main.getContext().close();
    }

}
