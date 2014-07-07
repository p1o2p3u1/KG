


import java.util.HashMap;

import edu.fudan.nlp.cn.tag.NERTagger;

/**
 * ʵ����ʶ��ʹ��ʾ��
 * @author xpqiu
 *
 */
public class test4 {	


	/**
	 * ������
	 * @param args
	 * @throws IOException 
	 * @throws  
	 */
	public static void main(String[] args) throws Exception {
		
		NERTagger tag = new NERTagger("./models/seg.m","./models/pos.m");
		String str = " ��������Ѷ������ʱ��4��15��03:00(Ӣ������ʱ��14��20:00)��2009/10����Ӣ�������򳬼�������34��һ������ս�ڰ�¹����չ�����𣬰�ɭ�ɿͳ�1��2����������ķ�ȴ̣�����-��˹�Ͱݶ��������򣬱����ɰ��һ�ǡ���ɭ��������ж���6��(��ʤ����15��)����ڼ�����Ӱ���ȴ̽� 7������ȡ��6ʤ��������1��֮��������ǡ�";
		HashMap<String, String> map = new HashMap<String, String>();
		tag.tag(str,map);
		System.out.println(map);
		map = tag.tagFile("./example-data/data-tag.txt");
		System.out.println(map);
		System.out.println("Done!");
	}
}
