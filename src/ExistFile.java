import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.lang.invoke.ConstantCallSite;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Pattern;

import javax.security.sasl.Sasl;

public class ExistFile {

	int linenow;
	int line0;
	int line02;
	int line03;
	String[] main000;
	String ed;
	String defaultname;
	String command_s="";
	EDLineEditor pattern=new EDLineEditor();
	ArrayList linek=new ArrayList();//�������
	ArrayList<ArrayList<Double>> tosave=new ArrayList<>();//��¼ÿһ�������㱣�棬��LIST
	ArrayList<Integer> save_linenow=new ArrayList<>();//���浱ǰ��
	ArrayList save0=new ArrayList();//ÿһ�����������

	public int Stringtoline1(String str0) {//������//�ͣ�����ֱ��ת��Ϊ����
		int line=0;
		String str2;
		char c=str0.charAt(0);
		int jj = 0;
		String str00="";
		if(c=='\'') {
			if(Character.isLowerCase(str0.charAt(1))) {
				String k0=String.valueOf(str0.charAt(1));
				for(int i=0;i<linek.size();i++) {
					if(linek.get(i).toString().endsWith(k0)) {
						for(int j=0;j<main000.length;j++) {
							if(main000[j].equals(linek.get(i).toString().substring(0,linek.get(i).toString().length()-1))) {
								line=j+1;
								}
							}
						if(line==0) {
							return -1;
							}
						else if(str0.endsWith(str0.substring(1, 2))&&str0.length()==2) {
							return line;
							}
						else {
							if(str0.substring(2,3).equals("-")) {
								line=line-Integer.parseInt(str0.substring(3, 4));
								return line;
							}
							if(str0.substring(2,3).equals("+")) {
								line=line+Integer.parseInt(str0.substring(3, 4));
								return line;
							}
							else {
								return line;
								}
							}
						}
					}
				}
			else {
				return -1;
			}
			}
		if(c=='/') {
			for(int j=1;j<str0.length();j++) {
				if(str0.charAt(j)=='/') {
					jj=j;
					break;
				}
				str00=str00+str0.charAt(j);
				if(j==str0.length()) {
					return -1;
				}
			}
			if(linenow==main000.length) {
				for(int m=0;m<linenow;m++) {
					if(main000[m].indexOf(str00)!=-1) {
						line=m+1;
						break;
					}
					else if((m==linenow-1)&&(main000[m].indexOf(str00)==-1)) {
						line=-1;
						break;
						}
					}
				}
			else {
				for(int k=linenow;k<main000.length;k++) {
					if(main000[k].indexOf(str00)!=-1) {
						line=k+1;
						break;
					}
					else if((k==main000.length-1)&&(main000[k].indexOf(str00)==-1)) {
						for(int m=0;m<linenow;m++) {
							if(main000[m].indexOf(str00)!=-1) {
								line=m+1;
								break;
							}
							else if((m==linenow-1)&&(main000[m].indexOf(str00)==-1)) {
								line=-1;
								break;
								}
							}
						}
					}
				}
			}
		if(c=='?') {
			for(int j=1;j<str0.length();j++) {
				if(str0.charAt(j)=='?') {
					jj=j;
					break;
				}
				str00=str00+str0.charAt(j);
				if(j==str0.length()) {
					return -1;
				}
			}
			if(linenow==1) {
				for(int m=main000.length-1;m>linenow-2;m--) {
					if(main000[m].indexOf(str00)!=-1) {
						line=m+1;
						break;
					}
					else if((m==linenow-1)&&(main000[m].indexOf(str00)==-1)) {
						line=-1;
						break;
						}
					}
				}
			else{
				for(int k=linenow-2;k>=0;k--) {
					if(main000[k].indexOf(str00)!=-1) {
						line=k+1;
						break;
					}
					else if((k==0)&&(main000[k].indexOf(str00)==-1)) {
						for(int m=main000.length-1;m>linenow-2;m--) {
							if(main000[m].indexOf(str00)!=-1) {
								line=m+1;
								break;
							}
							else if((m==linenow-1)&&(main000[m].indexOf(str00)==-1)) {
								line=-1;
								break;
								}
							}
				 		}
					}	
				}
			}
		else if(c!='/'&&c!='?'&&str0.indexOf(",")!=-1){
			for(int i=0;i<str0.length();i++) {
				if(str0.charAt(i)==',') {
					String str01=str0.substring(0, i);
					line=getline0(str01);
					return line;
					}
				}
			}
		else if(c!='/'&&c!='?'&&str0.indexOf(",")==-1) {
			line=getline0(str0);
			return line;
		}
		if(line==-1) {
			return line;
			}
		else if(line!=-1){
			if(str0.endsWith(String.valueOf(str0.charAt(jj)))){
				
			}
			else if(str0.charAt(jj+1)=='+') {
				if(str0.charAt(jj+2)=='/'||str0.charAt(jj+2)=='?') {
					line=-1;
				}
				else {
				line=line+Integer.valueOf(String.valueOf(str0.charAt(jj+2)));
				}
				}
			else if(str0.charAt(jj+1)=='-') {
				if(str0.charAt(jj+2)=='/'||str0.charAt(jj+2)=='?') {
					line=-1;
				}
				else {
				line=line-Integer.valueOf(String.valueOf(str0.charAt(jj+2)));
				}
				}			
			}
		return line;
		}
	
	public int Stringtoline2(String str2) {//���ڳ��ֶ���֮����еڶ���ת��
		String str20 = "";
		int line;
		for(int i=0;i<str2.length();i++) {
			if(str2.charAt(i)=='/') {
				for(int j=i+1;j<str2.length();j++) {
					if(str2.charAt(j)=='/') {
						for(int k=j+1;k<str2.length();k++) {
							if(str2.charAt(k)==',') {
								str20=str2.substring(k+1);
								break;
							}
						}
						break;
					}
				}
				break;
			}
			if(str2.charAt(i)=='?') {
				for(int j=i+1;j<str2.length();j++) {
					if(str2.charAt(j)=='?') {
						for(int k=j+1;k<str2.length();k++) {
							if(str2.charAt(k)==',') {
								str20=str2.substring(k+1);
								break;
							}
						}
						break;
					}
				}
				break;
			}
			if(str2.charAt(i)==',') {
				str20=str2.substring(i+1);
				break;
			}
		}
		line=Stringtoline1(str20);
		return line;
	}
	
	public int Stringtoline3(String str3) {//ֻ���m��tָ�����ܴ��ڵĵ�������ַ
		int line = 0;
		for(int i=0;i<str3.length();i++) {
			if(str3.charAt(i)=='/') {//����������
				for(int j=i+1;j<str3.length();j++) {
					if(str3.charAt(j)=='/') {
						i=j+1;
						break;
					}
				}
			}
			if(str3.charAt(i)=='?') {//����������
				for(int j=i+1;j<str3.length();j++) {
					if(str3.charAt(j)=='?') {
						i=j+1;
						break;
					}
				}
			}
			if(str3.charAt(i)=='m'&&str3.charAt(i-1)!='\'') {//����m������
				String str30=str3.substring(i+1);
				if(str30.indexOf("/")!=-1||str30.indexOf("?")!=-1) {
					line=Stringtoline1(str30);
					break;
				}
				else {
					line=getline0(str30);
					break;
				}
			}
			if(str3.charAt(i)=='t'&&str3.charAt(i-1)!='\'') {//����t������
				String str30=str3.substring(i+1);
				if(str30.indexOf("/")!=-1||str30.indexOf("?")!=-1) {
					line=Stringtoline1(str30);
					break;
				}
				else {
					line=getline0(str30);
					break;
				}
			}
		}
		return line;
	}
	
	public int getline0(String ed) {//��ü�����µ���һ��
		int line1 = 0;
		
		if(ed.equals(".")) {
			return linenow;
		}
		else if(ed.equals("$")) {
			return main000.length;
		}
		else if(ed.substring(0, 1).equals("\'")) {
			String k0=String.valueOf(ed.charAt(1));
			for(int i=0;i<linek.size();i++) {
				if(linek.get(i).toString().endsWith(k0)) {
					for(int j=0;j<main000.length;j++) {
						if(main000[j]==linek.get(i).toString().substring(0,linek.get(i).toString().length()-1)) {
							line1=j+1;
						}
					}
					if(ed.substring(2,3).equals("-")) {
						line1=line1-Integer.parseInt(ed.substring(3, 4));
						return line1;
					}
					if(ed.substring(2,3).equals("+")) {
						line1=line1+Integer.parseInt(ed.substring(3, 4));
						return line1;
					}
					else {
						return line1;
					}
				}
			}
		}
		else if(ed.substring(0, 1).equals(".")) {//�Ե�ǰ�в���
			String line=ed.substring(1,2);
			if(line.equals("-")) {
				String linee=ed.substring(2,3);
				line1=linenow-Integer.valueOf(linee);
				return line1;
			}
			else if(line.equals("+")) {
				String linee=ed.substring(2,3);
				line1=linenow+Integer.valueOf(linee);
				return line1;
			}
			else if(line.equals("$")) {
				return -1;
			}
			else {
				line1=linenow;
				return line1;
			}
		}
		else if(ed.substring(0, 1).equals("$")) {//�����һ�в���
			if(ed.equals("$")) {
				line1=main000.length;
				return line1;
			}
			else {
			String line=ed.substring(1,2);
			if(line.equals("-")) {
				String linee=ed.substring(2,3);
				if(linee.equals(".")) {
					line1=main000.length-linenow;
					return line1;
				}
				if(Character.isDigit(linee.charAt(0))) {
					line1=main000.length-Integer.valueOf(linee);
					return line1;
				}
				else {
					line1=-1;
					return line1;
				}
			}
			if(line.equals("+")) {
				String linee=ed.substring(2,3);
				if(linee.equals(".")) {
					line1=main000.length+linenow;
					return line1;
				}
				if(Character.isDigit(linee.charAt(0))) {
					line1=main000.length+Integer.valueOf(linee);
					return line1;
				}
				else {
					line1=-1;
					return line1;
				}
			}
			else {
			line1=main000.length;
			return line1;
			}
			}
		}
		else if(ed.substring(0, 1).equals("-")) {//��ǰ����ǰ
			String line=ed.substring(1, 2);
			line1=linenow-Integer.valueOf(line);
			return line1;
		}
		else if(ed.substring(0, 1).equals("+")) {//��ǰ�����
			String line=ed.substring(1, 2);
			line1=linenow+Integer.valueOf(line);
			return line1;
		}//�ڼ���
		else if(Character.isDigit(ed.charAt(0))){
			String line=ed.substring(0,1);
			line1=Integer.valueOf(line);
			return line1;
			}
		else {
			line1=-1;
			return line1;
		}
		return line1;
	}
	
	public int getline02(String ed) {//��������еĵڶ���
		int line2=0;
		String[] ed1=ed.split(",");
		String ed2=ed1[1];
		line2=getline0(ed2);
		return line2;
	}
	
	public String readcommand_s(String ed){//��ȡsǰ�ĵ�ַ
		String strs="";
		for(int i=0;i<ed.length();i++) {
			if(ed.charAt(i)=='s') {//����
				strs=ed.substring(0, i);
				return strs;
				}
			else if(ed.charAt(i)=='/') {//����������
				for(int j=i+1;j<ed.length();j++) {
					if(ed.charAt(j)=='/') {
						i=j;
						break;
						}
					}
				}
			else if(ed.charAt(i)=='?') {//����������
				for(int j=i+1;j<ed.length();j++) {
					if(ed.charAt(j)=='?') {
						i=j;
						break;
						}
					}
				}
			}
		return strs;
		}
	
	public String readcommand_s2(String ed){//��ȡs���������
		String strs="";
		for(int i=0;i<ed.length();i++) {
			if(ed.charAt(i)=='/') {//����������
				for(int j=i+1;j<ed.length();j++) {
					if(ed.charAt(j)=='/') {
						i=j+1;
						break;
						}
					}
				}
			if(ed.charAt(i)=='?') {//����������
				for(int j=i+1;j<ed.length();j++) {
					if(ed.charAt(j)=='?') {
						i=j+1;
						break;
						}
					}
				}
			if(ed.charAt(i)=='s') {//����
				strs=ed.substring(i+1);
				break;
				}
			}
		return strs;
		}
	
	public void commands(String ed) {//s�ķ���
		String strs=readcommand_s(ed);
		if(ed.startsWith("s")) {
			line0=linenow;
			line02=linenow;
			}
		else if(ed.startsWith(",")) {
			line0=1;
			line02=main000.length;
		}
		else if(ed.startsWith(";")) {
			line0=linenow;
			line02=main000.length;
		}
		else if(strs.indexOf(",")!=-1) {
			line0=Stringtoline1(strs);
			line02=Stringtoline2(strs);
			}
		else {
			line0=Stringtoline1(strs);
			line02=line0;
			}
		if(line0<=line02&&line0>=1&&line02>=1&&line0<=main000.length&&line0<=main000.length) {
		command_s=readcommand_s2(ed);
		String strs1 = "";
		String strs2 = "";
		for(int i=0;i<command_s.length();i++) {
			if(command_s.charAt(i)=='/') {
				for(int j=i+1;j<command_s.length();j++) {
					if(command_s.charAt(j)=='/') {
						strs1=command_s.substring(i+1,j);
						for(int k=j+1;k<command_s.length();k++) {
							if(command_s.charAt(k)=='/') {
								strs2=command_s.substring(j+1,k);
								break;
							}
						}break;
					}
				}break;
			}
		}
		int count1=0;//Ѱ�ҵڼ���
		int count2=0;//�ж��ǲ���ÿһ�ж�����
		if(command_s.endsWith("g")) {
			for(int i=line0-1;i<line02;i++) {
				for(int j=0;j<main000[i].length();j++) {		
					if(j+strs1.length()>main000[i].length()) {
						break;
						}
					else {
						if((main000[i].substring(j,j+strs1.length())).equals(strs1)) {
							main000[i]=main000[i].substring(0,j)+strs2+main000[i].substring(j+strs1.length());
							linenow=i+1;
							count1=0;
							count2++;
							}
						}
					}
				}
			}
		else {
			if(command_s.endsWith("/")) {
				for(int i=line0-1;i<line02;i++) {
					for(int j=0;j<main000[i].length();j++) {
						if(j+strs1.length()>main000[i].length()) {
							break;
							}
						if(main000[i].substring(j, j+strs1.length()).equals(strs1)) {
							main000[i]=main000[i].substring(0,j)+strs2+main000[i].substring(j+strs1.length());
							linenow=i+1;
							count1=0;
							count2++;
							break;
						}
					}	
				}
			}
			else {
				int count=Integer.parseInt(command_s.substring(command_s.length()-1));
				for(int i=line0-1;i<line02;i++) {
					for(int j=0;j<main000[i].length();j++) {
						if(j+strs1.length()>main000[i].length()) {
							break;
						}
						else {
							if((main000[i].substring(j,j+strs1.length())).equals(strs1)) {
								if(count1==count-1) {
									main000[i]=main000[i].substring(0,j)+strs2+main000[i].substring(j+strs1.length());
									linenow=i+1;
									count1=0;
									count2++;
									break;
								}
								else {
									j=j+strs1.length()-1;
									if(j+2>main000[i].length()) {
										count1=0;
										}
									else {
										count1++;
										}
									}
								}
							}
						}
					}
				}
			}
		if(count2==0) {
			System.out.println("?");
			}
		else {
			ArrayList saves=new ArrayList();//�����list
			for(int k=0;k<main000.length;k++) {
				saves.add(main000[k]);
				}
			tosave.add(saves);
			save_linenow.add(linenow);
			}
		}
		else {
			System.out.println("?");
		}
		}
	
	public void conduct(String[] main) {//��Ҫ�Ĳ���
		getAddress address=new getAddress();
		String[] main2=main;//�������û�б䶯������
		main000=main;//�ѵ�һ�ε�main��ɶ�̬main000
		linenow=main.length;//��ǰ�г�ʼ��
		if(pattern.ed.equals("ed")) {//�ж�Ĭ���ļ���
			defaultname="";
		}
		else {
			defaultname=pattern.edfile[1];
		}
		for(int i=0;i<main.length;i++) {//����Ķ�������
			save0.add(main[i]);
		}
		tosave.add(save0);
		save_linenow.add(linenow);
		int save=0;//�ж�q��һ������
		while(true) {
			String ed=pattern.input.nextLine();//ed��ģʽ��ed0���ı�
			if(ed.equals("q")) {
				if(tosave.size()==1||save==1) {
					break;
				}
				else {
					System.out.println("?");
					save=1;
					}
				}
			if(ed.indexOf("Q")!=-1&&ed.indexOf("k")==-1&&ed.indexOf("\'")==-1){
				if(ed.equals("Q")) {
					break;
				}
				else {
					System.out.println("?");
				}
			}
			if(address.getSimpleAddress(ed).endsWith("i")&&address.Condition(address.getSimpleAddress(ed), "i")) {//��ӵ�ָ����ǰ��
				if(ed.equals(".i")||ed.equals("i")) {
					line0=linenow;
				}
				else if(ed.indexOf("/")!=-1||ed.indexOf("?")!=-1){
					line0=Stringtoline1(ed);//��õڼ���
				}
				else {
					line0=Stringtoline1(ed);//��õڼ���
				}
				if(line0<=main000.length&&line0>=0) {
					ArrayList<String> eded=new ArrayList<String>();
					while(true) {//ѭ������ģʽ
						String ed0=pattern.input.nextLine();
						if(ed0.equals(".")) {
							break;
						}
						else {
						eded.add(ed0);
						}
					}
					int lines=eded.size();//����������ݵ�����
					String[] main1=new String[main000.length+lines];//�½�һ�����鱣����ĵ�����
					if(line0>=1) {
						for(int i=0;i<line0-1;i++) {//����û���֮ǰ����
							main1[i]=main000[i];
							}
						for(int i=0;i<lines;i++) {//����µ�����
							main1[line0-1+i]=eded.get(i);
							}
						for(int i=line0-1;i<main000.length;i++) {//����û���֮������
							main1[lines+i]=main000[i];
							}
						linenow=line0+lines-1;
						}
					else if(line0==0) {//����ai��Ч
						for(int i=0;i<lines;i++) {
							main1[i]=eded.get(i);
						}
						linenow=lines;
					}
					main000=main1;
					ArrayList savei=new ArrayList();
					for(int i=0;i<main000.length;i++) {
						savei.add(main000[i]);
					}
					tosave.add(savei);
					save_linenow.add(linenow);
					}
				else {
					System.out.println("?");
				}
				save=0;
			}
			if(address.getSimpleAddress(ed).endsWith("a")&&address.Condition(address.getSimpleAddress(ed), "a")) {//��ӵ�ָ���к���
				if(ed.equals(".a")||ed.equals("a")) {
					line0=linenow;
				}
				else if(ed.indexOf("/")!=-1||ed.indexOf("?")!=-1){
					line0=Stringtoline1(ed);//��õڼ���
				}
				else {
					line0=Stringtoline1(ed);//��õڼ���
				}
				if(line0<=main000.length&&line0>=0) {
					ArrayList<String> eded=new ArrayList<String>();
					while(true) {//ѭ������ģʽ
						String ed0=pattern.input.nextLine();
						if(ed0.equals(".")) {
							break;
							}
						else {
							eded.add(ed0);
							}
						}
					int lines=eded.size();//����������ݵ�����
					String[] main1=new String[main000.length+lines];//�½�һ�����鱣����ĵ�����//����Ҫ����������
					for(int i=0;i<line0;i++) {//����û���֮ǰ����
						main1[i]=main000[i];
						}
					for(int i=0;i<lines;i++) {//����µ�����
						main1[line0+i]=eded.get(i);
						}
					for(int i=line0;i<main000.length;i++) {//����û���֮������
						main1[lines+i]=main000[i];
						}
					main000=main1;
					ArrayList savea=new ArrayList();//�����list
					for(int i=0;i<main000.length;i++) {
						savea.add(main000[i]);
					}
					tosave.add(savea);
					linenow=line0+lines;
					save_linenow.add(linenow);
					}
				else {
					System.out.println("?");
					}
				save=0;
				}
			if(address.getSimpleAddress(ed).endsWith("c")&&address.Condition(address.getSimpleAddress(ed), "c")) {//�滻����
				if(ed.equals(",c")) {
					line0=1;
					line02=main000.length;
					}
				else if(ed.equals(";c")) {
					line0=linenow;
					line02=main000.length;
					}
				else if(ed.equals("c")||ed.equals(".c")) {
					line0=linenow;
					line02=line0;
					}
				else if(address.getSimpleAddress(ed).indexOf(",")!=-1) {
					line0=Stringtoline1(ed);
					line02=Stringtoline2(ed);
					}
				else if(address.getSimpleAddress(ed).indexOf(",")==-1) {
					line0=Stringtoline1(ed);	
					line02=line0;	
					}
				if(line02<=main000.length&&line02>=1&&line0>=1&&line0<=line02) {
					ArrayList<String> eded=new ArrayList<String>();
					while(true) {//ѭ������ģʽ
						String ed0=pattern.input.nextLine();
						if(ed0.equals(".")) {
							break;
							}
						else {
							eded.add(ed0);
							}
						}
					int lines=eded.size();//��ʼ�滻
					String[] main1=new String[main000.length-(line02-line0+1)+lines];
					for(int i=0;i<line0-1;i++) {//����һ��ʼû���
						main1[i]=main000[i];
						}
					for(int i=line0-1;i<line0+lines-1;i++) {//�ӵڼ��иı�����
						main1[i]=eded.get(i-line0+1);
						}
					for(int i=line0+lines-1;i<main1.length;i++) {//�������û���
						main1[i]=main000[i+(line02-line0+1-lines)];
						}
					main000=main1;
					ArrayList savec1=new ArrayList();
					for(int i=0;i<main000.length;i++) {//�����LIST
						savec1.add(main000[i]);
					}
					tosave.add(savec1);
					linenow=line0+lines-1;
					save_linenow.add(linenow);
					}
				else {
					System.out.println("?");
					}
				save=0;
				}
			if(address.getSimpleAddress(ed).endsWith("d")&&address.Condition(address.getSimpleAddress(ed), "d")) {//ɾ������
				if(ed.equals(",d")) {
					line0=1;
					line02=main000.length;
				}
				else if(ed.equals(";d")) {
					line0=linenow;
					line02=main000.length;
				}
				else if(ed.equals("d")||ed.equals(".d")) {
					line0=linenow;
					line02=line0;
				}
				else if(address.getSimpleAddress(ed).indexOf(",")!=-1) {
					line0=Stringtoline1(ed);
					line02=Stringtoline2(ed);
				}
				else if(address.getSimpleAddress(ed).indexOf(",")==-1) {
					line0=Stringtoline1(ed);
					line02=line0;
				}
				int lines=line02-line0;
				if(line02<=main000.length&&line0>=1&&line02>=1&&line0<=line02) {
					String[] main1=new String[main000.length-lines-1];
					for(int i=0;i<line0-1;i++) {
						main1[i]=main000[i];
						}
					for(int i=line0-1;i<main000.length-lines-1;i++) {
						main1[i]=main000[line02+i-line0+1];
						}
					if(line02==main000.length) {
						linenow=line0-1;
					}
					else {
						linenow=line0;
						}
					main000=main1;
					ArrayList saved2=new ArrayList();
					for(int i=0;i<main000.length;i++) {
						saved2.add(main000[i]);
					}
					tosave.add(saved2);
					save_linenow.add(linenow);
					}
				else {
					System.out.println("?");
					}
				save=0;
				}
			if(ed.endsWith("p")&&address.Condition(address.getSimpleAddress(ed), "p")) {//��ӡ����
				if(ed.equals(",p")) {
					line0=1;
					line02=main000.length;;
				}
				else if(ed.equals(";p")) {
					line0=linenow;
					line02=main000.length;
				}
				else if(ed.equals("p")||ed.equals(".p")) {
					line0=linenow;
					line02=line0;
				}
				else if(address.getSimpleAddress(ed).indexOf(",")!=-1) {
					line0=Stringtoline1(ed);
					line02=Stringtoline2(ed);
				}
				else if(address.getSimpleAddress(ed).indexOf(",")==-1) {
					line0=Stringtoline1(ed);
					line02=line0;
					}
				if(line02<=main000.length&&line0>=0&&line02>=0&&line0<=line02) {
					for(int i=line0-1;i<line02;i++) {
						System.out.println(main000[i]);
						}
					linenow=line02;
					save_linenow.set(save_linenow.size()-1, linenow);
					}
				else {
					System.out.println("?");
					}
				save=0;
				}
			if(address.getSimpleAddress(ed).endsWith("=")) {
				if(ed.indexOf("?")!=-1||ed.indexOf("/")!=-1) {
					line0=Stringtoline1(ed);
				}
				else if(ed.equals("=")) {
					line0=linenow;
				}
				else {
					line0=Stringtoline1(ed);
				}
				if(line0<=main000.length&&line0>=0) {
					System.out.println(line0);
					}
				else {
					System.out.println("?");
					}
				}
			else if((address.getSimpleAddress(ed).indexOf("z")!=-1&&address.Condition(address.getSimpleAddress(ed), "z"))) {
				String[] zz=ed.split("z");
				if(ed.equals("z")) {
					line0=linenow+1;
					line02=main000.length-line0;
				}
				else if(ed.startsWith("z")) {
					line0=linenow+1;
					line02=Stringtoline1(zz[1]);
				}
				else if(ed.endsWith("z")) {
					line0=Stringtoline1(ed);
					line02=main000.length;
				}
				else {
					line0=Stringtoline1(ed);
					line02=Stringtoline1(zz[zz.length-1]);
				}
				if(line0<=main000.length&&line0>=0&&line02>=0) { 
					if(line0+line02<=main000.length) {
						for(int i=line0-1;i<line0+line02;i++) {
							System.out.println(main000[i]);
							}
						linenow=line0+line02;
						}
					else {
						for(int i=line0-1;i<main000.length;i++) {
							System.out.println(main000[i]);
						}
						linenow=main000.length;
						}
					save_linenow.set(save_linenow.size()-1, linenow);
					}
				else {
					System.out.println("?");
					}
				save=0;
				}
			else if(address.getSimpleAddress(ed).indexOf("f")!=-1&&address.Condition(ed, "f")) {
				if(ed.startsWith("f")==false) {
					System.out.println("?");
				}
				else if(pattern.ed.equals("ed")) {
					if(ed.equals("f")) {
						System.out.println("?");
						}
					else{
						String newname=ed.split(" ")[1];
						defaultname=newname;
						}
				}
				else {
					if(ed.equals("f")) {
						System.out.println(defaultname);
						}
					else{
						String newname=ed.split(" ")[1];
						defaultname=newname;
						}
					}
				}
			else if(address.getSimpleAddress(ed).indexOf("w ")!=-1) {
				String[] edsave=address.getSimpleAddress(ed).split(" ");
				if(edsave[0].equals("w")) {
					line0=1;
					line02=main000.length;
					save=1;
					}
				else if(ed.startsWith(";")) {
					if(ed.charAt(1)=='w') {
						line0=linenow;
						line02=main000.length;
					}
					else {
						line0=line02=-1;
					}
				}
				else if(ed.startsWith(",")) {
					if(ed.charAt(1)=='w') {
						line0=1;
						line02=main000.length;
					}
					else {
						line0=line02=-1;
					}
				}
				else if(address.getSimpleAddress(ed).indexOf(",")!=-1){
					line0=Stringtoline1(ed);
					line02=Stringtoline2(ed);
				}
				else if(address.getSimpleAddress(ed).indexOf(",")==-1){
					line0=Stringtoline1(ed);
					line02=line0;
					}
				File filesave=new File(edsave[1]);
				if(line0<=line02&&line0>=1&&line02>=1&&line02<=main000.length) {
					if(filesave.exists()) {
						try{
							BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(new FileOutputStream(edsave[1])));
							for(int i=line0-1;i<line02;i++) {
								bw.write(main000[i]+"\r\n");
								}
							bw.close();
							} catch (Exception e) {e.printStackTrace();}
						}
					else {
						try{
							filesave.createNewFile();
							BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(new FileOutputStream(edsave[1])));
							for(int i=line0-1;i<line02;i++) {
								bw.write(main000[i]+"\r\n");
								}
							bw.close();
							} catch (Exception e) {e.printStackTrace();}
						}
					}
				else {
					System.out.println("?");
					}
				}
			else if(address.getSimpleAddress(ed).endsWith("w")&&address.Condition(address.getSimpleAddress(ed), "w")) {
				if(pattern.ed.equals("ed")&&defaultname.equals("")) {
					System.out.println("?");
				}
				else {
					if(ed.equals("w")) {
						line0=1;
						line02=main000.length;
						save=1;
						File filew=new File(defaultname);
						if(filew.exists()==false) {
							try {
								filew.createNewFile();
							} catch (IOException e) {
								e.printStackTrace();
							}
						}
					}
					else if(ed.startsWith(";")) {
						if(ed.charAt(1)=='w') {
							line0=linenow;
							line02=main000.length;
						}
						else {
							line0=line02=-1;
						}
					}
					else if(ed.startsWith(",")) {
						if(ed.charAt(1)=='w') {
							line0=1;
							line02=main000.length;
							save=1;
						}
						else {
							line0=line02=-1;
						}
					}
					else if(address.getSimpleAddress(ed).indexOf(",")!=-1){
						line0=Stringtoline1(ed);
						line02=Stringtoline2(ed);
					}
					else if(address.getSimpleAddress(ed).indexOf(",")==-1){
						line0=Stringtoline1(ed);
						line02=line0;
						}
					if(line0<=line02&&line0>=1&&line02>=1&&line02<=main000.length) {
						try{
							BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(new FileOutputStream(defaultname)));
							for(int i=line0-1;i<line02;i++) {
								bw.write(main000[i]+"\r\n");
								}
							bw.close();
							} catch (Exception e) {e.printStackTrace();}
						}
					else {
						System.out.println("?");
						}
					}
				}
			else if(address.getSimpleAddress(ed).indexOf("W ")!=-1) {
				String[] edsave=ed.split(" ");
				if(edsave[0].equals("W")) {
					line0=1;
					line02=main000.length;
					save=1;
					}
				else if(address.getSimpleAddress(ed).indexOf(",")!=-1){
					line0=Stringtoline1(ed);
					line02=Stringtoline2(ed);
				}
				else if(address.getSimpleAddress(ed).indexOf(",")==-1){
					line0=Stringtoline1(ed);
					line02=line0;
					}
				File filesave=new File(edsave[1]);
				if(filesave.exists()) {
					try{
						BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(new FileOutputStream(edsave[1],true)));
						for(int i=line0-1;i<line02;i++) {
							bw.write(main000[i]+"\r\n");
							}
						bw.close();
						} catch (Exception e) {e.printStackTrace();}
					}
				else {
					try{
						filesave.createNewFile();
						BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(new FileOutputStream(edsave[1])));
						for(int i=line0-1;i<line02;i++) {
							bw.write(main000[i]+"\r\n");
							}
						bw.close();
						} catch (Exception e) {e.printStackTrace();}
					ArrayList savew=new ArrayList();
					for(int i=0;i<main000.length;i++) {
						savew.add(main000[i]);
					}
					tosave.add(savew);
					}
				}
			else if(address.getSimpleAddress(ed).endsWith("W")&&address.getSimpleAddress(ed).indexOf("f ")==-1) {
				if(pattern.ed.equals("ed")&&defaultname=="") {
					System.out.println("?");
				}
				else {
					if(ed.equals("W")) {
						line0=1;
						line02=main000.length;
						save=1;
					}
					else if(address.getSimpleAddress(ed).indexOf(",")!=-1){
						line0=Stringtoline1(ed);
						line02=Stringtoline2(ed);
					}
					else if(address.getSimpleAddress(ed).indexOf(",")==-1){
						line0=Stringtoline1(ed);
						line02=line0;
						}
					try{
						BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(new FileOutputStream(defaultname,true)));
						for(int i=line0-1;i<line02;i++) {
							bw.write(main000[i]+"\r\n");
							}
						bw.close();
						} catch (Exception e) {e.printStackTrace();}
					}
				}
			else if(((address.getSimpleAddress(ed).indexOf("m")!=-1)||(address.getSimpleAddress(ed).endsWith("m")))&&ed.endsWith("c")==false&&address.Condition(address.getSimpleAddress(ed), "m")) {
				if(ed.equals("m")) {
					line0=linenow;
					line02=line0;
					line03=linenow;
				}
				else if(ed.startsWith(",")) {
					line0=1;
					line02=main000.length;
					line03=Stringtoline3(ed);
				}
				else if(ed.startsWith(";")) {
					line0=linenow;
					line02=main000.length;
					line03=Stringtoline3(ed);
				}
				else if(address.getSimpleAddress(ed).indexOf(",")!=-1) {
					if(ed.endsWith("m")) {
						line0=Stringtoline1(ed);
						line02=Stringtoline2(ed);
						line03=linenow;
						}
					else {
						line0=Stringtoline1(ed);
						line02=Stringtoline2(ed);
						line03=Stringtoline3(ed);
						}
					}
				else if(address.getSimpleAddress(ed).indexOf(",")==-1) {
					if(ed.endsWith("m")){
						line0=Stringtoline1(ed);
						line02=line0;
						line03=linenow;
					}
					else if(ed.startsWith("m")) {
						line0=linenow;
						line02=line0;
						String edm=ed.substring(1);
						line03=Stringtoline1(edm);
					}
					else {
						line0=Stringtoline1(ed);
						line02=line0;
						line03=Stringtoline3(ed);
						}
					}
				if(line02<=main000.length&&line03<=main000.length&&line0>=1&&line02>=1&&line0<=line02&&line03>=1) {
					String[] main1=new String[main000.length];
					if(line03<line0) {
						for(int i=0;i<line03;i++) {//����
							main1[i]=main000[i];
						}
						for(int i=line03;i<line03+line02-line0+1;i++) {//�ƶ�
							main1[i]=main000[i-line03+line0-1];
						}
						for(int i=line03+line02-line0+1;i<line02;i++) {
							main1[i]=main000[i-line02+line0-1];
						}
						for(int i=line02;i<main1.length;i++) {
							main1[i]=main000[i];
						}
						linenow=line03+line02-line0+1;
					}
					if(line03<=line02&&line03>=line0&&line0<line02) {
						System.out.println("?");
					}
					if(line0==line02&&line02==line03) {
						main1=main000;
					}
					if(line03>line02) {
						for(int i=0;i<line0-1;i++) {
							main1[i]=main000[i];
						}
						for(int i=line0-1;i<line03-line02+line0-1;i++) {
							main1[i]=main000[i-line0+1+line02];
						}
						for(int i=line03-line02+line0-1;i<line03;i++) {
							main1[i]=main000[i-(line03-line02+line0-1)+line0-1];
						}
						for(int i=line03;i<main1.length;i++) {
							main1[i]=main000[i];
						}
						linenow=line03;
					}
					main000=main1;
					ArrayList savem=new ArrayList();//�����list
					for(int i=0;i<main000.length;i++) {
						savem.add(main000[i]);
					}
					tosave.add(savem);
					save_linenow.add(linenow);
				}
				else {
					System.out.println("?");
				}
			}
			else if(((address.getSimpleAddress(ed).indexOf("t")!=-1)||(address.getSimpleAddress(ed).endsWith("t")))&&address.Condition(address.getSimpleAddress(ed), "t")) {
				if(address.getSimpleAddress(ed).indexOf(",")!=-1||ed.startsWith(";")||ed.startsWith(",")) {
					if(ed.startsWith(",")) {
						line0=1;
						line02=main000.length;
						line03=Stringtoline3(ed);
					}
					else if(ed.startsWith(";")) {
						line0=linenow;
						line02=main000.length;
						line03=Stringtoline3(ed);
					}
					else if(ed.endsWith("t")) {
						line0=Stringtoline1(ed);
						line02=Stringtoline2(ed);
						line03=linenow;
						}
					else {
						line0=Stringtoline1(ed);
						line02=Stringtoline2(ed);
						line03=Stringtoline3(ed);
						}
					}
				else {
					if(ed.equals("t")) {
						line0=linenow;
						line02=linenow;
						line03=linenow;
					}
					else if(ed.endsWith("t")){
						line0=Stringtoline1(ed);
						line02=line0;
						line03=linenow;
					}
					else if(ed.startsWith("t")) {
						line0=linenow;
						line02=line0;
						String edm=ed.substring(1);
						line03=Stringtoline1(edm);
						}
					else {
						line0=Stringtoline1(ed);
						line02=line0;
						line03=Stringtoline3(ed);
					}
					}
				if(line02<=main000.length&&line0>=1&&line02>=1&&line03>=1&&line03<=main000.length&&line0<=line02) {
					String[] main1=new String[main000.length+line02-line0+1];
					for(int i=0;i<line03;i++) {
						main1[i]=main000[i];
					}
					for(int i=line03;i<line03+line02-line0+1;i++) {
						main1[i]=main000[i-line03+line0-1];
					}
					for(int i=line03+line02-line0+1;i<main1.length;i++) {
						main1[i]=main000[i-line02+line0-1];
					}
					linenow=line03+line02-line0+1;
					main000=main1;
					ArrayList savet=new ArrayList();//�����list
					for(int i=0;i<main000.length;i++) {
						savet.add(main000[i]);
					}
					tosave.add(savet);
					save_linenow.add(linenow);
				}
				else {
					System.out.println("?");
					}
				}
			else if(address.getSimpleAddress(ed).endsWith("j")&&address.Condition(address.getSimpleAddress(ed), "j")) {
				if(ed.equals("j")) {
					line0=linenow;
					line02=linenow+1;
				}
				else if(ed.equals(",j")) {
					line0=1;
					line02=main000.length;
				}
				else if(ed.equals(";j")) {
					line0=linenow;
					line02=main000.length;
				}
				else if(address.getSimpleAddress(ed).indexOf(",")!=-1&&ed.equals(",j")==false) {
					line0=Stringtoline1(ed);
					line02=Stringtoline2(ed);
				}
				else if(address.getSimpleAddress(ed).indexOf(",")==-1&&ed.equals("j")==false&&ed.equals(";j")==false) {
					line0=Stringtoline1(ed);
					line02=Stringtoline1(ed);
				}
				if(line02<=main000.length&&line0>=1&&line02>=1&&line02>line0) {
					String[] main1=new String[main000.length-(line02-line0)];
					for(int i=0;i<line0-1;i++) {
						main1[i]=main000[i];
					}
					String str="";
					for(int i=line0-1;i<line02;i++) {
						str=str+main000[i];
					}
					main1[line0-1]=str;
					for(int i=line0;i<main1.length;i++) {
						main1[i]=main000[i-line0+line02];
					}
					linenow=line0;
					main000=main1;
					ArrayList savej=new ArrayList();//�����list
					for(int i=0;i<main000.length;i++) {
						savej.add(main000[i]);
					}
					tosave.add(savej);
					save_linenow.add(linenow);
				}
				else if(line02<=main000.length&&line0>=1&&line02>=1&&line02==line0) {
				}
				else {
					System.out.println("?");
				}
			}
			else if(address.getSimpleAddress(ed).indexOf("s")!=-1&&address.Condition(address.getSimpleAddress(ed), "s")) {
				if(ed.endsWith("s")) {
					if(command_s.equals("")) {
						System.out.println("?");
						}
					else {
						ed=ed+command_s;
						commands(ed);
						}
					}//��ûд��
				else {
					commands(ed);
					}
				}
			else if(ed.equals("u")) {
				if(tosave.size()==1||save_linenow.size()==1) {
					System.out.println("?");
				}
				else {
					ArrayList saveu=new ArrayList();
					tosave.remove(tosave.size()-1);
					saveu=tosave.get(tosave.size()-1);
					String[] save000=new String[saveu.size()];
					for(int i=0;i<save000.length;i++) {
						save000[i]=(String) saveu.get(i);
					}
					main000=save000;
					save_linenow.remove(save_linenow.size()-1);
					linenow=save_linenow.get(save_linenow.size()-1);
				}
			}
			else if(address.getSimpleAddress(ed).indexOf("k")!=-1&&address.getSimpleAddress(ed).indexOf("\'k")==-1&&(Character.isLetter(ed.charAt(ed.length()-1))||Character.isDigit(ed.charAt(ed.length()-1)))) {
				if(Character.isUpperCase(ed.charAt(ed.length()-1))||Character.isDigit(ed.charAt(ed.length()-1))) {
					System.out.println("?");
				}
				else {
					if(ed.startsWith("k")) {
						line0=linenow;
					}
					else {
						line0=Stringtoline1(ed);
					}
					char k=ed.charAt(ed.length()-1);
					if(linek.size()==0) {
						linek.add(main000[line0-1]+String.valueOf(k));
					}
					else {
						for(int i=0;i<linek.size();i++) {
							if(linek.get(i).toString().endsWith(String.valueOf(k))) {
								linek.remove(i);
								linek.add(main000[line0-1]+String.valueOf(k));
								}
							if(i==linek.size()-1) {
								linek.add(main000[line0-1]+String.valueOf(k));
								break;
								}
							}
						}
					}
				}
			}
		}
	}
