

import java.util.ArrayList;

import edu.fudan.ml.types.Dictionary;
import edu.fudan.nlp.cn.tag.CWSTagger;
import edu.fudan.nlp.cn.tag.POSTagger;

/**
 * ���Ա�עʹ��ʾ��
 * @author xpqiu
 *
 */
public class test {
	
	static POSTagger tag;

	/**
	 * ������
	 * @param args
	 * @throws IOException 
	 * @throws  
	 */
	public static void main(String[] args) throws Exception {

		System.out.println("�õ�֧�ֵĴ��Ա�ǩ����");
		CWSTagger cws = new CWSTagger("./models/seg.m");
		tag = new POSTagger(cws,"models/pos.m");
		
		System.out.println("�õ�֧�ֵĴ��Ա�ǩ����");
		System.out.println(tag.getSupportedTags());
		System.out.println(tag.getSupportedTags().size());
		System.out.println("\n");
		
		String str = "ý������о��������ˣ��߼������ھ���ѡ���phone�ܺã�";
		String s = tag.tag(str);
		System.out.println("����δ�ִʵľ���");
		System.out.println(s);
		
		System.out.println("ʹ��Ӣ�ı�ǩ");
		tag.SetTagType("en");		
		System.out.println(tag.getSupportedTags());
		System.out.println(tag.getSupportedTags().size());
		s = tag.tag(str);
		System.out.println(s);		
		System.out.println();
		
		CWSTagger cws2 = new CWSTagger("./models/seg.m", new Dictionary("./models/dict.txt"));
		
		//boolֵָ����dict�Ƿ�����cws�ִʣ��ִʺʹ��Կ���ʹ�ò�ͬ�Ĵʵ䣩
		tag = new POSTagger(cws2, "models/pos.m"
				, new Dictionary("./models/dict.txt"), true);//true���滻��֮ǰ��dict.txt
		tag.removeDictionary(false);//���Ƴ��ִʵĴʵ�
		tag.setDictionary(new Dictionary("./models/dict.txt"), false);//����POS�ʵ䣬�ִ�ʹ��ԭ������
		
		String str2 = "ý������о��������ˣ��߼������ھ���ѡ���phone�ܺã�";
		String s2 = tag.tag(str2);
		System.out.println("����δ�ִʵľ��ӣ�ʹ�ôʵ�");
		System.out.println(s2);
		System.out.println();
		
		Dictionary dict = new Dictionary();
		dict.add("ý�����","mypos1","mypos2");
		dict.add("��phone","ר����");
		tag.setDictionary(dict, true);
		String s22 = tag.tag(str2);
		System.out.println(s22);
		System.out.println();
		
		POSTagger tag1 = new POSTagger("./models/pos.m");
		String str1 = "ý����� �о��� ���� �� , �߼� �����ھ� �� ��";
		String[] w = str1.split(" ");
		String[] s1 = tag1.tagSeged(w);
		System.out.println("ֱ�Ӵ���ֺôʵľ���");
		for(int i=0;i<s1.length;i++){
			System.out.print(w[i]+"/"+s1[i]+" ");
		}
		System.out.println("\n");
		
		POSTagger tag3 = new POSTagger("./models/pos.m", new Dictionary("./models/dict.txt"));
		String str3 = "ý����� �о��� ���� �� , �߼� �����ھ� �� �� ";
		String[] w3 = str3.split(" ");
		String[] s3 = tag3.tagSeged(w3);
		System.out.println("ֱ�Ӵ���ֺôʵľ��ӣ�ʹ�ôʵ�");
		for(int i=0;i<s3.length;i++){
			System.out.print(w3[i]+"/"+s3[i]+" ");
		}
		System.out.println("\n");
		
		
		System.out.println("���¹���");
		cws = new CWSTagger("./models/seg.m");
		tag = new POSTagger(cws,"models/pos.m");
		str = "ý������о���������, �߼������ھ����";
		System.out.println(tag.tag(str));
		String[][] sa = tag.tag2Array(str);
		for(int i = 0; i < sa.length; i++) {
			for(int j = 0; j < sa[i].length; j++) {
					System.out.print(sa[i][j] + " ");
			}
			System.out.println();
		}
		
		String s4 = tag.tagFile("./example-data/data-tag.txt");
		System.out.println("\n�����ļ���");
		System.out.println(s4);
	}

}
