package Assignment.Gui;

import Assignment.Main;
import Assignment.Model.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.ChoiceBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 *
 * @author Domi
 */
public class UweAccommodationViewController implements Initializable {
    //extends Application
    ObservableList<String> occupancyStatusList = FXCollections.observableArrayList("Available", "Unavailable");
    ObservableList<String> cleaningStatusList = FXCollections.observableArrayList("Clean","Dirty","Offline");

    public TableView<Product> tableview;

    public TableColumn<Product, Integer> colLeaseNumber;
    public TableColumn<Product, String> colHallName;
    public TableColumn<Product, Integer> colHallNumber;
    public TableColumn<Product, Integer> colRoomNumber;
    public TableColumn<Product, String> colStudentName;
    public TableColumn<Product, String> colOccupancyStatus;
    public TableColumn<Product, String> colCleaningStatus;
    public TableColumn<Product, String> colRoomPrice;
    public TableColumn<Product, String> colRoomDescription;
    public TextField textLeaseNumber;
    public TextField textStudentName;
    public TextField textHallName;
    public TextField textHallNumber;
    public TextField textRoomNum;
    public TextField textRoomPrice;
    public TextField textRoomDes;
    @FXML
    public ChoiceBox occupancyStatusChoiceBox;
    public ChoiceBox cleaningStatusChoiceBox;
    @FXML
    public Button buttonLogout;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cleaningStatusChoiceBox.getItems().addAll(cleaningStatusList);
        occupancyStatusChoiceBox.getItems().addAll(occupancyStatusList);

        colLeaseNumber.setCellValueFactory(new PropertyValueFactory<>("LeaseNumber"));
        colHallName.setCellValueFactory(new PropertyValueFactory<>("HallName"));
        colHallNumber.setCellValueFactory(new PropertyValueFactory<>("HallNumber"));
        colRoomNumber.setCellValueFactory(new PropertyValueFactory<>("RoomNumber"));
        colStudentName.setCellValueFactory(new PropertyValueFactory<>("StudentName"));
        colOccupancyStatus.setCellValueFactory(new PropertyValueFactory<>("OccupancyStatus"));
        colCleaningStatus.setCellValueFactory(new PropertyValueFactory<>("CleaningStatus"));
        colRoomPrice.setCellValueFactory(new PropertyValueFactory<>("RoomPrice"));
        colRoomDescription.setCellValueFactory(new PropertyValueFactory<>("RoomDescription"));
        tableview.setItems(observableList);

        tableview.setEditable(true);
        colOccupancyStatus.setCellFactory(ChoiceBoxTableCell.forTableColumn());
        colCleaningStatus.setCellFactory(ChoiceBoxTableCell.forTableColumn());

        tableview.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                selectedNewTableRow(newSelection);
            }
        });

    }

    public void selectedNewTableRow(Product row){
        textLeaseNumber.setText(String.valueOf(row.getLeaseNumber()));
        textHallName.setText(row.getHallName());
        textHallNumber.setText(String.valueOf(row.getHallNumber()));
        textRoomNum.setText(String.valueOf(row.getRoomNumber()));
        textStudentName.setText(row.getStudentName());
        occupancyStatusChoiceBox.setValue(row.getOccupancyStatus());
        cleaningStatusChoiceBox.setValue(row.getCleaningStatus());
        textRoomPrice.setText(String.valueOf(row.getRoomPrice()));
        textRoomDes.setText(row.getRoomDescription());

    }

    ObservableList<Product> observableList = FXCollections.observableArrayList(
            new Product(1, "WallCourt", 2, 1, "Albert Bielecki",
                    "Occupied", "Clean", 500, "A single room with a bed, wardrobe" +
                    "and a desk and chair"),
            new Product(2,"WallCourt",2,2,"Tom Smith",
                    "Occupied","Clean",550,"A single room with a bed" +
                    "desk and chair and a window view"),
            new Product(3,"WallCourt",2,3,"Kate Jones",
                    "Occupied","Clean",500,"A single room with a bed, wardrobe" +
                    "and a desk and chair"),
            new Product(0,"WallCourt", 2,3,"","Unoccupied","Offline",500,"A single room with a bed, wardrobe" +
            "and a desk and chair")
    );

    public void buttonApply(ActionEvent actionEvent) {
        Product product = new Product(Integer.parseInt(textLeaseNumber.getText()),textHallName.getText(),Integer.parseInt(textHallNumber.getText()),
                Integer.parseInt(textRoomNum.getText()),textStudentName.getText(),occupancyStatusChoiceBox.getValue().toString(),cleaningStatusChoiceBox.getValue().toString(),
                Integer.parseInt(textRoomPrice.getText()),textRoomDes.getText());
        tableview.getItems().add(product);

    }
    public void logoutButton(ActionEvent event)throws IOException {
        Main m = new Main();
        m.changeScene("LoginPage.fxml");
    }
    public void onEditChanged(TableColumn.CellEditEvent<Product, String> productStringCellEditEvent) {
        Product product = tableview.getSelectionModel().getSelectedItem();
        product.setCleaningStatus(productStringCellEditEvent.getNewValue());
        product.setOccupancyStatus(productStringCellEditEvent.getNewValue());

    }

}
