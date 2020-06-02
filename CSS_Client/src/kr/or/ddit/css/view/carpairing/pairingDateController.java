package kr.or.ddit.css.view.carpairing;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.ResourceBundle;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import kr.or.ddit.css.session.CarDetailSession;
import kr.or.ddit.css.vo.CarPairingUseBoardVO;

public class pairingDateController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane dateMainRoot;

    @FXML
    private Label lbTitle;

    @FXML
    private Label lbTitle1;

    @FXML
    private Label lbTitle11;

    @FXML
    private Label lbTitle12;

    @FXML
    private DatePicker dateBtn;

    @FXML
    private TextField startDate;

    @FXML
    private TextField endDate;

    @FXML
    private TableView<Time> tableDate;

    @FXML
    private TableColumn<Time,String> dateCol;

    @FXML
    private Pagination pageView;

    @FXML
    void btnNext(MouseEvent event) throws IOException {	// 다음 단계 버튼 클릭 메서드(픽업장소)
    	if(dateBtn.getValue()==null) {
    		alert("입력오류", "연/월/일을 입력해주세요.");
    	}else if(dateBtn.getValue() != null) {
    		if(startDate.getText().equals("")) {
        		alert("입력오류", "카셰어링 시작 시간을 입력해주세요.");
        	}else if(!startDate.getText().equals("")) {
        		if(endDate.getText().equals("")) {
            		alert("입력오류", "카셰어링 종료 시간을 입력해주세요.");
            	}else if(!endDate.getText().equals("")){
            		System.out.println("startDate : " + startDate);
            		CarDetailSession.rentDate = dateBtn.getValue().toString();
                	
                	AnchorPane pane = FXMLLoader.load(getClass().getResource("btn1CarSharingMenuInsert3.fxml"));
                	//Stage primaryStage = 
                	dateMainRoot.getChildren().setAll(pane);
            	}
        	}
    	}
    }

    @FXML
    void btnPrevious(MouseEvent event) throws IOException {	// 이전 단계 버튼 클릭 메서드(차량)
    	AnchorPane pane = FXMLLoader.load(getClass().getResource("btn1CarSharingMenuInsert1.fxml"));
    	//Stage primaryStage = 
    	dateMainRoot.getChildren().setAll(pane);
    }

    @FXML
    void startBtn(ActionEvent event) {
    	if(tableDate.getSelectionModel().getSelectedItem()==null) {
    		alert("입력오류","시작 시간을 선택 후 버튼을 눌러주세요.");
    	}else {
    		startDate.setText(tableDate.getSelectionModel().getSelectedItem().date);
        	CarDetailSession.rentStart = startDate.getText();
    	}
    }
    
    @FXML
    void endBtn(ActionEvent event) {
    	if(tableDate.getSelectionModel().getSelectedItem()==null) {
    		alert("입력오류","종료 시간을 선택 후 버튼을 눌러주세요.");
    	}else {
	    	endDate.setText(tableDate.getSelectionModel().getSelectedItem().date);
	    	CarDetailSession.rentEnd = endDate.getText();
    	}
    }

    private int itemsForPage = 8; // 한페이지 보여줄 항목 수 설정
    private ObservableList<Time> dataList = FXCollections.observableArrayList(	// 시간 데이터
			new Time("01시00분"),
			new Time("02시00분"),
			new Time("03시00분"),
			new Time("04시00분"),
			new Time("05시00분"),
			new Time("06시00분"),
			new Time("07시00분"),
			new Time("08시00분"),
			new Time("09시00분"),
			new Time("10시00분"),
			new Time("11시00분"),
			new Time("12시00분"),
			new Time("13시00분"),
			new Time("14시00분"),
			new Time("15시00분"),
			new Time("16시00분"),
			new Time("17시00분"),
			new Time("18시00분"),
			new Time("19시00분"),
			new Time("20시00분"),
			new Time("21시00분"),
			new Time("22시00분"),
			new Time("23시00분"),
			new Time("24시00분")
    );
    
    @FXML
    void initialize() {
    	
    	dateCol.setCellValueFactory(new PropertyValueFactory<Time, String>("date"));
    	
		int totPageCount = dataList.size()%itemsForPage == 0
							? dataList.size()/itemsForPage
							: dataList.size()/itemsForPage + 1;
							
		pageView.setPageCount(totPageCount); // 전체 페이지 수 설정
  	
		pageView.setCurrentPageIndex(0);	//1페이지를 보여줌
		//3. TableView에 셋팅하기
    	//tbResult.setItems(data);
		changeTableView(0); // 처음 첫페이지 보여주기
		
		pageView.currentPageIndexProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				changeTableView(newValue.intValue());
			}
		});
		
//    	int itemsForPage = 10; // 한페이지 보여줄 항목 수 설정
//		int totPageCount = dataList.size()%itemsForPage == 0
//							? dataList.size()/itemsForPage
//							: dataList.size()/itemsForPage + 1;
//							
//		pageView.setPageCount(totPageCount); // 전체 페이지 수 설정
//    	
//    	tableDate.setItems(dataList);
//    	tableDate.getColumns().addAll(dateCol);
    	
    }
    
    // 페이지 세팅 메서드
    public void changeTableView(int index){
 		int fromIndex = index * itemsForPage;	//시작위치
 		int toIndex = Math.min(fromIndex + itemsForPage, dataList.size());	//종료위치(둘을 비교해서 작은값이 종료위치)
 		//data.subList(fromIndex, toIndex);	//사직위치부터 종료위치 (종료위치는 포함X)이전 까지의 자료 추출
 		List<Time> list = dataList.subList(fromIndex, toIndex);
 		//System.out.println("size = " + list.size());
 		//시작위치부터 종료위치 이전까지의 자료를 TableView에 셋팅한다.
 		tableDate.setItems(FXCollections.observableArrayList(list));
 	}
    
    // 시간을 저장하는 클래스
    public class Time {
    	private String date;
    	
    	public Time(String date) {
    		this.date = date;
    	}

    	public String getDate() {
    		return date;
    	}

    	public void setDate(String date) {
    		this.date = date;
    	}
    }
    
  //----------------------------------------------------------alert, infoMsg, confirm 메서드 설정 ---------------------------
    public void alert(String head, String msg) {
		Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle("경고");
		alert.setHeaderText(head);
		alert.setContentText(msg);
		
		alert.showAndWait();
	}
	
	public void infoMsg(String head, String msg) {
		Alert info = new Alert(AlertType.INFORMATION);
		info.setTitle("정보");
		info.setHeaderText(head);
		info.setContentText(msg);
		
		info.showAndWait();
	}
    
    public ButtonType confirm(String head, String msg) {
    	Alert confirm = new Alert(AlertType.CONFIRMATION);
    	confirm.setTitle("확인");
    	confirm.setHeaderText(head);
    	confirm.setContentText(msg);
    	
    	return confirm.showAndWait().get();
    }
    //--------------------------------------------------------------------------------------------------------------------
}







