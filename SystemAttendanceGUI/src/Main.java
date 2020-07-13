
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Main {
    static Company company = new Company();
    static List<DakaInfo> list = new ArrayList<DakaInfo>();
	public static void main(String[]  args) throws IOException, ClassNotFoundException {
	}
		
	
	
	
	//初始化员工信息
	public  void setEmployee() {
		company.addEmployee(new Employee(1,"MARY"));
		company.addEmployee(new Employee(2,"TOM"));
		company.addEmployee(new Employee(3,"SARA"));
		company.addEmployee(new Employee(4,"MIKE"));
		company.addEmployee(new Employee(5,"ALEN"));
		company.addEmployee(new Employee(6,"MAX"));
		company.addEmployee(new Employee(7,"LYNE"));
		company.addEmployee(new Employee(8,"PAMELA"));
	}

	//签到功能
	public String attendanceCheckIn(int inputID) {
		Employee e = company.getEmployee(inputID);
		if(e != null) {
	        DakaInfo dakaInfoIn= company.attendanceMessage.get(inputID);
			if (dakaInfoIn != null) {
				return "签到失败：今天已经打过卡了";
				}else {
					Date date = new Date();
					DakaInfo daka = new DakaInfo();
					daka.setID(inputID);
					daka.setSignIn(date);
					company.attendanceMessage.put(inputID,daka);
					updateAttendanceMessage(inputID);
					return "卡号：" + inputID + " 打卡成功";
				}
		    }else {
		    	
			return "签到失败：无此ID员工";
        }
    }
 
	//签退功能
	public String attendanceCheckOut(int inputID) {
		Employee e = company.getEmployee(inputID);
		if (e != null) {
			DakaInfo dakaInfoOut = company.attendanceMessage.get(inputID);
			if (dakaInfoOut != null) {
				Date date = new Date();
			    dakaInfoOut.setSignOut(date);
			    company.attendanceMessage.put(inputID,dakaInfoOut);
			    updateAttendanceMessage(inputID);
			    return "卡号：" + inputID + " 签退成功";
				}else{
					return "签退失败：今天还没有签到，无法签退";
					}
			}else {
			return "签退失败：无此ID员工";
			}
	}
	
	//初始化list
	public void setMessage() {
		for(Employee employee:company.employees) {
			DakaInfo daka = new DakaInfo();
			daka.setID(employee.getID());	
			list.add(daka);
			}
	}
	
	//更新签到记录
	public void updateAttendanceMessage(int inputID) {
		for(DakaInfo daka : list) {
			if(daka.getID() == inputID) {
				DakaInfo dk = company.attendanceMessage.get(inputID);
				daka.setSignIn(dk.getSignIn());
				daka.setSignOut(dk.getSignOut());
			}
		}
		
	}
			
	//将打卡情况写入文件中
	public static void fileWrite() {
		File file = new File("e:/AttendanceMessage.txt");
		FileOutputStream fos = null;
		ObjectOutputStream oos = null;
	    try {
	    	fos = new FileOutputStream(file);
			oos = new ObjectOutputStream(fos);
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd E");
			oos.writeChars("考勤日期："+sdf.format(date));
			for (DakaInfo daka : list) {
		    	oos.writeObject(daka);
		    }
			oos.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
				try {
					if(fos != null) {
					fos.close();
					}
					if(oos != null) {
						oos.close();
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
}
	
	//测试文件内容
//	public void testFileWrite() {
//		FileInputStream fis = null;
//		ObjectInputStream ois = null;
//		
//		try {
//			System.out.println();
//			System.out.println("以下为“AttendanceMessage.txt”中全部打卡记录");
//			fis = new FileInputStream("e:/AttendanceMessage.txt");
//			ois = new ObjectInputStream(fis);
//			Object o = null;
//			while(fis.available()>0) {
//				o= ois.readObject();
//				DakaInfo daka = (DakaInfo)o;
//				System.out.println(daka.toString());
//			}
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (ClassNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}finally {
//			if(fis!=null) {
//				try {
//					if(fis!=null) {
//						fis.close();
//					}
//					if(ois !=null) {
//						ois.close();
//					}	
//				} catch (IOException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//			}
//		}
//	}
//}
//		FileReader fr = null;
//		try {
//			fr = new FileReader("e:/课件/实训/实训文件练习/AttendanceMessage.txt");
//			System.out.println();
//			System.out.println((char)fr.read());
//			System.out.println();
//			int c;
//			while((c=fr.read())!=-1) {
//				System.out.print((char)c);
//			}
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}finally {
//			if(fr != null) {
//				try {
//					fr.close();
//				} catch (IOException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//			}
//		}
//	}

