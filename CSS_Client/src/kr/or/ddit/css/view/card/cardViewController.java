package kr.or.ddit.css.view.card;

import java.io.IOException;
import java.net.URL;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import kr.or.ddit.css.service.card.ICardService;
import kr.or.ddit.css.session.AdminLoginSession;
import kr.or.ddit.css.session.LoginSession;
import kr.or.ddit.css.vo.CardVO;
import kr.or.ddit.css.vo.LicenseVO;

public class cardViewController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane cardRoot;

    @FXML
    private Label lbTitle;

    @FXML
    private Label lbTitle11;

    @FXML
    private Label lbTitle1;

    @FXML
    private Label cardCheck;

    @FXML
    private HBox hbox1;

    @FXML
    private Label cardNum;

    @FXML
    private HBox hbox3;

    @FXML
    private Label cardMemName;

    @FXML
    private Button btnInsert;

    @FXML
    private Button btnDelete;

    @FXML
    void InsertOk(ActionEvent event) throws IOException {
    	AnchorPane pane = FXMLLoader.load(getClass().getResource("cardInsert.fxml"));
    	cardRoot.getChildren().setAll(pane);
    }

    @FXML
    void deleteOk(ActionEvent event) throws IOException {
//    	String memId = LoginSession.session.getMem_id();
//    	
//    	Alert alertConfirm = new Alert(AlertType.CONFIRMATION);
//		
//		alertConfirm.setTitle("CONFIRMATION");
//		alertConfirm.setContentText("삭제하시겠습니까?");
//		
//		ButtonType confirmResult = alertConfirm.showAndWait().get();
//		
//		if(confirmResult == ButtonType.OK) {
//			System.out.println("삭제 되었습니다.");
//			
//			int cnt=0;
//			try {
//				cnt = service.deleteCard(memId);
//			} catch (RemoteException e1) {
//				
//				e1.printStackTrace();
//			}
//			if(cnt > 0) {
//				infoMsg("정보","결제카드 삭제가 완료되었습니다");
//				ObservableList<CardVO> data_edit = FXCollections.observableArrayList();
//				List<CardVO> list_edit = new ArrayList<>();
//				
//				AnchorPane pane = FXMLLoader.load(getClass().getResource("../menu/projectMenu.fxml"));
//				cardRoot.getScene().setRoot(pane);
//			}
//		}else {
//			alert("입력오류","면허 삭제를 실패하였습니다.");
//		}
    	AnchorPane pane = FXMLLoader.load(getClass().getResource("../menu/projectMenu.fxml"));
    	cardRoot.getScene().setRoot(pane);
    }
    
    private ICardService service;
    private ObservableList<CardVO> data;
    private Registry reg;
    
    private String memId = "";
    
    @FXML
    void initialize() {
    	try {
			reg = LocateRegistry.getRegistry("localhost", 8899);
			service = (ICardService) reg.lookup("card");
			
			if(AdminLoginSession.adminSession==null) {
				memId=LoginSession.session.getMem_id();
				data = FXCollections.observableArrayList(service.getMemCard(memId));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
    	if(LoginSession.session==null) {
    		cardCheck.setText("회원 이용페이지입니다.");
    		hbox1.setVisible(false);
    		hbox3.setVisible(false);
    		btnInsert.setVisible(false);
    		btnDelete.setVisible(false);
    	}
    	if(AdminLoginSession.adminSession==null) {
    		if(data.isEmpty()) {
    			cardCheck.setText("결제카드를 등록해주세요");
    			hbox1.setVisible(false);
        		hbox3.setVisible(false);
    		}else {
    			cardCheck.setText("결제카드가 등록되어 있습니다.");
    			String nowCardNum=data.get(0).getCard_num().substring(0,9)+"**** ****";
    			cardNum.setText(nowCardNum);
    			cardMemName.setText(LoginSession.session.getMem_name());
    			btnInsert.setVisible(false);
    			
    		}
    	}
    }
    public void infoMsg(String headerText, String msg) {
	      Alert alert = new Alert(AlertType.INFORMATION);
	      alert.setTitle("정보 확인");
	      alert.setHeaderText(headerText);
	      alert.setContentText(msg);
	      alert.showAndWait();

	}
  public void alert(String head, String msg) {
		Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle("경고");
		alert.setHeaderText(head);
		alert.setContentText(msg);
		
		alert.showAndWait();
		
	}
}

