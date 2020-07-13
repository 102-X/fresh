import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DakaInfo implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int ID = 0;
	private Date signIn = null;
	private Date signOut = null;
	
	public DakaInfo() {}
	public DakaInfo(int ID,Date signIn,Date signOut) {
		this.ID = ID;
		this.signIn = signIn;
		this.signOut = signOut;
	}
	public void setID(int ID) {
		this.ID = ID;
	}
	public int getID() {
		return ID;
	}
	public void setSignIn(Date signIn) {
		this.signIn = signIn;
	}
	public Date getSignIn() {
		return signIn;
	}
	public void setSignOut(Date signOut) {
		this.signOut = signOut;
	}
	public Date getSignOut() {
		return signOut;
	}
	@Override
	public String toString() {
		return "DakaInfo [ID=" + ID + ", signIn=" + signIn + ", signOut=" + signOut + "]";
	}
	
}
