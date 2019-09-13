package unitofwork;

import java.util.ArrayList;

import org.omg.PortableServer.ID_ASSIGNMENT_POLICY_ID;

import javafx.scene.media.VideoTrack;
import models.Admin;
import models.Admin;
import data_mapper.*;

public class unitofworkAdmin {
	
	private static ThreadLocal<unitofworkAdmin> current = new ThreadLocal<>();
	
	private ArrayList<Admin> newAdmins = new ArrayList<Admin>();
	private ArrayList<Admin> dirtyAdmins = new ArrayList<Admin>();
	private ArrayList<Admin> deletedAdmins = new ArrayList<Admin>();
	
	public static void setCurrent(unitofworkAdmin uow) {
		current.set(uow);
	}
	
	public static void newCurrent() {
		setCurrent(new unitofworkAdmin());
	}
	
	public static unitofworkAdmin getCurrent() {
		return (unitofworkAdmin) current.get();
	}
	
	public void registerNew(Admin admin) {
		assert (admin.getUserID()<=0):"id is null";
		assert (!dirtyAdmins.contains(admin)):"this object is dirty";
		assert (!deletedAdmins.contains(admin)):"this object has been deleted";
		assert (!newAdmins.contains(admin)):"this object is new";
		newAdmins.add(admin);
	}
	
	public void registerDirty(Admin admin) {
		assert (admin.getUserID()<=0):"id is null";
		assert (!deletedAdmins.contains(admin)):"this object has been deleted";
		if(!dirtyAdmins.contains(admin) && !newAdmins.contains(admin)) {
			dirtyAdmins.add(admin);
		}
	}
	
	public void registerDeleted(Admin admin) {
		assert (admin.getUserID()<=0):"id is null";
		if(newAdmins.remove(admin)) return;
		dirtyAdmins.remove(admin);
		if(!deletedAdmins.contains(admin)) {
			deletedAdmins.add(admin);
		}
	}
	
	public void commit() {
		for(Admin admin: newAdmins) {
			AdminDataMapper.insert(admin);
		}
	
		for(Admin admin: dirtyAdmins) {
			AdminDataMapper.update(admin);
		}
		
		for(Admin admin: deletedAdmins) {
			AdminDataMapper.delete(admin);
		}
		
	}
}
