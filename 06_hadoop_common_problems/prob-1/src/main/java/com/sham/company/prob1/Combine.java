package com.sham.company.prob1;

import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Combine extends Reducer<Text, Tuple, Text, Tuple> {

    @Override
    public void reduce(final Text key,
                       final Iterable<Tuple> values,
                       final Context context)
            throws IOException, InterruptedException {
        HashMap<Text, Float> occupationFrequency = new HashMap<>();

        for (Tuple occupation : values) {
            Text occupationName = occupation.getOccupation();
            Float occupationWeight = occupation.getWeight().get();

            Float count = occupationFrequency.getOrDefault(occupationName, 0f);

            occupationFrequency.put(occupationName, count + occupationWeight);
        }

        for (Map.Entry<Text, Float> entry : occupationFrequency.entrySet()) {
            context.write(key, new Tuple(new FloatWritable(entry.getValue()), entry.getKey()));
        }
    }
}
