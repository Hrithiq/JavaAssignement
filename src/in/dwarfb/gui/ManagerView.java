package in.dwarfb.gui;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

import in.dwarfb.inventory.Inventory;
import in.dwarfb.inventory.Product;

public class ManagerView {

    private Scene scene;
    private Stage primaryStage;
    private Inventory inventory;
    private Notification notification;

    private List<RadioButton> productrb = new ArrayList<>();
    private ToggleGroup producttg = new ToggleGroup();
    private Label details = new Label("DETAILS:\nNAME OF PRODUCT\nPRICE OF PRODUCT\nGIVEN STOCK PRODUCTS\nTOTAL REMAINING PRODUCTS");
    private TextField addStockf;
    private Label stockprod = new Label("Given product stock: 0");
    private Label totalstock = new Label("Total Remaining Products: 0");

    public ManagerView(Stage stage, Inventory inventory, Notification notification){
        this.primaryStage = stage;
        this.inventory = inventory;
        this.notification = notification;
    }

    public void setScene() {
        // Top bar with statistics and notifications buttons
        HBox topBar = new HBox();
        topBar.setPadding(new Insets(10));
        topBar.setSpacing(10);
        topBar.setStyle("-fx-background-color: #d3d3d3;");

        Button statsButton = new Button("Statistics");
        statsButton.setOnAction(e -> showStatistics());

        Button notificationsButton = new Button("Notifications");
        notificationsButton.setOnAction(e -> showNotifications());

        topBar.getChildren().addAll(statsButton, notificationsButton);

        // Left box: Product selection with radio buttons
        VBox leftBox = new VBox(10);
        leftBox.setPadding(new Insets(10));
        leftBox.setStyle("-fx-background-color: #d3d3d3;");

        Label productLabel = new Label("PRODUCTS\nRADIOBUTTONS");
        productLabel.setStyle("-fx-font-weight: bold;");

        // MARKER
        for (var i : inventory.asArrayList()) {
            System.out.println("Adding rb: "+ i.toString());
            addProductrb(i.getName());
        }

        Button showDetails = new Button("SHOW DETAILS");
        showDetails.setOnAction(e -> showdetails());

        Button addProduct = new Button("ADD NEW PRODUCT");
        // TODO ADD product UI
        // addProduct.setOnAction(e -> addNewProduct());

        leftBox.getChildren().addAll(productLabel);
        leftBox.getChildren().addAll(productrb);
        leftBox.getChildren().addAll(showDetails, addProduct);

        // Right box: Product details and add stock feature
        VBox rightBox = new VBox(10);
        rightBox.setPadding(new Insets(10));
        rightBox.setStyle("-fx-background-color: #d3d3d3;");

        Button addStock = new Button("ADD STOCK");
        addStock.setOnAction(e -> showAddStockf());

        addStockf = new TextField();
        addStockf.setPromptText("Enter stock amount");
        addStockf.setOnAction(e -> addStock());
        addStockf.setVisible(false);

        rightBox.getChildren().addAll(details, stockprod, totalstock, addStock, addStockf);

        HBox mainlayout = new HBox(20, leftBox, rightBox);
        mainlayout.setPadding(new Insets(10));

        VBox root = new VBox(10, topBar, mainlayout);
        Scene scene = new Scene(root, 600, 400);

        primaryStage.setScene(scene);
        primaryStage.setTitle("Product Manager");
        primaryStage.show();
    }

    // Adds a new product radio button to the left side
    private void addProductrb(String productName) {
            RadioButton product = new RadioButton(productName);
            product.setToggleGroup(producttg);
            productrb.add(product);
    }

    // Adds a new product if limit is not reached
    private void addNewProduct(Product product) {
        addProductrb(product.getName());
    }

    private void showdetails() {
        try{
            RadioButton selectedProduct = (RadioButton) producttg.getSelectedToggle();
            if (selectedProduct != null) {
                String productName = selectedProduct.getText();
                Product product = inventory.getItemByName(productName);
                details.setText("DETAILS:\nID: "+product.getID()+"\nNAME OF PRODUCT: " + product.getName() + "\nPRICE OF PRODUCT: " + product.getPrice() + "\nGIVEN PRODUCT STOCK: " + product.getStock() + "\nProduct Type: " + product.getProductType().name());
            }
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    private void showAddStockf() {
        addStockf.setVisible(true);
    }

    private void addStock() {
        try {
            int addedStock = Integer.parseInt(addStockf.getText());
            int currentProductStock = Integer.parseInt(stockprod.getText().split(": ")[1]);
            int currentTotalStock = Integer.parseInt(totalstock.getText().split(": ")[1]);

            stockprod.setText("Given product stock: " + (currentProductStock + addedStock));
            totalstock.setText("Total Remaining Products: " + (currentTotalStock + addedStock));

            addStockf.clear();
            addStockf.setVisible(false);
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Please enter a valid number for stock.");
            alert.showAndWait();
        }
    }

    // Placeholder for statistics function
    private void showStatistics() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION, "Showing Statistics...");
        alert.showAndWait();
    }

    // Placeholder for notifications function
    private void showNotifications() {
        StringBuilder sb = new StringBuilder("Showing Notifications\n\n");
        for(var n : notification.asArrayList()){
            sb.append(n + "\n");
        }
        Alert alert = new Alert(Alert.AlertType.INFORMATION, sb.toString());
        alert.showAndWait();
    }

}
