package kr.or.ddit.css.view.carpairing;

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
import javafx.collections.ObservableArray;
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
import kr.or.ddit.css.service.community.ICommunityService;
import kr.or.ddit.css.service.login.ILoginService;
import kr.or.ddit.css.session.AdminLoginSession;
import kr.or.ddit.css.session.CarPairingSession;
import kr.or.ddit.css.session.CommunitySession;
import kr.or.ddit.css.session.LoginSession;
import kr.or.ddit.css.vo.CarPairingInsertBoardVO;
import kr.or.ddit.css.vo.CarVO;
import kr.or.ddit.css.vo.CommunityBoardVO;
import kr.or.ddit.css.vo.MemberVO;
import kr.or.ddit.css.vo.NoticeVO;

public class carPairingMenuController {
	
	//커뮤니티 게시판 메인

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane carPairingMainRoot;

    @FXML
    private Label lbTitle;

    @FXML
    private Label lbTitle1;

    @FXML
    private Button insertBtn;

    @FXML
    private TableView<CarPairingInsertBoardVO> tbResult;

    @FXML
    private TableColumn<CarPairingInsertBoardVO, Integer> colindex;

    @FXML
    private TableColumn<CarPairingInsertBoardVO, String> coltitle;

    @FXML
    private TableColumn<CarPairingInsertBoardVO, String> colwriter;

    @FXML
    private TableColumn<CarPairingInsertBoardVO, String> coldate;

    @FXML
    private TableColumn<CarPairingInsertBoardVO, Integer> colcount;

    @FXML
    private Pagination pageView;

    @FXML
    private TextField tfSearch;

    @FXML
    private ImageView btnSearch;
    
    @FXML
    void boardInsertClicked(MouseEvent event) throws IOException {   	
    	//게시글 작성으로 이동
    	//System.out.println(carPairingRoot);
    	AnchorPane pane = FXMLLoader.load(getClass().getResource("carPairingInsert.fxml"));
    	carPairingMainRoot.getChildren().setAll(pane);
    	//subFrame.getScene().setRoot(pane);
   
    }

    @FXML
    void boardSearch(MouseEvent event) {
    	ArrayList<CarPairingInsertBoardVO> vo = new ArrayList<>();
    	try {
			vo = (ArrayList<CarPairingInsertBoardVO>)service.getSearchBoardList(tfSearch.getText().trim());
		} catch (RemoteException e1) {

			e1.printStackTrace();
		}
    	data.setAll(vo);
		tbResult.setItems(data);
    }

    @FXML
    void tbResultView(MouseEvent event) throws IOException {
    	Parent parent = null;
    	if(tbResult.getSelectionModel().isEmpty())
    		return;
    	try {
			parent = FXMLLoader.load(getClass().getResource("carPairingContent.fxml"));
			carPairingMainRoot.getChildren().setAll(parent);
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
    	Button btnApprove = (Button) parent.lookup("#btnApprove");
    	
    	//ObservableList<CarPairingInsertBoardVO> temp = tbResult.getSelectionModel().getSelectedItems();
    	CarPairingInsertBoardVO vo=tbResult.getSelectionModel().getSelectedItem();
    	//for(CarPairingInsertBoardVO vo : temp) {
    		lbWriter.setText(vo.getMem_id());
    		lbDate.setText(vo.getPairinginsertboard_date());
    		txtTitle.setText(vo.getPairinginsertboard_title());
    		txtContent.setText(vo.getPairinginsertboard_content());
    		tfCarNum.setText(vo.getPairinginsertboard_carnum());
    		tfRentCost.setText(Integer.toString(vo.getPairinginsertboard_rentcost()));
    		tfDriveCost.setText(Integer.toString(vo.getPairinginsertboard_drivecost()));
    		img1.setImage(new Image(carPairingMenuController.class.getResourceAsStream("/img_pairing/"+vo.getPairinginsertboard_img1())));
    		img2.setImage(new Image(carPairingMenuController.class.getResourceAsStream("/img_pairing/"+vo.getPairinginsertboard_img2())));
    		img3.setImage(new Image(carPairingMenuController.class.getResourceAsStream("/img_pairing/"+vo.getPairinginsertboard_img3())));
    		
    		//if(LoginSession.session.getMem_id()!=vo.getMem_id()) {
    		System.out.println("admin="+AdminLoginSession.adminSession);
    		if(AdminLoginSession.adminSession==null) {
    			System.out.println("null일때");
    			btnApprove.setVisible(false);
    			if(!LoginSession.session.getMem_id().equals(vo.getMem_id())) {
        			btnUpdate.setVisible(false);
        			btnDelete.setVisible(false);
        		}
    		}else {
    			System.out.println("null아닐때");
    			btnUpdate.setVisible(false);
    		}
    		
    		

    	boardId=tbResult.getSelectionModel().getSelectedItem().getPairinginsertboard_id();
    	CarPairingSession.carPairingSession = boardId;
    	CarPairingSession.carPairingSessionImg1 = tbResult.getSelectionModel().getSelectedItem().getPairinginsertboard_img1();
    	CarPairingSession.carPairingSessionImg2 = tbResult.getSelectionModel().getSelectedItem().getPairinginsertboard_img2();
    	CarPairingSession.carPairingSessionImg3 = tbResult.getSelectionModel().getSelectedItem().getPairinginsertboard_img3();
    	CarPairingSession.carParingSessionWriter = tbResult.getSelectionModel().getSelectedItem().getMem_id();
    	int cnt = service.setCountIncrement(boardId);
    	
    	tfCarNum.setEditable(false);
    	tfRentCost.setEditable(false);
    	tfDriveCost.setEditable(false);
    	txtContent.setEditable(false);
    	
    }
    
    private ICarPairingInsertService service;
    private ObservableList<CarPairingInsertBoardVO> data;
    private Registry reg;
    private int itemsForPage = 18;
    private int boardId = 0;

    @FXML
    void initialize() throws RemoteException{
    	try {
			reg = LocateRegistry.getRegistry("localhost", 8899);
			service = (ICarPairingInsertService) reg.lookup("carpairingInsert");
		} catch (Exception e) {
			e.printStackTrace();
		}
    	
	
    	colindex.setCellValueFactory(new PropertyValueFactory<>("pairinginsertboard_id"));
    	coltitle.setCellValueFactory(new PropertyValueFactory<>("pairinginsertboard_title"));
    	colwriter.setCellValueFactory(new PropertyValueFactory<>("mem_id"));
    	coldate.setCellValueFactory(new PropertyValueFactory<>("pairinginsertboard_date"));
    	colcount.setCellValueFactory(new PropertyValueFactory<>("pairinginsertboard_cnt"));
    	
       //DB에서 데이터를 가져와서 TableView에 셋팅하기
    	List<CarPairingInsertBoardVO> boardList = service.getAllBoardList();
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
		
		if(LoginSession.session==null) {
			insertBtn.setVisible(false);
		}
    	
//		for (int i = 0; i < boardList.size(); i++) {
//			boardList.get(i).setPairinginsertboard_index(boardList.size() - i);
//		}
	}
    public void changeTableView(int index){
 		int fromIndex = index * itemsForPage;	//시작위치
 		int toIndex = Math.min(fromIndex + itemsForPage, data.size());	//종료위치(둘을 비교해서 작은값이 종료위치)
 		//data.subList(fromIndex, toIndex);	//사직위치부터 종료위치 (종료위치는 포함X)이전 까지의 자료 추출
 		List<CarPairingInsertBoardVO> list = data.subList(fromIndex, toIndex);
 		//System.out.println("size = " + list.size());
 		//시작위치부터 종료위치 이전까지의 자료를 TableView에 셋팅한다.
 		tbResult.setItems(FXCollections.observableArrayList(list));
 	}
}
