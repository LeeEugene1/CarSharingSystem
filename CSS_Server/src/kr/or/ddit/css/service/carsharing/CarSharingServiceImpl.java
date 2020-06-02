package kr.or.ddit.css.service.carsharing;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import kr.or.ddit.css.dao.carsharing.CarSharingDaoImpl;
import kr.or.ddit.css.dao.carsharing.ICarSharingDao;
import kr.or.ddit.css.vo.CarVO;

public class CarSharingServiceImpl extends UnicastRemoteObject implements ICarSharingService{
	private static CarSharingServiceImpl service;
	
	public static CarSharingServiceImpl getInstance() throws RemoteException{
		if(service==null) service = new CarSharingServiceImpl();
		return service;
	}
	
	private ICarSharingDao dao;
	
	public CarSharingServiceImpl() throws RemoteException{
		dao=CarSharingDaoImpl.getInstance();
	}

	@Override
	public List<CarVO> getAllCarList(int carTypeId) throws RemoteException {
		return dao.getAllCarList(carTypeId);
	}

	@Override
	public String getCarName(String carNum) throws RemoteException {
		return dao.getCarName(carNum);
	}

	
}
