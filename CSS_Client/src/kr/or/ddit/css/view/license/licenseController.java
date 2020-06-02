package kr.or.ddit.css.view.license;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

import com.google.cloud.vision.v1.AnnotateImageRequest;
import com.google.cloud.vision.v1.AnnotateImageResponse;
import com.google.cloud.vision.v1.BatchAnnotateImagesResponse;
import com.google.cloud.vision.v1.Feature;
import com.google.cloud.vision.v1.Image;
import com.google.cloud.vision.v1.ImageAnnotatorClient;
import com.google.cloud.vision.v1.Feature.Type;
import com.google.protobuf.ByteString;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import kr.or.ddit.css.service.carpairing.ICarPairingInsertService;
import kr.or.ddit.css.service.license.ILicenseService;
import kr.or.ddit.css.session.LoginSession;
import kr.or.ddit.css.vo.LicenseVO;

public class licenseController {

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
    private TextField tfLicense;

    @FXML
    private Button btnFile;

    @FXML
    private Button btnFile2;

    @FXML
    private TextField tfName;

    @FXML
    private TextField tfType;

    @FXML
    private TextField tfNum;

    @FXML
    private TextField tfStart;

    @FXML
    private TextField tfEnd;

    @FXML
    private Button btnInsert;

    @FXML
    private Button btnCancle;

    @FXML
    void InsertOk(ActionEvent event) throws IOException {
    	String memName = tfName.getText();
    	if(memName.isEmpty()) {
    		alert("입력오류","이름이 입력되지 않았습니다.");
    		return;
    	}
    	String licenseNum = tfNum.getText();
    	if(licenseNum.isEmpty()) {
    		alert("입력오류","면허번호가 입력되지 않았습니다.");
    		return;
    	}
    	String licenseType = tfType.getText();
    	if(licenseType.isEmpty()) {
    		alert("입력오류","면허종류가 입력되지 않았습니다.");
    		return;
    	}
    	String licenseStart = tfStart.getText();
    	if(licenseStart.isEmpty()) {
    		alert("입력오류","적성검사 기간 시작일이 입력되지 않았습니다.");
    		return;
    	}
    	String licenseEnd = tfEnd.getText();
    	if(licenseEnd.isEmpty()) {
    		alert("입력오류","적성검사 기간 종료일이 입력되지 않았습니다.");
    		return;
    	}
    	
    	String nowMemName=LoginSession.session.getMem_name();
    	if(!memName.equals(nowMemName)) {
    		alert("입력오류","본인의 면허증만 등록 가능합니다.");
    		return;
    	}
    	
    	String nowMemId = LoginSession.session.getMem_id();
    	LicenseVO licenseVo = new LicenseVO();
    	
    	licenseStart=licenseStart.replace(".", "-");
    	licenseStart=licenseStart.substring(0, licenseStart.length()-1);
    	
    	licenseEnd=licenseEnd.replace(".", "-");
    	licenseEnd=licenseEnd.substring(0, licenseEnd.length()-1);
    	
    	
    	
    	licenseVo.setLicense_num(licenseNum);
    	licenseVo.setMem_id(nowMemId);
    	licenseVo.setLicense_type(licenseType);
    	licenseVo.setLicense_start(licenseStart);
    	licenseVo.setLicense_end(licenseEnd);
    	
    	int cnt = service.insertLicense(licenseVo);
    	if(cnt>0) {
    		List<LicenseVO> licenseList = service.getAllLicense();
    		data=FXCollections.observableArrayList(licenseList);
    		infoMsg("작업성공","면허증을 등록하였습니다.");
    		AnchorPane pane = FXMLLoader.load(getClass().getResource("../menu/projectMenu.fxml"));
    		licenseRoot.getScene().setRoot(pane);
    	}else {
    		alert("작업실패","면허증 등록을 실패하였습니다.");
    	}
    	
    }

    @FXML
    void cancleOk(ActionEvent event) throws IOException {
    	AnchorPane pane = FXMLLoader.load(getClass().getResource("../menu/projectMenu.fxml"));
		licenseRoot.getScene().setRoot(pane);
    }

    @FXML
    void licenseImg(ActionEvent event) {
    	FileChooser fc = new FileChooser();
    	fc.getExtensionFilters().add(new ExtensionFilter("Image File", "*.png", "*.jpg", "*.gif"));
    	File f = fc.showOpenDialog(null);
    	tfLicense.setText(f.getAbsolutePath());
    }
    
    @FXML
    void licenseText(ActionEvent event) {
    	try {
    		
			String imageFilePath = tfLicense.getText();
			
			List<AnnotateImageRequest> requests = new ArrayList<>();
		
			ByteString imgBytes = ByteString.readFrom(new FileInputStream(imageFilePath));
		
			Image img = Image.newBuilder().setContent(imgBytes).build();
			Feature feat = Feature.newBuilder().setType(Type.TEXT_DETECTION).build();
			AnnotateImageRequest request = AnnotateImageRequest.newBuilder().addFeatures(feat).setImage(img).build();
			requests.add(request);
		
			try (ImageAnnotatorClient client = ImageAnnotatorClient.create()) {
				BatchAnnotateImagesResponse response = client.batchAnnotateImages(requests);
			    List<AnnotateImageResponse> responses = response.getResponsesList();
		
			    for (AnnotateImageResponse res : responses) {
			    	if (res.hasError()) {
			    		System.out.printf("Error: %s\n", res.getError().getMessage());
			    		return;
			    	}
		
			    	System.out.println("Text : ");
			    	System.out.println(res.getTextAnnotationsList().get(0).getDescription());
			    	String test = res.getTextAnnotationsList().get(0).getDescription();
			    	
			    	String[] results = test.split("\n");
			    	
			    	//System.out.println(Arrays.toString(results) );
			    	//System.out.println(results[1]);
			    	
			    	results[1]=results[1].substring(5,9);
			    	
			    	tfName.setText(results[5]);
			    	tfName.setEditable(false);
			    	
			    	tfType.setText(results[1]);
			    	tfType.setEditable(false);
			    	
			    	tfNum.setText(results[3]);
			    	tfNum.setEditable(false);
			    	
			    	tfStart.setText(results[16]);
			    	tfStart.setEditable(false);
			    	
			    	tfEnd.setText(results[17]);
			    	tfEnd.setEditable(false);
			    	
			    	
			      
			    	// For full list of available annotations, see http://g.co/cloud/vision/docs
			    	/*for (EntityAnnotation annotation : res.getTextAnnotationsList()) {
				    	  
						//System.out.printf("Text: %s\n", annotation.getDescription());
						//System.out.printf("Position : %s\n", annotation.getBoundingPoly());
					}*/
			    }
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
    }
    
    private ILicenseService service;
    private ObservableList<LicenseVO> data;
    private Registry reg;
    
    
    @FXML
    void initialize() {
    	try {
			reg = LocateRegistry.getRegistry("localhost", 8899);
			service = (ILicenseService) reg.lookup("license");
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    public void alert(String head, String msg) {
		Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle("경고");
		alert.setHeaderText(head);
		alert.setContentText(msg);
		
		alert.showAndWait();
		
	}
    
    public void infoMsg(String head,String msg) {
    	Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("정보");
		alert.setHeaderText(head);
		alert.setContentText(msg);
		
		alert.showAndWait();
    }
}
