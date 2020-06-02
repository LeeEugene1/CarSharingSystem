package kr.or.ddit.css.view.menu1;

import java.io.IOException;
import java.net.URL;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import kr.or.ddit.css.service.carpairing.ICarPairingInsertService;
import kr.or.ddit.css.service.carpairing.ICarPairingUseService;
import kr.or.ddit.css.session.AdminLoginSession;
import kr.or.ddit.css.session.CarPairingSession;
import kr.or.ddit.css.session.CarPairingUseSession;
import kr.or.ddit.css.view.carpairing.carPairingMenuController;
import kr.or.ddit.css.vo.CarPairingInsertBoardVO;
import kr.or.ddit.css.vo.CarPairingUseBoardVO;

public class btn1CarPairingMenuInsert1Controller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane carpairingUseMainRoot;

    @FXML
    private Label lbTitle;

    @FXML
    private Label lbTitle1;

    @FXML
    private Label lbTitle11;

    @FXML
    private Label lbTitle12;

    @FXML
    private TableView<CarPairingUseBoardVO> tbResult;

    @FXML
    private TableColumn<?, ?> collndex;

    @FXML
    private TableColumn<?, ?> coltitle;

    @FXML
    private TableColumn<?, ?> colwriter;

    @FXML
    private TableColumn<?, ?> coldate;

    @FXML
    private TableColumn<?, ?> colcount;

    @FXML
    private Pagination pageView;

    @FXML
    private TextField tfSearch;

    @FXML
    private ImageView btnSearch;

    @FXML
    void boardSearch(MouseEvent event) {
    	ArrayList<CarPairingUseBoardVO> vo = new ArrayList<>();
    	try {
			vo = (ArrayList<CarPairingUseBoardVO>)service.getSearchBoardList(tfSearch.getText().trim());
		} catch (RemoteException e1) {

			e1.printStackTrace();
		}
    	data.setAll(vo);
		tbResult.setItems(data);
    }

    @FXML
    void tbResultView(MouseEvent event) throws IOException{
    	Parent parent = null;
    	if(tbResult.getSelectionModel().isEmpty())
    		return;
    	try {
			parent = FXMLLoader.load(getClass().getResource("../carpairing/carPairingUseContent.fxml"));
			carpairingUseMainRoot.getChildren().setAll(parent);
		} catch (IOException e) {
			e.printStackTrace();
		}
    	Label lbWriter = (Label) parent.lookup("#lbWriter");
    	Label lbDate = (Label) parent.lookup("#lbDate");
    	Label txtTitle = (Label) parent.lookup("#tfTitle");
    	TextField tfCarNum	= (TextField) parent.lookup("#carNum");
    	TextField tfRentCost = (TextField) parent.lookup("#rentCost");
    	TextField tfDriveCost = (TextField) parent.lookup("#driveCost");
    	TextArea txtContent = (TextArea) parent.lookup("#taContent");
    	ImageView img1 = (ImageView) parent.lookup("#imgFile1");
    	ImageView img2 = (ImageView) parent.lookup("#imgFile2");
    	ImageView img3 = (ImageView) parent.lookup("#imgFile3");
    	Button btnDelete = (Button) parent.lookup("#deleteBtn");
    	Button btnUpdate = (Button) parent.lookup("#updateBtn");
    	
    	//ObservableList<CarPairingUseBoardVO> temp = tbResult.getSelectionModel().getSelectedItems();
    	CarPairingUseBoardVO vo = tbResult.getSelectionModel().getSelectedItem();
    	//for(CarPairingUseBoardVO vo : temp) {
    		lbWriter.setText(vo.getMem_id());
    		lbDate.setText(vo.getPairinguseboard_date());
    		txtTitle.setText(vo.getPairinguseboard_title());
    		txtContent.setText(vo.getPairinguseboard_content());
    		tfCarNum.setText(vo.getPairinguseboard_carnum());
    		tfRentCost.setText(Integer.toString(vo.getPairinguseboard_rentcost()));
    		tfDriveCost.setText(Integer.toString(vo.getPairinguseboard_drivecost()));
    		img1.setImage(new Image(btn1CarPairingMenuInsert1Controller.class.getResourceAsStream("/img_pairing/"+vo.getPairinguseboard_img1())));
    		img2.setImage(new Image(btn1CarPairingMenuInsert1Controller.class.getResourceAsStream("/img_pairing/"+vo.getPairinguseboard_img2())));
    		img3.setImage(new Image(btn1CarPairingMenuInsert1Controller.class.getResourceAsStream("/img_pairing/"+vo.getPairinguseboard_img3())));
    		
    		if(AdminLoginSession.adminSession==null) {
    			btnUpdate.setVisible(false);
    			btnDelete.setVisible(false);
    		}
    	//}
    	boardId=tbResult.getSelectionModel().getSelectedItem().getPairinguseboard_id();
    	CarPairingUseSession.carPairingUseSession=boardId;
    	CarPairingUseSession.carPairingUseSessionImg1 = tbResult.getSelectionModel().getSelectedItem().getPairinguseboard_img1();
    	CarPairingUseSession.carPairingUseSessionImg2 = tbResult.getSelectionModel().getSelectedItem().getPairinguseboard_img2();
    	CarPairingUseSession.carPairingUseSessionImg3  = tbResult.getSelectionModel().getSelectedItem().getPairinguseboard_img3();
    	int cnt = service.setCountIncrement(boardId);
    	
    	tfCarNum.setEditable(false);
    	tfRentCost.setEditable(false);
    	tfDriveCost.setEditable(false);
    	txtContent.setEditable(false);
    }
    
    private ICarPairingUseService service;
    private ObservableList<CarPairingUseBoardVO> data;
    private Registry reg;
    private int itemsForPage = 18;
    private int boardId = 0;
    
    @FXML
    void initialize() throws RemoteException{
    	try {
			reg = LocateRegistry.getRegistry("localhost", 8899);
			service = (ICarPairingUseService) reg.lookup("carpairingUse");
		} catch (Exception e) {
			e.printStackTrace();
		}
    	
    	
    	collndex.setCellValueFactory(new PropertyValueFactory<>("pairinguseboard_id"));
    	coltitle.setCellValueFactory(new PropertyValueFactory<>("pairinguseboard_title"));
    	colwriter.setCellValueFactory(new PropertyValueFactory<>("mem_id"));
    	coldate.setCellValueFactory(new PropertyValueFactory<>("pairinguseboard_date"));
    	colcount.setCellValueFactory(new PropertyValueFactory<>("pairinguseboard_cnt"));
    	
       //DB에서 데이터를 가져와서 TableView에 셋팅하기
    	List<CarPairingUseBoardVO> boardList = service.getAllBoardList();
    	data = FXCollections.observableArrayList(boardList);
    	
    	/////////////페이지네이션 연습//////////
		//itemsForPage = 10; // 한페이지 보여줄 항목 수 설정
		int totPageCount = data.size()%itemsForPage == 0
							? data.size()/itemsForPage
							: data.size()/itemsForPage + 1;
							
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
    	
    	
	}
    public void changeTableView(int index){
 		int fromIndex = index * itemsForPage;	//시작위치
 		int toIndex = Math.min(fromIndex + itemsForPage, data.size());	//종료위치(둘을 비교해서 작은값이 종료위치)
 		//data.subList(fromIndex, toIndex);	//사직위치부터 종료위치 (종료위치는 포함X)이전 까지의 자료 추출
 		List<CarPairingUseBoardVO> list = data.subList(fromIndex, toIndex);
 		//System.out.println("size = " + list.size());
 		//시작위치부터 종료위치 이전까지의 자료를 TableView에 셋팅한다.
 		tbResult.setItems(FXCollections.observableArrayList(list));
 	}
}
