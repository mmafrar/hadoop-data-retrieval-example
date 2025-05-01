package io.github.mmafrar;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class AvgTemperatureDriver {

    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        Configuration conf = new Configuration();

        // Create a new Job
        Job job = Job.getInstance(conf, "AvgTemperature");
        job.setJarByClass(AvgTemperatureDriver.class);

        // Set the output key and value types
        job.setOutputKeyClass(Text.class);
        job.setMapOutputValueClass(IntWritable.class);

        // Set the Mapper and Reducer classes
        job.setMapperClass(AvgTemperatureMapper.class);
        job.setReducerClass(AvgTemperatureReducer.class);

        // Set the input and output paths
        FileInputFormat.addInputPath(job, new Path("/Users/mmafrar/DataRetrieval/input"));
        FileOutputFormat.setOutputPath(job, new Path("/Users/mmafrar/DataRetrieval/output"));

        // Delete output path if it exists (to avoid errors)
        FileSystem fs = FileSystem.get(conf);
        Path outputPath = new Path("/Users/mmafrar/DataRetrieval/output");
        if (fs.exists(outputPath)) {
            fs.delete(outputPath, true);
        }

        // Run the job
        System.exit(job.waitForCompletion(true) ? 0 : 1);
    }

}
