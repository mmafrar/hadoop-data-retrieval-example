package io.github.mmafrar;

import java.io.IOException;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class AvgTemperatureReducer extends Reducer<Text, IntWritable, Text, IntWritable> {

    @Override
    public void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
        int sumTemps = 0; // sum of all temps per key
        int numItems = 0; // number of items

        for (IntWritable value : values) {
            sumTemps += value.get();
            numItems += 1;
        }

        if (numItems > 0) {
            context.write(key, new IntWritable(sumTemps / numItems));
        }
    }

}
