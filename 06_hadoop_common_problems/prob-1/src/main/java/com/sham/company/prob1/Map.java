package com.sham.company.prob1;


import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class Map extends Mapper<LongWritable, Text, Text, Text> {

    @Override
    public void map(LongWritable key, Text value, Context context)
            throws IOException, InterruptedException {
        String line = value.toString();
        String[] dataList = line.split(",");

        try {
            String age = dataList[0];
            String occupation = dataList[8];
            Double weight = Double.parseDouble(dataList[24]);

            // ugly stopgap, will increse process time by a lot. Emitted ~15gb of data due to replication.
            for (int i=0; i<=weight; i++) {
                context.write(new Text(age), new Text(occupation));
            }
        } catch (Exception e) {
            // pass
        }
    }
}