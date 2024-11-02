package in.dwarfb.gui;

import in.dwarfb.inventory.Inventory;
import in.dwarfb.inventory.Order;
import in.dwarfb.inventory.Product;
import in.dwarfb.inventory.ProductType;
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

    public UserView(Stage primaryStage, Inventory inventory, Notification notification){
        stage = primaryStage;
        this.inventory = inventory;
        this.notification = notification;
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

        VBox orderSummary = new VBox(10);
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
        placeOrderButton.setOnAction(e -> {
            // TODO Subtract User Money
            try {
                Order order = new Order();
                var labels = orderSummary.getChildren();
                for(int i = 1; i < labels.size(); i++){
                    var label = (Label) labels.get(i);
                    var text = label.getText();
                    var words = text.split(" ");
                    String itemName = words[0];
                    int quantity = Integer.parseInt(words[2]);
                    Product product = inventory.getItemByName(itemName);
                    inventory.purchase(product.getID(), quantity);
                    order.addProduct(product, quantity);
                }
                order.setDate();
                inventory.addOrder(order);
                LoginView lgnView = new LoginView(stage, inventory, notification);
                lgnView.setScene();
            } catch (Exception err){
                err.printStackTrace();
            }
        });

        addItemButton.setOnAction(e -> {
            try{
                String selectedProduct = products.getSelectionModel().getSelectedItem();
                String quantityText = numOfItemsField.getText();

                if (selectedProduct == null) {
                    showAlert("No product selected", "Please select a product to add.");
                    return;
                }
                Product product = inventory.getItemByName(selectedProduct);

                int quantity = Integer.parseInt(quantityText);
                if (quantity <= 0) {
                    showAlert("Invalid quantity", "Please enter a quantity greater than zero.");
                    return;
                }
                if (quantity > product.getStock()){
                    showAlert("Not in stock!", "Please enter a lesser value");
                    return;
                }

                Label orderItem = new Label(selectedProduct + " x " + quantity);
                orderSummary.getChildren().add(orderItem);
                products.getSelectionModel().clearSelection();
                numOfItemsField.clear();
            } catch(Exception er){
                er.printStackTrace();
            }
        });

        bottomRow.getChildren().addAll(numOfItemsLabel, numOfItemsField, addItemButton, placeOrderButton);

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

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
