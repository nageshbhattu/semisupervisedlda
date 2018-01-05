/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SGRLDSampler;

import cc.mallet.topics.TopicAssignment;
import cc.mallet.types.Alphabet;
import cc.mallet.types.CrossValidationIterator;
import cc.mallet.types.FeatureSequence;
import cc.mallet.types.Instance;
import cc.mallet.types.InstanceList;
import cc.mallet.types.LabelAlphabet;
import cc.mallet.types.LabelSequence;
import cc.mallet.util.Randoms;
import java.io.File;
import java.util.ArrayList;

/**
 *
 * @author Nagesh Bhattu
 */
public class SGRLDSampler {
    private Alphabet dataAlphabet;
    private int numTypes;
    private int numTopics;
    private double betaSum,beta,alphaSum,alpha;
    private int[][] typeTopicCounts;
    protected ArrayList<TopicAssignment> data;  
    protected LabelAlphabet topicAlphabet; 
    protected int[] tokensPerTopic;	
    protected Randoms random;
    protected int batchSize;
    public SGRLDSampler(){
        
    }
    public void addInstances(String fileName){
        InstanceList ilist = InstanceList.load(new File(fileName));
        dataAlphabet = ilist.getDataAlphabet();
        numTypes = dataAlphabet.size();

        betaSum = beta * numTypes;
        
        typeTopicCounts = new int[numTypes][numTopics];
        
        int doc = 0;

        for (Instance instance : ilist) {
            doc++;

            FeatureSequence tokens = (FeatureSequence) instance.getData();
            LabelSequence topicSequence =
                    new LabelSequence(topicAlphabet, new int[ tokens.size() ]);

            int[] topics = topicSequence.getFeatures();
            for (int position = 0; position < tokens.size(); position++) {

                int topic = random.nextInt(numTopics);
                topics[position] = topic;
                tokensPerTopic[topic]++;

                int type = tokens.getIndexAtPosition(position);
                typeTopicCounts[type][topic]++;
            }

            TopicAssignment t = new TopicAssignment (instance, topicSequence);
            data.add (t);
        }
    }
    public void sample (int iterations){
        CrossValidationIterator cvIter = new CrossValidationIterator(ilist, , r);
    }
    public void 
}
