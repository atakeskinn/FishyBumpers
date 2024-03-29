package de.tum.in.ase.eist.usercontrol;

import com.sun.scenario.effect.impl.sw.java.JSWBlend_SRC_OUTPeer;
import de.tum.in.ase.eist.car.Car;
import de.tum.in.ase.eist.Point2D;
import de.tum.in.ase.eist.gameview.GameBoardUI;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import org.w3c.dom.ls.LSOutput;

/**
 * This class is responsible for the handling the MOUSE_PRESSED Event, 
 * i.e. the steering of the UserCar 
 */

public class MouseSteering  {

	private Car userCar;
	private GameBoardUI gameBoardUI;
	
	/**
	 * Constructor, creates a MouseSteering instance with a specific playingFlied and userCar
	 * @param gameBoardUI
	 * @param userCar
	 */
	public MouseSteering(GameBoardUI gameBoardUI, Car userCar) {
		this.userCar = userCar;
		this.gameBoardUI = gameBoardUI;
		this.gameBoardUI.addEventHandler(MouseEvent.MOUSE_PRESSED, this.mouseHandler);
	}
	
	public Car getUserCar() {
		return this.userCar;
	}
	
	public void setUserCar(Car userCar) {
		this.userCar = userCar;
	}
	
	public GameBoardUI getGameBoardUI() {
		return this.gameBoardUI;
	}
	
	public void setGameBoardUI(GameBoardUI gameBoardUI) {
		this.gameBoardUI = gameBoardUI;
	}
	
	EventHandler<MouseEvent> mouseHandler = new EventHandler<MouseEvent>() {

        @Override
        public void handle(MouseEvent e) {
        	Point2D carPosition = MouseSteering.this.getUserCar().getPosition();
        	Point2D clickPosition = MouseSteering.this.getGameBoardUI().convertPosition(new Point2D(e.getX(), e.getY()));
    		int deltaX = (int) (clickPosition.getX() - carPosition.getX());
    		deltaX = Math.abs(deltaX);
    		int deltaY = (int) (clickPosition.getY() - carPosition.getY());
    		double diff = ((double)deltaY) / ((double)deltaX);
    		double theta = Math.atan(diff);
    		int degree = (int)Math.toDegrees(theta);

    		if(clickPosition.getX() > carPosition.getX()) {
    			degree = 90 - degree;
    		} else {
    			degree = 270 + degree;
    		}
    		userCar.setDirection(degree);
        }
     
    };

}
