package app.openjfx;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import javax.swing.JFileChooser;
import java.io.File;
import java.io.FileNotFoundException;

import app.labeler.SkolemGracefulLabeler;
import app.graph.Edge;
import app.graph.Graph;
import app.graph.Vertex;
import javafx.scene.control.Alert;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import org.chocosolver.solver.Solver;

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
        clear();
        Random generator = new Random();
        int v = Integer.parseInt(vertices.getText());
        String e = edges.getText();
        String[] connections = e.replaceAll("\\s+","").split(",");

        if (v >= connections.length + 1) {
            Graph g = new Graph();

            for (int i = 0; i < v; i++) {
                g.addVertex(new Vertex("v" + String.valueOf(i)));
            }

            for (int i = 1; i <= connections.length; i++) {
                String[] vertices = connections[i - 1].split("-");
                g.addEdge(new Edge("e" + String.valueOf(i), g.getVertex(vertices[0]), g.getVertex(vertices[1])));
            }

            SkolemGracefulLabeler l = new SkolemGracefulLabeler();
            Solver solver = l.labelGraph(g);

            if (solver.solve()) {
                ArrayList<Vertex> vert = g.getVertices();
                ArrayList<Edge> edges = g.getEdges();

                ArrayList<Integer> X = new ArrayList<Integer>();
                ArrayList<Integer> Y = new ArrayList<Integer>();

                for (int i = 0; i < v; i++) {
                    int x = generator.nextInt(670) + 30;
                    int y = generator.nextInt(360) + 30;
                    Circle circle = new Circle(x, y, 20);
                    circle.setFill(Color.YELLOWGREEN);
                    Text text = new Text('v' + String.valueOf(i));
                    text.setFont(Font.font("Consolas",FontWeight.BOLD, 16));
                    text.setX(x);
                    text.setY(y + 10);
                    graph.getChildren().add(circle);
                    graph.getChildren().add(text);
                    X.add(x);
                    Y.add(y);

                    for (int j = 0; j < v; j++) {
                        if (vert.get(j).getId().equals('v' + String.valueOf(i))) {
                            Text text2 = new Text(String.valueOf(vert.get(j).getVarId().getValue()));
                            text2.setX(x);
                            text2.setY(y - 10);
                            text2.setFont(Font.font("Consolas",FontWeight.BOLD, 16));
                            graph.getChildren().add(text2);
                        }
                    }
                }

                for (int i = 0; i < connections.length; i++) {
                    String[] vertices = connections[i].split("-");
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

                    for (int j = 0; j < edges.size(); j++) {
                        if ((('v' + vertices[0]).equals(edges.get(j).getV1().getId())
                                && ('v' + vertices[1]).equals(edges.get(j).getV2().getId()))
                                || (('v' + vertices[1]).equals(edges.get(j).getV1().getId())
                                        && ('v' + vertices[0]).equals(edges.get(j).getV2().getId()))) {
                            Text text = new Text(String.valueOf(edges.get(j).getVarId().getValue()));
                            text.setX(s_x);
                            text.setY(s_y - 10);
                            text.setFont(Font.font("Consolas",FontWeight.BOLD, 16));
                            text.setFill(c);
                            graph.getChildren().add(text);

                        }
                    }

                }
            }
        }
        else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Kolorowanie Skolem-Graceful");
            alert.setHeaderText("Podanego grafu nie można pokolorować metodą Skolem-Graceful");
            alert.showAndWait();
        }
    }

    @FXML
    private void clear() throws IOException {
        graph.getChildren().clear();
    }

    @FXML
    private void loadGraph() throws IOException {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
        int result = fileChooser.showOpenDialog(fileChooser);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();

            try {
                String data = "";
                File myObj = new File(selectedFile.getAbsolutePath());
                Scanner myReader = new Scanner(myObj);
                while (myReader.hasNextLine()) {
                    data += myReader.nextLine();
                }
                myReader.close();

                String[] elements = data.split("|");
                String numberOfVertices = elements[0];
                String e = data.replace(numberOfVertices + "|", "");

                vertices.setText(numberOfVertices);
                edges.setText(e);
            } catch (FileNotFoundException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
        }
    }
}