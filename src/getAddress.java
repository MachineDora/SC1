import java.lang.reflect.Array;
import java.util.ArrayList;

import org.omg.PortableServer.ID_ASSIGNMENT_POLICY_ID;

public class getAddress {
	ExistFile get=new ExistFile();
	
	public String getSimpleAddress(String ed) {//获取简化地址
		String[] address0=ed.split("");
		ArrayList<String> address1=new ArrayList<>();
		while(true) {//不断删除//？？等等
			for(int i=0;i<address0.length;i++) {
				if(address0[i].equals("?")) {
					for(int j=i+1;j<address0.length;j++) {
						if(address0[j].equals("?")) {
							for(int k=j+1;k<address0.length;k++) {
								address1.add(address0[k]);
								}
							break;
							}
						}
					break;
					}
				else if(address0[i].equals("/")){
					for(int j=i+1;j<address0.length;j++) {
						if(address0[j].equals("/")) {
							for(int k=j+1;k<address0.length;k++) {
								address1.add(address0[k]);
								}
							break;
							}
						}
					break;
					}
				else {
					address1.add(address0[i]);
					}
				}
			address0=new String[address1.size()];
			address1.toArray(address0);
			if(address1.indexOf("/")!=-1||address1.indexOf("?")!=-1) {
				address1.clear();
				continue;
				}
			if(address1.indexOf("/")==-1&&address1.indexOf("?")==-1) {
				break;
				}
			}
		
		String temp="";
		for(int i=0;i<address0.length;i++) {
			temp=temp+address0[i];
		}
		return temp;
	}
	
	public boolean Condition(String ed,String c) {//判断地址约束条件是否满足
		if(c.equals("s")) {
			if(ed.endsWith("\'"+c)==false
				&&(ed.indexOf("w ")==-1||ed.indexOf("W ")==-1)
				&&ed.indexOf("f ")==-1
				&&(ed.endsWith("k"+c)==false||ed.endsWith("\'k"+c)==true)) {
				return true;
				}
			else {
				return false;
				}
			}
		else if(c.equals("f")) {
			if(ed.endsWith("kf")==false
				&&ed.indexOf("\'f")==-1) {
				return true;
			}
			else {
				return false;
				}
			}
		else if(c.equals("t")||c.equals("m")||c.equals("z")) {
			if(ed.indexOf("\'"+c)==-1
				&&(ed.indexOf("s")==-1)
				&&(ed.indexOf("w ")==-1||ed.indexOf("W ")==-1)
				&&ed.indexOf("f ")==-1
				&&(ed.endsWith("k"+c)==false||ed.endsWith("\'k"+c)==true)) {
				return true;
				}
			else {
				return false;
				}
		}
		else {
			if(ed.endsWith("\'"+c)==false
				&&ed.indexOf("s")==-1
				&&(ed.indexOf("w ")==-1||ed.indexOf("W ")==-1)
				&&ed.indexOf("f ")==-1
				&&(ed.endsWith("k"+c)==false||ed.endsWith("\'k"+c)==true)) {
				return true;
				}
			else {
				return false;
				}
			}
		}
	}

