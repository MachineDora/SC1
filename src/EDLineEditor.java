//��ʵ�Ĳ���
import java.io.*;
import java.util.*;

public class EDLineEditor {
	
	static String[] edfile;
	static File fff;
	static String ed;
	static Scanner input;
	
	public static void main(String[] args)  {
		ExistFile dzy=new ExistFile();
		input=new Scanner(System.in);
		ed=input.nextLine();
		String file_data="";
		if(ed.equals("ed")) {
			String[] main= {};
			dzy.conduct(main);
			}
		if(ed.startsWith("ed ")) {//��ȡ�ļ�������������
			String[] main;
			edfile=ed.split(" ");
			fff=new File(edfile[1]);
			if(fff.length()!=0) {
			try{
				String line="";
				BufferedReader br=new BufferedReader(new FileReader(fff));
				while((line=br.readLine())!=null) {
					file_data=file_data+line+"\r\n";
					}
				br.close();
				}catch (IOException ex){ex.printStackTrace();
				}
			main=file_data.split("\r\n");//���ļ����ݱ���Ϊ����
			}
			else {
				main=new String[0];
			}
			dzy.conduct(main);
			}
	}
}
