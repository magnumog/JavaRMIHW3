package system;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import api.Space;

public class SpaceImpl extends UnicastRemoteObject implements Space {

	protected SpaceImpl() throws RemoteException {
		super();
	}

}
