package kr.or.ddit.css.view.menu1;

import java.io.IOException;
import java.net.URL;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import kr.or.ddit.css.service.carsharing.ICarSharingService;
import kr.or.ddit.css.service.community.ICommunityService;
import kr.or.ddit.css.session.CarDetailSession;
import kr.or.ddit.css.vo.CarVO;
import kr.or.ddit.css.vo.CommunityBoardVO;

public class btn1CarSharingMenuSportsController1 {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane communityMainRoot;

    @FXML
    private Label lbTitle;

    @FXML
    private Label lbTitle1;

    @FXML
    private Label lbTitle11;

    @FXML
    private Label lbTitle12;
    
    @FXML
    private ImageView imgmain;

    @FXML
    private Label lbCarName;

    @FXML
    private Label lbCarType;

    @FXML
    private Label lbCarRentCost;

    @FXML
    private Label lbCarDriveCost;
   
    @FXML
    private TableView<CarVO> tableCar;

    @FXML
    private TableColumn<CarVO, String> imgCol;

    @FXML
    private TableColumn<?, ?> contentCol;

   
    @FXML
    private Pagination pageView;
    
    @FXML
    void carDetail(MouseEvent event) throws IOException {
    	Stage dialog = new Stage(StageStyle.UTILITY);
    	dialog.initModality(Modality.WINDOW_MODAL);
    	
    	Parent child = FXMLLoader.load(btn1CarSharingMenuSportsController1.class.getResource("detailCar.fxml"));
    	Scene scene = new Scene(child);
    	
    	dialog.setScene(scene);
		dialog.setTitle("css_detail_img");
		dialog.setResizable(false); 	//창크기 변경 불가
		dialog.show();
    	
    	
    }
    
    @FXML
    void choiceCar(MouseEvent event) {
    	
    	if(tableCar.getSelectionModel().isEmpty())
    		return;
    	ObservableList<CarVO> temp = tableCar.getSelectionModel().getSelectedItems();
    	String carImg = tableCar.getSelectionModel().getSelectedItem().getCar_img();
    	imgmain.setImage(new Image(btn1CarSharingMenuSportsController1.class.getResourceAsStream("/img_car/" + carImg)));
    	lbCarName.setText(tableCar.getSelectionModel().getSelectedItem().getCar_name());
    	lbCarType.setText(tableCar.getSelectionModel().getSelectedItem().getCar_oiltype());
    	lbCarRentCost.setText(tableCar.getSelectionModel().getSelectedItem().getCar_rentcost()+"원");
    	lbCarDriveCost.setText(tableCar.getSelectionModel().getSelectedItem().getCar_drivecost()+"원");
    	
    	CarDetailSession.carDetailSession=tableCar.getSelectionModel().getSelectedItem().getCar_img1();
    	CarDetailSession.carName=tableCar.getSelectionModel().getSelectedItem().getCar_name();
    	CarDetailSession.carNum=tableCar.getSelectionModel().getSelectedItem().getCar_num();
    	CarDetailSession.carRentCost=tableCar.getSelectionModel().getSelectedItem().getCar_rentcost();
    	CarDetailSession.cartimeCost=tableCar.getSelectionModel().getSelectedItem().getCar_drivecost();
    }

    @FXML
    void btnNext(MouseEvent event) throws IOException {
    	if(tableCar.getSelectionModel().getSelectedItem()==null) {
    		alert("입력오류", "차량을 선택해주세요.");
    	}else {
	    	AnchorPane pane = FXMLLoader.load(getClass().getResource("btn1CarSharingMenuInsert2.fxml"));
	    	//Stage primaryStage = 
	    	communityMainRoot.getChildren().setAll(pane);
    	}
    }

    @FXML
    void btnPrevious(MouseEvent event) throws IOException {
    	AnchorPane pane = FXMLLoader.load(getClass().getResource("btn1CarSharingMenu.fxml"));
    	//Stage primaryStage = 
    	communityMainRoot.getChildren().setAll(pane);
    }
    
    private ICarSharingService service;
    private ObservableList<CarVO> data;
    private List<CarVO> carList;
    private Registry reg;
    private int itemsForPage = 4;
    String carInImg = "";
    
    @FXML
    void initialize() throws RemoteException {
    	try {
			reg = LocateRegistry.getRegistry("localhost", 8899);
			service = (ICarSharingService) reg.lookup("carSharing");
		} catch (Exception e) {
			e.printStackTrace();
		}
    	
    	
    	//imgCol.setPrefWidth(80);
    	imgCol.setCellValueFactory(new PropertyValueFactory<>("car_img"));
    	contentCol.setCellValueFactory(new PropertyValueFactory<>("car_name"));
    	
    	imgCol.setCellFactory(new Callback<TableColumn<CarVO,String>, TableCell<CarVO,String>>() {
			
			@Override
			public TableCell<CarVO, String> call(TableColumn<CarVO, String> param) {
				TableCell<CarVO, String> cell = new TableCell<CarVO, String>(){
					@Override
					protected void updateItem(String item, boolean empty) {
						super.updateItem(item, empty);
						//System.out.println(item + ", " + empty);
						if(item!=null) {
							ImageView imgView = new ImageView();
							imgView.setFitHeight(90);
							imgView.setFitWidth(120);
							
							imgView.setImage(new Image(btn1CarSharingMenuSportsController1.class.getResourceAsStream("/img_car/" + item)));
							setGraphic(imgView);
							
						}else {
							setGraphic(null);
						}
					}
				};
				return cell;
			}
		});
    	
    	
    	
    	int carTypeId = 5; 
    	
    	//ImageView carimg = new ImageView(new Image(this.getClass().getResourceAsStream("L_RP.png")));
    	
    	carList = service.getAllCarList(carTypeId);
    	//data = FXCollections.observableArrayList(carList);
    	
    	//itemsForPage = 3; // 한페이지 보여줄 항목 수 설정
		int totPageCount = carList.size()%itemsForPage == 0
							? carList.size()/itemsForPage
							: carList.size()/itemsForPage + 1;
							
		pageView.setPageCount(totPageCount);
		//처음 선택될 페이지의 index값 설정 ==> 0부터 시작
		pageView.setCurrentPageIndex(0);	//1페이지를 보여줌
				
				
		changeTableView(0); // 처음 첫페이지 보여주기
		
		pageView.currentPageIndexProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				changeTableView(newValue.intValue());
			}
		});
		
		//tableCar.setItems(data);
    }
    // Pagination에서 선택한 페이지의 index를 매개변수로 받아서 
 	// 해당 index번째에 맞는 데이터를 가져와 TableView에 다시 셋팅하는 메서드
 	public void changeTableView(int index){
 		int fromIndex = index * itemsForPage;	//시작위치
 		int toIndex = Math.min(fromIndex + itemsForPage, carList.size());	//종료위치(둘을 비교해서 작은값이 종료위치)
 		//data.subList(fromIndex, toIndex);	//사직위치부터 종료위치 (종료위치는 포함X)이전 까지의 자료 추출
 		List<CarVO> list = carList.subList(fromIndex, toIndex);
 		//System.out.println("size = " + list.size());
 		//시작위치부터 종료위치 이전까지의 자료를 TableView에 셋팅한다.
 		tableCar.setItems(FXCollections.observableArrayList(list));
 	}
 	
 	public void alert(String head, String msg) {
		Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle("경고");
		alert.setHeaderText(head);
		alert.setContentText(msg);
		
		alert.showAndWait();
	}
    
}
