package kr.or.ddit.css.view.license;

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
import kr.or.ddit.css.service.license.ILicenseService;
import kr.or.ddit.css.session.AdminLoginSession;
import kr.or.ddit.css.session.LoginSession;
import kr.or.ddit.css.vo.CommunityBoardVO;
import kr.or.ddit.css.vo.LicenseVO;

public class licenseViewController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane licenseRoot;

    @FXML
    private Label lbTitle;

    @FXML
    private Label lbTitle11;

    @FXML
    private Label lbTitle1;

    @FXML
    private Label licenseCheck;

    @FXML
    private HBox hbox1;

    @FXML
    private Label licenseNum;

    @FXML
    private HBox hbox2;

    @FXML
    private Label licenseType;

    @FXML
    private HBox hbox3;

    @FXML
    private Label licenseMemName;

    @FXML
    private Button btnInsert;

    @FXML
    private Button btnDelete;

    @FXML
    void InsertOk(ActionEvent event) throws IOException {
    	AnchorPane pane = FXMLLoader.load(getClass().getResource("licenseInsert.fxml"));
    	licenseRoot.getChildren().setAll(pane);
    	
    }

    @FXML
    void deleteOk(ActionEvent event) throws IOException {
    	String memId = LoginSession.session.getMem_id();
    	
    	Alert alertConfirm = new Alert(AlertType.CONFIRMATION);
		
		alertConfirm.setTitle("CONFIRMATION");
		alertConfirm.setContentText("삭제하시겠습니까?");
		
		ButtonType confirmResult = alertConfirm.showAndWait().get();
		
		if(confirmResult == ButtonType.OK) {
			System.out.println("삭제 되었습니다.");
			
			int cnt=0;
			try {
				cnt = service.deleteLicense(memId);
			} catch (RemoteException e1) {
				
				e1.printStackTrace();
			}
			if(cnt > 0) {
				infoMsg("정보","면허 삭제가 완료되었습니다");
				ObservableList<LicenseVO> data_edit = FXCollections.observableArrayList();
				List<LicenseVO> list_edit = new ArrayList<>();
				
				AnchorPane pane = FXMLLoader.load(getClass().getResource("../menu/projectMenu.fxml"));
				licenseRoot.getScene().setRoot(pane);
			}
		}else {
			alert("입력오류","면허 삭제를 실패하였습니다.");
		}
    	
    }
    
    private ILicenseService service;
    private ObservableList<LicenseVO> data;
    private Registry reg;
    
    private String memId = "";
    
    @FXML
    void initialize() throws RemoteException {
    	try {
			reg = LocateRegistry.getRegistry("localhost", 8899);
			service = (ILicenseService) reg.lookup("license");
			
			if(AdminLoginSession.adminSession==null) {
				memId=LoginSession.session.getMem_id();
				data = FXCollections.observableArrayList(service.getMemLicense(memId));
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
    	
    	if(LoginSession.session==null) {
    		licenseCheck.setText("회원 이용페이지 입니다.");
    		hbox1.setVisible(false);
    		hbox2.setVisible(false);
    		hbox3.setVisible(false);
    		btnInsert.setVisible(false);
    		btnDelete.setVisible(false);
    	}
    	if(AdminLoginSession.adminSession==null) {
    		if(data.isEmpty()) {
        		licenseCheck.setText("운전면허증을 등록해주세요");
        		hbox1.setVisible(false);
        		hbox2.setVisible(false);
        		hbox3.setVisible(false);
        	}else {
        		licenseCheck.setText(memId+"님의 면허 정보");
        		licenseNum.setText(data.get(0).getLicense_num());
        		licenseType.setText(data.get(0).getLicense_type());
        		licenseMemName.setText(LoginSession.session.getMem_name());
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
