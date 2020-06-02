package kr.or.ddit.css.service.carsharing;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import kr.or.ddit.css.vo.CarVO;

public interface ICarSharingService extends Remote{
	public List<CarVO> getAllCarList(int carTypeId) throws RemoteException;
	public String getCarName(String carNum) throws RemoteException;
}
