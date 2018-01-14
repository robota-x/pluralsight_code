package com.sham.company.prob1;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;

public class Reduce extends Reducer<Text, Text, Text, Text> {

    @Override
    public void reduce(final Text key,
                       final Iterable<Text> values,
                       final Context context)
            throws IOException, InterruptedException {
        HashMap<String, Float> occupationFrequency = new HashMap<>();

        for (Text occupation : values) {
            String[] inputValues = occupation.toString().split("§");
            String occ = inputValues[0];
            Float weight = Float.parseFloat(inputValues[1]);

            Float count = occupationFrequency.getOrDefault(occ, 0f);

            occupationFrequency.put(occ, count + weight);
        }

        String mostCommonOccupation = Collections.max(occupationFrequency.entrySet(), HashMap.Entry.comparingByValue()).getKey();
        context.write(key, new Text(mostCommonOccupation));
    }

}










