package sample;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.control.ListView;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.*;
import java.util.List;


public class Controller implements Initializable {
    private MonopolyGame game = MonopolyGame.getInstance();
    private List players;
    DatabaseAdapter DBStore = new DatabaseAdapter();
    CSVAdapter CSVStore = new CSVAdapter();
    private Map<String, ImageView> playerImageMapping = new HashMap<String, ImageView>();
    private Map<Integer, Point> indexToCordMapping = new HashMap<Integer, Point>();
    private ObservableList gameNameData = FXCollections.observableArrayList();

    @FXML
    private Button loadDBButton;

    @FXML
    private Button loadCSVButton;

    @FXML
    private GridPane grid;

    @FXML
    private ComboBox sourceBox;

    @FXML
    private TextField nameField;

    @FXML
    private TextArea outputArea;

    @FXML
    private ImageView boardImage;

    @FXML
    private Button saveButton;

    @FXML
    private ImageView horse;

    @FXML
    private ListView gamesCSVView;

    @FXML
    private ListView gamesDBView;

    @FXML
    private ImageView car;

    @FXML
    private Button playButton;

    public Controller() throws SQLException {
    }

    @FXML
    void playGame(ActionEvent event) {
        game.resetGame();
        addTextObservers();
        outputArea.setText("Welcome to Monopoly!\n");
        nameField.setText("");
        game.playGame();
    }

    void setupGame() throws SQLException {
        game = MonopolyGame.getInstance();
        players = game.getPlayers();
        GameObserver gui = new GameObserver();
        game.addObserver(gui);
        playerImageMapping.put("Horse",horse);
        playerImageMapping.put("Car",car);
        createIndexMapping();
        for (Iterator iter = players.iterator(); iter.hasNext(); )
        {
            Player player = (Player) iter.next();
            PlayerTextObserver text = new PlayerTextObserver(player);
            player.addObserver(text);
        }
        loadListsView();
    }
    void addTextObservers(){
        players = game.getPlayers();

        for (Iterator iter = players.iterator(); iter.hasNext(); )
        {
            Player player = (Player) iter.next();
            PlayerTextObserver text = new PlayerTextObserver(player);
            player.addObserver(text);
        }
    }
    void movePlayer(Player player){
        ImageView temp = playerImageMapping.get(player.getName());
        Point squareCords = indexToCordMapping.get(player.getLocation().getIndex());
        int x = (int)squareCords.getX();
        int y = (int)squareCords.getY();
        grid.setColumnIndex(temp, x);
        grid.setRowIndex(temp, y);
    }

    @FXML
    void loadCSVGame(ActionEvent event) throws SQLException {
        outputArea.setText("");
        List<PlayerPair> pairs = CSVStore.load(gamesCSVView.getSelectionModel().getSelectedItem().toString());
        if (game != null){
            game.createGameFromLoad(pairs);
        }
    }
    @FXML
    void loadDBGame(ActionEvent event) throws SQLException {
        outputArea.setText("");

        List<PlayerPair> pairs = DBStore.load(gamesDBView.getSelectionModel().getSelectedItem().toString());
        if (game != null){
            game.createGameFromLoad(pairs);
        }
    }

    void loadListsView() throws SQLException {
        List gameNames = CSVStore.getGameNames("CSV");
        for(int i = 0; i < gameNames.size(); i++){
            gameNameData.add(gameNames.get(i));
        }
        gamesCSVView.setItems(gameNameData);

        gameNames = DBStore.getGameNames("Database");
        gameNameData = FXCollections.observableArrayList();
        for(int i = 0; i < gameNames.size(); i++){
            gameNameData.add(gameNames.get(i));
        }
        gamesDBView.setItems(gameNameData);
    }

    @FXML
    void saveGame(ActionEvent event) throws IOException, SQLException {
        if(!(sourceBox.getSelectionModel().getSelectedItem() == null)) {
            if(!(nameField.getText().isEmpty())) {
                if (sourceBox.getSelectionModel().getSelectedItem().toString().equals("CSV")){
                    CSVStore.store(nameField.getText(), players);
                } else{
                    DBStore.store(nameField.getText(), players);
                }
                nameField.setText("");
                loadListsView();
            }else
                outputArea.setText("Give game a name");
        }else
            outputArea.setText("Select a save type");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        sourceBox.getItems().addAll("CSV","Database");
        try {
            setupGame();
        } catch (SQLException e) {
            System.out.println("test");
        }
    }

    private class GameObserver implements Observer {

        @Override
        public void update(Observable o, Object arg) {
            if (arg instanceof String) {
                outputArea.appendText((String) arg);
            }
            else if (arg instanceof Player) {
                movePlayer((Player) arg);
            }
        }
    }
    private void createIndexMapping(){
        indexToCordMapping.put(0,new Point(10,10));
        indexToCordMapping.put(1,new Point(9,10));
        indexToCordMapping.put(2,new Point(8,10));
        indexToCordMapping.put(3,new Point(7,10));
        indexToCordMapping.put(4,new Point(6,10));
        indexToCordMapping.put(5,new Point(5,10));
        indexToCordMapping.put(6,new Point(4,10));
        indexToCordMapping.put(7,new Point(3,10));
        indexToCordMapping.put(8,new Point(2,10));
        indexToCordMapping.put(9,new Point(1,10));
        indexToCordMapping.put(10,new Point(0,10));
        indexToCordMapping.put(11,new Point(0,9));
        indexToCordMapping.put(12,new Point(0,8));
        indexToCordMapping.put(13,new Point(0,7));
        indexToCordMapping.put(14,new Point(0,6));
        indexToCordMapping.put(15,new Point(0,5));
        indexToCordMapping.put(16,new Point(0,4));
        indexToCordMapping.put(17,new Point(0,3));
        indexToCordMapping.put(18,new Point(0,2));
        indexToCordMapping.put(19,new Point(0,1));
        indexToCordMapping.put(20,new Point(0,0));
        indexToCordMapping.put(21,new Point(1,0));
        indexToCordMapping.put(22,new Point(2,0));
        indexToCordMapping.put(23,new Point(3,0));
        indexToCordMapping.put(24,new Point(4,0));
        indexToCordMapping.put(25,new Point(5,0));
        indexToCordMapping.put(26,new Point(6,0));
        indexToCordMapping.put(27,new Point(7,0));
        indexToCordMapping.put(28,new Point(8,0));
        indexToCordMapping.put(29,new Point(9,0));
        indexToCordMapping.put(30,new Point(10,0));
        indexToCordMapping.put(31,new Point(10,1));
        indexToCordMapping.put(32,new Point(10,2));
        indexToCordMapping.put(33,new Point(10,3));
        indexToCordMapping.put(34,new Point(10,4));
        indexToCordMapping.put(35,new Point(10,5));
        indexToCordMapping.put(36,new Point(10,6));
        indexToCordMapping.put(37,new Point(10,7));
        indexToCordMapping.put(38,new Point(10,8));
        indexToCordMapping.put(39,new Point(10,9));

    }

}

