package pack;

import java.io.File;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Line;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.ColorPicker;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.input.MouseEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import java.lang.Math;
import java.util.List;
import java.util.ArrayList;
import javafx.scene.shape.*;
import javafx.scene.transform.Scale;
import javafx.scene.transform.Translate;

public class Controller 
{
	
	@FXML
    private RadioButton selectMove;
	
	@FXML
    private RadioButton ellipse;
	
	@FXML
    private RadioButton rectangle;
	
	@FXML
    private RadioButton line;
	
	@FXML
	private ColorPicker colorPicker;
	
	@FXML
    private Button delete;

    @FXML
    private Button clone;

    @FXML
	private Pane pane;
    
    @FXML
	public void initialize()
    {

    	//Mise en place du d�cochage des radio boutons
    	ToggleGroup group = new ToggleGroup();
    	selectMove.setToggleGroup(group);
    	selectMove.setSelected(true);
    	ellipse.setToggleGroup(group);
    	rectangle.setToggleGroup(group);
    	line.setToggleGroup(group);
    	
    	//Cr�ation d'un listener
    	/*ChangeListener<Number> listener = new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				Color newColor = new Color(0, 0, 1, newValue.doubleValue()/100);
				root.setBackground(new Background(new BackgroundFill(newColor, null, null)));
				
			}
		};*/
    	
    	//Liste des formes g�om�triques
    	List<Ellipse> listE = new ArrayList();
    	List<Rectangle> listR = new ArrayList();
    	List<Line> listL = new ArrayList();
    	
    	List<Ellipse> listET = new ArrayList(); //Liste compos�e de l'ellipse s�lectionn�e

		//Evenement li� au click de la souris
		EventHandler<MouseEvent> eventHandler = new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				double x = event.getSceneX();
				double y = event.getSceneY();
				if (selectMove.isSelected()) {
					if (listET.size() == 0) {
						boolean trouvee = false;
							for (int i = listE.size()-1 ; (i >= 0) && !trouvee ; i = i - 1) {
									if ((Math.abs(x - 244 - listE.get(i).getCenterX()) <= listE.get(i).getRadiusX()) && (Math.abs(y - listE.get(i).getCenterY()) <= listE.get(i).getRadiusY())) {
										trouvee = true;
										listE.get(i).setStroke(Color.RED); //Contour de l'ellipse s�lectionn�e en rouge
										listET.add(listE.get(i));
										System.out.println("Ellipse s�lectionn�e");
									}
							}
							if (!trouvee) {
								System.err.println("Ellipse non trouv�e (La fonction de s�lection n'est disponible qu'avec les ellipses)");
							}	
					} else {
						listET.get(0).setStroke(Color.BLACK);
						listET.remove(0);
						boolean trouvee = false;
						for (int i = listE.size()-1 ; (i >= 0) && !trouvee ; i = i - 1) {
								if ((Math.abs(x - 244 - listE.get(i).getCenterX()) <= listE.get(i).getRadiusX()) && (Math.abs(y - listE.get(i).getCenterY()) <= listE.get(i).getRadiusY())) {
									trouvee = true;
									listE.get(i).setStroke(Color.RED); //Contour de l'ellipse s�lectionn�e en rouge
									listET.add(listE.get(i));
									System.out.println("Ellipse s�lectionn�e");
								}
						}
						if (!trouvee) {
							System.err.println("Ellipse non trouv�e (Ellipse non trouv�e (La fonction de s�lection n'est disponible qu'avec les ellipses !)");
						}
					}
				} else if (ellipse.isSelected()) {
		    		System.out.println("Cr�ation d'une ellipse");
		    		Ellipse ellips = new Ellipse(x-244, y, 1, 1);
		    		ellips.setFill(colorPicker.getValue()); //Ajout de la couleur sur l'ellipse
		    		ellips.setStroke(Color.BLACK); //Contour de l'ellipse en noir
		    		pane.getChildren().add(ellips); //On ajoute l'ellipse au pane
		    		listE.add(ellips); //On ajoute l'ellipse � la liste des ellipses
		    	} else if (rectangle.isSelected()) {
		    		System.out.println("Cr�ation d'un rectangle");
		    		Rectangle rectangl = new Rectangle(x-244, y, 1, 1);
		    		rectangl.setFill(colorPicker.getValue()); //Ajout de la couleur sur le rectangle
		    		pane.getChildren().add(rectangl); //On ajoute le rectangle au pane
		    		listR.add(rectangl); //On ajoute le rectangle � la liste des rectangles
		    	} else if (line.isSelected()) {
		    		System.out.println("Cr�ation d'une ligne");
		    		Line lin = new Line(x-244, y, x-243, y+1);
		    		lin.setStrokeWidth(3); //Ajustement de la largeur de la ligne
		    		lin.setStroke(colorPicker.getValue()); //Ajout de la couleur de la ligne
		    		pane.getChildren().add(lin); //On ajoute la ligne au pane
		    		listL.add(lin); //On ajoute la ligne � la liste des lignes
		    	}
			}
		};
		
		//Evenement li� au d�placement de la souris
		EventHandler<MouseEvent> eventHandler2 = new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				double x = event.getSceneX();
				double y = event.getSceneY();
				if (selectMove.isSelected()) {
					
				} else if (ellipse.isSelected()) {
				   	//System.out.println("Modification de la taille de l'ellipse");
				   	listE.get(listE.size()-1).setRadiusX(Math.abs((x-244)-listE.get(listE.size()-1).getCenterX()));
				   	listE.get(listE.size()-1).setRadiusY(Math.abs((y)-listE.get(listE.size()-1).getCenterY()));
				} else if (rectangle.isSelected()) {
					//System.out.println("Modification de la taille du rectangle");
				  	listR.get(listR.size()-1).setScaleX(Math.abs((x-244)-listR.get(listR.size()-1).getX()));
				   	listR.get(listR.size()-1).setScaleY(Math.abs((y)-listR.get(listR.size()-1).getY()));
				} else if (line.isSelected()) {
				 	//System.out.println("Modification de la taille de la ligne");
				 	listL.get(listL.size()-1).setEndX(x-244);
				 	listL.get(listL.size()-1).setEndY(y);
				}
			}
		};
		
		EventHandler<MouseEvent> eventHandlerDelete = new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				if (selectMove.isSelected() || (ellipse.isSelected())) {
					if (listET.size() == 1) {
						System.out.println("L'ellipse s�lectionn�e a �t� supprim�e !");
						pane.getChildren().remove(listET.get(0)); //Suppression de l'ellipse s�lectionn�e dans le pane
						listE.remove(0); //Suppression de l'ellipse s�lectionn�e dans la liste des ellipses
						listET.remove(0); //Suppression de l'ellipse s�lectionn�e dans la liste de l'ellipse s�lectionne�
					}
				} else if (rectangle.isSelected()) {
					if (listR.size() > 0) {
						System.out.println("Suppression du dernier rectangle cr��");
						pane.getChildren().remove(listR.get(listR.size()-1)); //Suppression du dernier rectangle cr�� dans le pane
						listR.remove(listR.get(listR.size()-1)); //Suppression du dernier rectangle cr�� dans la liste des rectangles
					} else {
						System.err.print("Il n'y a aucun rectangle � supprimer\n");
					}
				} else if (line.isSelected()) {
					if (listL.size() > 0) {
						System.out.println("Suppression de la derni�re ligne cr��e");
						pane.getChildren().remove(listL.get(listL.size()-1)); //Suppression de la derni�re ligne cr��e dans le pane
						listL.remove(listL.get(listL.size()-1)); //Suppression de la derni�re ligne cr��e dans la liste des lignes
					} else {
						System.err.print("Il n'y a aucune ligne � supprimer\n");
					}
				}
			}
		};
		
		EventHandler<MouseEvent> eventHandlerClone = new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				if (selectMove.isSelected() || (ellipse.isSelected())) {
					if (listET.size() == 1) {
						System.out.println("L'ellipse s�lectionn�e a �t� clon�e !");
						Ellipse clone = new Ellipse(listET.get(0).getCenterX()+10, listET.get(0).getCenterY()+10, listET.get(0).getRadiusX(), listET.get(0).getRadiusY());
						clone.setStroke(Color.BLACK); //Contour de l'ellipse en noir
						clone.setFill(listE.get(listE.size()-1).getFill());
						listE.add(clone);
						pane.getChildren().add(clone); //On ajoute le clone au pane
					}
				} else if (rectangle.isSelected()) {
					if (listR.size() > 0) {
						System.out.println("Clonage du dernier rectangle r�alis�");
						Rectangle clone = new Rectangle(listR.get(listR.size()-1).getX()+20, listR.get(listR.size()-1).getY()+20, listR.get(listR.size()-1).getWidth(), listR.get(listR.size()-1).getHeight());
						clone.setScaleX(listR.get(listR.size()-1).getScaleX());
						clone.setScaleY(listR.get(listR.size()-1).getScaleY());
						clone.setFill(listR.get(listR.size()-1).getFill());
						listR.add(clone);
						pane.getChildren().add(clone); //On ajoute le clone au pane
					} else {
						System.err.print("Il n'y a aucun rectangle � cloner\n");
					}
				} else if (line.isSelected()) {
					if (listL.size() > 0) {
						System.out.println("Clonage de la derni�re ligne r�alis�");
						Line clone = new Line(listL.get(listL.size()-1).getStartX()+5, listL.get(listL.size()-1).getStartY()+5, listL.get(listL.size()-1).getEndX()+5, listL.get(listL.size()-1).getEndY()+5);
						clone.setStrokeWidth(3); //Ajustement de la largeur de la ligne
			    		clone.setStroke(listL.get(listL.size()-1).getStroke()); //Ajout de la couleur de la ligne
						listL.add(clone);
						pane.getChildren().add(clone); //On ajoute le clone au pane
					} else {
						System.err.print("Il n'y a aucune ligne � cloner\n");
					}
				}
			}
		};
		
		EventHandler<MouseEvent> eventCouleur = new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				if (selectMove.isSelected() && (listET.size() == 1)) {
					listET.get(0).setFill(colorPicker.getValue());
				}
			}
		};
		
		pane.setOnMousePressed(eventHandler); //Ajout d'une forme g�om�trique
		pane.setOnMouseDragged(eventHandler2); //Modification de la taille de la forme g�om�trique
		
		colorPicker.setOnMouseClicked(eventCouleur);
		
		delete.setOnMouseClicked(eventHandlerDelete); //Suppression de la derni�re formes g�om�trique cr��e (en fonction de la s�lection du radioButton)
		clone.setOnMouseClicked(eventHandlerClone); //Clonage de la derni�re forme g�om�trique cr��e (en fonction de la s�lection du radioButton)
    }
}
