package helpdesk.controller;

import helpdesk.model.Films;
import helpdesk.model.FilmsDAO;
import helpdesk.model.User;
import helpdesk.utils.Validation;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DashboardController {
    @FXML
    TextField id;
    @FXML
    private TextField message;
    @FXML
    private TextField description;
    @FXML
    private TextField rating;
    @FXML
    private TextField title;
    @FXML
    private TextField category;
    @FXML
    private Label username_label;
    @FXML
    private Label role_label;
    @FXML
    private Button search;
    @FXML
    private Button add;
    @FXML
    private Button delete;
    @FXML
    private Button update;
    @FXML
    private TableView table;
    private ResultSet rsAllEntries;
    private ObservableList<ObservableList> data = FXCollections.observableArrayList();
    // Jei nerodo ikonėlių prie FXML elementų patikrinti ar FXML'e yra nurodytas kontroleris

    public void initialize() {
        this.username_label.setText(User.getInstance().getUsername());
        if (User.getInstance().isAdmin()) {
            this.role_label.setText("Admin");
        } else {
            this.role_label.setText("User");
        }
    }

    /**
     * Funkcija grąžinantį vartotoją į login langą
     */
    public void goToLogin(ActionEvent actionEvent) {
        try {
            // Sukuriamas dashboard langas
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("view/login.fxml"));
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root, 550, 450));
        } catch (IOException e) {
            e.printStackTrace();
        }
    };

    public void onAddAction(ActionEvent actionEvent) {

        String title = "";
        if (Validation.isTitleValid(this.title.getText())) {
            title = this.title.getText();
        } else {
            this.message.setText("Title is not valid");
            return;
        }

        String description = "";
        if (Validation.isDescriptionValid(this.description.getText())) {
            description = this.description.getText();
        } else {
            this.message.setText("Description is not valid");
            return;
        }

        double rating = 0;
        String category = "";
        try {
            FilmsDAO.create(new Films(title, description, rating, category, User.getInstance().getUserID()));
//            this.message.setTextFill(Color.GREEN);
            this.message.setText("Successfully added new entry to database");
        } catch (SQLException throwables) {
            this.message.setText("Failed to add an entry to database");
            throwables.printStackTrace();
        }
    }


    public void onUpdateAction(ActionEvent actionEvent) {
        int id = 0;
        if (Validation.isIdValid(this.id.getText())) {
            id = Integer.parseInt(this.id.getText());
        } else {
            this.message.setText("Id is not valid");
        }

        String title = "";
        if (Validation.isTitleValid(this.title.getText())) {
            title = this.title.getText();
        } else {
            this.message.setText("Title is not valid");
            return;
        }

        String description = "";
        if (Validation.isDescriptionValid(this.description.getText())) {
            description = this.description.getText();
        } else {
            this.message.setText("Description is not valid");
            return;
        }

        double rating = 0;
        if (Validation.isIdValid(this.rating.getText())) {
            rating = Double.parseDouble(this.rating.getText());
        } else {
            this.message.setText("Rating is not valid");
        }

        String category  = "";
        try {
            //TODO: Pagal username_label is GUI (Duoda vartotojo varda kuris yra prisijunges prie sistemos)
            //Reikia parašyti užklausa duomenų bazėje, kuri gražintų vartotojo id
            //Tada jį perduosim į update metodą

            FilmsDAO.update(new Films(title, description, rating, category, 1));
//            this.message.setTextFill(Color.GREEN);
            this.message.setText("Successfully updated entry");
        } catch (SQLException throwables) {
            this.message.setText("Failed to update entry");
            }
    }

    public void onDeleteAction(ActionEvent actionEvent) {
        if (Validation.isIdValid(this.id.getText())) {
            try {
                FilmsDAO.delete(Integer.parseInt(this.id.getText()));
//                this.message.setTextFill(Color.GREEN);
                this.message.setText("Form deleted successfully");
            } catch (SQLException throwables) {
                this.message.setText("Failed to delete a request");
            }
        } else {
            this.message.setText("ID is not valid");
        }
    }

    public void onSearchAction(ActionEvent actionEvent) {
        try {
            String title = this.title.getText();

            updateTableFromDB(title);
        } catch (SQLException throwables) {
            this.message.setText("Search failed");
            throwables.printStackTrace();
        }
    }

    private void updateTableFromDB(String title) throws SQLException {
        this.rsAllEntries = FilmsDAO.search(title);
        this.fetchColumnList();
        this.fetchRowList();
    }

    //only fetch columns
    private void fetchColumnList() {
        try {
            table.getColumns().clear();
            //SQL FOR SELECTING ALL OF ENTRIES
            for (int i = 0; i < rsAllEntries.getMetaData().getColumnCount(); i++) {
                //We are using non property style for making dynamic table
                final int j = i;
                TableColumn col = new TableColumn(rsAllEntries.getMetaData().getColumnName(i + 1).toUpperCase());
                col.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
                    public ObservableValue<String> call(TableColumn.CellDataFeatures<ObservableList, String> param) {
                        return new SimpleStringProperty(param.getValue().get(j).toString());
                    }
                });
                table.getColumns().removeAll(col);
                table.getColumns().addAll(col);
            }
        } catch (SQLException e) {
            this.message.setText("Failure in getting all entries");
        }
    }

    //fetches rows and data from the list
    private void fetchRowList() {
        try {
            data.clear();
            while (rsAllEntries.next()) {
                //Iterate Row
                ObservableList<String> row = FXCollections.observableArrayList();
                for (int i = 1; i <= rsAllEntries.getMetaData().getColumnCount(); i++) {
                    //Iterate Column
                    row.add(rsAllEntries.getString(i));
                }
                data.add(row);
            }
            //Connects table with list
            table.setItems(data);
        } catch (SQLException ex) {
            this.message.setText("Failure in getting all entries");
        }
    }

}
