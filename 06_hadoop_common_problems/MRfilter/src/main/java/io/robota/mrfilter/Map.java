package io.robota.mrfilter;


import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class Map extends Mapper<LongWritable, Text, NullWritable, Text> {

    @Override
    public void map(LongWritable key, Text value, Context context)
            throws IOException, InterruptedException {
        String line = value.toString();
        String[] dataList = line.split(",");

        try {
            Float rating = Float.parseFloat(dataList[6]);
            if (rating > 3f) {
                context.write(NullWritable.get(), value);
            }


        } catch (Exception e) {
            System.out.println("except");
        }
    }
}