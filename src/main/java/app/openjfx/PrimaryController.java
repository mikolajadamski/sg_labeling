package app.openjfx;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class PrimaryController {

    @FXML
    private TextField vertices;

    @FXML
    private TextField edges;

    @FXML
    private Pane graph;

    @FXML
    private void switchToSecondary() throws IOException {
        Random generator = new Random();
        int v = Integer.parseInt(vertices.getText());
        String e = edges.getText();
        String[] connections = e.split(",");

        if (v >= connections.length + 1) {
            ArrayList<Integer> X = new ArrayList<Integer>();
            ArrayList<Integer> Y = new ArrayList<Integer>();

            for (int i = 0; i < v; i++) {
                int x = generator.nextInt(670) + 30;
                int y = generator.nextInt(360) + 30;
                Rectangle rectangle = new Rectangle(x, y, 20, 20);
                rectangle.setFill(Color.BLUE);
                Text text = new Text('v' + String.valueOf(i));
                text.setX(x);
                text.setY(y + 10);
                text.setFill(Color.WHITE);
                graph.getChildren().add(rectangle);
                graph.getChildren().add(text);
                X.add(x);
                Y.add(y);
            }

            for (String i : connections) {
                String[] vertices = i.split("-");
                vertices[0] = vertices[0].replace("v", "");
                vertices[1] = vertices[1].replace("v", "");

                int color = generator.nextInt(6);

                Paint c = Color.AZURE;
                switch (color) {
                    case 0:
                        c = Color.RED;
                        break;
                    case 1:
                        c = Color.BLUE;
                        break;
                    case 2:
                        c = Color.GREEN;
                        break;
                    case 3:
                        c = Color.YELLOW;
                        break;
                    case 4:
                        c = Color.PINK;
                        break;
                    case 5:
                        c = Color.ORANGE;
                        break;
                    case 6:
                        c = Color.PURPLE;
                        break;
                    default:
                        break;
                }

                Line line = new Line();
                line.setStartX(X.get(Integer.parseInt(vertices[0])));
                line.setStartY(Y.get(Integer.parseInt(vertices[0])));
                line.setEndX(X.get(Integer.parseInt(vertices[1])));
                line.setEndY(Y.get(Integer.parseInt(vertices[1])));
                line.setStroke(c);
                graph.getChildren().add(line);

                int s_x = (X.get(Integer.parseInt(vertices[0])) + X.get(Integer.parseInt(vertices[1]))) / 2;
                int s_y = (Y.get(Integer.parseInt(vertices[0])) + Y.get(Integer.parseInt(vertices[1]))) / 2;
                Text text = new Text("5");
                text.setX(s_x);
                text.setY(s_y - 10);
                text.setFill(c);
                graph.getChildren().add(text);
            }
        }
    }

    @FXML
    private void clear() throws IOException {
        graph.getChildren().clear();
    }
}

// v0-v1,v1-v4,v1-v5,v1-v2,v2-v3