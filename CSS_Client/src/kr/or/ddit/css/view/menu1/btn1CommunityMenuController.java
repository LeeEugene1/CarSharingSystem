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
import kr.or.ddit.css.service.community.ICommunityService;
import kr.or.ddit.css.service.login.ILoginService;
import kr.or.ddit.css.session.AdminLoginSession;
import kr.or.ddit.css.session.CommunitySession;
import kr.or.ddit.css.session.LoginSession;
import kr.or.ddit.css.view.carpairing.carPairingMenuController;
import kr.or.ddit.css.vo.CarPairingInsertBoardVO;
import kr.or.ddit.css.vo.CommunityBoardVO;
import kr.or.ddit.css.vo.MemberVO;
import kr.or.ddit.css.vo.NoticeVO;

public class btn1CommunityMenuController {
	
	//커뮤니티 게시판 메인

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
    private Button insertBtn;

    @FXML
    private TableView<CommunityBoardVO> tbResult;

    @FXML
    private TableColumn<CommunityBoardVO, Integer> colindex;

    @FXML
    private TableColumn<CommunityBoardVO, String> coltitle;

    @FXML
    private TableColumn<CommunityBoardVO, String> colwriter;

    @FXML
    private TableColumn<CommunityBoardVO, String> coldate;

    @FXML
    private TableColumn<CommunityBoardVO, Integer> colcount;

    @FXML
    private Pagination pageView;

    @FXML
    private TextField tfSearch;

    @FXML
    private ImageView btnSearch;
    
    @FXML
    void boardInsertClicked(MouseEvent event) throws IOException {   	
    	//게시글 작성으로 이동
    	AnchorPane pane = FXMLLoader.load(getClass().getResource("btn1CommunityInsert.fxml"));
    	communityMainRoot.getChildren().setAll(pane);
    	//subFrame.getScene().setRoot(pane);
   
    }

    @FXML
    void boardSearch(MouseEvent event) {
    	ArrayList<CommunityBoardVO> vo = new ArrayList<>();
    	try {
			vo = (ArrayList<CommunityBoardVO>)service.getSearchBoardList(tfSearch.getText().trim());
		} catch (RemoteException e1) {

			e1.printStackTrace();
		}
    	data.setAll(vo);
		tbResult.setItems(data);
    }

    @FXML
    void tbResultView(MouseEvent event) throws IOException {
    	// 게시판 내용보기
    	Parent parent = null;
		if(tbResult.getSelectionModel().isEmpty())
    		return;
		try {
			parent = FXMLLoader.load(getClass().getResource("btn1CommunityContent.fxml"));
			communityMainRoot.getChildren().setAll(parent);
		} catch (IOException e) {
			e.printStackTrace();
		}
		Label lbWriter = (Label) parent.lookup("#lbWriter");
		Label lbDate = (Label) parent.lookup("#lbDate");
		Label txtTitle = (Label) parent.lookup("#tfTitle");
		TextArea txtContent = (TextArea) parent.lookup("#taContent");
		Button updateBtn = (Button) parent.lookup("#updateBtn");
		Button deleteBtn = (Button) parent.lookup("#deleteBtn");
		ImageView img1 = (ImageView) parent.lookup("#communityImg");
		
		//ObservableList<CommunityBoardVO> temp = tbResult.getSelectionModel().getSelectedItems();
		CommunityBoardVO vo = tbResult.getSelectionModel().getSelectedItem();
		//for(CommunityBoardVO vo : temp) {
			lbWriter.setText(vo.getMem_id());
			lbDate.setText(vo.getBoard_date());
			txtTitle.setText(vo.getBoard_title());
			txtContent.setText(vo.getBoard_content());
			if(vo.getBoard_img()!=null) {
				img1.setImage(new Image(btn1CommunityMenuController.class.getResourceAsStream("/img_community/"+vo.getBoard_img())));
			}
			System.out.println("admin="+AdminLoginSession.adminSession);
			System.out.println("memId="+LoginSession.session);
			if(AdminLoginSession.adminSession==null) {
				System.out.println("관리자null");
				if(!LoginSession.session.getMem_id().equals(vo.getMem_id())) {
					System.out.println("같지않은 회원");
					updateBtn.setVisible(false);
					deleteBtn.setVisible(false);
				}
			}else if(LoginSession.session==null){
				System.out.println("관리자null 아닐때");
				updateBtn.setVisible(false);
			}
			
		//}
		boardId = tbResult.getSelectionModel().getSelectedItem().getBoard_id();
		CommunitySession.CommunitySession = boardId;
		CommunitySession.CommunityImg = tbResult.getSelectionModel().getSelectedItem().getBoard_img();
		
		int cnt = service.setCountIncrement(boardId);
		
//		txtTitle.setEditable(false);
		txtContent.setEditable(false);
    }
    
    private ICommunityService service;
    private ObservableList<CommunityBoardVO> data;
    private Registry reg;
    private int itemsForPage=18;
    private int boardId = 0;
    
     
    @FXML
    void initialize() throws RemoteException{
    	try {
			reg = LocateRegistry.getRegistry("localhost", 8899);
			service = (ICommunityService) reg.lookup("community");
		} catch (Exception e) {
			e.printStackTrace();
		}
    	
//    	try {
//			data.addAll(service.getAllBoardList());
//		} catch (RemoteException e2) {
//			
//			e2.printStackTrace();
//		}
//    	tbResult.setItems(data);
    	
    	colindex.setCellValueFactory(new PropertyValueFactory<>("board_id"));
    	coltitle.setCellValueFactory(new PropertyValueFactory<>("board_title"));
    	colwriter.setCellValueFactory(new PropertyValueFactory<>("mem_id"));
    	coldate.setCellValueFactory(new PropertyValueFactory<>("board_date"));
    	colcount.setCellValueFactory(new PropertyValueFactory<>("board_cnt"));
    	
       //DB에서 데이터를 가져와서 TableView에 셋팅하기
    	List<CommunityBoardVO> boardList = service.getAllBoardList();
    	data = FXCollections.observableArrayList(boardList);
    	
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
		
		if(LoginSession.session==null) {
			insertBtn.setVisible(false);
		}
    	
	}
    public void changeTableView(int index){
 		int fromIndex = index * itemsForPage;	//시작위치
 		int toIndex = Math.min(fromIndex + itemsForPage, data.size());	//종료위치(둘을 비교해서 작은값이 종료위치)
 		//data.subList(fromIndex, toIndex);	//사직위치부터 종료위치 (종료위치는 포함X)이전 까지의 자료 추출
 		List<CommunityBoardVO> list = data.subList(fromIndex, toIndex);
 		//System.out.println("size = " + list.size());
 		//시작위치부터 종료위치 이전까지의 자료를 TableView에 셋팅한다.
 		tbResult.setItems(FXCollections.observableArrayList(list));
 	}
}
