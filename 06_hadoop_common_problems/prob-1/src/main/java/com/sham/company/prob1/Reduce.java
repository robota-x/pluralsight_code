package com.sham.company.prob1;

import com.google.gson.Gson;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.HashMap;

public class Reduce extends Reducer<Text, Tuple, Text, Text> {

    @Override
    public void reduce(final Text key,
                       final Iterable<Tuple> values,
                       final Context context)
            throws IOException, InterruptedException {
        HashMap<String, Float> occupationFrequency = new HashMap<>();

        for (Tuple occupation : values) {
            String occupationName = occupation.getOccupation().toString();
            Float occupationWeight = occupation.getWeight().get();

            Float count = occupationFrequency.getOrDefault(occupationName, 0f);

            occupationFrequency.put(occupationName, count + occupationWeight);
        }

        // All unsorted occupations
        Gson gson = new Gson();
        context.write(key, new Text(gson.toJson(occupationFrequency.toString())));
    }
}










