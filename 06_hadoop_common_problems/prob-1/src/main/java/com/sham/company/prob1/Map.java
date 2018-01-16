package com.sham.company.prob1;


import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;
import java.util.Objects;

public class Map extends Mapper<LongWritable, Text, Text, Tuple> {

    @Override
    public void map(LongWritable key, Text value, Context context)
            throws IOException, InterruptedException {
        String line = value.toString();
        String[] dataList = line.split(", ");

        try {
            String age = dataList[0];
            String occupation = dataList[8];
            Float weight = Float.parseFloat(dataList[24]);

            if (!Objects.equals(occupation, "Not in universe or children")) {
                context.write(new Text(age), new Tuple(weight, occupation));
            }

        } catch (Exception e) {
            // pass
        }
    }
}