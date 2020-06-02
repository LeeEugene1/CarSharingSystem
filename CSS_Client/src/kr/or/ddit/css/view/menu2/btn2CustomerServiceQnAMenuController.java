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
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import kr.or.ddit.css.service.QnA.IQnAService;
import kr.or.ddit.css.service.notice.INoticeService;
import kr.or.ddit.css.session.AdminLoginSession;
import kr.or.ddit.css.session.NoticeSession;
import kr.or.ddit.css.session.QnASession;
import kr.or.ddit.css.vo.NoticeVO;
import kr.or.ddit.css.vo.QnAVO;

public class btn2CustomerServiceQnAMenuController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane QnAMainRoot;

    @FXML
    private Label lbTitle;

    @FXML
    private Label lbTitle1;

    @FXML
    private Button insertBtn;

    @FXML
    private TableView<QnAVO> tbResult;

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
    void boardInsertClicked(MouseEvent event) throws IOException {
    	//QnA 작성으로 이동
    	String adminId = AdminLoginSession.adminSession.getAdmin_id();
    	if(adminId==null) {
    		alert("입력오류","입력권한이 없습니다.");
    		return;
    	}
    	AnchorPane pane = FXMLLoader.load(getClass().getResource("btn2CustomerServiceQnAInsert.fxml"));
    	QnAMainRoot.getChildren().setAll(pane);
    }

    @FXML
    void boardSearch(MouseEvent event) {
    	ArrayList<QnAVO> vo = new ArrayList<>();
    	try {
			vo = (ArrayList<QnAVO>)service.getSearchQnAList(tfSearch.getText().trim());
		} catch (RemoteException e1) {

			e1.printStackTrace();
		}
    	data.setAll(vo);
		tbResult.setItems(data);
    }

    @FXML
    void tbResultView(MouseEvent event) throws IOException {
    	//QnA 내용으로 이동
//    	AnchorPane pane = FXMLLoader.load(getClass().getResource("btn2CustomerServiceQnAContent.fxml"));
//    	QnAMainRoot.getChildren().setAll(pane);
    	Parent parent = null;
    	if(tbResult.getSelectionModel().isEmpty())
    		return;
    	try {
    		parent = FXMLLoader.load(getClass().getResource("btn2CustomerServiceQnAContent.fxml"));
    		QnAMainRoot.getChildren().setAll(parent);
//    		FXMLLoader loader = new FXMLLoader(getClass().getResource("btn2CustomerServiceQnAContent.fxml"));
//    		parent = loader.load();
//    		
//    		btn2CustomerServiceQnAMenuController ctl = loader.getController();
//    		
//    		ctl.starter(tbResult.getSelectionModel().getSelectedItem());
//    		
//    		QnAMainRoot.getChildren().setAll(parent);
		} catch (IOException e) {
			e.printStackTrace();
		}
    	
    	TextField txtTitle = (TextField) parent.lookup("#tfTitle");
    	Label lbDate = (Label) parent.lookup("#lbDate");
    	TextArea txtContent = (TextArea) parent.lookup("#taContent");
    	Button updateBtn = (Button) parent.lookup("#updateBtn");
    	Button deleteBtn = (Button) parent.lookup("#deleteBtn");
    	
    	
    	QnAVO vo = tbResult.getSelectionModel().getSelectedItem();
		lbDate.setText(vo.getQna_date());
		txtTitle.setText(vo.getQna_title());
		txtContent.setText(vo.getQna_content());
		
		if(AdminLoginSession.adminSession==null) {
			updateBtn.setVisible(false);
			deleteBtn.setVisible(false);
		}
		

		boardId = tbResult.getSelectionModel().getSelectedItem().getQna_id();
		QnASession.QnAIdSession = boardId;
		int cnt = service.setCountIncrement(boardId);
		txtTitle.setEditable(false);
		txtContent.setEditable(false);
    }
    
    private IQnAService service;
    private ObservableList<QnAVO> data;
    private Registry reg;
    private int itemsForPage=18;
    private int boardId = 0;
    
    @FXML
    void initialize() throws RemoteException {
    	try {
    		reg = LocateRegistry.getRegistry("localhost", 8899);
			service = (IQnAService) reg.lookup("QnA");
		} catch (Exception e) {
			e.printStackTrace();
		}
    	
    	if(AdminLoginSession.adminSession==null) {
    		insertBtn.setVisible(false);
    	}
    	
    	coltitle.setCellValueFactory(new PropertyValueFactory<>("qna_title"));
    	coldate.setCellValueFactory(new PropertyValueFactory<>("qna_date"));
    	colcount.setCellValueFactory(new PropertyValueFactory<>("qna_cnt"));
    	
    	List<QnAVO> boardList = service.getAllQnAList();
    	data = FXCollections.observableArrayList(boardList);
    	
    	/////////////페이지네이션 연습//////////
		//itemsForPage = 10; // 한페이지 보여줄 항목 수 설정
		int totPageCount = data.size()%itemsForPage == 0
							? data.size()/itemsForPage
							: data.size()/itemsForPage + 1;
							
		pageView.setPageCount(totPageCount); // 전체 페이지 수 설정]
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
 		List<QnAVO> list = data.subList(fromIndex, toIndex);
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
}
