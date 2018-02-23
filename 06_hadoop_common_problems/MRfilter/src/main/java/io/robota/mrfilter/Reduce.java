package io.robota.mrfilter;

import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class Reduce extends Reducer<NullWritable, Text, NullWritable, Text> {

    @Override
    public void reduce(final NullWritable key,
                       final Iterable<Text> values,
                       final Context context)
            throws IOException, InterruptedException {
        System.out.println("reading");
        System.out.println(values);

        for (Text value : values) {
            context.write(NullWritable.get(), value);
        }
    }
}










