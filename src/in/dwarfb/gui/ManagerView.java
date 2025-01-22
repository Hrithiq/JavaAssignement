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


import in.dwarfb.inventory.StatisticsGenerator;
public class ManagerView {

    private Scene scene;
    private Stage primaryStage;
    private Inventory inventory;
    private Notification notification;

    private List<RadioButton> productrb = new ArrayList<>();
    private ToggleGroup producttg = new ToggleGroup();
    private Label details = new Label();
    private TextField addStockf;
    private VBox leftBox;
    private VBox rightBox;
    private VBox informationBox;
    private VBox newProductBox;

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

        Button logoutButton = new Button("Logout!");
        logoutButton.setOnAction(e -> {
            LoginView lgnView = new LoginView(primaryStage, inventory, notification);
            lgnView.setScene();
        });

        topBar.getChildren().addAll(statsButton, notificationsButton,logoutButton);

        // Left box: Product selection with radio buttons
        leftBox = new VBox(10);
        leftBox.setPadding(new Insets(10));
        leftBox.setStyle("-fx-background-color: #d3d3d3;");

        Label productLabel = new Label("PRODUCTS\nRADIOBUTTONS");
        productLabel.setStyle("-fx-font-weight: bold;");

        // MARKER
        for (var i : inventory.asArrayList()) {
            addProductrb(i.getName());
        }
        productrb.get(0).setSelected(true);
        showdetails();

        Button addProduct = new Button("ADD NEW PRODUCT");
        // TODO ADD product UI
        // addProduct.setOnAction(e -> addNewProduct());

        leftBox.getChildren().addAll(productLabel);
        leftBox.getChildren().addAll(productrb);
        leftBox.getChildren().addAll(addProduct);

        // Right box: Product details and add stock feature
        informationBox = new VBox(10);
        informationBox.setPadding(new Insets(10));
        informationBox.setStyle("-fx-background-color: #d3d3d3;");

        Button addStock = new Button("ADD STOCK");
        addStock.setOnAction(e -> showAddStockf());

        addStockf = new TextField();
        addStockf.setPromptText("Enter stock amount");
        addStockf.setVisible(true);

        informationBox.getChildren().addAll(details, addStock, addStockf);
        rightBox = informationBox;
        newProductBox = new VBox(10);

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
        product.setOnAction(e->{showdetails();});
        product.setToggleGroup(producttg);
        productrb.add(product);
    }

    // Adds a new product if limit is not reached
    private void addNewProduct(Product product) {
        rightBox = new VBox();
    }

    private void showdetails() {
        try{
            RadioButton selectedProduct = (RadioButton) producttg.getSelectedToggle();
            if (selectedProduct != null) {
                String productName = selectedProduct.getText();
                Product product = inventory.getItemByName(productName);
                details.setText("DETAILS:\nID: "+product.getID()+"\nNAME OF PRODUCT: $" + product.getName() + "\nPRICE OF PRODUCT: $" + product.getPrice() + "\nGIVEN PRODUCT STOCK: " + product.getStock() + "\nProduct Type: " + product.getProductType().name());
                rightBox = informationBox;

            }
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    private void showAddStockf() {
        try{
            RadioButton selectedProduct = (RadioButton) producttg.getSelectedToggle();
            if(selectedProduct != null){
                String productName = selectedProduct.getText();
                Product p = inventory.getItemByName(productName);
                int incStock = Integer.parseInt(addStockf.getText());
                p.setStock(p.getStock() + incStock);
                showdetails();
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }


    private void showStatistics() {
        StatisticsGenerator sg = new StatisticsGenerator(inventory);
        var statistics = sg.generateAllStatistics();
        StringBuilder sb = new StringBuilder("Showing Statistics...\n");
        for(var s : statistics){
            sb.append(s+"\n");
        }
        Alert alert = new Alert(Alert.AlertType.INFORMATION, sb.toString());
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
