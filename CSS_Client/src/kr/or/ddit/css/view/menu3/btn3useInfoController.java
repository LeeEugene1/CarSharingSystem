package kr.or.ddit.css.view.menu3;

import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import org.omg.CORBA.REBIND;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import kr.or.ddit.css.service.card.ICardService;
import kr.or.ddit.css.service.carsharing.ICarSharingService;
import kr.or.ddit.css.service.rent.IRentService;
import kr.or.ddit.css.session.CarDetailSession;
import kr.or.ddit.css.session.LoginSession;
import kr.or.ddit.css.vo.QnAVO;
import kr.or.ddit.css.vo.RentVO;

public class btn3useInfoController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane useMainRoot;

    @FXML
    private Label lbTitle;

    @FXML
    private Label lbTitle1;

    @FXML
    private Label lbTitle11;

    @FXML
    private TableView<TableVO> tbResult;

    @FXML
    private TableColumn<?, ?> colDate;

    @FXML
    private TableColumn<?, ?> colCarName;

    @FXML
    private TableColumn<?, ?> colAddr;

    @FXML
    private TableColumn<?, ?> colCost;

    private ICarSharingService carService;
    private IRentService rentService;
    private ObservableList<TableVO> data;
    
    private List<RentVO> list = new ArrayList<RentVO>();
    private String carName = "";
    
    @FXML
    void initialize() throws RemoteException {
    	Registry reg;
		try {
			reg = LocateRegistry.getRegistry("localhost",8899);
			carService = (ICarSharingService) reg.lookup("carSharing");
			rentService = (IRentService) reg.lookup("rent");
		} catch (RemoteException e1) {
			e1.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
		
		String memId = LoginSession.session.getMem_id();

		list = rentService.selectRentList(memId);
		
		if(list==null || list.size()==0) {
			alert("입력오류", "이용내역이 없습니다.");
		}else {
			String carNum = list.get(0).getCar_num();
			
			carName = carService.getCarName(carNum);
			if(carName == null) {
				alert("입력오류", "carName 반환 실패");
			}else if(carName != null) {
				ObservableList<TableVO> tableList = FXCollections.observableArrayList(
		    			new TableVO(new String(list.get(0).getRent_start() +" ~ "+list.get(0).getRent_end()),
		    					new String(carName),
		    					new String(list.get(0).getRent_addr()),
		    					new String(list.get(0).getRent_cost()+"원"))
		    		);
				
				colDate.setCellValueFactory(new PropertyValueFactory<>("rentDate"));
		    	colCarName.setCellValueFactory(new PropertyValueFactory<>("carName"));
		    	colAddr.setCellValueFactory(new PropertyValueFactory<>("rentAddr"));
		    	colCost.setCellValueFactory(new PropertyValueFactory<>("rentCost"));

		    	data = FXCollections.observableArrayList(tableList);
		    	
		    	tbResult.setItems(data);
		    	
			}
		}
		
    }
    
    
    
    public void alert(String head, String msg) {
		Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle("경고");
		alert.setHeaderText(head);
		alert.setContentText(msg);
		
		alert.showAndWait();
	}
    
    public class TableVO{
    	private String rentDate;
    	private String carName;
    	private String rentAddr;
    	private String rentCost;
    	
    	public TableVO(String rentDate, String carName, String rentAddr, String rentCost) {
			this.rentDate = rentDate;
			this.carName = carName;
			this.rentAddr = rentAddr;
			this.rentCost = rentCost;
		}
    	
		public String getRentDate() {
			return rentDate;
		}
		public void setRentDate(String rentDate) {
			this.rentDate = rentDate;
		}
		public String getCarName() {
			return carName;
		}
		public void setCarName(String carName) {
			this.carName = carName;
		}
		public String getRentAddr() {
			return rentAddr;
		}
		public void setRentAddr(String rentAddr) {
			this.rentAddr = rentAddr;
		}
		public String getRentCost() {
			return rentCost;
		}
		public void setRentCost(String rentCost) {
			this.rentCost = rentCost;
		}
    	
    }
}
