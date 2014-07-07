

import java.io.File;

import edu.fudan.data.reader.FileReader;
import edu.fudan.data.reader.Reader;
import edu.fudan.ml.classifier.linear.Linear;
import edu.fudan.ml.classifier.linear.OnlineTrainer;
import edu.fudan.ml.eval.Evaluation;
import edu.fudan.ml.types.Instance;
import edu.fudan.ml.types.InstanceSet;
import edu.fudan.ml.types.alphabet.AlphabetFactory;
import edu.fudan.nlp.pipe.NGram;
import edu.fudan.nlp.pipe.Pipe;
import edu.fudan.nlp.pipe.SeriesPipes;
import edu.fudan.nlp.pipe.StringArray2IndexArray;
import edu.fudan.nlp.pipe.Target2Label;

/**
 * �ı�����ʾ��
 * @author xpqiu
 *
 */

public class test3{

	/**
	 * ѵ������·��
	 */
	private static String trainDataPath = "./example-data/text-classification/";

	/**
	 * ģ���ļ�
	 */
	private static String modelFile = "./example-data/text-classification/model.gz";

	public static void main(String[] args) throws Exception {

		
		//�����ֵ������
		AlphabetFactory af = AlphabetFactory.buildFactory();
		
		//ʹ��nԪ����
		Pipe ngrampp = new NGram(new int[] {2,3 });
		//���ַ�����ת�����ֵ�����
		Pipe indexpp = new StringArray2IndexArray(af);
		//��Ŀ��ֵ��Ӧ����������Ϊ���
		Pipe targetpp = new Target2Label(af.DefaultLabelAlphabet());		
		
		//����pipe���
		SeriesPipes pp = new SeriesPipes(new Pipe[]{ngrampp,targetpp,indexpp});
		
		InstanceSet instset = new InstanceSet(pp,af);
		
		//�ò�ͬ��Reader��ȡ��Ӧ��ʽ���ļ�
		Reader reader = new FileReader(trainDataPath,"UTF-8",".data");
		
		//�������ݣ����������ݴ���
		instset.loadThruStagePipes(reader);
				
		float percent = 0.8f;
		
		//�����ݼ���Ϊѵ���ǺͲ��Լ�
		InstanceSet[] splitsets = instset.split(percent);
		
		InstanceSet trainset = splitsets[0];
		InstanceSet testset = splitsets[1];	
		
		/**
		 * ����������
		 */		
		OnlineTrainer trainer = new OnlineTrainer(af);
		Linear pclassifier = trainer.train(trainset);
		pp.removeTargetPipe();
		pclassifier.setPipe(pp);
		af.setStopIncrement(true);
		
		//�����������浽ģ���ļ�
		pclassifier.saveTo(modelFile);	
		pclassifier = null;
		
		//��ģ���ļ����������
		Linear cl =Linear.loadFrom(modelFile);
		
		//��������
		Evaluation eval = new Evaluation(testset);
		eval.eval(cl,1);

		/**
		 * ����
		 */
		System.out.println("��� : �ı�����");
		System.out.println("===================");
		for(int i=0;i<testset.size();i++){
			Instance data = testset.getInstance(i);
			
			Integer gold = (Integer) data.getTarget();
			String pred_label = cl.getStringLabel(data);
			String gold_label = cl.getLabel(gold);
			
			if(pred_label.equals(gold_label))
				System.out.println(pred_label+" : "+testset.getInstance(i).getSource());
			else
				System.err.println(gold_label+"->"+pred_label+" : "+testset.getInstance(i).getSource());
		}
		/**
		 * ������ʹ��
		 */
		String str = "Τ�£����ùھ�����ʧ�� ղ�ʣ�û��Ҳ����ζʧ��";
		System.out.println("============\n���ࣺ"+ str);
		Pipe p = cl.getPipe();
		Instance inst = new Instance(str);
		try {
			//����ת��
			p.addThruPipe(inst);
		} catch (Exception e) {
			e.printStackTrace();
		}
		String res = cl.getStringLabel(inst);
		System.out.println("���"+ res);
		//���ģ���ļ�
		(new File(modelFile)).deleteOnExit();
		System.exit(0);
	}
}