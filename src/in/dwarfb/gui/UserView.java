package in.dwarfb.gui;

import java.util.concurrent.SynchronousQueue;

import in.dwarfb.inventory.Inventory;
import in.dwarfb.inventory.Order;
import in.dwarfb.inventory.Product;
import in.dwarfb.inventory.ProductType;
import in.dwarfb.user.Customer;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class UserView {
    private Scene scene;
    private Stage stage;
    private Notification notification;
    private Inventory inventory;
    private Order order;
    private Customer customer;

    private VBox orderSummary;

    public UserView(Stage primaryStage, Customer customer,Inventory inventory, Notification notification){
        stage = primaryStage;
        this.inventory = inventory;
        this.notification = notification;
        this.customer = customer;
        order = new Order();
    }

    public void setScene() {
        ListView<String> products = new ListView<>();
        var items = products.getItems();
        for(var i : inventory.asArrayList())
            items.add(i.getName());
        // items
        products.setPrefHeight(300);

        ScrollPane scrollPane = new ScrollPane(products);
        scrollPane.setFitToWidth(true);
        scrollPane.setPrefViewportHeight(300);

        orderSummary = new VBox(10);
        orderSummary.setPadding(new Insets(10));
        orderSummary.setStyle("-fx-background-color: #d3d3d3;");

        Label summaryLabel = new Label("Order Summary");
        orderSummary.getChildren().add(summaryLabel);

        HBox bottomRow = new HBox(10);
        bottomRow.setAlignment(Pos.CENTER);

        Label numOfItemsLabel = new Label("No. of items:");
        TextField numOfItemsField = new TextField();
        numOfItemsField.setPrefWidth(50);

        Button addItemButton = new Button("Add item");
        Button placeOrderButton = new Button("Place order");
        Button logoutButton = new Button("Logout");
        placeOrderButton.setOnAction(e -> {
            Thread thr = new Thread(new Runnable() {
                    // TODO Subtract User Money
					@Override
					public void run() {
                        synchronized(order){

                            try {
                                System.out.println("Order: "+order.getProducts().size());
                                for(int i = 0; i < order.getProducts().size(); i++){
                                    System.out.println("here in for loop");

                                    inventory.purchase(order.getProducts().get(i).getID(), order.getProductQuantities().get(i));
                                }
                                order.setDate();
                                inventory.addOrder(order);
                            }
                                catch (Exception err){
                                    err.printStackTrace();
                                }
                        }

                    }
            });
            thr.start();
            try{
                thr.join();
            } catch (InterruptedException err){
                err.printStackTrace();
            }
            order = new Order();
            orderSummary.getChildren().clear();
            refreshContents();
        });

            addItemButton.setOnAction(e -> {
                try{
                    String selectedProduct = products.getSelectionModel().getSelectedItem();
                    String quantityText = numOfItemsField.getText();

                    Product product = inventory.getItemByName(selectedProduct);
                    long quantity = Integer.parseInt(quantityText);
                    order.addProduct(product, quantity);
                    System.out.println("Here: "+ product.getID() + " " + order.getProducts().size());
                    refreshContents();
                    products.getSelectionModel().clearSelection();
                    numOfItemsField.clear();

                    if (selectedProduct == null) {
                        showAlert("No product selected", "Please select a product to add.");
                        return;
                    }
                    if (quantity <= 0) {
                        showAlert("Invalid quantity", "Please enter a quantity greater than zero.");
                        return;
                    }
                    if (quantity > product.getStock()){
                        showAlert("Not in stock!", "Please enter a lesser value");
                        return;
                    }
                } catch(Exception er){
                    er.printStackTrace();
                }
            });

            logoutButton.setOnAction(e ->{
                LoginView lgnView = new LoginView(stage, inventory, notification);
                lgnView.setScene();
            });

            bottomRow.getChildren().addAll(numOfItemsLabel, numOfItemsField, addItemButton, placeOrderButton,logoutButton);

            GridPane mainGrid = new GridPane();
            mainGrid.setPadding(new Insets(10));
            mainGrid.setHgap(10);
            mainGrid.setVgap(10);

            mainGrid.add(scrollPane, 0, 1);
            mainGrid.add(orderSummary, 1, 1);
            mainGrid.add(bottomRow, 0, 2, 2, 1);

            ColumnConstraints col1 = new ColumnConstraints();
            col1.setPercentWidth(60);
            ColumnConstraints col2 = new ColumnConstraints();
            col2.setPercentWidth(40);
            mainGrid.getColumnConstraints().addAll(col1, col2);

            RowConstraints row1 = new RowConstraints();
            row1.setPrefHeight(40);
            RowConstraints row2 = new RowConstraints();
            row2.setVgrow(Priority.ALWAYS);
            RowConstraints row3 = new RowConstraints();
            row3.setPrefHeight(50);
            mainGrid.getRowConstraints().addAll(row1, row2, row3);

            scene = new Scene(mainGrid, 600, 400);
            stage.setTitle("Order Layout");
            stage.setScene(scene);
            stage.show();
        }

            private void refreshContents(){
        orderSummary.getChildren().clear();
        orderSummary.getChildren().add(new Label("Order Summary"));
        for(int i = 0; i < order.getProducts().size();i++){
            Product p = order.getProducts().get(i);
            long q = order.getProductQuantities().get(i);
            orderSummary.getChildren().add(new Label(p.getName() + " x " + q));
        }

    }
        private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    }
