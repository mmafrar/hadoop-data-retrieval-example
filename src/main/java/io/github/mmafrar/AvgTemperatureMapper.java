package io.github.mmafrar;

import java.io.IOException;
import org.apache.commons.lang3.StringUtils;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class AvgTemperatureMapper extends Mapper<LongWritable, Text, Text, IntWritable> {

    @Override
    public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String[] line = value.toString().split(","); // split line by comma into array
        String date = line[1]; // extract date part
        String temp = line[10]; // extract temperature

        if (StringUtils.isNumeric(temp)) {
            context.write(new Text(date), new IntWritable(Integer.parseInt(temp)));
        }
    }

}
