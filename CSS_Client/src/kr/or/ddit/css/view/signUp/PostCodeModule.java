package kr.or.ddit.css.view.signUp;

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

public class PostCodeModule extends Application {

	private JSObject javascriptConnector;

    private JavaConnector javaConnector = new JavaConnector();
    
	@Override
	public void start(Stage primaryStage) throws Exception{
		
//		URL url = this.getClass().getResource("/kr/or/ddit/fairplay/module/postcode.html").toURI().toURL();
		
        WebView webView = new WebView();
        final WebEngine webEngine = webView.getEngine();

        // set up the listener
        webEngine.getLoadWorker().stateProperty().addListener((observable, oldValue, newValue) -> {
            if (Worker.State.SUCCEEDED == newValue) {
                JSObject window = (JSObject) webEngine.executeScript("window");
                window.setMember("javaConnector", javaConnector);

//                javascriptConnector = (JSObject) webEngine.executeScript("getJsConnector()");
            }
        });
        
        BorderPane root = new BorderPane();
		root.setPrefSize(500, 500);
		root.setCenter(webView);
        Scene scene = new Scene(root);
        
        primaryStage.setTitle("우편번호 검색");
        primaryStage.setScene(scene);
        primaryStage.show();
        
        webEngine.load("http://localhost:8081/SignUpTest/member/postcode.html");
        		
		
	}

	
	public class JavaConnector{
		
	    public void addrFromJS(String addr,String sido) {
//	        System.out.println("fromJS = "+addr);
//	        System.out.println("fromJS = "+sido);
	        
	        try {
//	        	FXMLLoader loader = new FXMLLoader(getClass().getResource("./projectSignUp2.fxml"));
//	        	loader.load();
//	        	projectSignUp2Controller controller = loader.getController();
	        	projectSignUp2Controller controller = LoginSession.psuc;
	        	controller.getTfAdd1().setText(addr);
	        	
	        	System.out.println("addr" + addr);
//	        	
	        	
//	        	LoginSession.session.setMem_addr(addr);
//	        	projectSignUp2Controller psuc = null;
//	        	psuc.tfEmail2.setText(addr);
	        	
	        	LoginSession.psuc = null;
	        	
//	 	       	System.out.println("Controller addr = " + controller.addr);
//	 	       	controller.addrFunc(addr);
//	 	       	controller.primaryStage3.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
	    }
		
	    // 위도 / 경도
	    public void pointFromJS(double pointY, double pointX) {
	    	System.out.println("[POINT]");
	    	System.out.println(pointY);
	        System.out.println(pointX);
	    }

	}
}

