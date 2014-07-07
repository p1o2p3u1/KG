


import java.util.ArrayList;

import edu.fudan.ml.types.Dictionary;
import edu.fudan.nlp.cn.tag.CWSTagger;

/**
 * �ִ�ʹ��ʾ��
 * @author xpqiu
 *
 */
public class test2 {
	/**
	 * ������
	 * @param args 
	 * @throws IOException 
	 * @throws  
	 */
	public static void main(String[] args) throws Exception {
		CWSTagger tag = new CWSTagger("./models/seg.m");
		System.out.println("��ʹ�ôʵ�ķִʣ�");
		String str = " ý������о���������, �߼������ھ�(data mining)���ѡ� ��phone������";
		String s = tag.tag(str);
		System.out.println(s);
		
		//����Ӣ��Ԥ����
		tag.setEnFilter(true);
		s = tag.tag(str);
		System.out.println(s);
//		tag.setEnFilter(false);
		System.out.println("\n������ʱ�ʵ䣺");
		ArrayList<String> al = new ArrayList<String>();
		al.add("�����ھ�");
		al.add("ý������о���");
		al.add("��phone");
		Dictionary dict = new Dictionary(false);
		dict.addSegDict(al);
		tag.setDictionary(dict);
		s = tag.tag(str);
		System.out.println(s);
		
		
		CWSTagger tag2 = new CWSTagger("./models/seg.m", new Dictionary("./models/dict.txt"));
		System.out.println("\nʹ�ôʵ�ķִʣ�");
		String str2 = "ý������о���������, �߼������ھ���ѡ� ��phone������";
		String s2 = tag2.tag(str2);
		System.out.println(s2);
		
		//ʹ�ò��ϸ�Ĵʵ�
		CWSTagger tag3 = new CWSTagger("./models/seg.m", new Dictionary("./models/dict_ambiguity.txt",true));
		//��������ʵ䣬����ʵ����С��������������ˡ��͡��ˡ�, ��ʹ��Viterbi��������������
		System.out.println("\nʹ�ò��ϸ�Ĵʵ�ķִʣ�");
		String str3 = "ý������о���������, �߼������ھ����";
		String s3 = tag3.tag(str3);
		System.out.println(s3);
		str3 = "���͸���ѧϵ��ͬѧһ����� (�͸�������ѧ��ѧϵ���ڴʵ���)";
		s3 = tag3.tag(str3);
		System.out.println(s3);
		
		System.out.println("\n�����ļ���");
		String s4 = tag.tagFile("./example-data/data-tag.txt");
		System.out.println(s4);
		
		String s5 = tag2.tagFile("./example-data/data-tag.txt");
		System.out.println(s5);
		
	}

}
