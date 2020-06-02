package kr.or.ddit.css.view.menu2;

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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import kr.or.ddit.css.service.blacklist.IBlacklistService;
import kr.or.ddit.css.session.AdminLoginSession;
import kr.or.ddit.css.session.BlacklistSession;
import kr.or.ddit.css.session.LoginSession;
import kr.or.ddit.css.vo.BlackListVO;
import kr.or.ddit.css.vo.CommunityBoardVO;

public class btn2CustomerServiceBlackListMenuController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane BlackListMainRoot;

    @FXML
    private Label lbTitle;

    @FXML
    private Label lbTitle1;

    @FXML
    private Button insertBtn;

    @FXML
    private TableView<BlackListVO> tbResult;

    @FXML
    private TableColumn<BlackListVO, String> coltitle;

    @FXML
    private TableColumn<BlackListVO, String> colwriter;

    @FXML
    private TableColumn<BlackListVO, Integer> coldate;

    @FXML
    private TableColumn<BlackListVO, Integer> colcount;

    @FXML
    private Pagination pageView;

    @FXML
    private TextField tfSearch;

    @FXML
    private ImageView btnSearch;

    @FXML
    void boardInsertClicked(MouseEvent event) throws IOException {
    	//블랙리스트 작성으로 이동(회원 O, 관리자 X)
    	
//    	if(회원 == O) {
//    		AnchorPane pane = FXMLLoader.load(getClass().getResource("btn2CustomerServiceBlackListInsert.fxml"));
//        	BlackListMainRoot.getChildren().setAll(pane);
//    	}else if(관리자아이디 == O) {
//    		alert("오류","관리자는 작성이 불가능합니다.");
//    		return;
//    	}
    	
    	AnchorPane pane = FXMLLoader.load(getClass().getResource("btn2CustomerServiceBlackListInsert.fxml"));
    	BlackListMainRoot.getChildren().setAll(pane);
    }

    @FXML
    void boardSearch(MouseEvent event) {
    	ArrayList<BlackListVO> blist = new ArrayList<>();
    	try {
    		blist = (ArrayList<BlackListVO>)service.getSearchBoardList(tfSearch.getText().trim());
			
		} catch (RemoteException e1) {
			e1.printStackTrace();
		}
    	data.setAll(blist);
    	tbResult.setItems(data);
    }

    @FXML
    void tbResultView(MouseEvent event) throws IOException {
//    	//블랙리스트 내용으로 이동
//    	AnchorPane pane = FXMLLoader.load(getClass().getResource("btn2CustomerServiceBlackListContent.fxml"));
//    	BlackListMainRoot.getChildren().setAll(pane);
    	
    	Parent parent = null;
    	if(tbResult.getSelectionModel().isEmpty())
    		return;
    	BlackListVO vo = tbResult.getSelectionModel().getSelectedItem();
    	if(AdminLoginSession.adminSession==null) {
			if(!LoginSession.session.getMem_id().equals(tbResult.getSelectionModel().getSelectedItem().getMem_id())) {
				alert("오류","권한이 없습니다.");
				return;
			}
    	}
    	//System.out.println("blablabla"+AdminLoginSession.adminSession.getAdmin_id());
    	
//    	if(!vo.getMem_id().equals(LoginSession.session.getMem_id())&&AdminLoginSession.adminSession.getAdmin_id()==null) {
//    		//alert("오류","관리자만 확인가능합니다.");
//    		return;
//    	}
    	
//    	if(LoginSession.session.getMem_id()!=null){
//    		if(!vo.getMem_id().equals(LoginSession.session.getMem_id())) {
//    			return;
//    		}
//    	}else if(LoginSession.session.getMem_id()!=null) {
//    		if(AdminLoginSession.adminSession.getAdmin_id()==null) {
//    			return;    			
//    		}
//    	}
    	
    	try {
			parent = FXMLLoader.load(getClass().getResource("btn2CustomerServiceBlackListContent.fxml"));
			BlackListMainRoot.getChildren().setAll(parent);

		} catch (Exception e) {
			e.printStackTrace();
		}
		Label lbWriter = (Label) parent.lookup("#lbWriter");
		Label lbDate = (Label) parent.lookup("#lbDate");
		TextField txtTitle = (TextField) parent.lookup("#tfTitle");
		TextArea txtContent = (TextArea) parent.lookup("#taContent");
    	ImageView img1 = (ImageView) parent.lookup("#imgFile1");
    	TextField IdClaim = (TextField) parent.lookup("#IdClaim");
    	Button update = (Button) parent.lookup("#updateBtn");
    	Button delete = (Button) parent.lookup("#deleteBtn");
    	Button memUpdate = (Button) parent.lookup("#blacklistMemberUpdate");
    	
    	
    	//String memCId= IdClaim.getText();
    	//IdClaim

		//ObservableList<BlackListVO> temp = tbResult.getSelectionModel().getSelectedItems();		
		
		//for(BlackListVO vo : temp) {
			lbWriter.setText(vo.getMem_id());
			lbDate.setText(vo.getBlacklist_date());
			txtTitle.setText(vo.getBlacklist_title());
			txtContent.setText(vo.getBlacklist_content());
			//img1.setImage(new Image(carPairingMenuController.class.getResourceAsStream("/img_pairing/"+vo.getPairinginsertboard_img1())));
			if(vo.getBlacklist_img()==null || "".equals(vo.getBlacklist_img())) {
				img1.setImage(null);
			}else {
				img1.setImage(new Image(btn2CustomerServiceBlackListMenuController.class.getResourceAsStream("/img_blacklist/"+vo.getBlacklist_img())));
			}
			IdClaim.setText(vo.getBlacklist_claimid());
			
			if(AdminLoginSession.adminSession==null) {
				memUpdate.setVisible(false);
			}else {
				update.setVisible(false);
			}
			
			
		//}
		boardId = tbResult.getSelectionModel().getSelectedItem().getBlacklist_id();
		BlacklistSession.BlacklistSession = boardId;
		BlacklistSession.BlacklistSessionimg1 = tbResult.getSelectionModel().getSelectedItem().getBlacklist_img();
    
		int cnt = service.setCountIncrement(boardId);

		txtTitle.setEditable(false);
		txtContent.setEditable(false);
    }
    
    private IBlacklistService service;
    private ObservableList<BlackListVO> data;
    private Registry reg;
    private int itemsForPage =18;
    private int boardId = 0;

    @FXML
    void initialize() throws RemoteException {
    	try {
			reg = LocateRegistry.getRegistry("localhost", 8899);
			service = (IBlacklistService) reg.lookup("blacklist");
		} catch (Exception e) {
			e.printStackTrace();
		}
    	
    	coltitle.setCellValueFactory(new PropertyValueFactory<>("blacklist_title"));
    	colwriter.setCellValueFactory(new PropertyValueFactory<>("mem_id"));
    	coldate.setCellValueFactory(new PropertyValueFactory<>("blacklist_date"));
    	colcount.setCellValueFactory(new PropertyValueFactory<>("blacklist_cnt"));
    	
       //DB에서 데이터를 가져와서 TableView에 셋팅하기
    	List<BlackListVO> bListVo = service.getAllBoardList();
    	data = FXCollections.observableArrayList(bListVo);
    	
    	/////////////페이지네이션 연습//////////
		//itemsForPage = 10; // 한페이지 보여줄 항목 수 설정
		int totPageCount = data.size()%itemsForPage == 0
							? data.size()/itemsForPage
							: data.size()/itemsForPage + 1;
							
		pageView.setPageCount(totPageCount); // 전체 페이지 수 설정
		
		pageView.setCurrentPageIndex(0);
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
 		List<BlackListVO> list = data.subList(fromIndex, toIndex);
 		//System.out.println("size = " + list.size());
 		//시작위치부터 종료위치 이전까지의 자료를 TableView에 셋팅한다.
 		tbResult.setItems(FXCollections.observableArrayList(list));
 	}
    
    public void alert(String head, String msg) {
    	Alert alert = new Alert(AlertType.WARNING);
    	alert.setTitle("경고");
    	alert.setHeaderText(head);
    	alert.setContentText(msg);
    	
    	alert.showAndWait();
    	
    }
    public void infoMsg(String headerText, String msg) {
    	Alert alert = new Alert(AlertType.INFORMATION);
    	alert.setTitle("정보 확인");
    	alert.setHeaderText(headerText);
    	alert.setContentText(msg);
    	alert.showAndWait();
    	
    }
}
