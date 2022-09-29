package restaurantui.ui.restaurant;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import org.springframework.stereotype.Component;



@Component
public class RestauranteController {

    @FXML
    Button cancelar;

    @FXML
    Button guardar;

    @FXML
    TextField nombre;

    @FXML
    TextField telefono;

    @FXML
    TextField direccion;

    @FXML
    void guardarRestaurante(ActionEvent event){
        if (nombre.getText() == null || nombre.getText().equals("") || telefono.getText() == null ||
        telefono.getText().equals("") || direccion.getText() == null || direccion.getText().equals("")){
            showAlert("Faltan datos!", "No ha ingresado todos los datos necesarios");

        }else {
            String nombreR = nombre.getText();
            String telefonoR = telefono.getText();
            String direccionR = direccion.getText();

            try {
                String json = "";
                try {
                    ObjectMapper mapper = new ObjectMapper();
                    ObjectNode rest = mapper.createObjectNode();
                    rest.put("name", nombreR);
                    rest.put("telefono", telefonoR);
                    rest.put("direccion", direccionR);
                    json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(rest);

                } catch (Exception ignored) {
                    ignored.printStackTrace();
                }
                HttpResponse<String> apiResponse = null;

                try {
                    apiResponse = Unirest.post("http://localhost:8080/restaurante").header("Content-Type", "application/json").body(json).asString();
                    showAlert("Exito", "Se ha creado el restaurante correctamente:"+apiResponse.getStatus() + " " + apiResponse.getStatusText()+" "+apiResponse.getBody().toString());
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }


            } catch (Exception e) {
                throw new RuntimeException(e);
            }

        }
    }

    private void showAlert(String title, String contextText) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(contextText);
        alert.showAndWait();
    }

}
