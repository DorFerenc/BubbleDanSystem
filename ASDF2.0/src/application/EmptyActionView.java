package application;

import MVC.UIEventsListener;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;

public class EmptyActionView implements actionTemplate{
	

    @FXML private ImageView imgBubble;
    
	@Override
	public void initData() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void registerListener(UIEventsListener myListener) {
		// TODO Auto-generated method stub
		
	}
	
	public void noImage() {
		imgBubble.setVisible(false);
	}

}
