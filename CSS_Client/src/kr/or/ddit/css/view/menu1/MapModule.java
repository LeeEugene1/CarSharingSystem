package kr.or.ddit.css.view.menu1;

import java.io.File;
import java.net.URL;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import kr.or.ddit.css.session.LoginSession;
import kr.or.ddit.css.view.signUp.*;
import netscape.javascript.JSObject;

public class MapModule extends Application {

	private JSObject javascriptConnector;

    private JavaConnector javaConnector = new JavaConnector();
    
	@Override
	public void start(Stage primaryStage) throws Exception{
		
        WebView webView = new WebView();
        final WebEngine webEngine = webView.getEngine();

        webEngine.getLoadWorker().stateProperty().addListener((observable, oldValue, newValue) -> {
            if (Worker.State.SUCCEEDED == newValue) {
                JSObject window = (JSObject) webEngine.executeScript("window");
                window.setMember("javaConnector", javaConnector);

            }
        });
        
        BorderPane root = new BorderPane();
		root.setPrefSize(500, 500);
		root.setCenter(webView);
        Scene scene = new Scene(root);
        
        primaryStage.setTitle("지도 검색");
        primaryStage.setScene(scene);
        primaryStage.show();
        
        webEngine.load("http://localhost:8081/SignUpTest/member/map2.jsp");
		
	}

	public class JavaConnector{
		
	    public void addrFromJS(String addr) {
	        
	        try {
	        	btn1CarSharingMenuController3 controller = LoginSession.bcsmc;
	        	// addr = "
	        	int idx = addr.indexOf(": "); 
	        	int idx2 = addr.indexOf("</");
	        	System.out.println("idx : " + idx);
	        	System.out.println("idx2 : " + idx2);
	        	String map = addr.substring(idx+2, idx2);
	        	System.out.println("map : " + map);
//	        	controller.getTfSearch().setText(map);
	        	
	        	//3. 특정단어(부분)만 자르기
//	        	String str = "바나나 : 1000원, 사과 : 2000원, 배 : 3000원";
//	        	String target = "사과";
//	        	int target_num = str.indexOf(target); 
//	        	String result; result = str.substring(target_num,(str.substring(target_num).indexOf("원")+target_num));
//	        	System.out.println(result+"원"); 
	        	//결과값 : 사과 : 2000원
	        	
	        	LoginSession.bcsmc = null;
	        	
			} catch (Exception e) {
				e.printStackTrace();
			}
	    }
		
	    // 위도 / 경도
//	    public void pointFromJS(double pointY, double pointX) {
//	    	System.out.println("[POINT]");
//	    	System.out.println(pointY);
//	        System.out.println(pointX);
//	    }

	}
}

